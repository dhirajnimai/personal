package com.tollplaza.Tollplaza.entity;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="company_master_login")
public class CompanyMasterLoginEntity extends AbstractModel implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loginId;
	
	 @JsonBackReference
	 @JoinColumn(name = "userId", referencedColumnName = "userId")
	 @ManyToOne
    private TollMUser userId;
	
	private String companyPassword;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		
		Set<Authority> set=new HashSet<>();
		this.userId.getUserRoles().forEach(usrRole ->{
			set.add(new Authority(usrRole.getRole().getRollName()));
		});
		
		System.out.println("======================="+set.size());
		for(Authority s:set) {
			System.out.println("\"======================= companyMaster entity"+s.toString());
		}
		return set;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.companyPassword;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userId.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
