<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach var="product" items="${products}">

    <form action="controller">
        <div>
            <p>${product}
                <span>
                       <label>Количество
                            <input type="number" name="quantity">
                        </label>
                    <input type="submit" value="Добавить в корзину">

                    <input type="hidden" name="id" value="${product.id}">
                    <input type="hidden" name="command" value="add_product_to_cart">
                </span>
            </p>
        </div>

    </form>

</c:forEach>

</body>
</html>
