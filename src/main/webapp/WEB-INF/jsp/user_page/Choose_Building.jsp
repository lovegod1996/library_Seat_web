<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/20
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            width: 50%;
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
            color: #fff;
        }

        .box ul li .dask a {
            color: green;
            text-decoration: none
        }

        li {
            list-style: none;
        }

        a {
            width: 50%;
            height: 100%;
        }
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
            $('#dask1').click(function () {
                window.location.href = "/LS/jsp/book_Seat_User";
            });
            $('#dask2').click(function () {
                window.location.href = "/LS/jsp/book_Seat_User";
            })
        });

    </script>
</head>
<body>
<div class="box">
    <ul>
        <li>
            <a href="#"><img src="<%=request.getContextPath()%>/img/touming.png"></a>
            <div class="dask" id="dask1">
                <p style="font-size: 30px;margin-top: 20px;text-align: center">北楼</p>
            </div>
        </li>
        <li>
            <a href="#"><img src="<%=request.getContextPath()%>/img/touming.png"></a>
            <div class="dask" id="dask2">
                <p style="font-size: 30px;margin-top: 20px;text-align: center">南楼</p>
            </div>
        </li>
    </ul>
</div>
</body>
</html>
