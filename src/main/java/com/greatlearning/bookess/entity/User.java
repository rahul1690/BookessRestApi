package com.greatlearning.bookess.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false,unique = true)
	private String username;

	@Column(nullable = false)
	private String password;
	
	//we store comma separated book id's inside readLaterBooks and likedBooks

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "user_read_later",
	joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "book_id",referencedColumnName = "id"))
	private Set<Book> readLaterBooks;
	

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "user_loved_book",
	joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "book_id",referencedColumnName = "id"))
	private Set<Book> likedBooks;
	
	
	private String roles;

}
