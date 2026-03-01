<%@ page import="com.oceanviewresort1.model.User" %>
<%@ page session="true" %>

<%
User user = (User) session.getAttribute("loggedUser");

if (user == null || !user.getRole().equalsIgnoreCase("Admin")) {
    response.sendRedirect("login.jsp");
    return;
}
%>

<html>
<head>
<title>Admin Dashboard</title>
</head>

<body>

<h2>Admin Dashboard</h2>

<p>Welcome <%= user.getUsername() %></p>

<ul>
<li><a href="addGuest.jsp">Register Guest</a></li>
<li><a href="reservationForm">Create Reservation</a></li>
<li><a href="reservations">View Reservations</a></li>
<li><a href="searchReservation.jsp">Generate Invoice</a></li>
</ul>

<br>

<a href="logout">Logout</a>

</body>
</html>