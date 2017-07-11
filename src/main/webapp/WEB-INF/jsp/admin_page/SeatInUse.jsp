<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="col-sm-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i> 座位实时使用状态
            <div class="pull-right">
                <div class="btn-group">
                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                        南楼
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu pull-right" role="menu">
                        <li><a href="#">北楼</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- /.panel-heading -->
        <div class="table table-condensed">
            <table class="table">
                <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>学院</th>
                    <th>专业</th>
                    <th>班级</th>
                    <th>座位号</th>
                    <th>预约时间</th>
                    <th>使用状态</th>
                    <th>修改</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>小明</td>
                    <td>计算机学院</td>
                    <td>软件工程</td>
                    <td>141班</td>
                    <td>S2-0101</td>
                    <td>9:00-12:00</td>
                    <td>使用状态</td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm">释放</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>小明</td>
                    <td>计算机学院</td>
                    <td>软件工程</td>
                    <td>141班</td>
                    <td>S2-0101</td>
                    <td>9:00-12:00</td>
                    <td>预约状态</td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm">释放</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>小明</td>
                    <td>计算机学院</td>
                    <td>软件工程</td>
                    <td>141班</td>
                    <td>S2-0101</td>
                    <td>9:00-12:00</td>
                    <td>临时离开</td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm">释放</button>
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
</body>
</html>
