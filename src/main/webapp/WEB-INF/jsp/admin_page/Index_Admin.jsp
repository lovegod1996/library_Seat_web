<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/10
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FloorAdmin</title>
</head>
<frameset rows="50,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="<%=request.getContextPath()%>/jsp/header_Admin" name="topFrame_Admin" scrolling="No" noresize="noresize"
           id="topFrame_Admin" title="topFrame_Admin"/>
    <frameset rows="*" cols="250,*" framespacing="0" frameborder="no" border="0">
        <frame src="<%=request.getContextPath()%>/jsp/leftmenu" name="leftFrame_Admin" scrolling="No"
               noresize="noresize" id="leftFrame_Admin"
               title="leftFrame_Admin"/>
        <frame src="<%=request.getContextPath()%>/jsp/main_Admin" name="mainFrame_Admin" id="mainFrame_Admin"
               title="mainFrame_Admin"/>
    </frameset>
</frameset>
<noframes>
    <body>

    </body>
</noframes>
</html>
