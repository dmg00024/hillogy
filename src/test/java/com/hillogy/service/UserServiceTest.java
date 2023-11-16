package com.hillogy.service;

import static org.junit.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hillogy.exceptions.NoBooksFoundException;
import com.hillogy.model.Book;
import com.hillogy.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCheckOutBookWhenBookNotFound() {
        // Arrange
        Mockito.when(bookRepository.findById("123")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoBooksFoundException.class, () -> userService.checkOutBook("123"));
    }

    @Test
    public void testCheckOutBookWhenBookNotAvailable() {
        // Arrange
        Book unavailableBook = new Book("456", "Unavailable Book", "ISBN", false);
        Mockito.when(bookRepository.findById("456")).thenReturn(Optional.of(unavailableBook));

        // Act & Assert
        assertThrows(NoBooksFoundException.class, () -> userService.checkOutBook("456"));
    }

    @Test
    public void testCheckOutBookSuccess() {
        // Arrange
    	Book availableBook = new Book("789", "Available Book", "ISBN", true);
        Mockito.when(bookRepository.findById("789")).thenReturn(Optional.of(availableBook));

        // Act
        userService.checkOutBook("789");

        // Assert
        Mockito.verify(bookRepository, Mockito.times(1)).save(availableBook);
        assert !availableBook.isAvailable(); // Ensure the book is marked as unavailable
    }

    @Test
    public void testReturnBookWhenBookNotFound() {
        // Arrange
        Mockito.when(bookRepository.findById("123")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoBooksFoundException.class, () -> userService.returnBook("123"));
    }

    @Test
    public void testReturnBookSuccess() {
        // Arrange
        Book checkedOutBook = new Book("789", "Checked Out Book", "ISBN", false);
        Mockito.when(bookRepository.findById("789")).thenReturn(Optional.of(checkedOutBook));

        // Act
        userService.returnBook("789");

        // Assert
        Mockito.verify(bookRepository, Mockito.times(1)).save(checkedOutBook);
        assert checkedOutBook.isAvailable(); // Ensure the book is marked as available
    }
}
