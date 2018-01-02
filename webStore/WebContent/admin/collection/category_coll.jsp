<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function showDetail(cid) {
				location.href="${pageContext.request.contextPath}/adminProduct_getCollection.action?category.cid="+cid;
			}
		</script>
	</HEAD>
	<body>
		<br>
			<table style="width: 100%;text-align: center;">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>分类汇总</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<form name="formId" id="formId" action="${pageContext.request.contextPath}/adminCategory_getCollection.action" method="post">
							<table style="margin-bottom: 5px;BORDER-RIGHT: gray 0px solid; BORDER-TOP: gray 0px solid; BORDER-LEFT: gray 0px solid; WIDTH: 50%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 0px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr>
									<td class="ta_01">
										<select name="rank" style="float: right;" onchange="document.formId.submit();">
											<option value="1" <c:if test="${rank == 1}">selected</c:if>>按销售量</option>
											<option value="2" <c:if test="${rank == 2}">selected</c:if>>按销售额</option>
										</select>
									</td>
								</tr>
							</table>
							</form>
							<table border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 50%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
									<td width="10%" align="center">序号</td>
									<td width="20%" align="center">分类名称</td>
									<td width="25%" align="center">销售量</td>
									<td width="25%" align="center">销售额</td>
									<td width="" align="center">操作</td>
								</tr>
								<c:forEach var="c" items="${ list }" varStatus="i">
									<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';"
									 <c:if test="${i.count==1 or i.count==2 or i.count==3 }">style="color: #ee7a2f;"</c:if>>
										<td style="CURSOR: hand; HEIGHT: 27px" align="center">${i.count }</td>
										<td style="CURSOR: hand; HEIGHT: 27px" align="center">${c.cname }</td>
										<td style="CURSOR: hand; HEIGHT: 27px" align="center">${c.count}</td>
										<td style="CURSOR: hand; HEIGHT: 27px" align="center">${c.total }</td>
										<td style="CURSOR: hand; HEIGHT: 27px" align="center">
											<input type="button" value="查看详情" onclick="showDetail('${c.cid}')"/>
										</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
	</body>
</HTML>

