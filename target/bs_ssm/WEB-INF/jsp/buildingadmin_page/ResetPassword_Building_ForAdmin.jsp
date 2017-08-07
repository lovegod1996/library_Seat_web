<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/8/7
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重置密码</title>
    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <%--验证表单--%>
    <script src="<%=request.getContextPath()%>/js/checkForm.js"></script>

    <style type="text/css">
        body {
            width: 50%;
            margin-left: 20%;
        }

        form {
            margin-top: 50px;
        }

        button {
            width: 100px;
            margin-left: 30%;
            margin-top: 50px;
        }

        #errmsg {
            margin-top: 10px;
            width: 360px;
            display: none
        }

        .layui-inline {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">重置密码</li>
    </ul>
    <div class="layui-tab-content">
        <%--Tip  一页--%>
        <div class="layui-tab-item layui-show">
            <form class="form-horizontal" onsubmit="return checkSetPassword_ByOldpassword()" action="<%= request.getContextPath()%>/view/setNewPassSub" method="post">
                <div class="layui-inline">
                    <label class="layui-form-label">旧密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="oldPwd" id="oldPwd" class="layui-input" required style="width: 250px">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">新密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="newPwd" id="newPwd" class="layui-input" style="width: 250px">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">再次输入</label>
                    <div class="layui-input-inline">
                        <input type="password" name="newPwd" id="newRePwd" class="layui-input" style="width: 250px">
                    </div>
                </div>
                <div id="errmsg">
                    <label class="layui-form-label" style="color: #FF6838">错误提示</label>
                    <label class="layui-form-label" style="text-align: left;color: #FF6838;width: 100px">两次输入不一致</label>
                </div>
                <div class="layui-inline" style="width: 360px;text-align: center">
                    <button class="layui-btn" onclick="checkSetPassword_ByOldpassword()">确定</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
