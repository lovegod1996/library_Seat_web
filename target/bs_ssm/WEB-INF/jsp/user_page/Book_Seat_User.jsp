<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/11
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
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
        body {
            width: 90%;
            margin-left: 5%;
        }
    </style>

    <script language="JavaScript" type="text/javascript">
        //定义了楼层的二维数组，里面的顺序跟楼的顺序是相同的。通过selectedIndex获得楼的下标值来得到相应的楼层数组


        function getData() {
            var floor = $("#floor").val();
            var url = "/LS/jsp/book_Seat_User?floor=" + floor;
            window.location.href = encodeURI(url);
        }
    </script>

    <%--自动填充座位号--%>
    <script language="javascript">
        function getTableContent(node) {
//            按钮的父节点的父节点是tr。
            var tr1 = node.parentNode.parentNode;
//            alert(tr1.rowIndex);
//            alert(tb1.rows[tr1.rowIndex].cells[1].getElementsByTagName("INPUT")[0].value);
//            document.getElementById("seatNum").innerHTML = tb1.rows[tr1.rowIndex].cells[1].getElementsByTagName("INPUT")[0].value;

//            document.getElementById("seatNum").innerHTML = document.getElementsByName("theseat").innerHTML;
//            var objL = document.getElementById("LeftView");
//            var objR = document.getElementById("RightView");
//            objL.style.cssText = "width:65%;float:left";
//            objR.style.cssText = "display:block;float:left;margin-left: 30px;";

//            document.getElementById("seatNum").innerHTML = document.getElementById("theseat").innerHTML;
//            alert(tr1.getElementsByClassName("seatnumber")[0].innerHTML);
            document.getElementById("seatNum").innerHTML = tr1.getElementsByClassName("seatnumber")[0].innerHTML;

        }
    </script>

    <style>
        .Roundseat {
            width: 128px;
            height: 128px;
            border-radius: 64px;
            background-color: #316CB2;
            text-align: center;
            padding: 10px;
            color: #ffffff;
            float: left;
            margin-left: 10px;
        }

        .seatView_lable {
            font-size: 12px;
        }

        .LeftView {
            width: 65%;
            margin-right: 20px;
        }

        .RightView {
            width: 30%;
            height: 100%;
            margin-left: 20px;
            position: fixed;
            top: 0;
            right: 70px;
        }

        .dropdown-menu {
            background: #000;
            opacity: 0.8;
        }

        .dropdown-menu li {
            color: #ffffff;
            padding-left: 10px;
            margin-top: 10px;
        }
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
        .Roundseat {
            width: 128px;
            height: 128px;
            border-radius: 64px;
            background-color: #316CB2;
            text-align: center;
            padding: 15px;
            color: #ffffff;
            float: left;
            margin: 25px;
        }
        .dropdown-menu {
            width: 50px;
            margin-top: 50px;
            margin-left: -30px;
        }
        .RightView {
            width: 30%;
            height: 100%;
            margin-left: 20px;
            position: absolute;
            top: 15px;
            right: 70px;
        }
    </style>

    <script>
        $(document).ready(function () {
            $("span").hover(function () {//鼠标悬停触发事件
                $(".dropdown-toggle").dropdown('toggle');
            });
        });
    </script>
