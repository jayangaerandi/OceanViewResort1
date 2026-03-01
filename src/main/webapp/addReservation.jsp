<%@ page import="com.oceanviewresort1.model.User" %>
<%@ page session="true" %>

<%
    User user = (User) session.getAttribute("loggedUser");

    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Create Reservation</title>
</head>
<body>

<h2>Create Reservation</h2>

<form action="addReservation" method="post">

    <label>Guest ID:</label><br>
    <input type="number" name="guestId" required><br><br>

    <label>Room ID:</label><br>
    <input type="number" name="roomId" required><br><br>

    <label>Check-In Date:</label><br>
    <input type="date" name="checkInDate" required><br><br>

    <label>Check-Out Date:</label><br>
    <input type="date" name="checkOutDate" required><br><br>

    <input type="submit" value="Create Reservation">

</form>

<br>

<% if(request.getAttribute("successMessage") != null){ %>
    <p style="color:green;">
        <%= request.getAttribute("successMessage") %>
    </p>
<% } %>

<% if(request.getAttribute("errorMessage") != null){ %>
    <p style="color:red;">
        <%= request.getAttribute("errorMessage") %>
    </p>
<% } %>

<br>
<a href="dashboard.jsp">Back to Dashboard</a>

</body>
</html>