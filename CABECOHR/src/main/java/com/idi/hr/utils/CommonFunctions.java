package com.idi.hr.utils;

import java.io.Serializable;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class CommonFunctions implements Serializable {
 public String returnUserName () {
	
	 String username =null;
	 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			System.out.println("username is in CommonFunction " + username);
		} else {
			username = principal.toString();
		} 
		
		//anonymousUser
		if ( username.equals("anonymousUser")) {
			username=null;
		}
	return username;	
 }
 
}
