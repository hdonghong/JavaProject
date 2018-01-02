<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>商品详情</title>
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
			.forCounter{}
		</style>
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/layer/layer.js" type="text/javascript" ></script>
		<script type="text/javascript">
			function te() { // 加载“暂无商品评论信息”
				if ($(".forCounter").size() <= 0) {
					$("#noCommentId").html("<th>暂无商品评论信息</th>");
				}
			}
			
			function seeBig(pimage) {
				var str = "<img src='${pageContext.request.contextPath}/"+ pimage +"' title='大图' style='cursor:pointer;width: 450px;margin-left: 23px;' />"
				layer.open({
					type:1,
					title:"",
					area:['500px', '450px'],
					shadeClose:true,
					content:str
					
				});
			}
		</script>
	</head>

	<body onload="te()"  style="background-color:#f6f6f6;">
	
		<!--
			菜单栏 导航条
			动态包含 
		-->
		<jsp:include page="/jsp/head.jsp"></jsp:include>

		<div class="container">
			<div class="row">
				<%-- <div style="border: 1px solid #e4e4e4;width:930px;margin-bottom:10px;margin:0 auto;padding:10px;margin-bottom:10px;">
					<a href="${pageContext.request.contextPath }/">首页&nbsp;&nbsp;&gt;</a>
					<a href="./蔬菜分类.htm">${product.category.cname }&nbsp;&nbsp;&gt;</a>
				</div> --%>
				<div class="col-md-12">
					<ol class="breadcrumb" style="background-color: #E0E0E0;">
						<li><a href="${pageContext.request.contextPath }/">首页</a></li>
						<li><a href="${pageContext.request.contextPath }/product_findByPage?category.cid=${product.category.cid}">${product.category.cname }</a></li>
						<li><a href="javacript:void(0)">${product.pname }</a></li>
					</ol>
				</div>

				<div style="margin:0 auto;width:950px;">
					<div class="col-md-6" >
						<div class="col-md-1"></div>
						<div class="col-md-8" style="border: 1px solid #efeeee;height:240px;background-color: white;">
							<img style="opacity: 1;width:272px;height:230px;" title="商品图片" class="medium" src="${pageContext.request.contextPath}/${product.pimage }">
							<div style="position:absolute;top:215px;left:272px;">
								<img title="查看大图" src="${pageContext.request.contextPath }/images/zoom.png" style="cursor:pointer;width:20px;" onclick="seeBig('${product.pimage}')" > 
							</div>
						</div>
						<div class="col-md-3"></div>
					</div>
					<div class="col-md-6">
						<form id="formId" action="${pageContext.request.contextPath }/cart_add.action" method="post">
							<!-- 使用隐藏域提交商品id -->
							<input type="hidden" name="pid" value="${product.pid }">
							<div><strong>${product.pname }</strong></div>
<!-- 							<div style="border-bottom: 1px dotted #dddddd;width:350px;margin:10px 0 10px 0;">
								<div>编号：751</div>
							</div> -->
							<div style="margin:10px 0 10px 0;">
								亿家价:<strong style="color:#ef0101;">￥${product.shop_price }元/件</strong> &nbsp;参考价:<del>￥${product.market_price }元/件</del>
							</div>
	
							<div style="margin:10px 0 10px 0;">促销: <a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)" style="background-color: #f07373;">限时抢购</a> </div>
	
							<div style="padding:10px;border:1px solid #e7dbb1;width:330px;margin:15px 0 10px 0;;background-color: #fffee6;">
								<!-- <div style="margin:5px 0 10px 0;">白色</div> -->
	
								<div style="border-bottom: 1px solid #faeac7;margin-top:20px;padding-left: 10px;">购买数量:
									<input id="countId" name="count" value="1" maxlength="4" size="10" type="text"> <label>(库存:9999件)</label>
								</div>
	
								<div style="margin:20px 0 10px 0;;text-align: center;">
									<a href="javascript:void(0)" onclick="checkForm()" onmouseover="add2Cart()" >
										<input style="background: url('${pageContext.request.contextPath}/images/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0);height:36px;width:127px;" id="addId" value="加入购物车" type="button">
									</a> &nbsp;收藏商品
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="clear"></div>
				<div style="width:950px;margin:0 auto;">
					<div style="background-color:#d3d3d3;width:900px;padding:10px 10px;margin:10px 0 5px 0;border-radius: 5px;">
						<strong>商品介绍</strong>
					</div>
					<div>${product.pdesc }</div>

					<div style="background-color:#d3d3d3;width:900px;padding:10px 10px;margin:10px 0 5px 0;border-radius: 5px;">
						<strong>商品参数</strong>
					</div>
					<div style="margin-top:2px;width:900px;">
						<table class="table table-bordered">
							<tbody>
							<!-- 
								<tr class="active">
									<th colspan="2">基本参数</th>
								</tr>
							 -->
								<tr>
									<th width="15%">级别</th>
									<td>标准</td>
								</tr>
								<tr>
									<th>标重</th>
									<td>500</td>
								</tr>
								<tr>
									<th>浮动</th>
									<td>200</td>
								</tr>
							</tbody>
						</table>
					</div>

					<div style="background-color:#d3d3d3;width:900px;padding:10px 10px;margin:10px 0 5px 0;border-radius: 5px;">
						<strong>商品评论</strong>
					</div>
					<div style="margin-top:2px;width:900px;">
						<table class="table table-bordered">
							<tbody>
								<tr class="warning" id="noCommentId">
									
								</tr>
							<c:forEach items="${product.orderItems }" var="item">
							<c:if test="${item.order.state == 4 }">
								<tr class="forCounter">
									<th style="border-right: 0;width:15%;text-align: center;" rowspan="2">${item.order.user.username }</th>
									<td style="border-left: 0;border-bottom: 0;font-size: 15px;">${item.comment }</td>
								</tr>
								<tr >
									<td style="border-left: 0;border-top: 0;padding:0px 8px;font-size: 12px;color: #b3b3b3;">
										<jsp:useBean id="dateValue" class="java.util.Date"/> 
										<!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->
										<jsp:setProperty name="dateValue" property="time" value="${item.update_at}"/> 
										<!-- 转换格式 -->
										<fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm:ss"/> 
									</td>
								</tr>
							</c:if>
							</c:forEach>
							</tbody>
						</table>
					</div>

<!-- 				<div style="background-color:#d3d3d3;width:900px;">
						<table class="table table-bordered">
							<tbody>
								<tr class="active">
									<th><strong>商品咨询</strong></th>
								</tr>
								<tr class="warning">
									<th>暂无商品咨询信息 <a>[发表商品咨询]</a></th>
								</tr>
							</tbody>
						</table>
					</div> -->
				</div>

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
	<script type="text/javascript">
		var countReg = /^[1-9]+[0-9]*$/
		function add2Cart() {
			if (!countReg.test($("#countId").val())) {
				$("#addId").css("cursor","not-allowed");
				$("#addId").attr("title","请填写正确的数值");
			} else if ($("#countId").val() > 9999 ) {
				$("#addId").css("cursor","not-allowed");
				$("#addId").attr("title","库存不足");
			} else {
				$("#addId").attr("title","");
				$("#addId").css("cursor","pointer");
			}
		}
		function checkForm() {
			if (!countReg.test($("#countId").val()) || $("#countId").val() > 9999) {
				return false;
			} else {
				$("#formId").submit();
				/* $.post("${pageContext.request.contextPath}/cart_add.action",
						{"pid": ${product.pid}, "count" : $("#countId").val()}); */
			}
		}
	</script>
</html>