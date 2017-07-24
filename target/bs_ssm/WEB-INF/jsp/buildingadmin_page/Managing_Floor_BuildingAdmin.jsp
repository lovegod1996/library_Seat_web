<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/19
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>开闭馆管理</title>
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
</head>
<body>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">${sessionScope.admin.employer}</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <div class="layui-form">
                <table class="layui-table">
                    <thead>
                    <tr>
                        <th width="15%">楼层</th>
                        <th width="30%">藏书分类</th>
                        <th width="20%">管理员</th>
                        <th width="15%">开放状态</th>
                        <th width="15%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${floors}" var="floor">
                        <tr>
                            <td>${floor.floor}</td>
                            <td>${floor.employer}</td>
                            <td>${floor.name}</td>
                            <td>${floor.statue==0? "开放":"关闭"}</td>
                            <td>
                                <c:if test="${floor.statue==0}">
                                    <a href="<%=request.getContextPath()%>/view/floor/changestatue?fid=${floor.fid}&&statue=${floor.statue}">关闭</a> </c:if>
                                <c:if test="${floor.statue==1}"><a href="<%=request.getContextPath()%>/view/floor/changestatue?fid=${floor.fid}&&statue=${floor.statue}">开馆</a> </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${nullList!=null}">
                        <tr>
                            <td colspan="5" style="text-align: center">${nullList}</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
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

<%--选择日期--%>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        var start = {
            min: laydate.now()
            , format: 'YYYY-MM-DD hh:mm:ss' //日期格式
            , istime: true
            , max: '2099-06-16 23:59:59'
            , istoday: false
            , choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
            }
        };

        var end = {
            min: laydate.now()
            , format: 'YYYY-MM-DD hh:mm:ss' //日期格式
            , istime: true
            , max: '2099-06-16 23:59:59'
            , istoday: false
            , choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };

        document.getElementById('LAY_demorange_s_south').onclick = function () {
            start.elem = this;
            laydate(start);
        };
        document.getElementById('LAY_demorange_s_north').onclick = function () {
            start.elem = this;
            laydate(start);
        };
        document.getElementById('LAY_demorange_e_south').onclick = function () {
            end.elem = this;
            laydate(end);
        };
        document.getElementById('LAY_demorange_e_north').onclick = function () {
            end.elem = this;
            laydate(end);
        }

    });
</script>

<!-- jQuery -->
<script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%= request.getContextPath()%>/vendor/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
