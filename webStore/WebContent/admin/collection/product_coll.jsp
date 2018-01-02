<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			// 页面加载时查询总分类
			$(function(){
				// ajax发送请求
				$.get("${pageContext.request.contextPath}/category_findAll.action", function(data){
					
					// 遍历数组
					$(data).each(function(){
						// 从值栈中获取分类的cid
						var vsCid = "${model.category.cid}";
						if (vsCid == this.cid) {// 如果相同的选中
							$("#category_cid").append("<option value='"+ this.cid +"' selected>"+ this.cname +"</option>");
						} else {
							$("#category_cid").append("<option value='"+ this.cid +"'>"+ this.cname +"</option>");
						}
					});
					
				}, "json");
			});
		</script>
	</HEAD>
	<body>
		<br>
			<table style="width: 100%;text-align: center;">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>商品汇总</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<form name="formId" id="formId" action="${pageContext.request.contextPath}/adminProduct_getCollection.action" method="post">
							<table style="margin-bottom: 5px;BORDER-RIGHT: gray 0px solid; BORDER-TOP: gray 0px solid; BORDER-LEFT: gray 0px solid; WIDTH: 70%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 0px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr>
									<td class="ta_01" style="float: right;">
										<label for="category_cid">商品类型: </label><select name="category.cid" id="category_cid"><option value="">所有商品</option></select>&nbsp;&nbsp;
										<select name="rank">
											<option value="1" <c:if test="${rank == 1}">selected</c:if>>按销售量</option>
											<option value="2" <c:if test="${rank == 2}">selected</c:if>>按销售额</option>
										</select>&nbsp;&nbsp;
										<label for="pageSizeId">数据量：</label><input id="pageSizeId" name="size" type="text" value="${page.pageSize }" size="1px"/>&nbsp;&nbsp;
										<input type="submit" value="查询"/>
									</td>
								</tr>
							</table>
							</form>
							<table border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 70%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
									<td width="10%" align="center">序号</td>
									<td width="" align="center">商品名称</td>
									<td width="15%" align="center">销售量</td>
									<td width="25%" align="center">销售额</td>
								</tr>
								<c:forEach var="c" items="${ page.beanList }" varStatus="i">
									<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';"
									 <c:if test="${i.count==1 or i.count==2 or i.count==3 or i.count==4 or i.count==5 }">style="color: #ee7a2f;"</c:if>>
										<td style="CURSOR: hand; HEIGHT: 27px" align="center">${i.count }</td>
										<td style="CURSOR: hand; HEIGHT: 27px" align="center">${c.pname }</td>
										<td style="CURSOR: hand; HEIGHT: 27px" align="center">${c.pcount}</td>
										<td style="CURSOR: hand; HEIGHT: 27px" align="center">${c.total }</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
	</body>
</HTML>

