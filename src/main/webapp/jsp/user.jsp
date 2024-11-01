<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:import url="../components/header.jsp"/>


<main class="main">
    <div class="main__container">
        <div class="user__wrapper">
            <h1>Привет, ${user.firstName} ${user.lastName}</h1>
            <form class="user__form" action="controller">
                <button class="edit" onclick="toggleEdit(event)">
                    <img src="../images/edit.png" alt="Edit">
                </button>
                <fieldset class="fieldset" disabled>
                    <label>
                        Имя:<br>
                        <input type="text" name="first_name" value="${user.firstName}">
                    </label>
                    <label>
                        Фамилия:<br>
                        <input type="text" name="last_name" value="${user.lastName}">
                    </label>
                    <label>
                        Пароль:<br>
                        <input type="password" name="password" value="${user.password}">
                    </label>
                    <button type="submit" class="add-to-cart">Сохранить</button>
                </fieldset>
                <input type="hidden" name="command" value="change_personal_info">
            </form>
            <a class="user__logout" href="controller?command=log_out">
                Выход
                <img src="../images/exit.png" alt="Exit">
            </a>
        </div>
    </div>
</main>


<c:import url="../components/footer.jsp"/>
