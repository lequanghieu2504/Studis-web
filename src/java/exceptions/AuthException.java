package exceptions;

/**
 * Custom exception class for authentication-related errors.
 * This class extends RuntimeException and is used to throw authentication-specific errors 
 * with a custom message.
 * 
 * Example use case: Throw this exception when login or registration fails.
 */
public class AuthException extends RuntimeException {

    /**
     * Constructor that accepts a message to be passed to the RuntimeException.
     * @param message The message detailing the authentication error.
     */
    public AuthException(String message) {
        super(message); // Pass the message to the superclass constructor.
    }

}
