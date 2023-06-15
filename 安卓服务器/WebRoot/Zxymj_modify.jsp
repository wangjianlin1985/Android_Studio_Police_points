<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.chengxusheji.domain.Zxymj" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Zxymj zxymj = (Zxymj)request.getAttribute("zxymj");

    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>修改警员季度积分</TITLE>
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
    var bname = document.getElementById("zxymj.bname").value;
    if(bname=="") {
        alert('请输入部门名称!');
        return false;
    }
    var sname = document.getElementById("zxymj.sname").value;
    if(sname=="") {
        alert('请输入警员姓名!');
        return false;
    }
    var jfjd = document.getElementById("zxymj.jfjd").value;
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
    <TD align="left" vAlign=top ><s:form action="Zxymj/Zxymj_ModifyZxymj.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">
  <tr>
    <td width=30%>id:</td>
    <td width=70%><input id="zxymj.id" name="zxymj.id" type="text" value="<%=zxymj.getId() %>" readOnly /></td>
  </tr>

  <tr>
    <td width=30%>部门id:</td>
    <td width=70%><input id="zxymj.bid" name="zxymj.bid" type="text" size="8" value='<%=zxymj.getBid() %>'/></td>
  </tr>

  <tr>
    <td width=30%>部门名称:</td>
    <td width=70%><input id="zxymj.bname" name="zxymj.bname" type="text" size="20" value='<%=zxymj.getBname() %>'/></td>
  </tr>

  <tr>
    <td width=30%>警员id:</td>
    <td width=70%><input id="zxymj.sid" name="zxymj.sid" type="text" size="8" value='<%=zxymj.getSid() %>'/></td>
  </tr>

  <tr>
    <td width=30%>警员姓名:</td>
    <td width=70%><input id="zxymj.sname" name="zxymj.sname" type="text" size="20" value='<%=zxymj.getSname() %>'/></td>
  </tr>

  <tr>
    <td width=30%>部门类型:</td>
    <td width=70%><input id="zxymj.btypes" name="zxymj.btypes" type="text" size="8" value='<%=zxymj.getBtypes() %>'/></td>
  </tr>

  <tr>
    <td width=30%>积分条件ID:</td>
    <td width=70%><input id="zxymj.jid" name="zxymj.jid" type="text" size="8" value='<%=zxymj.getJid() %>'/></td>
  </tr>

  <tr>
    <td width=30%>积分季度:</td>
    <td width=70%><input id="zxymj.jfjd" name="zxymj.jfjd" type="text" size="20" value='<%=zxymj.getJfjd() %>'/></td>
  </tr>

  <tr>
    <td width=30%>积分季度开始时间:</td>
    <% DateFormat jdsdateSDF = new SimpleDateFormat("yyyy-MM-dd");  %>
    <td width=70%><input type="text" readonly  id="zxymj.jdsdate"  name="zxymj.jdsdate" onclick="setDay(this);" value='<%=jdsdateSDF.format(zxymj.getJdsdate()) %>'/></td>
  </tr>

  <tr>
    <td width=30%>积分季度结束时间:</td>
    <% DateFormat jdedateSDF = new SimpleDateFormat("yyyy-MM-dd");  %>
    <td width=70%><input type="text" readonly  id="zxymj.jdedate"  name="zxymj.jdedate" onclick="setDay(this);" value='<%=jdedateSDF.format(zxymj.getJdedate()) %>'/></td>
  </tr>

  <tr>
    <td width=30%>刑事发案积分:</td>
    <td width=70%><input id="zxymj.xsfajf" name="zxymj.xsfajf" type="text" size="8" value='<%=zxymj.getXsfajf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>号码走访积分:</td>
    <td width=70%><input id="zxymj.hmzfjf" name="zxymj.hmzfjf" type="text" size="8" value='<%=zxymj.getHmzfjf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>测评反馈积分:</td>
    <td width=70%><input id="zxymj.cpfkjf" name="zxymj.cpfkjf" type="text" size="8" value='<%=zxymj.getCpfkjf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>单位重视积分:</td>
    <td width=70%><input id="zxymj.dwzsjf" name="zxymj.dwzsjf" type="text" size="8" value='<%=zxymj.getDwzsjf() %>'/></td>
  </tr>

  <tr>
    <td width=30%>合计分:</td>
    <td width=70%><input id="zxymj.hjf" name="zxymj.hjf" type="text" size="8" value='<%=zxymj.getHjf() %>'/></td>
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
