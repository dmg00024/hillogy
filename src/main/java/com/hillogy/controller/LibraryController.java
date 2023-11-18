package com.hillogy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hillogy.dto.BookDTO;
import com.hillogy.service.LibraryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Library Management endpoints", description = "Library Management endpoints")
@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	public LibraryService libraryService;

	/**
	 * This method is used to add a new book to the library. It uses the
	 * libraryService to add the book. The book details are provided in the request
	 * body. If there is an error during the process, it throws a
	 * ResponseStatusException with a status of INTERNAL_SERVER_ERROR.
	 *
	 * @param book the book to be added
	 * @return the added book
	 * @throws ResponseStatusException if there is an error when adding the book
	 */
	@PostMapping("/books")
	@ApiOperation(value = "Add a new book")
	public boolean addBook(
			@ApiParam(value = "Book object to be added to the library", required = true) @RequestBody BookDTO book) {

		return libraryService.addBook(book);

	}
}
