package com.risk.exception;
import java.util.logging.Logger;
import com.risk.validate.MapValidator;

/**
 * Implementation of InvalidMapException exception.
 */
public class InvalidMapException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(MapValidator.class.getName());

	/**
	 * This method is used print InvalidMapException message.
	 * @param message contains exception message.
	 */
	public InvalidMapException(String message) {
		LOGGER.warning(message);
	}
}
