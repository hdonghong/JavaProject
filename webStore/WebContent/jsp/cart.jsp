<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>我的购物车</title>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
		<link rel="shortcut icon"  type="images/x-icon"  href="${pageContext.request.contextPath}/images/icon.ico">
		<link rel="Bookmark"  type="images/x-icon" href="${pageContext.request.contextPath}/images/icon.ico">
		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
			
			.container .row div {
				/* position:relative;
	 float:left; */
			}
			
			#cartPageId font {
				color: #3164af;
				font-size: 18px;
				font-weight: normal;
				padding: 0 10px;
			}
		</style>
	</head>

	<body>

		<!--
			菜单栏 导航条
			动态包含 
		-->
		<jsp:include page="/jsp/head.jsp"></jsp:include>


		<div class="container" id="cartPageId">
			<c:if test="${empty cart.cartItems }">
				<div style="width: 80%;height: 200px;margin-top: 20px;">
					<font style="font-size: 30px;font-weight: 800;color: #000000;">购物车空空的，快去逛逛吧~~</font>
				</div>
			</c:if>
			<c:if test="${not empty cart.cartItems }">
				<div class="row">
					<div style="margin:0 auto; margin-top:10px;width:950px;">
						<strong style="font-size:16px;margin:15px 5px;">我的购物车</strong>
						<table class="table table-bordered">
							<tbody>
								<tr class="warning">
									<th>图片</th>
									<th>商品</th>
									<th>价格</th>
									<th>数量</th>
									<th>小计</th>
									<th>操作</th>
								</tr>
								<c:forEach items="${cart.cartItems}" var="item">
									<tr class="active">
										<td width="60" width="40%">
											<input type="hidden" name="id" value="22">
											<img src="${pageContext.request.contextPath}/${item.product.pimage}" width="70" height="60">
										</td>
										<td width="30%">
											<a target="_blank">${item.product.pname }</a>
										</td>
										<td width="20%">
											￥${item.product.shop_price }
										</td>
										<td width="10%">
											<input type="text" name="quantity" value="${item.count }" maxlength="4" size="10" readonly />
										</td>
										<td width="15%">
											<span class="subtotal">￥${item.subtotal }</span>
										</td>
										<td>
											<a href="javascript:void(0)" class="delete" onclick="removeFromCart(${item.product.pid})">删除</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				
				<div style="margin-right:130px;">
					<div style="text-align:right;">
						<em style="color:#ff6600;">
					<c:if test="${empty user }">登录后确认是否享有优惠</c:if>&nbsp;&nbsp;
				</em> 赠送积分: <em style="color:#ff6600;">${cart.total }</em>&nbsp; 商品金额: <strong style="color:#ff6600;">￥${cart.total }元</strong>
					</div>
					<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
						<a href="javascript:void(0)" id="clear" class="clear" onclick="clearCart()">清空购物车</a>
						<a href="${pageContext.request.contextPath }/order_add.action">
							<input type="submit" width="100" value="提交订单" name="submit" 
							style="background: url('${pageContext.request.contextPath}/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
							height:35px;width:100px;color:white;">
						</a>
					</div>
				</div>
			</c:if>
		</div>

			<!--
            	作者：22311
            	时间：2017年11月9日07:05:08
            	描述：页脚部分
            -->
			<div>
				<%@ include file="foot.jsp" %>
			</div>

	</body>
	<script type="text/javascript">
		function removeFromCart(pid) {
			if (confirm("你确定要丢弃我吗？")) {
				location.href="${pageContext.request.contextPath}/cart_remove?pid="+pid;
			}
		}
		function clearCart() {
			if (confirm("你狠心抛弃我们吗？")) {
				location.href="${pageContext.request.contextPath }/cart_clear.action";
			}
		}
	</script>
</html>