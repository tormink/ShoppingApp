<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
    </head>
    <body>
        <%
            //allow access only if session exists
            String sessionUser = (String) session.getAttribute("user");
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
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">
                        <img alt="Brand" src="...">
                    </a>
                </div>
                <div class="navbar-right">
                    <p class="navbar-text">Signed in as <%=sessionUser%></p>
                    <form class="navbar-form" action="LogoutServlet" method="post">
                        <button type="submit" class="btn btn-default navbar-btn">Sign out</button>
                    </form>
                </div>
            </div>
        </nav>
    </body>
</html>
