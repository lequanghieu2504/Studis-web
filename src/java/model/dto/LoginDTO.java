package model.dto;

/**
 * Data Transfer Object (DTO) for user login information.
 * This class holds the user's name or email and password for authentication purposes.
 * 
 * Example use case: Used to transfer login credentials during the login process.
 */
public class LoginDTO {
    
    private final String nameOrEmail; // User's name or email for login
    private final String password;    // User's password for login

    /**
     * Constructor to initialize the login credentials.
     * @param nameOrEmail The user's name or email address.
     * @param password The user's password.
     */
    public LoginDTO(String nameOrEmail, String password) {
        this.nameOrEmail = nameOrEmail;
        this.password = password;
    }

    /**
     * Gets the user's name or email.
     * @return The user's name or email.
     */
    public String getNameOrEmail() {
        return nameOrEmail;
    }

    /**
     * Gets the user's password.
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Provides a string representation of the LoginDTO object.
     * @return A string representation of the LoginDTO with nameOrEmail and password.
     */
    @Override
    public String toString() {
        return "LoginDTO{" + "nameOrEmail=" + nameOrEmail + ", password=" + password + '}';
    }
}
