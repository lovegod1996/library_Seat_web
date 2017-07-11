<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp" target="_parent" style="color:#337AB7">用户首页</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i><span>小明</span>
                </a>
                <!-- /.dropdown-user -->
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-gear fa-fw"></i><span>设置</span>
                </a>
                <!-- /.dropdown-user -->
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-power-off fa-fw"></i><span>退出</span>
                </a>
                <!-- /.dropdown-user -->
            </li>

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
