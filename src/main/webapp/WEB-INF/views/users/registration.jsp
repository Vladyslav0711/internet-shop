<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Hello, please provide your users details</h1>
<form method="post" action="${pageContext.request.contextPath}/registration">
    Name <input type="text" name="name" value=${savedName}>
    Surname <input type="text" name="surname" value=${savedSurname}>
    Login: <input type="text" name="login" value=${savedLogin}>
    Password <input type="password" name="password">
    Repeat password <input type="password" name="repeatPassword">

    <button type="submit">Register</button>
</form>
    <label>${issue}</label>
</body>
</html>
