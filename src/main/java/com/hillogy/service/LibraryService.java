package com.hillogy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hillogy.model.Book;

@Service
public class LibraryService {
    private List<Book> books;

    public LibraryService() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> searchBook(String query) {
        return books.stream()
                .filter(book -> book.getTitle().contains(query) || book.getAuthor().contains(query) || book.getIsbn().contains(query))
                .collect(Collectors.toList());
    }

    public List<Book> displayAvailableBooks() {
        return books.stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }
}