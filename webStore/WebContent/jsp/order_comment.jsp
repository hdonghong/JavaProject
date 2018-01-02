<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon"  type="images/x-icon"  href="${pageContext.request.contextPath}/images/icon.ico">
		<link rel="Bookmark"  type="images/x-icon" href="${pageContext.request.contextPath}/images/icon.ico">
		<title>评价商品</title>
		<!-- 引入自定义css文件 style.css -->
		<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" /> --%>

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body style="background-color:#f6f6f6;">

		<!--
			菜单栏 导航条
			动态包含 
		-->
		<jsp:include page="/jsp/head.jsp"></jsp:include>

		<div class="container">
			<div class="row">
				<form action="${pageContext.request.contextPath }/order_addComment.action" method="post">
				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<label style="font-size: 15px;">评价商品</label>
					<table class="table table-bordered">
							<tbody>
								<tr class="success" >
									<th colspan="5">
										订单编号:${order.oid }&nbsp;&nbsp;
										订单金额:${order.total }&nbsp;&nbsp;
										
										<!-- 通过jsp:userBean标签引入java.util.Date日期类 -->
										<jsp:useBean id="dateValue" class="java.util.Date"/> 
										<!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->
										<jsp:setProperty name="dateValue" property="time" value="${order.create_at}"/> 
										<!-- 转换格式 -->
										<label style="float:right;margin:0;">
											<fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm:ss"/> 
										</label>
									</th>
								</tr>
								<tr class="warning">
									<th>图片</th>
									<th>商品</th>
									<th>价格</th>
									<th>数量</th>
									<th>小计</th>
								</tr>
								<c:forEach items="${order.orderItems }" var="item" varStatus="i">
									<tr class="active">
										<td width="60" width="40%">
											<img src="${pageContext.request.contextPath}/${item.product.pimage}" width="70" height="60">
										</td>
										<td width="30%">
											<a target="_blank" href="${pageContext.request.contextPath }/product_getById?pid=${item.product.pid}" > ${item.product.pname}</a>
										</td>
										<td width="20%">
											￥${item.product.shop_price}
										</td>
										<td width="10%">
											${item.count}
										</td>
										<td width="15%">
											<span class="subtotal">￥${item.subtotal }</span>
										</td>
									</tr>
									<tr>
										<td colspan="5" align="center">
											<input type="hidden" name="items[${i.index}].order.oid" value="${item.order.oid }" />
											<input type="hidden" name="items[${i.index}].itemid" value="${item.itemid }" />
											<textarea rows="2" cols="140" style="resize:none;" maxlength="255" placeholder="请输入你对该商品的评价" name="items[${i.index}].comment"></textarea>
										</td>
									</tr>
								</c:forEach>
							</tbody>
					</table>
					
					<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
						<input type="submit" width="100" value="提交评价" name="submit" 
						style="background: url('${pageContext.request.contextPath}/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
						height:35px;width:100px;color:white;">
					</div>
				</div>
				</form>
			</div>
		</div>

		<!-- 
			页脚
			时间：2017-12-12 08:28:10
		 -->
		<div>
			<%@ include file="foot.jsp" %>
		</div>
	</body>

</html>