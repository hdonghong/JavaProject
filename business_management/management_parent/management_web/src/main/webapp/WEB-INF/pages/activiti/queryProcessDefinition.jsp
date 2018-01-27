<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ include file="/WEB-INF/jsp/tag.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引用jquery easy ui的js库及css -->
<LINK rel="stylesheet" type="text/css" href="${baseurl}js/easyui/styles/default.css">
<%-- <%@ include file="/WEB-INF/jsp/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/common_js.jsp"%> --%>
<title>流程定义列表</title>
<script type="text/javascript">
    
    function deleteDeployment(id){
    	if(window.confirm("您确认删除该流程吗？")){
    		window.location="${pageContext.request.contextPath}/flow/flowAction_deleteDeployment.action?deploymentId="+id;
    	}
    }
    
    
</script>

</head>
<body>

	<form id="queryProcessDefinition" name="queryProcessDefinition"
		method="post">
		
		<TABLE class="toptable grid" >
			<TBODY>
				<tr>
					<td>流程部署id</td>
					<td>流程定义id</td>
					<td>流程定义名称</td>
					<td>流程定义key</td>
					<td>流程定义版本</td>
					<td>bpmn</td>
					<td>图片</td>
					<td>删除流程</td>
				</tr>
				<c:forEach items="${list }" var="processDefinition">
					<tr>   
						<td class=category>${processDefinition.deploymentId}</td>
						<td class=category>${processDefinition.id}</td>
						<td class=category>${processDefinition.name}</td>
						<td class=category>${processDefinition.key}</td>
						<td class=category>${processDefinition.version}</td>
						<td class=category><a href="${pageContext.request.contextPath}/flow/flowAction_findResource.action?processDefinitionId=${processDefinition.id}&resourceType=bpmn" target="_blank">查看bpmn</a></td>
						<td class=category><a href="${pageContext.request.contextPath}/flow/flowAction_findResource.action?processDefinitionId=${processDefinition.id}&resourceType=png" target="_blank">查看图片</a></td>
						<td class=category><a href=javascript:deleteDeployment('${processDefinition.deploymentId}') >删除流程</a></td>
					</tr>
				</c:forEach>
			</TBODY>
		</TABLE>
	</form>

</body>
</html>