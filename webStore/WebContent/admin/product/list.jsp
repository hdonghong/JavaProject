<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/layer/layer.js" ></script>
		<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/admin/product/add.jsp";
			}
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
			// 提交表单
			function to_page(page){
				if(page){
					document.getElementById("currPage").value = page;
				}
				document.productForm.submit();
			}
			// 查看商品评论
			function showComments(pid, title) {
				title = "\"" + title + "\"的用户评价";
 				$.post("${pageContext.request.contextPath}/adminProduct_showComments",
						{"pid":pid},
						function(data){
							var str="<table id='table-1' rules='none'><thead><tr><th>用户</th><th>评价</th></tr></thead>";
							var originalLength = str.length;
							$(data).each(function() {
								if (this.order && this.order.state == 4) {
									str += ("<tr><td width='30%'>"+this.order.user.username+"</td><td>"+this.comment+"</td></tr>");
								}
							});
							// 字符串长度没有变化说明循环遍历，此时没有用户评价
						 	if (str.length == originalLength) {
								str="<table id='table-1' rules='none'><thead><tr><th style='text-align:center'>暂无用户评价</th></tr></thead>";
							}
							str += ("</table>");
							layer.open({
								type:1,
								title:title,
								offset: '10%',
								area:['500px', '350px'],
								shadeClose:true,
								content:str
								
							});
						},
						"json");
			}
			// 下架商品前二次确认
			function deleteProduct(pid){
				if (confirm("你确定要下架该商品吗？")) {
					location.href="${pageContext.request.contextPath}/adminProduct_delete?pid="+pid;
				}
			}
		</script>
		<style>
			#table-1 thead {
				border-width: 2px;
				border-style: solid;
				border-color: #dddddd;
/* 				border-top-width: 2px;
				border-top-style: solid;
				border-top-color: #dddddd; */
			}
			#table-1 {
				width:100%;
/* 				border-bottom-width: 1px;
				border-bottom-style: solid;
				border-bottom-color: #dddddd; */
			}
		
			/* Padding and font style */
			#table-1 td, #table-1 th {
				text-align:left;
				padding: 5px 10px;
				font-size: 12px;
				font-family: Verdana;
				color: #000;
				cursor: default;
			}
			#table-1 th{
				font-size: 15px;
			}
		
			/* Alternating background colors */
			#table-1 tr:nth-child(even) {
				background: #f9f9f9
			}
			#table-1 tr:nth-child(odd) {
				background: #ffffff
			}
		</style>
	</HEAD>
	<body>
		<br>
		<form id="productForm" name="productForm" action="${pageContext.request.contextPath}/adminProduct_findAll.action" method="post">
			<table style="width: 100%;text-align: center;">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>商品列表</strong>
						</td>
					</tr>
					<tr>
						<td class="ta_01" >
							<input type="hidden" name="currPage" id="currPage" />
							<label for="category_cid">商品类型: </label><select name="category.cid" id="category_cid"><option value="">-请选择-</option></select>&nbsp;&nbsp;
							<label for="pdesc">关键词: </label> <input name="pdesc" id="pdesc" value="${model.pdesc }"></input>&nbsp;&nbsp;
							<label for="begin">商品上架时间起: </label><input type="date"  id="begin" name="begin" value="<fmt:formatDate value='${begin }' pattern='yyyy-MM-dd' />"/>&nbsp;&nbsp;
							<label for="end">至: </label><input type="date" name="end" id="end" value="<fmt:formatDate value='${end }' pattern='yyyy-MM-dd' />"/>&nbsp;&nbsp;
							<input type="submit" value="查询" ></input>
							<span style="float: right;">
							<label>商品数量：${page.totalCount }&nbsp;&nbsp;</label>
							<button type="button" id="add" name="add" value="添加" class="button_add" onclick="addProduct()">&#28155;&#21152;</button>
							</span>
						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="5%">
										序号
									</td>
									<td align="center" width="10%">
										商品图片
									</td>
									<td align="center" width="17%">
										商品名称
									</td>
									<td align="center" width="10%">
										商品价格
									</td>
									<td align="center" width="10%">
										商品销量
									</td>
									<td align="center" width="17%">
										上架时间
									</td>
									<td align="center" width="*">
										商品描述
									</td>
									<td width="3%" align="center">
										评价
									</td>
									<td width="3%" align="center">
										编辑
									</td>
									<td width="3%" align="center">
										删除
									</td>
								</tr>
								<c:forEach items="${page.beanList }" var="p" varStatus="vs">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												${vs.count+(page.currPage-1)*page.pageSize }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												<img width="40" height="45" src="${ pageContext.request.contextPath }/${p.pimage}">
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												${p.pname }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												${p.shop_price }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												${p.pcount }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												<!-- 通过jsp:userBean标签引入java.util.Date日期类 -->
												<jsp:useBean id="dateValue" class="java.util.Date"/> 
												<!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->
												<jsp:setProperty name="dateValue" property="time" value="${p.create_at }"/> 
												<!-- 转换格式 -->
												<fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm:ss"/> 
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center" title="${p.pdesc }">
												${fn:substring(p.pdesc,0,20) }...
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="javascript:showComments('${p.pid}', '${p.pname }')" title="查看该商品的评价">
													<img src="${pageContext.request.contextPath}/images/i_search.png" border="0" style="CURSOR: hand">
												</a>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${ pageContext.request.contextPath }/adminProduct_getById.action?pid=${p.pid}" title="修改该商品信息">
													<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<a href="javascript:deleteProduct('${p.pid }')" title="下架该商品">
													<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
											</td>
										</tr>
									</c:forEach>
							</table>
						</td>
					</tr>
					<tr align="center">
						<td colspan="10">
							<c:if test="${page.totalPage > 0 }">
								 第${page.currPage }/${page.totalPage }页<br/>
								<c:if test="${page.currPage == 1 }">
									<a href="javascript:void(0)" >首页</a>
									<a href="javascript:void(0)" >上一页</a>
								</c:if>
								<c:if test="${page.currPage != 1 }">
									<a href="javascript:void(0)" onclick="to_page(1)" >首页</a>
									<a href="javascript:void(0)" onclick="to_page('${page.currPage-1 }')" >上一页</a>
								</c:if>
								<c:if test="${page.currPage == page.totalPage }">
									<a href="javascript:void(0)" >下一页</a>
									<a href="javascript:void(0)" >尾页</a>
								</c:if>
								<c:if test="${page.currPage != page.totalPage }">
									<a href="javascript:to_page('${page.currPage+1}')" >下一页</a>
									<a href="javascript:void(0)" onclick="to_page('${page.totalPage }')" >尾页</a>
								</c:if>
							</c:if>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

