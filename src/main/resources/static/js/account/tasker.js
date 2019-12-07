$.fn.serializeObject = function()
{
  var o = {};
  var a = this.serializeArray();
  $.each(a, function() {
    if (o[this.name]) {
      if (!o[this.name].push) {
        o[this.name] = [o[this.name]];
      }
      o[this.name].push(this.value || '');
    } else {
      o[this.name] = this.value || '';
    }
  });
  return o;
};

$(document).ready(function () {
  $("#btnContinue").click(function () {
    $('form').each(function() {
      console.log($(this).serializeObject());
      $.post({
        type: "POST",
        url: "/tasker/step2/submit",
        data: $(this).serializeObject()
      }).done((data, statusText, xhr) => {
        console.log(xhr);
      });
    });

    $(".addSkill").attr("style", "display:none");
    $(".addSignature").removeAttr("style");
  });
  $('#signature1').click(function () {
    $('.addSignature').attr("style","display:none");
    $('.addSignature2').removeAttr("style");
  });

  $("#completeTasker").click(function () {
    $.post({
      type: "POST",
      url: "/tasker/step2/complete",
      data: $("#csrfuck").serializeObject()
    }).done((data, statusText, xhr) => {
      if (data === "true") window.location.replace("/?tasker=success");
    });
  });
});
