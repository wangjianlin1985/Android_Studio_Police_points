<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>添加积分条件</TITLE> 
<STYLE type=text/css>
BODY {
    	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
.label {font-style.:italic; }
.errorLabel {font-style.:italic;  color:red; }
.errorMessage {font-weight:bold; color:red; }
</STYLE>
 <script src="<%=basePath %>calendar.js"></script>
<script language="javascript">
/*验证表单*/
function checkForm() {
    var jftj = document.getElementById("jftjc.jftj").value;
    if(jftj=="") {
        alert('请输入积分条件!');
        return false;
    }
    return true; 
}
 </script>
</HEAD>

<BODY background="<%=basePath %>images/adminBg.jpg">
<s:fielderror cssStyle="color:red" />
<TABLE align="center" height="100%" cellSpacing=0 cellPadding=0 width="80%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top >
    <s:form action="Jftjc/Jftjc_AddJftjc.action" method="post" id="jftjcAddForm" onsubmit="return checkForm();"  enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">

  <tr>
    <td width=30%>积分条件:</td>
    <td width=70%><textarea id="jftjc.jftj" name="jftjc.jftj" rows="5" cols="50"></textarea></td>
  </tr>

  <tr>
    <td width=30%>分数:</td>
    <td width=70%><input id="jftjc.fs" name="jftjc.fs" type="text" size="8" /></td>
  </tr>

  <tr>
    <td width=30%>积分类型:</td>
    <td width=70%><input id="jftjc.typeid" name="jftjc.typeid" type="text" size="8" /></td>
  </tr>

  <tr>
    <td width=30%>mtypeid:</td>
    <td width=70%><input id="jftjc.mtypeid" name="jftjc.mtypeid" type="text" size="8" /></td>
  </tr>

  <tr>
    <td width=30%>备注:</td>
    <td width=70%><textarea id="jftjc.bz" name="jftjc.bz" rows="5" cols="50"></textarea></td>
  </tr>

  <tr bgcolor='#FFFFFF'>
      <td colspan="4" align="center">
        <input type='submit' name='button' value='保存' >
        &nbsp;&nbsp;
        <input type="reset" value='重写' />
      </td>
    </tr>

</table>
</s:form>
   </TD></TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
