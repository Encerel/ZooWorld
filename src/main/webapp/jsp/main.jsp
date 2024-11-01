<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../components/header.jsp"/>

<main class="main">
    <div class="main__container">
        <section class="section">
            <div class="main__banner-slider banner-slider swiper">
                <div class="banner-slider__wrapper swiper-wrapper">
                    <div class="banner-slider__slide swiper-slide">
                        <div class="banner-slide__slide-wrapper">
                            <div class="bannder-slide__text">
                                <h1 class="banner-slide__heading">
                                    Любимцам всегда рады!
                                </h1>
                                <p class="banner-slide__description">
                                    У нас есть все для ваших пушистых и пернатых друзей!
                                </p>
                            </div>
                            <img class="banner-slide__image" src="../images/cat.png" alt="Image">
                        </div>
                    </div>
                    <div class="banner-slider__slide swiper-slide">
                        <div class="banner-slide__slide-wrapper">
                            <div class="bannder-slide__text">
                                <h1 class="banner-slide__heading">
                                    Скидки на лучшие товары для питомцев!
                                </h1>
                                <p class="banner-slide__description">
                                    Экономьте на покупках для своих питомцев! Специальные скидки уже ждут вас.
                                </p>
                            </div>
                            <img class="banner-slide__image" src="../images/parrot.png" alt="Image">
                        </div>
                    </div>
                    <div class="banner-slider__slide swiper-slide">
                        <div class="banner-slide__slide-wrapper">
                            <div class="bannder-slide__text">
                                <h1 class="banner-slide__heading">
                                    Заботьтесь о своих друзьях с нами
                                </h1>
                                <p class="banner-slide__description">
                                    Доверьте заботу о своих животных профессионалам. Магазин зоотоваров - ваш надежный
                                    партнер.
                                </p>
                            </div>
                            <img class="banner-slide__image" src="../images/dog.webp" alt="Image">
                        </div>
                    </div>
                </div>
                <div class="swiper-pagination banner-pagination"></div>
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>
        </section>


        <section class="section">
            <h1>Хиты продаж</h1>

            <div class="item-slider swiper">
                <div class="item-slider__wraper swiper-wrapper">

                <c:forEach var="product" items="${products}" >
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
                            <a href="controller?command=add_product_to_cart&id=${product.id}&quantity=1" class="add-to-cart">В корзину</a>
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
        <section class="section">
            <h3>Интернет-зоомагазин "ZooWorld"</h3>
            <p>Для владельца красивых и веселых домашних животных нет ничего более приятного, чем порадовать своего
                питомца. Вкусные угощения, полезные витамины, красивые аксессуары – все это становится неотъемлемой
                частью нашей заботы. Зоомагазин в Минске «ZooWorld» поможет вам приобрести качественные товары для
                животных. Все предложения в нашем ассортименте внимательно проверены и выбраны с заботой о ваших
                питомцах.
                В зоомагазине особой популярностью пользуются предложения для кошек и собак. Также мы подобрали
                различные товары для грызунов и птиц. Любителей подводной фауны порадует раздел аквариумистики с
                красивыми и полезными товарами для ваших рыбок.</p>
        </section>
        <section class="section">
            <h3>Что можно купить в интернет-магазине зоотоваров.</h3>
            <p>В каталоге представлены самые разные типы кормов для ваших питомцев. Выбирайте добавки и витамины в корм
                для любимцев и поддерживайте их здоровье. Перед поступлением в продажу каждый продукт проверяется на
                предмет безопасности состава, качества производства.
            </p>
            <p>Основные разделы магазина, которые помогут вам подобрать нужные товары, следующие:<br>
                • корма и добавки, комплексы витаминов для различных случаев с полным спектром рекомендаций;<br>
                • средства гигиены, косметика и предметы для груминга, специальные предложения для разных пород;<br>
                • игрушки, клетки, домики и различные аксессуары с высоким качеством и практичностью;<br>
                • поводки, ошейники, миски, туалеты и другие необходимые вещи для содержания питомца в доме;<br>
                • элементы декора, украшения, аксессуары для аквариумов, полноценные комплексы для животных.
            </p>
            <p>
                Откройте для себя комфортный зоомагазин (интернет-магазин предлагает демократичные цены и разнообразный
                ассортимент). Достаточно выбрать подходящие для вас изделия, поместить их в корзину на сайте и оформить
                заказ. Оплатить вы можете любым удобным для вас способом. Действуют постоянные акции, скидки,
                предложения для регулярных покупок со значительными бонусами. Мы заботимся о ваших питомцах и всегда
                предлагаем лучшую продукцию.
            </p>
        </section>
    </div>
</main>

<c:import url="../components/footer.jsp"/>