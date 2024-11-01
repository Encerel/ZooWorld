<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:import url="../components/header.jsp"/>


<main class="main">

    <div class="main__container">
        <h1>Корзина</h1>
        <c:if test="${not empty cart_items}">
        <div class="cart">
            <div class="cart__wrapper">
                    <div class="cart__items">

                        <c:forEach var="item" items="${cart_items}">
                            <div class="cart__item item-cart">
                            <div class="item-cart__image-wrapper">
                                <img src="../images/products/${item.product.image}" alt="Image" class="item-cart__image">
                            </div>
                            <div class="item-cart__info">
                                <span class="item-cart__title">
                                    ${item.product.name}
                                </span>
                                <form action="controller">
                                <div class="item-cart__remove-container">
                                    <button class="item-cart__remove" type="submit">
                                        <img src="../images/remove.svg" alt="Remove">
                                        Удалить
                                    </button>
                                </div>
                                    <input type="hidden" name="id" value="${item.product.id}">
                                    <input type="hidden" name="command" value="delete_cart_item">
                                </form>
                            </div>
                            <div class="item-cart__quantity">
                                <span>Кол-во: ${item.quantity}</span>
                            </div>
                            <div class="item-cart__price">
                                <span class="item__price">${item.quantity * item.product.price} Р</span>
                            </div>
                        </div>
                        </c:forEach>



                    </div>
                    <div class="cart__confirm-wrapper">
                        <form class="cart__confirm" action="controller">
                            <button class="add-to-cart" type="submit">
                                Оформить заказ
                            </button>
                            <div class="cart__info">
                                <span>${cart_items_count} товара</span>
                                <span class="cart__info-price">${cart_total_price} Р</span>
                            </div>
                            <div class="cart__total">
                                <span>Итого</span>
                                <span class="cart__total-price">${cart_total_price} Р</span>
                            </div>
                            <input type="hidden" name="command" value="make_order">
                        </form>
                    </div>
            </div>
        </div>
        </c:if>
        <c:if test="${empty cart_items}" >
            <p>Здесь ничего нет...</p>
        </c:if>
    </div>

</main>
<c:import url="../components/footer.jsp"/>


