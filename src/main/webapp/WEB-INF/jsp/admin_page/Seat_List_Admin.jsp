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
</head>
<body>
<div class="col-sm-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i> 座位列表
        </div>
        <!-- /.panel-heading -->
        <div class="layui-form">
            <table class="layui-table">
                <%--<colgroup>--%>
                <%--<col>--%>
                <%--<col width="8%">--%>
                <%--<col width="20%">--%>
                <%--<col width="8%">--%>
                <%--<col width="15%">--%>
                <%--<col width="40%">--%>
                <%--</colgroup>--%>
                <thead>
                <tr>
                    <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
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
                        <td><input type="checkbox"></td>
                        <td>${seat.building}(${seat.floor})</td>
                        <td>${seat.seatnumber}</td>
                        <td>${seat.leftside==0?"左侧":"右侧"}</td>
                        <td>${seat.row}排${seat.columns}列</td>
                        <td>${seat.statue==0?"是":"否"}</td>
                        <td>
                            <div class="layui-btn-group">
                                <a href="${pageContext.request.contextPath }/view/getSeatPic?seatNumber=${seat.seatnumber}" class="layui-btn layui-btn-primary layui-btn-small" title="点击下载二维码">二维码</a>
                                <c:if test="${seat.statue==0}">
                                    <a href="${pageContext.request.contextPath }/view/changeSeatStatue?statue=${seat.statue}&sid=${seat.sid}&fid=${fid}"
                                       class="layui-btn layui-btn-primary layui-btn-small">关闭预约</a>
                                </c:if>
                                <c:if test="${seat.statue==1}">
                                    <a href="${pageContext.request.contextPath }/view/changeSeatStatue?statue=${seat.statue}&sid=${seat.sid}&fid=${fid}"
                                       class="layui-btn layui-btn-primary layui-btn-small">开启预约</a>
                                </c:if>
                                <a href="${pageContext.request.contextPath }/view/deleteSeat?sid=${seat.sid}&fid=${fid}"
                                   class="layui-btn layui-btn-primary layui-btn-small">删除</a>
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
</body>
</html>
