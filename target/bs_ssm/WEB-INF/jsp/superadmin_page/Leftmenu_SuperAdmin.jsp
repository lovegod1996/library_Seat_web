<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/19
  Time: 15:35
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
                <a href="<%=request.getContextPath()%>/view/index_SuperAdmin" target="_parent" class="active"><i
                        class="fa fa-dashboard fa-fw"></i> 后台管理首页</a>
            </li>
            <c:if test="${sessionScope.admin.admin==1}">
                <li>
                    <a href="<%=request.getContextPath()%>/view/manage_Admin"
                       target="mainFrame_SuperAdmin"><i class="fa fa-hdd-o fa-fw"></i> 管理员管理</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.admin.floor==1}">
                <li>
                    <a href="<%=request.getContextPath()%>/view/managing_Building_SuperAdmin"
                       target="mainFrame_SuperAdmin"><i class="fa fa-hdd-o fa-fw"></i> 创建楼和楼层</a>
                </li>
            </c:if>

            <c:if test="${sessionScope.admin.seat==1}">
                <li>
                    <a href="#"><i class="fa fa-bell fa-fw"></i> 座位管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">

                        <c:forEach items="${buidingfloors}" var="building">
                            <li>
                                <a href="#">${building.employer}<span class="fa arrow"></span></a>
                                <ul class="nav">
                                    <c:forEach items="${building.floors}" var="floor">
                                        <li>
                                            <a href="#">${floor.employer}<span class="fa arrow"></span></a>
                                            <ul class="nav">
                                                <li>
                                                    <a href="<%=request.getContextPath()%>/view/managing_Seat?fid=${floor.fid}"
                                                       target="mainFrame_SuperAdmin">增加座位</a>
                                                </li>
                                                <li>
                                                    <a href="<%=request.getContextPath()%>/view/floorSeatsList?fid=${floor.fid}"
                                                       target="mainFrame_SuperAdmin">查看座位</a>
                                                </li>
                                                <li>
                                                    <a href="<%=request.getContextPath()%>/view/managing_Floor?fid=${floor.fid}"
                                                       target="mainFrame_SuperAdmin">开闭馆管理</a>
                                                </li>
                                            </ul>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </c:if>

            <c:if test="${sessionScope.admin.user==1}">
                <li>
                    <a href="<%=request.getContextPath()%>/view/managing_Users"
                       target="mainFrame_SuperAdmin"><i class="fa fa-hdd-o fa-fw"></i> 用户管理</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.admin.notice==1}">
                <li>
                    <a href="#"><i class="fa fa-bell fa-fw"></i> 通知管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="<%=request.getContextPath()%>/jsp/news_List_Admin"
                               target="mainFrame_SuperAdmin">通知列表</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/jsp/add_News" target="mainFrame_SuperAdmin">发布通知</a>
                        </li>
                    </ul>
                </li>
            </c:if>
            <li>
                <a href="#"><i class="fa fa-bell fa-fw"></i> 数据统计<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="<%=request.getContextPath()%>/view/intofindStudent"
                           target="mainFrame_SuperAdmin">学生预约情况查询</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/view/study_DataStatistics_SuperAdmin"
                           target="mainFrame_SuperAdmin">院系学习情况统计</a>
                    </li>
                    <li>
                        <a href="#">场馆学习情况<span class="fa arrow"></span></a>
                        <ul class="nav">
                            <c:forEach items="${buidingfloors}" var="building">
                                <li>
                                    <a href="#">${building.employer}<span class="fa arrow"></span></a>
                                    <ul class="nav">
                                        <c:forEach items="${building.floors}" var="floor">
                                            <li>
                                                <a href="<%=request.getContextPath()%>/view/seat_DataStatistics_ForEachBuilding?fid=${floor.fid}"
                                                   target="mainFrame_SuperAdmin">${floor.employer}</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
            </li>
            <li style="height:100%;margin-top: 50px;padding-left: 10px;">
                <div class="layui-form">
                    <div class="layui-form-item">
                        <label type="text">职责：&nbsp;&nbsp;${sessionScope.admin.employer}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">姓名：&nbsp;&nbsp;${sessionScope.admin.name}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">账号：&nbsp;&nbsp;${sessionScope.admin.acountnumber}</label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">您的身份：&nbsp;&nbsp;
                            <c:if test="${sessionScope.admin.admin==1}">
                                您是超级管理员
                            </c:if>
                            <c:if test="${sessionScope.admin.admin==0}">
                                您是一般管理员
                            </c:if>
                        </label>
                    </div>
                    <div class="layui-form-item">
                        <label type="text">您的管理权限：&nbsp;&nbsp;
                            <c:if test="${sessionScope.admin.admin==1}">
                                管理员管理；
                            </c:if>
                            <c:if test="${sessionScope.admin.notice==1}">
                                资讯管理；
                            </c:if>
                            <c:if test="${sessionScope.admin.floor==1}">
                                楼层管理；
                            </c:if>
                            <c:if test="${sessionScope.admin.user==1}">
                                学生管理；
                            </c:if>
                            <c:if test="${sessionScope.admin.seat==1}">
                                座位管理
                            </c:if>
                        </label>
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

