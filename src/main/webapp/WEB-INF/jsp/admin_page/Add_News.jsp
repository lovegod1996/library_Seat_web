<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 20:25
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
            width: 75%;
            margin-left: 2%;
        }
    </style>
</head>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <i class="fa fa-bar-chart-o fa-fw"></i> 发布通知
    </div>
    <!-- /.panel-heading -->
    <div class="table table-condensed">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="title" class="col-sm-2 control-label">标题</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="title" placeholder="请输入标题" required>
                </div>
            </div>
            <div class="form-group">
                <label for="content" class="col-sm-2 control-label">内容</label>
                <div class="col-sm-9">
                    <textarea class="form-control" id="content" rows="10" placeholder="请输入内容" required></textarea>
                </div>
            </div>
            <div class="form-group">
                <label for="inputfile" class="col-sm-2 control-label">插入图片</label>
                <div class="col-sm-4">
                    <input type="file" id="inputfile">
                </div>
                <div class="col-sm-1" >
                    <button type="submit" class="btn btn-primary">发布</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- /.panel -->
</body>
</html>
