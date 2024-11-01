<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../components/header.jsp"/>

<main class="main">
    <div class="main__container">
        <h1>Детали заказа</h1>
<c:if test="${not empty order_items}">

    <div class="order-details">
        <table>
            <tr>
                <th>Название товара</th>
                <th>Количество</th>
                <th>Цена</th>
            </tr>
    <c:forEach var="item" items="${order_items}">
            <tr>
                <td><a href="controller?command=to_product_page&id=${item.product.id}">${item.product.name}</a></td>
                <td> ${item.quantity}</td>
                <td>${item.totalPrice} руб.</td>
            </tr>
    </c:forEach>
        </table>
    </div>

</c:if>

<c:if test="${empty order_items}">
    ${message}
</c:if>
    </div>
</main>
<c:import url="../components/footer.jsp"/>