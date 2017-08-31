<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/11
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>场馆开放查看</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        body{
            width: 100%;
            background-color: #eff3f8;
            margin-top: 15px;
        }
        .table-condensed{
            padding-left: 20px;
            padding-right: 20px;
        }
        .panel-default>.panel-heading {
            color: #5c9bd1;
            background-color: #ffffff;
            border-color: #f9f9f9;
        }
    </style>
</head>
<body>
<div class="col-sm-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i> 各场馆开放时间段查看
        </div>
        <!-- /.panel-heading -->
        <div class="table table-condensed">
            <table class="table">
                <caption style="text-align: center"><h3><fmt:formatDate value="${now}" pattern="yyyy年MM月dd日"/>开放场馆安排 </h3></caption>
                <tbody>
                <c:forEach items="${weekopens}" var="week">
                    <tr>
                        <td>
                            <table class="table">
                                <tbody>
                                <tr style="font-weight: bold;font-size: large">
                                    <td colspan="2"><a href="<%=request.getContextPath()%>/jsp/book_Seat_User?fid=${week.lid}&day=${0}">${week.floor}</a> </td>
                                    <td></td>
                                    <td colspan="2" style="text-align: center">${week.param1};${week.param2}</td>
                                </tr>
                                <tr style="font-size: smaller;color: #9d9d9d">
                                    <td>${week.building}</td>
                                    <td>F${week.fl}</td>
                                    <td>共${week.allSeat}座</td>
                                    <td>女生比例：${week.women}%</td>
                                    <td>座位使用率：<strong>${week.userPro}%</strong></td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
    <!-- /.panel -->
</div>
<!-- /.col-sm-12 -->
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
