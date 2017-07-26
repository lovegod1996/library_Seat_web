<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
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
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">

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
                        <a href="<%=request.getContextPath()%>/jsp/seat_In_Book" target="mainFrame_Admin">当前预约</a>
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
                <a href="#"><i class="fa fa-puzzle-piece fa-fw"></i> 座位管理<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="<%=request.getContextPath()%>/view/managing_Seat" target="mainFrame_Admin">增加座位</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/view/floorSeat" target="mainFrame_Admin">查看座位</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/view/managing_Floor" target="mainFrame_Admin">开闭馆管理</a>
                    </li>
                </ul>
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
                <a href="#"><i class="fa fa-bell fa-fw"></i> 数据统计<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="<%=request.getContextPath()%>/view/study_DataStatistics"
                           target="mainFrame_Admin">学习情况统计</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/view/seat_DataStatistics"
                           target="mainFrame_Admin">座位使用统计</a>
                    </li>
                </ul>
            </li>
            <li style="height:100%;margin-top: 50px;padding-left: 10px;">
                <div class="layui-form">
                    <div class="layui-form-item">
                        <label type="text">图书馆：&nbsp;&nbsp;${building.employer}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">阅览室：&nbsp;&nbsp;${sessionScope.admin.employer}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">姓名：&nbsp;&nbsp;${sessionScope.admin.name}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">账号：&nbsp;&nbsp;${sessionScope.admin.accountnumber}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">楼层状态：&nbsp;&nbsp;${sessionScope.admin.statue==0?"开放":"关闭"}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">今天开放时间：&nbsp;&nbsp;${opentoday.param1};${opentoday.param2}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">该层座位：&nbsp;&nbsp;${seatSize}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">当前已入座：&nbsp;&nbsp;${inSeat}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">未预约：&nbsp;&nbsp;${canbook}</label>
                    </div>
                </div>
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
