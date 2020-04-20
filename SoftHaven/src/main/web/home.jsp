<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Admin CPanel - Bookshop</title>
</head>
<body>
<div style="text-align: center">
    <div>

    </div>

    <h1>Welcome to SoftHaven Website Admin Panel</h1>
    <b>${user.first_name} (${user.email})</b>
    <br><br>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>
</body>
</html>