<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.chengxusheji.domain.Zxyjf" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Zxyjf zxyjf = (Zxyjf)request.getAttribute("zxyjf");

    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>�޸ĵ�λ�񾯻���</TITLE>
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
    var bname = document.getElementById("zxyjf.bname").value;
    if(bname=="") {
        alert('�����벿������!');
        return false;
    }
    var sid = document.getElementById("zxyjf.sid").value;
    if(sid=="") {
        alert('�����뾯Աid!');
        return false;
    }
    var sname = document.getElementById("zxyjf.sname").value;
    if(sname=="") {
        alert('�����뾯Ա����!');
        return false;
    }
    var jftj = document.getElementById("zxyjf.jftj").value;
    if(jftj=="") {
        alert('�������������!');
        return false;
    }
    var jfjd = document.getElementById("zxyjf.jfjd").value;
    if(jfjd=="") {
        alert('��������ּ���!');
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
    <TD align="left" vAlign=top ><s:form action="Zxyjf/Zxyjf_ModifyZxyjf.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">
  <tr>
    <td width=30%>id:</td>
    <td width=70%><input id="zxyjf.id" name="zxyjf.id" type="text" value="<%=zxyjf.getId() %>" readOnly /></td>
  </tr>

  <tr>
    <td width=30%>����id:</td>
    <td width=70%><input id="zxyjf.bid" name="zxyjf.bid" type="text" size="8" value='<%=zxyjf.getBid() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��������:</td>
    <td width=70%><input id="zxyjf.bname" name="zxyjf.bname" type="text" size="30" value='<%=zxyjf.getBname() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��������:</td>
    <td width=70%><input id="zxyjf.btypes" name="zxyjf.btypes" type="text" size="8" value='<%=zxyjf.getBtypes() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��Աid:</td>
    <td width=70%><input id="zxyjf.sid" name="zxyjf.sid" type="text" size="20" value='<%=zxyjf.getSid() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��Ա����:</td>
    <td width=70%><input id="zxyjf.sname" name="zxyjf.sname" type="text" size="20" value='<%=zxyjf.getSname() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��������ID:</td>
    <td width=70%><input id="zxyjf.jid" name="zxyjf.jid" type="text" size="8" value='<%=zxyjf.getJid() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��������:</td>
    <td width=70%><textarea id="zxyjf.jftj" name="zxyjf.jftj" rows=5 cols=50><%=zxyjf.getJftj() %></textarea></td>
  </tr>

  <tr>
    <td width=30%>���ּ���:</td>
    <td width=70%><input id="zxyjf.jfjd" name="zxyjf.jfjd" type="text" size="40" value='<%=zxyjf.getJfjd() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��������:</td>
    <% DateFormat jfdateSDF = new SimpleDateFormat("yyyy-MM-dd");  %>
    <td width=70%><input type="text" readonly  id="zxyjf.jfdate"  name="zxyjf.jfdate" onclick="setDay(this);" value='<%=jfdateSDF.format(zxyjf.getJfdate()) %>'/></td>
  </tr>

  <tr>
    <td width=30%>���ּ��ȿ�ʼʱ��:</td>
    <% DateFormat jdsdateSDF = new SimpleDateFormat("yyyy-MM-dd");  %>
    <td width=70%><input type="text" readonly  id="zxyjf.jdsdate"  name="zxyjf.jdsdate" onclick="setDay(this);" value='<%=jdsdateSDF.format(zxyjf.getJdsdate()) %>'/></td>
  </tr>

  <tr>
    <td width=30%>����:</td>
    <td width=70%><input id="zxyjf.fs" name="zxyjf.fs" type="text" size="8" value='<%=zxyjf.getFs() %>'/></td>
  </tr>

  <tr>
    <td width=30%>����:</td>
    <td width=70%><input id="zxyjf.sl" name="zxyjf.sl" type="text" size="8" value='<%=zxyjf.getSl() %>'/></td>
  </tr>

  <tr>
    <td width=30%>���·�������:</td>
    <td width=70%><input id="zxyjf.xsfajf" name="zxyjf.xsfajf" type="text" size="8" value='<%=zxyjf.getXsfajf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>�����߷û���:</td>
    <td width=70%><input id="zxyjf.hmzfjf" name="zxyjf.hmzfjf" type="text" size="8" value='<%=zxyjf.getHmzfjf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>������������:</td>
    <td width=70%><input id="zxyjf.cpfkjf" name="zxyjf.cpfkjf" type="text" size="8" value='<%=zxyjf.getCpfkjf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��λ���ӻ���:</td>
    <td width=70%><input id="zxyjf.dwzsjf" name="zxyjf.dwzsjf" type="text" size="8" value='<%=zxyjf.getDwzsjf() %>'/></td>
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
