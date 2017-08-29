<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/21
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>

    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <!-- jQuery -->
    <script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

    <%--导出table到Excel--%>
    <script>
        var tableToExcel = (function () {
            var uri = 'data:application/vnd.ms-excel;base64,'
                ,
                template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" ' +
                    'xmlns="http://www.w3.org/TR/REC-html40">' +
                    '<head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head>' +
                    '<body><table>{table}</table></body></html>'
                , base64 = function (s) {
                    return window.btoa(unescape(encodeURIComponent(s)))
                }
                , format = function (s, c) {
                    return s.replace(/{(\w+)}/g, function (m, p) {
                        return c[p];
                    })
                }
            return function (table, name, filename) {
                if (!table.nodeType) table = document.getElementById("tb")
                var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}

                document.getElementById("dlink").href = uri + base64(format(template, ctx));
                document.getElementById("dlink").download = filename;
                document.getElementById("dlink").click();
            }
        })()
    </script>
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
        .btn-block {
            display: block;
            width: 100px;
            margin: 30px auto;
            background-color: #8895a9;
        }
    </style>
</head>
<body>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">周</li>
        <li class="layui">月</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <form class="layui-form" action="">
                <div class="layui-form-item">
                    <div class="layui-input-inline">
                        <button type="button" class="layui-btn layui-btn-small"
                                onclick="tableToExcel('tablename', 'name', '学习情况统计.xls')">导出
                        </button>
                        <a id="dlink" style="display:none;"></a>
                    </div>
                </div>
            </form>
            <hr>
            <table class="layui-table" lay-skin="line" id="tb">
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
            <div id="learntime" style="width: 100%;height:400px;"></div>
            <br>
            <div id="alllearn" style="width: 100%;height:400px;"></div>

            
        </div>
        <div class="layui-tab-item">
            <form class="layui-form" action="">
                <div class="layui-form-item">
                    <div class="layui-input-inline">
                        <button type="button" class="layui-btn layui-btn-small"
                                onclick="tableToExcel('tablename', 'name', '学习情况统计.xls')">导出
                        </button>
                        <a id="dlinkmonth" style="display:none;"></a>
                    </div>
                </div>
            </form>
            <hr>
            <table class="layui-table" lay-skin="line" id="tbmonth">
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
                <c:forEach items="${monthdatas}" var="monthdata" varStatus="step">
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
            <div id="learntimemonth" style="width: 1257px;height:400px;"></div>
            <br>
            <div id="alllearnmonth" style="width: 1257px;height:400px;"></div>
        </div>
    </div>
</div>


<%--echart--%>
<script src="<%=request.getContextPath()%>/js/echarts-all.js"></script>
<script src="<%=request.getContextPath()%>/js/macarons.js"></script>

<script>
    layui.use(['form'], function () {
    });
</script>

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
    window.onload=function () {
        getData();
        getAlllearn();
        getmonthData();
    };
     function getmonthData() {
         var learntime = echarts.init(document.getElementById('learntimemonth'));
         var alllearn = echarts.init(document.getElementById('alllearnmonth'));
         learntime.showLoading({text: '正在努力的读取数据中...'});    //数据加载完之前先显示一段简单的loading动画
         alllearn.showLoading({text: '正在努力的读取数据中...'});    //数据加载完之前先显示一段简单的loading动画
         var months=[]; //所有月份
         var learntimes=[];//学习时长
         var alllearns=[];//学习次数
         var undeal=[]; //失信次数


         $.ajax({
             type: "post",
             async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
             url: "${pageContext.request.contextPath }/view/getmonthlearntime.form",    //getTrendData
             data: "",
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


    function getAlllearn() {
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('alllearn'));

        myChart.showLoading({text: '正在努力的读取数据中...'});    //数据加载完之前先显示一段简单的loading动画

        var floors = [];  //所有楼层
        var alllearn = [];  //空闲
        var undeal=[];
        $.ajax({
            type: "post",
            async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: "${pageContext.request.contextPath }/view/getweeklearntime.form",    //getTrendData
            data: "",
            //  dataType : "json",        //返回数据形式为json
            success: function (result) {
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                result = eval(result);
                if (result) {
                    for (var i = 0; i < result.length; i++) {
                        floors.push("第"+(i+1)+"周");    //挨个取出类别并填入类别数组
                    }
                    for (var i = 0; i < result.length; i++) {
                        alllearn.push(result[i].allLearn);    //挨个取出销量并填入销量数组
                    }
                    for (var i = 0; i < result.length; i++) {
                        undeal.push(result[i].undeal);    //挨个取出销量并填入销量数组
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
                            data: ['学习次数','失信次数']
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
                                name: '学习次数',
                                type: 'line',
                                data: alllearn
                            },{
                                name: '失信次数',
                                type: 'line',
                                data: undeal
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
    
    
    function getData() {
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('learntime'));

        myChart.showLoading({text: '正在努力的读取数据中...'});    //数据加载完之前先显示一段简单的loading动画

        var floors = [];  //所有楼层
        var learntimes = [];  //空闲

        $.ajax({
            type: "post",
            async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: "${pageContext.request.contextPath }/view/getweeklearntime.form",    //getTrendData
            data: "",
            //  dataType : "json",        //返回数据形式为json
            success: function (result) {
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                result = eval(result);
                if (result) {
                    for (var i = 0; i < result.length; i++) {
                        floors.push("第"+(i+1)+"周");    //挨个取出类别并填入类别数组
                    }
                    for (var i = 0; i < result.length; i++) {
                        learntimes.push(result[i].learntime);    //挨个取出销量并填入销量数组
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
                            data: ['学习时长']
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
                                name: '学习时长',
                                type: 'line',
                                data: learntimes
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
