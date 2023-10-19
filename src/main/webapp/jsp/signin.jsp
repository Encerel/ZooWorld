<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!DOCTYPE html>
<html>
<head>
    <title>Форма входа</title>
</head>
<body>

<h2>Вход</h2>

<form action="controller" method="post">
    <label for="username">Email:</label><br>
    <input type="text" id="username" name="email" required><br><br>

    <label for="password">Пароль:</label><br>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="Войти">
    <p>${message}</p>
    <input type="hidden" name="command" value="log_in">
</form>

</body>
</html>

</body>
</html>
