<%--
  Created by IntelliJ IDEA.
  User: toroot
  Date: 2019/4/22
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!--引入layui-->
    <link rel="stylesheet" href="../static/layui/css/layui.css">
</head>
<body>
<h1>测试springBoot</h1>

<div class="layui-form-item">
    <label class="layui-form-label">单行输入框</label>
    <div class="layui-input-block">
        <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
    </div>
</div>
</body>
</html>
