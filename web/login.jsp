<%-- 
    Document   : login
    Created on : 23-Dec-2024, 11:55:19
    Author     : ho huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <h1>Login</h1>
        
        <% 
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
            <div id="success-message" style="background-color: #FFFFFF; color: black; padding: 15px; text-align: center; margin-bottom: 20px;">
                <%= message %>
            </div>
        <% } %>
        
        <form action="login" method="post">
            Enter username/email    : <input type="text" name="nameOrEmail"><br>
            Enter password          : <input type="text" name="password"><br>
            <input type="submit"><br>
            <a href="register.jsp">Register new account</a><br>
            <a href="confirm-email.jsp">Forgot password</a><br>
            
        </form>
    </body>
</html>
