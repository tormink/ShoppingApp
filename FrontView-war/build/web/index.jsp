<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css">
</head>

<body>
    <%@ include file="inc/nav.jsp" %>
    <main class="container">
        <h1>User manager interface</h1>
        <p class="text-right">Session ID is <%=sessionId%></p>
        <a class="btn btn-default" href="FrontControllerServlet?controller=List">List all users</a>
        <div class="row">
            <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                <h2>Search user by name</h2>
                <!--Pesquisar usuários  -->
                <form class="form-inline" action="FrontControllerServlet?controller=Search" method="post">
                    <fieldset class="form-group">
                        <label for="name">Username</label>
                        <input class="form-control" name="name" id="name" placeholder="Type in the name...">
                    </fieldset>
                    <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                </form>
            </div>
            <div class="col-md-6">
                <h2>Search user by Id</h2>
                <!--Pesquisar usuários por ID  -->
                <form class="form-inline" action="FrontControllerServlet?controller=SearchId" method="post">
                    <fieldset class="form-group">
                        <label for="id">User ID</label>
                        <input class="form-control" type="text" id="id" name="id" placeholder="Type in the ID...">
                    </fieldset>
                    <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                </form>
            </div>
        </div>
        <div class='row '>
            <div class="col-md-6">
                <h2>Create a new user</h2>
                <!-- Cria usuários-->
                <form action="FrontControllerServlet?controller=New" method="post">
                    <fieldset class="form-group">
                        <label for="nname">Name</label>
                        <input class="form-control" type="text" id="nname" name="name">
                    </fieldset>
                    <fieldset class="form-group">
                        <label for="nsurname">Surname</label>
                        <input class="form-control" type="text" id="nsurname" name="surname">
                    </fieldset>
                    <fieldset class="form-group">
                        <label for="nlogin">Login</label>
                        <input class="form-control" type="text" id="nlogin" name="username">
                    </fieldset>
                    <fieldset class="form-group">
                        <label for="npassword">Password</label>
                        <input class="form-control" type="password" name="password" id="npassword">
                    </fieldset>
                    <button class="btn btn-default" type="submit">Add <i class="glyphicon glyphicon-plus"></i></button>
                </form>
            </div>
            <div class="col-md-6">
                <h2>Update an existing user</h2>
                <!-- Atualizar usuários-->
                <form action="FrontControllerServlet?controller=Update" method="post">
                    <fieldset class="form-group">
                        <label for="uid">User Id</label>
                        <input class="form-control" type="text" id="uid" name="id">
                    </fieldset>
                    <fieldset class="form-group">
                        <label for="uname">Name</label>
                        <input class="form-control" type="text" id="uname" name="name">
                    </fieldset>
                    <fieldset class="form-group">
                        <label for="surname">Surname</label>
                        <input class="form-control" type="text" id="surname" name="surname">
                    </fieldset>
                    <fieldset class="form-group">
                        <label for="login">Login</label>
                        <input class="form-control" type="text" id="login" name="login">
                    </fieldset>
                    <fieldset class="form-group">
                        <label for="password">Password</label>
                        <input class="form-control" type="password" name="password" id="password">
                    </fieldset>
                    <button class="btn btn-default" type="submit">Update <i class="glyphicon glyphicon-pencil"></i></button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <h2>Remove an existing user</h2>
                <!-- Remover usuários  -->
                <form action="FrontControllerServlet?controller=Remove" method="post">
                    <fieldset class="form-group">
                        <label for="rid">User ID</label>
                        <input type="text" id="rid" name="id">
                    </fieldset>
                    <button class="btn btn-danger" type="submit">Remove <i class="glyphicon glyphicon-remove"></i></button>
                </form>
            </div>
        </div>


    </main>
</body>

</html>
