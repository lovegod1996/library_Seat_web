<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/15
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>开闭馆管理</title>
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
</head>
<body>

<div class="col-sm-8">
    <div class="layui-tab">
        <ul class="layui-tab-title">
            <li class="layui-this">南楼</li>
            <li>北楼</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <div class="layui-form">
                    <table class="layui-table">
                        <colgroup>
                            <col width="50">
                            <col width="150">
                            <col width="150">
                            <col width="200">
                            <col>
                        </colgroup>
                        <thead>
                        <tr>
                            <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
                            <th>楼层</th>
                            <th>闭馆时间</th>
                            <th>闭馆</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="checkbox" name="" lay-skin="primary"></td>
                            <td>南一</td>
                            <td>
                                <input class="layui-input" name="stime" placeholder="开始时间" required style="width: 220px"
                                       onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})">
                            </td>
                            <td>
                                <button class="layui-btn">闭馆</button>
                            </td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="" lay-skin="primary"></td>
                            <td>南二</td>
                            <td>
                                <input class="layui-input" name="stime" placeholder="开始时间" required style="width: 220px"
                                       onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})">
                            </td>
                            <td>
                                <button class="layui-btn">闭馆</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <button class="layui-btn">同时选择多层闭馆</button>
                </div>
            </div>
            <div class="layui-tab-item">
                <div class="layui-form">
                    <table class="layui-table">
                        <colgroup>
                            <col width="50">
                            <col width="150">
                            <col width="150">
                            <col width="200">
                            <col>
                        </colgroup>
                        <thead>
                        <tr>
                            <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
                            <th>楼层</th>
                            <th>闭馆时间</th>
                            <th>闭馆</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="checkbox" name="" lay-skin="primary"></td>
                            <td>北一</td>
                            <td>
                                <input class="layui-input" name="stime" placeholder="开始时间" required style="width: 220px"
                                       onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})">
                            </td>
                            <td>
                                <button class="layui-btn">闭馆</button>
                            </td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="" lay-skin="primary"></td>
                            <td>北二</td>
                            <td>
                                <input class="layui-input" name="stime" placeholder="开始时间" required style="width: 220px"
                                       onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})">
                            </td>
                            <td>
                                <button class="layui-btn">闭馆</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <button class="layui-btn">同时选择多层闭馆</button>
                </div>
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
            , max: '2099-06-16 23:59:59'
            , istoday: false
            , choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
            }
        };

        var end = {
            min: laydate.now()
            , max: '2099-06-16 23:59:59'
            , istoday: false
            , choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };

        document.getElementById('LAY_demorange_s').onclick = function () {
            start.elem = this;
            laydate(start);
        };
        document.getElementById('LAY_demorange_e').onclick = function () {
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
