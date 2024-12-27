<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <h1>Register</h1>
        
        <% 
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
            <div id="success-message" style="background-color: #FFFFFF; color: black; padding: 15px; text-align: center; margin-bottom: 20px;">
                <%= message %>
            </div>
        <% } %>

        <form action="register" method="post">
            Enter user name         : <input type="text" name="name"><br>
            Enter email             : <input type="text" name="email"><br>
            Enter password          : <input type="text" name="password"><br>
            Enter confirm password  : <input type="text" name="confirmPassword"><br>
            <input type="submit"><br>
            Already have an account? <a href="login.jsp">click here</a>
        </form>
    </body>
</html>