<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引用jquery easy ui的js库及css -->
<LINK rel="stylesheet" type="text/css"
	href="${baseurl}js/easyui/styles/default.css">

<title>当前运行采购流程</title>
<script type="text/javascript">
	function queryorder() {
		$("#queryOrderForm").submit();

	}


</script>

</head>
<body>

	<form id="queryOrderForm" name="queryOrderForm"
		action="${baseurl}/orderflow/queryActivityOrder.action" method="post">

		<!-- <TABLE class="table_search">
			<TBODY>
				<TR>
					<TD class="left">采购单号：</td>
					<td><input type="text" name="orderCustom.name" /></TD>
					<TD class="left">采购单名称：</TD>
					<td><INPUT type="text" name="orderCustom.name" /></TD>

					<td><a id="btn" href="#" onclick="queryorder()"
						class="easyui-linkbutton" iconCls='icon-search'>查询</a></td>
				</TR>

			</TBODY>
		</TABLE> -->

		<TABLE class="toptable grid">
			<TBODY>
				<tr>
					<td>流程实例id</td>
					<td>采购单名称</td>
					<td>采购金额</td>
					<td>创建时间</td>
					<td>当前活动</td>
					<td>查看任务</td>
					<td>查看流程图</td> 
				</tr>
				<c:forEach items="${list}" var="order">
					<tr>
						<td class=category>${order.processinstanceId}</td>
						<td class=category>${order.name}</td>
						<td class=category>${order.price}</td>
						<td class=category><fmt:formatDate
								value="${order.createtime}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
						<td class=category>${order.activityId}</td>
						<td class=category><a
							href="${baseurl}orderflow/queryOrderTaskByPid.action?pid=${order.processinstanceId}"
							target="_blank">查看任务</a></td>
						<td class=category><a
							href="${baseurl }flow/queryActivityMap.action?processInstanceId=${order.processinstanceId}"
							target="_blank">查看流程图</a></td>
					</tr>
				</c:forEach>
			</TBODY>
		</TABLE>

	</form>

</body>
</html>