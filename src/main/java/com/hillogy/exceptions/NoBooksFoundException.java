package com.hillogy.exceptions;

public class NoBooksFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoBooksFoundException(String message) {
        super(message);
    }
}
