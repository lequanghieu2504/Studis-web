package utils.validator;

import model.dto.LoginDTO;
import model.dto.RegisterDTO;
import model.entity.Result;
import utils.resultUtils.ValidationResult;

/**
 * AuthValidator provides validation methods for login and register data.
 * It uses specific validators for name/email and password, and applies them
 * to check the validity of input data for login and registration.
 * 
 * The class checks for valid inputs for both login and register operations 
 * and returns appropriate results based on the validation outcomes.
 */
public class AuthValidator {

    // Validators for name/email and password
    private NameOrEmailValidator noev = new NameOrEmailValidator();
    private PasswordValidator pv = new PasswordValidator();

    /**
     * Validates the login data from the provided LoginDTO.
     * 
     * @param loginDTO The login data transfer object containing name/email and password.
     * @return A Result indicating whether the login data is valid or not.
     */
    public Result<Void> validateLogin(LoginDTO loginDTO) {
        // Validate name or email
        Result checkNameOrEmail = noev.validate(loginDTO.getNameOrEmail());
        if (!checkNameOrEmail.isSuccess()) {
            return ValidationResult.fail(false, checkNameOrEmail.getMessage(), checkNameOrEmail.getErrorDetails());
        }

        // Validate password
        Result checkPassword = pv.validate(loginDTO.getPassword());
        if (!checkPassword.isSuccess()) {
            return ValidationResult.fail(false, checkPassword.getMessage(), checkPassword.getErrorDetails());
        }

        // Return success if both validations pass
        return ValidationResult.success(true, "Login data is valid.");
    }

    /**
     * Validates the registration data from the provided RegisterDTO.
     * 
     * @param registerDTO The register data transfer object containing name, email, and password.
     * @return A Result indicating whether the register data is valid or not.
     */
    public Result<Void> validateRegister(RegisterDTO registerDTO) {
        // Validate name
        Result checkName = noev.validate(registerDTO.getName());
        if (!checkName.isSuccess()) {
            return ValidationResult.fail(false, checkName.getMessage(), checkName.getErrorDetails());
        }

        // Validate email
        Result checkEmail = noev.validate(registerDTO.getEmail());
        if (!checkEmail.isSuccess()) {
            return ValidationResult.fail(false, checkEmail.getMessage(), checkEmail.getErrorDetails());
        }

        // Validate password
        Result checkPassword = pv.validate(registerDTO.getPassword());
        if (!checkPassword.isSuccess()) {
            return ValidationResult.fail(false, checkPassword.getMessage(), checkPassword.getErrorDetails());
        }

        // Return success if all validations pass
        return ValidationResult.success(true, "Register data is valid.");
    }
}
