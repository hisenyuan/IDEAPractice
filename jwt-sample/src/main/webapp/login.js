/**
 * Created by hisen on 17-8-26.
 */

var frm = $('#form');
frm.submit(function (ev) {
  $.ajax({
    type: "post",
    url: "http://localhost:8080/hisen/login",
    data: frm.serialize(),
    success:function(data) {
      console.log(data)
      var json = jQuery.parseJSON(data);
      var jwt = json.jwt;
      var kv = $.base64.decode(jwt.split('.')[1]);
      var info = jQuery.parseJSON(kv);
      // 登录成功,存储令牌到本地
      localStorage.setItem("jwt",jwt)
      alert(localStorage.getItem("jwt"));

      window.location.href="./hisenyuan.html";
    },
    error:function(data){
      console.log(data)
    }
  });
  ev.preventDefault();
});

