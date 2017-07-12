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
        select{
            width: 150px;
        }
    </style>

    <script language="JavaScript" type="text/javascript">
        //定义了专业的二维数组，里面的顺序跟学院的顺序是相同的。通过selectedIndex获得学院的下标值来得到相应的专业数组
        var major = [
            ["软件", "计科", "网络"],
            ["网络", "软件", "计科"]
        ];

        function getCollege() {
            //获得学院下拉框的对象
            var sltCollege = document.addform.college;
            //获得专业下拉框的对象
            var sltMajor = document.addform.major;
            //得到对应楼的专业数组
            var collegeMajor = major[sltCollege.selectedIndex - 1];

            //清空专业下拉框，仅留提示选项
            sltMajor.length = 1;

            //将专业数组中的值填充到学院下拉框中
            for (var i = 0; i < collegeMajor.length; i++) {
                sltMajor[i + 1] = new Option(collegeMajor[i], collegeMajor[i]);
            }
        }
    </script>
</head>
<body>
<div id="wrapper">
    <div class="col-sm-12">
        <form class="form-horizontal" role="form" name="addform">
            <div class="form-group">
                <label class="control-label" style="float: left">入学年份</label>
                <div class="col-sm-7" style="float: left">
                    <input type="text" class="layui-input" placeholder="输入" style="width: 220px" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label" style="float: left">姓名</label>
                <div class="col-sm-7" style="float: left">
                    <input type="text" class="layui-input" placeholder="输入" style="width: 220px" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label" style="float: left">学号</label>
                <div class="col-sm-7" style="float: left">
                    <input type="text" class="layui-input" placeholder="输入" style="width: 220px" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label" style="float: left">学院</label>
                <div class="col-sm-7" style="float: left">
                    <select name="college" onchange="getCollege()" style="float: left;margin-left: 1%">
                        <option value="0">选择学院</option>
                        <option value="计算机学院">计算机学院</option>
                        <option value="软件学院">软件学院</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label" style="float: left">专业</label>
                <div class="col-sm-7" style="float: left">
                    <select name="major" style="float: left;margin-left: 1%" required>
                        <option value="0">选择专业</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label" style="float: left">班级</label>
                <div class="col-sm-7" style="float: left">
                    <input type="text" class="layui-input" placeholder="输入" style="width: 220px" required>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">确定</button>
        </form>
    </div>
</div>
</body>
</html>