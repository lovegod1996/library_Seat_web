<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 17:01
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
    <%--操作确认--%>
    <script src="<%=request.getContextPath()%>/js/dialog.js"></script>

    <script language="JavaScript" type="text/javascript">
        //定义了楼层的二维数组，里面的顺序跟楼的顺序是相同的。通过selectedIndex获得楼的下标值来得到相应的楼层数组

        function getData() {
            var floor = $("#floor").val();
            var url = "/LS/jsp/seat_In_Use?floor=" + floor;
            window.location.href = encodeURI(url);
        }
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
<div class="col-sm-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i> 使用中座位统计
            <a href="${pageContext.request.contextPath }/jsp/seat_In_Use" class="btn btn-success btn-xs"
               style="margin-left:20px ">点击刷新</a>
        </div>
        <!-- /.panel-heading -->
        <div class="layui-form">
            <table class="layui-table">
                <thead>
                <tr>
                    <th>学号</th>
                    <th>座位号</th>
                    <th>位置</th>
                    <th>预约时间</th>
                    <th>入座时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${inseats}" var="inseat">
                    <tr>
                        <td>${inseat.sno}</td>
                        <td>${inseat.seatnumber}</td>
                        <td>${inseat.leftside==0?"左":"右"}侧${inseat.row}排${inseat.columns}列</td>
                        <td><fmt:formatDate value="${inseat.bstime}" pattern="yyyy-MM-dd HH:mm:ss"/>-<fmt:formatDate value="${inseat.betime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${inseat.stime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${inseat.statue==1?"入座":"临时离开"}</td>
                        <td>
                            <a href="<%=request.getContextPath()%>/jsp/releaseSeat?bid=${inseat.bid}&type=0" onclick="return confirmAct()">本人释放</a>
                            <a href="<%=request.getContextPath()%>/jsp/releaseSeat?bid=${inseat.bid}&type=1" onclick="return confirmAct()">他人释放</a>
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
                            <a href="${pageContext.request.contextPath }/jsp/seat_In_Use.form?page=${currentPage-1}">&laquo;</a>
                        </c:if>
                    </li>
                    <c:if test="${currentPage==1}">
                        <li class="active"><a href="#">1</a></li>
                    </c:if>
                    <c:if test="${currentPage!=1}">
                        <li>
                            <a href="${pageContext.request.contextPath }/jsp/seat_In_Use.form?page=1">1</a>
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
                                <a href="${pageContext.request.contextPath }/jsp/seat_In_Use.form?page=<%=i+1%>"><%=i + 1%>
                                </a></li>
                        </c:if>
                    </c:if>
                    <% } %>
                    <c:if test="${currentPage == pageTimes}">
                        <li>    <a href="#" class="active">&raquo;</a> </li>
                    </c:if>
                    <c:if test="${currentPage != pageTimes}">
                        <li>  <a href="${pageContext.request.contextPath }/jsp/seat_In_Use.form?page=${currentPage+1}">&raquo;</a>  </li>
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
