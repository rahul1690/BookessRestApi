package com.greatlearning.bookess.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.greatlearning.bookess.entity.User;
import com.greatlearning.bookess.service.UserService;

@RestController
@RequestMapping("/admin")
public class RoleAdminController {

	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public String userRegistration(@RequestBody User user) {
		user.setRoles("ROLE_ADMIN");
		return userService.registerUser(user);
	}
	
	@GetMapping("/getUserList")
	public List<User> getUserList(){
		return userService.getUserList();
	}
	
	@GetMapping("/getUserById/{id}")
	public Optional<User> getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@DeleteMapping("/deleteUserById/{id}")
	public String deleteUserById(@PathVariable Long id) {
		return userService.deleteUserById(id);
	}
	
	@PostMapping("/updateUserById")
	public User updateUserById(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@GetMapping("/getMyDetails")
	public User getMyDetails() {
		return userService.getUserDetails();
	}
	
	@GetMapping("/makeAUserAdmin/{id}")
	public String makeAUserAdmin(@PathVariable Long id) {
		return userService.makeAUserAdminById(id);
	}
	
	@GetMapping("/removeFromAdmin/{id}")
	public String removeFromAdmin(@PathVariable Long id) {
		return userService.removeFromAdmin(id);
	}
}
