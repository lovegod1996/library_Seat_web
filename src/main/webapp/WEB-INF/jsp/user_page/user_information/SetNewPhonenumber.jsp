<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/13
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重置手机号</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

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
    <h2>重置手机号</h2>
</div>
<hr>
<form class="form-horizontal" role="form" name="resetform">
    <div class="form-group">
        <label class="col-sm-3 control-label">手机号</label>
        <div class="col-sm-7">
            <input type="text" class="form-control" id="phoneNum" placeholder="请输入" required>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">验证码</label>
        <div class="col-sm-7">
            <input type="text" class="form-control" id="code" placeholder="输入" style="width: 140px;float: left" required>
            <input type="button" class="btn btn-default" value="点击发送验证码" style="width: 125px;float: left;margin-left: 10px"
                   onclick="sendCode(this)"/>
        </div>
    </div>
    <button type="submit" class="btn btn-primary">确定</button>
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
