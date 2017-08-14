<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/20
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>选择南北楼</title>
    <%--layui --%>
    <link href="<%=request.getContextPath()%>/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>
    <style type="text/css">
        body {
            background-image: url("/LS/img/library.jpg");
            background-size: 100% 100%;
            -moz-background-size: 100% 100%;
        }

        .box {
            width: 100%;
            height: 100%;
            overflow: hidden;
        }

        .box ul li {
            /*宽度根据<li></li>数量自动计算填充*/
            /*width: 50%;*/
            height: 100%;
            float: left;
            position: relative;
            overflow: hidden;
        }

        .box ul li .dask {
            width: 100%;
            height: 100%;
            background: #000;
            opacity: 0.8;
            position: absolute;
            left: 0;
        }

        .box ul li .dask p {
            margin-top: 10%;
            color: #fff;
        }

        li {
            list-style: none;
        }

        .ShowFloors {
            margin-top: 50%;
            text-align: center;

        }
        .Roundseat {
            width: 25%;
            /*height: 40px;*/
            background-color: #8fe2fc;
            text-align: center;
            padding: 10px;
            color: #ffffff;
            float: left;
            margin-left: 10px;
            margin-top: 10px;
        }
        .TheFloor{
            width: 100%;
            height: 100%;
            background: transparent;
            color: #6c5c32;
        }
        .TheFloor:hover{color: #ffffff}

    </style>
    <script>
        $(function () {
            $(".box ul li").hover(
                function () {
                    $(this).find(".dask").stop().delay(50).animate({"top": 0, opacity: 0.8}, 300)
                },
                function () {
                    $(this).find(".dask").stop().animate({"top": 0, opacity: 0}, 300)
                }
            );

            var $ul = $(".UL");
            var ul_width = $ul.width();
            var li_num = $ul.find("li").length;
            $ul.find("li").css("width", ul_width / li_num);

//            $('.dask').click(function () {
//                window.location.href = "/LS/jsp/book_Seat_User";
//            });
        });
    </script>
</head>
<body>
<div class="box">
    <ul class="UL">

        <c:forEach items="${buildFloors}" var="building">
            <li>
                <a href="#"><img src="<%=request.getContextPath()%>/img/touming.png"></a>
                <div class="dask">
                    <p style="font-size: 30px;margin-top: 20px;text-align: center">${building.employer}</p>
                    <div class="ShowFloors">

                        <c:forEach items="${building.floors}" var="floor">
                            <div class="Roundseat">
                                <a class="TheFloor" type="button" href="<%=request.getContextPath()%>/jsp/book_Seat_User?fid=${floor.fid}&day=${day}">${floor.employer}</a>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>

    <%--底部缩略图居中--%>
    <%--<div style="width: 30%;height: 50px;margin-left: 35%;position: fixed;bottom: 0;background-color: #2b542c">--%>
    <%--<ul class="UL_BOTTOM">--%>

    <%--</ul>--%>
    <%--</div>--%>
</div>
</body>
</html>
