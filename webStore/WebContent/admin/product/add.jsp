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
						$("#category_cid").append("<option value='"+ this.cid +"'>"+ this.cname +"</option>");
					});
					
				}, "json");
			});
			// 提交表单前检查，添加商品图片(格式、大小)的限制
			 function checkForm() {
				  var target = document.getElementById("uploadId");
			      var fileSize = 0;  
			      fileSize = target.files[0].size;
			      var size = fileSize / 1024;
			      if(size>255){  
			         alert("商品图片不能大于255kb");  
			         target.value="";  
			         return false;   //阻止submit提交  
			      }  
			      var name=target.value;
			      var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();  
			      if(fileName !="jpg" && fileName !="jpeg" && fileName !="png" && fileName !="dwg" && fileName !="gif" ){  
			         alert("请选择图片格式文件上传(jpg,png,gif,dwg,pdf,gif等)！");
			         target.value="";
			         return false;   //阻止submit提交  
			      }  
			} 
		</script>
	</HEAD>
	
	<body>
		<!--  -->
		<form id="userAction_save_do" name="Form1" onsubmit="return checkForm()"
			action="${pageContext.request.contextPath}/adminProduct_save.action" method="post" enctype="multipart/form-data">
			&nbsp;
			<table style="width: 100%;text-align: center;border: 1px solid #8ba7e3;"  >
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"height="26">
						<strong><STRONG>添加商品</STRONG>
						</strong>
					</td>
				</tr>

				<tr>
					<td style="width:50%;text-align:right;padding-right:15px;background-color: #f5fafe;height:22px;" class="ta_01">
						<label for="userAction_save_do_logonName">商品名称：</label>
						<input type="text" name="pname" value="" maxlength="20" id="userAction_save_do_logonName" required/>
					</td>
					<td  style="width:50%;text-align:left;padding-left:15px;background-color: #f5fafe;" class="ta_01" >
						<label for="category_cid">所属分类：</label>
						<select name="category.cid" id="category_cid" >
							<option value="1">-请选择-</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="width:50%;text-align:right;padding-right:15px;background-color: #f5fafe;height:22px;" class="ta_01">
						<label for="market_priceId">市场价格：</label>
						<input type="text" name="market_price" maxlength="10" id="market_priceId" />
					</td>
					<td style="width:50%;text-align:left;padding-left:15px;background-color: #f5fafe;" class="ta_01">
						<label for="shop_priceId">商城价格：</label>
						<input type="text" name="shop_price" maxlength="10" id="shop_priceId" />
					</td>
				</tr>
				<tr>
					<td style="width:50%;text-align:right;padding-right:15px;background-color: #f5fafe;" class="ta_01" rowspan="2">
						<label for="pdescId">商品描述：</label>
						<textarea id="pdescId" name="pdesc" rows="5" cols="23"  maxlength="255" style="resize: none;"></textarea>
					</td>
					<td style="width:50%;text-align:left;padding-left:15px;background-color: #f5fafe;height:22px;" class="ta_01" >
						<label for="uploadId">商品图片：</label>
						<input id="uploadId" type="file" name="upload"  accept="image/*" />
					</td>
				</tr>
				<tr>
					<td style="background-color: #f5fafe;" class="ta_01" ></td>
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