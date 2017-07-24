<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/22
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>500</title>
    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <style type="text/css">
        body{
            text-align: center;
            padding-top: 50px;
        }
    </style>
</head>
<body>
<img src="<%=request.getContextPath()%>/img/500pic.png">
<div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;text-align: center">
        <legend>服务器出问题了呢，我也很绝望啊--${message}</legend>
    </fieldset>
    <a type="button" class="layui-btn" href="#" target="mainFrame_User">重新加载</a>
    <a type="button" class="layui-btn" href="<%=request.getContextPath()%>/index.jsp" target="_parent">返回首页</a>
</div>
</body>
</html>
