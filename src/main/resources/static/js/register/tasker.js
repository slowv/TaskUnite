$(document).ready(function () {
  $("#btnContinue").click(function () {
    $(".addSkill").attr("style", "display:none");
    $(".addSignature").removeAttr("style");
  });
  $('#signature1').click(function () {
    $('.addSignature').attr("style","display:none");
    $('.addSignature2').removeAttr("style");
  });
});
