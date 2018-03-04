<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />

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

	<body>

		<!--
			菜单栏 导航条
			动态包含 
		-->
		<jsp:include page="/jsp/head.jsp"></jsp:include>

		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong>我的订单</strong>
					<table class="table table-bordered">
						<c:forEach items="${pageBean.list }" var="order">
							<tbody>
								<tr class="success">
									<th colspan="5">
										订单编号:${order.oid }&nbsp;&nbsp;
										订单金额:${order.total }&nbsp;&nbsp;
										<c:if test="${order.state == 0 }">
											<a href="${pageContext.request.contextPath }/order?method=getById&oid=${order.oid}">付款</a>
										</c:if>
										<c:if test="${order.state == 1 }">
											等待发货
										</c:if>
										<c:if test="${order.state == 2 }">
											<a href="${pageContext.request.contextPath }/order?method=updateState&oid=${order.oid}&state=3">确认收货</a>
										</c:if>
										<c:if test="${order.state == 3 }">
											已完成
										</c:if>
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
											<input type="hidden" name="id" value="22">
											<img src="${pageContext.request.contextPath}/${item.product.pimage}" width="70" height="60">
										</td>
										<td width="30%">
											<a target="_blank"> ${item.product.pname}</a>
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
				<ul class="pagination">
					<!-- 上一页展示 -->
					<c:if test="${pageBean.currPage==1 }">
						<li class="disabled"><a aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					</c:if>
					<c:if test="${pageBean.currPage!=1 }">
						<li><a href="${pageContext.request.contextPath }/order?method=findAllByPage&currPage=${pageBean.currPage-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					</c:if>
					
					<!-- 分页展示 -->
					<c:forEach begin="${pageBean.currPage-5>1? pageBean.currPage-5:1}" end="${pageBean.currPage+4>pageBean.totalPage? pageBean.totalPage:pageBean.currPage+4}" var="i">
						<c:if test="${pageBean.currPage==i }">
							<li class="active"><a>${i }</a></li>
						</c:if>
						<c:if test="${pageBean.currPage!=i }"><li>
							<a href="${pageContext.request.contextPath }/order?method=findAllByPage&currPage=${i}">${i }</a>
						</li></c:if>
					</c:forEach>
					
					<!-- 下一页展示 -->
					<c:if test="${pageBean.currPage==pageBean.totalPage }">
						<li class="disabled"><a aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
					</c:if>
					<c:if test="${pageBean.currPage!=pageBean.totalPage }">
						<li><a href="${pageContext.request.contextPath }/order?method=findAllByPage&currPage=${pageBean.currPage+1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
					</c:if>
				</ul>
			</div>
		</div>

		<div style="margin-top:50px;">
			<img src="${pageContext.request.contextPath}/image/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a>关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2017-? web商城 版权所有
		</div>
	</body>

</html>