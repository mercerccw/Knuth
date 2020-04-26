<%--
  Created by IntelliJ IDEA.
  User: Clayton
  Date: 4/25/2020
  Time: 8:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pre-Arrival Forms</title>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
<div style="text-align: center">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <h1>Pre-Arrival Forms</h1>
    <h3>Customs Officer Portal</h3>
    <p>${user.last_name}, ${user.first_name}</p>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>

</body>
</html>
