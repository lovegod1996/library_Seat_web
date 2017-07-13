<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/12
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>

    <style type="text/css">
        body {
            padding-top: 20px;
            line-height: 22px;
            background-color: #393D49;
            font-weight: 300;
        }

        label {
            color: #fff;
            width: 25%;
            text-align: center;
        }
        button {
            width: 50%;
            margin-top: 10%;
            margin-left: 25%;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <div class="col-sm-12">
        <form class="form-horizontal" role="form" name="resetform">
            <div class="form-group">
                <label class="control-label" style="float: left">账号</label>
                <div class="col-sm-7" style="float: left">
                    <input type="text" class="layui-input" placeholder="输入" style="width: 220px" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label" style="float: left">手机号</label>
                <div class="col-sm-7" style="float: left">
                    <input type="text" class="layui-input" placeholder="输入" style="width: 220px" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label" style="float: left">验证码</label>
                <div class="col-sm-7" style="float: left">
                    <input type="text" class="layui-input" placeholder="输入" style="width: 80px;float: left" required>
                    <input type="button" class="layui-btn" value="点击发送验证码" style="width: 130px;float: left;margin-left: 10px" onclick="sendCode(this)"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">确定</button>
        </form>
    </div>
</div>
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