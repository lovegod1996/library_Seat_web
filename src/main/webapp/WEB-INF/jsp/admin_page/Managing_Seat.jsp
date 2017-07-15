<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/15
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增删座位</title>
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <style type="text/css">
        .layui-input-block {
            width: 250px;
        }
    </style>
</head>
<body>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">增加</li>
        <li>删除</li>
    </ul>
    <div class="layui-tab-content">
        <%--增加座位页面--%>
        <div class="layui-tab-item layui-show">
            <form class="layui-form" action="" style="width:40%;float: left">
                <div class="layui-form-item">
                    <label class="layui-form-label">选择楼</label>
                    <div class="layui-input-block">
                        <select name="interest" lay-filter="building" id="building">
                            <option value="0"></option>
                            <option value="南楼">南楼</option>
                            <option value="北楼">北楼</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">选择层</label>
                    <div class="layui-input-block">
                        <select name="interest" lay-filter="floor" id="floor">
                            <option value="0"></option>
                            <option value="一层">一层</option>
                            <option value="二层">二层</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">选择行</label>
                    <div class="layui-input-block">
                        <select name="interest" lay-filter="hang" id="hang">
                            <option value="0"></option>
                            <option value="01行">01</option>
                            <option value="02行">02</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">选择列</label>
                    <div class="layui-input-block">
                        <select name="interest" lay-filter="lie" id="lie">
                            <option value="0"></option>
                            <option value="01列">01</option>
                            <option value="02列">02</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">选择标志</label>
                    <div class="layui-input-block">
                        <select name="interest" lay-filter="sign" id="sign">
                            <option value="0"></option>
                            <option value="1">向阳</option>
                            <option value="2">靠窗</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item" style="text-align: center">
                    <button class="layui-btn" type="submit">添加座位</button>
                </div>
            </form>
            <div id="savePic" style="width: 35%;float: left;text-align: center">
                <div id="qrcode"
                     style="width: 200px;margin-left:25%;height:200px;background-image: url(<%=request.getContextPath()%>/img/qrcodebackground.png);">
                </div>
                <div style="margin-top: 30px"><label id="showSeatMsg" style="width: 200px;">座位信息</label></div>
                <div style="margin-top: 50px">
                    <button class="layui-btn" onclick="getSeatMsg();makeqrcode()">生成二维码并保存</button>
                </div>
            </div>
        </div>
        <%--删除座位页面--%>
        <div class="layui-tab-item">

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

<%--//下拉选择用--%>
<script>
    layui.use(['form'], function () {

    });
</script>

<%--//右侧二维码下面lable值--%>
<script>
    function getSeatMsg() {
        var obj_building = document.getElementById("building"); //定位id
        var obj_floor = document.getElementById("floor"); //定位id
        var obj_hang = document.getElementById("hang"); //定位id
        var obj_lie = document.getElementById("lie"); //定位id

        var index_building = obj_building.selectedIndex; // 选中索引
        var text_building = obj_building.options[index_building].text; // 选中文本
        var value_building = obj_building.options[index_building].value; // 选中值

        var index_floor = obj_floor.selectedIndex; // 选中索引
        var text_floor = obj_floor.options[index_floor].text; // 选中文本
        var value_floor = obj_floor.options[index_floor].value; // 选中值

        var index_hang = obj_hang.selectedIndex; // 选中索引
        var text_hang = obj_hang.options[index_hang].text; // 选中文本
        var value_hang = obj_hang.options[index_hang].value; // 选中值

        var index_lie = obj_lie.selectedIndex; // 选中索引
        var text_lie = obj_lie.options[index_lie].text; // 选中文本
        var value_lie = obj_lie.options[index_lie].value; // 选中值

        document.getElementById('showSeatMsg').innerHTML = value_building + "&nbsp;" + value_floor + "&nbsp;" + value_hang + "&nbsp;" + value_lie;
        var qrcode = new QRCode(document.getElementById("qrcode"), {
            width: 200,//设置宽高
            height: 200
        });
//        qrcode.makeCode("http://www.baidu.com");
        qrcode.makeCode(document.getElementById('showSeatMsg').innerHTML);
    }
</script>

<script src="<%=request.getContextPath()%>/js/qrcode.js"></script>
<!-- jQuery -->
<script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

</body>
</html>
