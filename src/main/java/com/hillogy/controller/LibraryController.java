package com.hillogy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hillogy.dto.BookDTO;
import com.hillogy.exception.BookAlreadyExistsException;
import com.hillogy.exception.NoBooksFoundException;
import com.hillogy.service.LibraryService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(info = @Info(title = "Hillogy test API", version = "v1", description = "Author: Daniel Muñoz GAllardo", license = @License(name = " Test for hillogy © 2023 by Daniel Muñoz Gallardo is licensed under CC BY-NC-ND 4.0 ", url = "https://creativecommons.org/licenses/by/4.0/"

)))
@Tag(name = "Library Controller", description = "Library Management endpoints")
@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	public LibraryService libraryService;

	/**
	 * Adds a new book to the library.
	 *
	 * @param dto the BookDTO containing the data of the new book.
	 * @return ResponseEntity with status CREATED if the book was added
	 *         successfully, CONFLICT if a book with the same ISBN already exists,
	 *         INTERNAL_SERVER_ERROR for any other exceptions.
	 */
	@Operation(summary = "Add a new book")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Book added", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "409", description = "Book already exists", content = @Content),
			@ApiResponse(responseCode = "500", description = "Server error", content = @Content) })
	@PostMapping("/books")
	public ResponseEntity<?> addBook(@RequestBody BookDTO dto) {
		try {
			boolean isAdded = libraryService.addBook(dto);
			if (isAdded) {
				return ResponseEntity.created(null).build();
			} else {
				return ResponseEntity.internalServerError().build();
			}
		} catch (BookAlreadyExistsException e) {
			return ResponseEntity.status(409).build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	/**
	 * Removes a book from the library.
	 *
	 * @param isbn the ISBN of the book to remove.
	 * @return ResponseEntity with status OK if the book was removed successfully,
	 *         NOT_FOUND if the book with the given ISBN does not exist,
	 *         INTERNAL_SERVER_ERROR for any other exceptions.
	 */
	@Operation(summary = "Remove a book from the library")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Book removed successfully", content = @Content),
			@ApiResponse(responseCode = "404", description = "No book found with the given ISBN", content = @Content),
			@ApiResponse(responseCode = "500", description = "Unable to remove book", content = @Content) })
	@DeleteMapping("/books/{isbn}")
	public ResponseEntity<?> removeBook(@PathVariable String isbn) {
		try {
			libraryService.removeBook(isbn);
			return ResponseEntity.ok().build();
		} catch (NoBooksFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Retrieves a book from the library by its title.
	 *
	 * @param title the title of the book to retrieve.
	 * @return ResponseEntity with the retrieved book if it exists, NOT_FOUND if the
	 *         book with the given title does not exist.
	 */
	@Operation(summary = "Get a book by its title")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the book", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
	@GetMapping("/books/title/{title}")
	public ResponseEntity<BookDTO> getBookByTitle(@PathVariable String title) {
		try {
			BookDTO book = libraryService.getBookByTitle(title);
			return ResponseEntity.ok(book);
		} catch (NoBooksFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Retrieves a list of books by a specific author.
	 *
	 * @param author the name of the author whose books are to be retrieved.
	 * @return ResponseEntity with a list of books by the specified author if they
	 *         exist, NOT_FOUND if no books are found by the specified author.
	 */
	@Operation(summary = "Get books by author")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the books", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "No books found by the author", content = @Content) })
	@GetMapping("/books/author/{author}")
	public ResponseEntity<List<BookDTO>> getBooksByAuthor(@PathVariable String author) {
		try {
			List<BookDTO> books = libraryService.getBooksByAuthor(author);
			return ResponseEntity.ok(books);
		} catch (NoBooksFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Retrieves a book by its ISBN.
	 *
	 * @param isbn the ISBN of the book to be retrieved.
	 * @return ResponseEntity with the retrieved book if it exists, NOT_FOUND if no
	 *         book is found with the specified ISBN.
	 */
	@Operation(summary = "Get a book by its ISBN")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the book", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
	@GetMapping("/books/isbn/{isbn}")
	public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable String isbn) {
		try {
			BookDTO book = libraryService.getBookByIsbn(isbn);
			return ResponseEntity.ok(book);
		} catch (NoBooksFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Retrieves a list of all available books.
	 *
	 * @return ResponseEntity with a list of available books if they exist,
	 *         NOT_FOUND if no available books are found.
	 */
	@Operation(summary = "Get all available books")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the books", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "No available books found", content = @Content) })
	@GetMapping("/books/available")
	public ResponseEntity<List<BookDTO>> getAvailableBooks() {
		try {
			List<BookDTO> books = libraryService.getAvailableBooks();
			return ResponseEntity.ok(books);
		} catch (NoBooksFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
