package com.hillogy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hillogy.dto.BookDTO;
import com.hillogy.service.LibraryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Tag(name = "Library Management endpoints", description = "Library Management endpoints")
@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	public LibraryService libraryService;

	/**
	 * Adds a new book to the library.
	 *
	 * @param bookDTO The data transfer object containing the details of the book to be added.
	 * @return A ResponseEntity with a boolean indicating whether the book was added successfully.
	 */
	@ApiOperation(value = "Add a new book to the library", response = Boolean.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "Book successfully added"),
	    @ApiResponse(code = 400, message = "Invalid input"),
	    @ApiResponse(code = 409, message = "Book already exists")
	})
	@PostMapping("/books")
	public ResponseEntity<Boolean> addBook(@RequestBody @ApiParam(value = "Book data to add") BookDTO bookDTO) {
	    boolean isAdded = libraryService.addBook(bookDTO);
	    return new ResponseEntity<>(isAdded, HttpStatus.CREATED);
	}
}
