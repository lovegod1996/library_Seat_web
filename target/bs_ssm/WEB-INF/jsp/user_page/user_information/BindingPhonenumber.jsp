<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/13
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>绑定手机号</title>
    <script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>
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

        .content_title {
            text-align: center;
        }

        form {
            margin-top: 50px;
        }

        button {
            width: 100px;
            margin-left: 30%;
            margin-top: 50px;
        }
    </style>
</head>
<body>
<div class="content_title">
    <p style="font-size: 30px;margin-top: 20px">绑定手机号</p>
</div>
<hr>
<form class="form-horizontal" role="form" name="resetform" onsubmit="return checkForm_BindingPhonenumber()">
    <div class="layui-inline">
        <label class="layui-form-label">验证手机</label>
        <div class="layui-input-inline">
            <input type="tel" name="phone" id="checkphonenumber" class="layui-input" style="width: 250px" onblur="checkPhoneNumber()">
        </div>
    </div>
    <div class="form-group" style="margin-top: 10px">
        <label class="col-sm-3 layui-form-label">验证码</label>
        <div class="col-sm-7">
            <input type="text" class="layui-input" id="checkcode" placeholder="输入" style="width: 120px;float: left" onblur="checkCode()">
            <input type="button" class="layui-btn layui-btn-primary" value="点击发送验证码" style="width: 120px;float: left;margin-left: 10px"
                   onclick="sendCode(this)"/>
        </div>
    </div>
    <button type="submit" class="layui-btn" onclick="checkForm_BindingPhonenumber()">确定</button>
</form>

<script type="text/javascript">
    var clock = '';
    var nums = 60;
    var btn;
    function sendCode(thisBtn) {
        btn = thisBtn;
        btn.disabled = true; //将按钮置为不可点击
        btn.value = nums + '秒后重新获取';
        clock = setInterval(doLoop, 1000); //一秒执行一次
    }
    function doLoop() {
        nums--;
        if (nums > 0) {
            btn.value = nums + '秒后重新获取';
        } else {
            clearInterval(clock); //清除js定时器
            btn.disabled = false;
            btn.value = '点击发送验证码';
            nums = 10; //重置时间
        }
    }
</script>
</body>
</html>
