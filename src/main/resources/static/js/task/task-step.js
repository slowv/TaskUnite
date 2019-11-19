$(document).ready(function () {
  for (var i = 0; i <= 3; i++) {
    var step = $('.st' + (i + 1));
    if (step.hasClass('active')) {
      if (step.next().hasClass('active')) {
        step.find('.line').css({'background': '#51af33'});
      }
    }
  }
});
