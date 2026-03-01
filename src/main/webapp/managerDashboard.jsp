<%@ page import="com.oceanviewresort1.model.User" %>

<%
User user = (User) session.getAttribute("loggedUser");

if (user == null || !user.getRole().equalsIgnoreCase("Manager")) {
    response.sendRedirect("login.jsp");
    return;
}
%>

<html>
<head>
<title>Manager Dashboard</title>
</head>

<body>

<h2>Manager Dashboard</h2>

<p>Welcome <%= user.getUsername() %></p>

<ul>
<li><a href="reservations">View Reservations</a></li>
<li><a href="searchReservation.jsp">Generate Invoice</a></li>
</ul>

<br>

<a href="logout">Logout</a>

</body>
</html>