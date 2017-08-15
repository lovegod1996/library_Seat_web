<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/12
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Custom CSS -->
    <link href="<%= request.getContextPath()%>/dist/css/sb-admin-2.css" rel="stylesheet">
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <style type="text/css">
        body{
            padding-left: 20%;
            text-align: center;
        }
        ul {
            background-color: #F8F8F8;
            height: 100%;
            width: 100%;
            padding-left: 25%;
        }
        .layui-nav-tree{
            width: 100%;
        }
        #wrapper {
            width: 80%;
            margin-left: -70px;
        }
        .layui-nav-tree .layui-nav-child dd.layui-this, .layui-nav-tree .layui-this, .layui-nav-tree .layui-this>a, .layui-nav-tree .layui-this>a:hover {
            background-color: #31c7b2;
            color: #fff;
        }
        .layui-nav {
            background-color: #707b88;
        }
        body {
            padding-left: 20%;
            text-align: center;
            background-color: #f1f3fa;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <ul class="layui-nav layui-nav-tree" lay-filter="demo">
        <li class="layui-nav-item layui-this"><a href="<%=request.getContextPath()%>/view/userInformation_rightcontent"
                                                 target="mainFrame_UserInformation">个人资料</a></li>
        <li class="layui-nav-item"><a href="<%=request.getContextPath()%>/view/study_List"
                                      target="mainFrame_UserInformation">学习记录</a></li>
        <li class="layui-nav-item"><a href="<%=request.getContextPath()%>/view/unpromise_List"
                                      target="mainFrame_UserInformation">失信记录</a></li>
        <li class="layui-nav-item">
            <a href="javascript:;">其他设置</a>
            <dl class="layui-nav-child">
                <dd><a href="<%=request.getContextPath()%>/view/setNewPassword"
                       target="mainFrame_UserInformation">重置密码</a></dd>
                <%--<dd><a href="<%=request.getContextPath()%>/view/bindingPhonenumber"--%>
                       <%--target="mainFrame_UserInformation">绑定手机号</a></dd>--%>
                <%--<dd><a href="<%=request.getContextPath()%>/view/setNewPhonenumber"--%>
                       <%--target="mainFrame_UserInformation">重置手机号</a></dd>--%>
            </dl>
        </li>
    </ul>
</div>
<!-- jQuery -->
<script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>
<script>
    layui.use('element', function () {
        var element = layui.element(); //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(demo)', function (elem) {
            //console.log(elem)
            layer.msg(elem.text());
        });
    });
</script>
</body>
</html>
