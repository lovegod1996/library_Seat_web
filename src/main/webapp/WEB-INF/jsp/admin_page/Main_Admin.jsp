<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书馆座位预约系统</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<%= request.getContextPath()%>/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%= request.getContextPath()%>/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%= request.getContextPath()%>/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">

    <style type="text/css">
        body {
            margin-left: 2%;
        }
    </style>
</head>
<body>

<div id="wrapper">

    <div class="col-sm-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-bar-chart-o fa-fw"></i> 座位实时使用状态
                <div class="pull-right">
                    <FORM METHOD=POST ACTION="" name="selectform">
                        <SELECT NAME="building" onChange="getCity()">
                            <OPTION VALUE="南楼">南楼 </OPTION>
                            <OPTION VALUE="北楼">北楼 </OPTION>
                        </SELECT>
                    </FORM>
                </div>
            </div>
            <!-- /.panel-heading -->
            <!-- EChart 显示各楼层座位状态-->
            <div id="main" style="width: 100%;height:400px;"></div>
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-sm-8 -->

    <div class="col-sm-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-bell fa-fw"></i> 公告
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body" style="height: 400px">
                <div class="list-group">
                    <a href="<%=request.getContextPath()%>/jsp/news_Content" target="mainFrame_Admin"
                       class="list-group-item">我是标题
                        <span class="pull-right text-muted small"><em>2017.07.11</em></span>
                    </a>
                </div>
                <!-- /.list-group -->
                <a href="<%=request.getContextPath()%>/jsp/news_List_Admin" target="mainFrame_Admin"
                   class="btn btn-default btn-block">查看更多</a>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-4 -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%= request.getContextPath()%>/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%= request.getContextPath()%>/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%= request.getContextPath()%>/dist/js/sb-admin-2.js"></script>

<%--echart--%>
<script src="<%=request.getContextPath()%>/js/echarts-all.js"></script>
<script src="<%=request.getContextPath()%>/js/macarons.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('main'), 'macarons');
    var option = {
        title: {
            text: '',
            subtext: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['空闲中', '已预约', '已入座']
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                data: ['南一', '南二', '南三', '南四', '南五', '南六']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '空闲中',
                type: 'bar',
                data: [23.2, 25.6, 76.7, 135.6, 6.4, 13.3]
            },
            {
                name: '已预约',
                type: 'bar',
                data: [34.6, 5.9, 9.0, 175.6, 6.0, 24.3, 60]
            },
            {
                name: '已入座',
                type: 'bar',
                data: [2.9, 28.7, 70.7, 18.8, 6.0, 2.4, 55]
            }
        ]
    };


    // 为echarts对象加载数据
    myChart.setOption(option);
</script>

</body>
</html>
