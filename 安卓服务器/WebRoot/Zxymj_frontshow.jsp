<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.chengxusheji.domain.Zxymj" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Zxymj zxymj = (Zxymj)request.getAttribute("zxymj");

%>
<HTML><HEAD><TITLE>�鿴��Ա���Ȼ���</TITLE>
<STYLE type=text/css>
body{margin:0px; font-size:12px; background-image:url(<%=basePath%>images/bg.jpg); background-position:bottom; background-repeat:repeat-x; background-color:#A2D5F0;}
.STYLE1 {color: #ECE9D8}
.label {font-style.:italic; }
.errorLabel {font-style.:italic;  color:red; }
.errorMessage {font-weight:bold; color:red; }
</STYLE>
 <script src="<%=basePath %>calendar.js"></script>
</HEAD>
<BODY><br/><br/>
<s:fielderror cssStyle="color:red" />
<TABLE align="center" height="100%" cellSpacing=0 cellPadding=0 width="80%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top ><s:form action="" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3'  class="tablewidth">
  <tr>
    <td width=30%>id:</td>
    <td width=70%><%=zxymj.getId() %></td>
  </tr>

  <tr>
    <td width=30%>����id:</td>
    <td width=70%><%=zxymj.getBid() %></td>
  </tr>

  <tr>
    <td width=30%>��������:</td>
    <td width=70%><%=zxymj.getBname() %></td>
  </tr>

  <tr>
    <td width=30%>��Աid:</td>
    <td width=70%><%=zxymj.getSid() %></td>
  </tr>

  <tr>
    <td width=30%>��Ա����:</td>
    <td width=70%><%=zxymj.getSname() %></td>
  </tr>

  <tr>
    <td width=30%>��������:</td>
    <td width=70%><%=zxymj.getBtypes() %></td>
  </tr>

  <tr>
    <td width=30%>��������ID:</td>
    <td width=70%><%=zxymj.getJid() %></td>
  </tr>

  <tr>
    <td width=30%>���ּ���:</td>
    <td width=70%><%=zxymj.getJfjd() %></td>
  </tr>

  <tr>
    <td width=30%>���ּ��ȿ�ʼʱ��:</td>
        <% java.text.DateFormat jdsdateSDF = new java.text.SimpleDateFormat("yyyy-MM-dd");  %>
    <td width=70%><%=jdsdateSDF.format(zxymj.getJdsdate()) %></td>
  </tr>

  <tr>
    <td width=30%>���ּ��Ƚ���ʱ��:</td>
        <% java.text.DateFormat jdedateSDF = new java.text.SimpleDateFormat("yyyy-MM-dd");  %>
    <td width=70%><%=jdedateSDF.format(zxymj.getJdedate()) %></td>
  </tr>

  <tr>
    <td width=30%>���·�������:</td>
    <td width=70%><%=zxymj.getXsfajf() %></td>
  </tr>

  <tr>
    <td width=30%>�����߷û���:</td>
    <td width=70%><%=zxymj.getHmzfjf() %></td>
  </tr>

  <tr>
    <td width=30%>������������:</td>
    <td width=70%><%=zxymj.getCpfkjf() %></td>
  </tr>

  <tr>
    <td width=30%>��λ���ӻ���:</td>
    <td width=70%><%=zxymj.getDwzsjf() %></td>
  </tr>

  <tr>
    <td width=30%>�ϼƷ�:</td>
    <td width=70%><%=zxymj.getHjf() %></td>
  </tr>

  <tr>
      <td colspan="4" align="center">
        <input type="button" value="����" onclick="history.back();"/>
      </td>
    </tr>

</table>
</s:form>
   </TD></TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
