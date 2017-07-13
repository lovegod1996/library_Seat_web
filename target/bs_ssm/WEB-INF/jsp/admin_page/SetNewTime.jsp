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
    <h2>设置时间参数</h2>
</div>
<hr>
<form class="form-horizontal" role="form">
    <div class="form-group">
        <label class="col-sm-3 control-label">设置时间上限</label>
        <div class="col-sm-7">
            <input type="text" class="form-control" id="newTime" placeholder="请输入（小时）" required>
        </div>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-primary">确定</button>
    </div>
</form>

</body>
</html>
