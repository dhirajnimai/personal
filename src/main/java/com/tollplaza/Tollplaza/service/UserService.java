package com.tollplaza.Tollplaza.service;

import java.util.List;

import com.tollplaza.Tollplaza.dto.TollMasterDto;
import com.tollplaza.Tollplaza.entity.TollMUser;
import com.tollplaza.Tollplaza.model.Users;

public interface UserService {

	List<TollMasterDto> listOfUsersDetails();
	
	public List<TollMUser> getUsera(TollMUser user);

	TollMUser createUser(TollMasterDto user);

	TollMUser getUserById(String userId);

	
	
}
