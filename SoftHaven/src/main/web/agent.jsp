<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="scripts/berthFilter.js"></script>
<html>
<head>
    <title>Ship Agent</title>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
</head>
<body>
<div class="text-center">
    <div class="text-center">
        <a href="${pageContext.request.contextPath}/">Home</a>
        <h1>Ship Agent Dashboard</h1>
        <h3>${user.last_name}, ${user.first_name}</h3>
        <div>
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
            <br><br>
            <a href="${pageContext.request.contextPath}/vessels?vesselsPerPage=50&currentPage=1">Vessel List</a>
            <br><br>
            <a href="${pageContext.request.contextPath}/allReviewForms">Pre-Arrival Forms</a>
        </div>
    </div>

    <br><br><br>
    <div class="text-center" id="berthing_data">
        <input type="text" id="port" onkeyup="filter()" placeholder="Search for Port..">
        <input type="text" id="quay" onkeyup="filter()" placeholder="Search for Quay..">
        <input type="text" id="type" onkeyup="filter()" placeholder="Search for Terminal Type..">
        <input type="text" id="number" onkeyup="filter()" placeholder="Search for Berth Number..">
        <input type="text"
               id="ship_imo"
               onkeyup="filter()"
               placeholder="Search for Ship..">
        <label for="ship_imo">Enter <code>No Ship Docked</code> to see open berths.</label>
        <br><br><br><br><br>
        <table class="table" id="berths">
            <tr>
                <th scope="col">Port</th>
                <th scope="col">Quay</th>
                <th scope="col">Terminal Type</th>
                <th scope="col">Berth Number</th>
                <th scope="col">Ship Docked <strong>(Submit <code>0</code> to clear a booking)</strong></th>
            </tr>
            <c:forEach items="${allBerths}" var="berth">
                <tr class="data-row">
                    <td scope="row">${berth.port}</td>
                    <td scope="row">${berth.quay}</td>
                    <td scope="row">${berth.type}</td>
                    <td scope="row">${berth.number}</td>
                    <c:if test="${berth.ship_imo != 0}">
                        <td scope="row">
                            <form action="${pageContext.request.contextPath}/berths/addVessels" method="post">
                                <label>
                                        ${berth.ship_imo}
                                    <input type="text" value="${berth.number}" name="berthNumber" hidden
                                           style="display: none;"/>
                                    <input type="text" value="${berth.ship_imo}" name="vessel_imo" hidden
                                           style="display: none;"/>
                                    <input type="text" name="shipImo" placeholder="Change Vessel IMO..."/>
                                </label>
                                <input class="button" type="submit" value="submit"/>

                            </form>

                        </td>
                    </c:if>
                    <c:if test="${berth.ship_imo == 0}">
                        <td scope="row">
                            <form action="${pageContext.request.contextPath}/berths/addVessels" method="post">
                                <label>
                                    No Ship Docked
                                    <input type="text" value="${berth.number}" name="berthNumber"
                                           style="display: none"/>
                                    <input type="text" name="shipImo" placeholder="Add Vessel IMO..."/>
                                </label>
                                <input class="button" type="submit" name="submit" value="submit"/>
                            </form>
                        </td>
                    </c:if>

                </tr>
            </c:forEach>
        </table>


    </div>

</div>
</body>
</html>