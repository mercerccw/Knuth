<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoodBooks.com ${param.title}</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bookstore.css"/>

</head>
<body>
	<jsp:include page="/Header.jsp"/>

	<jsp:include page="/${param.content}.jsp"/>
</body>
</html>