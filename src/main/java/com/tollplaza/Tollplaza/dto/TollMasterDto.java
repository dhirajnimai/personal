package com.tollplaza.Tollplaza.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tollplaza.Tollplaza.entity.TollplazaUserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TollMasterDto {
	private String userId;
	private String firstName;
	private String lastName;
	private String phone;
	private String emailId;
	private String companyName;
	private String password;
	private List<RoleDto> userRole;
	
	@JsonIgnore
	private Set<TollplazaUserRole> userRoles=new HashSet<>();
	
}
