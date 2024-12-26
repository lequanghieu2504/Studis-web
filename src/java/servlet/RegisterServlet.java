/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.AuthController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Result;
import utils.validatorUtils.DataFormatValidator;

/**
 *
 * @author ho huy
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private AuthController ac = new AuthController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!DataFormatValidator.isNameValid(name)) {
            request.setAttribute("errorMessage", "Invalid name format.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!DataFormatValidator.isEmailValid(email)) {
            request.setAttribute("errorMessage", "Invalid email format.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!DataFormatValidator.isPasswordValid(password)) {
            request.setAttribute("errorMessage", "Invalid password format.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        Result result = ac.handleRegister(name, email, password, confirmPassword);

        if (result.getStatus() == HttpServletResponse.SC_CREATED) {
            request.setAttribute("successMessage", "Registration successful! You can now log in.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Registration failed: " + result.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
