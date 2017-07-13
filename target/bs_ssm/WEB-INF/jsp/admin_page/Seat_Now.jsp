<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/12
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>现场入座</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <%--layui 日期选择控件--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <script>
        layui.use('laydate', function () {
            var laydate = layui.laydate;
            var start = {
                min: laydate.now()
                , max: '2099-06-16 23:59:59'
                , istoday: false
                , choose: function (datas) {
                    end.min = datas; //开始日选好后，重置结束日的最小日期
                    end.start = datas //将结束日的初始值设定为开始日
                }
            };

            var end = {
                min: laydate.now()
                , max: '2099-06-16 23:59:59'
                , istoday: false
                , choose: function (datas) {
                    start.max = datas; //结束日选好后，重置开始日的最大日期
                }
            };

            document.getElementById('LAY_demorange_s').onclick = function () {
                start.elem = this;
                laydate(start);
            };
            document.getElementById('LAY_demorange_e').onclick = function () {
                end.elem = this;
                laydate(end);
            }

        });
    </script>
    <style type="text/css">
        body{
            padding-top: 20px;
            line-height: 22px;
            background-color: #393D49;
            font-weight: 300;"
        }
        label{
            color: #fff;
            width: 25%;
            text-align: center;
        }
        button{
            width: 50%;
            margin-top: 10%;
            margin-left: 25%;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <div class="col-sm-12">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="control-label" style="float: left">座号</label>
                <div class="col-sm-7" style="float: left">
                    <input type="text" class="layui-input" placeholder="点击入座自动填充" style="width: 220px" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label" style="float: left">学号</label>
                <div class="col-sm-7" style="float: left">
                    <input type="text" class="layui-input" placeholder="输入学号" style="width: 220px" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label" style="float: left">开始</label>
                <div class="col-sm-7" style="float: left">
                    <div class="layui-inline">
                        <input class="layui-input" placeholder="开始时间" style="width: 220px" required
                               onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm',min: laydate.now(0), max: laydate.now(+1)})">
                        <%--now(0)表示今天；now(1)表示明天,限制预约只能今天明天--%>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label" style="float: left">结束</label>
                <div class="col-sm-7" style="float: left">
                    <div class="layui-inline">
                        <input class="layui-input" placeholder="结束时间" style="width: 220px" required
                               onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm',min: laydate.now(0), max: laydate.now(+1)})">
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">确定</button>
        </form>
    </div>
</div>
</body>
</html>
