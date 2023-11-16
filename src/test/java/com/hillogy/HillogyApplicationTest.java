package com.hillogy;

import com.hillogy.model.Book;
import com.hillogy.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = HillogyApplication.class)
public class HillogyApplicationTest {

	@MockBean
	private BookRepository bookRepository;

	@Test
	public void whenFindByIsbn_thenReturnBook() {
		// given
		Book book = new Book("Title", "Author", "1234567890", true);
		when(bookRepository.findById(book.getIsbn())).thenReturn(Optional.of(book));

		// when
		Book found = bookRepository.findById(book.getIsbn()).orElse(null);

		// then
		assertThat(found).isNotNull();
		assertThat(found.getIsbn()).isEqualTo(book.getIsbn());
	}

	@Test
	public void whenSaveBook_thenCallBookRepositorySave() {
		// given
		Book book = new Book("Title", "Author", "1234567890", true);

		// when
		bookRepository.save(book);

		// then
		verify(bookRepository).save(book);
	}
}