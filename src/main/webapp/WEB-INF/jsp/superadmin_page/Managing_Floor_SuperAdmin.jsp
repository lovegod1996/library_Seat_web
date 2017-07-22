<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/20
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>

    <!-- jQuery -->
    <script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

    <style>
        .black_overlay {
            display: none;
            position: absolute;
            top: 0%;
            left: 0%;
            width: 100%;
            height: 100%;
            background-color: black;
            z-index: 1001;
            -moz-opacity: 0.8;
            opacity: .80;
            filter: alpha(opacity=80);
        }

        .white_content {
            display: none;
            position: absolute;
            top: 20%;
            left: 32%;
            width: 400px;
            height: 250px;
            padding: 16px;
            border: 1px solid orange;
            background-color: white;
            z-index: 1002;
            overflow: auto;
        }
    </style>

    <script language="javascript">
        function getTableContent(node) {
            // 按钮的父节点的父节点是tr。
            var tr1 = node.parentNode.parentNode;
            //alert(tr1.rowIndex);获得行
            //alert(tb1.rows[tr1.rowIndex].cells[1].getElementsByTagName("INPUT")[0].value);
            document.getElementById("floorname").value= tb1.rows[tr1.rowIndex].cells[1].innerHTML;
            document.getElementById("flooradmin").value = tb1.rows[tr1.rowIndex].cells[2].innerHTML;
        }
    </script>

</head>
<body>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this" id="libraryname">${building.employer}</li>
        <li>
            <button class="layui-btn layui-btn-small" href="javascript:void(0)"
                    onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">
                <i class="layui-icon"></i>添加楼层
            </button>
        </li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <div class="layui-form">
                <table class="layui-table" id="tb1">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>楼层</th>
                        <th>管理员</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${floors}" var="floor" varStatus="step">
                        <tr>
                            <td>${step.index}</td>
                            <td>${floor.employer}</td>
                            <td>${floor.accountnumber}</td>
                            <td>
                                <button type="button" class="layui-btn layui-btn-small" onclick="document.getElementById('light1').style.display='block';
                                        document.getElementById('fade').style.display='block';getTableContent(this)">编辑
                                </button>
                                <a href="<%=request.getContextPath()%>/view/deletefloor?bid=${building.bid}&&fid=${floor.fid}" class="layui-btn layui-btn-small">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%--添加弹出框--%>
<div id="light" class="white_content">
    <div style="text-align: end;width: 100%">
        <a class="layui-icon" href="javascript:void(0)"
           onclick="document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">
            &#x1006;</a>
    </div>
    <form class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">添加楼层</label>
            <div class="layui-input-inline">
                <input placeholder="请输入" class="layui-input" required>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">添加管理员</label>
            <div class="layui-input-inline">
                <input placeholder="请输入" class="layui-input" required>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"></label>
            <div class="layui-input-inline">
                <label style="color: #777777">默认密码为 123456</label>
            </div>
        </div>
        <div class="layui-form-item">
            <button class="layui-btn" style="margin:20px 0 0 45%">确认</button>
        </div>
    </form>
</div>

<%--编辑弹出框--%>
<div id="light1" class="white_content">
    <div style="text-align: end;width: 100%">
        <a class="layui-icon" href="javascript:void(0)"
           onclick="document.getElementById('light1').style.display='none';document.getElementById('fade').style.display='none'">
            &#x1006;</a>
    </div>
    <form class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">编辑楼层</label>
            <div class="layui-input-inline">
                <input id="floorname" class="layui-input" required>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">编辑管理员</label>
            <div class="layui-input-inline">
                <input id="flooradmin" class="layui-input" required>
            </div>
        </div>
        <div class="layui-form-item">
            <button class="layui-btn" style="margin:20px 0 0 45%">确认</button>
        </div>
    </form>
</div>
<div id="fade" class="black_overlay"></div>
</body>
</html>
