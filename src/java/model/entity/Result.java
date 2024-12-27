package model.entity;

/**
 * A generic class used to encapsulate the result of an operation.
 * This class holds the status, message, data, and error details of an operation.
 * It can be used for handling both success and failure scenarios.
 * 
 * @param <T> The type of data the result will hold (could be any object).
 */
public class Result<T> {

    private T Data;              // Data returned from the operation
    private int status;          // HTTP status code (e.g., 200 for success)
    private String message;      // Message describing the result
    private String errorDetails; // Details of any error (if applicable)

    /**
     * Constructor to initialize the result object.
     * @param Data The result data of the operation.
     * @param status The status code of the operation.
     * @param message A message describing the outcome.
     * @param errorDetails Detailed error information (if any).
     */
    public Result(T Data, int status, String message, String errorDetails) {
        this.Data = Data;
        this.status = status;
        this.message = message;
        this.errorDetails = errorDetails;
    }
    
    /**
     * Gets the status of the operation.
     * @return The status code of the operation.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Gets the message describing the result of the operation.
     * @return The result message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the data returned by the operation.
     * @return The data of type T.
     */
    public T getData() {
        return Data;
    }

    /**
     * Gets the error details, if applicable.
     * @return The error details message.
     */
    public String getErrorDetails() {
        return errorDetails;
    }

    /**
     * Sets the status of the operation.
     * @param status The status code to set.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Sets the message of the result.
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Sets the data of the result.
     * @param Data The data to set.
     */
    public void setData(T Data) {
        this.Data = Data;
    }

    /**
     * Sets the error details.
     * @param errorDetails The error details to set.
     */
    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    /**
     * Returns a string representation of the result object.
     * @return A string representing the result object.
     */
    @Override
    public String toString() {
        return "Result{" + "Data=" + Data + ", status=" + status + ", message=" + message + ", errorDetails=" + errorDetails + '}';
    }

    /**
     * Checks if the operation was successful (status 200).
     * @return True if the status is 200, indicating success.
     */
    public boolean isSuccess(){
        return this.getStatus() == 200;
    }
}
