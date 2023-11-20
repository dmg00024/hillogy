package com.hillogy.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hillogy.dto.BookDTO;
import com.hillogy.model.Book;

class BookConverterTest {

	@Test
	void testToEntity() {
		BookDTO dto = new BookDTO();
		dto.setTitle("Test Title");
		dto.setIsbn("1234567890");
		

		Book book = BookConverter.toEntity(dto);

		assertEquals(dto.getTitle(), book.getTitle());
		assertEquals(dto.getIsbn(), book.getIsbn());
		
	}

	@Test
	void testToDto() {
		Book book = new Book();
		book.setTitle("Test Title");
		book.setIsbn("1234567890");

		BookDTO dto = BookConverter.toDto(book);

		assertEquals(book.getTitle(), dto.getTitle());
		assertEquals(book.getIsbn(), dto.getIsbn());
	}
}
