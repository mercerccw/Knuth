<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="scripts/tableFunctions.js"></script>
<html>
<head>
    <title>Ship Agent</title>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
</head>
<body>
<div class="text-center">
    <div class="text-center">
        <a href="${pageContext.request.contextPath}/">Home</a>
        <h1>Ship Agent</h1>
        <p>${user.position}</p>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
    <br><br><br><br>
    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#berthing_data"
            aria-expanded="false" aria-controls="berthing_data">
        Berthing Data Table
    </button>
    <br><br><br>
    <div class="text-center collapse" id="berthing_data">
        <input type="text" id="port" onkeyup="filter()" placeholder="Search for Port..">
        <input type="text" id="quay" onkeyup="filter()" placeholder="Search for Quay..">
        <input type="text" id="type" onkeyup="filter()" placeholder="Search for Terminal Type..">
        <input type="text" id="number" onkeyup="filter()" placeholder="Search for Berth Number..">
        <input type="text" id="ship_imo" onkeyup="filter()" placeholder="Search for Ship..">
        <br><br><br><br><br>
        <table class="table" id="berths">
            <tr>
                <th scope="col">Port</th>
                <th scope="col">Quay</th>
                <th scope="col">Terminal Type</th>
                <th scope="col">Berth Number</th>
                <th scope="col">Ship Docked</th>
            </tr>
            <c:forEach items="${berthList}" var="berth">
                <tr class="data-row">
                    <td scope="row">${berth.port}</td>
                    <td scope="row">${berth.quay}</td>
                    <td scope="row">${berth.type}</td>
                    <td scope="row">${berth.number}</td>
                    <td scope="row">${berth.ship_imo}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>




</body>
</html>