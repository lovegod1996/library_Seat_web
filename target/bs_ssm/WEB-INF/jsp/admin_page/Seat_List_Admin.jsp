<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 20:20
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
    <style>
        body {
            background-color: #f1f3fa;
        }

        .panel-default > .panel-heading {
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

        .panel-default > .panel-heading {
            color: #9e9e9e;
            background-color: #f1f3fa;
            border-color: #f1f3fa;
        }

        .panel {
            background-color: #f1f3fa;
        }

        .layui-table {
            width: 90%;
            margin: 10px 5%;
            background-color: #fff;
        }

        .pagination {
            display: inline-block;
            padding-left: 0;
            margin: 20px;
            margin-left: -10%;
            border-radius: 4px;
        }

        .pagination > .active > a, .pagination > .active > a:focus, .pagination > .active > a:hover, .pagination > .active > span, .pagination > .active > span:focus, .pagination > .active > span:hover {
            z-index: 3;
            color: #fff;
            cursor: default;
            background-color: #5c9bd1;
            border-color: #5c9bd1;
        }

        .layui-table thead tr {
            background-color: #f1f3fa;
        }
    </style>
</head>
<body>
<div class="col-sm-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i> 座位列表
        </div>
        <div style="margin: 30px;font-size:14px;line-height: 150%;color: #757575;">
            <h4 style="color:#5c9bd1;margin-left:-10px;margin-bottom: 10px;">使用规则</h4>
            1、谨慎操作、数据无价
            <br>2、本页面可查看该场馆的所由座位信息，提供有多种二维码主题样式供选择，点击即可自动下载到本地。
            <br>3、点击关闭预约按钮可关闭该座位的开放状态，该座位可不被预约。
            <br>4、点击删除确认即可删除座位信息，请谨慎操作。
        </div><hr>
        <!-- /.panel-heading -->
        <div class="layui-form">
            <table class="layui-table">
                <thead>
                <tr>
                    <th>所属楼层</th>
                    <th>座位编号</th>
                    <th>分布</th>
                    <th>排列</th>
                    <th>可预约</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${seats}" var="seat">
                    <tr>
                        <td>${seat.building}(${seat.floor})</td>
                        <td>${seat.seatnumber}</td>
                        <td>${seat.leftside==0?"左侧":"右侧"}</td>
                        <td>${seat.row}排${seat.columns}列</td>
                        <td>${seat.statue==0?"是":"否"}</td>
                        <td>
                            <div class="layui-btn-group">
                                    <%--<a href="${pageContext.request.contextPath }/view/getSeatPic?seatNumber=${seat.seatnumber}&type=1"--%>
                                    <%--class="layui-btn layui-btn-primary layui-btn-small" title="点击下载二维码">二维码</a>--%>

                                <a href="${pageContext.request.contextPath }/view/getSeatPic?seatNumber=${seat.seatnumber}&type=0"
                                   class="layui-btn layui-btn-primary layui-btn-small" title="点击下载二维码"> 默认</a>
                                <a href="${pageContext.request.contextPath }/view/getSeatPic?seatNumber=${seat.seatnumber}&type=1"
                                   class="layui-btn layui-btn-primary layui-btn-small" title="点击下载二维码"> 主题1</a>
                                <a href="${pageContext.request.contextPath }/view/getSeatPic?seatNumber=${seat.seatnumber}&type=2"
                                   class="layui-btn layui-btn-primary layui-btn-small" title="点击下载二维码"> 主题2</a>
                                <a href="${pageContext.request.contextPath }/view/getSeatPic?seatNumber=${seat.seatnumber}&type=3"
                                   class="layui-btn layui-btn-primary layui-btn-small" title="点击下载二维码"> 主题3</a>
                                <a href="${pageContext.request.contextPath }/view/getSeatPic?seatNumber=${seat.seatnumber}&type=4"
                                   class="layui-btn layui-btn-primary layui-btn-small" title="点击下载二维码"> 主题4</a>
                                <a href="${pageContext.request.contextPath }/view/getSeatPic?seatNumber=${seat.seatnumber}&type=5"
                                   class="layui-btn layui-btn-primary layui-btn-small" title="点击下载二维码"> 主题5</a>
                                <a href="${pageContext.request.contextPath }/view/getSeatPic?seatNumber=${seat.seatnumber}&type=6"
                                   class="layui-btn layui-btn-primary layui-btn-small" title="点击下载二维码"> 主题6</a>
                                <a href="${pageContext.request.contextPath }/view/getSeatPic?seatNumber=${seat.seatnumber}&type=7"
                                   class="layui-btn layui-btn-primary layui-btn-small" title="点击下载二维码"> 主题7</a>
                                        <a href="${pageContext.request.contextPath }/view/getSeatPic?seatNumber=${seat.seatnumber}&type=8"
                                           class="layui-btn layui-btn-primary layui-btn-small" title="点击下载二维码"> 主题8</a>
                                <c:if test="${seat.statue==0}">
                                    <a href="${pageContext.request.contextPath }/view/changeSeatStatue?statue=${seat.statue}&sid=${seat.sid}&fid=${fid}"
                                       class="layui-btn layui-btn-primary layui-btn-small"
                                       onclick="return confirmAct()">关闭预约</a>
                                </c:if>
                                <c:if test="${seat.statue==1}">
                                    <a href="${pageContext.request.contextPath }/view/changeSeatStatue?statue=${seat.statue}&sid=${seat.sid}&fid=${fid}"
                                       class="layui-btn layui-btn-primary layui-btn-small"
                                       onclick="return confirmAct()">开启预约</a>
                                </c:if>
                                <a href="${pageContext.request.contextPath }/view/deleteSeat?sid=${seat.sid}&fid=${fid}"
                                   class="layui-btn layui-btn-primary layui-btn-small"
                                   onclick="return confirmAct()">删除</a>
                            </div>
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
                            <a href="<%=request.getContextPath()%>/view/floorSeatsList?fid=${fid}&page=${currentPage-1}">&laquo;</a>
                        </c:if>
                    </li>
                    <c:if test="${currentPage==1}">
                        <li class="active"><a href="#">1</a></li>
                    </c:if>
                    <c:if test="${currentPage!=1}">
                        <li>
                            <a href="${pageContext.request.contextPath }/view/floorSeatsList?page=1&fid=${fid}">1</a>
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
                                <a href="${pageContext.request.contextPath }/view/floorSeatsList?page=<%=i+1%>&fid=${fid}"><%=i + 1%>
                                </a></li>
                        </c:if>
                    </c:if>
                    <% } %>
                    <c:if test="${currentPage == pageTimes}">
                        <li><a href="#" class="active">&raquo;</a></li>
                    </c:if>
                    <c:if test="${currentPage != pageTimes}">
                        <li>
                            <a href="${pageContext.request.contextPath }/view/floorSeatsList?page=${currentPage+1}&fid=${fid}">&raquo;</a>
                        </li>
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
</body>
</html>
