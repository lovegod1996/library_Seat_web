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
    <script src="<%=request.getContextPath()%>/js/timer.js"></script>
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
            height: 100%;
            margin-right: 20px;
        }

        .RightView {
            width: 30%;
            height: 100%;
            margin-left: 20px;
            position: fixed;
            right: 70px;
            top: 0;
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

        .panel-default > .panel-heading {
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

        .dropdown-menu {
            width: 100px;
            margin-top: -100px;
            margin-left: -30px;
        }

        .dropdown-menu {
            background: #4d5b69;
            opacity: 0.9;
        }

    </style>

    <script>
        $(document).ready(function () {
            $("span").click(function () {//鼠标悬停触发事件
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
                <i class="fa fa-bar-chart-o fa-fw"></i> 座位查看
                <font id="nowtime" style="margin-left: 20px"></font>
                <a href="${pageContext.request.contextPath }/jsp/book_Seat_User?fid=${fid}&day=${day}"
                   class="btn btn-success btn-xs"
                   style="margin-left:20px ">点击刷新</a>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body" style="height: auto">
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
                                    <li><fmt:formatDate
                                            value="${booking.bstime}"
                                            pattern="HH:mm"/>--<fmt:formatDate
                                            value="${booking.betime}" pattern="HH:mm"/>&nbsp;&nbsp;&nbsp;
                                        <c:if test="${booking.statue==0}">
                                            已预约
                                        </c:if>
                                        <c:if test="${booking.statue==1}">
                                            已入座
                                        </c:if>
                                        <c:if test="${booking.statue==2}">
                                            暂离开
                                        </c:if>
                                        <c:if test="${booking.statue==3}">
                                            已离开
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
            <div style="margin: 25px;font-size:14px;line-height: 150%;color: #757575;">
                <h4 style="color:#5c9bd1;margin-left:-10px;margin-bottom: 10px;">使用规则：</h4>
                1、请正确登录本系统后开始预约操作。
                <br>
                2、本系统采用更加精确的自定义分时预约机制，同学们预约的时候一定要注意不要预约重复的时间段，点击“点击预约”按钮，即可看到该座位的当前预约时间段，该座位信息会自动传到右边输入框内，请同学们按需输入预计学习的时间段。
                <br>
                3、如果预约失败，请检查当前场馆是否已经关闭，或是预约的时间已经被预约，或是自己处于被惩罚的时间段内。
                <br>
                4、请大家自觉遵守预约规则，按时入座、离座，禁止撕毁、涂改二维码等信息。
                <br>
            </div>
            <hr>
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
                            <input type="hidden" name="fid" value="${fid}">
                            <label type="text" class="layui-input" id="seatNum"
                                   style="width: 220px">点击预约自动填充</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">日期</label>
                        <div class="col-sm-9">
                            <div class="layui-inline">
                                <label><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">开始</label>
                        <div class="col-sm-9">
                            <div class="layui-inline">
                                <input type="hidden" name="day" value="${day}">
                                <%--<input type="time" class="layui-input" name="stime" placeholder="开始时间"--%>
                                <%--style="width: 220px" required>--%>
                                <div id="timer" class="layui-input"
                                     style="display: none;width: 220px;text-align: center"></div>
                                <input id="Clicktimer" name="" class="layui-input" placeholder="开始时间"
                                       style="width: 220px;"
                                       onclick="showTimer('timer') ;showTIME()"  />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">结束</label>
                        <div class="col-sm-9">
                            <div class="layui-inline">
                                <%--<input type="time" class="layui-input" name="etime" placeholder="结束时间"--%>
                                <%--style="width: 220px" required>--%>
                                <div id="timer1" class="layui-input"
                                     style="display: none;width: 220px;text-align: center"></div>
                                <input id="Clicktimer1" class="layui-input" placeholder="结束时间"
                                       style="width: 220px;"
                                       onclick="showTimer('timer1') ;showTIME1()"/>
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
                                <li style="width: 80%;margin-left:10%;margin-top: 25px">${book.building}&nbsp;&nbsp;&nbsp;${book.leftside==0?"左":"右"}侧${book.row}排${book.columns}&nbsp;&nbsp;&nbsp;${book.floor}</li>
                            </c:when>
                            <c:otherwise>
                                <li style="width: 80%;margin-left:10%">${book.building}&nbsp;&nbsp;&nbsp;${book.leftside==0?"左":"右"}侧${book.row}排${book.columns}&nbsp;&nbsp;&nbsp;${book.floor}</li>
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

<script>
    function showTIME() {
        document.getElementById("timer").style.display = "block";
        document.getElementById("Clicktimer").style.display = "none";
    }

    function showTIME1() {
        document.getElementById("timer1").style.display = "block";
        document.getElementById("Clicktimer1").style.display = "none";
    }



    //获取选择的时间
    //    function aa(id) {
    //        alert(returnTimer(id));
    //    }
</script>

<script>
    $(function () {
        $('#time').combodate({
            firstItem: 'name', //show 'hour' and 'minute' string at first item of dropdown
            minuteStep: 1
        });
        $('#time1').combodate({
            firstItem: 'name', //show 'hour' and 'minute' string at first item of dropdown
            minuteStep: 1
        });
    });
</script>

<script type="text/javascript">

    window.onload = function () {
        var url="<%=request.getContextPath()%>/jsp/header_User";
        window.parent.document.getElementById("topFrame_User").src=url;
    };

    function getnum(form) {
        var $form = $(form);
//        var seatNum=$("#seatNum").val();
        var seatNum = document.getElementById("seatNum").innerText;
//        alert(seatNum);
        var stime = returnTimer("timer");
        var etime = returnTimer("timer1");
        var editor = "<input type='hidden' name='seatNum' value='" + seatNum + "' />";
        var editor1 = "<input type='hidden' name='stime' value='" + stime + "' />";
        var editor2 = "<input type='hidden' name='etime' value='" + etime + "' />";
        $form.append(editor);
        $form.append(editor1);
        $form.append(editor2);
    }

    <c:if test="${!empty error_msg}">alert("${error_msg}");
    </c:if>

</script>

</body>
</html>