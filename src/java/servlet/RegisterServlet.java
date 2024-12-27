package servlet;

import controller.AuthController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is responsible for handling registration requests.
 * It listens for POST requests at the "/register" URL and delegates the
 * registration logic to the AuthController.
 */
@WebServlet("/register")  // This annotation maps the servlet to the "/register" URL pattern
public class RegisterServlet extends HttpServlet {

    private AuthController ac = new AuthController();  // Creating an instance of AuthController to handle registration logic

    /**
     * Handles POST requests for registering a user.
     * The registration logic is delegated to the AuthController's register method.
     * 
     * @param request The HttpServletRequest object containing the request data.
     * @param response The HttpServletResponse object to send the response to the client.
     * @throws ServletException If an error occurs during the handling of the request.
     * @throws IOException If an error occurs while sending the response.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ac.register(request, response);  // Delegating the registration handling to AuthController
    }
}
