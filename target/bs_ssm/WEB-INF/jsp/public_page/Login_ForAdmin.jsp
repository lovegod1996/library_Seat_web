<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/19
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>管理员登录</title>
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
                    <h3 class="panel-title">管理员登录</h3>
                </div>
                <div class="panel-body">
                    <form action="<%=request.getContextPath()%>/admin/adminLoginSub" method="post">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="管理员账号" name="loginId" type="text" autofocus
                                       required>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="密码" name="password" type="password" value=""
                                       required>
                            </div>
                            <div class="radio" style="float: left">
                                <label>
                                    <input type="radio" name="optionsRadiosinline" id="optionsRadios1" value="1"
                                           checked>楼层管理员
                                </label>
                                <label>
                                    <input type="radio" name="optionsRadiosinline" id="optionsRadios2" value="2">图书馆管理员
                                </label>
                                <label>
                                    <input type="radio" name="optionsRadiosinline" id="optionsRadios3" value="3">系统管理员
                                </label>
                            </div>
                            <div class="checkbox" style="float: left">
                                <label>
                                    <input name="remember" type="checkbox" value="Remember Me">记住我
                                </label>
                            </div>
                            <%--<div class="fogetPwd" style="float: left">--%>
                                <%--<a data-method="notice" class="layui-btn layui-btn-mini" target="_parent">忘记密码?</a>--%>
                            <%--</div>--%>

                            <button class="btn btn-lg btn-success btn-block">登录</button>
                            <div class="form-group" style="margin-top: 20px;text-align: center">
                                <a href="<%=request.getContextPath()%>/jsp/login" target="_parent">
                                    <span class="glyphicon glyphicon-arrow-left" style="color: #337AB7;"></span>学生登录
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

</body>
</html>

