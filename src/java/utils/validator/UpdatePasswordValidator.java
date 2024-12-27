package utils.validator;

import model.dto.ConfirmEmailDTO;
import model.dto.UpdatePasswordDTO;
import model.entity.Result;
import utils.resultUtils.AuthResult;

/**
 * UpdatePasswordValidator is a class that validates the data for updating the password and confirming email.
 * It validates the email and password to ensure they meet the required security and format standards.
 */
public class UpdatePasswordValidator {
    
    // Instance of PasswordValidator for validating passwords.
    private PasswordValidator pv = new PasswordValidator();
    
    // Instance of NameOrEmailValidator for validating email addresses.
    private NameOrEmailValidator noev = new NameOrEmailValidator();

    /**
     * Validates the email in the ConfirmEmailDTO.
     * 
     * @param confirmEmailDTO The ConfirmEmailDTO object containing the email to be validated.
     * @return A Result indicating whether the email is valid or not.
     */
    public Result<Void> validate(ConfirmEmailDTO confirmEmailDTO){
        // Validating the email in ConfirmEmailDTO using NameOrEmailValidator.
        return noev.validate(confirmEmailDTO.getEmail());
    }

    /**
     * Validates the password and confirm password in the UpdatePasswordDTO.
     * Ensures that both passwords are valid and match each other.
     * 
     * @param updatePasswordDTO The UpdatePasswordDTO object containing the password and confirm password.
     * @return A Result indicating whether the passwords are valid and match.
     */
    public Result<Void> validate(UpdatePasswordDTO updatePasswordDTO){
        // Validate password and confirm password using PasswordValidator.
        Result checkPassword = pv.validate(updatePasswordDTO.getPassword());
        Result checkConfirmPassword = pv.validate(updatePasswordDTO.getConfirmPassword());
        
        // If the password is not valid, return the failure result.
        if(!checkPassword.isSuccess()){
            return checkPassword;
        }
        
        // If the confirm password is not valid, return the failure result.
        if(!checkConfirmPassword.isSuccess()){
            return checkConfirmPassword;
        }
        
        // If both passwords are valid and match, return success.
        return AuthResult.success(true, checkPassword.getMessage());
    }
}
