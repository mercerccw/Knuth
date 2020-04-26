<%--
  Created by IntelliJ IDEA.
  User: Clayton
  Date: 4/25/2020
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pre-Arrival Forms</title>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
<div style="text-align: center">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <h1>Pre-Arrival Forms</h1>
    <h3>Agent Portal</h3>
    <p>${user.last_name}, ${user.first_name}</p>
    <a href="${pageContext.request.contextPath}/agent">Back to Agent Dashboard</a><br><br><br>
    <a href="${pageContext.request.contextPath}/logout">Logout</a><br><br><br>
</div>
<c:if test="${approvedForms != null}">

    <div style="text-align: center">
        <table class="table">
            <tr>
                <th>IMO Number</th>
                <th>Agent Email</th>
                <th>Arriving From</th>
                <th>ETA</th>
                <th>Berth Number</th>
                <th>Next port</th>
                <th>ETD</th>
                <th>Discharging Cargo Description</th>
                <th>Discharging Cargo Amount</th>
                <th>Loading Cargo Description</th>
                <th>Loading Cargo Amount</th>
                <th>Number of Passengers (Arrival)</th>
                <th>Number of Passenger (Departure)</th>
                <th>Berth Validation</th>
            </tr>
            <c:forEach items="${approvedForms}" var="form">
                <tr>
                    <td>${form.imo_number}</td>
                    <td>${form.agent_email}</td>
                    <td>${form.arriving_from}</td>
                    <td>${form.eta}</td>
                    <td>${form.berth_number}</td>
                    <td>${form.next_port_name}</td>
                    <td>${form.etd}</td>
                    <td>${form.discharging_cargo_description}</td>
                    <td>${form.discharging_cargo_amount}</td>
                    <td>${form.loading_cargo_description}</td>
                    <td>${form.loading_cargo_amount}</td>
                    <td>${form.number_of_passengers_arrival}</td>
                    <td>${form.number_of_passengers_departure}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/allReviewForms" method="post">
                            <label for="validation">
                                <c:if test="${form.form_validation == 0}">
                                    unapproved berth
                                </c:if>
                                <c:if test="${form.form_validation == 1}">
                                    approve berth
                                </c:if>
                                <select name="validate" id="validation" required>
                                    <option value="0">unapproved</option>
                                    <option value="1">approve berth</option>
                                </select>
                            </label>
                            <label>
                                <input type="text" hidden value="${form.id}" style="display: none" name="id"/>
                            </label>
                            <input type="submit" value="submit">
                        </form>
                    </td>
                </tr>
            </c:forEach>

        </table>

    </div>
</c:if>
<c:if test="${approvedForms == null}">
    <h1 style="text-align: center">no new forms to approve</h1>
</c:if>
</body>
</html>
