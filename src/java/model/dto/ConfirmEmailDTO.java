package model.dto;

/**
 * Data Transfer Object (DTO) for confirming an email.
 * This class holds the email address that is to be confirmed during the authentication process.
 * 
 * Example use case: Used to pass email data when confirming or validating the user's email during login or password reset processes.
 */
public class ConfirmEmailDTO {
    private final String email;

    /**
     * Constructor to initialize the email for confirmation.
     * @param email The email address to be confirmed.
     */
    public ConfirmEmailDTO(String email) {
        this.email = email;
    }

    /**
     * Gets the email address.
     * @return The email address.
     */
    public String getEmail() {
        return email; // Return the email address.
    }
}
