<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/12
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重置密码</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <style type="text/css">
        body {
            width: 50%;
            margin-left: 25%;
        }
        .content_title{
            text-align: center;
        }
        form{
            margin-top: 50px;
        }
        button{
            width: 100px;
            margin-left: 30%;
            margin-top: 50px;
        }
    </style>
</head>
<body>
<div class="content_title">
    <h2>重置密码</h2>
</div>
<hr>
<form class="form-horizontal" role="form">
    <div class="form-group">
        <label class="col-sm-3 control-label">旧密码</label>
        <div class="col-sm-7">
            <input type="text" class="form-control" id="oldPwd" placeholder="请输入" required>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">新密码</label>
        <div class="col-sm-7">
            <input type="text" class="form-control" id="newPwd" placeholder="请输入" required>
        </div>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-primary">确定</button>
    </div>
</form>

</body>
</html>