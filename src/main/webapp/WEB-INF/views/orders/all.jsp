<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
    <ul>
        <c:forEach var="order" items="${orders}">
        <li>
            <a href="${pageContext.request.contextPath}/orders/info?order_id=${order.id}">Order №${order.id}</a>
            <form method="get" action="${pageContext.request.contextPath}/orders/delete">
                <input type="hidden" value="${order.id}" name="order_id">
                <button type="submit">X</button>
            </form>
        </li>
        </c:forEach>
    </ul>
</body>
</html>
