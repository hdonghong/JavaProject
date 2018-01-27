<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>

<html> 
<head>
<title>流程定义部署</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">



<script type="text/javascript">

</script>
</head>
<body>
<form id="deployProcess" name="deployProcess" action="${pageContext.request.contextPath}/flow/flowAction_deployprocesssubmit.action" method="post" enctype="multipart/form-data" >
<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" bgColor=#c4d8ed>
		<TBODY>
			<TR>
				<TD background=images/r_0.gif width="100%">
					<TABLE cellSpacing=0 cellPadding=0 width="100%">
						<TBODY>
							<TR>
								<TD>&nbsp;流程定义部署</TD>
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
								<TD height=30 align=right>流程定义部署说明：</TD>
								<TD >
								请在下边分别选择流程定义bpmn文件和png文件
								</TD>
							</TR>
							<TR>
								<TD height=30 align=right>选择流程定义bpmn文件</TD>
								<TD class=category>
								<input type="file" name="resourcebpmn" />					
								</TD>
							</TR>
							<TR>
								<TD height=30 align=right>选择流程定义png文件</TD>
								<TD class=category>
								<input type="file" name="resourcepng" />					
								</TD>
							</TR>
							<TR>
								
								<TD colspan=2  align=center class=category>
									<input type="submit" value="确认部署"/> 
								</TD>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
</form>
</body>

</html>

