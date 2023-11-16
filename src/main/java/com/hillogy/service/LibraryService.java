package com.hillogy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hillogy.exception.BookAlreadyExistsException;
import com.hillogy.exception.NoBooksFoundException;
import com.hillogy.model.Book;
import com.hillogy.repository.BookRepository;

@Service
public class LibraryService {

	@Autowired
	private BookRepository bookRepository;

	/**
	 * Adds a book to the repository.
	 *
	 * @param book the book to add
	 * @return the added book
	 * @throws BookAlreadyExistsException if a book with the same ISBN already
	 *                                    exists
	 */
	public Book addBook(Book book) {
		if (bookRepository.existsById(book.getIsbn())) {
			throw new BookAlreadyExistsException("Book with ISBN " + book.getIsbn() + " already exists");
		}
		return bookRepository.save(book);
	}

	/**
	 * Removes a book from the repository.
	 *
	 * @param isbn the ISBN of the book to remove
	 * @throws NoBooksFoundException if a book with the given ISBN does not exist in
	 *                               the repository
	 */
	public void removeBook(String isbn) {
		if (!bookRepository.existsById(isbn)) {
			throw new NoBooksFoundException("Book with ISBN " + isbn + " not found");
		}
		bookRepository.deleteById(isbn);
	}

	/**
	 * Retrieves a book from the repository by its title.
	 *
	 * @param title the title of the book to retrieve
	 * @return the retrieved book
	 * @throws NoBooksFoundException if a book with the given title does not exist
	 *                               in the repository
	 */
	public Book getBookByTitle(String title) {
		Book book = bookRepository.findByTitle(title);
		if (book == null) {
			throw new NoBooksFoundException("Book with title " + title + " not found");
		}
		return book;
	}

	/**
	 * Retrieves all books from the repository by a given author.
	 *
	 * @param author the author of the books to retrieve
	 * @return a list of books by the given author
	 * @throws NoBooksFoundException if no books by the given author exist in the
	 *                               repository
	 */
	public List<Book> getBookByAuthor(String author) {
		List<Book> books = bookRepository.findByAuthor(author);
		if (books.isEmpty()) {
			throw new NoBooksFoundException("No books found by author " + author);
		}
		return books;
	}

	/**
	 * Retrieves a book from the repository by its ISBN.
	 *
	 * @param isbn the ISBN of the book to retrieve
	 * @return the retrieved book
	 * @throws NoBooksFoundException if a book with the given ISBN does not exist in
	 *                               the repository
	 */
	public Book getBookByIsbn(String isbn) {
		return bookRepository.findById(isbn)
				.orElseThrow(() -> new NoBooksFoundException("Book with ISBN " + isbn + " not found"));
	}

	/**
	 * Retrieves all available books from the repository.
	 *
	 * @return a list of available books
	 * @throws NoBooksFoundException if no available books exist in the repository
	 */
	public List<Book> getAvailableBooks() {
		List<Book> books = bookRepository.findByAvailable(true);
		if (books.isEmpty()) {
			throw new NoBooksFoundException("No available books found");
		}
		return books;
	}
}