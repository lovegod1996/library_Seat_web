<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>用户登录</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="<%= request.getContextPath()%>/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]-->
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <!--[endif]-->

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default" style="margin-top: 20%">
                <div class="panel-heading">
                    <h3 class="panel-title">学生登录</h3>
                </div>
                <div class="panel-body">
                    <form action="${pageContext.request.contextPath }/admin/userloginSub" method="post">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="学号/工号" name="loginId" autofocus
                                       required>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="密码" name="password" type="password" value=""
                                       required>
                            </div>
                            <div class="checkbox" style="float: left">
                                <label>
                                    <input name="remember" type="checkbox" value="Remember Me">记住我
                                </label>
                            </div>

                            <button class="btn btn-lg btn-success btn-block">登录</button>

                            <div class="form-group" style="margin-top: 20px;text-align: center">
                                <a href="<%=request.getContextPath()%>/view/login_ForAdmin" target="_parent">管理员登录
                                    <span class="glyphicon glyphicon-arrow-right" style="color: #337AB7;"></span>
                                </a>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%= request.getContextPath()%>/vendor/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">
    <c:if test="${!empty error_msg}">alert("${error_msg}");
    </c:if>
</script>


<%--<script src="<%=request.getContextPath()%>/layui/layui.js"></script>--%>
<%--<script>--%>
    <%--layui.use('layer', function () { //独立版的layer无需执行这一句--%>
        <%--var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句--%>

<%--//触发事件--%>
        <%--var active = {--%>
            <%--notice: function () {--%>
<%--//示范一个公告层--%>
                <%--layer.open({--%>
                    <%--type: 2--%>
                    <%--,--%>
                    <%--title: ['忘记密码', 'font-size:18px;text-align:center;background:#32AA9F']--%>
                    <%--,--%>
                    <%--closeBtn: false--%>
                    <%--,--%>
                    <%--area: ['400px', '550px']--%>
                    <%--,--%>
                    <%--shade: 0--%>
                    <%--,--%>
                    <%--id: 'LAY_layuipro' //设定一个id，防止重复弹出--%>
                    <%--,--%>
                    <%--btn: ['关闭']--%>
                    <%--,--%>
                    <%--moveType: 0 //拖拽模式，0或者1--%>
                    <%--,--%>
                    <%--content: ['/LS/view/forgetPassword']--%>
                    <%--,--%>
                    <%--success: function (layero) {--%>
                        <%--var btn = layero.find('.layui-layer-btn');--%>
                        <%--btn.css('text-align', 'center');--%>
                        <%--btn.class('layui-btn layui-btn-primary')--%>
                    <%--}--%>
                <%--});--%>
            <%--}--%>
        <%--};--%>

        <%--$('.layui-btn').on('click', function () {--%>
            <%--var othis = $(this), method = othis.data('method');--%>
            <%--active[method] ? active[method].call(this, othis) : '';--%>
        <%--});--%>

    <%--});--%>
<%--</script>--%>

</body>
</html>
