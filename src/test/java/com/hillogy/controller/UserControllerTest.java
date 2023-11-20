package com.hillogy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hillogy.dto.UserDTO;
import com.hillogy.exception.UserAlreadyExistsException;
import com.hillogy.model.User;
import com.hillogy.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testCheckOutBook() {
        String username = "testUser";
        String isbn = "1234567890";

        when(userService.checkOutBook(username, isbn)).thenReturn(true);

        ResponseEntity<Boolean> response = userController.checkOutBook(username, isbn);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
    }
    
    @Test
    void testCheckOutBookThrowsException() {
        String username = "testUser";
        String isbn = "1234567890";

        when(userService.checkOutBook(username, isbn)).thenThrow(new RuntimeException());

        ResponseEntity<Boolean> response = userController.checkOutBook(username, isbn);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testReturnBook() {
        String username = "testUser";
        String isbn = "1234567890";

        when(userService.returnBook(username, isbn)).thenReturn(true);

        ResponseEntity<Boolean> response = userController.returnBook(username, isbn);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

    @Test
    void testReturnBookThrowsException() {
        String username = "testUser";
        String isbn = "1234567890";

        when(userService.returnBook(username, isbn)).thenThrow(new RuntimeException());

        ResponseEntity<Boolean> response = userController.returnBook(username, isbn);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    
    @Test
    void testAddUser() {
        UserDTO dto = new UserDTO();
        dto.setUsername("testUser");

        User user = new User();
        user.setUsername(dto.getUsername());

        when(userService.addUser(dto)).thenReturn(user);

        ResponseEntity<?> response = userController.addUser(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testAddUserAlreadyExists() {
        UserDTO dto = new UserDTO();
        dto.setUsername("testUser");
        

        when(userService.addUser(dto)).thenThrow(new UserAlreadyExistsException("User already exists"));

        ResponseEntity<?> response = userController.addUser(dto);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("User already exists", response.getBody());
    }

    @Test
    void testAddUserThrowsException() {
        UserDTO dto = new UserDTO();
        dto.setUsername("testUser");

        when(userService.addUser(dto)).thenThrow(new RuntimeException());

        ResponseEntity<?> response = userController.addUser(dto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred while processing the request.", response.getBody());
    }
}
