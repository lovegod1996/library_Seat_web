<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书馆座位预约系统</title>
</head>
<frameset rows="50,*,35" frameborder="no" border="0" framespacing="0">
    <frame src="<%=request.getContextPath()%>/jsp/header_User" name="topFrame_User" scrolling="No" noresize="noresize"
           id="topFrame_User" title="topFrame_User"/>
    <frame src="<%=request.getContextPath()%>/jsp/main_User" name="mainFrame_User" id="mainFrame_User"
           title="mainFrame_User"/>
    <frame src="<%=request.getContextPath()%>/jsp/footer" name="bottomFrame" id="bottomFrame"
           title="bottomFrame"/>
</frameset>
<noframes>
    <body>
    </body>
</noframes>
</html>