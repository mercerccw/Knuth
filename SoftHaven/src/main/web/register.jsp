<%--
  Created by IntelliJ IDEA.
  User: Clayton
  Date: 4/20/2020
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<a href="index.jsp">Home</a>
<div style="text-align: center">
    <h1>Register</h1>
    <form action="register" method="post">
        <label for="first_name">First Name:</label>
        <input name="first_name" size="30" id="first_name" required/>
        <br><br>
        <label for="last_name">Last Name:</label>
        <input name="last_name" size="30" id="last_name" required/>
        <br><br>
        <label for="email">Email:</label>
        <input name="email" size="30" id="email" required/>
        <br><br>
        <label for="position">Position:</label>
        <select name="position" id="position" required>
            <option value="master">Ship Master</option>
            <option value="agent">Ship Agent</option>
            <option value="customs">Customs Officer</option>
        </select>
        <br><br>
        <label for="password">Password:</label>
        <input name="password" id="password" required/>
        <br><br>
        <label for="confirm_password">Confirm Password:</label>
        <input name="confirm_password" id="confirm_password" required/>
        <br><br>
        <button type="submit">Register</button>
    </form>
    ${message}
</div>
</body>
</html>
