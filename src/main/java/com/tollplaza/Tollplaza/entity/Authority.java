package com.tollplaza.Tollplaza.entity;

import org.springframework.security.core.GrantedAuthority;

import lombok.ToString;

@ToString
public class Authority implements GrantedAuthority {

	private String authority;
	
	
	
	public Authority(String authority) {
		super();
		this.authority = authority;
	}


	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}

}
