/**
 * Created by hisen on 17-8-26.
 */
$.ajax({
  type: "GET",
  url: "http://localhost:8080/hisen/test",
  async: false,
  headers: {
    "Authorization":localStorage.getItem("jwt")
  },
  success: function(result) {
    $("#hisenyuan").text(result);
    alert(1 )
  }
});