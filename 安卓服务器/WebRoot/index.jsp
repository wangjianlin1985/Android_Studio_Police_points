<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%> <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>双提升积分管理系统-首页</title>
<link href="<%=basePath %>css/index.css" rel="stylesheet" type="text/css" />
 </head>
<body>
<div id="container">
	<div id="banner"><img src="<%=basePath %>images/logo.gif" /></div>
	<div id="globallink">
		<ul>
			<li><a href="<%=basePath %>index.jsp">首页</a></li>
			<li><a href="<%=basePath %>Jftjc/Jftjc_FrontQueryJftjc.action" target="OfficeMain">积分条件</a></li> 
			<li><a href="<%=basePath %>Zxyjf/Zxyjf_FrontQueryZxyjf.action" target="OfficeMain">单位民警积分</a></li> 
			<li><a href="<%=basePath %>Zxy/Zxy_FrontQueryZxy.action" target="OfficeMain">单位季度积分</a></li> 
			<li><a href="<%=basePath %>Zxymj/Zxymj_FrontQueryZxymj.action" target="OfficeMain">警员季度积分</a></li> 
			<li><a href="<%=basePath %>Zxynd/Zxynd_FrontQueryZxynd.action" target="OfficeMain">单位年度积分</a></li> 
			<li><a href="<%=basePath %>Zxymjnd/Zxymjnd_FrontQueryZxymjnd.action" target="OfficeMain">警员年度积分</a></li> 
		</ul>
		<br />
	</div> 
	<div id="main">
	 <iframe id="frame1" src="<%=basePath %>desk.jsp" name="OfficeMain" width="100%" height="100%" scrolling="yes" marginwidth=0 marginheight=0 frameborder=0 vspace=0 hspace=0 >
	 </iframe>
	</div>
	<div id="footer">
		<p>双鱼林设计 QQ:287307421或254540457 &copy;版权所有 <a href="http://www.shuangyulin.com" target="_blank">双鱼林设计网</a>&nbsp;&nbsp;<a href="<%=basePath%>login/login_view.action"><font color=red>后台登陆</font></a></p>
	</div>
</div>
</body>
</html>
