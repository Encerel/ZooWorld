<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../assets/style/swiper-bundle.min.css">
    <link rel="stylesheet" href="../assets/style/reset.css">
    <link rel="stylesheet" href="../assets/style/style.css">
    <link rel="stylesheet" href="../assets/style/burger.css">
    <link rel="stylesheet" href="../assets/style/accordion.css">
    <link rel="stylesheet" href="../assets/style/quantity.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
            rel="stylesheet">
    <title>ZooWorld - зоомагазин</title>
</head>

<body>
<div class="wrapper">
    <header class="header">
        <div class="header__container">
            <div class="header__row-top">
                <a href="controller?command=to_main" class="header__logo">
                        <span class="header__logo-blue">
                            Zoo
                        </span>
                    <span class="header__logo-orange">
                            World
                        </span>
                </a>
                <div class="header__right-block">
                    <div class="header__search header__block">
                        <form action="controller">
                            <input type="text" name="product_name" placeholder="Поиск товаров">
                            <button type="submit">
                                <img class="icon" src="../images/search.svg" alt="Submit">
                            </button>
                            <input type="hidden" name="command" value="find_product_by_name">
                        </form>
                    </div>

                    <c:if test="${user == null}">
                        <a href="controller?command=to_sign_in" class="header__profile header__block">
                            <img class="icon" src="../images/profile.svg" alt="Profile">
                            <span>Account</span>
                        </a>
                    </c:if>
                    <c:if test="${user != null}">
                        <a href="controller?command=to_personal_page" class="header__profile header__block">
                            <img class="icon" src="../images/profile.svg" alt="Profile">
                            <span>Профиль</span>
                        </a>
                    </c:if>
                    <a href="controller?command=to_cart_page" class="header__cart header__block cart">
                        <div class="cart-image__wrapper">
                            <img class="icon" src="../images/basket.svg" alt="cart">

                            <c:if test="${empty cart_items_count}">
                                <span class="cart-items-count">0</span>
                            </c:if>
                            <c:if test="${not empty cart_items_count}">
                                 <span class="cart-items-count">
                                         ${cart_items_count}
                                 </span>
                            </c:if>

                        </div>

                            <c:if test="${empty cart_total_price}">
                                <span>0 P</span>
                            </c:if>
                            <c:if test="${not empty cart_total_price}">
                                <span>${cart_total_price} P</span>
                            </c:if>

                    </a>
                </div>
            </div>
            <div class="header__row-bottom">
                <nav class="header__nav">
                    <ul class="header__nav-list">
                        <li class="header__nav-item">
                            <a href="controller?command=to_main" class="header__nav-link">Главная</a>
                        </li>
                        <li class="header__nav-item">
                            <a href="controller?command=to_products_page" class="header__nav-link">Все товары</a>
                        </li>
                        <li class="header__nav-item">
                            <a href="controller?command=to_order_page" class="header__nav-link">Заказы</a>
                        </li>
                    </ul>
                </nav>
                <div class="nav__burger">
                    <div class="icon-circle"></div>
                    <div class="icon"></div>
                </div>
            </div>
    </header>