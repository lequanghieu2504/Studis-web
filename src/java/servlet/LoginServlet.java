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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AuthController ac = new AuthController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameOrEmail = request.getParameter("nameOrEmail");
        String password = request.getParameter("password");

        if (!DataFormatValidator.isNameOrEmailValid(nameOrEmail)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid email or name format.");
            return;
        }

        if (!DataFormatValidator.isPasswordValid(password)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid password format.");
            return;
        }

        Result result = ac.handleLogin(nameOrEmail, password);

        if (result.getStatus() == HttpServletResponse.SC_OK) {
            request.getSession().setAttribute("user", result.getData());
            response.sendRedirect("home.jsp");
        } else {
            request.setAttribute("error", result.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }
}
