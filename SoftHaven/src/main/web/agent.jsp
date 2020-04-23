<%@ page import="daos.BerthDAO" %>
<%@ page import="daos.UserDAO" %>
<%@ page import="models.Berth" %>
<%@ page import="models.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Ship Agent</title>
</head>
<body>

<div style="text-align: center">
    <h1>Ship Agent</h1>
    <p>${user.position}</p>
    <br><br><br><br>
    <div style="text-align: center">
        <table style="text-align: center">
            <c:forEach items="${berthList}" var="berth">
                <tr>
                    <td>${berth.port}</td>
                    <td>${berth.quay}</td>
                    <td>${berth.type}</td>
                    <td>${berth.number}</td>
                    <td>${berth.ship_imo}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>
</body>
</html>