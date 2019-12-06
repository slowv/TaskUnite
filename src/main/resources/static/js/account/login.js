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

$("#loginForm").on("submit", function(e) {
  e.preventDefault();
  $.post({
    type: "POST",
    url: "/api/authentication",
    data: $(this).serialize()
  }).done((data, statusText, xhr) => {
    console.log(xhr);
    if (xhr.status === 200) window.location.replace("/");
  });
});
