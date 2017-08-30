<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/8/11
  Time: 13:07
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
    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <!-- jQuery -->
    <script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%= request.getContextPath()%>/vendor/bootstrap/js/bootstrap.min.js"></script>
    <%--获取当前时间--%>
    <script src="<%=request.getContextPath()%>/js/nowtime.js"></script>

    <style>
        * {
            margin: 0px;
            padding: 0px
        }

        body {
            width: 90%;
            margin-left: 5%;
            background-color: #eff3f8;
        }

        .carousel {
            margin-top: 2px;
        }

        .panel-default > .panel-heading {
            /*color: #5c9bd1;*/
            color: #ffffff;
            background-color: rgba(1, 170, 234, 0.72);
            border-color: #f9f9f9;
        }

        .panel-default {
            border-color: #eff3f8;
        }

        li {
            list-style: none;
            color: #2b4454;
            margin-bottom: 15px;
        }

        li span {
            margin-top: 10px;
            margin-right: 10px;
        }

        .btn-block {
            display: block;
            width: 100px;
            margin: 30px auto;
            background-color: #8895a9;
        }

        .pull-right {
            float: right !important;
            /*margin-top: 15px;*/
        }

        a:focus, a:hover, a:active {
            color: #8895a9;
            text-decoration: none;
        }

        a {
            color: #4d5b69;
            /*color: #ffffff;*/
            text-decoration: none;
        }

        caption {
            padding-top: 8px;
            padding-bottom: 8px;
            color: #777;
            text-align: left;
            font-weight: 600;
            margin-left: 10px;
        }

        .panel-default {
            border-color: #eff3f8;
            margin-left: -18px;
            margin-right: -18px;
        }

        .carousel-control {
            opacity: 0;
        }

        .carousel-control:focus, .carousel-control:hover {
            color: rgb(141, 165, 177);
            opacity: 0.1;
        }

        .Middle_img {
            height: 100px;
            margin-top: 30px;
            position: relative;
        <%--background-image: url(<%=request.getContextPath()%>/img/middleBG.jpg);--%> line-height: 100px;
            text-align: center;
        }

        .Middle_img a img {
            border: 0;
            transform: scale(1);
            transition: all 0.5s ease 0s;
            -webkit-transform: scale(1);
        }

        .Middle_img a img:hover {
            transform: scale(1.3);
            transition: all 0.5s ease 0s;
            -webkit-transform: scale(1.1);
        }
    </style>
</head>
<body>
<div id="myCarousel" class="carousel slide" style="height: 450px;">
    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
        <div class="item active">
            <img src="<%=request.getContextPath()%>/img/bg3.jpg" alt="First slide" style="height: 450px;width: 100%">
        </div>
        <div class="item">
            <img src="<%=request.getContextPath()%>/img/bg4.jpg" alt="Second slide" style="height: 450px;width: 100%">
        </div>
        <div class="item">
            <img src="<%=request.getContextPath()%>/img/bg1.jpg" alt="Third slide" style="height: 450px;width: 100%">
        </div>
    </div>
    <!-- 轮播（Carousel）导航 -->
    <a class="carousel-control left" href="#myCarousel"
       data-slide="prev">&lsaquo;</a>
    <a class="carousel-control right" href="#myCarousel"
       data-slide="next">&rsaquo;</a>
</div>

<div class="col-sm-12 Middle_img">
    <img src="<%=request.getContextPath()%>/img/middleBG.jpg" height="" width="100%">
    <a href="<%=request.getContextPath()%>/view/choose_Building?day=0"><img
            src="<%=request.getContextPath()%>/img/today.png"
            style="position:absolute; z-index:2; right:260px; top: 20px"></a>
    <a href="<%=request.getContextPath()%>/view/choose_Building?day=1"><img
            src="<%=request.getContextPath()%>/img/tomorrow.png"
            style="position:absolute; z-index:2; right:50px; top:20px"></a>
</div>

