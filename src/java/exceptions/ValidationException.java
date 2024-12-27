package exceptions;

/**
 * Custom exception class for validation-related errors.
 * This class extends RuntimeException and is used to throw errors
 * when input validation fails, such as invalid user input or incorrect data formats.
 * 
 * Example use case: Throw this exception when input fields do not meet required validation rules.
 */
public class ValidationException extends RuntimeException {

    /**
     * Constructor that accepts a message to be passed to the RuntimeException.
     * @param message The message detailing the validation error.
     */
    public ValidationException(String message) {
        super(message); // Pass the message to the superclass constructor.
    }
}
