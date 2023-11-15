package com.hillogy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Book {

	@Id
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private boolean isAvailable;

    public Book(String title, String author, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }
    
    public Book() {}

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
