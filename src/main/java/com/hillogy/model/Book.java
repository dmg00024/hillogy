package com.hillogy.model;

public class Book {

	private String title;
	private String author;
	private String isbn;
	private boolean isAvailable;

	/**
	 * 
	 * @param title
	 * @param author
	 * @param isbn
	 * @param isAvailable
	 */
	public Book(String title, String author, String isbn, boolean isAvailable) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.isAvailable = isAvailable;
	}

	/**
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @return
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * 
	 * @return
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isAvailable() {
		return isAvailable;
	}

	/**
	 * 
	 * @param available
	 */
	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Book{" + "title='" + title + '\'' + ", author='" + author + '\'' + ", isbn='" + isbn + '\''
				+ ", isAvailable=" + isAvailable + '}';
	}
}
