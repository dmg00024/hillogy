package com.hillogy.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hillogy.exception.NoBooksFoundException;
import com.hillogy.exception.NoUserFoundException;
import com.hillogy.exception.UnableToCheckOutBookException;
import com.hillogy.exception.UnableToRemoveBookException;
import com.hillogy.exception.UnableToReturnBookException;
import com.hillogy.model.Book;
import com.hillogy.model.User;
import com.hillogy.repository.BookRepository;
import com.hillogy.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void testCheckOutBookSuccess() {
        User user = new User();
        Book book = new Book();

        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        when(bookRepository.findById(anyString())).thenReturn(Optional.of(book));

        assertTrue(userService.checkOutBook("username", "isbn"));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testCheckOutBookUserNotFound() {
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(UnableToCheckOutBookException.class, () -> userService.checkOutBook("username", "isbn"));
    }

    @Test
    public void testCheckOutBookBookNotFound() {
        User user = new User();

        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        when(bookRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(UnableToCheckOutBookException.class, () -> userService.checkOutBook("username", "isbn"));
    }
    
    @Test
    public void testReturnBookSuccess() {
        User user = new User();
        Book book = new Book();
        user.getBooks().add(book);

        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        when(bookRepository.findById(anyString())).thenReturn(Optional.of(book));

        assertTrue(userService.returnBook("username", "isbn"));
        assertFalse(user.getBooks().contains(book));
    }

    @Test
    public void testReturnBookUserNotFound() {
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(NoUserFoundException.class, () -> userService.returnBook("username", "isbn"));
    }

    @Test
    public void testReturnBookBookNotFound() {
        User user = new User();

        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        when(bookRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(NoBooksFoundException.class, () -> userService.returnBook("username", "isbn"));
    }

    @Test
    public void testReturnBookNotCheckedOut() {
        User user = new User();
        Book book = new Book();

        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        when(bookRepository.findById(anyString())).thenReturn(Optional.of(book));

        assertThrows(UnableToReturnBookException.class, () -> userService.returnBook("username", "isbn"));
    }
}
