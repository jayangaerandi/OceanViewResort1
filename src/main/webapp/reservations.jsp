<%@ page import="java.util.List" %>
<%@ page import="com.oceanviewresort1.model.Reservation" %>

<html>
<head>
<title>All Reservations</title>
</head>

<body>

<h2>Reservation List</h2>

<table border="1" cellpadding="10">

<tr>
    <th>ID</th>
    <th>Reservation Number</th>
    <th>Total Amount</th>
    <th>Status</th>
</tr>

<%
    List<Reservation> reservations =
        (List<Reservation>) request.getAttribute("reservations");

    if(reservations != null){
        for(Reservation r : reservations){
%>

<tr>
    <td><%= r.getReservationId() %></td>
    <td><%= r.getReservationNumber() %></td>
    <td><%= r.getTotalAmount() %></td>
    <td><%= r.getStatus() %></td>
</tr>

<%
        }
    }
%>

</table>

<br>
<a href="dashboard.jsp">Back to Dashboard</a>

</body>
</html>