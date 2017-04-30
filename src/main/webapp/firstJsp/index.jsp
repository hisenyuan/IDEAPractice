<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/24
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>Do you come in?</h1>
<form method="post" action="/hello.do">
    Select:<br>
    <select>
        <option name="a" value="yes">yes</option>
        <option name="b" value="no">no</option>
    </select>
    <input type="submit">
</form>
</body>
</html>
