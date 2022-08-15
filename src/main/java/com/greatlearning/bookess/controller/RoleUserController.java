package com.greatlearning.bookess.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.bookess.entity.Book;
import com.greatlearning.bookess.entity.User;
import com.greatlearning.bookess.service.UserService;

@RestController
@RequestMapping("/user")
public class RoleUserController {

	@Autowired
	UserService userService;
	
	
	@PatchMapping("/addReadLaterBook/{id}")
	public String addReadLaterBook(@PathVariable Long id) {
		return userService.addReadLaterBook(id);
	}
	
	@PatchMapping("/addLikedBook/{id}")
	public String addLikedBook(@PathVariable Long id) {
		return userService.addLikedBook(id);
	}
	
	@GetMapping("/getReadLaterBooks")
	public Set<Book> getReadLaterBook() {
		return userService.getReadLaterBooks();
	}
	
	@GetMapping("/getLikedBooks")
	public Set<Book> getLikedBooks() {
		return userService.getLikedBooks();
	}
	
	
	
	
}
