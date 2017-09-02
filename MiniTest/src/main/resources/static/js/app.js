function showLoading() {
  $("#btn_search").prop('disabled', true);
  $("#txt_search").text(' In Progess');
  $("#fa_spinner").removeClass("hidden");
}

function hiddenLoading() {
  $("#btn_search").prop('disabled', false);
  $("#txt_search").text('Search');
  $("#fa_spinner").addClass("hidden");
}
function appendDataArea(areas) {
  for (i = 0; i < areas.length; i++) {
    document.getElementById("areas").innerHTML += '<div class="col-md-1"><a href="/location/detail?id=' + areas[i].id + '">' + areas[i].name + '</a></div>';
  }
}

$(document).ready(function() {
  $('#btn_search').click(function (e) {
    var area = $('#area').val();
    $.ajax({
      type: 'GET',
      beforeSend: function () {
        document.getElementById("areas").innerHTML = "";
        showLoading();
      },
      url: "/location/ajax",
      data: {area: area},
      dataType: "json",
      success: function(res) {
        appendDataArea(res);
      },
      complete: function() {
        hiddenLoading();
      },
      error: function() {
        hiddenLoading();
      }
    });
    e.preventDefault();

  });

  $('#users').DataTable();
});
