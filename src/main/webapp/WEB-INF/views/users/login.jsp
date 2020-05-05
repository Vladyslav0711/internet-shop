<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        <%@include file="../bootstrap/css/bootstrap.min.css" %>
        <%@include file="../css/signin.css" %>
    </style>
    <title>Login</title>
</head>
<body class="text-center">
<form class="form-signin" method="post" action="${pageContext.request.contextPath}/login">
    <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/registration">Sign up</a>
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="inputEmail" class="sr-only">User name</label>
    <input name="login" type="text" id="inputEmail" class="form-control" placeholder="User name" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
    <button class="btn btn-lg btn-block btn-primary" type="submit">Sign in</button>
    <h5 class="mt-3 text-danger">${errorMsg}</h5>
</form>
</body>
</html>
