<%@ page import="com.lntormin.javaee.ejb.entities.User" %><%--
  Created by IntelliJ IDEA.
  User: lntormin
  Date: 09/10/16
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <meta charset="UTF-8">
</head>
<body>
<div class="container">
    <h1>User</h1>
    <% User user = (User) request.getAttribute("user");

        if (user != null) {%>
    <table>
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
</body>
</html>
