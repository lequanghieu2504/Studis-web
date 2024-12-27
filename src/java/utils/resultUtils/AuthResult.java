package utils.resultUtils;

import model.entity.User;

/**
 * AuthResult is a subclass of BaseResult that is specifically designed to handle results
 * related to authentication operations (e.g., login, registration).
 * This class can hold the result of authentication-related operations for a User object.
 * 
 * AuthResult is used to return a consistent structure for authentication responses
 * such as success, failure, and error states.
 * 
 * Inherited from BaseResult, it provides the basic result structure and can be extended
 * with specific methods for handling authentication responses.
 * 
 * @param <User> The type of entity returned in the result, which is the User object in this case.
 */
public class AuthResult extends BaseResult<User> {

    // No additional methods or properties are defined here yet.
    // It simply extends BaseResult<User> and inherits its properties and behavior.

}
