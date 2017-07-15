<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/12
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <%--layui 日期选择控件--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>

    <%--验证表单--%>
    <script src="<%=request.getContextPath()%>/js/checkForm.js"></script>

    <style type="text/css">
        body {
            padding-top: 20px;
            line-height: 22px;
            background-color: #393D49;
            font-weight: 300;
        "
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

        input {
            width: 250px;
        }
    </style>

</head>
<body>
<div id="wrapper">
    <div class="col-sm-12">
        <form class="form-horizontal" role="form" name="addform" action="<%= request.getContextPath()%>/view/aduserSub"
              method="post" target="mainFrame_Admin">

            <div class="form-group">
                <label class="control-label" style="float: left">姓名</label>
                <div class="col-sm-7" style="float: left">
                    <input type="text" name="name" id="checkname" class="layui-input" placeholder="输入" required
                           >
                </div>
            </div>
            <div class="form-group">
                <label class="control-label" style="float: left">学号</label>
                <div class="col-sm-7" style="float: left">
                    <input type="text" name="sno" id="checkstudentid" class="layui-input" placeholder="输入" required
                           >
                </div>
            </div>

            <div class="form-group">
                <label class="control-label" style="float: left">学院</label>
                <div class="col-sm-7" style="float: left">
                    <input type="text" name="college" id="checkcollege" class="layui-input" placeholder="输入" required
                           >
                </div>
            </div>
            <div class="form-group">
                <label class="control-label" style="float: left">专业</label>
                <div class="col-sm-7" style="float: left">
                    <input type="text" name="major" id="checkmajor" class="layui-input" placeholder="输入" required
                           >
                </div>
            </div>
            <div class="form-group">
                <label class="control-label" style="float: left">班级</label>
                <div class="col-sm-7" style="float: left">
                    <input type="text" name="classes" id="checkclass" class="layui-input" placeholder="输入" required
                           >
                </div>
            </div>

            <button type="submit" class="btn btn-primary">确定</button>
        </form>
    </div>
</div>
</body>
</html>