<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<h1>Sign up</h1>

<!DOCTYPE html>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<h2>Регистрация</h2>
<form action="controller" method="post">
    <label for="firstName">Имя:</label>
    <input type="text" id="firstName" name="first_name" required><br><br>

    <label for="lastName">Фамилия:</label>
    <input type="text" id="lastName" name="last_name" required><br><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br><br>


    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="confirmed_password">Подтвердите пароль:</label>
    <input type="password" id="confirmed_password" name="confirmed_password" required><br><br>

    <label for="phoneNumber">Номер телефона:</label>
    <input type="text" id="phoneNumber" name="phone"><br><br>


    <input type="submit" value="Зарегистрироваться">
    <input type="hidden" name="command" value="sign_up">
</form>
${message}
</body>
</html>

</body>
</html>
