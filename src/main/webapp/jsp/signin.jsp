<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:import url="../components/header.jsp"/>

<main class="main">
    <div class="main__container">
        <div class="sign__wrapper">
            <h1>Вход</h1>
            <form class="sign__form" action="controller" method="post">
                <label>
                    Email:<br>
                    <input type="email" name="email" placeholder="Email">
                </label>
                <label>
                    Password:<br>
                    <input type="password" name="password" placeholder="Password">
                </label>
                <button class="add-to-cart" type="submit">Войти</button>
                    <input type="hidden" name="command" value="log_in">
                <a href="controller?command=to_sign_up" class="user__signin">Нет аккаунта?</a>
            </form>
            <p>${message}</p>
        </div>
    </div>
</main>

<c:import url="../components/footer.jsp"/>

