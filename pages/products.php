<?php
require_once('../components/header.php');
?>

<main class="main">
    <div class="main__container">
        <h1>Все товары</h1>
        <div class="all-items__wrapper">
            <section>
                <form class="products-filter" action="#">
                    <button class="accordion">Цена</button>
                    <div class="accordion-content">
                        <p>
                        <div class="filter-price">
                            <label for="price-start">От</label>
                            <input id="price-start" type="text" name="start" placeholder="Начальная цена">
                            <label for="price-end">До</label>
                            <input id="price-end" type="text" name="end" placeholder="Конечная цена">
                        </div>
                        </p>
                    </div>

                    <button class="accordion">Для кого</button>
                    <div class="accordion-content">
                        <p>
                        <div class="filter-animal">

                            <label>
                                <input type="checkbox" name="cats">
                                Кошки
                            </label>

                            <label>
                                <input type="checkbox" name="dogs">
                                Собаки
                            </label>
                        </div>
                        </p>
                    </div>

                    <button class="accordion">Тип товара</button>
                    <div class="accordion-content">
                        <p>
                        <div class="filter-type">

                            <label>
                                <input type="checkbox" name="cats">
                                Аксессуары
                            </label>

                            <label>
                                <input type="checkbox" name="dogs">
                                Корм
                            </label>
                            <label>
                                <input type="checkbox" name="dogs">
                                Игрушки
                            </label>
                        </div>
                        </p>
                    </div>
                    <button class="add-to-cart" type="submit">Применить</button>
                </form>
            </section>
            <section class="section__all-items">
                <div class="item-slider__slide item-slide">
                    <div class="item-slider__slide-wrapper">
                        <div class="item-slide__image-wrapper">
                            <img src="../images/item.png" alt="Image">
                        </div>
                        <a class="slide-item__title" href="#">Влажный корм Royal Canin British shorthair кусочки в
                            соусе для британских кошек
                        </a>
                        <p class="item-slide__price-container">
                            <span class="item-slide__price">999</span>
                            Р
                        </p>
                        <button class="add-to-cart">В корзину</button>
                    </div>
                </div>
                <div class="item-slider__slide item-slide">
                    <div class="item-slider__slide-wrapper">
                        <div class="item-slide__image-wrapper">
                            <img src="../images/item.png" alt="Image">
                        </div>
                        <a class="slide-item__title" href="#">Влажный корм Royal Canin British shorthair кусочки в
                            соусе для британских кошек
                        </a>
                        <p class="item-slide__price-container">
                            <span class="item-slide__price">999</span>
                            Р
                        </p>
                        <button class="add-to-cart">В корзину</button>
                    </div>
                </div>
                <div class="item-slider__slide item-slide">
                    <div class="item-slider__slide-wrapper">
                        <div class="item-slide__image-wrapper">
                            <img src="images/item.png" alt="Image">
                        </div>
                        <a class="slide-item__title" href="#">Влажный корм Royal Canin British shorthair кусочки в
                            соусе для британских кошек
                        </a>
                        <p class="item-slide__price-container">
                            <span class="item-slide__price">999</span>
                            Р
                        </p>
                        <button class="add-to-cart">В корзину</button>
                    </div>
                </div>
                <div class="item-slider__slide item-slide">
                    <div class="item-slider__slide-wrapper">
                        <div class="item-slide__image-wrapper">
                            <img src="images/item.png" alt="Image">
                        </div>
                        <a class="slide-item__title" href="#">Влажный корм Royal Canin British shorthair кусочки в
                            соусе для британских кошек
                        </a>
                        <p class="item-slide__price-container">
                            <span class="item-slide__price">999</span>
                            Р
                        </p>
                        <button class="add-to-cart">В корзину</button>
                    </div>
                </div>
                <div class="item-slider__slide item-slide">
                    <div class="item-slider__slide-wrapper">
                        <div class="item-slide__image-wrapper">
                            <img src="images/item.png" alt="Image">
                        </div>
                        <a class="slide-item__title" href="#">Влажный корм Royal Canin British shorthair кусочки в
                            соусе для британских кошек
                        </a>
                        <p class="item-slide__price-container">
                            <span class="item-slide__price">999</span>
                            Р
                        </p>
                        <button class="add-to-cart">В корзину</button>
                    </div>
                </div>
                <div class="item-slider__slide item-slide">
                    <div class="item-slider__slide-wrapper">
                        <div class="item-slide__image-wrapper">
                            <img src="images/item.png" alt="Image">
                        </div>
                        <a class="slide-item__title" href="#">Влажный корм Royal Canin British shorthair кусочки в
                            соусе для британских кошек
                        </a>
                        <p class="item-slide__price-container">
                            <span class="item-slide__price">999</span>
                            Р
                        </p>
                        <button class="add-to-cart">В корзину</button>
                    </div>
                </div>
                <div class="item-slider__slide item-slide">
                    <div class="item-slider__slide-wrapper">
                        <div class="item-slide__image-wrapper">
                            <img src="images/item.png" alt="Image">
                        </div>
                        <a class="slide-item__title" href="#">Влажный корм Royal Canin British shorthair кусочки в
                            соусе для британских кошек
                        </a>
                        <p class="item-slide__price-container">
                            <span class="item-slide__price">999</span>
                            Р
                        </p>
                        <button class="add-to-cart">В корзину</button>
                    </div>
                </div>
            </section>
        </div>

        <section class="section__pagination">
            <div class="pagionation__wrapper">
                <a href="#" class="pagination__item pagination-prev">
                    <img src="../images/pagination.svg" alt="Back">
                </a>
                <a href="#" class="pagination__item pagination-current">1</a>
                <a href="#" class="pagination__item">2</a>
                <a href="#" class="pagination__item">3</a>
                <a href="#" class="pagination__item pagination-next">
                    <img src="../images/pagination.svg" alt="next">
                </a>
            </div>
        </section>
    </div>
</main>

<?php
require_once('../components/footer.php');
?>