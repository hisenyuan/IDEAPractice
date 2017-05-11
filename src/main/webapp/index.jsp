<%@ page import="com.hisen.image.ShowImageByBase64" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/11
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String imageStr = ShowImageByBase64.showimage();%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<img src="data:image/png;base64,<%=imageStr%>"/>
</body>
</html>
