<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon"  type="images/x-icon"  href="${pageContext.request.contextPath}/images/icon.ico">
		<link rel="Bookmark"  type="images/x-icon" href="${pageContext.request.contextPath}/images/icon.ico">
		<title>订单详情</title>
		<!-- 引入自定义css文件 style.css -->
		<!--  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />-->
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
		
		<script type="text/javascript">
			function hideBank() {
				var bankId = document.getElementById("bankId");
				var forBank = document.getElementById("forBank");
				if (bankId.checked) {
					forBank.style.visibility = "visible";
				} else {
					forBank.style.visibility = "collapse";
				}
			}
			function checkForm() {
				if ($("#name").val().trim().length <= 0 || 
						$("#telephone").val().trim().length <= 0 || 
						$("#address").val().trim().length <= 0) {
					alert("请填写完整信息");
					return false;
				} else {
					$("#orderForm").submit();
				}
			}
			
		</script>
	</head>

	<body style="background-color:#f6f6f6;">

		<!--
			菜单栏 导航条
			动态包含 
		-->
		<jsp:include page="/jsp/head.jsp"></jsp:include>

		<div class="container">
			<div class="row">

				<div style="margin:0 auto;margin-top:10px;width:950px;">
					<strong>订单详情</strong>
					<table class="table table-bordered">
						<tbody>
							<tr class="warning">
								<th colspan="5">订单编号:${order.oid } </th>
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
										<a target="_blank"> ${item.product.pname }</a>
									</td>
									<td width="20%">
										￥${item.product.shop_price }
									</td>
									<td width="10%">
										<span>${item.count }</span>
									</td>
									<td width="15%">
										<span class="subtotal">￥${item.subtotal }</span>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div style="text-align:right;margin-right:120px;">
					商品金额: <strong style="color:#ff6600;">￥${order.total }元</strong>
				</div>

			</div>

			<div>
				<hr/>
				<form id="orderForm" class="form-horizontal" style="margin-top:5px;margin-left:150px;" action="${pageContext.request.contextPath }/order_pay" method="post">
					<input type="hidden" name="oid" value="${order.oid }"/>
					<input type="hidden" name="total" value="${order.total }"/>
					<div class="form-group">
						<label for="username" class="col-sm-1 control-label">地址</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="address" placeholder="请输入收货地址" name="address" required>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-1 control-label">收货人</label>
						<div class="col-sm-5">
							<input class="form-control" id="name" placeholder="请输收货人" name="name"  required>
						</div>
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-1 control-label">电话</label>
						<div class="col-sm-5">
							<input class="form-control" id="telephone" placeholder="请输入联系方式" name="telephone" required>
						</div>
					</div>

				<hr/>

					<div style="margin-top:5px;margin-left:150px;">
						<div onclick="hideBank()">
							<strong>支付方式：</strong>
							<input type="radio" name="payMethod" value="bank" id="bankId" checked />
							<button type="button" class="btn btn-default" style="margin-right:15px;" onclick="document.getElementById('bankId').checked='checked';" >网银支付</button>
							<input type="radio" name="payMethod" value="alipay" id="alipayId" />
							<button type="button" class="btn btn-default" style="margin-right:15px;" onclick="document.getElementById('alipayId').checked='checked';">支付宝</button>
							<input type="radio" name="payMethod" value="cod" id="codId" />
							<button type="button" class="btn btn-default" onclick="document.getElementById('codId').checked='checked';" >货到付款</button>
							<a href="javascript:checkForm();" style="margin-left: 230px;">
								<img src="${pageContext.request.contextPath}/images/finalbutton.gif" width="150" border="0" />
							</a>
						</div>
						<div id="forBank">
						<hr/>
						<strong>选择银行：</strong>
						<p>
							<br/>
							<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked" id="icbcId" />
							<label for="icbcId">工商银行<img src="${pageContext.request.contextPath}/bank_img/icbc.bmp" align="middle" /></label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="BOC-NET-B2C" id="bocId" />
							<label for="bocId">中国银行<img src="${pageContext.request.contextPath}/bank_img/bc.bmp" align="middle" /></label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="ABC-NET-B2C" id="abcId" />
							<label for="abcId">农业银行<img src="${pageContext.request.contextPath}/bank_img/abc.bmp" align="middle" /></label>
							<br/>
							<br/>
							<input type="radio" name="pd_FrpId" value="BOCO-NET-B2C" id="bocoId" />
							<label for="bocoId">交通银行<img src="${pageContext.request.contextPath}/bank_img/bcc.bmp" align="middle" /></label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="PINGANBANK-NET" id="pinganbankId" />
							<label for="pinganbankId">平安银行<img src="${pageContext.request.contextPath}/bank_img/pingan.bmp" align="middle" /></label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="CCB-NET-B2C" id="ccbId" />
							<label for="ccbId">建设银行<img src="${pageContext.request.contextPath}/bank_img/ccb.bmp" align="middle" /></label>
							<br/>
							<br/>
							<input type="radio" name="pd_FrpId" value="CEB-NET-B2C" id="cebId" />
							<label for="cebId">光大银行<img src="${pageContext.request.contextPath}/bank_img/guangda.bmp" align="middle" /></label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C" id="cmbchinaId" />
							<label for="cmbchinaId">招商银行<img src="${pageContext.request.contextPath}/bank_img/cmb.bmp" align="middle" /></label>
	
						</p>
	
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