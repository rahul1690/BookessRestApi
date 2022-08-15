package com.greatlearning.bookess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.bookess.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
