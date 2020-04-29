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
    <title>All users page</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<div class="container mt-5 pt-5">
<h1 class="text-center mt-3">Users in storage</h1>
<table border="1" class="table text-center table-hover">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Login</th>
        <th scope="col">Name</th>
        <th scope="col">Surname</th>
        <th scope="col">Delete</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                    <c:out value="${user.id}"/>
                </td>
                <td>
                    <c:out value="${user.login}"/>
                </td>
                <td>
                    <c:out value = "${user.name}"/>
                </td>
                <td>
                    <c:out value = "${user.surname}"/>
                </td>
                <td class="td-hover" onclick="window.location.href='/deleteUser?user_id=${user.id}'">
                   X
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>
