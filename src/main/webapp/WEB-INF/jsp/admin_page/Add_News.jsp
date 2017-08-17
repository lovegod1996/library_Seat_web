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
    <style type="text/css">
        body {
            width: 90%;
            margin: 5%;
            background-color: #eff3f8;
            margin-top: 15px;
        }
        .panel-default>.panel-heading {
            color: #5c9bd1;
            background-color: #ffffff;
            border-color: #f9f9f9;
        }
        .btn-block {
            display: block;
            width: 100px;
            margin: 30px auto;
            background-color: #8895a9;
        }
        .layui-input-block {
            margin: 20px 20px 20px 110px;
            min-height: 36px;
        }
    </style>
</head>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <i class="fa fa-bar-chart-o fa-fw"></i> 发布通知
    </div>

    <div style="margin: 30px;font-size:14px;line-height: 150%;color: #757575;">
        <h4 style="color:#5c9bd1;margin-left:-10px;margin-bottom: 10px;">使用规则</h4>
        1、谨慎操作、数据无价
        <br>2、请按照要求输入相关的内容
        <br>3、本公告不提供图片输入，请正确操作。
    </div><hr>
    <!-- /.panel-heading -->
    <div class="table table-condensed">
        <form class="layui-form" role="form" action="<%= request.getContextPath()%>/admin/adNewsSub" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-block">
                    <input type="text" name="title" id="title" required autocomplete="off" placeholder="请输入标题"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">内容</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容,并确保内容总字数小于一千字" name="content" class="layui-textarea" required rows="10"></textarea>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <button type="submit" class="btn btn-primary" style="margin-left:50%;">发布</button>
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
