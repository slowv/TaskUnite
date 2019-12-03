$(document).ready(init());

var calendar__container;

// Hàm để khởi tạo func
function init() {
  start();
}

// Hàm để fixed UI ngay lúc vừa vào page và khởi tạo biến trong này
function start() {
  calendar__container = $('.calendar__container');
}

// function onClick() {
//   calendar__container.on('click', '.calendar__day', function () {
//     calendar__container.find('.calendar__day--selected').removeClass('calendar__day--selected');
//     var day = $(this).addClass('calendar__day--selected').html();
//     var month = String(new Date().getMonth() + 1).padStart(2, '0');
//     var hours = $('#select-time').val();
//     $('.pre-datetime').html(`${day} - T${month}, ${hours}`);
//   });
// }

// Truong hop lien quan den date pahi cho vao day.
$(document).ready(function () {
  var date = new Date();
  var endDate = date.getDate() + 4;
  $('#calendar-value').datepicker({
    language: 'vi',
    // format: 'yyyy/mm/dd',
    format: {
      /*
       * Say our UI should display a week ahead,
       * but textbox should store the actual date.
       * This is useful if we need UI to select local dates,
       * but store in UTC
       */
      toDisplay: function (date, format, language) {
        var d = new Date(date);
        return d.toISOString();
      },
      toValue: function (date, format, language) {
        var d = new Date(date);
        return new Date(d);
      }
    },
    startDate: date,
  }).datepicker('setDate', date);
  $('#calendar-value').on('changeDate', function() {
    $('#selected-date').val(
      $('#calendar-value').datepicker('getDate')
    );
    var selectedDate = moment($('#selected-date').val()).format("DD/MM/YYYY");
    var hours = $('#select-time').val();
    $('.pre-datetime').html(`${selectedDate}, ${hours}`);
  });
});






