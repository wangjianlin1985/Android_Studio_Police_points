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
<HTML><HEAD><TITLE>添加单位年度积分</TITLE> 
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
    var bname = document.getElementById("zxynd.bname").value;
    if(bname=="") {
        alert('请输入部门名称!');
        return false;
    }
    var jfjd = document.getElementById("zxynd.jfjd").value;
    if(jfjd=="") {
        alert('请输入积分年度!');
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
    <s:form action="Zxynd/Zxynd_AddZxynd.action" method="post" id="zxyndAddForm" onsubmit="return checkForm();"  enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">

  <tr>
    <td width=30%>部门ID:</td>
    <td width=70%><input id="zxynd.bid" name="zxynd.bid" type="text" size="8" /></td>
  </tr>

  <tr>
    <td width=30%>部门名称:</td>
    <td width=70%><input id="zxynd.bname" name="zxynd.bname" type="text" size="20" /></td>
  </tr>

  <tr>
    <td width=30%>部门类型:</td>
    <td width=70%><input id="zxynd.btypes" name="zxynd.btypes" type="text" size="8" /></td>
  </tr>

  <tr>
    <td width=30%>积分年度:</td>
    <td width=70%><input id="zxynd.jfjd" name="zxynd.jfjd" type="text" size="20" /></td>
  </tr>

  <tr>
    <td width=30%>刑事发案积分:</td>
    <td width=70%><input id="zxynd.xsfajf" name="zxynd.xsfajf" type="text" size="8" /></td>
  </tr>

  <tr>
    <td width=30%>号码走访积分:</td>
    <td width=70%><input id="zxynd.hmzfjf" name="zxynd.hmzfjf" type="text" size="8" /></td>
  </tr>

  <tr>
    <td width=30%>测评反馈积分:</td>
    <td width=70%><input id="zxynd.cpfkjf" name="zxynd.cpfkjf" type="text" size="8" /></td>
  </tr>

  <tr>
    <td width=30%>单位重视积分:</td>
    <td width=70%><input id="zxynd.dwzsjf" name="zxynd.dwzsjf" type="text" size="8" /></td>
  </tr>

  <tr>
    <td width=30%>合计分:</td>
    <td width=70%><input id="zxynd.hjf" name="zxynd.hjf" type="text" size="8" /></td>
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
