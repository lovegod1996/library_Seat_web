<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/11
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
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

    <style type="text/css">
        body {
            width: 80%;
            margin-left: 10%;
        }
    </style>

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

    <%--自动填充座位号--%>
    <script language=javascript>
        var selectedTr = null;
        function c1(obj) {
            obj.style.backgroundColor = '#F5F5F5'; //把点到的那一行变颜色;
            if (selectedTr != null)
                selectedTr.style.removeAttribute("backgroundColor");
            if (selectedTr == obj)
                selectedTr = null;//加上此句，以控制点击变白，再点击反灰
            else
                selectedTr = obj;
        }
        /*得到选中行的第二列的座位号*/
        function check() {
            if (selectedTr != null) {
                var str = selectedTr.cells[1].childNodes[0].value;
                document.getElementById("seatNum").innerHTML = str;
            } else {
                alert("请选择一行");
            }
        }
    </script>

    <style>
        #seatNum{
            cursor: pointer;
        }
        #seatNum:hover #show_msg{
            display: block;
        }

        #seatNum #show_msg{
            display: none;
        }
    </style>

</head>
<body>
<div id="wrapper">
    <div class="col-sm-8">
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
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr onclick="c1(this);">
                        <td>1</td>
                        <td><input type="text" disabled="disabled" style="border:none;background-color: transparent;width: 100px" value="S1-0101"></td>
                        <td>空闲中</td>
                        <td>
                            <button type="button" class="btn btn-primary btn-sm" onclick="check()">预约</button>
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
    <!-- /.col-sm-8 -->
    <div class="col-sm-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-bell fa-fw"></i> 预约座位
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body" style="height: 400px">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">座位号</label>
                        <div class="col-sm-9">
                            <label type="text" class="layui-input" id="seatNum" style="width: 220px"><span id="show_msg">点击入座自动填入</span></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">开始</label>
                        <div class="col-sm-9">
                            <div class="layui-inline">
                                <input class="layui-input" placeholder="开始时间" style="width: 220px" required
                                       onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm',min: laydate.now(0), max: laydate.now(+1)})">
                                <%--now(0)表示今天；now(1)表示明天,限制预约只能今天明天--%>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">结束</label>
                        <div class="col-sm-9">
                            <div class="layui-inline">
                                <input class="layui-input" placeholder="结束时间" style="width: 220px" required
                                       onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm',min: laydate.now(0), max: laydate.now(+1)})">
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">确定</button>
                </form>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-4 -->
</div>
<!-- jQuery -->
<script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%= request.getContextPath()%>/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%= request.getContextPath()%>/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%= request.getContextPath()%>/dist/js/sb-admin-2.js"></script>

</body>
</html>
