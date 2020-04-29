<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        <%@include file="../bootstrap/css/bootstrap.min.css" %>
        <%@include file="../css/styles.css" %>
    </style>
    <title>Title</title>
</head>
<body>
<div class="container">
    <jsp:include page="../navbar.jsp" />
    <div class="row mt-5 pt-5">
    <div class="col-md-9 col-lg-8">
        <h1>Add product</h1>
        <form method="post" action="${pageContext.request.contextPath}/products/edit">
            <div class="form-label-group">
                <label for="product-name">Product Name</label>
                <input id="product-name" name="name" type="text" class="form-control" required autofocus>
            </div>

            <div class="form-label-group mt-1 mb-4">
                <label for="price">Price</label>
                <input type="text" id="price" name="price" class="form-control" required>
            </div>
            <div>
                <button class="btn btn-dark" type="submit">Add product</button>
                <label class="text-success">${message}</label>
            </div>
        </form>
    </div>
</div>
<h1 class="text-center mt-3"> All products </h1>
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
            <td class="td-hover" onclick="window.location.href='${pageContext.request.contextPath}/products/delete?product_id=${product.id}'" >
                X
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>

</body>
</html>
