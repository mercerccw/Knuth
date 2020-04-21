<%@ page import="daos.UserDAO" %>
<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ship Agent</title>
</head>
<%
    User user = (User) session.getAttribute("user");
    UserDAO userDao = new UserDAO();
    User revised_user = userDao.checkPosition(user.getEmail());
    user.setPosition(revised_user.getPosition());
    session.setAttribute("user", user);
    if (!user.getPosition().equals("agent")) {
        response.sendRedirect(request.getContextPath() + "/" + user.getPosition() + ".jsp");
    }
%>
<body>

<div style="text-align: center">
    <h1>Ship Agent</h1>
    <p>${user.position}</p>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>
</body>
</html>