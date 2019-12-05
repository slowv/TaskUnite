$(document).ready(init());

var calendar__container;

// Hàm để khởi tạo func
function init() {
  start();
  selectDate()
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
  date.setHours(0,0,0,0);
  var endDate = date.getDate() + 4;
  $('#calendar-value').datepicker({
    language: 'vi',
    format: {
      /*
       * Say our UI should display a week ahead,
       * but textbox should store the actual date.
       * This is useful if we need UI to select local dates,
       * but store in UTC
       */
      toDisplay: function (date, format, language) {
        var d = new Date(date);
        d.setHours(0,0,0,0);
        return d.toISOString();
      },
      toValue: function (date, format, language) {
        var d = new Date(date);
        d.setHours(0,0,0,0);
        return d;
      }
    },
    startDate: date,
  }).datepicker('setDate', date);

  $('#calendar-value').on('changeDate', function() {
    $('#selected-date').val(
      selectDate($('#calendar-value').datepicker('getFormattedDate'))
    );
  });

  $('#selected-time').on('change', function () {
    selectDate($('#calendar-value').datepicker('getFormattedDate'));
  })

  $('#selected-date').val(
    selectDate($('#calendar-value').datepicker('getFormattedDate'))
  );
});

function selectDate(date) {
  var selectedDate = moment(date).format("DD-MM-YYYY");
  var time = $('#selected-time').val();
  var hour = time.split(":")[0];
  var minute = time.split(":")[1];
  $('.pre-datetime').html(`${selectedDate}, ${time}`);
  return moment(date).add(hour, "hours").add(minute, "minutes");
}





