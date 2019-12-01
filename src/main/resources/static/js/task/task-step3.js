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
}

function onClick() {
  calendar__container.on('click', '.calendar__day', function () {
    calendar__container.find('.calendar__day--selected').removeClass('calendar__day--selected');
    var day = $(this).addClass('calendar__day--selected').html();
    var month = String(new Date().getMonth() + 1).padStart(2, '0');
    var hours = $('#select-time').val();
    $('.pre-datetime').html(`${day} - T${month}, ${hours}`);
  });
}

// Truong hop lien quan den date pahi cho vao day.
$(document).ready(function () {
  var date = new Date();
  var endDate = date.getDate() + 4;
  $('#calendar-value').datepicker({
    language: 'vi',
    format: 'yyyy/mm/dd',
    startDate: date,
  }).datepicker('setDate', date);

});







