package exceptions;

/**
 * Custom exception class for business logic-related errors.
 * This class extends RuntimeException and is used to throw errors
 * related to business operations with a custom message.
 * 
 * Example use case: Throw this exception when business rules are violated,
 * such as invalid data processing or unauthorized actions.
 */
public class BusinessException extends RuntimeException {

    /**
     * Constructor that accepts a message to be passed to the RuntimeException.
     * @param message The message detailing the business logic error.
     */
    public BusinessException(String message) {
        super(message); // Pass the message to the superclass constructor.
    }
}
