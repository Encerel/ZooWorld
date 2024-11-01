<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../components/header.jsp"/>


<main class="main">
    <div class="main__container">
        <div class="sign__wrapper">
            <h1>Регистрация</h1>
            <form class="sign__form" action="controller" method="post">
                <label>
                    Имя:<br>
                    <input type="text" name="first_name" placeholder="Имя">
                </label>
                <label>
                    Фамилия:<br>
                    <input type="text" name="last_name" placeholder="Фамилия">
                </label>
                <label>
                    Email:<br>
                    <input type="email" name="email" placeholder="Email">
                </label>
                <label>
                    Номер телефона:<br>
                    <input type="tel" name="phone" placeholder="Номер телефона">
                </label>
                <label>
                    Пароль:<br>
                    <input type="password" name="password" placeholder="Password">
                </label>
                <label>
                    Подтвердите пароль:<br>
                    <input type="password" name="confirmed_password" placeholder="Подтвердите пароль">
                </label>
                <button class="add-to-cart" type="submit">Войти</button>
                <input type="hidden" name="command" value="sign_up">
            </form>
            ${message}
        </div>
    </div>
</main>





<c:import url="../components/footer.jsp"/>