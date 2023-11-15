package com.hillogy.model;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;

public class BookTest {

	private Book book;

	@Before
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