package com.hillogy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a book data transfer object (DTO). It includes
 * properties for isbn, title, author, and availability of the book.
 */
public class BookDTO {

	@JsonProperty("isbn")
	private String isbn;

	@JsonProperty("title")
	private String title;

	@JsonProperty("author")
	private String author;

	@JsonProperty("available")
	private boolean available;

	/**
	 * 
	 */
	public BookDTO() {}
	
	/**
	 * 
	 * @param isbn
	 * @param title
	 * @param author
	 * @param available
	 */
	public BookDTO(String isbn, String title, String author, boolean available) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.available = available;
	}

	/**
	 * Gets the ISBN of the book.
	 * 
	 * @return the ISBN of the book.
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * Sets the ISBN of the book.
	 * 
	 * @param isbn the new ISBN of the book.
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * Gets the title of the book.
	 * 
	 * @return the title of the book.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the book.
	 * 
	 * @param title the new title of the book.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the author of the book.
	 * 
	 * @return the author of the book.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author of the book.
	 * 
	 * @param author the new author of the book.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Checks if the book is available.
	 * 
	 * @return true if the book is available, false otherwise.
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * Sets the availability of the book.
	 * 
	 * @param available the new availability of the book.
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
}