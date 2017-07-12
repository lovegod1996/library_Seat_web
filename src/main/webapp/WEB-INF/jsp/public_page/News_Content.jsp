<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        body {
            width: 50%;
            margin-left: 25%;
        }
        .content_title{
            text-align: center;
        }
        .content_bootom{
            margin-top: 5%;
        }

    </style>
</head>
<body>
<div class="content_title">
    <h2>${news.title}</h2>
    <span>发布时间</span>&nbsp;&nbsp;<span><fmt:formatDate value="${news.creattime}"
                                                       pattern="yyyy-MM-dd HH:mm:ss"/></span>
</div>
<hr>
<div class="content_main">
    <p>
        ${news.content}
      </p>
</div>
<div class="content_bootom">
    <c:if test="${news.nid!=1}">
        <span>上一篇</span>&nbsp;<a href="<%=request.getContextPath()%>/jsp/news_Content?nid=${news.nid-1}">点我就是上一篇</a>
    </c:if>
    <br>
    <span>下一篇</span>&nbsp;<a href="<%=request.getContextPath()%>/jsp/news_Content?nid=${news.nid+1}">点我就是下一篇</a>
</div>

<script type="text/javascript">
    <c:if test="${!empty error_msg}">alert("${error_msg}");</c:if>
</script>

</body>
</html>