<div class="col-sm-12" style="height: auto;margin-top: 30px">
    <div class="col-sm-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="glyphicon glyphicon-time"></i> 开馆时间安排
                <a href="<%=request.getContextPath()%>/jsp/openToday" target="mainFrame_User"
                   style="float:right;">查看更多场馆&nbsp;> </a>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body" style="height: 350px">

                <div class="list-group">
                    <c:forEach items="${weekopens}" var="week" varStatus="tt">
                        <c:if test="${tt.index<5}">
                            <li>
                                <div style="font-weight: bold;font-size: large">${week.floor} <span class="pull-right"
                                                                                                    style="font-size: smaller;margin-top: 5px">${week.param1};${week.param2}</span>
                                </div>
                                <div style="font-size: smaller;color: #9d9d9d">${week.building}<span style="margin-left: 10px">F${week.fl}</span><span style="margin-left: 20px">共${week.allSeat}座</span>
                                    <span class="pull-right" style="margin-top: 0px">座位使用率：<strong>${week.userPro}%</strong></span></div>
                                    <%--<h4 style="font-weight: bold;font-size: large">${week.floor}<span class="pull-right">入座率：<strong>${week.userPro}%</strong></span></h4>--%>
                                    <%--<span style="font-size: smaller;color: #9d9d9d">${week.building}</span>--%>
                                    <%--<span class="pull-right text-muted small"><em style="color: #0C0C0C">${week.param1};${week.param2}</em></span>--%>
                            </li>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <div class="col-sm-4">
        <div class="panel panel-default" style="margin-left:10px;">
            <div class="panel-heading">
                <i class="glyphicon glyphicon-bell"></i> 公告

                <a href="<%=request.getContextPath()%>/jsp/news_List_User" target="mainFrame_User"
                   style="float:right;">查看更多&nbsp;> </a>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body" style="height:  350px">

                <c:forEach items="${noticestop}" var="notice">
                    <div class="list-group">
                        <a href="<%=request.getContextPath()%>/jsp/news_Content?nid=${notice.nid}">${notice.title}
                            <span class="pull-right text-muted small"><em><fmt:formatDate value="${notice.creattime}"
                                                                                          pattern="yyyy-MM-dd HH:mm:ss"/></em></span>
                        </a>
                    </div>
                </c:forEach>

                <!-- /.list-group -->

            </div>
            <!-- /.panel-body -->
        </div>
    </div>

    <div class="col-sm-4">
        <div class="panel panel-default" style="margin-left:10px;">
            <div class="panel-heading">
                <i class="glyphicon glyphicon-phone"></i> 手机客户端
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body" style="height:  350px">
                <div style="text-align: center;width: 100%;height: auto">
                    <img src="<%=request.getContextPath()%>/img/Android_qrcode.jpg" style="height: 200px;width: 200px;">
                </div>
                <div style="text-align: center;width: 100%;height: 100%;margin-top: 10px">
                    <span class="layui-input" style="border: none">扫一扫下载手机客户端</span>
                </div>

            </div>
            <!-- /.panel-body -->
        </div>
    </div>
</div>

<div class="col-sm-12" style="margin-top: 30px">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="glyphicon glyphicon-dashboard"></i> 座位实时使用状态
            <font id="nowtime" style="margin-left: 20px"></font>
            <a href="${pageContext.request.contextPath }/jsp/main_User" class="btn btn-success btn-xs"
               style="margin-left:20px ">点击刷新</a>
            <div class="pull-right">
                <FORM METHOD=POST ACTION="" name="selectform">
                    <SELECT NAME="building" id="floorSide" onChange="getData()"
                            style="border-radius: 15px;color: #5c9bd1;">
                        <c:forEach items="${buildings}" var="building">
                            <OPTION VALUE="${building.bid}">${building.employer}</OPTION>
                        </c:forEach>
                    </SELECT>
                </FORM>
            </div>
        </div>
        <!-- /.panel-heading -->
        <!-- EChart 显示各楼层座位状态-->
        <div id="main" style="width:100%;height:500px;"></div>
    </div>
</div>

<div class="col-sm-12" style="margin-top: 30px">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="glyphicon glyphicon-star"></i> 排行榜(前20名)
        </div>
        <div class="col-sm-6" style="background-color: white;height: 470px">
            <table class="layui-table" lay-even="" lay-skin="nob">
                <thead style="color:#555;font-weight:bold;background-color: #f9f9f9;">
                <th>&nbsp;&nbsp;排名</th>
                <th>&nbsp;&nbsp;姓名</th>
                <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学院</th>
                <th>总时长</th>
                </thead>
                <tbody>
                <c:forEach items="${userdatas}" var="userdata" varStatus="step">
                    <c:if test="${step.index<10}">
                        <tr style="color:#555;background-color: #ffffff;">
                            <td>${step.index+1}</td>
                            <td>${userdata.username}</td>
                            <td>${userdata.venue}</td>
                            <td>${userdata.learntime}小时</td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-sm-6" style="background-color: white;height: 470px">
            <table class="layui-table" lay-even="" lay-skin="nob">
                <thead>
                <thead style="color:#555;font-weight:bold;background-color: #f9f9f9;">
                <th>&nbsp;&nbsp;排名</th>
                <th>&nbsp;&nbsp;姓名</th>
                <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学院</th>
                <th>总时长</th>
                </thead>
                <tbody>
                <c:forEach items="${userdatas}" var="userdata" varStatus="step">
                    <c:if test="${step.index>9}">
                        <c:if test="${step.index<20}">
                            <tr style="color:#555;background-color: #ffffff;">
                                <td>${step.index+1}</td>
                                <td>${userdata.username}</td>
                                <td>${userdata.venue}</td>
                                <td>${userdata.learntime}小时</td>
                            </tr>
                        </c:if>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

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
