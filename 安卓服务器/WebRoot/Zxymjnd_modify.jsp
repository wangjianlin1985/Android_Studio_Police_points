<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.chengxusheji.domain.Zxymjnd" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Zxymjnd zxymjnd = (Zxymjnd)request.getAttribute("zxymjnd");

    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>�޸ľ�Ա��Ȼ���</TITLE>
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
    var bname = document.getElementById("zxymjnd.bname").value;
    if(bname=="") {
        alert('�����벿������!');
        return false;
    }
    var sname = document.getElementById("zxymjnd.sname").value;
    if(sname=="") {
        alert('�����뾯Ա����!');
        return false;
    }
    var jfjd = document.getElementById("zxymjnd.jfjd").value;
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
    <TD align="left" vAlign=top ><s:form action="Zxymjnd/Zxymjnd_ModifyZxymjnd.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">
  <tr>
    <td width=30%>id:</td>
    <td width=70%><input id="zxymjnd.id" name="zxymjnd.id" type="text" value="<%=zxymjnd.getId() %>" readOnly /></td>
  </tr>

  <tr>
    <td width=30%>����ID:</td>
    <td width=70%><input id="zxymjnd.bid" name="zxymjnd.bid" type="text" size="8" value='<%=zxymjnd.getBid() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��������:</td>
    <td width=70%><input id="zxymjnd.bname" name="zxymjnd.bname" type="text" size="30" value='<%=zxymjnd.getBname() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��Աid:</td>
    <td width=70%><input id="zxymjnd.sid" name="zxymjnd.sid" type="text" size="8" value='<%=zxymjnd.getSid() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��Ա����:</td>
    <td width=70%><input id="zxymjnd.sname" name="zxymjnd.sname" type="text" size="20" value='<%=zxymjnd.getSname() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��������:</td>
    <td width=70%><input id="zxymjnd.btypes" name="zxymjnd.btypes" type="text" size="8" value='<%=zxymjnd.getBtypes() %>'/></td>
  </tr>

  <tr>
    <td width=30%>�������:</td>
    <td width=70%><input id="zxymjnd.jfjd" name="zxymjnd.jfjd" type="text" size="20" value='<%=zxymjnd.getJfjd() %>'/></td>
  </tr>

  <tr>
    <td width=30%>���·�������:</td>
    <td width=70%><input id="zxymjnd.xsfajf" name="zxymjnd.xsfajf" type="text" size="8" value='<%=zxymjnd.getXsfajf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>�����߷û���:</td>
    <td width=70%><input id="zxymjnd.hmzfjf" name="zxymjnd.hmzfjf" type="text" size="8" value='<%=zxymjnd.getHmzfjf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>������������:</td>
    <td width=70%><input id="zxymjnd.cpfkjf" name="zxymjnd.cpfkjf" type="text" size="8" value='<%=zxymjnd.getCpfkjf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��λ���ӻ���:</td>
    <td width=70%><input id="zxymjnd.dwzsjf" name="zxymjnd.dwzsjf" type="text" size="8" value='<%=zxymjnd.getDwzsjf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>�ϼƷ�:</td>
    <td width=70%><input id="zxymjnd.hjf" name="zxymjnd.hjf" type="text" size="8" value='<%=zxymjnd.getHjf() %>'/></td>
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
