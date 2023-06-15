<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.chengxusheji.domain.Zxy" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Zxy zxy = (Zxy)request.getAttribute("zxy");

    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>修改单位季度积分</TITLE>
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
    var bname = document.getElementById("zxy.bname").value;
    if(bname=="") {
        alert('请输入部门名称!');
        return false;
    }
    var jfjd = document.getElementById("zxy.jfjd").value;
    if(jfjd=="") {
        alert('请输入积分季度!');
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
    <TD align="left" vAlign=top ><s:form action="Zxy/Zxy_ModifyZxy.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">
  <tr>
    <td width=30%>id:</td>
    <td width=70%><input id="zxy.id" name="zxy.id" type="text" value="<%=zxy.getId() %>" readOnly /></td>
  </tr>

  <tr>
    <td width=30%>部门ID:</td>
    <td width=70%><input id="zxy.bid" name="zxy.bid" type="text" size="8" value='<%=zxy.getBid() %>'/></td>
  </tr>

  <tr>
    <td width=30%>部门名称:</td>
    <td width=70%><input id="zxy.bname" name="zxy.bname" type="text" size="20" value='<%=zxy.getBname() %>'/></td>
  </tr>

  <tr>
    <td width=30%>部门类型:</td>
    <td width=70%><input id="zxy.btypes" name="zxy.btypes" type="text" size="8" value='<%=zxy.getBtypes() %>'/></td>
  </tr>

  <tr>
    <td width=30%>积分条件:</td>
    <td width=70%><input id="zxy.jid" name="zxy.jid" type="text" size="8" value='<%=zxy.getJid() %>'/></td>
  </tr>

  <tr>
    <td width=30%>积分季度:</td>
    <td width=70%><input id="zxy.jfjd" name="zxy.jfjd" type="text" size="20" value='<%=zxy.getJfjd() %>'/></td>
  </tr>

  <tr>
    <td width=30%>积分季度开始时间:</td>
    <% DateFormat jdsdateSDF = new SimpleDateFormat("yyyy-MM-dd");  %>
    <td width=70%><input type="text" readonly  id="zxy.jdsdate"  name="zxy.jdsdate" onclick="setDay(this);" value='<%=jdsdateSDF.format(zxy.getJdsdate()) %>'/></td>
  </tr>

  <tr>
    <td width=30%>积分季度结束时间:</td>
    <% DateFormat jdedateSDF = new SimpleDateFormat("yyyy-MM-dd");  %>
    <td width=70%><input type="text" readonly  id="zxy.jdedate"  name="zxy.jdedate" onclick="setDay(this);" value='<%=jdedateSDF.format(zxy.getJdedate()) %>'/></td>
  </tr>

  <tr>
    <td width=30%>刑事发案积分:</td>
    <td width=70%><input id="zxy.xsfajf" name="zxy.xsfajf" type="text" size="8" value='<%=zxy.getXsfajf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>号码走访积分:</td>
    <td width=70%><input id="zxy.hmzfjf" name="zxy.hmzfjf" type="text" size="8" value='<%=zxy.getHmzfjf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>测评反馈积分:</td>
    <td width=70%><input id="zxy.cpfkjf" name="zxy.cpfkjf" type="text" size="8" value='<%=zxy.getCpfkjf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>单位重视积分:</td>
    <td width=70%><input id="zxy.dwzsjf" name="zxy.dwzsjf" type="text" size="8" value='<%=zxy.getDwzsjf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>合计分:</td>
    <td width=70%><input id="zxy.hjf" name="zxy.hjf" type="text" size="8" value='<%=zxy.getHjf() %>'/></td>
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
