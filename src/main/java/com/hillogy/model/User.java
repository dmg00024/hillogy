package com.hillogy.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	private String username;
	@Column(nullable = false)
	private String password;
	
	@OneToMany
	private List<Book> books;

	/**
	 * 
	 */
	public User() {
		this.books = new ArrayList<>();
	}

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.books = new ArrayList<>();
	}

	/**
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return
	 */
	public List<Book> getBooks() {
		return books;
	}

	/**
	 * 
	 * @param books
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}
}