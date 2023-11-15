package com.hillogy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hillogy.exceptions.BookAlreadyExistsException;
import com.hillogy.exceptions.NoBooksFoundException;
import com.hillogy.model.Book;
import com.hillogy.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {

    @InjectMocks
    private LibraryService libraryService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testAddBook() {
        Book book = new Book();
        book.setIsbn("123");
        when(bookRepository.existsById("123")).thenReturn(false);
        when(bookRepository.save(book)).thenReturn(book);

        libraryService.addBook(book);

        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testAddBookAlreadyExists() {
        Book book = new Book();
        book.setIsbn("123");
        when(bookRepository.existsById("123")).thenReturn(true);

        assertThrows(BookAlreadyExistsException.class, () -> libraryService.addBook(book));
    }

    @Test
    public void testRemoveBook() {
        when(bookRepository.existsById("123")).thenReturn(true);

        libraryService.removeBook("123");

        verify(bookRepository, times(1)).deleteById("123");
    }

    @Test
    public void testRemoveBookNotFound() {
        when(bookRepository.existsById("123")).thenReturn(false);

        assertThrows(NoBooksFoundException.class, () -> libraryService.removeBook("123"));
    }
    
    @Test
    public void testGetBookByTitle() {
        Book book = new Book();
        book.setTitle("Test Title");
        when(bookRepository.findByTitle("Test Title")).thenReturn(book);

        Book returnedBook = libraryService.getBookByTitle("Test Title");

        assertEquals(book, returnedBook);
    }

    @Test
    public void testGetBookByTitleNotFound() {
        when(bookRepository.findByTitle("Test Title")).thenReturn(null);

        assertThrows(NoBooksFoundException.class, () -> libraryService.getBookByTitle("Test Title"));
    }
    
    @Test
    public void testGetBookByAuthor() {
        Book book1 = new Book();
        book1.setAuthor("Test Author");
        Book book2 = new Book();
        book2.setAuthor("Test Author");
        List<Book> books = Arrays.asList(book1, book2);
        when(bookRepository.findByAuthor("Test Author")).thenReturn(books);

        List<Book> returnedBooks = libraryService.getBookByAuthor("Test Author");

        assertEquals(books, returnedBooks);
    }

    @Test
    public void testGetBookByAuthorNotFound() {
        when(bookRepository.findByAuthor("Test Author")).thenReturn(Collections.emptyList());

        assertThrows(NoBooksFoundException.class, () -> libraryService.getBookByAuthor("Test Author"));
    }
    
    @Test
    public void testGetBookByIsbn() {
        Book book = new Book();
        book.setIsbn("123");
        when(bookRepository.findById("123")).thenReturn(Optional.of(book));

        Book returnedBook = libraryService.getBookByIsbn("123");

        assertEquals(book, returnedBook);
    }

    @Test
    public void testGetBookByIsbnNotFound() {
        when(bookRepository.findById("123")).thenReturn(Optional.empty());

        assertThrows(NoBooksFoundException.class, () -> libraryService.getBookByIsbn("123"));
    }
    
    @Test
    public void testGetAvailableBooks() {
        Book book1 = new Book();
        book1.setAvailable(true);
        Book book2 = new Book();
        book2.setAvailable(true);
        List<Book> books = Arrays.asList(book1, book2);
        when(bookRepository.findByAvailable(true)).thenReturn(books);

        List<Book> returnedBooks = libraryService.getAvailableBooks();

        assertEquals(books, returnedBooks);
    }

    @Test
    public void testGetAvailableBooksNotFound() {
        when(bookRepository.findByAvailable(true)).thenReturn(Collections.emptyList());

        assertThrows(NoBooksFoundException.class, () -> libraryService.getAvailableBooks());
    }
}