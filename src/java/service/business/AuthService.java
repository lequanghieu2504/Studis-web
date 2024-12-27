package service.business;

import javax.servlet.http.HttpServletResponse;
import model.dto.LoginDTO;
import model.dto.RegisterDTO;
import model.entity.Result;
import model.entity.User;
import service.dao.UserModificationService;
import service.dao.UserRetrieveService;
import utils.hashUtils.HashUtils;
import utils.resultUtils.AuthResult;

/**
 * Service class to handle user authentication operations such as login and registration.
 * This class interacts with DAO services for user retrieval and user modification.
 */
public class AuthService {

    // Service for retrieving user information from the database
    private UserRetrieveService urs = new UserRetrieveService();
    
    // Service for handling user modification, such as creating new users
    private UserModificationService ums = new UserModificationService();

    /**
     * Handles the login process by validating the user credentials.
     * 
     * @param loginDTO The login data transfer object containing user credentials.
     * @return Result containing the outcome of the login attempt, including any error or success message.
     */
    public Result login(LoginDTO loginDTO) {
        // Retrieve the user from the database using the provided username or email
        Result findResult = urs.handleRetrieve(loginDTO.getNameOrEmail());
        User user = (User) findResult.getData();

        // Check if there was an internal server error when retrieving the user
        if (findResult.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
            return AuthResult.error(null, "Server error. Unable to retrieve user.", findResult.getErrorDetails());
        }

        // Check if there was a bad request error
        if (findResult.getStatus() == HttpServletResponse.SC_BAD_REQUEST) {
            return AuthResult.fail(user, "Server error. Unable to retrieve user.", findResult.getErrorDetails());
        }

        // Verify the user's password using the salt and hashed password stored in the database
        if (HashUtils.verifyPassword(loginDTO.getPassword(), user.getSalt(), user.getHashedPassword())) {
            // Password matches, login is successful
            return AuthResult.success(user, "login successful");
        } else {
            // Password does not match, login failed
            return AuthResult.fail(null, "Invalid password", null);
        }
    }

    /**
     * Handles the registration process by creating a new user.
     * 
     * @param registerDTO The registration data transfer object containing the user's registration information.
     * @return Result containing the outcome of the registration attempt, including any error or success message.
     */
    public Result register(RegisterDTO registerDTO) {
        // Check if the password and confirmation password match
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            return AuthResult.fail(null, "Passwords do not match", null);
        }

        // Handle the creation of the new user
        Result registrationResult = ums.handleCreate(registerDTO.getName(), registerDTO.getEmail(), registerDTO.getPassword());

        // Check if the registration was successful
        if (registrationResult.getStatus() != HttpServletResponse.SC_OK) {
            // If the registration failed, return the result from the user modification service
            return registrationResult;
        }

        // If registration is successful, return a success result
        return AuthResult.success(null, "Registration successful");
    }
}
