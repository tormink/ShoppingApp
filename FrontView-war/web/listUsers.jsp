<%@ page import="com.lntormin.javaee.ejb.entities.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lntormin
  Date: 09/10/16
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <meta charset="UTF-8">
</head>
<body>
<div class="container">
    <h1>Users</h1>
    <%
        List<User> users = (List<User>) request.getAttribute("users");
    %>
    <%if (users.size() > 0) { %>
    <table>
        <% for (User u : users) {%>
        <tr>
            <td><%=u.getId()%>
            </td>
            <td><%=u.getName()%>
            </td>
            <td><%=u.getSurname()%>
            </td>
            <td><%=u.getUsername()%>
            </td>
            <td><%=u.getHash()%>
            </td>
        </tr>
        <%}%>
    </table>
    <%}%>
</div>
</body>
</html>
