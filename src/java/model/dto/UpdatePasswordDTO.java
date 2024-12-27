package model.dto;

import model.entity.User;

/**
 * Data Transfer Object (DTO) for updating a user's password.
 * This class holds the necessary details for updating the password, including the user object,
 * the new password, and the confirmation of the password.
 * 
 * Example use case: Used to transfer password update details for an authenticated user.
 */
public class UpdatePasswordDTO {
    
    private final User user;            // User whose password is being updated
    private final String password;      // New password
    private final String confirmPassword; // Confirmation of the new password

    /**
     * Constructor to initialize the update password details.
     * @param user The user whose password is being updated.
     * @param password The new password.
     * @param confirmPassword The confirmation of the new password.
     */
    public UpdatePasswordDTO(User user, String password, String confirmPassword) {
        this.user = user;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    /**
     * Gets the user whose password is being updated.
     * @return The user whose password is being updated.
     */
    public User getUser(){
        return user;
    }
    
    /**
     * Gets the new password.
     * @return The new password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the password confirmation.
     * @return The password confirmation.
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }
}
