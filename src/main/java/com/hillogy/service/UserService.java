package com.hillogy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hillogy.exceptions.NoBooksFoundException;
import com.hillogy.model.Book;
import com.hillogy.repository.BookRepository;

@Service
public class UserService {

	@Autowired
	private BookRepository bookRepository;

	/**
	 * Checks out a book from the library.
	 *
	 * @param isbn the ISBN of the book to check out
	 * @throws NoBooksFoundException if a book with the given ISBN does not exist
	 * @throws NoBooksFoundException if the book is not available
	 */
	public void checkOutBook(String isbn) {
		Optional<Book> book = bookRepository.findById(isbn);
		if (!book.isPresent()) {
			throw new NoBooksFoundException("Book with ISBN " + isbn + " not found");
		}
		if (!book.get().isAvailable()) {
			throw new NoBooksFoundException("Book with ISBN " + isbn + " is not available");
		}
		book.get().setAvailable(false);
		bookRepository.save(book.get());
	}

	/**
	 * Returns a book to the library.
	 *
	 * @param isbn the ISBN of the book to return
	 * @throws NoBooksFoundException if a book with the given ISBN does not exist in
	 *                               the repository
	 */
	public void returnBook(String isbn) {
		Optional<Book> book = bookRepository.findById(isbn);
		if (!book.isPresent()) {
			throw new NoBooksFoundException("Book with ISBN " + isbn + " not found");
		}
		book.get().setAvailable(true);
		bookRepository.save(book.get());
	}

}
