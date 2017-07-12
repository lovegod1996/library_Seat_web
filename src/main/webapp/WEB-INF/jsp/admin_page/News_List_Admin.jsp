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
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
</head>
<body>
<div class="col-sm-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i> 通知列表
        </div>
        <!-- /.panel-heading -->
        <div class="layui-form">
            <table class="layui-table">
                <colgroup>
                    <col width="7%">
                    <col width="8%">
                    <col width="45%">
                    <col width="20%">
                    <col width="20%">
                </colgroup>
                <thead>
                <tr>
                    <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
                    <th>序号</th>
                    <th>标题</th>
                    <th>发布时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input type="checkbox" name="" lay-skin="primary"></td>
                    <td>1</td>
                    <td>123</td>
                    <td>123</td>
                    <td>
                        <div class="layui-btn-group">
                            <button class="layui-btn ">编辑</button>
                            <button class="layui-btn">删除</button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="" lay-skin="primary"></td>
                    <td>1</td>
                    <td>123</td>
                    <td>123</td>
                    <td>
                        <div class="layui-btn-group">
                            <button class="layui-btn ">编辑</button>
                            <button class="layui-btn">删除</button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="" lay-skin="primary"></td>
                    <td>1</td>
                    <td>123</td>
                    <td>123</td>
                    <td>
                        <div class="layui-btn-group">
                            <button class="layui-btn ">编辑</button>
                            <button class="layui-btn">删除</button>
                        </div>
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

<script src="<%=request.getContextPath()%>/layui/layui.js"></script>
<script>
    layui.use('form', function () {
        var $ = layui.jquery, form = layui.form();

        //全选
        form.on('checkbox(allChoose)', function (data) {
            //language=JQuery-CSS
            var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
            child.each(function (index, item) {
                item.checked = data.elem.checked;
            });
            form.render('checkbox');
        });

    });
</script>
</body>
</html>
