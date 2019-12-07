function logout() {
  $.post({
    type: "POST",
    url: "/api/logout",
    data: $("#logout").serialize()
  }).done((data, statusText, xhr) => {
    console.log(xhr);
    if (xhr.status === 200) window.location.replace("/");
  });
}
