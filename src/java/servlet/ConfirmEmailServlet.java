package servlet;

import controller.PasswordController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is responsible for handling requests to confirm a user's email.
 * It listens for POST requests at the "/confirmEmail" URL and delegates the 
 * email confirmation logic to the PasswordController.
 */
@WebServlet("/confirmEmail")  // This annotation maps the servlet to the "/confirmEmail" URL pattern
public class ConfirmEmailServlet extends HttpServlet {

    private PasswordController pc = new PasswordController();  // Creating an instance of PasswordController to handle email confirmation

    /**
     * Handles POST requests to confirm a user's email.
     * The email confirmation logic is delegated to the PasswordController.
     * 
     * @param request The HttpServletRequest object containing the request data.
     * @param response The HttpServletResponse object to send the response to the client.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        pc.confirmEmail(request, response);  // Delegating email confirmation handling to PasswordController
    }
}
