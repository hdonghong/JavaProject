<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引用jquery easy ui的js库及css -->
<LINK rel="stylesheet" type="text/css" href="${baseurl}js/easyui/styles/default.css">

<title>采购单审核</title>

<script type="text/javascript">
function orderAudit(){
	document.oorderAuditForm.submit();
	
}
</script>
</head>
<body>

<form id="oorderAuditForm" name="oorderAuditForm" action="${ctx}/flow/orderFlowAction_submitOrderAudit.action" method="post">

	<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" bgColor=#c4d8ed>

   <TBODY>
   <TR>
				<TD background=images/r_0.gif width="100%">
					<TABLE cellSpacing=0 cellPadding=0 width="100%">
						<TBODY>
							<TR>
								<TD>&nbsp;审核采购单</TD>
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
								<TD height=30 width="15%" align=right >任务id/采购单id：</TD>
								<TD class=category width="35%">
								任务id：<input type="text" name="taskId" value="${taskId}"/><br/>
								采购单id：<input type="text" name="orderId" value="${orderId}"/>
								</TD>
								
								<TD height=30 width="15%" align=right >审核类型：</TD>
								<TD class=category width="35%">
								<input type="text" name="auditType" value="${auditType}"/>
								</TD>
							</TR>
							<TR>
								<TD height=30 width="15%" align=right >审核结果：</TD>
								<TD class=category width="35%">
								<input type="radio" name="orderAuditCustom.status" value="1"/>通过
								<input type="radio" name="orderAuditCustom.status" value="0" />不通过
								</TD>
								<TD height=30 width="15%" align=right >审核意见</TD>
								<TD class=category width="35%">
								<textarea rows="3" cols="30" name="orderAuditCustom.auditInfo" ></textarea>
								</div>
								</TD>
								
								
							</TR>
							
							<tr>
							  <td colspan=4 align=center class=category>
								<a id="submitbtn"  class="easyui-linkbutton"   iconCls="icon-ok" href="#" onclick="orderAudit()">提交</a>
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