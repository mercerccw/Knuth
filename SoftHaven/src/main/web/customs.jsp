<%@ page import="com.softhaven.bean.User" %>
<%@ page import="com.softhaven.dao.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customs</title>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>

<body>
<div class="text-center">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <h1>Customs Officer Dashboard</h1>
    <h3>${user.last_name}, ${user.first_name}</h3>
    <div>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
        <br><br>
        <a href="${pageContext.request.contextPath}/vessels?vesselsPerPage=50&currentPage=1">Vessel List</a>
        <br><br>
        <a href="${pageContext.request.contextPath}/allReviewForms">Pre-Arrival Forms</a>
    </div>
    <div>
        <c:if test="${forms != null}">
            <h1>You have Pre-Arrival forms to review</h1>
        </c:if>
    </div>
</div>
</body>
</html>