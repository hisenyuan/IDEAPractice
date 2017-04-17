<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/17
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String appPath = request.getContextPath() + "/"; %>
<html>
<head>
    <title>预约结果</title>
</head>
<body>
${requestScope.get('result')}
<form id="appoint" action="<%=appPath%>book/appoint" method="post">
    <label>学生编号</label><input class="form-control" name="studentId"/>
    <label>图书编号</label><input class="form-control" name="bookId"/>
</form>
</body>
<script src="https://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
  var frm = $('#appoint');
  frm.submit(function (ev) {
    $.ajax({
      type: frm.attr('method'),
      url: frm.attr('action'),
      data: frm.serialize(),
      success:function(data) {
        alert("添加成功");
      },
      error:function(data){
        alert("添加失败");
      }
    });
    ev.preventDefault();
  });
</script>
</html>
