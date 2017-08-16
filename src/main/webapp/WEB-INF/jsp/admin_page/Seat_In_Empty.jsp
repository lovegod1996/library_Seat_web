<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/11
  Time: 14:16
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
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>

    <!-- jQuery -->
    <script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%= request.getContextPath()%>/vendor/bootstrap/js/bootstrap.min.js"></script>

    <script src="<%= request.getContextPath()%>/js/combodate.js"></script>
    <script src="<%= request.getContextPath()%>/js/moment.js"></script>

    <%--验证表单--%>
    <script src="<%=request.getContextPath()%>/js/checkForm.js"></script>

    <script language="JavaScript" type="text/javascript">
        //定义了楼层的二维数组，里面的顺序跟楼的顺序是相同的。通过selectedIndex获得楼的下标值来得到相应的楼层数组
        function getData() {
            var floor = $("#floor").val();
            var url = "/LS/jsp/seat_In_Empty?floor=" + floor;
            window.location.href = encodeURI(url);
        }
    </script>

    <%--自动填充座位号--%>
    <script language="javascript">
        function getTableContent(node) {
            // 按钮的父节点的父节点是tr。
            var tr1 = node.parentNode.parentNode;
//            alert(tr1.rowIndex);
//            alert(tb1.rows[tr1.rowIndex].cells[1].getElementsByTagName("INPUT")[0].value);
            document.getElementById("seatNum").innerHTML = tb1.rows[tr1.rowIndex].cells[1].getElementsByTagName("INPUT")[0].value;
        }
    </script>

    <%--选择日期--%>
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
        });
    </script>

    <script>
        $(function(){
            $('#time').combodate({
                firstItem: 'name', //show 'hour' and 'minute' string at first item of dropdown
                minuteStep: 1
            });
        });
    </script>

    <%--<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>--%>
    <script>
        var flag = {
            "sno":false,
        };

        $(function(){

            $("#checkstudentid").blur(function(){
                // 学号校验
                var sno = $(this).val();

                // 校验规则，可调整
                var pattern = /\b(^[0-9]{1,20}$)\b/;
                if(!pattern.test(sno)){
                    $("#sno\\.info").html("格式错误");
                    return;
                }else{
                    $("#sno\\.info").html("");
                    flag.sno = true;
                }
            });

        })
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
        .table .table {
            background-color: #fff;
            font-size: 14px;
            color: #555;
        }
        .panel-default {
            border-color: #fff;
            height: 500px;
        }
        .layui-form-label {
            float: left;
            display: block;
            padding: 9px 15px;
            width: 100px;
            font-weight: 400;
            text-align: right;
        }
        .panel-body {
            padding: 70px;
        }
        .btn1 {
            display: inline-block;
            padding: 6px 20px;
            margin-bottom: 0;
            margin-left: 180px;
            font-size: 14px;
            font-weight: 400;
            line-height: 1.42857143;
            text-align: center;
            white-space: nowrap;
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="col-sm-6">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i> 空闲座位统计
            <a href="${pageContext.request.contextPath }/jsp/seat_In_Empty" class="btn btn-success btn-xs"
               style="margin-left:20px ">点击刷新</a>
            <%--<div class="pull-right">--%>
                <%--<FORM METHOD=POST ACTION="" name="selectform">--%>
                    <%--<SELECT NAME="building" onChange="getCity()">--%>
                        <%--<OPTION VALUE="0">选择南北楼</OPTION>--%>
                        <%--<OPTION VALUE="南楼">南楼</OPTION>--%>
                        <%--<OPTION VALUE="北楼">北楼</OPTION>--%>
                    <%--</SELECT>--%>
                    <%--<SELECT NAME="floor" onchange="getData()" id="floor">--%>
                        <%--<OPTION VALUE="0">选择所在楼层</OPTION>--%>
                        <%--<OPTION VALUE="0">${floor}</OPTION>--%>
                    <%--</SELECT>--%>
                <%--</FORM>--%>
            <%--</div>--%>
        </div>
        <!-- /.panel-heading -->
        <div class="table table-condensed">
            <table class="table" id="tb1">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>&nbsp;&nbsp;座位号</th>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;位置</th>
                    <th>座位状态</th>
                    <th>&nbsp;&nbsp;入座</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${seats}" var="seat" varStatus="step">
                    <tr>
                        <td>${step.index+1}</td>
                            <%--<td>${seat.seatnumber}</td>--%>
                        <td><input type="text" disabled="disabled" name="INPUT"
                                   style="border:none;background-color: transparent;width: 100px"
                                   value=${seat.seatnumber}></td>
                        <td>${seat.leftside==0?"左":"右"}侧${seat.row}排${seat.columns}列</td>
                        <td>空闲中</td>
                        <td>
                            <button type="button" id="inSeat" class="btn btn-primary btn-sm" onclick="getTableContent(this)">入座
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${ nullList != null}">
                    <tr style="text-align: center">
                        <td colspan="5">${nullList}</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
            <div style="text-align: center">
                <ul class="pagination">
                    <li>
                        <c:if test="${currentPage ==1}">
                            <a href="#" class="disabled">&laquo;</a>
                        </c:if>
                        <c:if test="${currentPage != 1}">
                            <a href="${pageContext.request.contextPath }/jsp/seat_In_Empty.form?page=${currentPage-1}">&laquo;</a>
                        </c:if>
                    </li>
                    <c:if test="${currentPage==1}">
                        <li class="active"><a href="#">1</a></li>
                    </c:if>
                    <c:if test="${currentPage!=1}">
                        <li>
                            <a href="${pageContext.request.contextPath }/jsp/seat_In_Empty.form?page=1">1</a>
                        </li>
                    </c:if>
                    <%
                        int pageTimes = (Integer) session.getAttribute("pageTimes");
                        for (int i = 1; i < pageTimes; i++) {

                            request.setAttribute("page", i + 1);
                            pageContext.setAttribute("i", i);
                    %>

                    <c:if test="${i<(currentPage+5)&&i>(currentPage-5)}">
                        <c:if test="${currentPage == page}">
                            <li><a href="#" class="active"><%=i + 1%>
                            </a></li>
                        </c:if>
                        <c:if test="${currentPage != page}">
                            <li>
                                <a href="${pageContext.request.contextPath }/jsp/seat_In_Empty.form?page=<%=i+1%>"><%=i + 1%>
                                </a></li>
                        </c:if>
                    </c:if>
                    <% } %>
                    <c:if test="${currentPage == pageTimes}">
                        <li><a href="#" class="active">&raquo;</a></li>
                    </c:if>
                    <c:if test="${currentPage != pageTimes}">
                        <li>
                            <a href="${pageContext.request.contextPath }/jsp/seat_In_Empty.form?page=${currentPage+1}">&raquo;</a>
                        </li>
                    </c:if>

                </ul>
            </div>

        </div>
    </div>
    <!-- /.panel -->
</div>
<div class="col-sm-6">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-bell fa-fw"></i> 预约座位
        </div>
        <!-- /.panel-heading -->
        <div class="panel-body" style="height: 400px">
            <form class="layui-form" role="form" action="<%= request.getContextPath()%>/jsp/adSeatBookSub"
                  method="post" onsubmit="getnum(this);">
                <div class="layui-form-item">
                    <label class="layui-form-label">座位号</label>
                    <div class="layui-input-block">
                        <label type="text" class="layui-input" id="seatNum" style="width: 220px">点击入座自动填入</label>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">学号</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="sno" id="checkstudentid" required
                               placeholder="请输入学号" style="width: 220px" >
                        <span id="sno.info" style="color:red"></span>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">预计结束</label>
                    <div class="layui-input-block">
                        <div class="layui-inline">
                            <%--<input class="layui-input" name="etime" placeholder="结束时间" style="width: 220px" required--%>
                                   <%--onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss',min: laydate.now(0), max: laydate.now(+1)})">--%>
                                <input name="etime" type="text" id="time" data-format="HH:mm" data-template="HH : mm" style="width: 220px">
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn1 btn-primary" >确定</button>
            </form>
        </div>
        <!-- /.panel-body -->
    </div>
    <!-- /.panel -->
</div>
<!-- /.col-sm-12 -->

<script type="text/javascript">
    function getnum(form) {
        var $form = $(form);
//        var seatNum=$("#seatNum").val();
        var seatNum = document.getElementById("seatNum").innerText;
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
                    var  south=[];
                    var north=[];
                    south=result.south;
//                alert(south);
                    north=result.north;

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
