package utils.validator;

import model.entity.Result;
import utils.resultUtils.ValidationResult;

/**
 * PasswordValidator is a class that validates a given password based on various security criteria.
 * It checks the password's length, presence of uppercase and lowercase letters, and inclusion of digits.
 * 
 * This class implements the Validator interface for a generic String input (the password).
 */
public class PasswordValidator implements Validator<String> {

    // Regular expression to match a valid password format (must contain at least one uppercase letter, one lowercase letter, and one digit)
    private String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";

    /**
     * Validates if the input password meets the required security criteria.
     * 
     * @param value The password to be validated.
     * @return A Result indicating whether the password is valid or not.
     */
    @Override
    public Result<Void> validate(String value) {
        // Check if the password length is less than 8 characters
        if (value.length() < 8) {
            return ValidationResult.fail(false, "Password too short", "Password must be at least 8 characters long");
        }

        // Check if the password contains at least one uppercase letter
        if (!value.matches(".*[A-Z].*")) {
            return ValidationResult.fail(false, "Missing uppercase letter", "Password must contain at least one uppercase letter");
        }

        // Check if the password contains at least one lowercase letter
        if (!value.matches(".*[a-z].*")) {
            return ValidationResult.fail(false, "Missing lowercase letter", "Password must contain at least one lowercase letter");
        }

        // Check if the password contains at least one digit
        if (!value.matches(".*\\d.*")) {
            return ValidationResult.fail(false, "Missing digit", "Password must contain at least one digit");
        }

        // Check if the password matches the required format (contains uppercase, lowercase, and digits)
        if (value.matches(regex)) {
            return ValidationResult.success(true, "Valid password");
        }

        // If password does not meet the required format, return failure
        return ValidationResult.fail(false, "Invalid password format", "Password does not meet the required format");
    }
}
