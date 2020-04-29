<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order info</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />

    <h1>Order №${order.id} info</h1>
    <ul>
        <c:forEach var="product" items="${products}">
        <li>
            <p>Product id: ${product.id}</p>
            <p>Product name: ${product.name}</p>
            <p>Product price: ${product.price}</p>
        </li>
        </c:forEach>
    </ul>
</body>
</html>
