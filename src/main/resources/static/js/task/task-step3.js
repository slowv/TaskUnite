$(document).ready(init());

var calendar__container;

// Hàm để khởi tạo func
function init() {
  start();
  onClick();
}

// Hàm để fixed UI ngay lúc vừa vào page và khởi tạo biến trong này
function start() {
  calendar__container = $('.calendar__container');

  var today = new Date();
  var dd = String(today.getDate()).padStart(2, '0');
  var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
  var yyyy = today.getFullYear();
  today = mm + '-' + dd + '-' + yyyy;
  $('.calendar__title span').html(today.toString());
}

function onClick() {
  calendar__container.on('click', '.calendar__day', function () {
    var day = 0;
    var month = 0;
    var hours = 0
    calendar__container.find('.calendar__day--selected').removeClass('calendar__day--selected');
    hours = $('#select-time').val();
    if($(this).find(".calendar__day--with-month").length == 0){
      day = $(this).addClass('calendar__day--selected').html();
      month = String(new Date().getMonth() + 1).padStart(2, '0');
    } else {
      day = $(this).find(".calendar__day--with-month div:nth-child(2)").html();
      month = $(this).find(".calendar__day--with-month div:nth-child(1)").html().replace(/[^0-9]/gi,'');
    }
    $('.pre-datetime').html(`${day} - T${month}, ${hours}`);
  });
}







