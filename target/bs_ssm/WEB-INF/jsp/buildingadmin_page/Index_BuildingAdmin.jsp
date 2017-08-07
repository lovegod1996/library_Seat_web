<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/19
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BuildingAdmin</title>
</head>
<%--<frameset rows="50,*" cols="*" frameborder="no" border="0" framespacing="0">--%>
    <%--<frame src="<%=request.getContextPath()%>/jsp/header_Admin" name="topFrame_BuildingAdmin" scrolling="No" noresize="noresize"--%>
           <%--id="topFrame_BuildingAdmin" title="topFrame_BuildingAdmin"/>--%>
    <%--<frameset rows="*" cols="250,*" framespacing="0" frameborder="no" border="0">--%>
        <%--<frame src="<%=request.getContextPath()%>/view/leftmenu_BuildingAdmin" name="leftFrame_BuildingAdmin" scrolling="Yes"--%>
               <%--noresize="noresize" id="leftFrame_BuildingAdmin"--%>
               <%--title="leftFrame_BuildingAdmin"/>--%>
        <%--<frame src="<%=request.getContextPath()%>/view/main_BuildingAdmin" name="mainFrame_BuildingAdmin" id="mainFrame_BuildingAdmin"--%>
               <%--title="mainFrame_BuildingAdmin"/>--%>
    <%--</frameset>--%>
<%--</frameset>--%>
<%--<noframes>--%>
    <%--<body>--%>

    <%--</body>--%>
<%--</noframes>--%>
<frameset rows="50,*,50" frameborder="no" border="0" framespacing="0">
    <frame src="<%=request.getContextPath()%>/jsp/header_Admin" name="topFrame_BuildingAdmin" scrolling="No" noresize="noresize" id="topFrame_BuildingAdmin" title="topFrame_BuildingAdmin" />
    <frameset cols="250,*" frameborder="no" border="0" framespacing="0">
        <frame src="<%=request.getContextPath()%>/view/leftmenu_BuildingAdmin" name="leftmenu_BuildingAdmin" scrolling="Yes" noresize="noresize" id="leftmenu_BuildingAdmin" title="leftmenu_BuildingAdmin" />
        <frame src="<%=request.getContextPath()%>/view/main_BuildingAdmin" name="main_BuildingAdmin" id="main_BuildingAdmin" title="main_BuildingAdmin" />
    </frameset>
    <frame src="<%=request.getContextPath()%>/jsp/footer" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" title="bottomFrame" />
</frameset>
<noframes><body>
</body></noframes>
</html>
