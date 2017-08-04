<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/13
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>我的信息</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>
    <script src="<%= request.getContextPath()%>/vendor/bootstrap/js/bootstrap.min.js"></script>
    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <style type="text/css">
        body {
            width: 60%;
            margin-left: 20%;
            background-color: #ffffff;
        }

        .user_img {
            margin-left: 20px;
            margin-top: 20px;
            height: 150px;
            float: left;
        }

        .user_msg {
            margin-left: 100px;
            margin-top: 20px;
            height: 150px;
            float: left;
        }

        img {
            width: 100px;
            height: 100px;
        }

        .upload {
            padding: 4px 10px;
            height: 20px;
            line-height: 20px;
            position: relative;
            border: 1px solid #999;
            text-decoration: none;
            color: #666;
        }

        .change {
            position: absolute;
            overflow: hidden;
            right: 0;
            top: 0;
            opacity: 0;
        }

        .imgCom {
            width: 100px;
            height: 100px;
            border: 1px solid #34A8FF;
        }

        .imgCom > img {
            width: 100%;
            height: 100%;
        }
    </style>
    <script type="text/javascript">
        function preview(file) {
            var prevDiv = document.getElementById('preview');
            if (file.files && file.files[0]) {
                var reader = new FileReader();
                reader.onload = function (evt) {
                    prevDiv.innerHTML = '<img src="' + evt.target.result + '" />';
                }
                reader.readAsDataURL(file.files[0]);
            }
            else {
                prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
            }
        }
    </script>
