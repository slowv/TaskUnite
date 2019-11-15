$(document).ready(init());

function init() {
  changeUI();
  eventUI();
}

function changeUI() {
  for (var i = 0; i <= 3; i++) {
    var step = $('.st' + (i + 1));
    if (step.hasClass('active')) {
      if (step.next().hasClass('active')) {
        step.find('.line').css({'background': '#C4592B'});
      }
    }
  }

  var taskBox = $('.task-box');
  $('.task-box[data-editing=false]').addClass('d-none');
  if (taskBox.attr('data-editing') && taskBox.attr('data-state') == 'summary') {
    $('.status-task-box').addClass('d-none');
  } else {
    $('span.status-task-box').removeClass('d-none');
  }

}

function eventUI(){
  $('select[name="option-address"]').change(function () {
    if ($(this).find('option:selected').attr('value') == 1){
      $('.box-input-address-single').slideDown();
      $('.box-input-address-multi').slideUp();
    }else{
      $('.box-input-address-single').slideUp();
      $('.box-input-address-multi').removeClass('d-none');
      $('.box-input-address-multi').slideDown();
    }
  });

  $('.task-box').on('click', '.btn-continue', function () {
    var parent = $(this).parent().parent().parent();
    parent.attr('data-editing', false).attr('data-state', 'summary').find('span.status-task-box').removeClass('d-none');
    if ($('select option:selected').val() == 1){
      parent.find('.content').html(parent.find('#address').val());
    }else{
      parent.find('.content').html(`${parent.find('#addressStart').val()} - ${parent.find('#addressEnd').val()} `);
    }
    parent.find('.box-input').addClass('d-none');
    parent.find('.preview-content').removeClass('d-none');
    parent.find('select[name="option-address"]').addClass('d-none');
    parent.next().attr('data-editing', true).attr('data-state', 'summary').find('span.status-task-box').addClass('d-none');
  });

  var iconStatus = $('span.status-task-box');
  iconStatus.hover(function () {
    $(this).find('i').removeClass('icon-check');
    $(this).find('i').addClass('icon-pencil');
  }, function () {
    $(this).find('i').addClass('icon-check');
    $(this).find('i').removeClass('icon-pencil');
  });
  iconStatus.click(function () {
    var parent = $(this).parent();
    parent.attr('data-editing', true).find('span.status-task-box').addClass('d-none');
    if ($('select option:selected').val() == 1){
      $('.box-input-address-single').slideDown();
      $('.box-input-address-multi').slideUp();
    }else{
      $('.box-input-address-single').slideUp();
      $('.box-input-address-multi').slideDown();
    }
    parent.find('.box-input').removeClass('d-none');
    parent.find('.preview-content').addClass('d-none');
    parent.find('select[name="option-address"]').removeClass('d-none');
  });
}
