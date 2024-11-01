<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Админ-панель</title>
    <link rel="stylesheet" href="../assets/style/style.css">
    <style>
        /* Простой стиль для страницы */
        body {
            font-family: Arial, sans-serif;
        }

        .container {
            width: 80%;
            margin: 0 auto;
        }

        h1 {
            text-align: center;
        }

        .section {
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table,
        th,
        td {
            border: 1px solid #ddd;
        }

        th,
        td {
            padding: 8px;
            text-align: left;
        }

        .header__nav ul {
            display: flex;
            gap: 20px;
            justify-content: center;
            list-style: none;
        }

        .admin-pagination {
            display: flex;
            justify-content: center;
        }

        .admin-pagination ul {
            display: flex;
            gap: 15px;
            list-style: none;
        }

        .message {
            display: flex;
            justify-content: center;
        }

        .confirm__block, .cancel__block {
            text-align: center;
        }

        .confirm__block a {
            color: white;
            background: green;
            padding: 5px 10px;
            transition: 0.3s;
        }

        .confirm__block a:hover {
            background: #045c04;
        }

        .cancel__block a {
            color: white;
            background: red;
            padding: 5px 10px;
            transition: 0.3s;
        }

        .cancel__block a:hover {
            background: #bd0202;
        }
    </style>
</head>

<body>
<div class="wrapper">
    <header class="header">
        <div class="header__container">
            <nav class="header__nav">
                <ul>
                    <li><a href="controller?command=to_main">Главная</a></li>
                    <li><a href="controller?command=find_all_users">Пользователи</a></li>
                    <li><a href="controller?command=to_orders">Заказы</a></li>
                    <li><a href="controller?command=log_out">Выйти</a></li>
                </ul>
            </nav>
        </div>
    </header>
