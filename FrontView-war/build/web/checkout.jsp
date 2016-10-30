<%--
  Created by IntelliJ IDEA.
  User: lntormin
  Date: 14/10/16
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkout</title>
</head>
<body>
<%
    String userName = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                userName = cookie.getValue();
            }
        }
    }
%>
<div>
    <h3>Hi <%=userName%>, do the checkout.</h3>
    <br>
    <form action="LogoutServlet" method="post">
        <input type="submit" value="Logout">
    </form>
</div>
</body>
</html>
