<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="<%= request.getContextPath()%>/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">
    <%--获取当前时间--%>
    <script src="<%=request.getContextPath()%>/js/nowtime.js"></script>
    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <style type="text/css">
        body {
            width: 80%;
            margin-left: 10%;
        }
    </style>

</head>
<body>
<div id="wrapper">
    <div class="col-sm-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-bar-chart-o fa-fw"></i> 座位实时使用状态
                <font id="nowtime" style="margin-left: 20px"></font>
                <a href="${pageContext.request.contextPath }/jsp/main_User" class="btn btn-success btn-xs"
                   style="margin-left:20px ">点击刷新</a>
                <div class="pull-right">
                    <FORM METHOD=POST ACTION="" name="selectform">
                        <SELECT NAME="building" id="floorSide" onChange="getData()">
                            <OPTION VALUE="南楼">南楼</OPTION>
                            <OPTION VALUE="北楼">北楼</OPTION>
                        </SELECT>
                    </FORM>
                </div>
            </div>
            <!-- /.panel-heading -->

            <!-- EChart 显示各楼层座位状态-->
            <div id="main" style="width:100%;height:400px;"></div>
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-sm-8 -->

    <div class="col-sm-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="glyphicon glyphicon-star"></i> 排行榜
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body" style="height: 400px;padding: 0 0 0 0 ">
                <table class="layui-table" lay-even="" lay-skin="nob">
                    <tr>
                        <td>第一名</td>
                        <td>小黄</td>
                        <td>排名依据</td>
                    </tr>
                    <tr>
                        <td>第二名</td>
                        <td>小红</td>
                        <td>排名依据</td>
                    </tr>
                    <tr>
                        <td>第三名</td>
                        <td>小绿</td>
                        <td>排名依据</td>
                    </tr>
                </table>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-4 -->

</div>
<!-- /#wrapper -->


<div class="col-sm-8">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-bell fa-fw"></i> 公告
        </div>
        <!-- /.panel-heading -->
        <div class="panel-body" style="height: 400px">

            <c:forEach items="${noticestop}" var="notice">
                <div class="list-group">
                    <a href="<%=request.getContextPath()%>/jsp/news_Content?nid=${notice.nid}">${notice.title}
                        <span class="pull-right text-muted small"><em><fmt:formatDate value="${notice.creattime}"
                                                                                      pattern="yyyy-MM-dd HH:mm:ss"/></em></span>
                    </a>
                </div>
            </c:forEach>

            <!-- /.list-group -->
            <a href="<%=request.getContextPath()%>/jsp/news_List_Admin" target="mainFrame_User"
               class="btn btn-default btn-block">查看更多</a>
        </div>
        <!-- /.panel-body -->
    </div>
</div>
<div class="col-sm-4">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="glyphicon glyphicon-time"></i> 开馆时间安排
        </div>
        <!-- /.panel-heading -->
        <div class="panel-body" style="height: 400px">

            <div class="list-group">
                <c:forEach items="${weekopens}" var="week" varStatus="tt">
                    <li>${week.building}(${week.floor})
                        <span class="pull-right text-muted small"><em>${week.param1};${week.param2}</em></span>
                    </li>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<!-- jQuery -->
<script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%= request.getContextPath()%>/vendor/bootstrap/js/bootstrap.min.js"></script>

<%--echart--%>
<script src="<%=request.getContextPath()%>/js/echarts-all.js"></script>
<script type="text/javascript">
    window.onload = function () {
        getData();
    };

    function getData() {
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main'), 'macarons');

        myChart.showLoading({text: '正在努力的读取数据中...'});    //数据加载完之前先显示一段简单的loading动画

        var floors = [];  //所有楼层
        var noBook = [];  //空闲
        var booknum = [];  //预约
        var seated = []; //入座

        var floorside = $("#floorSide").val();

        $.ajax({
            type: "post",
            async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: "${pageContext.request.contextPath }/jsp/getSeatData.form",    //getTrendData
            data: 'floor=' + floorside,
            //  dataType : "json",        //返回数据形式为json
            success: function (result) {
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                result = eval(result);
                if (result) {
                    for (var i = 0; i < result.length; i++) {
                        floors.push(result[i].floor);    //挨个取出类别并填入类别数组
                    }
                    for (var i = 0; i < result.length; i++) {
                        noBook.push(result[i].nobook);    //挨个取出销量并填入销量数组
                    }
                    for (var i = 0; i < result.length; i++) {
                        booknum.push(result[i].bookNum);    //挨个取出销量并填入销量数组
                    }
                    for (var i = 0; i < result.length; i++) {
                        seated.push(result[i].seatedNum);    //挨个取出销量并填入销量数组
                    }
                    myChart.hideLoading();    //隐藏加载动画

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
                                data: floors
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
                                data: noBook
                            },
                            {
                                name: '已预约',
                                type: 'bar',
                                data: booknum
                            },
                            {
                                name: '已入座',
                                type: 'bar',
                                data: seated
                            }
                        ]
                    };
                    myChart.setOption(option);
                }
            },
            error: function (errorMsg) {
                //请求失败时执行该函数
                alert("图表请求数据失败!");
                myChart.hideLoading();
            }
        });
    }


</script>
</body>
</html>
