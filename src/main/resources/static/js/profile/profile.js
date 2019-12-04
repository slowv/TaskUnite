$(document).ready(function() {

  if ( $('.nonloop-block-profile').length > 0 ) {
    var owl = $('.nonloop-block-profile');
    owl.owlCarousel({
      center: false,
      items: 1,
      loop: true,
      stagePadding: 0,
      autoplay: false,
      margin: 20,
      nav: false,
      dots: true,
      autoHeight: true,
      navText: ['<span class="icon-arrow_back">', '<span class="icon-arrow_forward">'],
      responsive:{
        600:{
          margin: 20,
          stagePadding: 0,
          items: 1,
          nav: false,
          dots: true
        },
        1000:{
          margin: 20,
          stagePadding: 0,
          items: 1,
          nav: false,
          dots: true
        },
        1200:{
          margin: 20,
          stagePadding: 0,
          items: 1,
          nav: false,
          dots: true
        }
      }
    });

    $('.owl-custom-next').click(function(e) {
      e.preventDefault();
      owl.trigger('next.owl.carousel');
    });
    $('.owl-custom-prev').click(function(e) {
      e.preventDefault();
      owl.trigger('prev.owl.carousel');
    });
  }
});
