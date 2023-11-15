package com.hillogy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hillogy.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {

	List<Book> findByAuthor(String author);

	Book findByTitle(String title);

	List<Book> findByAvailable(boolean b);
}