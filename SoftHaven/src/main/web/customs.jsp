<%@ page import="models.User" %>
<%@ page import="daos.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customs</title>
</head>

<body>
<div style="text-align: center">
    <h1>Customs</h1>
    <p>${user.position}</p>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>
</body>
</html>