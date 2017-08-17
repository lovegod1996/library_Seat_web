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
        body {background-color: #f1f3fa;}
        .panel-default>.panel-heading {
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
        .pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
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
        <div style="margin: 30px;font-size:14px;line-height: 150%;color: #757575;">
            <h4 style="color:#5c9bd1;margin-left:-10px;margin-bottom: 10px;">使用规则</h4>
            1、谨慎操作、数据无价
            <br>2、该页面可查看所有公告记录信息。
            <br>3、点击编辑可对该条公告内容做修改，请谨慎操作。
            <br>4、点击删除确认即可删除该条公告，谨慎操作。
        </div><hr>
        <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i> 通知列表
        </div>
        <!-- /.panel-heading -->
        <div class="layui-form">
            <table class="layui-table">
                <colgroup>
                    <col>
                    <col width="8%">
                    <col width="45%">
                    <col width="20%">
                    <col width="20%">
                </colgroup>
                <thead>
                <tr>
                    <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
                    <th>序号</th>
                    <th>标题</th>
                    <th>发布时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${notices}" var="notice">
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>${notice.nid}</td>
                        <td><a href="<%=request.getContextPath()%>/jsp/news_Content?nid=${notice.nid}" target="mainFrame_Admin">${notice.title}</a></td>
                        <td><fmt:formatDate value="${notice.creattime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>
                            <a href="<%=request.getContextPath()%>/admin/editNews?nid=${notice.nid}" class="btn btn-primary btn-sm">编辑</a>
                            <a href="<%=request.getContextPath()%>/admin/newsDele?nid=${notice.nid}" class="btn btn-danger btn-sm" onclick="return confirmAct()">删除</a>
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
                            <a href="${pageContext.request.contextPath }/jsp/news_List_Admin.form?page=${currentPage-1}">&laquo;</a>
                        </c:if>
                    </li>
                    <c:if test="${currentPage==1}">
                        <li class="active"><a href="#">1</a></li>
                    </c:if>
                    <c:if test="${currentPage!=1}">
                        <li>
                            <a href="${pageContext.request.contextPath }/jsp/news_List_Admin.form?page=1">1</a>
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
                                <a href="${pageContext.request.contextPath }/jsp/news_List_Admin.form?page=<%=i+1%>"><%=i + 1%>
                                </a></li>
                        </c:if>
                    </c:if>
                    <% } %>
                    <c:if test="${currentPage == pageTimes}">
                        <li>    <a href="#" class="active">&raquo;</a> </li>
                    </c:if>
                    <c:if test="${currentPage != pageTimes}">
                        <li>  <a href="${pageContext.request.contextPath }/jsp/news_List_Admin.form?page=${currentPage+1}">&raquo;</a>  </li>
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
