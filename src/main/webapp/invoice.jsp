<%@ page import="com.oceanviewresort1.model.Reservation" %>

<%
Reservation r = (Reservation) request.getAttribute("reservation");
%>

<html>
<head>
<title>Reservation Invoice</title>
</head>

<body>

<h2>Reservation Invoice</h2>

<table border="1" cellpadding="10">

<tr>
<td>Reservation Number</td>
<td><%= r.getReservationNumber() %></td>
</tr>

<tr>
<td>Guest</td>
<td><%= r.getGuest().getFullName() %></td>
</tr>

<tr>
<td>Room</td>
<td><%= r.getRoom().getRoomNumber() %></td>
</tr>

<tr>
<td>Check-In Date</td>
<td><%= r.getCheckIn() %></td>
</tr>

<tr>
<td>Check-Out Date</td>
<td><%= r.getCheckOut() %></td>
</tr>

<tr>
<td>Total Amount</td>
<td>$<%= r.getTotalAmount() %></td>
</tr>

<tr>
<td>Status</td>
<td><%= r.getStatus() %></td>
</tr>

</table>

<br>

<button onclick="window.print()">Print Invoice</button>

<br><br>

<a href="dashboard.jsp">Back to Dashboard</a>

</body>
</html>