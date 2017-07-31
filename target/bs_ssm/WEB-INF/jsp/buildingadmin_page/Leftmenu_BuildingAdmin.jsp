<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/19
  Time: 14:20
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
                <a href="<%=request.getContextPath()%>/view/index_BuildingAdmin" target="_parent" class="active"><i
                        class="fa fa-dashboard fa-fw"></i> 后台管理首页</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/view/managing_Floor_BuildingAdmin"
                   target="mainFrame_BuildingAdmin"><i class="fa fa-hdd-o fa-fw"></i> 开闭馆管理</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-bell fa-fw"></i> 通知管理<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="<%=request.getContextPath()%>/jsp/news_List_Admin" target="mainFrame_BuildingAdmin">通知列表</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/jsp/add_News" target="mainFrame_BuildingAdmin">发布通知</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#"><i class="fa fa-bell fa-fw"></i> 数据统计<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="<%=request.getContextPath()%>/view/study_DataStatistics_ForEachBuilding"
                           target="mainFrame_BuildingAdmin">学习情况统计</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/view/seat_DataStatistics_ForEachBuilding"
                           target="mainFrame_BuildingAdmin">座位使用统计</a>
                    </li>
                </ul>
            </li>
            <li style="height:100%;margin-top: 50px;padding-left: 10px;">
                <div class="layui-form">
                    <div class="layui-form-item">
                        <label type="text">场馆：&nbsp;&nbsp;${sessionScope.admin.employer}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">姓名：&nbsp;&nbsp;${sessionScope.admin.name}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">账号：&nbsp;&nbsp;${sessionScope.admin.accountnumber}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">管理阅览室：&nbsp;&nbsp;${floors}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">图书馆座位：&nbsp;&nbsp;${seatSize}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">当前使用：&nbsp;&nbsp;${seatnow}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">可预约：&nbsp;&nbsp;${noseat}</label>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>

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

