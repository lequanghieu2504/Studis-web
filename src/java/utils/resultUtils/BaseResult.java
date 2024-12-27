package utils.resultUtils;

import javax.servlet.http.HttpServletResponse;
import model.entity.Result;

/**
 * BaseResult provides methods for generating Result objects with different statuses.
 * The Result can be used to return success, failure, or error responses from various services.
 * It is a generic class that works with any type of data.
 *
 * @param <T> The type of data to be returned in the Result object (e.g., User, String, etc.)
 */
public abstract class BaseResult<T> {

    /**
     * Generates a Result indicating a successful operation.
     * 
     * @param data The data to be included in the Result (can be any object of type T).
     * @param message A message describing the success.
     * @return A Result with a status code of 200 (OK).
     */
    public static <T> Result success(T data, String message) {
        return new Result(data, HttpServletResponse.SC_OK, message, null);
    }

    /**
     * Generates a Result indicating a failure due to bad request.
     * 
     * @param data The data to be included in the Result (can be any object of type T).
     * @param message A message describing the failure.
     * @param errorDetails Additional error details for the failure.
     * @return A Result with a status code of 400 (Bad Request).
     */
    public static <T> Result fail(T data, String message, String errorDetails) {
        return new Result(data, HttpServletResponse.SC_BAD_REQUEST, message, errorDetails);
    }

    /**
     * Generates a Result indicating an error occurred.
     * 
     * @param data The data to be included in the Result (can be any object of type T).
     * @param message A message describing the error.
     * @param errorDetails Detailed error information for debugging purposes.
     * @return A Result with a status code of 500 (Internal Server Error).
     */
    public static <T> Result error(T data, String message, String errorDetails) {
        return new Result(data, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message, errorDetails);
    }
}
