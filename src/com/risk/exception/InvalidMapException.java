package com.risk.exception;

/**
 * Implementation of InvalidMapException exception.
 */
public class InvalidMapException extends Exception{
	private static final long serialVersionUID = 1L;

	/**
	 * This method is used print InvalidMapException message.
	 * @param message contains exception message.
	 */
	public InvalidMapException(String message) {
		super(message);
	}
}
