package com.hillogy;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hillogy.repository.BookRepository;

@SpringBootTest(classes = HillogyApplication.class)
public class HillogyApplicationTest {

	@MockBean
	private BookRepository bookRepository;

	@Test
	public void testMain() {
		
		assertTrue(true);
	}


}