<%@ page import="daos.BerthDAO" %>
<%@ page import="daos.UserDAO" %>
<%@ page import="models.Berth" %>
<%@ page import="models.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ship Agent</title>
</head>
<body>

<div style="text-align: center">
    <h1>Ship Agent</h1>
    <p>${user.position}</p>
    <p>${berths}</p>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>
</body>
</html>