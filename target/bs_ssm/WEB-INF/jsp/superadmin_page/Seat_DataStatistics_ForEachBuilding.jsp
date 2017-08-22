<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/21
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>每栋楼座位使用详情</title>
    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <style>
        body {
            background-color: #eff3f8;
            margin-top: 15px;
        }
        .panel-default>.panel-heading {
            color: #5c9bd1;
            background-color: #ffffff;
            border-color: #f9f9f9;
        }
        .panel-default>.panel-heading {
            color: #9e9e9e;
            background-color: #f1f3fa;
            border-color: #f1f3fa;
            font-size: 15px;
            font-weight: bold;
            font-family: inherit;
        }
        .panel-default {
            border-color: #f1f3fa;
        }
        .panel {
            background-color: #f1f3fa;
        }
        .layui-table {
            width: 90%;
            margin: 10px 0;
            background-color: #fff;
            margin-left: 5%;
        }
        .pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
            z-index: 3;
            color: #fff;
            cursor: default;
            background-color: #5c9bd1;
            border-color: #5c9bd1;
        }
        .layui-table thead tr {
            background-color: #f1f3fa;
        }
        .layui-table {
            width: 80%;
            margin: 10px 10%;
            background-color: #fff;
        }
    </style>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title">
    <legend>查看每个场馆的预约学习情况</legend>
    <div class="layui-field-box">
        <div class="layui-tab">
            <ul class="layui-tab-title">
                <li class="layui-this">按周查看</li>
                <li >按月查看</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <table class="layui-table" id="tb1">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>场馆</th>
                            <th>学习时长</th>
                            <th>学习总次数</th>
                            <th>失信总次数</th>
                            <th>失信率</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${weekdatas}" var="weekdata" varStatus="step">
                            <tr>
                                <td>第${step.index+1}周</td>
                                <td>${weekdata.venue}</td>
                                <td>${weekdata.learntime}小时</td>
                                <td>${weekdata.allLearn}</td>
                                <td>${weekdata.undeal}</td>
                                <td>${weekdata.dealpro}%</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <!-- EChart 显示各楼层座位状态-->
                    <div id="weeklearntime" style="width: 100%;height:400px;"></div>
                    <br>
                    <div id="weekalllearn" style="width: 100%;height:400px;"></div>
                </div>
                <div class="layui-tab-item">
                    <table class="layui-table" id="tb2" style="margin-left: 3%;">
                        <thead>
                        <tr>
                            <th>月份</th>
                            <th>场馆</th>
                            <th>学习时长</th>
                            <th>学习总次数</th>
                            <th>失信总次数</th>
                            <th>失信率</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${monthdatas}" var="monthdata">
                            <tr>
                                <td>${monthdata.month}月</td>
                                <td>${monthdata.venue}</td>
                                <td>${monthdata.learntime}小时</td>
                                <td>${monthdata.allLearn}</td>
                                <td>${monthdata.undeal}</td>
                                <td>${monthdata.dealpro}%</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <!-- EChart 显示各楼层座位状态-->
                    <div id="monthlearntime" style="width: 1086px;height:400px;"></div>
                    <br>
                    <div id="monthalllearn" style="width: 1086px;height:400px;"></div>
                </div>
            </div>
        </div>
    </div>
</fieldset>


<!-- jQuery -->
<script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>
<%--echart--%>
<script src="<%=request.getContextPath()%>/js/echarts-all.js"></script>
<script src="<%=request.getContextPath()%>/js/macarons.js"></script>

