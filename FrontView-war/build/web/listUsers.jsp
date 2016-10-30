<%@ page import="com.lntormin.javaee.ejb.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Users</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <%@ include file="inc/nav.jsp" %>
        <div class="container">
            <div class="row">
                <h1>Users</h1>
                <%        List<User> users = (List<User>) request.getAttribute("users");
                %>
                <%if (users.size() > 0) { %>
                <table class="table table-hover table-responsive">
                    <th>ID</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Username</th>
                    <th>Hash</th>
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
        </div>
    </body>
</html>
