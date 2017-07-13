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
            var url = "/LS/jsp/seat_In_Use?floor=" + floor;
            window.location.href = encodeURI(url);
        }


    </script>
</head>
<body>
<div class="col-sm-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i> 使用中座位统计
            <a href="${pageContext.request.contextPath }/jsp/seat_In_Use" class="btn btn-success btn-xs"
               style="margin-left:20px ">点击刷新</a>
            <div class="pull-right">
                <FORM METHOD=POST ACTION="" name="selectform">
                    <SELECT NAME="building" onChange="getCity()">
                        <OPTION VALUE="0">选择南北楼 </OPTION>
                        <OPTION VALUE="南楼">南楼 </OPTION>
                        <OPTION VALUE="北楼">北楼 </OPTION>
                    </SELECT>
                    <SELECT NAME="floor" onchange="getData()" id="floor">
                        <OPTION VALUE="0">选择所在楼层 </OPTION>
                        <OPTION VALUE="0">${floor}</OPTION>
                    </SELECT>
                </FORM>
            </div>
        </div>
        <!-- /.panel-heading -->
        <div class="layui-form">
            <table class="layui-table">
                <%--<colgroup>--%>
                    <%--<col width="10%">--%>
                    <%--<col width="10%">--%>
                    <%--<col width="10%">--%>
                    <%--<col width="10%">--%>
                    <%--<col width="10%">--%>
                    <%--<col width="10%">--%>
                    <%--<col width="10%">--%>
                    <%--<col width="10%">--%>
                    <%--<col width="10%">--%>
                    <%--<col width="10%">--%>
                <%--</colgroup>--%>
                <thead>
                <tr>
                    <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>学院</th>
                    <th>专业</th>
                    <th>班级</th>
                    <th>座位号</th>
                    <th>预约时间</th>
                    <th>座位状态</th>
                    <th>修改</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userLearned}" var="userLearn">
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>${userLearn.sno}</td>
                        <td>${userLearn.name}</td>
                        <td>${userLearn.college}</td>
                        <td>${userLearn.major}</td>
                        <td>${userLearn.classes}</td>
                        <td>${userLearn.seatnumber}</td>
                        <td>${userLearn.period}</td>
                        <td>学习中</td>
                        <td>
                            <button type="button" class="btn btn-danger btn-sm">操作</button>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${ nullList != null}">
                    <tr style="text-align: center">
                        <td colspan="9">${nullList}</td>
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
                            <a href="${pageContext.request.contextPath }/jsp/seat_In_Use.form?page=${currentPage-1}&floor=${floor}">&laquo;</a>
                        </c:if>
                    </li>
                    <c:if test="${currentPage==1}">
                        <li class="active"><a href="#">1</a></li>
                    </c:if>
                    <c:if test="${currentPage!=1}">
                        <li>
                            <a href="${pageContext.request.contextPath }/jsp/seat_In_Use.form?page=1&floor=${floor}">1</a>
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
                                <a href="${pageContext.request.contextPath }/jsp/seat_In_Use.form?page=<%=i+1%>&floor=${floor}"><%=i + 1%>
                                </a></li>
                        </c:if>
                    </c:if>
                    <% } %>
                    <c:if test="${currentPage == pageTimes}">
                        <li>    <a href="#" class="active">&raquo;</a> </li>
                    </c:if>
                    <c:if test="${currentPage != pageTimes}">
                        <li>  <a href="${pageContext.request.contextPath }/jsp/seat_In_Use.form?page=${currentPage+1}&floor=${floor}">&raquo;</a>  </li>
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
