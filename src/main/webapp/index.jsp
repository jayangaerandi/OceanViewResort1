<%@ page import="com.oceanviewresort1.model.User" %>
<%@ page session="true" %>

<%
    User user = (User) session.getAttribute("loggedUser");
%>

<html>
<head>
    <title>Ocean View Resort</title>
</head>
<body>

<h1>Welcome to Ocean View Resort Reservation System</h1>

<%
    if (user != null) {
%>
        <p>You are logged in as <b><%= user.getUsername() %></b></p>
        <a href="dashboard.jsp">Go to Dashboard</a><br><br>
        <a href="logout">Logout</a>
<%
    } else {
%>
        <a href="login.jsp">Login to System</a>
<%
    }
%>

</body>
</html>