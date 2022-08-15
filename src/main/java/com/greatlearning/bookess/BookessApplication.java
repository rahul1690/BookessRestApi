package com.greatlearning.bookess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.bookess.entity.Book;
import com.greatlearning.bookess.entity.User;
import com.greatlearning.bookess.repository.BookRepository;
import com.greatlearning.bookess.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableJpaRepositories( basePackageClasses = UserRepository.class)
public class BookessApplication implements CommandLineRunner{

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookessApplication.class, args);
	}
	
	

	@Override
	public void run(String... args) throws Exception {
		//add books
//		Set<Book> books = new HashSet<>();
//		Book book1 = Book.builder().title("Molloy, Malone Dies, The Unnamable, the trilogy").author("Samuel Beckett").language("French, English").pages(256).build();
//		Book book2 = Book.builder().title("Things Fall Apart").author("Chinua Achebe").language("Danish").pages(784).build();
//		Book book3 = Book.builder().title("The Decameron").author("Giovanni Boccaccio").language("Italian").pages(1024).build();
//		books.add(book1);
//		books.add(book2);
//		books.add(book3);
//		
//		bookRepository.saveAll(books);
//		log.info("books ->{}",bookRepository.findAll());
//		
//		
//		// add admin
//		User admin = User.builder().username("admin").email("admin@gmail.com").password(passwordEncoder.encode("admin")).name("admin").roles("ROLE_ADMIN,").build();
//		userRepository.save(admin);
//		
//		// add user
//		User user = User.builder().username("user").email("user@gmail.com").password(passwordEncoder.encode("user")).name("user").roles("ROLE_USER,").build();
//		userRepository.save(user);
//		
//		ArrayList<User> users = new ArrayList<>();
//		users.addAll(userRepository.findAll());
//		log.info("user ->{}",users);
	}

}
