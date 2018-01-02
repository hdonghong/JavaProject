<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
		<script type="text/javascript">
			// 查看订单详情
			function showDetail(oid){
				$.post("${pageContext.request.contextPath}/adminOrder_showOrderItems.action", 
						{"oid":oid}, function(data){
					var str = "<table id='table-1' rules='none'><thead><tr><th>商品名称</th><th>数量</th><th>金额</th></tr></thead>";
					var total=0;
					$(data).each(function() {
						str += ("<tr><td width='60%'>"+this.product.pname+"</td><td width='20%'>"+this.count+"</td><td>"+this.subtotal+"</td></tr>");
						total += this.subtotal;
					})
					str += "<tr><td></td><td></td><td>"+total+"</td></tr></table>";
					
					layer.open({
						type:1,
						title:"订单详情",
						offset:'10%',
						area:['500px', '350px'],
						shadeClose:true,
						content:str
					});
				}, "json");
			}
			// 取消订单前二次确认
			function deleteOrder(oid){
				if (confirm("你确定要拒绝此订单吗？")) {
					location.href="${pageContext.request.contextPath}/adminOrder_delete?oid="+oid;
				}
			}
			// 提交表单
			function to_page(page){
				if(page){
					document.getElementById("currPage").value = page;
				}
				document.orderForm.submit();
			}
		</script>
		<style>
			#table-1 thead {
				border-width: 2px;
				border-style: solid;
				border-color: #dddddd;
			}
			#table-1 {
				width:100%;
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
			.enlarge:hover{
				font-weight: 800;
				font-size: 13px;
			}
		</style>
	</HEAD>
	<body>
		<br>
		<form id="orderForm" name="orderForm" action="${pageContext.request.contextPath}/adminOrder_findAll.action" method="post">
			<table style="width: 100%;text-align: center;" >
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>订单列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" >
							<input type="hidden" name="currPage" id="currPage" />
							<label for="state">订单状态: </label>
							<select name="state" id="state">
								<option value="">-请选择-</option>
								<option value="0" <c:if test="${model.state==0 }">selected</c:if> >待付款</option>
								<option value="1" <c:if test="${model.state==1 }">selected</c:if> >待发货</option>
								<option value="2" <c:if test="${model.state==2 }">selected</c:if> >待收货</option>
								<option value="3" <c:if test="${model.state==3 }">selected</c:if> >待评价</option>
								<option value="4" <c:if test="${model.state==4 }">selected</c:if> >已完成</option>
							</select>&nbsp;&nbsp;
							<label for="user_username">买方: </label> <input name="user.username" id="user_username" value="${model.user.username }"></input>&nbsp;&nbsp;
							<label for="begin">下单时间起: </label><input type="date"  id="begin" name="begin" value="<fmt:formatDate value='${begin }' pattern='yyyy-MM-dd' />"/>&nbsp;&nbsp;
							<label for="end">至: </label><input type="date" name="end" id="end" value="<fmt:formatDate value='${end }' pattern='yyyy-MM-dd' />"/>&nbsp;&nbsp;
							<input type="submit" value="查询" ></input>
							<label style="float: right;">订单数量：${page.totalCount }&nbsp;&nbsp;</label>
						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table  border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
									<td align="center" width="5%">序号</td>
									<td align="center" width="10%">买方</td>
									<td align="center" width="10%">收货人</td>
									<td align="center" width="10%">联系方式</td>
									<td align="center" width="20%">收货地址</td>
									<td align="center" width="10%">订单金额</td>
									<td align="center" width="5%">订单状态</td>
									<td align="center" width="15%">下单时间</td>
									<td align="center" width="7%">订单详情</td>
									<td align="center" width="3%">操作</td>
								</tr>
									<c:forEach items="${page.beanList }" var="o" varStatus="status">
										<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">${status.count+(page.currPage-1)*page.pageSize }</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">${o.user.username }</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">${o.name }</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">${o.telephone }</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center" title="${o.address }">${fn:substring(o.address,0,20) }...</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">${o.total }</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												<c:if test="${o.state==0 }">
													待付款
												</c:if>
												<c:if test="${o.state==1 }">
													<a  class="enlarge" title="单击为此订单发货" style="color: #ee7a2f;"
													    href="${ pageContext.request.contextPath }/adminOrder_updateState.action?oid=${o.oid}&state=${o.state+1}">
														待发货
													</a>
												</c:if>
												<c:if test="${o.state==2 }">
													待收货
												</c:if>
												<c:if test="${o.state==3 }">
													待评价
												</c:if>
												<c:if test="${o.state==4 }">
													已完成
												</c:if>
											
											</td>
											<td align="center" style="CURSOR: hand; HEIGHT: 22px">
												<!-- 通过jsp:userBean标签引入java.util.Date日期类 -->
												<jsp:useBean id="dateValue" class="java.util.Date"/> 
												<!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->
												<jsp:setProperty name="dateValue" property="time" value="${o.create_at }"/> 
												<!-- 转换格式 -->
												<fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<input type="button" value="订单详情" onclick="showDetail('${o.oid}')"/>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="javascript:deleteOrder('${o.oid }')" title="拒绝订单">
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

