package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dto.LoginDTO;
import model.dto.RegisterDTO;
import model.entity.Result;
import service.business.AuthService;
import utils.validator.AuthValidator;
import utils.view.ViewHandler;

/**
 * Controller to handle login and registration requests.
 * Contains methods for login and register functionalities.
 */
public class AuthController extends HttpServlet {
    
    private AuthValidator av = new AuthValidator(); // Validator for login and registration.
    private AuthService as = new AuthService();     // Service to handle login and registration logic.
    
    /**
     * Handles login request.
     * @param request  HttpServletRequest object containing the request information.
     * @param response HttpServletResponse object to send the response.
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve login information from the request and create a LoginDTO object.
        LoginDTO loginDTO = new LoginDTO(request.getParameter("nameOrEmail"), request.getParameter("password"));
        
        // Validate the login information.
        Result checkFormat = av.validateLogin(loginDTO);
        
        if (!checkFormat.isSuccess()) {
            // If validation fails, forward back to the login page with the error message.
            ViewHandler.forwardWithMessage(request, response, "login.jsp", checkFormat.getMessage());
            return;
        }
        
        // Handle the login process.
        Result result = as.login(loginDTO);
        
        if (!result.isSuccess()) {
            // If login fails, forward back to the login page with the error message.
            ViewHandler.forwardWithMessage(request, response, "login.jsp", result.getMessage());
            return;
        }
        
        // If login is successful, set the user data in the session and redirect to the home page.
        request.getSession().setAttribute("user", result.getData());
        response.sendRedirect("home.jsp");
    }
    
    /**
     * Handles registration request.
     * @param request  HttpServletRequest object containing the request information.
     * @param response HttpServletResponse object to send the response.
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve registration information from the request and create a RegisterDTO object.
        RegisterDTO registerDTO = new RegisterDTO(request.getParameter("name"), request.getParameter("email"), request.getParameter("password"), request.getParameter("confirmPassword"));
        
        // Validate the registration information.
        Result checkFormat = av.validateRegister(registerDTO);
        
        if (!checkFormat.isSuccess()) {
            // If validation fails, forward back to the registration page with the error message.
            ViewHandler.forwardWithMessage(request, response, "register.jsp", checkFormat.getMessage());
            return;
        }
        
        // Handle the registration process.
        Result result = as.register(registerDTO);
        
        if (!result.isSuccess()) {
            // If registration fails, forward back to the registration page with the error message.
            ViewHandler.forwardWithMessage(request, response, "register.jsp", result.getMessage());
        } else {
            // If registration is successful, forward back to the registration page with a success message.
            ViewHandler.forwardWithMessage(request, response, "register.jsp", "Register successfully.");
        }
        
    }
}
