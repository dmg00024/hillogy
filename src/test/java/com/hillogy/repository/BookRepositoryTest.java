package com.hillogy.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hillogy.model.Book;

@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void testSaveBook() {
		Book book = new Book("Title", "Author", "ISBN", true);
		bookRepository.save(book);

		Book foundBook = bookRepository.findById("ISBN").orElse(null);
		assertThat(foundBook).isNotNull();
		assertThat(foundBook.getTitle()).isEqualTo("Title");
		assertThat(foundBook.getAuthor()).isEqualTo("Author");
		assertThat(foundBook.getIsbn()).isEqualTo("ISBN");
		assertThat(foundBook.isAvailable()).isEqualTo(true);
	}
}