</head>
<body>
<div id="wrapper">
    <div class="row">
        <div class="user_img" style="text-align: center;display:table-cell; vertical-align:middle;">
            <p>
            <div class="imgCom" id="preview"></div>
            </p>
            <p><a href="javascript:;" class="upload" style="margin-top: 20px">点击上传
                <input class="change" type="file" multiple="multiple" onchange="preview(this)"/></a></p>
        </div>
        <div class="user_msg">
            <label class="control-label">姓名</label><label class="control-label"
                                                          style="margin-left: 20px">${users.name}</label><br>
            <label class="control-label">学号</label><label class="control-label"
                                                          style="margin-left: 20px">${users.sno}</label><br>
            <label class="control-label">学院</label><label class="control-label"
                                                          style="margin-left: 20px">${users.college}</label><br>
            <label class="control-label">专业</label><label class="control-label"
                                                          style="margin-left: 20px">${users.major}</label><br>
            <label class="control-label">班级</label><label class="control-label"
                                                          style="margin-left: 20px">${users.classes}</label><br>

        </div>
    </div>

    <table class="table">
        <caption style="text-align: center">查看每周的预约数据</caption>
        <thead>
        <th>序号</th>
        <th>学习时间</th>
        <th>学习次数</th>
        <th>失信次数</th>
        <th>失信次数</th>
        </thead>
        <tbody><c:forEach items="${weekdatas}" var="weekdata" varStatus="step">
            <tr>
                <td>${step.index+1}</td>
                <td>${weekdata.learntime}小时</td>
                <td>${weekdata.allLearn}</td>
                <td>${weekdata.undeal}</td>
                <td>${weekdata.dealpro}%</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <!-- EChart 显示各楼层座位状态-->
    <div id="main" style="width: 100%;height:400px;"></div>
    <%--<div class="row">--%>
        <%--<div class="layui-tab">--%>
            <%--<ul class="layui-tab-title">--%>
                <%--<li class="layui-this">天视图</li>--%>
                <%--<li>周视图</li>--%>
                <%--<li>月视图</li>--%>
                <%--<li>学年视图</li>--%>
            <%--</ul>--%>
            <%--<div class="layui-tab-content">--%>
                <%--&lt;%&ndash; 一页&ndash;%&gt;--%>
                <%--<div class="layui-tab-item layui-show">--%>
                    <%--<table class="layui-table" lay-even="" lay-skin="nob">--%>
                        <%--<colgroup>--%>
                            <%--<col width="150">--%>
                            <%--<col width="150">--%>
                            <%--<col width="200">--%>
                            <%--<col>--%>
                        <%--</colgroup>--%>
                        <%--<thead>--%>
                        <%--<tr>--%>
                            <%--<th>预约次数</th>--%>
                            <%--<th>不良记录</th>--%>
                            <%--<th>时间统计</th>--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <%--<tbody>--%>
                        <%--<tr>--%>
                            <%--<td>10次</td>--%>
                            <%--<td>0次</td>--%>
                            <%--<td>20小时</td>--%>
                        <%--</tr>--%>
                        <%--</tbody>--%>
                    <%--</table>--%>
                <%--</div>--%>
                <%--&lt;%&ndash;二页&ndash;%&gt;--%>
                <%--<div class="layui-tab-item">--%>
                    <%--<table class="layui-table" lay-even="" lay-skin="nob">--%>
                        <%--<colgroup>--%>
                            <%--<col width="150">--%>
                            <%--<col width="150">--%>
                            <%--<col width="200">--%>
                            <%--<col>--%>
                        <%--</colgroup>--%>
                        <%--<thead>--%>
                        <%--<tr>--%>
                            <%--<th>预约次数</th>--%>
                            <%--<th>不良记录</th>--%>
                            <%--<th>时间统计</th>--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <%--<tbody>--%>
                        <%--<tr>--%>
                            <%--<td>1次</td>--%>
                            <%--<td>0次</td>--%>
                            <%--<td>2小时</td>--%>
                        <%--</tr>--%>
                        <%--</tbody>--%>
                    <%--</table>--%>
                <%--</div>--%>
                <%--&lt;%&ndash;三页&ndash;%&gt;--%>
                <%--<div class="layui-tab-item">--%>
                    <%--<table class="layui-table" lay-even="" lay-skin="nob">--%>
                        <%--<colgroup>--%>
                            <%--<col width="150">--%>
                            <%--<col width="150">--%>
                            <%--<col width="200">--%>
                            <%--<col>--%>
                        <%--</colgroup>--%>
                        <%--<thead>--%>
                        <%--<tr>--%>
                            <%--<th>预约次数</th>--%>
                            <%--<th>不良记录</th>--%>
                            <%--<th>时间统计</th>--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <%--<tbody>--%>
                        <%--<tr>--%>
                            <%--<td>2次</td>--%>
                            <%--<td>1次</td>--%>
                            <%--<td>5小时</td>--%>
                        <%--</tr>--%>
                        <%--</tbody>--%>
                    <%--</table>--%>
                <%--</div>--%>
                <%--&lt;%&ndash;四页&ndash;%&gt;--%>
                <%--<div class="layui-tab-item">--%>
                    <%--<table class="layui-table" lay-even="" lay-skin="nob">--%>
                        <%--<colgroup>--%>
                            <%--<col width="150">--%>
                            <%--<col width="150">--%>
                            <%--<col width="200">--%>
                            <%--<col>--%>
                        <%--</colgroup>--%>
                        <%--<thead>--%>
                        <%--<tr>--%>
                            <%--<th>预约次数</th>--%>
                            <%--<th>不良记录</th>--%>
                            <%--<th>时间统计</th>--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <%--<tbody>--%>
                        <%--<tr>--%>
                            <%--<td>12次</td>--%>
                            <%--<td>11次</td>--%>
                            <%--<td>20小时</td>--%>
                        <%--</tr>--%>
                        <%--</tbody>--%>
                    <%--</table>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
</div>

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

<script type="text/javascript">
    window.onload = function () {
        getweekData();
    };

    function getweekData() {

        var charts = echarts.init(document.getElementById('main'));

        charts.showLoading({text: '正在努力的读取数据中...'});    //数据加载完之前先显示一段简单的loading动画
        var weeks=[]; //所有月份
        var learntimes=[];//学习时长
        var alllearns=[];//学习次数
        var undeal=[]; //失信次数

        $.ajax({
            type: "post",
            async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: "${pageContext.request.contextPath }/view/findUserBookWeek.form",    //getTrendData
            data: "sno=${users.sno}",
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

                    charts.hideLoading();    //隐藏加载动画

                    var alllearnoption = {
                        title: {
                            text: '',
                            subtext: ''
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['学习时长','学习次数','失信次数']
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
                            },
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

                    charts.setOption(alllearnoption);
                }
            },
            error: function (errorMsg) {
                //请求失败时执行该函数
                alert("图表请求数据失败!");

                charts.hideLoading();
            }
        });
    }


</script>

</body>
</html>
