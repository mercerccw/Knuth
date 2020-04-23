<%@ page import="daos.UserDAO" %>
<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ship Master</title>
</head>
<body>

<div style="text-align: center">
    <h1>Ship Master</h1>
    <p>${user.position}</p>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>
</body>
</html>
