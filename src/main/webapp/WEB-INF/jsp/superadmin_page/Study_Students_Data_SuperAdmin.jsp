<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/21
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>学习统计</title>
    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <!-- jQuery -->
    <script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

    <%--<script language="javascript">--%>
    <%--function getTableContent(node) {--%>
    <%--// 按钮的父节点的父节点是tr。--%>
    <%--var tr1 = node.parentNode.parentNode;--%>
    <%--//获得图书馆名称，传值至详情页面--%>
    <%--var buildingname;--%>
    <%--buildingname = tb1.rows[tr1.rowIndex].cells[1].innerText;--%>
    <%--return buildingname;--%>
    <%--}--%>
    <%--</script>--%>
    <style>
        body {
            background-color: #eff3f8;
            margin-top: 15px;
        }
        .panel-default>.panel-heading {
            color: #5c9bd1;
            background-color: #ffffff;
            border-color: #f9f9f9;
        }
        .layui-table thead tr {
            background-color: #eff3f8;
        }
    </style>
</head>
<body>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">${college}-${major}-${classes}学习情况统计</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <table class="layui-table" id="tb1">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>学号</th>
                    <th>学习时间</th>
                    <th>学习次数</th>
                    <th>失信次数</th>
                    <th>失信率</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${studentsDatas}" var="student" varStatus="step">
                    <tr>
                        <td>${step.index+1}</td>
                        <td>${student.username} </td>
                        <td>${student.sno}</td>
                        <td>${student.learntime}小时</td>
                        <td>${student.allLearn}</td>
                        <td>${student.undeal}</td>
                        <td>${student.dealpro}%</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <%--<div id="main" style="width: 100%;height:400px;"></div>--%>
        </div>
    </div>
</div>

<%--echart--%>
<script src="<%=request.getContextPath()%>/js/echarts-all.js"></script>
<script src="<%=request.getContextPath()%>/js/macarons.js"></script>

</body>
</html>