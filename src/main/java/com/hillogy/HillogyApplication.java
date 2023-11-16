package com.hillogy;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hillogy.model.Book;
import com.hillogy.model.User;
import com.hillogy.repository.BookRepository;
import com.hillogy.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class HillogyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HillogyApplication.class, args);
	}

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void insertData() {
		Book book1 = new Book("Title1", "Author1", "ISBN1", true);
		Book book2 = new Book("Title2", "Author2", "ISBN2", true);
		Book book3 = new Book("Title3", "Author3", "ISBN3", true);
		Book book4 = new Book("Title4", "Author4", "ISBN4", true);
		Book book5 = new Book("Title5", "Author5", "ISBN5", true);
		Book book6 = new Book("Title6", "Author6", "ISBN6", true);
		Book book7 = new Book("Title7", "Author7", "ISBN7", true);
		Book book8 = new Book("Title8", "Author8", "ISBN8", true);
		Book book9 = new Book("Title9", "Author9", "ISBN9", true);
		Book book10 = new Book("Title10", "Author10", "ISBN10", true);

		bookRepository.save(book1);
		bookRepository.save(book2);
		bookRepository.save(book3);
		bookRepository.save(book4);
		bookRepository.save(book5);
		bookRepository.save(book6);
		bookRepository.save(book7);
		bookRepository.save(book8);
		bookRepository.save(book9);
		bookRepository.save(book10);

		for (int i = 0; i < 20; i++) {
			User user = new User();
			user.setUsername("User" + i);
			user.setPassword(UUID.randomUUID().toString());

			userRepository.save(user);
		}
	}

}
