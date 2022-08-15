package com.greatlearning.bookess.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import com.greatlearning.bookess.entity.Book;
import com.greatlearning.bookess.entity.User;
import com.greatlearning.bookess.model.LoginDto;
import com.greatlearning.bookess.repository.BookRepository;
import com.greatlearning.bookess.repository.UserRepository;
import com.greatlearning.bookess.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	@Override
	public String login(LoginDto loginDto) {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println(authentication.getName());
		String username = authentication.getName();
		UserDetails user = userDetailsService.loadUserByUsername(username);
		return "Welcome "+authentication.getName().toUpperCase()+" "+user.getAuthorities();
	}
	
	@Override
	public String registerUser(User user) {
		User isThereAUserWithUsername = userRepository.findByUsername(user.getUsername());
		User isThereAUserWithEmail = userRepository.findByEmail(user.getEmail());
		
		if(isThereAUserWithUsername != null) {
			return "User with this username "+user.getUsername()+" already exists!";
		}
		else if(isThereAUserWithEmail != null)
		   return "User with this Email id "+user.getEmail()+" already exists!";
		
		else {
			user.setRoles("ROLE_USER,");
			System.out.println(user.getRoles());
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return "You have registered successfully";
		}
	}
	
	@Override
	public List<User> getUserList(){
		return userRepository.findAll();
	}
	
	@Override
	public String deleteUserById(long id) {
		userRepository.deleteById(id);
		return "Deleted the user with id "+id;
	}

	@Override
	public Optional<User> getUserById(long id) {
		return userRepository.findById(id);
	}
	
	@Override
	public User updateUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		getUserById(user.getId());
		return userRepository.save(user);
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User getUserDetails() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return userRepository.findByUsername(username);
	}
	
	@Override
	public String addReadLaterBook(Long id) {
		User user = getUserDetails();
		Optional<Book> optionalbook = bookRepository.findById(id);
		if(optionalbook.isPresent()) {
			Book book = optionalbook.get();
			Set<Book> books = new HashSet<>();
			books.addAll(user.getReadLaterBooks());
			books.add(book);
			user.setReadLaterBooks(books);
			userRepository.save(user);
			return "Book added successfully";
		}
		else {
			return "Book Not Found!";
		}
	}
	
	@Override
	public String addLikedBook(Long id) {
		User user = getUserDetails();
		Optional<Book> optionalbook = bookRepository.findById(id);
		if(optionalbook.isPresent()) {
			Book book = optionalbook.get();
			Set<Book> books = new HashSet<>();
			books.addAll(user.getLikedBooks());
			books.add(book);
			user.setLikedBooks(books);
			userRepository.save(user);
			return "Book added successfully";
		}
		else {
			return "Book not found!";
		}
	}
	
	@Override
	public Set<Book> getReadLaterBooks(){
		User user = getUserDetails();
		return user.getReadLaterBooks();
	}
	
	@Override
	public Set<Book> getLikedBooks(){
		User user = getUserDetails();
		return user.getLikedBooks();
	}
	
	@Override
	public String makeAUserAdminById(Long id) {
		Optional<User> userOptional = getUserById(id);
		if(userOptional.isPresent())
		{
			User user = new User();
			user = userOptional.get();
			user.setRoles(user.getRoles().concat("ROLE_ADMIN,"));
			userRepository.save(user);
			return "User with id "+id+ " is ADMIN now";
		}
		else return "User Not Found with id " + id; 
	}
	
	@Override
	public String removeFromAdmin(Long id) {
		Optional<User> userOptional = getUserById(id);
		if(userOptional.isPresent()) {
			User user = new User();
			user = userOptional.get();
			String[] roles =  user.getRoles().split(",");
			String newRoles="";
			for(int i=0;i<roles.length;i++) {
				if(roles[i].equals("ROLE_ADMIN")) {
					if(roles.length == 1) {
						return "You cannot remove Main Admin from Admin Authority";
					}
					continue;
				}
				newRoles =  newRoles.concat(roles[i]).concat(",");
			}
			System.out.println(newRoles);
			user.setRoles(newRoles);
			userRepository.save(user);
			return "Removed from Admin authority";
		}
		else {
			return "User Not Found!";
		}
	}
	
	@Override
	public void logOut(HttpServletRequest request ,HttpServletResponse response,Authentication auth) {
		new SecurityContextLogoutHandler().logout(request,response,auth);
	}
}
