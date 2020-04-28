<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add products</title>
</head>
<body>
    <h1>Add product:</h1>
    <form method="post" action="${pageContext.request.contextPath}/products/add">
        <p>Product name: </p>
        <input type="text" name="name">
        <p>Price: </p>
        <input type="text" name="price">
        <button type="submit">Add</button>
    </form>
    <label>${message}</label>
</body>
</html>
