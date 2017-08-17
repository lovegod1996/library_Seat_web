<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/15
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>管理员管理</title>
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <%--操作确认--%>
    <script src="<%=request.getContextPath()%>/js/dialog.js"></script>
    <script>
        function changeItem(obj) {
//    var index=obj.selectedIndex;
            alert(obj);
//    if(index==obj.options.length-1){
//            document.getElementById("param2").style.display="block";
//        }
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
        .layui-table thead tr {
            background-color: #eff3f8;
        }
        button, input, optgroup, option, select, textarea {
            font-family: inherit;
            font-size: inherit;
            font-style: inherit;
            font-weight: inherit;
            outline: 0;
            width: 100px;
            height: 30px;
            background-color: #eff3f8;
        }
        .layui-table {
            width: 86%;
            background-color: #fff;
        }

    </style>
</head>
<body>

<div class="layui-tab">
    <div style="margin: 30px;font-size:14px;line-height: 150%;color: #757575;">
        <h4 style="color:#5c9bd1;margin-left:-10px;margin-bottom: 10px;">使用规则</h4>
            1、谨慎操作、数据无价
        <br>2、添加管理员请注意输入规范数据，请正确输入管理人员的姓名和身份单位等信息。
        <br>3、谨慎操作权限，默认勾选框说明已有改权限，点击选择框确认即可添加权限。
        <br>4、一般管理员可以删除，超级管理员无法删除，请谨慎操作数据。
    </div><hr>
    <ul class="layui-tab-title">
        <li class="layui-this">管理员状态</li>
        <li class="layui">添加</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <div class="layui-form">
                <table class="layui-table" >
                    <thead>
                    <tr>
                        <th width="5%">姓名</th>
                        <th width="10%">账号</th>
                        <th width="10%">身份单位</th>
                        <th width="15%">权限状态</th>
                        <th width="5%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${admins}" var="admin">
                        <tr>
                            <td>${admin.name}</td>
                            <td>${admin.acountnumber}</td>
                            <td>${admin.employer}</td>
                            <td>
                                <table>
                                    <tbody>
                                    <c:if test="${admin.admin==1}">
                                        <caption> ${admin.admin==0?"一般管理员":"超级管理员"}</caption>
                                    </c:if>

                                    <tr>
                                        <c:if test="${admin.floor==1}">
                                            <td>
                                                <a href="<%=request.getContextPath()%>/view/UpdateAdmin/${admin.aid}/0/${admin.notice}/${admin.user}/${admin.seat}" title="点击关闭">
                                                    <span style="color: #4CAF50" onclick="confirmAct()">楼层管理&nbsp;&nbsp;<input type="checkbox" checked></span>
                                                    </a>
                                            </td>
                                        </c:if>
                                        <c:if test="${admin.notice==1}">
                                            <td>
                                                <a href="<%=request.getContextPath()%>/view/UpdateAdmin/${admin.aid}/${admin.floor}/0/${admin.user}/${admin.seat}" title="点击关闭">
                                                    <span style="color: #4CAF50" onclick="confirmAct()">消息管理&nbsp;&nbsp;<input type="checkbox" checked></span>
                                                </a>
                                            </td>
                                        </c:if>

                                        <c:if test="${admin.floor==0}">
                                            <td>
                                                <a href="<%=request.getContextPath()%>/view/UpdateAdmin/${admin.aid}/1/${admin.notice}/${admin.user}/${admin.seat}" title="点击开启">
                                                    <span style="color: #7d9096" onclick="confirmAct()">楼层管理&nbsp;&nbsp;<input type="checkbox"> </span>
                                                    </a>
                                            </td>
                                        </c:if>
                                        <c:if test="${admin.notice==0}">
                                            <td>
                                                <a href="<%=request.getContextPath()%>/view/UpdateAdmin/${admin.aid}/${admin.floor}/1/${admin.user}/${admin.seat}"
                                                   title="点击开启"><span style="color: #7d9096" onclick="confirmAct()">消息管理&nbsp;&nbsp;<input type="checkbox"></span></a>
                                            </td>
                                        </c:if>
                                    </tr>

                                    <tr>
                                        <c:if test="${admin.user==1}">
                                            <td>
                                                <a href="<%=request.getContextPath()%>/view/UpdateAdmin/${admin.aid}/${admin.floor}/${admin.notice}/0/${admin.seat}"
                                                   title="点击关闭"> <span style="color: #4CAF50" onclick="confirmAct()">学生管理&nbsp;&nbsp;<input type="checkbox" checked></span> </a>
                                            </td>
                                        </c:if>
                                        <c:if test="${admin.seat==1}">
                                            <td>
                                                <a href="<%=request.getContextPath()%>/view/UpdateAdmin/${admin.aid}/${admin.floor}/${admin.notice}/${admin.user}/0"
                                                   title="点击关闭"> <span style="color: #4CAF50" onclick="confirmAct()">座位管理&nbsp;&nbsp;<input type="checkbox" checked></span> </a>
                                            </td>
                                        </c:if>

                                        <c:if test="${admin.user==0}">
                                            <td>
                                                <a href="<%=request.getContextPath()%>/view/UpdateAdmin/${admin.aid}/${admin.floor}/${admin.notice}/1/${admin.seat}"
                                                   title="点击开启"><span style="color: #7d9096" onclick="confirmAct()">学生管理&nbsp;&nbsp;<input type="checkbox"></span></a>
                                            </td>
                                        </c:if>
                                        <c:if test="${admin.seat==0}">
                                            <td>
                                                <a href="<%=request.getContextPath()%>/view/UpdateAdmin/${admin.aid}/${admin.floor}/${admin.notice}/${admin.user}/1"
                                                   title="点击开启"><span style="color: #7d9096" onclick="confirmAct()">座位管理&nbsp;&nbsp;<input type="checkbox"></span></a>
                                            </td>
                                        </c:if>
                                    </tr>
                                    </tbody>
                                </table>
                            </td>
                            <td>
                                <c:if test="${admin.admin==0}">
                                    <a href="<%=request.getContextPath()%>/view/deleteAdmin?aid=${admin.aid}" style="color:red;" onclick="return confirmAct()">删除</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${NullList!=null}">
                        <tr>
                            <td colspan="3" style="text-align: center">${NullList}</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="layui-tab-item">
            <div class="layui-form">
                <form class="layui-form" action="<%=request.getContextPath()%>/view/AdAd    min" method="post">
                    <table class="layui-table" style="width:60%;margin-left:20%">
                        <caption style="text-align: center;font-size: 18px;
    font-weight: bold;">添加管理员</caption>
                        <tbody>
                        <tr>
                            <td>
                                <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请输入添加人员的姓名:</label>
                                <input type="text" name="name" placeholder="请输入姓名" style="width:250px;height:25px;" required/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>请输入添加人员的身份单位:</label>
                                <input type="text" name="employ" placeholder="请输入身份单位" style="width:250px;height:25px;" required/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <button class="btn btn-primary" style="margin-left: 45%">提交</button>
                </form>
            </div>


        </div>
    </div>
</div>


<%--Tip页切换--%>
<script>
    layui.use('element', function () {
        var $ = layui.jquery
            , element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块

        //触发事件
        var active = {
            tabChange: function () {
                //切换到指定Tab项
                element.tabChange('demo', '22'); //
            }
        };

        $('.site-demo-active').on('click', function () {
            var othis = $(this), type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
        });

        //Hash地址的定位
        var layid = location.hash.replace(/^#test=/, '');
        element.tabChange('test', layid);

        element.on('tab(test)', function (elem) {
            location.hash = 'test=' + $(this).attr('lay-id');
        });

    });
</script>

<%--//全选--%>
<script>
    layui.use('form', function () {
        var $ = layui.jquery, form = layui.form();
        //全选
        form.on('checkbox(allChoose)', function (data) {
            var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
            child.each(function (index, item) {
                item.checked = data.elem.checked;
            });
            form.render('checkbox');
        });

    });
</script>
<script type="text/javascript">
    function confirmAct() {
        if (confirm('确定要执行此操作吗?')) {
            return true;
        }
        return false;
    }
</script>

<%--选择日期--%>
<%--<script>--%>
<%--layui.use('laydate', function () {--%>
<%--var laydate = layui.laydate;--%>
<%--var start = {--%>
<%--min: laydate.now()--%>
<%--, format: 'YYYY-MM-DD hh:mm:ss' //日期格式--%>
<%--, istime: true--%>
<%--, max: '2099-06-16 23:59:59'--%>
<%--, istoday: false--%>
<%--, choose: function (datas) {--%>
<%--end.min = datas; //开始日选好后，重置结束日的最小日期--%>
<%--end.start = datas //将结束日的初始值设定为开始日--%>
<%--}--%>
<%--};--%>

<%--var end = {--%>
<%--min: laydate.now()--%>
<%--, format: 'YYYY-MM-DD hh:mm:ss' //日期格式--%>
<%--, istime: true--%>
<%--, max: '2099-06-16 23:59:59'--%>
<%--, istoday: false--%>
<%--, choose: function (datas) {--%>
<%--start.max = datas; //结束日选好后，重置开始日的最大日期--%>
<%--}--%>
<%--};--%>

<%--document.getElementById('LAY_demorange_s_south').onclick = function () {--%>
<%--start.elem = this;--%>
<%--laydate(start);--%>
<%--};--%>
<%--document.getElementById('LAY_demorange_s_north').onclick = function () {--%>
<%--start.elem = this;--%>
<%--laydate(start);--%>
<%--};--%>
<%--document.getElementById('LAY_demorange_e_south').onclick = function () {--%>
<%--end.elem = this;--%>
<%--laydate(end);--%>
<%--};--%>
<%--document.getElementById('LAY_demorange_e_north').onclick = function () {--%>
<%--end.elem = this;--%>
<%--laydate(end);--%>
<%--}--%>

<%--});--%>
<%--</script>--%>

<!-- jQuery -->
<script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%= request.getContextPath()%>/vendor/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">

    <c:if test="${!empty error_msg}">alert("${error_msg}");
    </c:if>

</script>
</body>
</html>
