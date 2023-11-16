package com.hillogy.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class UserTest {

    // Step 1: Create an instance of User
    private User user = new User();
    
    @Test
    public void testUserConstructor() {
        // Step 1: Create an instance of User using the constructor
        User user = new User("TestUser", "TestPassword");

        // Step 2: Assert that the fields of the User instance are correctly set
        assertEquals("TestUser", user.getUsername());
        assertEquals("TestPassword", user.getPassword());
    }

    @Test
    public void testGetUsername() {
        user.setUsername("TestUser");
        assertEquals("TestUser", user.getUsername());
    }

    @Test
    public void testSetUsername() {
        user.setUsername("NewUser");
        assertEquals("NewUser", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        user.setPassword("TestPassword");
        assertEquals("TestPassword", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("NewPassword");
        assertEquals("NewPassword", user.getPassword());
    }
    
    @Test
    public void testGetBooks() {
        User user = new User();
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        user.setBooks(books);

        assertEquals(books, user.getBooks());
    }

    @Test
    public void testSetBooks() {
        User user = new User();
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        user.setBooks(books);

        assertEquals(books, user.getBooks());
    }
}
