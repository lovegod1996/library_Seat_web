<%@ taglib prefix="target" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
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
        .navbar-default .navbar-nav>li>a:hover {
            color: #fff;
            background-color: transparent;
        }
    </style>

    <script>
        function show() {
            document.getElementById("today").style.display="block";
            document.getElementById("tomorrow").style.display="block";
        }
    </script>
</head>
<body>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <img src="<%=request.getContextPath()%>/img/ZZTI.jpg" class="img-circle" style="width: 50px;height: 50px">
        </div>
        <div class="navbar-header">
            <a class="navbar-brand" href="#">图书馆座位预约系统</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="<%=request.getContextPath()%>/" target="_parent">首页</a></li>

                <c:if test="${sessionScope.user !=null}">
                    <li><a href="#" onclick="show()">预约</a></li>
                </c:if>
                <li id="today" style="display: none;"><a href="<%=request.getContextPath()%>/view/choose_Building?day=0" target="mainFrame_User" style="color:#ffffff ">预约今天</a></li>
                <li id="tomorrow" style="display: none;"><a href="<%=request.getContextPath()%>/view/choose_Building?day=1" target="mainFrame_User" style="color:#ffffff ">预约明天</a></li>
            </ul>
        </div>

        <ul class="nav navbar-top-links navbar-right">
            <c:choose>
                <c:when test="${empty sessionScope.user && empty sessionScope.admin}">
                    <li class="dropdown">
                        <a href="<%=request.getContextPath()%>/jsp/login" target="_parent">
                            <i class="fa fa-user fa-fw"></i><span>登录</span>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <c:if test="${sessionScope.user !=null}">
                        <li class="dropdown">
                            <a class="dropdown-toggle" href="<%=request.getContextPath()%>/view/information_User"
                               target="mainFrame_User">
                                <i class="fa fa-user fa-fw"></i><span>${sessionScope.user.name}</span>
                            </a>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" href="${pageContext.request.contextPath }/admin/loginOut"
                               target="_parent">
                                <i class="fa fa-power-off fa-fw"></i><span>退出</span>
                            </a>
                            <!-- /.dropdown-user -->
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.admin !=null}">
                        <li class="dropdown">
                            <a class="dropdown-toggle" href="<%=request.getContextPath()%>/jsp/index_Admin"
                               target="_blank">
                                <i class="fa fa-user fa-fw"></i><span>${sessionScope.admin.name}</span>
                            </a>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" href="${pageContext.request.contextPath }/admin/loginOut"
                               target="_parent">
                                <i class="fa fa-power-off fa-fw"></i><span>退出</span>
                            </a>
                            <!-- /.dropdown-user -->
                        </li>
                    </c:if>
                </c:otherwise>
            </c:choose>

        </ul>
    </nav>
</div>
<div>this is a footer</div>
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
