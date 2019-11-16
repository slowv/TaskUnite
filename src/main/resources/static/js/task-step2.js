$(document).ready(init());

function init() {
  start();
  update();
}

function start() {
}

function update() {
  $('.iconSlideUp').click(function () {
    $(this).toggleClass('icon-chevron-down');
    $(this).parent().next().slideToggle();

  });
}
