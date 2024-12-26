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
        <form action="login" method="post">
            Enter username/email    : <input type="text" name="nameOrEmail"><br>
            Enter password          : <input type="text" name="password"><br>
            <input type="submit"><br>
            <a href="register.jsp">Register new account</a><br>
            <a href="update_password.jsp">Forgot password</a><br>
            
        </form>
    </body>
</html>
