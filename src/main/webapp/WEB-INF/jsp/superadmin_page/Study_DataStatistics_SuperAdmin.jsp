<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/21
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学习统计</title>
    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
</head>
<body>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">学习情况统计</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <table class="layui-table" id="tb1">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>图书馆</th>
                    <th>详情</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>中原工学院南区图书馆</td>
                    <td>
                        <a type="button" class="layui-btn layui-btn-small"
                           href="<%=request.getContextPath()%>/view/study_DataStatistics_ForEachBuilding"
                           target="mainFrame_SuperAdmin">详情</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
