<%--
  Created by IntelliJ IDEA.
  User: zhanglulu
  Date: 2017/8/4
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>页脚</title>
    <style>
        span {
            color: #959595;

        }

        body {
            background-color: #4d5b69;
            height: 5px;
            width: 100%;
        }

        html {
            height: 5px;
            overflow: hidden;
        }

        .btmbg {
            width: 100%;
            overflow: hidden;
        }

        .btm {
            width: 100%;
            overflow: hidden;
            color: #999999;
            font-family: "宋体";
            text-align: center;
            margin-top: -10px;
            font-size: 13px;
            color: white;
        }

    </style>
</head>
<body>
<div class="btmbg">
    <div class="btm">
        <p>郑州小桥科技有限公司 版权所有&nbsp;|&nbsp;(MyCodes.Net) all rights reserved 2002-2017 豫ICP备********号&nbsp;|&nbsp;
            <a href="<%=request.getContextPath()%>/view/bookRule"  title="点击查看预约规则" target="mainFrame_User" style="color: #ffffff">预约规则</a>&nbsp;|&nbsp;
            <a href="<%=request.getContextPath()%>/view/loseRule" title="点击查看惩罚规则" target="mainFrame_User" style="color: #ffffff">惩罚规则</a></p>
    </div>
</div>

</body>
</html>
