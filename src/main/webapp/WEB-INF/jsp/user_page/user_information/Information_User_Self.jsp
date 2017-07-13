<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/13
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的信息</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<%= request.getContextPath()%>/dist/css/sb-admin-2.css" rel="stylesheet">
    <script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>
    <script src="<%= request.getContextPath()%>/vendor/bootstrap/js/bootstrap.min.js"></script>
    <style type="text/css">
        body {
            width: 60%;
            margin-left: 20%;
            background-color: #ffffff;
        }

        .user_img {
            margin-left: 20px;
            margin-top: 20px;
            width: 100px;
            height: 150px;
            float: left;
        }

        .user_msg {
            margin-left: 100px;
            margin-top: 20px;
            width: 200px;
            height: 150px;
            float: left;
        }

        img {
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <div class="user_img">
        <img src="<%=request.getContextPath()%>/img/ZZTI.jpg">
    </div>
    <div class="user_msg">
        <label class="control-label">姓名</label><label class="control-label" style="margin-left: 20px">小明</label><br>
        <label class="control-label">学号</label><label class="control-label"
                                                      style="margin-left: 20px">201400834117</label><br>
        <label class="control-label">学院</label><label class="control-label" style="margin-left: 20px">计算机学院</label><br>
        <label class="control-label">专业</label><label class="control-label" style="margin-left: 20px">软件工程</label><br>
        <label class="control-label">班级</label><label class="control-label" style="margin-left: 20px">141</label><br>
    </div>
    <div class="col-lg-12">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>学习记录（次）</th>
                <th>时间统计（小时）</th>
                <th>失信记录（次）</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>5</td>
                <td>20</td>
                <td>0</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
