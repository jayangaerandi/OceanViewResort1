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
    <title>Dashboard</title>
</head>
<body>

<h2>Welcome, <%= user.getUsername() %></h2>

<p>Your Role: <%= user.getRole() %></p>

<br>
<a href="logout">Logout</a>

</body>
</html>