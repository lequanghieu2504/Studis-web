package servlet;

import controller.PasswordController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet handles password update requests.
 * It listens for POST requests at the "/updatePassword" URL and delegates the
 * password update logic to the PasswordController.
 */
@WebServlet("/updatePassword")  // Maps the servlet to the "/updatePassword" URL pattern
public class UpdatePasswordServlet extends HttpServlet {

    private PasswordController pc = new PasswordController();  // Creating an instance of PasswordController to handle password update logic

    /**
     * Handles POST requests for updating a user's password.
     * The password update logic is delegated to the PasswordController's updatePassword method.
     * 
     * @param request The HttpServletRequest object containing the request data.
     * @param response The HttpServletResponse object to send the response to the client.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        pc.updatePassword(request, response);  // Delegating the password update handling to PasswordController
    }
}
