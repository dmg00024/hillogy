package com.hillogy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hillogy.dto.BookDTO;
import com.hillogy.exception.BookAlreadyExistsException;
import com.hillogy.service.LibraryService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Library Management endpoints", description = "Library Management endpoints")
@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	public LibraryService libraryService;

	/**
	 * Adds a new book to the library.
	 *
	 * @param bookDTO the BookDTO containing the data of the new book.
	 * @return ResponseEntity with status CREATED if the book was added
	 *         successfully, BAD_REQUEST if the BookDTO is null or its fields are
	 *         empty, CONFLICT if a book with the same ISBN already exists,
	 *         INTERNAL_SERVER_ERROR for any other exceptions.
	 */
	@Operation(summary = "Add a new book to the library")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "Book added successfully", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid BookDTO supplied", content = @Content),
			@ApiResponse(responseCode = "409", description = "Book already exists", content = @Content),
			@ApiResponse(responseCode = "500", description = "Server error", content = @Content) })
	@PostMapping(value = "/book", consumes = "application/json")
	public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO) {
		if (bookDTO == null || bookDTO.getIsbn() == null || bookDTO.getTitle() == null || bookDTO.getAuthor() == null) {
			return new ResponseEntity<>("Invalid data input", HttpStatus.BAD_REQUEST);
		}
		try {
			libraryService.addBook(bookDTO);
			return new ResponseEntity<>("Book added successfully", HttpStatus.CREATED);
		} catch (BookAlreadyExistsException e) {
			return new ResponseEntity<>("Book already exists", HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
