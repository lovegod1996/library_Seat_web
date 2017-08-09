<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/21
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>学生预约情况统计</title>
    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <!-- jQuery -->
    <script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>
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
    </style>
</head>
<body>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">学生预约情况查看</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <table class="layui-table" id="tb1">
                <caption style="text-align: center"><h3>查询学生学习情况</h3></caption>
                <tbody>
                <tr>
                    <td>
                        <label for="sno">请输入要查询的学号：</label>
                        <input type="text" placeholder="请输入学号" id="sno" width="100%">
                    </td>
                    <td>
                        <button type="button" id="find">查询</button>
                    </td>
                </tr>
                </tbody>
            </table>
            <hr>
            <table id="total" style="width: 100%" class="layui-table">
                <caption style="text-align: center"><h4>学习情况概览</h4></caption>
                <thead>
                <th>姓名</th>
                <th>学号</th>
                <th>学习时长</th>
                <th>学习次数</th>
                <th>失信次数</th>
                <th>失信率</th>
                </thead>
                <tbody>

                </tbody>
            </table>
            <hr>
            <table id="detail" style="width: 100%" class="layui-table">
                <caption style="text-align: center"><h4>学习记录</h4></caption>
                <thead>
                <th>学号</th>
                <th>场馆</th>
                <th>座位号</th>
                <th>入座时间</th>
                <th>离开时间</th>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript">

    $("#find").click(function () {
        var sno = document.getElementById("sno").value;
        if (sno == null) {
            alert("请输入正确的学号");
        } else {
            $.ajax({
                url: "${pageContext.request.contextPath }/view/findStudentStuday.form",
                data: 'sno=' + sno,
                type: 'post',
                success: function (result) {
                    $("#total tbody tr").remove();//清空原来的选项
                    //   alert(result);
                result=eval("("+result+")");
                $("#total tbody").append("<tr> <td>"+result.username+"</td> <td> "+result.sno+"</td> <td>"+result.learntime+"小时</td> <td>"+result.allLearn+"</td> <td>"+result.undeal+"</td>  <td>"+result.dealpro+"%</td>  </tr>");
////                for(var i=0;i<result.length;i++)
////                {
////                    $("#major").append("<option > "+result[i].mname+"</option>")
////                }
//                    result = eval(result);
//                    for (var i = 0; i < result.length; i++) {
//                        $("#total tbody").append("<tr> <td>" + result[i].mid + "</td> <td> <a href='/sinan/university/majordetail.form?mid=" + result[i].mid + "' >" + result[i].mname + "</a></td> <td>" + result[i].fields + "</td> <td>" + result[i].subject + "</td> <td>" + result[i].mlevel + "</td>  </tr>");
//                    }
                }
            });
            $.ajax({
                url: "${pageContext.request.contextPath }/view/findStudentBookings.form",
                data: 'sno=' + sno,
                type: 'post',
                success: function (result) {
                    $("#detail tbody tr").remove();//清空原来的选项

//                    result=eval("("+result+")");
//                    $("#total tbody").append("<tr> <td>"+result.username+"</td> <td> "+result.sno+"</td> <td>"+result.learntime+"小时</td> <td>"+result.allLearn+"</td> <td>"+result.undeal+"</td>  <td>"+result.dealpro+"%</td>  </tr>");
////                for(var i=0;i<result.length;i++)
////                {
////                    $("#major").append("<option > "+result[i].mname+"</option>")
////                }
                    result = eval(result);
                    for (var i = 0; i < result.length; i++) {
                        var stime="";
                        var etime="";
                        if(result[i].stime!==null){
                            var date1=new Date(parseInt(result[i].stime,10));
                            stime=getDateTime(date1);
                            var date2=new Date(parseInt(result[i].etime,10));
                            etime=getDateTime(date2);
                        }
                        $("#detail tbody").append("<tr> <td>" + result[i].sno + "</td> <td> " + result[i].floor + "</td> <td>" + result[i].seatnumber + "</td> <td>" + stime+ "</td> <td>" + etime+ "</td>  </tr>");
                    }
                }
            });

        }
    });

    function getDateTime(date) {
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var hh = date.getHours();
        var mm = date.getMinutes();
        var ss = date.getSeconds();
        return year + "-" + month + "-" + day + " " + hh + ":" + mm + ":" + ss;
    }

</script>


</body>
</html>
