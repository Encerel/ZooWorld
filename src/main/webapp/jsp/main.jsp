<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Main page</h1>

<c:if test="${user == null}">
    <form action="controller">
        <input type="submit" value="Войти">
        <input type="hidden" name="command" value="to_sign_in">
    </form>
    <a href="/controller?command=to_sign_up">Регистрация</a>
</c:if>
<c:if test="${user != null}">
    <form action="controller">
        <input type="submit" value="Профиль">
        <input type="hidden" name="command" value="to_personal_page">
    </form>

    <form action="controller">
        <input type="submit" value="Выйти">
        <input type="hidden" name="command" value="log_out">
    </form>
</c:if>
<br>
<a href="/controller?command=to_products_page">Товары</a>


<c:if test="${user != null}">
    <a href="/controller?command=to_cart_page">Корзина</a>
</c:if>




</body>
</html>
