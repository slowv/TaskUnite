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

  $('#saveReviewBtn').on('click', function () {
    if($('#valueStarRating').val() == ''){
      $('#valueStarRating').val(0);
    }

    var data = {
      content: $('#reviewContent').val(),
      point: parseInt($('#valueStarRating').val()),
      // taskId: parseInt($('#taskId').val())
    };

    // $.ajax({
    //   type: "POST",
    //   url: "/review/create",
    //   data: JSON.stringify(data),
    //   contentType:"application/json; charset=utf-8",
    //   dataType: "json",
    //   success: function(response){
    //     console.log("success")
    //   },
    //   error: function(req, response){
    //     alert("Error:" + response);
    //   }
    // });
  })
}
