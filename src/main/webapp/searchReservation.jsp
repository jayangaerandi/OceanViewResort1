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

<a href="dashboard.jsp">Back to Dashboard</a>

</body>
</html>