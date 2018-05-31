<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/login.css">
    <title>Login</title>
</head>
<body>
    <form action="login" class="form-signin" method="POST">
        <div class="text-center mb-4">
            <h1 class="h3 mb-3 font-weight-normal">CPMS Login</h1>
        </div>

        <c:if test="${requestScope.error}">
            <code>Authentication error</code>
        </c:if>

        <div class="form-label-group">
            <input type="text" name="login" id="inputLogin" class="form-control" placeholder="Login" required="" autofocus="">
            <label for="inputLogin">Login</label>
        </div>

        <div class="form-label-group">
            <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required="">
            <label for="inputPassword">Password</label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/login.js"></script>
</html>
