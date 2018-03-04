<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
		<script type="text/javascript">
			function showDetail(oid){
				$.post("${pageContext.request.contextPath}/adminOrder", {"method":"getDetailByOid","oid":oid}, function(data){
					var str = "<table border='1' align='center'><tr><th>商品名称</th><th>商品数量</th><th>金额</th></tr>";
					var total=0;
					$(data).each(function() {
						str += ("<tr><td>"+this.product.pname+"</td><td>"+this.count+"</td><td>"+this.subtotal+"</td></tr>");
						total += this.subtotal;
					})
					str += "<tr><td></td><td></td><td>"+total+"</td></tr></table>";
					
					layer.open({
						type:1,
						title:"订单详情",
						area:['500px', '350px'],
						shadeClose:true,
						content:str
					});
				}, "json");
			}
		</script>
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table style="width: 100%;text-align: center;" >
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>订单列表</strong>
						</TD>
					</tr>
					
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table  border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="15%">
										序号
									</td>
									<td align="center" width="20%">
										订单编号
									</td>
									<td align="center" width="15%">
										订单金额
									</td>
									<td align="center" width="15%">
										收货人
									</td>
									<td align="center" width="15%">
										订单状态
									</td>
									<td align="center" width="20%">
										订单详情
									</td>
								</tr>
									<c:forEach items="${list }" var="o" varStatus="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="15%">
												${status.count }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="20%">
												${o.oid }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="15%">
												${o.total }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="15%">
												${o.name }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="15%">
												<c:if test="${o.state==0 }">
													待付款
												</c:if>
												<c:if test="${o.state==1 }">
													<a href="${ pageContext.request.contextPath }/adminOrder?method=updateState&oid=${o.oid}&state=2">
														<font color="blue">发货</font>
													</a>
												</c:if>
												<c:if test="${o.state==2 }">
													等待确认收货
												</c:if>
												<c:if test="${o.state==3 }">
													订单完成
												</c:if>
											
											</td>
											<td align="center" style="HEIGHT: 22px">
												<input type="button" value="订单详情" onclick="showDetail('${o.oid}')"/>
											</td>
							
										</tr>
									</c:forEach>
							</table>
						</td>
					</tr>
					<tr align="center">
						<td colspan="7">
							第/页 
							<c:if test="pageBean.page != 1">
								<a href="${ pageContext.request.contextPath }/adminOrder_findAll.action?page=1">首页</a>|
								<a href="${ pageContext.request.contextPath }/adminOrder_findAll.action?page=<s:property value="pageBean.page-1"/>">上一页</a>|
							</c:if>
							<c:if test="pageBean.page != pageBean.totalPage">
								<a href="${ pageContext.request.contextPath }/adminOrder_findAll.action?page=<s:property value="pageBean.page+1"/>">下一页</a>|
								<a href="${ pageContext.request.contextPath }/adminOrder_findAll.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>|
							</c:if>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

