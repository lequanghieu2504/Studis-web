/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.PasswordController;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
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

@WebServlet("/updatePassword")
public class UpdatePasswordServlet extends HttpServlet{
    
    private PasswordController pc = new PasswordController();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getAttribute("confirmEmail").toString();
        
        if(!DataFormatValidator.isPasswordValid(password)){
            //
            return;
        }
        
        if(!password.equals(confirmPassword)){
            //
            return;
        }
        
        Result result = pc.updatePassword(email, password);
        if(result.getStatus() == HttpServletResponse.SC_OK){
            //
            response.sendRedirect("login.jsp");
        }else{
            //
            RequestDispatcher dispatcher = request.getRequestDispatcher("updatePassword");
            dispatcher.forward(request, response);
        }
        
    }
}
