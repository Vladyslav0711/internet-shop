<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Add product:</h1>
    <form method="post" action="${pageContext.request.contextPath}/products/edit">
        <p>Product name: </p>
        <input type="text" name="name">
        <p>Price: </p>
        <input type="text" name="price">
        <button type="submit">Add</button>
    </form>
    <label>${message}</label>
    <table border="1">
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
                    <c:out value = "${product.price}"/>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/products/delete?product_id=${product.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
