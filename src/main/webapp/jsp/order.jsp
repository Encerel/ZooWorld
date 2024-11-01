<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="../components/header.jsp"/>
<main class="main">
    <div class="main__container">
        <h1>Заказы</h1>
<c:if test="${not empty orders}">
        <table>
            <thead>
            <tr>
                <th>ID заказа</th>
                <th>Дата заказа</th>
                <th>Статус заказа</th>
                <th>Стоимость заказа</th>
                <th>Отменить</th>
            </tr>
            </thead>
            <tbody>
    <c:forEach var="order" items="${orders}">
            <tr>
                <td><a href="controller?command=to_order_details&id=${order.id}">${order.id}</a></td>
                <td><fmt:formatDate type = "date" value = "${order.orderAt}" /></td>
                <c:if test="${order.completedAt = null}">
                    -
                </c:if>
                <c:if test="${order.completedAt != null}">
                    ${order.completedAt}
                </c:if>
                <td>${order.status}</td>
                <td>${order.totalPrice} руб.</td>
                <td>
                    <c:if test="${order.status == 'PENDING'}">
                    <form action="controller" method="post">
                        <input type="submit" value="Отменить заказ">
                        <input type="hidden" name="id" value="${order.id}">
                        <input type="hidden" name="command" value="cancel_order">
                    </form>
                    </c:if>
                </td>
            </tr>

    </c:forEach>
            </tbody>
        </table>
</c:if>

    <c:if test="${empty orders}">
        ${message}
    </c:if>
<%--        <div>--%>
<%--            <a href="controller?command=to_order_details&id=${order.id}">${order.id}</a><br>--%>
<%--                ${order.orderAt}--%>
<%--            <c:if test="${order.completedAt = null}">--%>
<%--                ---%>
<%--            </c:if>--%>
<%--            <c:if test="${order.completedAt != null}">--%>
<%--                ${order.completedAt}--%>
<%--            </c:if>--%>
<%--                ${order.status}<br>--%>
<%--                ${order.totalPrice}--%>
<%--        </div>--%>

<%--        <c:if test="${order.status == 'PENDING'}">--%>
<%--            <form action="controller">--%>
<%--                <input type="submit" value="Отменить заказ">--%>
<%--                <input type="hidden" name="order_id" value="${order.id}">--%>
<%--                <input type="hidden" name="command" value="cancel_order">--%>
<%--            </form>--%>
<%--        </c:if>--%>



<%--</c:if>--%>

<%--<c:if test="${empty orders}">--%>
<%--    ${message}--%>
<%--</c:if>--%>

    </div>
</main>
<c:import url="../components/footer.jsp"/>