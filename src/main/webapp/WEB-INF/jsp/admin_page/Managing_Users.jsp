<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/12
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>管理用户</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">


    <script language="JavaScript" type="text/javascript">
        //定义了专业的二维数组，里面的顺序跟学院的顺序是相同的。通过selectedIndex获得学院的下标值来得到相应的专业数组

        function getYear() {
            var year = $("#year").val();
            var url = "/LS/view/managing_Users?year=" + year + "&college=&major=";
            window.location.href = encodeURI(url);
        }

        function getCollege() {

            $.ajax({
                url: "${pageContext.request.contextPath }/view/collegeMajor.form",
                data: 'college=' + $("#college").val(),
                type: 'post',
                success: function (result) {
                    $("#major option").remove();//清空原来的选项
                    //  alert(result);
                    $("#major").append("<option > --选择专业--</option>");
                    result = eval(result);
                    for (var i = 0; i < result.length; i++) {
                        $("#major").append("<option > " + result[i] + "</option>")
                    }
                }
            })
        }

        function getMajor() {
            var major = $("#major").val();
            var college = $("#college").val();
            var year = $("#year").val();

            var url = "/LS/view/managing_Users?year=" + year + "&college=" + college + "&major=" + major;
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
            <label style="float: left">用户管理</label>
            <form method="post" action="" name="selectform" style="float: left;margin-left: 20px;width: 40%">
                <select name="year" id="year" onchange="getYear()" style="float: left">
                    <option value="0">按年份筛选</option>
                    <c:if test="${year!=null}">
                        <option value="${year}" selected>${year}</option>
                    </c:if>
                    <option value="2017">2017</option>
                    <option value="2018">2018</option>
                    <option value="2016">2016</option>
                    <option value="2015">2015</option>
                    <option value="2014">2014</option>
                </select>
                <select name="college" id="college" onchange="getCollege()" style="float: left;margin-left: 1%">
                    <option >--选择学院名称--</option>
                    <c:if test="${college!=null}">
                        <option value="${college}" selected>${college}</option>
                    </c:if>
                    <c:forEach items="${colleges}" var="collegee">
                        <option value="${collegee}">${collegee}</option>
                    </c:forEach>
                </select>
                <select name="major" id="major" onchange="getMajor()" style="float: left;margin-left: 1%">
                    <option value="0">--选择专业--</option>
                    <c:if test="${major!=null}">
                        <option value="${major}" selected>${major}</option>
                    </c:if>
                </select>
            </form>
            <div>
                <form action="${pageContext.request.contextPath }/view/allUserUp" method="post" enctype="multipart/form-data">
                    <input type="file" id="inputfile" name="excelfile" accept=".xls,.xlsx">
                    <button type="submit">提交</button>
                </form>
            </div>
            <button type="button" data-method="notice" class="layui-btn layui-btn-mini" style="margin-left:20px ">添加用户
            </button>
            <button type="button" class="layui-btn layui-btn-mini layui-btn-danger" style="margin-left:20px ">删除用户
            </button>
            <a href="${pageContext.request.contextPath }/view/managing_Users.form?year=${year}&college=${college}&major=${major}"
               class="btn btn-success btn-xs" style="margin-left:20px ">点击刷新
            </a>

        </div>
        <!-- /.panel-heading -->
        <div class="layui-form">
            <table class="layui-table">
                <thead>
                <tr>
                    <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>学号</th>
                    <th>学院</th>
                    <th>专业</th>
                    <th>班级</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${users}" var="userss">
                    <tr>
                        <td><input type="checkbox" name="" lay-skin="primary"></td>
                        <td>${userss.uid}</td>
                        <td>${userss.name}</td>
                        <td>${userss.sno}</td>
                        <td>${userss.college}</td>
                        <td>${userss.major}</td>
                        <td>${userss.classes}</td>
                        <td>
                            <a href="${pageContext.request.contextPath }/view/userDelete?uid=${userss.uid}" class="btn btn-danger btn-sm">删除</a>
                        </td>
                    </tr>

                </c:forEach>

                <c:if test="${ nullList != null}">
                    <tr style="text-align: center">
                        <td colspan="8">${nullList}</td>
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
                            <a href="${pageContext.request.contextPath }/view/managing_Users.form?page=${currentPage-1}&year=${year}&college=${college}&major=${major}">&laquo;</a>
                        </c:if>
                    </li>
                    <c:if test="${currentPage==1}">
                        <li class="active"><a href="#">1</a></li>
                    </c:if>
                    <c:if test="${currentPage!=1}">
                        <li>
                            <a href="${pageContext.request.contextPath }/view/managing_Users.form?page=1&year=${year}&college=${college}&major=${major}">1</a>
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
                                <a href="${pageContext.request.contextPath }/view/managing_Users.form?page=<%=i+1%>&year=${year}&college=${college}&major=${major}"><%=i + 1%>
                                </a></li>
                        </c:if>
                    </c:if>
                    <% } %>
                    <c:if test="${currentPage == pageTimes}">
                        <li><a href="#" class="active">&raquo;</a></li>
                    </c:if>
                    <c:if test="${currentPage != pageTimes}">
                        <li>
                            <a href="${pageContext.request.contextPath }/view/managing_Users.form?page=${currentPage+1}&year=${year}&college=${college}&major=${major}">&raquo;</a>
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
<script type="text/javascript">
    <c:if test="${!empty error_msg}">alert("${error_msg}");
    </c:if>
</script>

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
                    title: ['添加用户', 'font-size:18px;text-align:center;background:#32AA9F']
                    ,
                    closeBtn: false
                    ,
                    area: ['400px', '550px']
                    ,
                    shade: 0
                    ,
                    id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,
                    btn: ['关闭']
                    ,
                    moveType: 0 //拖拽模式，0或者1
                    ,
                    content: ['/LS/view/add_User']
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
