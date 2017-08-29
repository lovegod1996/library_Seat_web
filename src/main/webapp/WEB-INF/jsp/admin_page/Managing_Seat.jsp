<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/15
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
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
        body {
            background-color: #eff3f8;
            margin-top: 15px;
        }
        .panel-default>.panel-heading {
            color: #5c9bd1;
            background-color: #ffffff;
            border-color: #f9f9f9;
        }
    </style>
</head>
<body>
<div class="layui-tab">
    <div style="margin: 30px;font-size:14px;line-height: 150%;color: #757575;">
        <h4 style="color:#5c9bd1;margin-left:-10px;margin-bottom: 10px;">使用规则</h4>
        1、谨慎操作、数据无价
        <br>2、左右、行、列，为必选项，请记得添加此类信息，标志可不选。
        <br>3、添加保存即可添加成功，并且会生成默认的二维码下载本地。
    </div><hr>
    <ul class="layui-tab-title">
        <li class="layui-this">单个增加</li>
        <li>按行添加</li>
    </ul>
    <div class="layui-tab-content">
        <%--增加座位页面--%>
        <div class="layui-tab-item layui-show">
            <form class="layui-form" action="<%=request.getContextPath()%>/view/addSeatSub" method="post" style="width:40%;float: left">
                <div class="layui-form-item">
                    <label class="layui-form-label">选择左右</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="fid" value="${fid}">
                        <select name="left" lay-filter="hang" id="left">
                            <option value="0"></option>
                            <option value="0">左</option>
                            <option value="1">右</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">选择行</label>
                    <div class="layui-input-block">
                        <select name="row" lay-filter="hang" id="hang">
                            <option value="0"></option>
                            <option value="1">01</option>
                            <option value="2">02</option>
                            <option value="3">03</option>
                            <option value="4">04</option>
                            <option value="5">05</option>
                            <option value="6">06</option>
                            <option value="7">07</option>
                            <option value="8">08</option>
                            <option value="9">09</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="13">13</option>
                            <option value="14">14</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">选择列</label>
                    <div class="layui-input-block">
                        <select name="column" lay-filter="lie" id="lie">
                            <option value="0"></option>
                            <option value="1">01</option>
                            <option value="2">02</option>
                            <option value="3">03</option>
                            <option value="4">04</option>
                            <option value="5">05</option>
                            <option value="6">06</option>
                            <option value="7">07</option>
                            <option value="8">08</option>
                            <option value="9">09</option>
                            <option value="10">10</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">选择标志</label>
                    <div class="layui-input-block">
                        <select name="mark" lay-filter="sign" id="sign">
                            <option></option>
                            <option value="向阳">向阳</option>
                            <option value="靠窗">靠窗</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item" style="text-align: center">
                    <button class="layui-btn" type="submit" style="background-color: #5c9bd1;">添加座位</button>
                </div>
            </form>
        </div>
        <%--删除座位页面--%>
        <div class="layui-tab-item">
            <form class="layui-form" action="<%=request.getContextPath()%>/view/addColumsSeat" method="post" >
                <div class="layui-form-item">
                    <label class="layui-form-label">选择左右</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="fid" value="${fid}">
                        <select name="left" lay-filter="hang" id="cleft">
                            <option></option>
                            <option value="0">左</option>
                            <option value="1">右</option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">选择行</label>
                    <div class="layui-input-block">
                        <select name="row" lay-filter="hang" id="row">
                            <option ></option>
                            <option value="1">01</option>
                            <option value="2">02</option>
                            <option value="3">03</option>
                            <option value="4">04</option>
                            <option value="5">05</option>
                            <option value="6">06</option>
                            <option value="7">07</option>
                            <option value="8">08</option>
                            <option value="9">09</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="13">13</option>
                            <option value="14">14</option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">选择列数</label>
                    <div class="layui-input-block">
                        <select name="column" lay-filter="lie" id="column">
                            <option ></option>
                            <option value="1">01</option>
                            <option value="2">02</option>
                            <option value="3">03</option>
                            <option value="4">04</option>
                            <option value="5">05</option>
                            <option value="6">06</option>
                            <option value="7">07</option>
                            <option value="8">08</option>
                            <option value="9">09</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="13">13</option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item" style="text-align: center">
                    <button class="layui-btn" type="submit" style="background-color: #5c9bd1;">添加座位</button>
                </div>
            </form>
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

<%--&lt;%&ndash;//右侧二维码下面lable值&ndash;%&gt;--%>
<%--<script>--%>
    <%--function getSeatMsg() {--%>
        <%--var obj_hang = document.getElementById("hang"); //定位id--%>
        <%--var obj_lie = document.getElementById("lie"); //定位id--%>


        <%--var index_hang = obj_hang.selectedIndex; // 选中索引--%>
        <%--var text_hang = obj_hang.options[index_hang].text; // 选中文本--%>
        <%--var value_hang = obj_hang.options[index_hang].value; // 选中值--%>

        <%--var index_lie = obj_lie.selectedIndex; // 选中索引--%>
        <%--var text_lie = obj_lie.options[index_lie].text; // 选中文本--%>
        <%--var value_lie = obj_lie.options[index_lie].value; // 选中值--%>

        <%--document.getElementById('showSeatMsg').innerHTML = value_hang + "&nbsp;" + value_lie;--%>
        <%--var qrcode = new QRCode(document.getElementById("qrcode"), {--%>
            <%--width: 200,//设置宽高--%>
            <%--height: 200--%>
        <%--});--%>
<%--//        qrcode.makeCode("http://www.baidu.com");--%>
        <%--qrcode.makeCode(document.getElementById('showSeatMsg').innerHTML);--%>
    <%--}--%>
<%--</script>--%>

<script src="<%=request.getContextPath()%>/js/qrcode.js"></script>
<!-- jQuery -->
<script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

<script type="text/javascript">
    <c:if test="${!empty error_msg}">alert("${error_msg}");</c:if>
</script>

</body>
</html>
