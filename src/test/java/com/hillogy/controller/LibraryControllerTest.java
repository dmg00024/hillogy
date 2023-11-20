package com.hillogy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hillogy.dto.BookDTO;
import com.hillogy.exception.BookAlreadyExistsException;
import com.hillogy.exception.NoBooksFoundException;
import com.hillogy.service.LibraryService;

@ExtendWith(MockitoExtension.class)
public class LibraryControllerTest {

	@InjectMocks
	private LibraryController libraryController;

	@Mock
	private LibraryService libraryService;

	@Test
	public void testAddBookSuccess() throws Exception {
		BookDTO dto = new BookDTO();
		// set properties of dto

		when(libraryService.addBook(dto)).thenReturn(true);

		ResponseEntity<?> response = libraryController.addBook(dto);

		assertEquals(ResponseEntity.created(null).build(), response);
	}

	@Test
	public void testAddBookAlreadyExists() throws Exception {
		BookDTO dto = new BookDTO();
		// set properties of dto

		when(libraryService.addBook(dto)).thenThrow(new BookAlreadyExistsException(any()));

		ResponseEntity<?> response = libraryController.addBook(dto);

		assertEquals(ResponseEntity.status(409).build(), response);
	}

	@Test
	public void testAddBookUnexpectedError() throws Exception {
		BookDTO dto = new BookDTO();
		// set properties of dto

		when(libraryService.addBook(dto)).thenThrow(new RuntimeException());

		ResponseEntity<?> response = libraryController.addBook(dto);

		assertEquals(ResponseEntity.internalServerError().build(), response);
	}

	@Test
	public void testAddBookInternalServerError() throws Exception {
		BookDTO dto = new BookDTO();
		// set properties of dto

		when(libraryService.addBook(dto)).thenThrow(new RuntimeException());

		ResponseEntity<?> response = libraryController.addBook(dto);

		assertEquals(ResponseEntity.internalServerError().build(), response);
	}

	@Test
	public void testAddBookFailure() {
		BookDTO dto = new BookDTO();
		// set properties of dto

		when(libraryService.addBook(dto)).thenReturn(false);

		ResponseEntity<?> response = libraryController.addBook(dto);

		assertEquals(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(), response);
	}

	@Test
	public void testRemoveBookSuccess() throws Exception {
		String isbn = "1234567890";

		when(libraryService.removeBook(isbn)).thenReturn(true);
		ResponseEntity<?> response = libraryController.removeBook(isbn);

		assertEquals(ResponseEntity.ok().build(), response);
	}

	@Test
	public void testRemoveBookNotFound() throws Exception {
		String isbn = "1234567890";

		doThrow(new NoBooksFoundException(isbn)).when(libraryService).removeBook(isbn);

		ResponseEntity<?> response = libraryController.removeBook(isbn);

		assertEquals(ResponseEntity.notFound().build(), response);
	}

	@Test
	public void testRemoveBookInternalServerError() throws Exception {
		String isbn = "1234567890";

		doThrow(new RuntimeException()).when(libraryService).removeBook(isbn);

		ResponseEntity<?> response = libraryController.removeBook(isbn);

		assertEquals(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(), response);
	}

	@Test
	public void testGetBookByTitleSuccess() throws Exception {
		String title = "Test Title";
		BookDTO dto = new BookDTO();
		// set properties of dto

		when(libraryService.getBookByTitle(title)).thenReturn(dto);

		ResponseEntity<BookDTO> response = libraryController.getBookByTitle(title);

		assertEquals(ResponseEntity.ok(dto), response);
	}

	@Test
	public void testGetBookByTitleNotFound() throws Exception {
		String title = "Test Title";

		when(libraryService.getBookByTitle(title)).thenThrow(new NoBooksFoundException(anyString()));

		ResponseEntity<BookDTO> response = libraryController.getBookByTitle(title);

		assertEquals(ResponseEntity.notFound().build(), response);
	}

	@Test
	public void testGetBooksByAuthorSuccess() throws Exception {
		String author = "Test Author";
		List<BookDTO> dtoList = Collections.singletonList(new BookDTO());
		// set properties of dtoList

		when(libraryService.getBooksByAuthor(author)).thenReturn(dtoList);

		ResponseEntity<List<BookDTO>> response = libraryController.getBooksByAuthor(author);

		assertEquals(ResponseEntity.ok(dtoList), response);
	}

	@Test
	public void testGetBooksByAuthorNotFound() throws Exception {
		String author = "Test Author";

		when(libraryService.getBooksByAuthor(author)).thenThrow(new NoBooksFoundException(anyString()));

		ResponseEntity<List<BookDTO>> response = libraryController.getBooksByAuthor(author);

		assertEquals(ResponseEntity.notFound().build(), response);
	}

	@Test
	public void testGetBookByIsbnSuccess() throws Exception {
		String isbn = "1234567890";
		BookDTO dto = new BookDTO();
		// set properties of dto

		when(libraryService.getBookByIsbn(isbn)).thenReturn(dto);

		ResponseEntity<BookDTO> response = libraryController.getBookByIsbn(isbn);

		assertEquals(ResponseEntity.ok(dto), response);
	}

	@Test
	public void testGetBookByIsbnNotFound() throws Exception {
		String isbn = "1234567890";

		when(libraryService.getBookByIsbn(isbn)).thenThrow(new NoBooksFoundException(null));

		ResponseEntity<BookDTO> response = libraryController.getBookByIsbn(isbn);

		assertEquals(ResponseEntity.notFound().build(), response);
	}

	@Test
	public void testGetAvailableBooksSuccess() throws Exception {
		List<BookDTO> dtoList = Collections.singletonList(new BookDTO());
		// set properties of dtoList

		when(libraryService.getAvailableBooks()).thenReturn(dtoList);

		ResponseEntity<List<BookDTO>> response = libraryController.getAvailableBooks();

		assertEquals(ResponseEntity.ok(dtoList), response);
	}
	
	@Test
    public void testGetAvailableBooksNotFound() throws Exception {
        when(libraryService.getAvailableBooks()).thenThrow(new NoBooksFoundException(null));

        ResponseEntity<List<BookDTO>> response = libraryController.getAvailableBooks();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        List<BookDTO> body = response.getBody();
		assertTrue(body == null || body.isEmpty());
    }
}