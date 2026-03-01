<%@ page import="com.oceanviewresort1.model.User" %>

<%
User user = (User) session.getAttribute("loggedUser");

if (user == null || !user.getRole().equalsIgnoreCase("Receptionist")) {
    response.sendRedirect("login.jsp");
    return;
}
%>

<html>
<head>
<title>Receptionist Dashboard</title>
</head>

<body>

<h2>Receptionist Dashboard</h2>

<p>Welcome <%= user.getUsername() %></p>

<ul>
<li><a href="reservationForm">Create Reservation</a></li>
<li><a href="registerGuest.jsp">Register Guest</a></li>
<li><a href="searchReservation.jsp">Generate Invoice</a></li>
</ul>

<br>

<a href="logout">Logout</a>

</body>
</html>