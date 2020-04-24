<%@ page import="com.softhaven.bean.User" %>
<%@ page import="com.softhaven.dao.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customs</title>
</head>

<body>
<div style="text-align: center">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <h1>Customs</h1>
    <p>${user.position}</p>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
    <br><br>
    <a href="${pageContext.request.contextPath}/vessels">Vessel List</a>
</div>
</body>
</html>