$(document).ready(init());
var iconSlideUp;
var btnTaskDone;
var starRating;
// Hàm để khởi tạo func
function init() {
  start();
  onClick();
}

// Hàm để fixed UI ngay lúc vừa vào page và khởi tạo biến trong này
function start() {
  iconSlideUp = $('.iconSlideUp');
  btnTaskDone = $('.btn-task-done');
  starRating = $('.starRating');
  iconSlideUp.parent().find('.box-option').slideUp();
}

function onClick(){
  iconSlideUp.click(function () {
    $(this).toggleClass('icon-chevron-up');
    $(this).toggleClass('icon-chevron-down');
    $(this).parent().find('.box-option').slideToggle();
  });

  btnTaskDone.click(function () {
    $('#modal-review').modal();
  });

  starRating.click(function () {
    var indexStar = $(this).attr('data-index');
    starRating.each(function (index, value) {
      if ($(this).hasClass('icon-star')){
        $(this).removeClass('icon-star');
        $(this).addClass('icon-star-o');
      }
      if (index <= indexStar){
        $(this).toggleClass('icon-star-o');
        $(this).toggleClass('icon-star');
      }
    });
    $('#valueStarRating').val(parseInt(indexStar) + 1);
    // console.log($('#valueStarRating').val());
  })
}
