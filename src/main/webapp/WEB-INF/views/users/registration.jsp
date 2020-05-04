<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <style type="text/css">
        <%@include file="../bootstrap/css/bootstrap.min.css" %>
        <%@include file="../css/styles.css" %>
    </style>
    <title>Registration</title>
</head>
<body>

<a class="btn btn-block btn-outline-primary" href="/login">Sign in</a>
<h1 class="text-center mt-3">Registration</h1>

<div class="row justify-content-center">
    <form class="w-auto" method="post" action="${pageContext.request.contextPath}/registration">
        <div class="col">
            <div class="form-group">
                <label for="name">Name </label>
                <input class="form-control" id="name" type="text" name="name" value="${savedName}" placeholder="Name" required autofocus>
            </div>
            <div class="form-group">
                <label for="surname">Surname</label>
                <input class="form-control" id="surname" type="text" name="surname" value="${savedSurname}" placeholder="Surname" required>
            </div>
            <div class="form-group">
                <label for="login">Login</label>
                <input class="form-control" id="login" type="text" name="login" value="${savedLogin}" placeholder="Login" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input class="form-control" id="password" type="password" name="password" placeholder="Password" required>
            </div>
            <div class="form-group">
                <label for="rpt-pass">Repeat password</label>
                <input class="form-control" id="rpt-pass" type="password" name="repeatPassword" placeholder="Repeat password" required>
            </div>
            <button type="submit" class="btn btn-block">Sign up</button>
        </div>
    </form>
</div>

<p class="text-center text-danger">${issue}<p/>


</body>
</html>
