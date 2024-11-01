const burger = document.querySelector('.nav__burger');

burger.addEventListener('click', function() {
    const navigation = document.querySelector('.header__nav');
    const search = document.querySelector('.header__search');

    navigation.classList.toggle('nav__active');
    burger.classList.toggle('open');
    search.classList.toggle('search__active');
});