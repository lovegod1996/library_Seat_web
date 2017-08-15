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
            background-color: #eff3f8;
        }

        #wrapper {
            margin-top:15px;
        }
        .panel-default>.panel-heading {
            color: #5c9bd1;
            background-color: #ffffff;
            border-color: #f9f9f9;
        }
        .panel-default {
            border-color: #eff3f8;
        }

        html {
            width: 100%;
            height: 100%;
            overflow: hidden;
            overflow-y: auto;
        }
        a{
            overflow: hidden;
            /*white-space: nowrap;*/
            text-overflow: ellipsis;
        }
        li {
            list-style: none;
            color: #93a2a9;
            margin-bottom: 15px;
        }
        li span{
            margin-top: 10px;
            margin-right:10px;
        }
        .btn-block {
            display: block;
            width: 100px;
            margin: 30px auto;
            background-color: #8895a9;
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
                        <SELECT NAME="building" id="floorSide" onChange="getData()" style="border-radius: 15px;">
                            <c:forEach items="${buildings}" var="building">
                                <OPTION VALUE="${building.bid}">${building.employer}</OPTION>
                            </c:forEach>
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
                   <tbody>
                        <tr style="color:#555;font-weight:bold;background-color: #f9f9f9;">
                           <td>&nbsp;&nbsp;姓名</td>
                           <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学院</td>
                           <td>总时长</td>
                        </tr>
                    <c:forEach items="${userdatas}" var="userdata" varStatus="step">
                        <c:if test="${step.index<6}">

                            <tr style="color:#555;background-color: #ffffff;">
                                <td>${userdata.username}</td>
                                <td>${userdata.venue}</td>
                                <td>${userdata.learntime}小时</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                   </tbody>
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
            <i class="glyphicon glyphicon-time"></i> 开馆时间安排
        </div>
        <!-- /.panel-heading -->
        <div class="panel-body" style="height: 300px">

            <div class="list-group">
                <c:forEach items="${weekopens}" var="week" varStatus="tt">
                <li>${week.floor}(${week.building})
                    <span class="pull-right text-muted small"><em>${week.param1};${week.param2}</em></span>
                </li>
            </c:forEach>
            </div>
        </div>
    </div>
</div><br>

<div class="col-sm-4">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-bell fa-fw"></i> 公告
        </div>
        <!-- /.panel-heading -->
        <div class="panel-body" style="height: 300px">

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
        var snapnum = [];//临时离开

        var floorside = $("#floorSide").val();

        $.ajax({
            type: "post",
            async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: "${pageContext.request.contextPath }/jsp/getSeatData.form",    //getTrendData
            data: 'building=' + floorside,
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
                    for (var i = 0; i < result.length; i++) {
                        snapnum.push(result[i].snapNum);    //挨个取出销量并填入销量数组
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
                            data: ['空闲中', '已预约', '已入座', '临时离开']
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
                            }, {
                                name: '临时离开',
                                type: 'bar',
                                data: snapnum
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
