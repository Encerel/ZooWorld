<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../components/admin-header.jsp"/>

<main class="main">
    <div class="main__container">
        <h1>Информация о клиенте</h1>

        <c:if test="${requestScope.user != null}">
        <div class="customer-info">
            <h2>${requestScope.user.firstName} ${requestScope.user.lastName}</h2>
            <p>Email: ${requestScope.user.email}</p>
            <p>Телефон: ${requestScope.user.phoneNumber}</p>
        </div>

        <div class="order-history">
            <h2>История заказов</h2>
            <c:if test="${not empty orders}">
                <c:forEach var="order" items="${orders}">
                    <table>
                        <tr>
                            <th>Заказ №</th>
                            <th>Дата</th>
                            <th>Сумма заказа</th>
                        </tr>
                        <tr>
                            <td><a href="controller?command=to_order_details_admin&id=${order.id}&user_id=${requestScope.user.id}">${order.id}</a></td>
                            <td>${order.orderAt}</td>
                            <td>${order.totalPrice}</td>
                        </tr>
                    </table>
                </c:forEach>
            </c:if>
            <c:if test="${empty orders}">
                ${message}
            </c:if>
        </div>
        </c:if>

        <c:if test="${requestScope.user == null}">
            ${message}
        </c:if>
    </div>
</main>

<c:import url="../components/footer.jsp"/>