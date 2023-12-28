package com.tollplaza.Tollplaza.controller;

import java.security.Principal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tollplaza.Tollplaza.dto.GenericResponse;
import com.tollplaza.Tollplaza.dto.TollMasterDto;
import com.tollplaza.Tollplaza.entity.CompanyMasterLoginEntity;
import com.tollplaza.Tollplaza.entity.TollMUser;
import com.tollplaza.Tollplaza.jwtsecurity.JwtHelper;
import com.tollplaza.Tollplaza.model.JwtRequest;
import com.tollplaza.Tollplaza.model.JwtResponse;
import com.tollplaza.Tollplaza.model.Users;
import com.tollplaza.Tollplaza.service.UserService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class AuthController {
	
	@Autowired
	UserService usrService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtHelper helper;
	
	@Autowired
	GenericResponse genResponse;
	
	
	//http://localhost:8081/admin/user
	
	@GetMapping("/user")
	@CrossOrigin("*")
	public ResponseEntity<?> login() {
		System.out.println("getting users"); 
		List<TollMasterDto> usrList=usrService.listOfUsersDetails();
		genResponse.setList(usrList);
		
		return new ResponseEntity<Object>(genResponse,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/current-user")
	public ResponseEntity<Object> getLoggedInUser(Principal principal) {
		System.out.println("Current user api");
		return new ResponseEntity<Object>( (CompanyMasterLoginEntity) this.userDetailsService.loadUserByUsername(principal.getName()),HttpStatus.OK);
		
	}
	
	//creating user
	@CrossOrigin(origins = "*", allowedHeaders = "*") 
	@PostMapping("/create-user")
	public ResponseEntity<?>createOrUpdateUser(@RequestBody TollMasterDto tollDto) {
		
		try {
			TollMUser usrDetails=usrService.createUser(tollDto);
			System.out.println(usrDetails.toString());
		genResponse.setData(usrDetails);
		genResponse.setErrMessage("User created!!");
		 return new ResponseEntity<>(genResponse, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			genResponse.setErrMessage("Fail to save the entity !!");
			 return new ResponseEntity<>(genResponse, HttpStatus.BAD_REQUEST);
			
		}
		
		
	}
	
	
	
	@GetMapping("/get-User/{userID}")
	public ResponseEntity<?> getUserbyUserId(@PathVariable(value = "userID") String userID){
	TollMUser userDetails=usrService.getUserById(userID);
	if(userDetails==null) {
		genResponse.setData(null);
		genResponse.setErrMessage("Fail");
	}	else {
		genResponse.setData(userDetails);
		genResponse.setErrMessage("Success");
	}
	
		return new ResponseEntity<>(genResponse,HttpStatus.OK);
	}
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*") 
	 @PostMapping("/login")
	    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
		System.out.println("Current Login user api");
	        this.doAuthenticate(request.getEmail(), request.getPassword());
	      

	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	        String token = this.helper.generateToken(userDetails);

	        JwtResponse response = JwtResponse.builder()
	                .token(token)
	                .username(userDetails.getUsername()).build();
	        System.out.println("latest token fromlogin"+response.getToken());
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	    private void doAuthenticate(String email, String password) {
       System.out.println("===============username"+email);
         System.out.println("=================password"+password);
	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
	        try {
	            manager.authenticate(authentication);


	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException(" Invalid Username or Password  !!");
	        }

	    }

	    @ExceptionHandler(BadCredentialsException.class)
	    public String exceptionHandler() {
	        return "Credentials Invalid !!";
	    }


	
}
