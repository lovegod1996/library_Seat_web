<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Header</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<%= request.getContextPath()%>/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%= request.getContextPath()%>/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%= request.getContextPath()%>/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">
    <style>
        body {
            padding-left: 5%;
            padding-right: 5%;
        }
        body {
            background-color: #4d5b69;
        }
        .navbar-default {
            background-color: #4d5b69;
            border-color: #e7e7e7;
        }
        .navbar-default .navbar-brand {
            color: #fff;
        }
        .navbar-default .navbar-nav>.active>a, .navbar-default .navbar-nav>.active>a:focus, .navbar-default .navbar-nav>.active>a:hover {
            color: #4d5b69;
            background-color: #4d5b69;
        }
        html {
            font-size: 10px;
            background-color: #eff3f8;
        }
        .navbar-default .navbar-brand:hover {
            color: #ffffff;
            background-color: transparent;
        }
        .navbar-default .navbar-nav>.active>a, .navbar-default .navbar-nav>.active>a:focus, .navbar-default .navbar-nav>.active>a:hover {
            color: #ffffff;
            background-color: #4d5b69;
        }
        a {
            color: #ffffff;
            text-decoration: none;
        }
        .navbar-default .navbar-nav>li>a {
            color: #fff;
        }
        .navbar-default .navbar-nav>li>a:hover {
            color: #fff;
        }
    </style>
</head>
<body>
<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <img src="<%=request.getContextPath()%>/img/ZZTI.jpg" class="img-circle" style="width: 50px;height: 50px">
        </div>
        <div class="navbar-header">
            <a class="navbar-brand" href="<%=request.getContextPath()%>/jsp/index_Admin" target="_parent">图书馆座位预约系统</a>
            <a class="navbar-brand" href="<%=request.getContextPath()%>/" target="_parent">用户首页</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">

            <c:choose>
                <c:when test="${empty sessionScope.admin}">
                    <li class="dropdown">
                        <a class="dropdown-toggle" href="${pageContext.request.contextPath }/jsp/login"
                           target="_parent">
                            <i class="fa fa-user fa-fw"></i><span>登录</span>
                        </a>
                        <!-- /.dropdown-user -->
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="dropdown">
                        <a class="dropdown-toggle" href="#">
                            <i class="fa fa-user fa-fw"></i><span>${sessionScope.admin.name}</span>
                        </a>
                    </li>
                    <li class="dropdown">
                            <%--
                            判断管理员类型，修改target=""
                            层管理员：target="mainFrame_Admin"
                            楼管理员：target="main_BuildingAdmin"
                            系统管理员：target="mainFrame_SuperAdmin"
                            --%>
                        <c:if test="${sessionScope.admintype==1}">
                            <a class="dropdown-toggle" href="<%=request.getContextPath()%>/view/resetPassword_ForAdmin"
                               target="mainFrame_Admin">
                                <i class="fa fa-gear fa-fw"></i><span>重置密码</span>
                            </a>
                        </c:if>
                        <c:if test="${sessionScope.admintype==2}">
                            <a class="dropdown-toggle" href="<%=request.getContextPath()%>/view/resetPassword_ForAdmin"
                               target="main_BuildingAdmin">
                                <i class="fa fa-gear fa-fw"></i><span>重置密码</span>
                            </a>
                        </c:if>
                        <c:if test="${sessionScope.admintype==3}">
                            <a class="dropdown-toggle" href="<%=request.getContextPath()%>/view/resetPassword_ForAdmin"
                               target="mainFrame_SuperAdmin">
                                <i class="fa fa-gear fa-fw"></i><span>重置密码</span>
                            </a>
                        </c:if>

                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" href="${pageContext.request.contextPath }/admin/loginOut"
                           target="_parent">
                            <i class="fa fa-power-off fa-fw"></i><span>退出</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
        <!-- /.navbar-top-links -->
    </nav>
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
