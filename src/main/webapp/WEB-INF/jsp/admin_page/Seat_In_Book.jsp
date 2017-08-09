<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/11
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <!-- jQuery -->
    <script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%= request.getContextPath()%>/vendor/bootstrap/js/bootstrap.min.js"></script>
    <style>
        .black_overlay {
            display: none;
            position: absolute;
            top: 0%;
            left: 0%;
            width: 100%;
            height: 100%;
            background-color: black;
            z-index: 1001;
            -moz-opacity: 0.8;
            opacity: .80;
            filter: alpha(opacity=80);
        }

        .white_content {
            display: none;
            position: absolute;
            top: 20%;
            left: 32%;
            width: 400px;
            height: 300px;
            padding: 16px;
            border: 1px solid orange;
            background-color: white;
            z-index: 1002;
            overflow: auto;
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
    </style>
    <script language="JavaScript" type="text/javascript">
        //定义了楼层的二维数组，里面的顺序跟楼的顺序是相同的。通过selectedIndex获得楼的下标值来得到相应的楼层数组

        function getData() {
            var floor = $("#floor").val();
            var url = "/LS/jsp/seat_In_Book?floor=" + floor;
            window.location.href = encodeURI(url);
        }
    </script>
    <script language="JavaScript" type="text/javascript">
        function getTableContent(node) {
            // 按钮的父节点的父节点是tr。
            var tr1 = node.parentNode.parentNode;
//            alert(tr1.rowIndex);//获得行
            //alert(tb1.rows[tr1.rowIndex].cells[1].getElementsByTagName("INPUT")[0].value);
            document.getElementById("seatNum").innerHTML = tb1.rows[tr1.rowIndex].cells[1].innerHTML;
//            alert(document.getElementById("seatNum").innerHTML);
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

</head>
<body>
<div class="col-sm-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i> 预约中座位统计
            <a href="${pageContext.request.contextPath }/jsp/seat_In_Book" class="btn btn-success btn-xs"
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
        <div class="layui-form">
            <table class="layui-table" id="tb1">
                <thead>
                <tr>
                    <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
                    <th>座位编号</th>
                    <th>位置</th>
                    <th>当前预约状况</th>
                    <th>当前座位状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${seatbooks}" var="seat">
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>${seat.seatnumber}</td>
                        <td>${seat.leftside==0?"左":"右"}侧${seat.row}排${seat.columns}列</td>
                        <td>
                            <div class="layui-collapse" lay-accordion="">
                                <div class="layui-colla-item">
                                    <h2 class="layui-colla-title">当前已预约${seat.bookings.size()}人</h2>
                                    <div class="layui-colla-content">
                                        <ul>
                                            <c:forEach items="${seat.bookings}" var="booking">
                                                <li>${booking.sno}&nbsp;&nbsp;&nbsp;<fmt:formatDate
                                                        value="${booking.bstime}"
                                                        pattern="yyyy-MM-dd HH:mm:ss"/>--<fmt:formatDate
                                                        value="${booking.betime}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;&nbsp;
                                                    <c:if test="${booking.statue==0}">
                                                        预约
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
                            </div>

                                <%--<table class="table">--%>
                                <%--<thead>--%>
                                <%--<th>预约人</th>--%>
                                <%--<th>预约时间段</th>--%>
                                <%--<th>当前状态</th>--%>
                                <%--</thead>--%>
                                <%--<tbody>--%>
                                <%--<c:forEach items="${seat.bookings}" var="booking">--%>
                                <%--<tr>--%>
                                <%--<td>${booking.sno}</td>--%>
                                <%--<td><fmt:formatDate value="${booking.bstime}"--%>
                                <%--pattern="yyyy-MM-dd HH:mm:ss"/>-<fmt:formatDate--%>
                                <%--value="${booking.betime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
                                <%--<td>--%>
                                <%--<c:if test="${booking.statue==0}">--%>
                                <%--未入座--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${booking.statue==1}">--%>
                                <%--入座--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${booking.statue==2}">--%>
                                <%--临时离开--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${booking.statue==3}">--%>
                                <%--离开--%>
                                <%--</c:if>--%>
                                <%--</td>--%>
                                <%--</tr>--%>
                                <%--</c:forEach>--%>
                                <%--</tbody>--%>
                                <%--</table>--%>
                        </td>
                        <td>
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
                        </td>
                        <td>
                            <c:if test="${seat.seatStatue==0}">
                                <button class="layui-btn layui-btn-small"
                                        onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block';getTableContent(this);">
                                    入座
                                </button>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${ nullList != null}">
                    <tr style="text-align: center">
                        <td colspan="10">${nullList}</td>
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
                            <a href="${pageContext.request.contextPath }/jsp/seat_In_Book.form?page=${currentPage-1}">&laquo;</a>
                        </c:if>
                    </li>
                    <c:if test="${currentPage==1}">
                        <li class="active"><a href="#">1</a></li>
                    </c:if>
                    <c:if test="${currentPage!=1}">
                        <li>
                            <a href="${pageContext.request.contextPath }/jsp/seat_In_Book.form?page=1">1</a>
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
                                <a href="${pageContext.request.contextPath }/jsp/seat_In_Book.form?page=<%=i+1%>"><%=i + 1%>
                                </a></li>
                        </c:if>
                    </c:if>
                    <% } %>
                    <c:if test="${currentPage == pageTimes}">
                        <li><a href="#" class="active">&raquo;</a></li>
                    </c:if>
                    <c:if test="${currentPage != pageTimes}">
                        <li>
                            <a href="${pageContext.request.contextPath }/jsp/seat_In_Book.form?page=${currentPage+1}">&raquo;</a>
                        </li>
                    </c:if>

                </ul>
            </div>

        </div>
    </div>
    <!-- /.panel -->
</div>
<!-- /.col-sm-12 -->
<%--添加弹出框--%>
<div id="light" class="white_content">
    <div style="text-align: end;width: 100%">
        <a class="layui-icon" href="javascript:void(0)"
           onclick="document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">
            &#x1006;</a>
    </div>
    <form class="layui-form" role="form" action="<%= request.getContextPath()%>/jsp/bookEmptSeat"
          method="post" onsubmit="getnum(this);">
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">座位号</label>
            <div class="layui-input-block">
                <label type="text" class="layui-input" id="seatNum" style="width: 220px">点击入座自动填入</label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">学号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="sno" id="checkstudentid" required
                       placeholder="请输入学号" style="width: 220px">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">预计结束</label>
            <div class="layui-input-block">
                <div class="layui-inline">
                    <input class="layui-input" name="etime" placeholder="结束时间" style="width: 220px" required
                           onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss',min: laydate.now(0), max: laydate.now(+1)})">
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">确定</button>
    </form>
</div>
<div id="fade" class="black_overlay"></div>

<!-- jQuery -->
<script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%= request.getContextPath()%>/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%= request.getContextPath()%>/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%= request.getContextPath()%>/dist/js/sb-admin-2.js"></script>

<script>
    layui.use(['element', 'layer'], function () {
        var element = layui.element();
        var layer = layui.layer;

        //监听折叠
        element.on('collapse(test)', function (data) {
            layer.msg('展开状态：' + data.show);
        });
    });
</script>

<script>
    layui.use('form', function () {
        var $ = layui.jquery, form = layui.form();

        //全选
        form.on('checkbox(allChoose)', function (data) {
            //language=JQuery-CSS
            var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
            child.each(function (index, item) {
                item.checked = data.elem.checked;
            });
            form.render('checkbox');
        });
    });
</script>

<script type="text/javascript">

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
</script>
</body>
</html>
