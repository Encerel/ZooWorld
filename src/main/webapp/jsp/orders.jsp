<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../components/admin-header.jsp"/>

<main class="main">
    <div class="main__container">
        <h1>Заказы на подтверждение</h1>
        <c:if test="${not empty orders}">

        <table>
                <tr>
                    <th>ID заказа</th>
                    <th>Пользователь</th>
                    <th>Сумма заказа</th>
                    <th>Подтвердить</th>
                    <th>Отменить</th>
                </tr>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td><a href="controller?command=to_order_details_admin&id=${order.id}&user_id=${order.owner.id}">${order.id}</a></td>
                    <td><a href="controller?command=find_user_by_id&id=${order.owner.id}">${order.owner.firstName} ${order.owner.lastName}</a></td>
                    <td>${order.totalPrice} руб.</td>
                    <td class="confirm__block">
                        <a href="controller?command=confirm_order&id=${order.id}">Подтвердить</a>
                    </td>
                    <td class="cancel__block">
                        <a href="controller?command=cancel_order_admin&id=${order.id}">Отменить</a>
                    </td>
                </tr>
            </c:forEach>
            </table>

        </c:if>
        <c:if test="${not empty orders}">
            ${message}
        </c:if>
    </div>
</main>

<c:import url="../components/footer.jsp"/>