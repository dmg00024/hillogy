package com.hillogy.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    public void testBookConstructor() {
        // Create an instance of Book using the constructor
        Book book = new Book("Test Title", "Test Author", "1234567890", true);

        // Assert that the fields of the Book instance are correctly set
        assertEquals("Test Title", book.getTitle());
        assertEquals("Test Author", book.getAuthor());
        assertEquals("1234567890", book.getIsbn());
        assertTrue(book.isAvailable());
    }

    @Test
    public void testSettersAndGetters() {
        // Create an instance of Book using the no-arg constructor
        Book book = new Book();

        // Test setters
        book.setTitle("New Title");
        book.setAuthor("New Author");
        book.setIsbn("0987654321");
        book.setAvailable(false);

        // Test getters
        assertEquals("New Title", book.getTitle());
        assertEquals("New Author", book.getAuthor());
        assertEquals("0987654321", book.getIsbn());
        assertFalse(book.isAvailable());
    }

    @Test
    void toStringTest() {
        // Arrange
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setIsbn("123");
        book.setAvailable(true);

        // Act
        String result = book.toString();

        // Assert
        String expected = "Book{title='Title', author='Author', isbn='123', isAvailable=true}";
        assertEquals(expected, result);
    }
}