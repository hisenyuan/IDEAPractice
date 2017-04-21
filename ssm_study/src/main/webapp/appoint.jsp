<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/17
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String appPath = request.getContextPath(); %>
<html>
<head>
    <title>预约图书</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <ul class="nav nav-tabs">
                <li><a href="<%=appPath%>/book/list">首页</a></li>
                <li><a href="<%=appPath%>/book/detail/1003">图书具体信息</a></li>
                <li><a href="<%=appPath%>/add.jsp">添加图书信息</a></li>
                <li class="active"><a href="<%=appPath%>/appoint.jsp">预约图书</a></li>
                <li class="disabled"><a href="#">信息</a></li>
            </ul>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    添加图书
                    <small>增加图书信息</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form" id="appoint" method="post" action="<%=appPath%>book/appoint" accept-charset="utf-8">
                <div class="form-group">
                    <label>学生编号</label><input class="form-control" name="studentId"/>
                </div>
                <div class="form-group">
                    <label>图书编号</label><input class="form-control" name="bookId"/>
                </div>
                <button class="btn btn-default" id="sub">Submit</button>
            </form>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-success alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×
                </button>
                <h4>
                    注意!
                </h4> <strong>Warning!</strong> Best check yo self, you're not looking too good. <a
                    href="#" class="alert-link">alert link</a>
            </div>
        </div>
    </div>
    <div id="footer" class="container">
        <nav class="navbar navbar-default navbar-fixed-bottom">
            <div class="navbar-inner navbar-content-center">
                <p class="text-muted credit" style="padding: 10px;">
                <p class="muted credit">powered by <a href="http://hisen.me">HiSEN</a> and <a href="http://hisen.me">A.L</a>.</p>
                </p>
            </div>
        </nav>
    </div>
</div>

<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="https://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
  function list() {
    window.location.href="<%=appPath%>/book/list";
  }
  var frm = $('#appoint');
  frm.submit(function (ev) {
    $.ajax({
      type: frm.attr('method'),
      url: frm.attr('action'),
      data: frm.serialize(),
      success:function(data) {
        list();
      },
      error:function(data){
        list();
      }
    });
    ev.preventDefault();
  });
</script>
</body>
</html>

<html>
<head>
    <title>预约结果</title>
</head>
<body>
${requestScope.get('result')}
<
</body>

</html>
