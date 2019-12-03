$(document).ready(init());

var iconStatus;
var taskBox;

// Hàm để khởi tạo func
function init() {
  start();
  onChange();
  onKeyPress();
  onClick();
  onHover();
}

// Hàm để fixed UI ngay lúc vừa vào page và khởi tạo biến trong này
function start () {
  taskBox = $('.task-box');
  iconStatus = $('span.status-task-box');

  $('.task-box[data-editing=false] .box-input').addClass('d-none');
  if (taskBox.attr('data-editing') && taskBox.attr('data-state') == 'summary') {
    iconStatus.addClass('d-none');
  } else {
    iconStatus.removeClass('d-none');
  }
}

// Hàm để viết sự kiện change
function onChange() {
  $('select[name="option-address"]').change(function () {
    if ($(this).find('option:selected').val() == 1) {
      $('.box-input-address-single').slideDown();
      $('.box-input-address-multi').slideUp();
    } else {
      $('.box-input-address-single').slideUp();
      $('.box-input-address-multi').removeClass('d-none');
      $('.box-input-address-multi').slideDown();
    }
  });
}

// Hàm để viết sự kiện keypress
function onKeyPress() {
  $('#form-task input').keypress(function () {
    $('.error-msg').each(function (index, value) {
      if(!$(this).hasClass('d-none')){
        $(this).addClass('d-none');
      }
    })
  });
}

// Hàm để viết sự kiện click
function onClick() {
  taskBox.on('click', '.btn-continue', function () {
    var parent = $(this).parent().parent().parent();
    switch ($(this).attr('data-type')) {
      case 'address':
        if ($('select option:selected').val() == 1) {
          var inputAddress = parent.find('#address');
          if (inputAddress === undefined || inputAddress.val() == '' || inputAddress.val().length == 0){
            parent.find('.error-msg').removeClass('d-none').html('Vui lòng điền thông tin đầy đủ!')
            return;
          }
          parent.find('.content').html(inputAddress.val());
          taskBox.address = inputAddress.val();
        } else {
          var addressStart = parent.find('#addressStart');
          var addressEnd = parent.find('#addressEnd');
          if (addressStart === undefined || addressStart.val() == '' || addressStart.val().length == 0 ||
            addressEnd === undefined || addressEnd.val() == '' || addressEnd.val().length == 0){
            parent.find('.error-msg').removeClass('d-none').html('Vui lòng điền thông tin đầy đủ!')
            return;
          }
          var rangeAddress = `${parent.find('#addressStart').val()} - ${parent.find('#addressEnd').val()} `;
          parent.find('.content').html(rangeAddress);
          taskBox.address = rangeAddress;
        }
        parent.find('select[name="option-address"]').addClass('d-none');
        break;
      case 'title':
        var title = parent.find('#title-task').val();
        parent.find('.content').html(title);
        taskBox.title = title;
        break;
      case 'category':
        var content = parent.find('.content');
        var contentStr = [];
        parent.find('input[type="checkbox"]:checked').each(function (index, value) {
          contentStr.push($(this).siblings("label").text());
          if(!taskBox.category){
            taskBox.category = [];
          }
          taskBox.category.push($(this).attr('id').replace("cateId-", ""));
        });
        content.html(contentStr.join(", "));
        break;
      case 'estimated-time':
        parent.find('.content').html(parent.find('input[type="number"]').val() + " Giờ");
        break;
      case 'description':
        var strDescription = parent.find('textarea').val();
        parent.find('.content').html(strDescription);
        taskBox.description = strDescription;
        // $('#next-step2').attr('href', '/task/create/step2').find('button').attr('disabled', false);
        $('#next-step2').attr('disabled', false);
        $('#next-step2').attr('href', '/task/create/step2').find('button').attr('disabled', false);
        break;
    }
    parent.attr('data-editing', false).attr('data-state', 'summary').find('span.status-task-box').removeClass('d-none');
    parent.find('.box-input').addClass('d-none');
    parent.find('.preview-content').removeClass('d-none');
    var taskBoxNext = parent.parent().next().find('.task-box');
    taskBoxNext.attr('data-editing', true)
      .attr('data-state', 'summary')
      .removeClass('d-none')
      .find('span.status-task-box').addClass('d-none');
    taskBoxNext.find('.box-input').removeClass('d-none');
  });

  iconStatus.click(function () {
    var parent = $(this).parent();
    switch (parent.find('.btn-continue').attr('data-type')) {
      case 'address':
        if ($('select option:selected').val() == 1) {
          $('.box-input-address-single').slideDown();
          $('.box-input-address-multi').slideUp();
        } else {
          $('.box-input-address-single').slideUp();
          $('.box-input-address-multi').slideDown();
        }
        parent.find('select[name="option-address"]').removeClass('d-none');
        break;
      case 'title':
        break;
      case 'category':
        parent.find('.content').html('');
        break;
      case 'description' :
        $('#next-step2').attr('disabled', true);
        break;
    }
    $('.task-box').attr('data-editing', false);
    $('.box-input').each(function (index, value) {
      if (!$(this).hasClass('d-none')) {
        $(this).addClass('d-none');
      }
    });
    parent.attr('data-editing', true).find('span.status-task-box').addClass('d-none');
    parent.find('.box-input').removeClass('d-none');
    parent.find('.preview-content').addClass('d-none');
  });
}

// Hàm để viết sự kiện hover
function onHover() {
  iconStatus.hover(function () {
    $(this).find('i').removeClass('icon-done');
    $(this).find('i').addClass('icon-pencil');
  }, function () {
    $(this).find('i').addClass('icon-done');
    $(this).find('i').removeClass('icon-pencil');
  });
}
