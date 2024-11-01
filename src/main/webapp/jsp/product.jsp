<%@ page import="by.mitso.zooworld.entity.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="../components/header.jsp"/>

<main class="main">
    <div class="main__container">
        <div class="product__wrapper">
            <div class="product-image__wrapper">
                <img src="../images/products/item.png" alt="Image">
            </div>
            <div class="product-info__wrapper">
                <h2 class="product-info__title">
                    ${product.name}
                </h2>
                <div class="product-info__details">
                    <div class="product-info__description">
                        <p class="product-info__code">
                            <span class="product-code">Код товара: ${product.id}</span>
                        </p>
                        <p>
                        <div class="product-info__info-grid">
                            <span>Тип</span>
                            <span>${product.type}</span>
                            <span>Категория</span>
                            <span>${product.category}</span>
                            <span>Наличие</span>
                            <span>${product.availability}</span>
                        </div>
                        </p>
                        <label>Количество:
                            <input form="add-to-cart__form" type="number" min="1" name="quantity" value="1">
                        </label>
                        <h3>Описание:</h3>
                        <p class="product-description">
                           ${product.description}
                        </p>

                    </div>
                    <div class="product-info__order">
                        <div class="cart__confirm-wrapper">
                            <div class="cart__confirm">
                                <form id="add-to-cart__form" action="controller">
                                    <button class="add-to-cart" type="submit">
                                        В корзину
                                    </button>
                                    <input type="hidden" name="command" value="add_product_to_cart">
                                    <input type="hidden" name="id" value="${product.id}">
                                </form>
                                <div class="cart__total">
                                    <span>Цена</span>
                                    <span class="cart__total-price">${product.price} Р</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <section class="section">
            <h1>Похожие товары</h1>
            <div class="item-slider swiper">
                <div class="item-slider__wraper swiper-wrapper">


                    <c:forEach var="product" items="${products}">
                    <div class="item-slider__slide swiper-slide item-slide">
                        <div class="item-slider__slide-wrapper" style="height: 390px;">
                            <div class="item-slide__image-wrapper">
                                <img src="../images/products/${product.image}" alt="Image">
                            </div>
                            <a class="slide-item__title" href="controller?command=to_product_page&id=${product.id}">
                                ${product.name}
                            </a>
                            <p class="item-slide__price-container">
                                <span class="item-slide__price">${product.price}</span>
                                Р
                            </p>
                            <a class="add-to-cart" href=controller?command=add_product_to_cart&id=${product.id}&quantity=1>В корзину</a>
                        </div>
                    </div>
                    </c:forEach>
                </div>

            </div>

            <c:if test="${products.size() gt 5}">
            <div class="item-navigation item-next">
                <img src="../images/arrow.svg" alt="Next">
            </div>
            <div class="item-navigation item-back">
                <img src="../images/arrow.svg" alt="Next">
            </div>
            </c:if>
        </section>
    </div>
</main>




<c:import url="../components/footer.jsp"/>