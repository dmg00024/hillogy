package com.hillogy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hillogy.converter.UserConverter;
import com.hillogy.dto.UserDTO;
import com.hillogy.exception.NoBooksFoundException;
import com.hillogy.exception.NoUserFoundException;
import com.hillogy.exception.UnableToCheckOutBookException;
import com.hillogy.exception.UnableToReturnBookException;
import com.hillogy.exception.UserAlreadyExistsException;
import com.hillogy.model.Book;
import com.hillogy.model.User;
import com.hillogy.repository.BookRepository;
import com.hillogy.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	/**
	 * Checks out a book for a user.
	 *
	 * @param username the username of the user
	 * @param isbn the ISBN of the book
	 * @return true if the book was successfully checked out, false otherwise
	 * @throws NoUserFoundException if the user is not found
	 * @throws NoBooksFoundException if the book is not found
	 * @throws UnableToCheckOutBookException if the book cannot be checked out
	 */
	public boolean checkOutBook(String username, String isbn) {
		try {
			User user = userRepository.findById(username).orElseThrow(() -> new NoUserFoundException("User not found"));
			Book book = bookRepository.findById(isbn).orElseThrow(() -> new NoBooksFoundException("Book not found"));

			user.getBooks().add(book);

			userRepository.save(user);

			return true;
		} catch (NoUserFoundException e) {
			throw new UnableToCheckOutBookException(e.getMessage());
		} catch (NoBooksFoundException e) {
			throw new UnableToCheckOutBookException(e.getMessage());
		}
	}

	/**
	 * Returns a book from a user.
	 *
	 * @param username the username of the user who is returning the book
	 * @param isbn     the ISBN of the book to be returned
	 * @return true if the book was successfully returned
	 * @throws NoUserFoundException        if no user is found with the given
	 *                                     username
	 * @throws NoBooksFoundException       if no book is found with the given ISBN
	 * @throws UnableToReturnBookException if the user does not have the book
	 */
	public boolean returnBook(String username, String isbn) throws UnableToReturnBookException {
		User user = userRepository.findById(username).orElseThrow(() -> new NoUserFoundException("User not found"));
		Book book = bookRepository.findById(isbn).orElseThrow(() -> new NoBooksFoundException("Book not found"));

		if (user.getBooks().contains(book)) {
			book.setAvailable(true);
			user.getBooks().remove(book);
			return true;
		} else {
			throw new UnableToReturnBookException("Unable to return book: User does not have this book");
		}
	}
	
	/**
     * Adds a new user.
     *
     * @param dto the UserDTO containing the data of the new user.
     * @return the added User.
     * @throws UserAlreadyExistsException if a user with the same username already exists.
     */
    public User addUser(UserDTO dto) throws UserAlreadyExistsException {
        User user = UserConverter.toEntity(dto);

        if (userRepository.existsById(user.getUsername())) {
            throw new UserAlreadyExistsException("A user with this username already exists.");
        }

        return userRepository.save(user);
    }
}
