package service.dao;

import dao.UserDAO;
import model.entity.Result;
import model.entity.User;
import utils.resultUtils.AuthResult;

/**
 * Service class responsible for retrieving user data from the database. 
 * It handles multiple types of retrieval operations including retrieving by ID,
 * username, email, or other specific fields. Each method wraps the DAO calls 
 * and handles exceptions gracefully.
 */
public class UserRetrieveService {

    private UserDAO userDAO = new UserDAO(); // DAO class for interacting with the user database

    /**
     * Retrieves a user by their ID.
     * 
     * @param id The ID of the user to retrieve.
     * @return A result object containing the user data or an error message.
     */
    public Result handleRetrieve(int id) {
        try {
            // Retrieve user by ID and wrap the result
            return toResult(userDAO.retrieve(id));
        } catch (Exception e) {
            // Handle any exception that occurs during the retrieval
            return AuthResult.error(null, "Error occurred while retrieving user.", e.getMessage());
        }
    }

    /**
     * Retrieves a user by their username or email.
     * 
     * @param nameOrEmail The username or email to search for.
     * @return A result object containing the user data or an error message.
     */
    public Result handleRetrieve(String nameOrEmail) {
        try {
            // Retrieve user by name or email and wrap the result
            return toResult(userDAO.retrieve(nameOrEmail));
        } catch (Exception e) {
            // Handle any exception that occurs during the retrieval
            return AuthResult.error(null, "Error occurred while retrieving user.", e.getMessage());
        }
    }

    /**
     * Retrieves a user by a specific field and its value (e.g., user_name or user_email).
     * 
     * @param field The field to search by (e.g., user_name, user_email).
     * @param value The value of the field to search for.
     * @return A result object containing the user data or an error message.
     */
    public Result handleRetrieve(String field, String value) {
        try {
            // Retrieve user by field and value and wrap the result
            return toResult(userDAO.retrieve(field, value));
        } catch (Exception e) {
            // Handle any exception that occurs during the retrieval
            return AuthResult.error(null, "Error occurred while retrieving user.", e.getMessage());
        }
    }

    /**
     * Retrieves the user ID based on a specific field and value.
     * 
     * @param field The field to search by (e.g., user_name, user_email).
     * @param value The value of the field to search for.
     * @return A result object containing the user ID or an error message.
     */
    public Result handleRetrieveId(String field, String value) {
        try {
            // Retrieve user ID by field and value
            int result = userDAO.retrieveId(field, value);
            if (result == -1) {
                // If the result is -1, it indicates failure in retrieving the ID
                return new Result<>(result, 400, "The operation was not successful.", "The user id returned is marked as default, indicating the operation did not succeed.");
            }
            // If the result is valid, return a success result
            return new Result<>(result, 200, "The operation was completed successfully.", null);
        } catch (Exception e) {
            // Handle any exception that occurs during the ID retrieval
            return new Result<>(null, 500, "Error occurred while retrieving user id.", e.getMessage());
        }
    }

    /**
     * Converts a User object into a Result object. This method handles cases where the user
     * might be null or marked as default, returning appropriate results.
     * 
     * @param user The user object to convert.
     * @return A result object containing the user data or an error message.
     */
    public Result toResult(User user) {
        if (user == null) {
            // Return an error result if the user is null
            return AuthResult.error(user, "Error occurred while retrieving user.", "Unexpected error.");
        }

        if (user.isDefault()) {
            // Return a failure result if the user is marked as default
            return AuthResult.fail(user, "The operation was not successful.", "The user returned is marked as default, indicating the operation did not succeed.");
        }

        // Return a success result if the user is valid
        return AuthResult.success(user, "The operation was completed successfully.");
    }
}
