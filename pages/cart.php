<?php
require_once('../components/header.php');
?>

<main class="main">
    <div class="main__container">
        <h1>Корзина</h1>
        <div class="cart">
            <div class="cart__wrapper">
                <form action="#">
                    <div class="cart__items">
                        <div class="cart__item item-cart">
                            <div class="item-cart__image-wrapper">
                                <img src="../images/item.png" alt="Image" class="item-cart__image">
                            </div>
                            <div class="item-cart__info">
                                <span class="item-cart__title">Сухой корм Royal Canin Mini adult для собак мелких пород
                                    с 10 месяцев до 3 лет / Вес – 12 кг</span>
                                <div class="item-cart__remove-container">
                                    <button class="item-cart__remove">
                                        <img src="../images/remove.svg" alt="Remove">
                                        Удалить
                                    </button>
                                </div>
                            </div>
                            <div class="item-cart__quantity quantity-field">
                                <button class="value-button decrease-button" onclick="decreaseValue(this, event)"
                                    title="Azalt">-</button>
                                <div class="number">0</div>
                                <button class="value-button increase-button" onclick="increaseValue(this, 99, event)"
                                    title="Arrtır">+
                                </button>
                            </div>
                            <div class="item-cart__price">
                                <span class="item__price">9999 Р</span>
                            </div>
                        </div>
                        <div class="cart__item item-cart">
                            <div class="item-cart__image-wrapper">
                                <img src="../images/item.png" alt="Image" class="item-cart__image">
                            </div>
                            <div class="item-cart__info">
                                <span class="item-cart__title">Сухой корм Royal Canin Mini adult для собак мелких пород
                                    с 10 месяцев до 3 лет / Вес – 12 кг</span>
                                <div class="item-cart__remove-container">
                                    <button class="item-cart__remove">
                                        <img src="../images/remove.svg" alt="Remove">
                                        Удалить
                                    </button>
                                </div>
                            </div>
                            <div class="item-cart__quantity quantity-field">
                                <button class="value-button decrease-button" onclick="decreaseValue(this, event)"
                                    title="Azalt">-</button>
                                <div class="number">0</div>
                                <button class="value-button increase-button" onclick="increaseValue(this, 99, event)"
                                    title="Arrtır">+
                                </button>
                            </div>
                            <div class="item-cart__price">
                                <span class="item__price">9999 Р</span>
                            </div>
                        </div>
                        <div class="cart__item item-cart">
                            <div class="item-cart__image-wrapper">
                                <img src="../images/item.png" alt="Image" class="item-cart__image">
                            </div>
                            <div class="item-cart__info">
                                <span class="item-cart__title">Сухой корм Royal Canin Mini adult для собак мелких пород
                                    с 10 месяцев до 3 лет / Вес – 12 кг</span>
                                <div class="item-cart__remove-container">
                                    <button class="item-cart__remove">
                                        <img src="../images/remove.svg" alt="Remove">
                                        Удалить
                                    </button>
                                </div>
                            </div>
                            <div class="item-cart__quantity quantity-field">
                                <button class="value-button decrease-button" onclick="decreaseValue(this, event)"
                                    title="Azalt">-</button>
                                <div class="number">0</div>
                                <button class="value-button increase-button" onclick="increaseValue(this, 99, event)"
                                    title="Arrtır">+
                                </button>
                            </div>
                            <div class="item-cart__price">
                                <span class="item__price">9999 Р</span>
                            </div>
                        </div>
                        <div class="cart__item item-cart">
                            <div class="item-cart__image-wrapper">
                                <img src="../images/item.png" alt="Image" class="item-cart__image">
                            </div>
                            <div class="item-cart__info">
                                <span class="item-cart__title">Сухой корм Royal Canin Mini adult для собак мелких пород
                                    с 10 месяцев до 3 лет / Вес – 12 кг</span>
                                <div class="item-cart__remove-container">
                                    <button class="item-cart__remove">
                                        <img src="../images/remove.svg" alt="Remove">
                                        Удалить
                                    </button>
                                </div>
                            </div>
                            <div class="item-cart__quantity quantity-field">
                                <button class="value-button decrease-button" onclick="decreaseValue(this, event)"
                                    title="Azalt">-</button>
                                <div class="number">0</div>
                                <button class="value-button increase-button" onclick="increaseValue(this, 99, event)"
                                    title="Arrtır">+
                                </button>
                            </div>
                            <div class="item-cart__price">
                                <span class="item__price">9999 Р</span>
                            </div>
                        </div>
                    </div>
                    <div class="cart__confirm-wrapper">
                        <div class="cart__confirm">
                            <button class="add-to-cart" type="submit">
                                Оформить заказ
                            </button>
                            <div class="cart__info">
                                <span>4 товара</span>
                                <span class="cart__info-price">9999 Р</span>
                            </div>
                            <div class="cart__total">
                                <span>Итого</span>
                                <span class="cart__total-price">9999 Р</span>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>

<?php
require_once('../components/footer.php');
?>