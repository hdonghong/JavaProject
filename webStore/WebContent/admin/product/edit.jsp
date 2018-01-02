<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js" ></script>
		<script type="text/javascript">
			// 页面加载时查询总分类
			$(function(){
				// ajax发送请求
				$.get("${pageContext.request.contextPath}/category_findAll.action", function(data){
					
					// 遍历数组
					$(data).each(function(){
						if (this.cid == '${product.category.cid}') {
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
		<!--  -->
		<form id="userAction_save_do" name="Form1"
			action="${pageContext.request.contextPath}/adminProduct_update.action" method="post" >
			&nbsp;
			<table style="width: 100%;text-align: center;border: 1px solid #8ba7e3;"  >
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"height="26">
						<strong><STRONG>修改商品</STRONG>
						</strong>
					</td>
				</tr>

				<tr>
					<td style="width:50%;text-align:right;padding-right:15px;background-color: #f5fafe;height:22px;" class="ta_01">
						<%-- 隐藏一个pid --%>
						<input type="hidden" name="pid" value="${product.pid }"/>
						<label for="userAction_save_do_logonName">商品名称：</label>
						<input type="text" name="pname" value="${product.pname }" maxlength="20" id="userAction_save_do_logonName" required/>
					</td>
					<td  style="width:50%;text-align:left;padding-left:15px;background-color: #f5fafe;" class="ta_01" >
						<label for="category_cid">所属分类：</label>
						<select name="category.cid" id="category_cid" >
						</select>
					</td>
				</tr>
				<tr>
					<td style="width:50%;text-align:right;padding-right:15px;background-color: #f5fafe;height:22px;" class="ta_01">
						<label for="market_priceId">市场价格：</label>
						<input type="text" name="market_price" maxlength="10" id="market_priceId" value="${product.market_price }" />
					</td>
					<td style="width:50%;text-align:left;padding-left:15px;background-color: #f5fafe;" class="ta_01">
						<label for="shop_priceId">商城价格：</label>
						<input type="text" name="shop_price" maxlength="10" id="shop_priceId" value="${product.shop_price }" />
					</td>
				</tr>
				<tr>
					<td style="width:50%;text-align:right;padding-right:15px;background-color: #f5fafe;" class="ta_01">
						<label for="pdescId">商品描述：</label>
						<textarea id="pdescId" name="pdesc" rows="5" cols="23"  maxlength="255" style="resize: none;" >${product.pdesc }</textarea>
					</td>
					<td style="width:50%;text-align:left;padding-left:15px;background-color: #f5fafe;height:22px;" class="ta_01" >
						<label for="uploadId">商品图片：</label>
						<img id="uploadId" height="80px" src="${ pageContext.request.contextPath }/${product.pimage}">
					</td>
				</tr>
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="2">
						<button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
							&#30830;&#23450;
						</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
	</body>
</HTML>