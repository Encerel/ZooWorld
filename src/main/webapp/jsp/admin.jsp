<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<c:forEach items="${users}" var="user">--%>
<%--    ${user}<br>--%>
<%--</c:forEach>--%>

<%--<form action="controller">--%>
<%--    <button type="submit" name="command" value="find_all_users">Найти всех пользователей</button>--%>
<%--</form>--%>




<c:import url="../components/admin-header.jsp"/>

<main class="main">
    <div class="main__container">
        <h1>Список пользователей</h1>

        <!-- Блок с фильтрами -->
        <div class="filters">
            <form action="controller">
                <label for="nameFilter">Фильтр по фамилии:</label>
                <input type="text" id="nameFilter" name="last_name" value="">
                <br>
                <label for="emailFilter">Фильтр по почте:</label>
                <input type="text" id="emailFilter" name="email" value="">
                <br>
                <label for="phoneFilter">Фильтр по телефону:</label>
                <input type="text" id="phoneFilter" name="phone" value="">
                <br>
                <button>Применить фильтры</button>
                <input type="hidden" name="command" value="find_filtered_users">
            </form>
        </div>

        <table>
            <tr>
                <th>ID</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Email</th>
                <th>Телефон</th>
            </tr>
            <c:forEach items="${users}" var="user">

            <tr>
                <td><a href="controller?command=find_user_by_id&id=${user.id}">${user.id}</a></td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.phoneNumber}</td>
            </tr>
            </c:forEach>
        </table>
    </div>

    <div class="message">
        <span>${message}</span>
    </div>
    <div class="admin-pagination">
    <c:if test="${not empty users}">
        <ul>
            <c:forEach begin="1" end="${number_of_pages}" var="i">
            <li>
                <form action="controller" method="POST">
                    <button type="submit" value="${i}">
                        <c:out value="${i}"/>
                    </button>
                    <input type="hidden" name="users_start_from" value="${i}">
                    <input type="hidden" name="command" value="find_users_pagination">
                </form>
            </li>
            </c:forEach>
        </ul>

    </c:if>
    </div>
</main>

<c:import url="../components/footer.jsp"/>
