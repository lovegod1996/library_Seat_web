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
    <title>开闭馆管理</title>
    <%--layui--%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <script type="text/javascript">
        $("#param2").disable();
    </script>
</head>
<body>

<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">一周开放状态</li>
        <li class="layui-this">添加</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <div class="layui-form">
                <table class="layui-table">
                    <thead>
                    <tr>
                        <th width="15%">楼层</th>
                        <th width="30%">时间段</th>
                        <th width="15%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>周一</td>
                        <td style="text-align: left">
                            <div class="layui-input-inline">
                                <select name="modules" lay-verify="required" lay-search="">
                                    <option value="">开始时间</option>
                                    <option value="1">layer</option>
                                    <option value="2">form</option>
                                </select>
                            </div>
                            <div class="layui-input-inline">
                                <select name="modules" lay-verify="required" lay-search="">
                                    <option value="">结束时间</option>
                                    <option value="1">layer</option>
                                    <option value="2">form</option>
                                </select>
                            </div>
                        </td>
                        <td>
                            <button class="layui-btn">保存</button>
                        </td>
                    </tr>

                    <tr>
                        <td>周二</td>
                        <td style="text-align: left">
                            <div class="layui-input-inline">
                                <select name="modules" lay-verify="required" lay-search="">
                                    <option value="">开始时间</option>
                                    <option value="1">layer</option>
                                    <option value="2">form</option>
                                </select>
                            </div>
                            <div class="layui-input-inline">
                                <select name="modules" lay-verify="required" lay-search="">
                                    <option value="">结束时间</option>
                                    <option value="1">layer</option>
                                    <option value="2">form</option>
                                </select>
                            </div>
                        </td>
                        <td>
                            <button class="layui-btn">保存</button>
                        </td>
                    </tr>

                    <tr>
                        <td>周三</td>
                        <td style="text-align: left">
                            <div class="layui-input-inline">
                                <select name="modules" lay-verify="required" lay-search="">
                                    <option value="">开始时间</option>
                                    <option value="1">layer</option>
                                    <option value="2">form</option>
                                </select>
                            </div>
                            <div class="layui-input-inline">
                                <select name="modules" lay-verify="required" lay-search="">
                                    <option value="">结束时间</option>
                                    <option value="1">layer</option>
                                    <option value="2">form</option>
                                </select>
                            </div>
                        </td>
                        <td>
                            <button class="layui-btn">保存</button>
                        </td>
                    </tr>
                    <tr>
                        <td>周四</td>
                        <td style="text-align: left">
                            <div class="layui-input-inline">
                                <select name="modules" lay-verify="required" lay-search="">
                                    <option value="">开始时间</option>
                                    <option value="1">layer</option>
                                    <option value="2">form</option>
                                </select>
                            </div>
                            <div class="layui-input-inline">
                                <select name="modules" lay-verify="required" lay-search="">
                                    <option value="">结束时间</option>
                                    <option value="1">layer</option>
                                    <option value="2">form</option>
                                </select>
                            </div>
                        </td>
                        <td>
                            <button class="layui-btn">保存</button>
                        </td>
                    </tr>
                    <tr>
                        <td>周五</td>
                        <td style="text-align: left">
                            <div class="layui-input-inline">
                                <select name="modules" lay-verify="required" lay-search="">
                                    <option value="">开始时间</option>
                                    <option value="1">layer</option>
                                    <option value="2">form</option>
                                </select>
                            </div>
                            <div class="layui-input-inline">
                                <select name="modules" lay-verify="required" lay-search="">
                                    <option value="">结束时间</option>
                                    <option value="1">layer</option>
                                    <option value="2">form</option>
                                </select>
                            </div>
                        </td>
                        <td>
                            <button class="layui-btn">保存</button>
                        </td>
                    </tr>
                    <tr>
                        <td>周六</td>
                        <td style="text-align: left">
                            <div class="layui-input-inline">
                                <select name="modules" lay-verify="required" lay-search="">
                                    <option value="">开始时间</option>
                                    <option value="1">layer</option>
                                    <option value="2">form</option>
                                </select>
                            </div>
                            <div class="layui-input-inline">
                                <select name="modules" lay-verify="required" lay-search="">
                                    <option value="">结束时间</option>
                                    <option value="1">layer</option>
                                    <option value="2">form</option>
                                </select>
                            </div>
                        </td>
                        <td>
                            <button class="layui-btn">保存</button>
                        </td>
                    </tr>
                    <tr>
                        <td>周日</td>
                        <td style="text-align: left">
                            <div class="layui-input-inline">
                                <select name="modules" lay-verify="required" lay-search="">
                                    <option value="">开始时间</option>
                                    <option value="1">layer</option>
                                    <option value="2">form</option>
                                </select>
                            </div>
                            <div class="layui-input-inline">
                                <select name="modules" lay-verify="required" lay-search="">
                                    <option value="">结束时间</option>
                                    <option value="1">layer</option>
                                    <option value="2">form</option>
                                </select>
                            </div>
                        </td>
                        <td>
                            <button class="layui-btn">保存</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="layui-tab-item">
          <div class="layui-form">
              <form class="layui-form" action="" method="post">
                  <table class="layui-table">
                      <tbody>
                      <tr>
                          <td>
                              <select class="layui-form-select" name="week">
                                  <option value="1">周一</option>
                                  <option value="2">周二</option>
                                  <option value="3">周三</option>
                                  <option value="4">周四</option>
                                  <option value="5">周五</option>
                                  <option value="6">周六</option>
                                  <option value="7">周七</option>
                              </select>
                          </td>
                      </tr>
                      <tr>
                          <td>
                              <select class="layui-form-select" name="param1" id="param1">
                                  <option>9:00-21:00</option>
                                  <option>9:00-11:30</option>
                              </select>
                              <select  class="layui-form-select" name="param2" id="param2">
                                  <option>16:00-21:00</option>
                                  <option>14:00-21:00</option>
                              </select>
                          </td>
                      </tr>
                      <tr>
                          <td>
                              <button type="submit" class="btn btn-primary">提交</button>
                          </td>
                      </tr>
                      </tbody>
                  </table>


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
