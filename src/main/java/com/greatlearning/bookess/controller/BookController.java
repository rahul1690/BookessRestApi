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

import com.greatlearning.bookess.entity.Book;
import com.greatlearning.bookess.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@PostMapping("/addBook")
	public Book addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}
	
	// Latest added order
	@GetMapping("/getBookList")
	public List<Book> getBookList(){
		return bookService.getBooklist();
	}
	
	// Sorted by Book title
	@GetMapping("/getBookListSortedByTitle")
	public List<Book> getBookListSortedByTitle(){
		return bookService.getBookListSortedByTitle();
	}
	
	@DeleteMapping("/deleteBookById/{id}")
	public String deleteBookById(@PathVariable Long id) {
		bookService.deleteBookById(id);
		return "Deleted the Book";
	}
	
	@GetMapping("/getBookById/{id}")
	public Optional<Book> getBookById(@PathVariable Long id) {
		return bookService.getBookById(id);
	}
	
}
