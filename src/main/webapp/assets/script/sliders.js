const banner = new Swiper(".banner-slider", {
  navigation: {
    enabled: false,
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
  pagination: {
    el: ".banner-pagination",
    clickable: true,
  },
  breakpoints: {
    768: {
      navigation: {
        enabled: true,
      },
    }
  }
});

const item = new Swiper(".item-slider", {
  slidesPerView: 1,
  navigation: {
    enabled: true,
    nextEl: ".item-next",
    prevEl: ".item-back",
  },
  breakpoints: {
    1140: {
      slidesPerView: 5,
    },
    768: {
      slidesPerView: 3,
    },
    375: {
      slidesPerView: 2,
    }
  }
});
  