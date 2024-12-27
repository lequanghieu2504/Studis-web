package service.dao;

import dao.UserDAO;
import exceptions.BusinessException;
import model.entity.Result;
import model.entity.User;
import utils.hashUtils.HashUtils;

/**
 * Service class responsible for modifying user data. This includes handling user creation,
 * updating user details (name, email, password), and deleting a user.
 * The methods ensure that business rules are followed and transactions are handled correctly.
 */
public class UserModificationService extends BaseService {

    private UserDAO userDAO = new UserDAO(); // DAO class for interacting with the user database

    /**
     * Handles user registration by checking if the username and email already exist,
     * generating a salt, hashing the password, and creating a new user in the database.
     * 
     * @param name The user's name.
     * @param email The user's email.
     * @param password The user's plain-text password.
     * @return A result object indicating the outcome of the operation (success or failure).
     */
    public Result handleCreate(String name, String email, String password) {
        return executeServiceOperation(() -> {
            // Check if the username already exists in the database
            if (!userDAO.retrieve("user_name", name).isDefault()) {
                throw new BusinessException("User name already exists.");
            }

            // Check if the email already exists in the database
            if (!userDAO.retrieve("user_email", email).isDefault()) {
                throw new BusinessException("User email already exists.");
            }

            // Generate a salt and hash the password before storing
            String salt = HashUtils.generateSalt();
            String hashedPassword = HashUtils.hashPassword(password, salt);

            // Create the user in the database
            return userDAO.create(new User(name, email, hashedPassword, salt));
        });
    }

    /**
     * Handles updating the user's name or email. Ensures that the new name or email is not already in use.
     * 
     * @param id The ID of the user to update.
     * @param field The field to update ("user_name" or "user_email").
     * @param value The new value for the field.
     * @return A result object indicating the outcome of the operation (success or failure).
     */
    public Result handleUpdateNameOrEmail(int id, String field, String value) {
        return executeServiceOperation(() -> {
            // Retrieve the user by ID
            User updateUser = userDAO.retrieve(id);
            if (updateUser == null || updateUser.isDefault()) {
                throw new BusinessException("User not found or is default.");
            }

            // Check if the new name or email already exists
            if (!userDAO.retrieve(field, value).isDefault()) {
                throw new BusinessException("This " + field + " already exists.");
            }

            // Update the corresponding field
            switch (field) {
                case "user_name":
                    updateUser.setName(value);
                    break;
                case "user_email":
                    updateUser.setEmail(value);
                    break;
                default:
                    throw new BusinessException("Invalid field for update.");
            }

            // Update the user in the database
            return userDAO.update(updateUser);
        });
    }

    /**
     * Handles updating the user's password. The new password is hashed and the salt is regenerated.
     * 
     * @param id The ID of the user to update.
     * @param newPassword The new password to set.
     * @return A result object indicating the outcome of the operation (success or failure).
     */
    public Result handleUpdatePassword(int id, String newPassword) {
        return executeServiceOperation(() -> {
            // Retrieve the user by ID
            User updateUser = userDAO.retrieve(id);
            if (updateUser == null || updateUser.isDefault()) {
                throw new BusinessException("User not found or is default.");
            }

            // Generate a new salt and hash the new password
            String salt = HashUtils.generateSalt();
            String hashedPassword = HashUtils.hashPassword(newPassword, salt);

            // Set the new hashed password and salt
            updateUser.setHashedPassword(hashedPassword);
            updateUser.setSalt(salt);

            // Update the user in the database
            return userDAO.update(updateUser);
        });
    }

    /**
     * Handles deleting a user by ID.
     * 
     * @param id The ID of the user to delete.
     * @return A result object indicating the outcome of the operation (success or failure).
     */
    public Result handleDelete(int id) {
        return executeServiceOperation(() -> {
            // Retrieve the user by ID
            User deleteUser = userDAO.retrieve(id);
            if (deleteUser == null || deleteUser.isDefault()) {
                throw new BusinessException("User not found or is default.");
            }

            // Delete the user from the database
            return userDAO.delete(deleteUser);
        });
    }
}
