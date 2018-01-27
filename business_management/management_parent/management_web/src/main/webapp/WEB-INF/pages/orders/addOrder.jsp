<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK rel="stylesheet" type="text/css" href="${ctx}js/easyui/styles/default.css">

<title>创建采购单</title>

<script type="text/javascript">
function ordersave(){
	document.orderform.submit();
	
}
</script>
</head>
<body>

<form id="orderform" name="orderform" action="${ctx}/flow/orderFlowAction_addOrderSubmit.action" method="post">
<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" bgColor=#c4d8ed>

   <TBODY>
   <TR>
				<TD background=images/r_0.gif width="100%">
					<TABLE cellSpacing=0 cellPadding=0 width="100%">
						<TBODY>
							<TR>
								<TD>&nbsp;添加采购单</TD>
								<TD align=right>&nbsp;</TD>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
			
			<TR>
				<TD>
					<TABLE class="toptable grid" border=1 cellSpacing=1 cellPadding=4
						align=center>
						<TBODY>
							
							<TR>
								<TD height=30 width="15%" align=right >采购单名称：</TD>
								<TD class=category width="35%">
								<input type="text" name="orderCustom.name"/>
								</TD>
								<TD height=30 width="15%" align=right >采购单内容：</TD>
								<TD class=category width="35%">
								<textarea rows="3" cols="30" name="orderCustom.content"></textarea>
								</TD>
							</TR>
							<TR>
								<TD height=30 width="15%" align=right >采购金额：</TD>
								<TD class=category width="35%">
								<input type="text" name="orderCustom.price"/>
								</TD>
								<TD height=30 width="15%" align=right ></TD>
								<TD class=category width="35%">
								
								</div>
								</TD>
								
								
							</TR>
							
							<tr>
							  <td colspan=4 align=center class=category>
								<a id="submitbtn"  class="easyui-linkbutton"   iconCls="icon-ok" href="#" onclick="ordersave()">提交</a>
								<a id="closebtn"  class="easyui-linkbutton" iconCls="icon-cancel" href="#" onclick="parent.closemodalwindow()">关闭</a>
							  </td>
							</tr>
						
							</TBODY>
						</TABLE>
					</TD>
				</TR>
   </TBODY>
</TABLE>
</form>
</body>
</html>