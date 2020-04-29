<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        <%@include file="../bootstrap/css/bootstrap.min.css" %>
        <%@include file="../css/styles.css" %>
    </style>
    <title>All products:</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<div class="container mt-lg-5 pt-5">
    <div class="row">
    <c:forEach var="product" items="${products}">
        <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
                <div class="card-body">
                        <h4 class="card-title">
                            <c:out value="${product.name}"/>
                        </h4>
                        <h5><c:out value="${product.price}"/>$</h5>
                </div>
                <div class="card-footer">
                    <small class="text-muted"><a href="${pageContext.request.contextPath}/cart/add?product_id=${product.id}">Add to cart</a></small>
                </div>
            </div>

        </div>
    </c:forEach>
    </div>
</div>
</table>
</body>
</html>
