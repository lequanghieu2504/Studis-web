/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.businessService;

import javax.servlet.http.HttpServletResponse;
import model.Result;
import model.User;
import service.daoService.UserModificationService;
import service.daoService.UserRetrieveService;
import utils.hashUtils.HashUtils;

/**
 *
 * @author ho huy
 */
public class AuthService {

    private UserRetrieveService urs = new UserRetrieveService();
    private UserModificationService ums = new UserModificationService();

    public Result login(String nameOrEmail, String password) {
        Result findResult = urs.handleRetrieve(nameOrEmail);
        User user = (User) findResult.getData();

        if (findResult.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
            return new Result(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error. Unable to retrieve user.", null, findResult.getErrorDetails());
        }

        if (findResult.getStatus() == HttpServletResponse.SC_BAD_REQUEST) {
            return new Result(HttpServletResponse.SC_BAD_REQUEST, "Server error. Unable to retrieve user.", null, findResult.getErrorDetails());
        }

        if (HashUtils.verifyPassword(password, user.getSalt(), user.getHashedPassword())) {
            return new Result(HttpServletResponse.SC_OK, "Login successful", user, null);
        } else {
            return new Result(HttpServletResponse.SC_UNAUTHORIZED, "Invalid password", null, null);
        }
    }

    public Result register(String name, String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return new Result(HttpServletResponse.SC_BAD_REQUEST, "Passwords do not match", null, null);
        }

        Result registrationResult = ums.handleCreate(name, email, password);

        if (registrationResult.getStatus() != HttpServletResponse.SC_OK) {
            return registrationResult;
        }

        return new Result(HttpServletResponse.SC_CREATED, "Registration successful", null, null);
    }

}
