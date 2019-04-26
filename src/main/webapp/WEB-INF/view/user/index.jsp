<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!--引入layui-->
    <link rel="stylesheet" type="text/css" href="/static/layui/css/layui.css">

    <script type="text/javascript" src="/static/js/jquery.js"></script>


</head>
<body>
<div class="layui-form-item">
    <label class="layui-form-label">单行输入框</label>
    <div class="layui-input-block">
        <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
    </div>
</div>
<h1>测试springBoot</h1>


用户名:<input type="text" name="username" id="username"/> <br>
密码:<input type="password" name="password" id="password"><br>
<input type="button" value="提交" id="formSubmit">

<script>
    $("#formSubmit").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        $.post("/admin/user/login", {username: username, password: password}, function (result) {
            if (result.success) {
                console.info("成功了");
                window.location.href= "/admin/user/main";
            } else {
                console.info("失败了");
            }
        });
    });
</script>
</body>
</html>
