package com.hillogy.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    // Step 1: Create an instance of User
    private User user = new User();

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
}
