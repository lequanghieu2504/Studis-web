package utils.validator;

import model.entity.Result;
import utils.resultUtils.ValidationResult;

/**
 * NameOrEmailValidator is a class that validates if a given value is either a valid email or a valid name.
 * It checks the input based on a predefined email format and the length of the input for name validation.
 * 
 * This class implements the Validator interface for a generic String input.
 */
public class NameOrEmailValidator implements Validator<String> {

    // Regular expression for validating email format
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    /**
     * Validates if the input value is either a valid email address or a valid name.
     * 
     * @param value The input value to be validated (either email or name).
     * @return A Result indicating whether the validation is successful or not.
     */
    @Override
    public Result<Void> validate(String value) {
        // Check if the value is null or empty
        if (value == null || value.isEmpty()) {
            return ValidationResult.fail(false, "Value cannot be null or empty", "Input is empty or null");
        }

        // Check if the value matches the email format
        if (value.matches(EMAIL_REGEX)) {
            return ValidationResult.success(true, "Valid email format");
        }

        // Check if the value is within valid length for a name (between 3 and 300 characters)
        if (value.length() >= 3 && value.length() <= 300) {
            return ValidationResult.success(true, "Valid name length");
        }

        // If the value doesn't match email format or valid name length, return failure
        return ValidationResult.fail(false, "Invalid input", "Input does not match valid email or name format");
    }
}
