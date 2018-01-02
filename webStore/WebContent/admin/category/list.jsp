<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function addCategory(){
				window.location.href = "${pageContext.request.contextPath}/admin/category/add.jsp";
			}
		</script>
	</HEAD>
	<body>
		<br>
			<table style="width: 100%;text-align: center;">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>分类列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="right">
							<label>商品类型数量：${fn:length(list) }&nbsp;&nbsp; </label>
							<button type="button" id="add" name="add" value="添加" class="button_add" onclick="addCategory()">&#28155;&#21152;</button>
						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
									<td align="center" width="18%">序号</td>
									<td align="center" width="17%">分类名称</td>
									<td width="7%" align="center">编辑</td>
									<td width="7%" align="center">删除</td>
								</tr>
								<c:forEach var="c" items="${ list }" varStatus="i">
									<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<td style="CURSOR: hand; HEIGHT: 27px" align="center" width="18%">${i.count }</td>
										<td style="CURSOR: hand; HEIGHT: 27px" align="center" width="17%">${c.cname }</td>
										<td align="center" style="HEIGHT: 27px">
											<a href="${ pageContext.request.contextPath }/adminCategory_getById?cid=${c.cid}" title="修改该分类名称">
												<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
											</a>
										</td>
										<td align="center" style="HEIGHT: 27px">
											<a href="javascript:void(0)" onclick="deleteCategory('${c.cid}')" title="删除此项分类">
												<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
											</a>
										</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
	</body>
	<script type="text/javascript">
		function deleteCategory(cid){
			if (confirm("你确定要删除该分类吗？")) {
				location.href="${pageContext.request.contextPath}/adminCategory_delete?cid="+cid;
			}
		}
	</script>
</HTML>