</head>
<body>
<div id="wrapper">
    <div class="LeftView" id="LeftView">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-bar-chart-o fa-fw"></i> 空闲座位统计
                <font id="nowtime" style="margin-left: 20px"></font>
                <a href="${pageContext.request.contextPath }/jsp/book_Seat_User?fid=${fid}&day=${day}"
                   class="btn btn-success btn-xs"
                   style="margin-left:20px ">点击刷新</a>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body" style="height: 100%">
                <c:forEach items="${seatsbooks}" var="seat">
                    <div class="Roundseat">
                        <label class="seatView_lable"><span class="seatnumber">${seat.seatnumber}</span> </label>
                        <label class="seatView_lable">${seat.leftside==0?"左":"右"}侧${seat.row}排${seat.columns}列</label>
                        <label class="seatView_lable">
                            <c:if test="${seat.seatStatue==0}">
                                空闲
                            </c:if>
                            <c:if test="${seat.seatStatue==1}">
                                预约时间内
                            </c:if>
                            <c:if test="${seat.seatStatue==2}">
                                正在学习
                            </c:if>
                            <c:if test="${seat.seatStatue==3}">
                                临时离开
                            </c:if>
                        </label>
                        <div class="dropdown">
                        <span class="dropdown-toggle btn btn-success btn-sm" type="button" data-toggle="dropdown"
                              onclick="getTableContent(this)">点击预约</span>
                            <ul class="dropdown-menu">
                                <c:forEach items="${seat.bookings}" var="booking">
                                    <li>${booking.sno}&nbsp;&nbsp;&nbsp;<fmt:formatDate
                                            value="${booking.bstime}"
                                            pattern="yyyy-MM-dd HH:mm:ss"/>--<fmt:formatDate
                                            value="${booking.betime}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;&nbsp;
                                        <c:if test="${booking.statue==0}">
                                            未入座
                                        </c:if>
                                        <c:if test="${booking.statue==1}">
                                            入座
                                        </c:if>
                                        <c:if test="${booking.statue==2}">
                                            临时离开
                                        </c:if>
                                        <c:if test="${booking.statue==3}">
                                            离开
                                        </c:if></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>
        <!-- /.panel -->
    </div>

    <div class="RightView" id="RightView">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-bell fa-fw"></i> 预约座位
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body" style="height: 98%">

                <form class="form-horizontal" role="form"
                      action="<%= request.getContextPath()%>/jsp/bookSeatUserSub" method="post"
                      onsubmit="getnum(this)">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">座位号</label>
                        <div class="col-sm-9">
                            <label type="text" class="layui-input" id="seatNum"
                                   style="width: 220px">点击预约自动填充</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">开始</label>
                        <div class="col-sm-9">
                            <div class="layui-inline">
                                <input type="hidden" name="day" value="${day}">
                                <input class="layui-input" name="stime" placeholder="开始时间" style="width: 220px"
                                       required
                                       onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss',min: laydate.now(0), max: laydate.now(+1)})">
                                <%--now(0)表示今天；now(1)表示明天,限制预约只能今天明天--%>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">结束</label>
                        <div class="col-sm-9">
                            <div class="layui-inline">
                                <input class="layui-input" name="etime" placeholder="结束时间" style="width: 220px"
                                       required
                                       onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss',min: laydate.now(0), max: laydate.now(+1)})">
                            </div>

                        </div>
                    </div>
                    <button class="btn btn-primary" style="width: 30%;margin-left: 30%;margin-top: 20px">确定
                    </button>
                </form>


                <hr style="margin-top: 50px">
                <h3 style="text-align: center;font-size: 20px;">我的预约</h3>
                <ul>
                    <c:forEach items="${bookings}" var="book" varStatus="step">
                        <c:choose>
                            <c:when test="${step.index==0}">
                                <li style="width: 80%;margin-left:10%;margin-top: 25px">${book.seatnumber}&nbsp;&nbsp;&nbsp;${book.leftside==0?"左":"右"}侧${book.row}排${book.columns}&nbsp;&nbsp;&nbsp;${book.floor}</li>
                            </c:when>
                            <c:otherwise>
                                <li style="width: 80%;margin-left:10%">${book.seatnumber}&nbsp;&nbsp;&nbsp;${book.leftside==0?"左":"右"}侧${book.row}排${book.columns}&nbsp;&nbsp;&nbsp;${book.floor}</li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-4 -->
</div>

<script type="text/javascript">
    function getnum(form) {
        var $form = $(form);
//        var seatNum=$("#seatNum").val();
        var seatNum = document.getElementById("seatNum").innerText;
//        alert(seatNum);
        var editor = "<input type='hidden' name='seatNum' value='" + seatNum + "' />";
        $form.append(editor);
    }

    <c:if test="${!empty error_msg}">alert("${error_msg}");
    </c:if>


    function getCity() {
        $.ajax({
            type: "post",
            async: false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: "${pageContext.request.contextPath }/admin/getfloors.form",    //getTrendData
            data: "",
            //  dataType : "json",        //返回数据形式为json
            success: function (result) {
//            alert(result);
                //请求成功时执行该函数内容，result即为服务器返回的json对象
//            result = eval(result);
                var result = eval('(' + result + ')');
                if (result) {

                    //获得南北楼下拉框的对象
                    var sltProvince = document.selectform.building;
                    //获得楼层下拉框的对象
                    var sltCity = document.selectform.floor;
                    //得到对应楼的楼层数组
                    var south = [];
                    var north = [];
                    south = result.south;
//                alert(south);
                    north = result.north;

                    var city = [
                        south,
                        north
                    ];
                    var provinceCity = city[sltProvince.selectedIndex - 1];
                    //清空楼层下拉框，仅留提示选项
                    sltCity.length = 1;
                    //将楼层数组中的值填充到楼下拉框中
                    for (var i = 0; i < provinceCity.length; i++) {
                        sltCity[i + 1] = new Option(provinceCity[i], provinceCity[i]);
                    }
                }
            },
            error: function (errorMsg) {
                //请求失败时执行该函数
                alert("请求数据失败!");
            }
        });
    }

</script>

</body>
</html>