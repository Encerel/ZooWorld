<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<c:if test="${not empty cart_items}">
    <c:forEach var="item" items="${cart_items}">
        <div>
                ${item.product}<br>
                ${item.quantity}<br>
                ${item.totalPrice}
        </div>
    </c:forEach>
</c:if>

<c:if test="${empty cart_items}">
  ${message}
</c:if>

</body>
</html>
