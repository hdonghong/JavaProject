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
		<title>我的订单</title>
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

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<label style="font-size: 15px;">我的订单</label>
					<table class="table table-bordered">
						<c:forEach items="${pageBean.beanList }" var="order">
							<tbody>
								<tr class="success" >
									<th colspan="5">
										订单编号:${order.oid }&nbsp;&nbsp;
										订单金额:${order.total }&nbsp;&nbsp;
										<c:if test="${order.state == 0 }">
											<a href="${pageContext.request.contextPath }/order_getById.action?oid=${order.oid}">付款</a>
											&nbsp;&nbsp;
											<a href="${pageContext.request.contextPath }/order_cancelOrder.action?oid=${order.oid}">取消订单</a>
										</c:if>
										<c:if test="${order.state == 1 }">
											<font style="color: #ee7a2f;">待发货</font>
										</c:if>
										<c:if test="${order.state == 2 }">
											<a href="${pageContext.request.contextPath }/order_updateState.action?oid=${order.oid}&state=3">确认收货</a>
										</c:if>
										<c:if test="${order.state == 3 }">
											<a href="${pageContext.request.contextPath }/order_comment.action?oid=${order.oid}">评价</a>
										</c:if>
										<c:if test="${order.state == 4 }">
											已完成
										</c:if>
										
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
								<c:forEach items="${order.orderItems }" var="item">
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
								</c:forEach>
							</tbody>
						</c:forEach>
					</table>
				</div>
			</div>
			<div style="text-align: center;">
				<c:if test="${empty pageBean }">您暂时没买过任何东西哦，快去购物吧。</c:if>
				<c:if test="${not empty pageBean }">
				<ul class="pagination">
					<!-- 上一页展示 -->
					<c:if test="${pageBean.currPage==1 }">
						<li class="disabled"><a aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					</c:if>
					<c:if test="${pageBean.currPage!=1 }">
						<li><a href="${pageContext.request.contextPath }/order_findByPage?currPage=${pageBean.currPage-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					</c:if>
					
					<!-- 分页展示 -->
					<c:forEach begin="${pageBean.currPage-5>1? pageBean.currPage-5:1}" end="${pageBean.currPage+4>pageBean.totalPage? pageBean.totalPage:pageBean.currPage+4}" var="i">
						<c:if test="${pageBean.currPage==i }">
							<li class="active"><a>${i }</a></li>
						</c:if>
						<c:if test="${pageBean.currPage!=i }"><li>
							<a href="${pageContext.request.contextPath }/order_findByPage?currPage=${i}">${i }</a>
						</li></c:if>
					</c:forEach>
					
					<!-- 下一页展示 -->
					<c:if test="${pageBean.currPage==pageBean.totalPage }">
						<li class="disabled"><a aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
					</c:if>
					<c:if test="${pageBean.currPage!=pageBean.totalPage }">
						<li><a href="${pageContext.request.contextPath }/order_findByPage?currPage=${pageBean.currPage+1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
					</c:if>
				</ul>
				</c:if>
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