package com.hillogy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hillogy.dto.UserDTO;
import com.hillogy.exception.UserAlreadyExistsException;
import com.hillogy.model.User;
import com.hillogy.service.UserService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(info = @Info(title = "Hillogy test API", version = "v1", description = "Author: Daniel Muñoz GAllardo", license = @License(name = " Test for hillogy © 2023 by Daniel Muñoz Gallardo is licensed under CC BY-NC-ND 4.0 ", url = "https://creativecommons.org/licenses/by/4.0/"

)))
@Tag(name = "User Controller", description = "Endpoints for managing users")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Checks out a book for a user.
	 *
	 * @param username the username of the user
	 * @param isbn     the ISBN of the book
	 * @return ResponseEntity with a boolean value: true if the book was
	 *         successfully checked out, false otherwise
	 * @throws Exception if the user or the book is not found or the book cannot be
	 *                   checked out
	 */
	@Operation(summary = "Check out a book for a user")
	@PostMapping("/{username}/checkout/{isbn}")
	public ResponseEntity<Boolean> checkOutBook(
			@Parameter(description = "Username of the user", required = true) @PathVariable String username,
			@Parameter(description = "ISBN of the book", required = true) @PathVariable String isbn) {
		try {
			boolean result = userService.checkOutBook(username, isbn);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Returns a book from a user.
	 *
	 * @param username the username of the user who is returning the book
	 * @param isbn     the ISBN of the book to be returned
	 * @return ResponseEntity with a boolean value: true if the book was
	 *         successfully returned
	 * @throws Exception if no user is found with the given username, no book is
	 *                   found with the given ISBN, or the user does not have the
	 *                   book
	 */
	@Operation(summary = "Return a book for a user")
	@PostMapping("/{username}/return/{isbn}")
	public ResponseEntity<Boolean> returnBook(
			@Parameter(description = "Username of the user", required = true) @PathVariable String username,
			@Parameter(description = "ISBN of the book", required = true) @PathVariable String isbn) {
		try {
			boolean result = userService.returnBook(username, isbn);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Adds a new user.
	 *
	 * @param dto the UserDTO containing the data of the new user.
	 * @return ResponseEntity with the added User.
	 * @throws Exception if a user with the same username already exists.
	 */
	@Operation(summary = "Add a new user")
	@PostMapping("/")
	public ResponseEntity<?> addUser(
			@Parameter(description = "UserDTO containing the data of the new user", required = true) @RequestBody UserDTO dto) {
		try {
			User user = userService.addUser(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		} catch (UserAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while processing the request.");
		}
	}
}