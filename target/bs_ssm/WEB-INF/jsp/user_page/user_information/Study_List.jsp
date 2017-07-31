<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/12
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>学习记录</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
</head>
<body>
<div class="col-sm-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i>学习记录
        </div>
        <!-- /.panel-heading -->
        <div class="layui-form">
            <table class="layui-table">
                <colgroup>
                    <col width="10%">
                    <col width="30%">
                    <col width="30%">
                    <col width="30%">
                </colgroup>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>日期</th>
                    <th>场馆</th>
                    <th>座号</th>
                    <th>位置</th>
                    <th>预订时间段</th>
                    <th>学习时间段</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${userbooks}" var="book" varStatus="step">
                    <tr>
                        <td>${step.index}</td>
                        <td><fmt:formatDate value="${book.bstime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${book.floor}</td>
                        <td>${book.seatnumber}</td>
                        <td>${book.leftside==0?"左":"右"}侧${book.row}排${book.columns}列</td>
                        <td><fmt:formatDate value="${book.bstime}" pattern="yyyy-MM-dd HH:mm:ss"/>--<fmt:formatDate value="${book.betime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${book.stime}" pattern="yyyy-MM-dd HH:mm:ss"/>--<fmt:formatDate value="${book.etime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>
                </c:forEach>

                <c:if test="${ nullList != null}">
                    <tr style="text-align: center">
                        <td colspan="7">${nullList}</td>
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
                            <a href="${pageContext.request.contextPath }/view/study_List.form?page=${currentPage-1}">&laquo;</a>
                        </c:if>
                    </li>
                    <c:if test="${currentPage==1}">
                        <li class="active"><a href="#">1</a></li>
                    </c:if>
                    <c:if test="${currentPage!=1}">
                        <li>
                            <a href="${pageContext.request.contextPath }/view/study_List.form?page=1">1</a>
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
                                <a href="${pageContext.request.contextPath }/view/study_List.form?page=<%=i+1%>"><%=i + 1%>
                                </a></li>
                        </c:if>
                    </c:if>
                    <% } %>
                    <c:if test="${currentPage == pageTimes}">
                        <li>    <a href="#" class="active">&raquo;</a> </li>
                    </c:if>
                    <c:if test="${currentPage != pageTimes}">
                        <li>  <a href="${pageContext.request.contextPath }/view/study_List.form?page=${currentPage+1}">&raquo;</a>  </li>
                    </c:if>
                </ul>
            </div>

        </div>
    </div>
    <!-- /.panel -->
</div>

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
