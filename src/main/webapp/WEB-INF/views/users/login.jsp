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
<%--    <img class="mb-4" src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">--%>
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input name="login" type="text" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
<%--    <div class="checkbox mb-3">--%>
<%--        <label>--%>
<%--            <input type="checkbox" value="remember-me"> Remember me--%>
<%--        </label>--%>
<%--    </div>--%>
    <button class="btn btn-lg btn-block btn-primary" type="submit">Sign in</button>
    <h5 class="mt-3 text-danger">${errorMsg}</h5>
</form>
</body>
</html>
