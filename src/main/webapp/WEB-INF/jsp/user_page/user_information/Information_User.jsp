<%--
  Created by IntelliJ IDEA.
  User: 1Q84
  Date: 2017/7/12
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
</head>
<frameset cols="25%,*" frameborder="no" border="0" framespacing="0">
    <frame src="<%=request.getContextPath()%>/view/userInformation_leftmenu" name="leftFrame_UserInformation"
           scrolling="No" noresize="noresize" id="leftFrame_UserInformation" title="leftFrame_UserInformation"/>
    <frame src="<%=request.getContextPath()%>/view/userInformation_rightcontent" name="mainFrame_UserInformation"
           id="mainFrame_UserInformation" title="mainFrame_UserInformation"/>
</frameset>
<noframes>
    <body>
    </body>
</noframes>
</html>
