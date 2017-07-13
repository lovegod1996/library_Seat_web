<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/12
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重置密码</title>
    <script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>
    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>

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
    <p style="font-size: 30px;margin-top: 20px">重置密码</p>
</div>
<hr>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">记得旧密码</li>
        <li>手机验证码</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <form class="form-horizontal" role="form">
                <div class="layui-inline">
                    <label class="layui-form-label">旧密码</label>
                    <div class="layui-input-inline">
                        <input type="text" name="oldPwd" class="layui-input" required style="width: 250px">
                    </div>
                </div>
                <div class="layui-inline" style="margin-top: 10px">
                    <label class="layui-form-label">新密码</label>
                    <div class="layui-input-inline">
                        <input type="text" name="newPwd" class="layui-input" required style="width: 250px">
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="layui-btn">确定</button>
                </div>
            </form>
        </div>
        <div class="layui-tab-item">
            <form class="form-horizontal" role="form" name="resetform">
                <div class="layui-inline">
                    <label class="layui-form-label">验证手机</label>
                    <div class="layui-input-inline">
                        <input type="tel" name="phone" class="layui-input" required style="width: 250px">
                    </div>
                </div>
                <div class="form-group" style="margin-top: 10px">
                    <label class="col-sm-3 layui-form-label">验证码</label>
                    <div class="col-sm-7">
                        <input type="text" class="layui-input" id="code" placeholder="输入" style="width: 120px;float: left" required>
                        <input type="button" class="layui-btn layui-btn-primary" value="点击发送验证码" style="width: 120px;float: left;margin-left: 10px"
                               onclick="sendCode(this)"/>
                    </div>
                </div>
                <button type="submit" class="layui-btn">确定</button>
            </form>
        </div>
    </div>
</div>

<script>
    layui.use('element', function () {
        var $ = layui.jquery
            , element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块

        //触发事件
        var active = {
            tabChange: function () {
                //切换到指定Tab项
                element.tabChange('demo', '22'); //
            }
        };

        $('.site-demo-active').on('click', function () {
            var othis = $(this), type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
        });

        //Hash地址的定位
        var layid = location.hash.replace(/^#test=/, '');
        element.tabChange('test', layid);

        element.on('tab(test)', function (elem) {
            location.hash = 'test=' + $(this).attr('lay-id');
        });

    });
</script>

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