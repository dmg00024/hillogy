package com.hillogy.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {

	private Book book;

	@BeforeEach
	public void setUp() {
		book = new Book("Title", "Author", "ISBN", true);
	}

	@Test
	public void testGetTitle() {
		assertEquals("Title", book.getTitle());
	}

	@Test
	public void testGetAuthor() {
		assertEquals("Author", book.getAuthor());
	}

	@Test
	public void testGetIsbn() {
		assertEquals("ISBN", book.getIsbn());
	}

	@Test
	public void testIsAvailable() {
		assertTrue(book.isAvailable());
	}

	@Test
	public void testSetAvailable() {
		book.setAvailable(false);
		assertFalse(book.isAvailable());
	}

	@Test
	public void testToString() {
		String expected = "Book{title='Title', author='Author', isbn='ISBN', isAvailable=true}";
		assertEquals(expected, book.toString());
	}
}