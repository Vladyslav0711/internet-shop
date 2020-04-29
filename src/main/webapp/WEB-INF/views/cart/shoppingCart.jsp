<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping cart</title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Delete</th>
    </tr>

    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.id}"/>
            </td>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td><a href="${pageContext.request.contextPath}/cart/delete?product_id=${product.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<p>${message}</p>
<form action="${pageContext.request.contextPath}/orders/complete" method="post">
    <button type="submit">Complete order</button>
</form>

</body>
</html>
