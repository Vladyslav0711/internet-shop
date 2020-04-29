<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        <%@include file="../bootstrap/css/bootstrap.min.css" %>
        <%@include file="../css/styles.css" %>
    </style>
    <title>Shopping cart</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<div class="container mt-5 pt-5">
    <table border="1" class="table text-center table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>
                    <c:out value="${product.id}"/>
                </td>
                <td>
                    <c:out value="${product.name}"/>
                </td>
                <td>
                    <c:out value = "${product.price}"/>
                </td>
                <td class="td-hover" onclick="window.location.href='${pageContext.request.contextPath}/cart/delete?product_id=${product.id}'" >
                    X
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<p>${message}</p>
<form action="${pageContext.request.contextPath}/orders/complete" method="post">
    <button type="submit" class="btn btn-primary">Complete order</button>
</form>
</div>
</body>
</html>
