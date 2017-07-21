<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/21
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <!-- jQuery -->
    <script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

    <%--导出table到Excel--%>
    <script>
        var tableToExcel = (function () {
            var uri = 'data:application/vnd.ms-excel;base64,'
                ,
                template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" ' +
                    'xmlns="http://www.w3.org/TR/REC-html40">' +
                    '<head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head>' +
                    '<body><table>{table}</table></body></html>'
                , base64 = function (s) {
                    return window.btoa(unescape(encodeURIComponent(s)))
                }
                , format = function (s, c) {
                    return s.replace(/{(\w+)}/g, function (m, p) {
                        return c[p];
                    })
                }
            return function (table, name, filename) {
                if (!table.nodeType) table = document.getElementById("tb")
                var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}

                document.getElementById("dlink").href = uri + base64(format(template, ctx));
                document.getElementById("dlink").download = filename;
                document.getElementById("dlink").click();
            }
        })()
    </script>

</head>
<body>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">楼层</li>
        <li>详情</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <div class="layui-form">
                <table class="layui-table" lay-skin="line">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>楼层</th>
                        <th>学习时间</th>
                        <th>入馆次数</th>
                        <th>失信次数</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>南一</td>
                        <td>100</td>
                        <td>25</td>
                        <td>2</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="layui-tab-item" onload="getBadPercent()">
            <form class="layui-form" action="">
                <div class="layui-form-item">
                    <div class="layui-input-inline">
                        <select name="modules" lay-verify="required" lay-search="">
                            <option value="">学院搜索选择</option>
                            <option value="1">layer</option>
                            <option value="2">form</option>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select name="modules" lay-verify="required" lay-search="">
                            <option value="">专业搜索选择</option>
                            <option value="1">layer</option>
                            <option value="2">form</option>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select name="modules" lay-verify="required" lay-search="">
                            <option value="">班级搜索选择</option>
                            <option value="1">layer</option>
                            <option value="2">form</option>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <button type="button" class="layui-btn layui-btn-small"
                                onclick="tableToExcel('tablename', 'name', '学习情况统计.xls')">导出
                        </button>
                        <a id="dlink" style="display:none;"></a>
                    </div>
                </div>
            </form>
            <hr>
            <table class="layui-table" lay-skin="line" id="tb">
                <thead>
                <tr>
                    <th>排名</th>
                    <th>学院</th>
                    <th>专业</th>
                    <th>班级</th>
                    <th>学习时长</th>
                    <th>入馆次数</th>
                    <th>失信次数</th>
                    <th>失信率</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>计算机学院</td>
                    <td>软件工程</td>
                    <td>141</td>
                    <td>100</td>
                    <td>25</td>
                    <td>2</td>
                    <td>8%</td>
                </tr>
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

<script>
    layui.use(['form'], function () {

    });
</script>

</body>
</html>
