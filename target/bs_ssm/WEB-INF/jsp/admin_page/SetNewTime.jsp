<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/12
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <%--操作确认--%>
    <script src="<%=request.getContextPath()%>/js/dialog.js"></script>

    <style type="text/css">
        body {
            width: 50%;
            margin-left: 25%;
        }

        .content_title {
            text-align: center;
        }

        form {
            margin-top: 50px;
        }

        button {
            width: 100px;
            margin-left: 30%;
            margin-top: 50px;
        }
    </style>
</head>
<body>
<div class="content_title">
    <p style="font-size: 30px;margin-top: 20px">设置时间参数</p>
</div>
<hr>
<form class="form-horizontal" role="form">
    <div class="layui-inline">
        <label class="layui-form-label" style="width: 120px">设置时间上限</label>
        <div class="layui-input-inline">
            <input type="password" id="newTime" class="layui-input" placeholder="请输入（小时）" required style="width: 250px">
        </div>
    </div>
    <button type="submit" class="layui-btn" onclick="return confirmAct()">确定</button>
</form>

</body>
</html>
