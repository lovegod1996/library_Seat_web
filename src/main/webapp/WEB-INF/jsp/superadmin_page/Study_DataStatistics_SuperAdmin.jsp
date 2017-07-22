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
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>
                        <a href="<%=request.getContextPath()%>/view/study_DataStatistics_ForEachBuilding"
                           target="mainFrame_SuperAdmin">中原工学院南区图书馆</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>