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

<title>任务列表</title>
<script type="text/javascript">
	function queryOrderTask() {
		$("#queryOrderForm").submit();

	}
	

</script>

</head>
<body>

	<form id="queryOrderForm" name="queryOrderForm"
		action="${baseurl}/order/queryOrderTaskByPid.action"
		method="post">
		<input type="hidden" id="page_index" name="page_index" value="1"/>
		<input type="hidden" id="page_size" name="page_size" value="15"> 
		
		<TABLE class="toptable grid" >
			<TBODY>
				<tr>
					<td>任务id</td>
					<td>任务名称</td>
					<td>负责人</td>
					<td>任务标识</td>
					<td>开始时间</td>
					<td>结束时间</td>
					
				</tr>
				<c:forEach items="${list}" var="order">
					<tr>
						<td class=category>${order.taskId}</td>
						<td class=category>${order.taskName }</td>
						<td class=category>${order.assignee}</td>
						<td class=category>${order.taskDefinitionKey}</td>
						<td class=category><fmt:formatDate value="${order.startTime}"
								pattern="yyyy-MM-dd hh:mm:ss" /></td>
						<td class=category><fmt:formatDate value="${order.endTime}"
								pattern="yyyy-MM-dd hh:mm:ss" /></td>
					</tr>
				</c:forEach>
			</TBODY>
		</TABLE>

	</form>

</body>
</html>