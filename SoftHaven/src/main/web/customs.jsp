<%@ page import="models.User" %>
<%@ page import="daos.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customs</title>
</head>

<body>
<%
    User user = (User) session.getAttribute("user");
    UserDAO userDao = new UserDAO();
    User revised_user = userDao.checkPosition(user.getEmail());
    user.setPosition(revised_user.getPosition());
    session.setAttribute("user", user);
    if (!user.getPosition().equals("customs")) {
        response.sendRedirect(request.getContextPath() + "/" + user.getPosition() + ".jsp");
    }
%>
<div style="text-align: center">
    <h1>Customs</h1>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>
</body>
</html>