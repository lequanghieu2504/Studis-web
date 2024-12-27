package servlet;

import controller.AuthController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is responsible for handling login requests.
 * It listens for POST requests at the "/login" URL and delegates the
 * login logic to the AuthController.
 */
@WebServlet("/login")  // This annotation maps the servlet to the "/login" URL pattern
public class LoginServlet extends HttpServlet {

    private AuthController ac = new AuthController();  // Creating an instance of AuthController to handle login logic

    /**
     * Handles POST requests for logging in a user.
     * The login logic is delegated to the AuthController's login method.
     * 
     * @param request The HttpServletRequest object containing the request data.
     * @param response The HttpServletResponse object to send the response to the client.
     * @throws ServletException If an error occurs during the handling of the request.
     * @throws IOException If an error occurs while sending the response.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ac.login(request, response);  // Delegating the login handling to AuthController
    }
}
