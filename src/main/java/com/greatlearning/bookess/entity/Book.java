package com.greatlearning.bookess.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "books")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String title;
	
	private String author;
	
	private int pages;
	
	private String language;
}
