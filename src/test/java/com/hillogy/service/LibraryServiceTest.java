package com.hillogy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hillogy.converter.BookConverter;
import com.hillogy.dto.BookDTO;
import com.hillogy.exception.BookAlreadyExistsException;
import com.hillogy.exception.NoBooksFoundException;
import com.hillogy.exception.UnableToRemoveBookException;
import com.hillogy.model.Book;
import com.hillogy.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {

	@InjectMocks
	private LibraryService libraryService;

	@Mock
	private BookRepository bookRepository;

	@Test
	public void testAddBookSuccessfully() {
		BookDTO dto = new BookDTO("123", "Title", "Author", true);
		Book book = BookConverter.toEntity(dto);

		when(bookRepository.existsById(book.getIsbn())).thenReturn(false);
		when(bookRepository.save(any(Book.class))).thenReturn(book);

		boolean result = libraryService.addBook(dto);

		assertTrue(result);
		verify(bookRepository, times(1)).save(any(Book.class));
	}

	@Test
	public void testAddBookThrowsBookAlreadyExistsException() {
		BookDTO dto = new BookDTO("123", "Title", "Author", true);
		Book book = BookConverter.toEntity(dto);

		when(bookRepository.existsById(book.getIsbn())).thenReturn(true);

		assertThrows(BookAlreadyExistsException.class, () -> libraryService.addBook(dto));
	}

	@Test
	public void testAddBookReturnsFalseWhenSavedBookIsNull() {
		BookDTO dto = new BookDTO("123", "Title", "Author", true);

		when(bookRepository.existsById(dto.getIsbn())).thenReturn(false);
		when(bookRepository.save(any(Book.class))).thenReturn(null);

		boolean result = libraryService.addBook(dto);

		assertFalse(result);
	}

	@Test
	public void testAddBookReturnsFalseWhenSavedBookISBNDoesNotMatch() {
		BookDTO dto = new BookDTO("123", "Title", "Author", true);
		Book book = new Book("456", "Title", "Author", true);

		when(bookRepository.existsById(dto.getIsbn())).thenReturn(false);
		when(bookRepository.save(any(Book.class))).thenReturn(book);

		boolean result = libraryService.addBook(dto);

		assertFalse(result);
	}
	
	@Test
	public void testRemoveBookSuccessfully() {
	    String isbn = "123";
	    Book book = new Book(isbn, "Title", "Author", true);

	    when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));
	    when(bookRepository.existsById(isbn)).thenReturn(false);

	    boolean result = libraryService.removeBook(isbn);

	    assertTrue(result);
	    verify(bookRepository, times(1)).deleteById(isbn);
	}

	@Test
	public void testRemoveBookThrowsNoBooksFoundException() {
	    String isbn = "123";

	    when(bookRepository.findById(isbn)).thenReturn(Optional.empty());

	    assertThrows(NoBooksFoundException.class, () -> libraryService.removeBook(isbn));
	}

	@Test
	public void testRemoveBookThrowsUnableToRemoveBookException() {
	    String isbn = "123";
	    Book book = new Book(isbn, "Title", "Author", false);

	    when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));

	    assertThrows(UnableToRemoveBookException.class, () -> libraryService.removeBook(isbn));
	}

	@Test
	public void testRemoveBookReturnsFalseWhenBookStillExistsAfterDeletion() {
	    String isbn = "123";
	    Book book = new Book(isbn, "Title", "Author", true);

	    when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));
	    when(bookRepository.existsById(isbn)).thenReturn(true);

	    boolean result = libraryService.removeBook(isbn);

	    assertFalse(result);
	}	
	
	@Test
	void getBookByTitleTest() {
	    // Arrange
	    Book book = new Book();
	    book.setTitle("some title");
	    when(bookRepository.findByTitle("some title")).thenReturn(book);

	    // Act
	    BookDTO result = libraryService.getBookByTitle("some title");

	    // Assert
	    assertNotNull(result);
	    assertEquals("some title", result.getTitle());
	}

	@Test
	void getBookByTitleTest_ThrowsNoBooksFoundException() {
	    // Arrange
	    when(bookRepository.findByTitle("some title")).thenReturn(null);

	    // Act and Assert
	    Exception exception = assertThrows(NoBooksFoundException.class, () -> {
	        libraryService.getBookByTitle("some title");
	    });

	    String expectedMessage = "Book with title some title not found";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void getBooksByAuthorTest() {
	    // Arrange
	    List<Book> books = new ArrayList<>();
	    Book book = new Book();
	    book.setAuthor("some author");
	    books.add(book);
	    when(bookRepository.findByAuthor("some author")).thenReturn(books);

	    // Act
	    List<BookDTO> result = libraryService.getBooksByAuthor("some author");

	    // Assert
	    assertNotNull(result);
	    assertEquals(1, result.size());
	    assertEquals("some author", result.get(0).getAuthor());
	}

	@Test
	void getBooksByAuthorTest_ThrowsNoBooksFoundException() {
	    // Arrange
	    when(bookRepository.findByAuthor("some author")).thenReturn(new ArrayList<>());

	    // Act and Assert
	    Exception exception = assertThrows(NoBooksFoundException.class, () -> {
	        libraryService.getBooksByAuthor("some author");
	    });

	    String expectedMessage = "No books found by author some author";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void getBookByIsbnTest() {
	    // Arrange
	    Book book = new Book();
	    book.setIsbn("some isbn");
	    when(bookRepository.findById("some isbn")).thenReturn(Optional.of(book));

	    // Act
	    BookDTO result = libraryService.getBookByIsbn("some isbn");

	    // Assert
	    assertNotNull(result);
	    assertEquals("some isbn", result.getIsbn());
	}

	@Test
	void getBookByIsbnTest_ThrowsNoBooksFoundException() {
	    // Arrange
	    when(bookRepository.findById("some isbn")).thenReturn(Optional.empty());

	    // Act and Assert
	    Exception exception = assertThrows(NoBooksFoundException.class, () -> {
	        libraryService.getBookByIsbn("some isbn");
	    });

	    String expectedMessage = "Book with isbn some isbn not found";
	    String actualMessage = exception.getMessage();

	    assertFalse(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void getAvailableBooksTest() {
	    // Arrange
	    List<Book> books = new ArrayList<>();
	    Book book = new Book();
	    book.setIsbn("some isbn");
	    book.setAvailable(true);
	    books.add(book);
	    when(bookRepository.findByAvailable(true)).thenReturn(books);

	    // Act
	    List<BookDTO> result = libraryService.getAvailableBooks();

	    // Assert
	    assertNotNull(result);
	    assertEquals(1, result.size());
	    assertEquals("some isbn", result.get(0).getIsbn());
	    assertTrue(result.get(0).isAvailable());
	}

	@Test
	void getAvailableBooksTest_ThrowsNoBooksFoundException() {
	    // Arrange
	    when(bookRepository.findByAvailable(true)).thenReturn(new ArrayList<>());

	    // Act and Assert
	    Exception exception = assertThrows(NoBooksFoundException.class, () -> {
	        libraryService.getAvailableBooks();
	    });

	    String expectedMessage = "No available books found";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
}