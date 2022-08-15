package com.greatlearning.bookess.service;

import java.util.List;
import java.util.Optional;

import com.greatlearning.bookess.entity.Book;

public interface BookService {

	Book addBook(Book book);

	List<Book> getBooklist();

	Optional<Book> getBookById(Long id);

	void deleteBookById(Long id);

	List<Book> getBookListSortedByTitle();
}