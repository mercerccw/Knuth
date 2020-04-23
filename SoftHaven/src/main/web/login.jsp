<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>SoftHaven Login</title>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel='stylesheet' href='./styles/basic_reset.css'>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script type="text/javascript"
            src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
</head>
<body>
<div class="content-container">
    <div id="content" class="content-container">
        <a href="${pageContext.request.contextPath}/">Home</a>
        <h1>Login</h1>
        <form action="login" method="post">
            <label for="email">Email:</label>
            <input name="email" size="30" id="email"/>
            <br><br>
            <label for="password">Password:</label>
            <input type="password" name="password" size="30" id="password"/>
            <br>${message}
            <br><br>
            <button type="submit">Login</button>
        </form>
        <a href="${pageContext.request.contextPath}/register">Register</a>
    </div>
</div>
</body>
</html>