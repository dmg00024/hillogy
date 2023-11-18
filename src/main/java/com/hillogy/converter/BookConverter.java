package com.hillogy.converter;

import org.springframework.stereotype.Component;

import com.hillogy.dto.BookDTO;
import com.hillogy.model.Book;

/**
 * This class provides methods to convert between Book and BookDto objects.
 */
@Component
public class BookConverter {

	/**
	 * Converts a Book entity to a BookDto.
	 * 
	 * @param book the Book entity to convert.
	 * @return a new BookDto with the data from the Book entity.
	 */
	public static BookDTO toDto(Book book) {
		BookDTO bookDto = new BookDTO();
		bookDto.setIsbn(book.getIsbn());
		bookDto.setTitle(book.getTitle());
		bookDto.setAuthor(book.getAuthor());
		bookDto.setAvailable(book.isAvailable());
		return bookDto;
	}

	/**
	 * Converts a BookDto to a Book entity.
	 * 
	 * @param bookDto the BookDto to convert.
	 * @return a new Book with the data from the BookDto.
	 */
	public static Book toEntity(BookDTO bookDto) {
		Book book = new Book();
		book.setIsbn(bookDto.getIsbn());
		book.setTitle(bookDto.getTitle());
		book.setAuthor(bookDto.getAuthor());
		book.setAvailable(bookDto.isAvailable());
		return book;
	}
}