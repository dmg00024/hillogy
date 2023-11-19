package com.hillogy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hillogy.converter.BookConverter;
import com.hillogy.dto.BookDTO;
import com.hillogy.exception.BookAlreadyExistsException;
import com.hillogy.exception.NoBooksFoundException;
import com.hillogy.exception.UnableToRemoveBookException;
import com.hillogy.model.Book;
import com.hillogy.repository.BookRepository;

/**
 * This service class provides methods for managing books in a library.
 */
@Service
public class LibraryService {

	@Autowired
	private BookRepository bookRepository;

	/**
	 * Adds a new book to the library.
	 *
	 * @param dto the BookDTO containing the data of the new book.
	 * @return the added Book.
	 * @throws BookAlreadyExistsException if a book with the same ISBN already
	 *                                    exists.
	 */
	public boolean addBook(BookDTO dto) {
		Book book = BookConverter.toEntity(dto);

		if (bookRepository.existsById(book.getIsbn())) {
			throw new BookAlreadyExistsException("Book with ISBN " + book.getIsbn() + " already exists");
		}

		Book savedBook = bookRepository.save(book);
		return savedBook != null && savedBook.getIsbn().equals(book.getIsbn());
	}

	/**
	 * Removes a book from the repository.
	 *
	 * @param isbn the ISBN of the book to remove
	 * @throws NoBooksFoundException       if a book with the given ISBN does not
	 *                                     exist in the repository
	 * @throws UnableToRemoveBookException if the book is not available
	 */
	public boolean removeBook(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(() -> new NoBooksFoundException("Book not found"));

		if (book.isAvailable()) {
			bookRepository.deleteById(isbn);
			return !bookRepository.existsById(isbn);
		} else {
			throw new UnableToRemoveBookException("Book is not available");
		}
	}

	/**
	 * Retrieves a book from the repository by its title.
	 *
	 * @param title the title of the book to retrieve
	 * @return the retrieved book
	 * @throws NoBooksFoundException if a book with the given title does not exist
	 *                               in the repository
	 */
	public BookDTO getBookByTitle(String title) {
		Book book = bookRepository.findByTitle(title);
		if (book == null) {
			throw new NoBooksFoundException("Book with title " + title + " not found");
		}
		return BookConverter.toDto(book);
	}

	/**
	 * Retrieves a list of books by a specific author.
	 *
	 * @param author the name of the author whose books are to be retrieved.
	 * @return a list of BookDTO instances representing the books by the specified
	 *         author.
	 * @throws NoBooksFoundException if no books are found by the specified author.
	 */
	public List<BookDTO> getBooksByAuthor(String author) {
		List<Book> books = bookRepository.findByAuthor(author);
		if (books.isEmpty()) {
			throw new NoBooksFoundException("No books found by author " + author);
		}

		// Convert each Book instance to a BookDTO instance
		return books.stream().map(BookConverter::toDto).collect(Collectors.toList());
	}

	/**
	 * Retrieves a book by its ISBN.
	 *
	 * @param isbn the ISBN of the book to be retrieved.
	 * @return a BookDTO instance representing the book with the specified ISBN.
	 * @throws NoBooksFoundException if no book is found with the specified ISBN.
	 */
	public BookDTO getBookByIsbn(String isbn) {
		return BookConverter.toDto(bookRepository.findById(isbn)
				.orElseThrow(() -> new NoBooksFoundException("Book with ISBN " + isbn + " not found")));
	}

	/**
	 * Retrieves a list of all available books.
	 *
	 * @return a list of BookDTO instances representing the available books.
	 * @throws NoBooksFoundException if no available books are found.
	 */
	public List<BookDTO> getAvailableBooks() {
		List<Book> books = bookRepository.findByAvailable(true);
		if (books.isEmpty()) {
			throw new NoBooksFoundException("No available books found");
		}

		// Convert each Book instance to a BookDTO instance
		return books.stream().map(BookConverter::toDto).collect(Collectors.toList());
	}
}