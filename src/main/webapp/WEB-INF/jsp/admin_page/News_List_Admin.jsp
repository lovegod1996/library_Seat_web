<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">

        .table-condensed {
            padding-left: 20px;
            padding-right: 20px;
        }
    </style>
</head>
<body>
<div class="col-sm-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i> 通知列表
        </div>
        <!-- /.panel-heading -->
        <div class="table table-condensed">
            <table class="table">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>标题</th>
                    <th>发布时间</th>
                    <th>修改</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td><a href="<%=request.getContextPath()%>/jsp/news_Content" target="mainFrame_Admin">我是标题</a></td>
                    <td>2017年7月11日</td>
                    <td>
                        <button type="button" class="btn btn-primary btn-sm">编辑</button>
                        <button type="button" class="btn btn-danger btn-sm">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td><a href="<%=request.getContextPath()%>/jsp/news_Content" target="mainFrame_Admin">我是标题</a></td>
                    <td>2017年7月11日</td>
                    <td>
                        <button type="button" class="btn btn-primary btn-sm">编辑</button>
                        <button type="button" class="btn btn-danger btn-sm">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td><a href="<%=request.getContextPath()%>/jsp/news_Content" target="mainFrame_Admin">我是标题</a></td>
                    <td>2017年7月11日</td>
                    <td>
                        <button type="button" class="btn btn-primary btn-sm">编辑</button>
                        <button type="button" class="btn btn-danger btn-sm">删除</button>
                    </td>
                </tr>
                </tbody>
            </table>
            <div style="text-align: center">
                <ul class="pagination">
                    <li><a href="#">&laquo;</a></li>
                    <li class="active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">&raquo;</a></li>
                </ul>
            </div>

        </div>
    </div>
    <!-- /.panel -->
</div>
<!-- /.col-sm-12 -->
<!-- jQuery -->
<script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%= request.getContextPath()%>/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%= request.getContextPath()%>/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%= request.getContextPath()%>/dist/js/sb-admin-2.js"></script>
</body>
</html>
