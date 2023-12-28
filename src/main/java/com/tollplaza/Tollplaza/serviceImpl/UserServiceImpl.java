package com.tollplaza.Tollplaza.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tollplaza.Tollplaza.dto.RoleDto;
import com.tollplaza.Tollplaza.dto.TollMasterDto;
import com.tollplaza.Tollplaza.entity.CompanyMasterLoginEntity;
import com.tollplaza.Tollplaza.entity.TollMRole;
import com.tollplaza.Tollplaza.entity.TollMUser;
import com.tollplaza.Tollplaza.entity.TollplazaUserRole;
import com.tollplaza.Tollplaza.model.Users;
import com.tollplaza.Tollplaza.repository.CompanyMLoginRepository;
import com.tollplaza.Tollplaza.repository.TollMRepository;
import com.tollplaza.Tollplaza.repository.TollMRoleRepository;
import com.tollplaza.Tollplaza.repository.TollUserRoleRepository;
import com.tollplaza.Tollplaza.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	TollMRepository userRepo;
	
	@Autowired
	CompanyMLoginRepository loginRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	TollMRoleRepository roleRepo;
	
	@Autowired
	TollUserRoleRepository usrRoleRepo;
	
	@Autowired
	Utility utilCls;

	@Override
	public List<TollMasterDto> listOfUsersDetails() {
		// TODO Auto-generated method stub
	List<TollMUser> usrList=userRepo.findAllDetails();
	List<TollMasterDto> usrListDto=usrList.stream().map(usr ->{
		TollMasterDto dto=new TollMasterDto();
		dto.setUserId(usr.getUserId());
		dto.setEmailId(usr.getEmailId());
		dto.setCompanyName(usr.getCompanyName());
		return dto;
	}).collect(Collectors.toList());
	
	
	
	
	System.out.println("List of size"+usrList.size());
		return usrListDto;
	}

	@Override
	public TollMUser createUser(TollMasterDto user) {
		// TODO Auto-generated method stub
		TollMUser userDetails=new TollMUser();
		Optional<TollMUser> usrDetails=userRepo.findByEmailId(user.getEmailId());
		
		userDetails.setUserId(utilCls.username("TOLLCOMPANY", "NEWBANK"));
		userDetails.setCompanyName(user.getCompanyName());
		userDetails.setEmailId(user.getEmailId());
		userDetails.setFirstName(user.getFirstName());
		userDetails.setLastName(user.getLastName());
		userDetails.setEmailId(user.getEmailId());
		userRepo.save(userDetails);
		Optional<CompanyMasterLoginEntity> login=loginRepo.findByUserId(userDetails.getUserId());
		if(login.isPresent()) {
			CompanyMasterLoginEntity loginEntity=login.get();
			loginRepo.save(loginEntity);
		}else {
			CompanyMasterLoginEntity newUsrLogin=new CompanyMasterLoginEntity();
			newUsrLogin.setUserId(userDetails);
			newUsrLogin.setCompanyPassword(passwordEncoder.encode(user.getPassword()));
			Date currentTime = new Date();
			newUsrLogin.setCreatedOn(currentTime);
			newUsrLogin.setCreatedBy(userDetails.getCompanyName());
			loginRepo.save(newUsrLogin);
		}
		List<TollMRole> rolNList=new ArrayList<>();
		List<TollplazaUserRole> roleList=new ArrayList<>();
		if(user.getUserRole().size()>0) {
				for(RoleDto role:user.getUserRole()) {
				TollMRole actRole=new TollMRole();
				Optional<TollMRole> roleMaster=roleRepo.findByRoleName(role.getRollName());
				if(roleMaster.isPresent()) {
				TollMRole roleN=new TollMRole();
				roleN.setRollName(roleMaster.get().getRollName());
				roleN.setRoleId(roleMaster.get().getRoleId());
				roleN.setRollShortName(roleMaster.get().getRollShortName());
					roleRepo.save(roleN);
					TollplazaUserRole usrRoleDb=new TollplazaUserRole();
					usrRoleDb.setUser(userDetails);
					usrRoleDb.setRole(roleN);
					usrRoleDb.setStatus("ACTIVE");
					roleList.add(usrRoleDb);
				}else {
					actRole.setRollName(role.getRollName());
					actRole.setRollShortName(role.getRollShortNam());
					roleRepo.save(actRole);
					
					TollplazaUserRole usrRoleDb=new TollplazaUserRole();
					usrRoleDb.setUser(userDetails);
					usrRoleDb.setRole(actRole);
					//usrRoleDb.setRole(new TollMRole());
					usrRoleDb.setStatus("ACTIVE");
					roleList.add(usrRoleDb);
				}
				
			}
			usrRoleRepo.saveAll(roleList);
		}
		
		return userDetails;
	}

	@Override
	public List<TollMUser> getUsera(TollMUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TollMUser getUserById(String userId) {
		// TODO Auto-generated method stub
		Optional<TollMUser> user=userRepo.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}else {
			return null;
		}
		
	}

}
