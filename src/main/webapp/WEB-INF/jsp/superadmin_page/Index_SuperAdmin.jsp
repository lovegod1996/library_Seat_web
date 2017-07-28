<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/19
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SuperAdmin</title>
</head>
<frameset rows="50,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="<%=request.getContextPath()%>/jsp/header_Admin" name="topFrame_SuperAdmin" scrolling="No" noresize="noresize"
           id="topFrame_SuperAdmin" title="topFrame_SuperAdmin"/>
    <frameset rows="*" cols="250,*" framespacing="0" frameborder="no" border="0">
        <frame src="<%=request.getContextPath()%>/view/leftmenu_SuperAdmin" name="leftFrame_SuperAdmin" scrolling="Yes"
               noresize="noresize" id="leftFrame_SuperAdmin"
               title="leftFrame_SuperAdmin"/>
        <frame src="<%=request.getContextPath()%>/view/main_SuperAdmin" name="mainFrame_SuperAdmin" id="mainFrame_SuperAdmin"
               title="mainFrame_SuperAdmin"/>
    </frameset>
</frameset>
<noframes>
    <body>

    </body>
</noframes>
</html>
