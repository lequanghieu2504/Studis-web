/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.validatorUtils.DataFormatValidator;

/**
 *
 * @author ho huy
 */
@WebServlet("/confirmEmail")
public class ConfirmEmailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("confirmEmail");
        String captcha = request.getParameter("captcha");
        
        if(!DataFormatValidator.isEmailValid(email)){
            //
        }
        
        
    }
}
