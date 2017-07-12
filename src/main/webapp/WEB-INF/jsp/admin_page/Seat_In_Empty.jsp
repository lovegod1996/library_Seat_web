<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/11
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            ["南一", "南二", "南三", "南四"],
            ["北一", "北二", "北三", "北四"]
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
    </script>
</head>
<body>
<div class="col-sm-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i> 空闲座位统计
            <button type="button" class="btn btn-success btn-xs" style="margin-left:20px ">实时刷新</button>
            <div class="pull-right">
                <FORM METHOD=POST ACTION="" name="selectform">
                    <SELECT NAME="building" onChange="getCity()">
                        <OPTION VALUE="0">选择南北楼</OPTION>
                        <OPTION VALUE="南楼">南楼</OPTION>
                        <OPTION VALUE="北楼">北楼</OPTION>
                    </SELECT>
                    <SELECT NAME="floor">
                        <OPTION VALUE="0">选择所在楼层</OPTION>
                    </SELECT>
                </FORM>
            </div>
        </div>
        <!-- /.panel-heading -->
        <div class="table table-condensed">
            <table class="table">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>座位号</th>
                    <th>座位状态</th>
                    <th>修改</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>S2-0101</td>
                    <td>空闲中</td>
                    <td>
                        <button data-method="notice" class="layui-btn">入座</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>S2-0101</td>
                    <td>空闲中</td>
                    <td>
                        <button data-method="notice" class="layui-btn">入座</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>S2-0101</td>
                    <td>空闲中</td>
                    <td>
                        <button data-method="notice" class="layui-btn">入座</button>
                    </td>
                </tr>
                </tbody>
            </table>
            <div style="text-align: center">
                <ul class="pagination">
                    <li><a href="#">&laquo;</a></li>
                    <li class="active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">&raquo;</a></li>
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
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句

//触发事件
        var active = {
            notice: function () {
//示范一个公告层
                layer.open({
                    type: 2
                    ,
                    title: ['立即入座', 'font-size:18px;text-align:center;background:#32AA9F']
                    ,
                    closeBtn: false
                    ,
                    area: ['400px','550px']
                    ,
                    shade: 0
                    ,
                    id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,
                    btn: ['关闭']
                    ,
                    moveType: 0 //拖拽模式，0或者1
                    ,
                    content: ['/Library_Seat/view/seat_Now', 'no']
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
