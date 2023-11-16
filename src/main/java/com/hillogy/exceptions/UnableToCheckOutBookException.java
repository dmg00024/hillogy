package com.hillogy.exceptions;

public class UnableToCheckOutBookException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnableToCheckOutBookException(String message) {
		super(message);
	}
}
