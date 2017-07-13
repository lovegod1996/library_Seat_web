<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>图书馆座位预约系统</title>
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
            margin-left: 2%;
        }
    </style>
</head>
<body>

<div id="wrapper">

    <div class="col-sm-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-bar-chart-o fa-fw"></i> 座位实时使用状态
                <div class="pull-right">
                    <FORM METHOD=POST ACTION="" name="selectform">
                        <SELECT NAME="building" onChange="getCity()">
                            <OPTION VALUE="南楼">南楼 </OPTION>
                            <OPTION VALUE="北楼">北楼 </OPTION>
                        </SELECT>
                    </FORM>
                </div>
            </div>
            <!-- /.panel-heading -->
            <!-- EChart 显示各楼层座位状态-->
            <div id="main" style="width: 600px;height:400px;"></div>
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-sm-8 -->

    <div class="col-sm-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-bell fa-fw"></i> 公告
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body" style="height: 400px">
                <div class="list-group">

                    <c:forEach items="${newsTop}" var="newss">
                        <a href="<%=request.getContextPath()%>/jsp/news_Content?nid=${newss.nid}"
                           class="list-group-item">${newss.title}
                            <span class="pull-right text-muted small"><em><fmt:formatDate value="${newss.creattime}" pattern="yyyy-MM-dd HH:mm:ss"/></em></span>
                        </a>
                    </c:forEach>

                </div>
                <!-- /.list-group -->
                <a href="<%=request.getContextPath()%>/jsp/news_List_Admin" target="mainFrame_Admin"
                   class="btn btn-default btn-block">查看更多</a>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-4 -->

</div>
<!-- /#wrapper -->

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
