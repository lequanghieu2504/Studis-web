package model.dto;

/**
 * Data Transfer Object (DTO) for user registration information.
 * This class holds the necessary details for registering a user, including name, email,
 * password, and confirmation of the password.
 * 
 * Example use case: Used to transfer user registration data during the user sign-up process.
 */
public class RegisterDTO {
    
    private final String name;           // User's name for registration
    private final String email;          // User's email for registration
    private final String password;       // User's password for registration
    private final String confirmPassword; // Password confirmation for registration

    /**
     * Constructor to initialize the registration details.
     * @param name The user's name.
     * @param email The user's email address.
     * @param password The user's password.
     * @param confirmPassword The password confirmation.
     */
    public RegisterDTO(String name, String email, String password, String confirmPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    /**
     * Gets the user's name.
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user's email address.
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the user's password.
     * @return The user's password.
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
