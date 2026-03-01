<%@ page import="java.util.List" %>
<%@ page import="com.oceanviewresort1.model.User" %>
<%@ page import="com.oceanviewresort1.model.Guest" %>
<%@ page import="com.oceanviewresort1.model.Room" %>

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

<!-- Guest Dropdown -->
<label>Select Guest:</label><br>
<select name="guestId" required>

<%
List<Guest> guests = (List<Guest>) request.getAttribute("guests");

if (guests != null) {
    for (Guest g : guests) {
%>

<option value="<%= g.getGuestId() %>">
    <%= g.getFullName() %>
</option>

<%
    }
}
%>

</select>

<br><br>

<!-- Room Dropdown -->
<label>Select Room:</label><br>
<select name="roomId" required>

<%
List<Room> rooms = (List<Room>) request.getAttribute("rooms");

if (rooms != null) {
    for (Room r : rooms) {
%>

<option value="<%= r.getRoomId() %>">
    Room <%= r.getRoomNumber() %>
</option>

<%
    }
}
%>

</select>

<br><br>

<label>Check-In Date:</label><br>
<input type="date" name="checkInDate" required>

<br><br>

<label>Check-Out Date:</label><br>
<input type="date" name="checkOutDate" required>

<br><br>

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