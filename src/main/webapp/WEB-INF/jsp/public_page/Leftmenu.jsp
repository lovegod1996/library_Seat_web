<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>leftMenu</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<%= request.getContextPath()%>/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%= request.getContextPath()%>/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%= request.getContextPath()%>/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">
    <style type="text/css">
        body {
            background-color: #F8F8F8;
        }
    </style>
</head>
<body>
<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">

            <li>
                <a href="<%=request.getContextPath()%>/jsp/index_Admin" target="_parent" class="active"><i
                        class="fa fa-dashboard fa-fw"></i> 后台管理首页</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-hdd-o fa-fw"></i> 预约管理<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="<%=request.getContextPath()%>/jsp/seat_In_Book" target="mainFrame_Admin">当前入座</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/jsp/seat_In_Use" target="mainFrame_Admin">当前入座</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/jsp/seat_In_Empty" target="mainFrame_Admin">当前空闲</a>
                    </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>
            <li>
                <a href="#"><i class="fa fa-puzzle-piece fa-fw"></i> 座位管理</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-user fa-fw"></i> 用户管理</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-bell fa-fw"></i> 通知管理<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="<%=request.getContextPath()%>/jsp/news_List_Admin" target="mainFrame_Admin">通知列表</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/jsp/add_News" target="mainFrame_Admin">发布通知</a>
                    </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>
            <li>
                <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 数据统计</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-gear fa-fw"></i> 设置时间参数</a>
            </li>
        </ul>
    </div>
    <!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->

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
