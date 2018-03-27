<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Вход</title>
</head>
<body>
<div class="container">
    <form>
        <label for="loginInput">Login</label>
        <input type="text" id="loginInput"/>
        <label for="passwordInput">Password</label>
        <input type="password" id="passwordInput"/>
    </form>
    <button id="loginButton">Submit</button>
</div>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/main.js"></script>
</html>