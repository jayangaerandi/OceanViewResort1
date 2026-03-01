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
    <title>Register Guest</title>
</head>
<body>

<h2>Guest Registration</h2>

<form action="addGuest" method="post">

    <label>Full Name:</label><br>
    <input type="text" name="fullName" required><br><br>

    <label>Address:</label><br>
    <input type="text" name="address"><br><br>

    <label>Contact Number:</label><br>
    <input type="text" name="contactNumber" required pattern="[0-9]{10}">
    <small>Enter 10 digit number</small><br><br>

    <label>Email:</label><br>
    <input type="email" name="email"><br><br>

    <input type="submit" value="Register Guest">

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


<br><br>
<%

String dashboard = "login.jsp";

if(user != null){

    if("Admin".equalsIgnoreCase(user.getRole())){
        dashboard = "adminDashboard.jsp";
    }
    else if("Manager".equalsIgnoreCase(user.getRole())){
        dashboard = "managerDashboard.jsp";
    }
    else if("Receptionist".equalsIgnoreCase(user.getRole())){
        dashboard = "receptionDashboard.jsp";
    }
}
%>

<br><br>
<a href="<%= dashboard %>">Back to Dashboard</a>

</body>
</html>