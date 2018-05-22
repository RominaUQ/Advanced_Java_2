package exceptions;

/**
 * This class implements a custom NoSuchAgeException
 * @author Romina Sharifpour
 */

public class NoSuchAgeException extends Exception{
	public NoSuchAgeException(String message) {
		super(message);
	}
}