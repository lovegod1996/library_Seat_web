<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/20
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            top: 35%;
            left: 32%;
            width: 35%;
            height: 30%;
            padding: 16px;
            border: 1px solid orange;
            background-color: white;
            z-index: 1002;
            overflow: auto;
        }
    </style>

</head>
<body>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this" id="libraryname">中原工学院南区图书馆</li>
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
                    <tr>
                        <td>1</td>
                        <td>南一</td>
                        <td>Admin</td>
                        <td>
                            <div class="layui-btn-group">
                                <button type="button" class="layui-btn layui-btn-small" onclick="document.getElementById('light1').style.display='block';
                                        document.getElementById('fade').style.display='block';">编辑
                                </button>
                                <button type="button" class="layui-btn layui-btn-small">删除</button>
                            </div>
                        </td>
                    </tr>
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
                <input type="text" placeholder="请输入" class="layui-input" required>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">添加管理员</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入" class="layui-input" required>
            </div>
        </div>
        <div class="layui-form-item">
            <button type="submit" class="layui-btn">确认</button>
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
                <input type="text" id="floorname" class="layui-input" required>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">添加管理员</label>
            <div class="layui-input-inline">
                <input type="text" id="flooradmin" class="layui-input" required>
            </div>
        </div>
        <div class="layui-form-item">
            <button type="submit" class="layui-btn">确认</button>
        </div>
    </form>
</div>
<div id="fade" class="black_overlay"></div>
</body>
</html>
