package com.hillogy;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.hillogy.model.Book;
import com.hillogy.model.User;
import com.hillogy.repository.BookRepository;
import com.hillogy.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class HillogyApplicationTests {

	@Mock
	private BookRepository bookRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private HillogyApplication hillogyApplication;

	@Test
	void testInsertData() {
		hillogyApplication.insertData();

		// Verify that 10 books were saved
		verify(bookRepository, times(10)).save(Mockito.any(Book.class));

		// Verify that 20 users were saved
		verify(userRepository, times(20)).save(Mockito.any(User.class));
	}

	/**
	 * 
	 */
	@Test
	void contextLoads() {
	}
}