<%--Tip页切换--%>
<script>
    layui.use('element', function () {
        var $ = layui.jquery
            , element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块

        //触发事件
        var active = {
            tabChange: function () {
                //切换到指定Tab项
                element.tabChange('demo', '22'); //
            }
        };

        $('.site-demo-active').on('click', function () {
            var othis = $(this), type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
        });

        //Hash地址的定位
        var layid = location.hash.replace(/^#test=/, '');
        element.tabChange('test', layid);

        element.on('tab(test)', function (elem) {
            location.hash = 'test=' + $(this).attr('lay-id');
        });

    });
</script>
<script>
    layui.use(['form'], function () {

    });
</script>
<script type="text/javascript">
    window.onload = function () {
        getweekData();
        getmonthData();
    };

    function getweekData() {
        var weeklearntime = echarts.init(document.getElementById('weeklearntime'));
        var weekalllearn = echarts.init(document.getElementById('weekalllearn'));
        weeklearntime.showLoading({text: '正在努力的读取数据中...'});    //数据加载完之前先显示一段简单的loading动画
        weekalllearn.showLoading({text: '正在努力的读取数据中...'});    //数据加载完之前先显示一段简单的loading动画
        var weeks=[]; //所有月份
        var learntimes=[];//学习时长
        var alllearns=[];//学习次数
        var undeal=[]; //失信次数

        $.ajax({
            type: "post",
            async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: "${pageContext.request.contextPath }/view/getweeklearntimefloor.form",    //getTrendData
            data: "fid=${floor.fid}",
            //  dataType : "json",        //返回数据形式为json
            success: function (result) {
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                result = eval(result);
                if (result) {
                    for (var i = 0; i < result.length; i++) {
                        weeks.push("第"+(i+1)+"周");    //挨个取出类别并填入类别数组
                    }
                    for (var i = 0; i < result.length; i++) {
                        learntimes.push(result[i].learntime);    //挨个取出销量并填入销量数组
                    }
                    for (var i = 0; i < result.length; i++) {
                        alllearns.push(result[i].allLearn);    //挨个取出销量并填入销量数组
                    }
                    for (var i = 0; i < result.length; i++) {
                        undeal.push(result[i].undeal);    //挨个取出销量并填入销量数组
                    }
                    weeklearntime.hideLoading();    //隐藏加载动画
                    weekalllearn.hideLoading();    //隐藏加载动画

                    var alllearnoption = {
                        title: {
                            text: '',
                            subtext: ''
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['学习次数','失信次数']
                        },
                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                data: weeks
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value'
                            }
                        ],
                        series: [
                            {
                                name: '学习次数',
                                type: 'line',
                                data: alllearns
                            },{
                                name: '失信次数',
                                type: 'line',
                                data: undeal
                            }
                        ]
                    };
                    var timeoption = {
                        title: {
                            text: '',
                            subtext: ''
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['学习时长']
                        },
                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                data: weeks
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value'
                            }
                        ],
                        series: [
                            {
                                name: '学习时长',
                                type: 'line',
                                data: learntimes
                            }
                        ]
                    };

                    weekalllearn.setOption(alllearnoption);
                    weeklearntime.setOption(timeoption);
                }
            },
            error: function (errorMsg) {
                //请求失败时执行该函数
                alert("图表请求数据失败!");
                weekalllearn.hideLoading();
                weeklearntime.hideLoading();
            }
        });
    }

    function getmonthData() {
        var learntime = echarts.init(document.getElementById('monthlearntime'));
        var alllearn = echarts.init(document.getElementById('monthalllearn'));
        learntime.showLoading({text: '正在努力的读取数据中...'});    //数据加载完之前先显示一段简单的loading动画
        alllearn.showLoading({text: '正在努力的读取数据中...'});    //数据加载完之前先显示一段简单的loading动画
        var months=[]; //所有月份
        var learntimes=[];//学习时长
        var alllearns=[];//学习次数
        var undeal=[]; //失信次数


        $.ajax({
            type: "post",
            async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: "${pageContext.request.contextPath }/view/getmonthlearntimefloor.form",    //getTrendData
            data: "fid=${floor.fid}",
            //  dataType : "json",        //返回数据形式为json
            success: function (result) {
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                result = eval(result);
                if (result) {
                    for (var i = 0; i < result.length; i++) {
                        months.push(result[i].month+"月");    //挨个取出类别并填入类别数组
                    }
                    for (var i = 0; i < result.length; i++) {
                        learntimes.push(result[i].learntime);    //挨个取出销量并填入销量数组
                    }
                    for (var i = 0; i < result.length; i++) {
                        alllearns.push(result[i].allLearn);    //挨个取出销量并填入销量数组
                    }
                    for (var i = 0; i < result.length; i++) {
                        undeal.push(result[i].undeal);    //挨个取出销量并填入销量数组
                    }
                    learntime.hideLoading();    //隐藏加载动画
                    alllearn.hideLoading();    //隐藏加载动画

                    var alllearnoption = {
                        title: {
                            text: '',
                            subtext: ''
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['学习次数','失信次数']
                        },
                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                data: months
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value'
                            }
                        ],
                        series: [
                            {
                                name: '学习次数',
                                type: 'line',
                                data: alllearns
                            },{
                                name: '失信次数',
                                type: 'line',
                                data: undeal
                            }
                        ]
                    };
                    var timeoption = {
                        title: {
                            text: '',
                            subtext: ''
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['学习时长']
                        },
                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                data: months
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value'
                            }
                        ],
                        series: [
                            {
                                name: '学习时长',
                                type: 'line',
                                data: learntimes
                            }
                        ]
                    };

                    alllearn.setOption(alllearnoption);
                    learntime.setOption(timeoption);
                }
            },
            error: function (errorMsg) {
                //请求失败时执行该函数
                alert("图表请求数据失败!");
                alllearn.hideLoading();
                learntime.hideLoading();
            }
        });
    }

</script>

</body>
</html>
