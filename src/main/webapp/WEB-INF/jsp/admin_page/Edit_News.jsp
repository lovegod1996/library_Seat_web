<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <%--操作确认--%>
    <script src="<%=request.getContextPath()%>/js/dialog.js"></script>
    <style type="text/css">
        body {
            width: 75%;
            margin-left: 2%;
            background-color: #eff3f8;
            margin-top: 15px;
        }
        .panel-default>.panel-heading {
            color: #5c9bd1;
            background-color: #ffffff;
            border-color: #f9f9f9;
        }
        .col-sm-1 {
            width: 8.33333333%;
            margin-left: 50%;
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
        <form class="form-horizontal" role="form" action="<%= request.getContextPath()%>/admin/editNewsSub" method="post">
            <div class="form-group">
                <label for="title" class="col-sm-2 control-label">标题</label>
                <div class="col-sm-9">
                    <input type="hidden" name="nid" value="${notice.nid}">
                    <input type="text" class="form-control" id="title" name="title"  value="${notice.title}" required>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">内容</label>
                <div class="col-sm-9">
                    <textarea class="form-control" id="content" name="content" rows="10"  required>${notice.content}</textarea>
                </div>
            </div>
            <div class="form-group">
                <%--<label for="inputfile" class="col-sm-2 control-label">插入图片</label>--%>
                <%--<div class="col-sm-4">--%>
                    <%--<input type="file" id="inputfile">--%>
                <%--</div>--%>
                <div class="col-sm-1" >
                    <button type="submit" class="btn btn-primary" onclick="return confirmAct()">发布</button>
                </div>
            </div>
        </form>
    </div>
    <!-- /.panel -->
</div>
<!-- jQuery -->
<script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%= request.getContextPath()%>/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%= request.getContextPath()%>/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%= request.getContextPath()%>/dist/js/sb-admin-2.js"></script>
<script src="<%=request.getContextPath()%>/layui/layui.js"></script>

</body>
</html>
