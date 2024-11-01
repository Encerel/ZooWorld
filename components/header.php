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
                    <a href="#" class="header__logo">
                        <span class="header__logo-blue">
                            Zoo
                        </span>
                        <span class="header__logo-orange">
                            World
                        </span>
                    </a>
                    <div class="header__right-block">
                        <div class="header__search header__block">
                            <form action="#">
                                <input type="text" placeholder="Поиск товаров">
                                <button type="submit">
                                    <img class="icon" src="../images/search.svg" alt="Submit">
                                </button>
                            </form>
                        </div>
                        <a href="#" class="header__profile header__block">
                            <img class="icon" src="../images/profile.svg" alt="Profile">
                            <span>Account</span>
                        </a>
                        <a href="#" class="header__cart header__block cart">
                            <div class="cart-image__wrapper">
                                <img class="icon" src="../images/basket.svg" alt="cart">
                                <span class="cart-items-count">
                                    4
                                </span>
                            </div>
                            <span>9999 P</span>
                        </a>
                    </div>
                </div>
                <div class="header__row-bottom">
                    <nav class="header__nav">
                        <ul class="header__nav-list">
                            <li class="header__nav-item">
                                <a href="#" class="header__nav-link">Главная</a>
                            </li>
                            <li class="header__nav-item">
                                <a href="pages/products.php" class="header__nav-link">Все товары</a>
                            </li>
                        </ul>
                    </nav>
                    <div class="nav__burger">
                        <div class="icon-circle"></div>
                        <div class="icon"></div>
                    </div>
                </div>
        </header>