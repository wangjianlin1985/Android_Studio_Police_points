<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.chengxusheji.domain.Zxynd" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Zxynd zxynd = (Zxynd)request.getAttribute("zxynd");

    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>�޸ĵ�λ��Ȼ���</TITLE>
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
/*��֤��*/
function checkForm() {
    var bname = document.getElementById("zxynd.bname").value;
    if(bname=="") {
        alert('�����벿������!');
        return false;
    }
    var jfjd = document.getElementById("zxynd.jfjd").value;
    if(jfjd=="") {
        alert('������������!');
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
    <TD align="left" vAlign=top ><s:form action="Zxynd/Zxynd_ModifyZxynd.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">
  <tr>
    <td width=30%>id:</td>
    <td width=70%><input id="zxynd.id" name="zxynd.id" type="text" value="<%=zxynd.getId() %>" readOnly /></td>
  </tr>

  <tr>
    <td width=30%>����ID:</td>
    <td width=70%><input id="zxynd.bid" name="zxynd.bid" type="text" size="8" value='<%=zxynd.getBid() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��������:</td>
    <td width=70%><input id="zxynd.bname" name="zxynd.bname" type="text" size="20" value='<%=zxynd.getBname() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��������:</td>
    <td width=70%><input id="zxynd.btypes" name="zxynd.btypes" type="text" size="8" value='<%=zxynd.getBtypes() %>'/></td>
  </tr>

  <tr>
    <td width=30%>�������:</td>
    <td width=70%><input id="zxynd.jfjd" name="zxynd.jfjd" type="text" size="20" value='<%=zxynd.getJfjd() %>'/></td>
  </tr>

  <tr>
    <td width=30%>���·�������:</td>
    <td width=70%><input id="zxynd.xsfajf" name="zxynd.xsfajf" type="text" size="8" value='<%=zxynd.getXsfajf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>�����߷û���:</td>
    <td width=70%><input id="zxynd.hmzfjf" name="zxynd.hmzfjf" type="text" size="8" value='<%=zxynd.getHmzfjf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>������������:</td>
    <td width=70%><input id="zxynd.cpfkjf" name="zxynd.cpfkjf" type="text" size="8" value='<%=zxynd.getCpfkjf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��λ���ӻ���:</td>
    <td width=70%><input id="zxynd.dwzsjf" name="zxynd.dwzsjf" type="text" size="8" value='<%=zxynd.getDwzsjf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>�ϼƷ�:</td>
    <td width=70%><input id="zxynd.hjf" name="zxynd.hjf" type="text" size="8" value='<%=zxynd.getHjf() %>'/></td>
  </tr>

  <tr bgcolor='#FFFFFF'>
      <td colspan="4" align="center">
        <input type='submit' name='button' value='����' >
        &nbsp;&nbsp;
        <input type="reset" value='��д' />
      </td>
    </tr>

</table>
</s:form>
   </TD></TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
