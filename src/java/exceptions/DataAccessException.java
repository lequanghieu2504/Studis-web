package exceptions;

/**
 * Custom exception class for data access-related errors.
 * This class extends RuntimeException and is used to throw errors
 * related to database operations or data access failures.
 * 
 * Example use case: Throw this exception when a database connection fails, 
 * a query execution error occurs, or data retrieval fails.
 */
public class DataAccessException extends RuntimeException {

    /**
     * Constructor that accepts a message to be passed to the RuntimeException.
     * @param message The message detailing the data access error.
     */
    public DataAccessException(String message) {
        super(message); // Pass the message to the superclass constructor.
    }

    /**
     * Constructor that accepts both a message and a cause (another throwable).
     * @param message The message detailing the data access error.
     * @param cause The underlying cause of the error (e.g., SQLException).
     */
    public DataAccessException(String message, Throwable cause) {
        super(message, cause); // Pass both the message and cause to the superclass constructor.
    }
}
