package com.greatlearning.bookess.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.bookess.entity.User;
import com.greatlearning.bookess.model.LoginDto;
import com.greatlearning.bookess.service.UserService;

@RestController
public class AuthController {

	@Autowired
	UserService userService;

	// By default registering as a user role
	// for admin_role only admin can give access
	@PostMapping("/register")
	public String userRegistration(@RequestBody User user) {
		return userService.registerUser(user);
	}

	@PostMapping("/login")
	public String user(@RequestBody LoginDto loginDto) {

		return userService.login(loginDto);

	}

	@GetMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			userService.logOut(request, response, auth);
		}
	}
	
	// Getting current user details
	@GetMapping("/getMyDetails")
	public User getMyDetails() {
		return userService.getUserDetails();
	}
}
