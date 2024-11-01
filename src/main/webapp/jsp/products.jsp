<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:import url="../components/header.jsp"/>


<main class="main">
    <div class="main__container">
        <h1>Все товары</h1>
        <div class="all-items__wrapper">
            <section>
                <form class="products-filter" action="controller">
                    <button class="accordion">Цена</button>
                    <div class="accordion-content">
                        <p>
                        <div class="filter-price">
                            <label for="price-start">От</label>
                            <input id="price-start" type="number" min="1" name="start_price" placeholder="${product_min_price}">
                            <label for="price-end">До</label>
                            <input id="price-end" type="number" name="end_price" placeholder="${product_max_price}">
                        </div>
                        </p>
                    </div>

                    <button class="accordion">Для кого</button>
                    <div class="accordion-content">
                        <p>
                        <div class="filter-animal">

                            <label>
                                <input type="checkbox" name="category" value="CATS">
                                Кошки
                            </label>

                            <label>
                                <input type="checkbox" name="category" value="DOGS">
                                Собаки
                            </label>
                        <label>
                            <input type="checkbox"  name="category" value="BIRDS">
                            Птицы
                        </label>
                        <label>
                            <input type="checkbox" name="category" value="TURTLES">
                            Черепахи
                        </label>
                        </div>
                        </p>
                    </div>

                    <button class="accordion">Тип товара</button>
                    <div class="accordion-content">
                        <p>
                        <div class="filter-type">

                            <label>
                                <input type="checkbox" name="type" value="ACCESSORIES">
                                Аксессуары
                            </label>

                            <label>
                                <input type="checkbox" name="type" value="FOOD">
                                Корм
                            </label>
                            <label>
                                <input type="checkbox" name="type" value="TOY">
                                Игрушки
                            </label>
                        </div>
                        </p>
                    </div>
                    <button class="add-to-cart" type="submit">Применить</button>
                    <input type="hidden" name="command" value="to_filtered_products">
                </form>
            </section>
            <section class="section__all-items">


<c:forEach var="product" items="${products}">
                <form class="item-slider__slide item-slide" action="controller">
                    <div class="item-slider__slide-wrapper">
                        <div class="item-slide__image-wrapper">
                            <img src="../images/products/${product.image}" alt="Image">
                        </div>
                        <a class="slide-item__title" href="controller?command=to_product_page&id=${product.id}">
                           ${product.name}
                        </a>
                        <input type="number" min="1" name="quantity" value="1"/>
                        <p class="item-slide__price-container">
                            <span class="item-slide__price">${product.price}</span>
                            Р
                        </p>
                                <button class="add-to-cart" type="submit" value="Добавить в корзину">Добавить в корзину</button>
                                <input type="hidden" name="id" value="${product.id}">
                                <input type="hidden" name="command" value="add_product_to_cart">
                    </div>
                </form>

</c:forEach>


                </section>
            </div>

            <section class="section__pagination">
                <div class="pagionation__wrapper">
                    <c:forEach begin="1" end="${number_of_pages}" var="i">
                        <c:if test="${sessionScope.products_start_from == i}">
                            <a href="controller?command=find_products_pagination&products_start_from=${i}" class="pagination__item pagination-current">
                                <c:out value="${i}"/>
                            </a>
                        </c:if>
                        <c:if test="${sessionScope.products_start_from != i}">
                            <a href="controller?command=find_products_pagination&products_start_from=${i}" class="pagination__item">
                                <c:out value="${i}"/>
                            </a>
                        </c:if>
                    </c:forEach>
                </div>
            </section>
        </div>
    </main>



    <c:import url="../components/footer.jsp"/>