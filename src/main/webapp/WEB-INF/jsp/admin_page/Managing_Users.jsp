<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/12
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理用户</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">

    <script language="JavaScript" type="text/javascript">
        //定义了专业的二维数组，里面的顺序跟学院的顺序是相同的。通过selectedIndex获得学院的下标值来得到相应的专业数组
        var major = [
            ["软件", "计科", "网络"],
            ["网络", "软件", "计科"]
        ];

        function getCollege() {
            //获得学院下拉框的对象
            var sltCollege = document.selectform.college;
            //获得专业下拉框的对象
            var sltMajor = document.selectform.major;
            //得到对应楼的专业数组
            var collegeMajor = major[sltCollege.selectedIndex - 1];

            //清空专业下拉框，仅留提示选项
            sltMajor.length = 1;

            //将专业数组中的值填充到学院下拉框中
            for (var i = 0; i < collegeMajor.length; i++) {
                sltMajor[i + 1] = new Option(collegeMajor[i], collegeMajor[i]);
            }
        }
    </script>
</head>
<body>
<div class="col-sm-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <label style="float: left">用户管理</label>
            <form method="post" action="" name="selectform" style="float: left;margin-left: 20px;width: 40%">
                <select name="year" style="float: left">
                    <option value="0">按年份筛选</option>
                    <option value="2017">2017</option>
                    <option value="2016">2016</option>
                    <option value="2015">2015</option>
                    <option value="2014">2014</option>
                </select>
                <select name="college" onchange="getCollege()" style="float: left;margin-left: 1%">
                    <option value="0">按学院筛选</option>
                    <option value="计算机学院">计算机学院</option>
                    <option value="软件学院">软件学院</option>
                </select>
                <select name="major" style="float: left;margin-left: 1%">
                    <option value="0">按专业筛选</option>
                </select>
            </form>
            <button type="button" data-method="notice" class="layui-btn layui-btn-mini" style="margin-left:20px ">添加用户</button>
            <button type="button" class="layui-btn layui-btn-mini layui-btn-danger" style="margin-left:20px ">删除用户</button>
            <button type="button" class="layui-btn layui-btn-mini layui-btn-normal" style="margin-left:20px ">实时刷新</button>

        </div>
        <!-- /.panel-heading -->
        <div class="layui-form">
            <table class="layui-table">
                <thead>
                <tr>
                    <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
                    <th>序号</th>
                    <th>入学年份</th>
                    <th>姓名</th>
                    <th>学号</th>
                    <th>学院</th>
                    <th>专业</th>
                    <th>班级</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input type="checkbox" name="" lay-skin="primary"></td>
                    <td>1</td>
                    <td>2014</td>
                    <td>小明</td>
                    <td>201400002222</td>
                    <td>计算机学院</td>
                    <td>软件工程</td>
                    <td>141班</td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm">删除</button>
                    </td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="" lay-skin="primary"></td>
                    <td>1</td>
                    <td>2014</td>
                    <td>小明</td>
                    <td>201400002222</td>
                    <td>计算机学院</td>
                    <td>软件工程</td>
                    <td>141班</td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm">删除</button>
                    </td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="" lay-skin="primary"></td>
                    <td>1</td>
                    <td>2014</td>
                    <td>小明</td>
                    <td>201400002222</td>
                    <td>计算机学院</td>
                    <td>软件工程</td>
                    <td>141班</td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm">删除</button>
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
