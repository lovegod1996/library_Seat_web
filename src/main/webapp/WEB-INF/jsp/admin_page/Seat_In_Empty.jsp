
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
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script language="JavaScript" type="text/javascript">
        //定义了楼层的二维数组，里面的顺序跟楼的顺序是相同的。通过selectedIndex获得楼的下标值来得到相应的楼层数组
        var city = [
            ["南1", "南2", "南3", "南4"],
            ["北1", "北2", "北3", "北4"]
        ];

        function getCity() {
            //获得南北楼下拉框的对象
            var sltProvince = document.selectform.building;
            //获得楼层下拉框的对象
            var sltCity = document.selectform.floor;
            //得到对应楼的楼层数组
            var provinceCity = city[sltProvince.selectedIndex - 1];

            //清空楼层下拉框，仅留提示选项
            sltCity.length = 1;

            //将楼层数组中的值填充到楼下拉框中
            for (var i = 0; i < provinceCity.length; i++) {
                sltCity[i + 1] = new Option(provinceCity[i], provinceCity[i]);
            }
        }

        function getData() {
            var floor = $("#floor").val();
            var url = "/Lseat/jsp/seat_In_Empty?floor=" + floor;
            window.location.href = encodeURI(url);
        }

    </script>
</head>
<body>
<div class="col-sm-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i> 空闲座位统计
            <a href="${pageContext.request.contextPath }/jsp/seat_In_Empty" class="btn btn-success btn-xs"
               style="margin-left:20px ">点击刷新</a>
            <div class="pull-right">
                <FORM METHOD=POST ACTION="" name="selectform">
                    <SELECT NAME="building" onChange="getCity()">
                        <OPTION VALUE="0">选择南北楼</OPTION>
                        <OPTION VALUE="南楼">南楼</OPTION>
                        <OPTION VALUE="北楼">北楼</OPTION>
                    </SELECT>
                    <SELECT NAME="floor" onchange="getData()" id="floor">
                        <OPTION VALUE="0">选择所在楼层</OPTION>
                        <OPTION VALUE="0">${floor}</OPTION>
                    </SELECT>
                </FORM>
            </div>
        </div>
        <!-- /.panel-heading -->
        <div class="table table-condensed">
            <table class="table">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>座位号</th>
                    <th>特别</th>
                    <th>座位状态</th>
                    <th>修改</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${seats}" var="seat">
                    <tr>
                        <td>${seat.sid}</td>
                        <td>${seat.seatnumber}</td>
                        <td>${seat.seattype}</td>
                        <td>空闲中</td>
                        <td>
                            <button type="button" class="btn btn-danger btn-sm">操作</button>
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
                            <a href="${pageContext.request.contextPath }/jsp/seat_In_Empty.form?page=${currentPage-1}&floor=${floor}">&laquo;</a>
                        </c:if>
                    </li>
                    <c:if test="${currentPage==1}">
                        <li class="active"><a href="#">1</a></li>
                    </c:if>
                    <c:if test="${currentPage!=1}">
                        <li>
                            <a href="${pageContext.request.contextPath }/jsp/seat_In_Empty.form?page=1&floor=${floor}">1</a>
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
                                <a href="${pageContext.request.contextPath }/jsp/seat_In_Empty.form?page=<%=i+1%>&floor=${floor}"><%=i + 1%>
                                </a></li>
                        </c:if>
                    </c:if>
                    <% } %>
                    <c:if test="${currentPage == pageTimes}">
                        <li>    <a href="#" class="active">&raquo;</a> </li>
                    </c:if>
                    <c:if test="${currentPage != pageTimes}">
                        <li>  <a href="${pageContext.request.contextPath }/jsp/seat_In_Empty.form?page=${currentPage+1}&floor=${floor}">&raquo;</a>  </li>
                    </c:if>

                </ul>
            </div>

        </div>
    </div>
    <!-- /.panel -->
</div>
<!-- /.col-sm-12 -->



<!-- jQuery -->
<script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%= request.getContextPath()%>/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%= request.getContextPath()%>/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%= request.getContextPath()%>/dist/js/sb-admin-2.js"></script>

<script src="<%=request.getContextPath()%>/layui/layui.js"></script>
<script>
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句

//触发事件
        var active = {
            notice: function () {
//示范一个公告层
                layer.open({
                    type: 2
                    ,
                    title: ['立即入座', 'font-size:18px;text-align:center;background:#32AA9F']
                    ,
                    closeBtn: false
                    ,
                    area: ['400px','550px']
                    ,
                    shade: 0
                    ,
                    id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,
                    btn: ['关闭']
                    ,
                    moveType: 0 //拖拽模式，0或者1
                    ,
                    content: ['/Library_Seat/view/seat_Now', 'no']
                    ,
                    success: function (layero) {
                        var btn = layero.find('.layui-layer-btn');
                        btn.css('text-align', 'center');
                        btn.class('layui-btn layui-btn-primary')
                    }
                });
            }
        };

        $('.layui-btn').on('click', function () {
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, othis) : '';
        });

    });
</script>
</body>
</html>
