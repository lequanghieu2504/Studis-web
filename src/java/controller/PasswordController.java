package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dto.ConfirmEmailDTO;
import model.dto.UpdatePasswordDTO;
import model.entity.Result;
import model.entity.User;
import service.dao.UserModificationService;
import service.dao.UserRetrieveService;
import utils.validator.UpdatePasswordValidator;
import utils.view.ViewHandler;

/**
 * Controller to handle password-related actions.
 * Includes methods for confirming email and updating password.
 */
public class PasswordController extends HttpServlet {

    private UpdatePasswordValidator upv = new UpdatePasswordValidator(); // Validator for updating passwords.
    private UserRetrieveService urs = new UserRetrieveService();         // Service to retrieve user details.
    private UserModificationService ums = new UserModificationService(); // Service to modify user data (e.g., updating password).

    /**
     * Handles email confirmation before allowing password update.
     * @param request  HttpServletRequest containing the request data.
     * @param response HttpServletResponse to send the response.
     */
    public void confirmEmail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Create a DTO object from the provided email.
        ConfirmEmailDTO confirmEmailDTO = new ConfirmEmailDTO(request.getParameter("confirmEmail"));
        
        // Validate the email format.
        Result checkFormat = upv.validate(confirmEmailDTO);
        if (!checkFormat.isSuccess()) {
            // If the email format is invalid, forward back with an error message.
            ViewHandler.forwardWithMessage(request, response, "confirm-email.jsp", checkFormat.getMessage());
        }

        // Check if the email exists in the database.
        Result checkExist = urs.handleRetrieve("user_email", confirmEmailDTO.getEmail());
        if (!checkExist.isSuccess()) {
            // If the email doesn't exist, forward back with an error message.
            ViewHandler.forwardWithMessage(request, response, "confirm-email.jsp", checkExist.getMessage());
        } else {
            // If the email exists, store the user in the session and redirect to the password update page.
            request.getSession().setAttribute("user", checkExist.getData());
            response.sendRedirect("update-password.jsp");
        }
    }

    /**
     * Handles the password update process.
     * @param request  HttpServletRequest containing the request data.
     * @param response HttpServletResponse to send the response.
     */
    public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve user from session and create UpdatePasswordDTO with the new password details.
        UpdatePasswordDTO updatePasswordDTO = new UpdatePasswordDTO(
                (User) request.getSession().getAttribute("user"),
                request.getParameter("password"),
                request.getParameter("confirmPassword"));
        
        // Remove user from session to ensure it is processed again.
        request.getSession().removeAttribute("user");
        
        // Validate the password format.
        Result checkFormat = upv.validate(updatePasswordDTO);
        if (!checkFormat.isSuccess()) {
            // If the password format is invalid, forward back with an error message.
            ViewHandler.forwardWithMessage(request, response, "update-password.jsp", checkFormat.getMessage());
        }

        // Attempt to update the password in the database.
        Result result = ums.handleUpdatePassword(updatePasswordDTO.getUser().getId(), updatePasswordDTO.getPassword());
        if (!result.isSuccess()) {
            // If updating the password fails, forward back with an error message.
            ViewHandler.forwardWithMessage(request, response, "update-password.jsp", result.getMessage());
        }

        // If the password update is successful, redirect to the login page.
        response.sendRedirect("login.jsp");
    }
}
