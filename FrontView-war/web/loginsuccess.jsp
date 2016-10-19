<%--
  Created by IntelliJ IDEA.
  User: lntormin
  Date: 02/10/16
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login success</title>
</head>
<body>
<%
    //allow access only if session exists
    String user = (String) session.getAttribute("user");
    String userName = null;
    String sessionId = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                userName = cookie.getValue();
            }
            if (cookie.getName().equals("JSESSIONID")) {
                sessionId = cookie.getValue();
            }
        }
    }
%>

<div>
    <h3>Hi <%=userName%>, login successful. Your session ID is: <%=sessionId%>
    </h3>
    <div>
        <p>User= <%=user%>
        </p>
    </div>
    <a href="checkout.jsp">Checkout</a>
    <form action="LogoutServlet" method="post">
        <input type="submit" value="Logout">
    </form>
    <p><br><a href="index.html">Go to the main page</a></p>

</div>
</body>
</html>
