<%-- 
    Document   : confirm_email
    Created on : 26-Dec-2024, 09:19:47
    Author     : ho huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <h1>Confirm email</h1>
        <form action="confirmEmail" method="post">
            Enter confirm email: <input type="text" name="confirmEmail"><br>
            Enter captcha code: <input type="text" name="captcha"><br>
            <input type="submit"><br>
        </form><br>
    </body>
</html>
