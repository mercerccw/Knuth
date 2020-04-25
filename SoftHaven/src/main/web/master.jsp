<%@ page import="com.softhaven.dao.UserDAO" %>
<%@ page import="com.softhaven.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ship Master</title>
</head>
<body>

<div style="text-align: center">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <h1>Ship Master</h1>
    <h4>Your job title: ${user.position}</h4>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>
</body>
</html>
