<%@ page import="models.User" %><%--
  Created by IntelliJ IDEA.
  User: Clayton
  Date: 4/18/2020
  Time: 12:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ship Master</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    if(!user.getPosition().equals("master")){
        response.sendRedirect(request.getContextPath() + "/" + user.getPosition() + ".jsp");
    }
%>
<div style="text-align: center">
    <h1>Ship Master</h1>
    <p>${user.position}</p>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>
</body>
</html>
