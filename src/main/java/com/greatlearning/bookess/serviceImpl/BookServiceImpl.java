package com.greatlearning.bookess.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.greatlearning.bookess.entity.Book;
import com.greatlearning.bookess.repository.BookRepository;
import com.greatlearning.bookess.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}
	
	@Override
	public List<Book> getBooklist(){
		return bookRepository.findAll(Sort.by(Direction.DESC,"id"));
	}
	
	@Override
	public List<Book> getBookListSortedByTitle(){
		return bookRepository.findAll(Sort.by(Direction.ASC,"title"));
	}
	
	
	@Override
	public Optional<Book> getBookById(Long id) {
		return bookRepository.findById(id);
	}
	
	@Override
	public void deleteBookById(Long id) {
		bookRepository.deleteById(id);
	}
}
