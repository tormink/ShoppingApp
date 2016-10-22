<%@ page import="com.lntormin.javaee.ejb.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
    <%@ include file="inc/nav.jsp" %>
<div class="container">
    <h1>User</h1>
    <div class="row">
    <% User user = (User) request.getAttribute("user");
        if (user != null) {%>
    <table class="table table-hover">
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Username</th>
        <th>Hash</th>
        <tr>
            <td><%=user.getId()%>
            </td>
            <td><%=user.getName()%>
            </td>
            <td><%=user.getSurname()%>
            </td>
            <td><%=user.getUsername()%>
            </td>
            <td><%=user.getHash()%>
            </td>

        </tr>
    </table>
    <%}%>
    </div>
</div>
</body>
</html>
