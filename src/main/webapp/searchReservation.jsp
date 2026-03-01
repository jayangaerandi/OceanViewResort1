<%@ page import="com.oceanviewresort1.model.User" %>
<html>
<head>
<title>Search Reservation</title>
</head>

<body>

<h2>Search Reservation</h2>

<form action="invoice" method="get">

<label>Reservation Number:</label><br>
<input type="text" name="reservationNumber" required>

<br><br>

<input type="submit" value="Generate Invoice">

</form>

<br>

<% if(request.getAttribute("errorMessage") != null){ %>
<p style="color:red;">
<%= request.getAttribute("errorMessage") %>
</p>
<% } %>

<br>

<%
User user = (User) session.getAttribute("loggedUser");

String dashboard = "login.jsp";

if(user != null){

    if("Admin".equalsIgnoreCase(user.getRole())){
        dashboard = "adminDashboard.jsp";
    }
    else if("Manager".equalsIgnoreCase(user.getRole())){
        dashboard = "managerDashboard.jsp";
    }
    else if("Receptionist".equalsIgnoreCase(user.getRole())){
        dashboard = "receptionDashboard.jsp";
    }
}
%>

<br><br>
<a href="<%= dashboard %>">Back to Dashboard</a>
</body>
</html>