<%--
  Created by IntelliJ IDEA.
  User: Eli
  Date: 3/30/2020
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>--%>
<%--<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>--%>
<html>
<head>
    <title>SoftHaven</title>
<%--    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>--%>
    <link rel='stylesheet' href='./styles/basic_reset.css'>
</head>
<body>
<div class="content-container">
    <div id="content" class="content-container">
        <h1 id="title">SoftHaven</h1>
        <div><a href="${pageContext.request.contextPath}/login">login</a></div>
        <br>
        <div><a href="${pageContext.request.contextPath}/register">register</a></div>
    </div>
</div>

</body>
</html>
