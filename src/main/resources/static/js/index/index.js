

$(document).ready(function() {
  var params={};
  window.location.search
    .replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str,key,value) {
      params[key] = value;
    });
  if (params["register"] &&  params["register"] === "success") toastr.info("Register success");
  if (params["tasker"] &&  params["tasker"] === "success") toastr.info("Congrats! You're tasker now");
  if (params["master"] &&  params["master"] === "success") toastr.info("Congrats! You're master now");

});
