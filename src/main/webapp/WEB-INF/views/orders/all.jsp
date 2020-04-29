<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
    <div class="container mt-5 pt-5">
        <table class="table">
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td><h3><a class="text-info" href="${pageContext.request.contextPath}/orders/info?order_id=${order.id}">Order №${order.id}</a></h3></td>
                    <td><button class="btn btn-danger" onclick="window.location.href='${pageContext.request.contextPath}/orders/delete?order_id=${order.id}'">X</button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
