<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.softhaven.dao.UserDAO" %>
<%@ page import="com.softhaven.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ship Master</title>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel='stylesheet' href='./styles/basic_reset.css'>
    <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
<div style="text-align: center">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <h1>Ship Master</h1>
    <h3>${user.last_name}, ${user.first_name}</h3>
    <h4>Your job title: ${user.position}</h4>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
    <br><br><br>
    <h1>Pre-Arrival Form</h1>
    <div>
        <p>${imo_message}</p>
        <p>${date_message}</p>
        <p>${submission_message}</p>
    </div>
</div>
<div class="content-container">
    <div id="white" class="content-container">
        <form action="arrival" method="post">
            <label for="imo_number">Ship IMO Number</label>
            <input name="imo_number" size="7" id="imo_number" required/>
            <br><br>
            <label for="agent_email">Agent Email</label>
            <select name="agent_email" id="agent_email" required>
                <c:forEach items="${emails}" var="email">
                    <option value="${email}">${email}</option>
                </c:forEach>
            </select>
            <br><br>
            <label for="arriving_from">Arriving From</label>
            <input name="arriving_from" size="30" id="arriving_from" required/>
            <br><br>
            <label for="eta">ETA</label>
            <input name="eta" type="datetime-local" id="eta" required/>
            <br><br>
            <label for="berth_number">Berth Number</label>
            <select name="berth_number" id="berth_number" required>
                <c:forEach items="${berths}" var="berth">
                    <option value="${berth}">${berth}</option>
                </c:forEach>
            </select>
            <input type="text" hidden value="${berths}" name="berths">
            <input type="text" hidden value="${emails}" name="emails">
            <br><br>
            <label>Next Port <code>Fetched based on the Berth Number</code></label>
            <br><br>
            <label for="etd">ETD</label>
            <input name="etd" type="datetime-local" id="etd" required/>
            <br><br>
            <label for="discharging_cargo_description">Discharging Cargo Description</label>
            <code>If you need more than one berth, let us know now</code>
            <input name="discharging_cargo_description" size="100" id="discharging_cargo_description" required/>
            <br><br>
            <label for="discharging_cargo_amount">Discharging Cargo Amount</label>
            <input name="discharging_cargo_amount" id="discharging_cargo_amount" required/>
            <br><br>
            <label for="loading_cargo_description">Loading Cargo Description</label>
            <input name="loading_cargo_description" size="100" id="loading_cargo_description" required/>
            <br><br>
            <label for="loading_cargo_amount">Loading Cargo Amount</label>
            <input name="loading_cargo_amount" id="loading_cargo_amount" required/>
            <br><br>
            <label for="number_of_passengers_arrival">Number of Passengers Arrival</label>
            <input name="number_of_passengers_arrival" id="number_of_passengers_arrival" required/>
            <br><br>
            <label for="number_of_passengers_departure">Number of Passengers Departure</label>
            <input name="number_of_passengers_departure" id="number_of_passengers_departure" required/>
            <br><br>
<%--            <label for="crew_documents_customs">crew_documents_customs</label>--%>
<%--            <input name="crew_documents_customs" size="30" id="crew_documents_customs" required/>--%>
<%--            <br><br>--%>
<%--            <label for="form_validation">form_validation</label>--%>
<%--            <input name="form_validation" size="30" id="form_validation" required/>--%>
<%--            <br><br>--%>
<%--            <select name="validation" id="position" required>--%>
<%--                <option value="master">Ship Master</option>--%>
<%--                <option value="agent">Ship Agent</option>--%>
<%--                <option value="customs">Customs Officer</option>--%>
<%--            </select>--%>
            <br><br>
            <button type="submit">Submit for Review</button>
        </form>

    </div>
</div>
</body>
</html>





