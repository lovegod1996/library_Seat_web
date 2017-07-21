<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/21
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
</head>
<body>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">根据身份传值</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <table class="layui-table" id="tb1">
                <thead>
                <tr>
                    <th>楼层</th>
                    <th>入馆次数</th>
                    <th>使用时间</th>
                    <th>使用率</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>当前楼层</td>
                    <td>585</td>
                    <td>200</td>
                    <td>80%</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
