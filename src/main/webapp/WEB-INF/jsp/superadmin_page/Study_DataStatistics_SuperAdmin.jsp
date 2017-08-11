<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/21
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>学习统计</title>
    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <!-- jQuery -->
    <script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

    <%--<script language="javascript">--%>
    <%--function getTableContent(node) {--%>
    <%--// 按钮的父节点的父节点是tr。--%>
    <%--var tr1 = node.parentNode.parentNode;--%>
    <%--//获得图书馆名称，传值至详情页面--%>
    <%--var buildingname;--%>
    <%--buildingname = tb1.rows[tr1.rowIndex].cells[1].innerText;--%>
    <%--return buildingname;--%>
    <%--}--%>
    <%--</script>--%>
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
        .layui-table thead tr {
            background-color: #eff3f8;
        }
        .layui-table {
            width: 90%;
            margin: 10px 5%;
            background-color: #fff;
        }
    </style>
</head>
<body>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">学习情况统计</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <table class="layui-table" id="tb1">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>院系名称</th>
                    <th>院系人数</th>
                    <th>学习时间</th>
                    <th>学习次数</th>
                    <th>失信次数</th>
                    <th>失信率</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${collegeDatas}" var="collegedata" varStatus="step">
                    <tr>
                        <td>${step.index+1}</td>
                        <td><a href="<%=request.getContextPath()%>/view/getmajorlearn?college=${collegedata.venue}">${collegedata.venue}</a> </td>
                        <td>${collegedata.students}</td>
                        <td>${collegedata.learntime}小时</td>
                        <td>${collegedata.allLearn}</td>
                        <td>${collegedata.undeal}</td>
                        <td>${collegedata.dealpro}%</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div id="main" style="width: 100%;height:400px;"></div>
        </div>
    </div>
</div>

<%--echart--%>
<script src="<%=request.getContextPath()%>/js/echarts-all.js"></script>
<script src="<%=request.getContextPath()%>/js/macarons.js"></script>

<script type="text/javascript">
    window.onload = function () {
        getData();
    };

    function getData() {
        var charts = echarts.init(document.getElementById('main'));

        charts.showLoading({text: '正在努力的读取数据中...'});    //数据加载完之前先显示一段简单的loading动画
        var colleges = []; //所有月份
        var learntimes = [];//学习时长
        var alllearns = [];//学习次数
        var undeal = []; //失信次数

        $.ajax({
            type: "post",
            async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: "${pageContext.request.contextPath }/view/getcollegelearn.form",    //getTrendData
            data: "",
            //  dataType : "json",        //返回数据形式为json
            success: function (result) {
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                result = eval(result);
                if (result) {
                    for (var i = 0; i < result.length; i++) {
                        colleges.push(result[i].venue);    //挨个取出类别并填入类别数组
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


                    var collegeoption = {
                        title: {
                            text: '',
                            subtext: ''
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['学习时长', '学习次数', '失信次数']
                        },
                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                data: colleges
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
                                type: 'bar',
                                data: learntimes
                            },
                            {
                                name: '学习次数',
                                type: 'bar',
                                data: alllearns
                            }, {
                                name: '失信次数',
                                type: 'bar',
                                data: undeal
                            }
                        ]
                    };


                    charts.setOption(collegeoption);
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