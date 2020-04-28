<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
<%--    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <style type="text/css">
        <%@include file="bootstrap/css/bootstrap.min.css" %>
    </style>
    <title>Registration</title>
</head>
<body>
<h1 class="text-center my-md-3">Hello, please provide your users details</h1>
<form method="post" action="${pageContext.request.contextPath}/registration">
    <div class="row  my-auto justify-content-center">
        <div class="col-md-3 col-md-offset-3">
            <div class="form-group">
                <label for="name">Name </label>
                <input class="form-control" id="name" type="text" name="name" value=${savedName}>
            </div>
            <div class="form-group">
                <label for="surname">Surname</label>
                <input class="form-control" id="surname" type="text" name="surname" value=${savedSurname}>
            </div>
            <div class="form-group">
                <label for="login">Login</label>
                <input class="form-control" id="login" type="text" name="login" value=${savedLogin}>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input class="form-control" id="password" type="password" name="password">
            </div>
            <div class="form-group">
                <label for="rpt-pass">Repeat password</label>
                <input class="form-control" id="rpt-pass" type="password" name="repeatPassword">
            </div>

            <button type="submit" class="btn btn-block">Register</button>
        </div>
    </div>
</form>
    <label>${issue}</label>
</body>
</html>
