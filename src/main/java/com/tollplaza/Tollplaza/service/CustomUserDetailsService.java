package com.tollplaza.Tollplaza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tollplaza.Tollplaza.entity.CompanyMasterLoginEntity;
import com.tollplaza.Tollplaza.repository.CompanyMLoginRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	CompanyMLoginRepository loginRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//load user from database
		CompanyMasterLoginEntity mLogin=loginRepo.findByUserId(username).orElseThrow(() -> new RuntimeException("User not found"));
		return mLogin;
	}

}
