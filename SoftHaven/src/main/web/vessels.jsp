<%--
  Created by IntelliJ IDEA.
  User: Clayton
  Date: 4/23/2020
  Time: 11:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="scripts/vesselFilter.js"></script>
<html>
<head>
    <title>Vessels</title>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
</head>
<body>
<div class="text-center">
    <div class="text-center">
        <a href="${pageContext.request.contextPath}/">Home</a>
        <h1>Vessel Dashboard</h1>
        <h3>${user.last_name}, ${user.first_name}</h3>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
    <br><br><br>

    <div>
        <form action="vessels" method="get">
            <label style="display: none" for="currentPage"><input value="1" type="number" name="currentPage" id="currentPage" required></label>
            <label for="vesselsPerPage"><input type="number" name="vesselsPerPage" id="vesselsPerPage" required max="500" min="100"></label>
            <input type="submit" name="submit" value="submit"/>
        </form>
        <p>Current Page: ${currentPage}</p>
        <p>Number of Pages: ${numOfPages}</p>
        <p>Vessels Per Page: ${vesselsPerPage}</p>
        <p>Total Number of Vessels: ${numberOfVessels}</p>
    </div>
    <%--    <div class="text-center" id="vessels_data">--%>
    <%--        <input type="text" id="imo" onkeyup="filterVessels()" placeholder="Search for IMO..">--%>
    <%--        <input type="text" id="flag" onkeyup="filterVessels()" placeholder="Search for Flag..">--%>
    <%--        <input type="text" id="name" onkeyup="filterVessels()" placeholder="Search for Name..">--%>
    <%--        <input type="text" id="number" onkeyup="filterVessels()" placeholder="Search for Build Year..">--%>
    <%--        <input type="text" id="call_sign" onkeyup="filterVessels()" placeholder="Search for Call Sign..">--%>
    <%--        <input type="text" id="length" onkeyup="filterVessels()" placeholder="Search for Length..">--%>
    <%--        <input type="text" id="breadth" onkeyup="filterVessels()" placeholder="Search for Breadth..">--%>
    <%--        <input type="text" id="tonnage" onkeyup="filterVessels()" placeholder="Search for Tonnage..">--%>
    <%--        <input type="text" id="mmsi" onkeyup="filterVessels()" placeholder="Search for MMSI..">--%>
    <%--        <input type="text" id="type" onkeyup="filterVessels()" placeholder="Search for Type..">--%>
    <%--        <input type="text" id="owner_code" onkeyup="filterVessels()" placeholder="Search for Owner Code..">--%>
    <%--        <input type="text" id="status" onkeyup="filterVessels()" placeholder="Search for Status..">--%>
    <%--        <br><br><br><br><br>--%>

    <table class="table" id="vessels">
        <tr>
            <th scope="col">IMO</th>
            <th scope="col">Flag</th>
            <th scope="col">Name</th>
            <th scope="col">Built Year</th>
            <th scope="col">Call Sign</th>
            <th scope="col">Length</th>
            <th scope="col">Breadth</th>
            <th scope="col">Tonnage</th>
            <th scope="col">MMSI</th>
            <th scope="col">Type</th>
            <th scope="col">Owner Code</th>
            <th scope="col">Status</th>
        </tr>
        <c:forEach items="${vessels}" var="vessel">
            <tr class="data-row">
                <td scope="row">${vessel.imo}</td>
                <td scope="row">${vessel.flag}</td>
                <td scope="row">${vessel.name}</td>
                <td scope="row">${vessel.built}</td>
                <td scope="row">${vessel.callSign}</td>
                <td scope="row">${vessel.length}</td>
                <td scope="row">${vessel.breadth}</td>
                <td scope="row">${vessel.tonnage}</td>
                <td scope="row">${vessel.mmsi}</td>
                <td scope="row">${vessel.type}</td>
                <td scope="row">${vessel.ownerCode}</td>
                <td scope="row">${vessel.status}</td>
                    <%--                        <form action="${pageContext.request.contextPath}/agent/vessels/updateStatus" method="post">--%>
                    <%--                            <label>--%>

                    <%--                                <select name="status" id="">--%>
                    <%--                                    <option value="expected"></option>--%>
                    <%--                                    <option value="waiting"></option>--%>
                    <%--                                    <option value="departed"></option>--%>
                    <%--                                </select>--%>
                    <%--                            </label>--%>
                    <%--                            <input class="button" type="submit" name="submit" value="submit"/>--%>
                    <%--                        </form>--%>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="Navigation for countries">
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/vessels?vesselsPerPage=${vesselsPerPage}&currentPage=${currentPage-1}">Previous</a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${numOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="${pageContext.request.contextPath}/vessels?vesselsPerPage=${vesselsPerPage}&currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt numOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/vessels?vesselsPerPage=${vesselsPerPage}&currentPage=${currentPage+1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>

</div>

<%--</div>--%>
</body>
</html>
