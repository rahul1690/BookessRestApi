package com.greatlearning.bookess.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import com.greatlearning.bookess.entity.Book;
import com.greatlearning.bookess.entity.User;
import com.greatlearning.bookess.model.LoginDto;

public interface UserService {

	String login(LoginDto loginDto);

	String registerUser(User user);

	List<User> getUserList();

	String deleteUserById(long id);

	Optional<User> getUserById(long id);

	User updateUser(User user);

	User findByUsername(String username);

	User getUserDetails();

	String addReadLaterBook(Long id);

	String addLikedBook(Long id);

	Set<Book> getReadLaterBooks();

	Set<Book> getLikedBooks();

	String makeAUserAdminById(Long id);
	
	String removeFromAdmin(Long id);

	void logOut(HttpServletRequest request, HttpServletResponse response, Authentication auth);

}