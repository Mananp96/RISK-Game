package com.risk.exception;

public class InvalidMapException extends Exception{

	private static final long serialVersionUID = 1L;
	
	/**
	 * print InvalidMapException message
	 */
	public InvalidMapException(String message) {
		super(message);
	}
}
