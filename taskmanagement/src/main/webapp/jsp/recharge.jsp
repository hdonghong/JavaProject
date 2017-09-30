<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>校园卡查询系统</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
		<script src="${pageContext.request.contextPath }/js/jquery-1.11.0.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
	</head>
	<body style="background-color: whitesmoke;">
		<div class="container-fluid">
			<!-- 
				Logo图、导航条
			 -->
			<jsp:include page="/jsp/head.jsp"></jsp:include>
			
			<!--
	        	时间：2017-08-21
	        	描述：余额充值
	        -->
			<div class="container-fluid"  style="margin-bottom: 100px; padding-top: 50px;">
				<form action="${pageContext.request.contextPath }/bill?method=add" method="post" >
					<table style="margin: 0 auto;">
						<tr>
							<td>
								<div class="input-group">
						            <span class="input-group-addon">$</span>
						            <input id="money" name="money" type="text" class="form-control" required pattern="^[0-9]+">
						            <span class="input-group-addon">.00</span>
						        </div>
							</td>
						</tr>
						<tr style="text-align: center;">
							<td>
								<input type="submit" value="确定" style="margin: 20px 10px;" class="btn btn-primary">
								<label>余额：${user.balance }元</label>
							</td>
						</tr>
					</table>
				</form>
			</div>
			
			<!--
            	时间：2017-08-22
            	描述：页脚
            -->
			<div class="container-fluid">		
				<div style="text-align: center;margin-top: 35px;">
					<ul class="list-inline">
						<li><a>关于我们</a></li>
						<li><a>联系我们</a></li>
						<li><a>招贤纳士</a></li>
						<li><a>法律声明</a></li>
						<li><a>友情链接</a></li>
						<li><a>支付方式</a></li>
						<li><a>服务声明</a></li>
					</ul>
				</div>
				<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
					Copyright &copy; 2017-? 校园卡查询系统 版权所有
				</div>
			</div>
			
		</div>
	</body>
	<script type="text/javascript">
		//将所选设为高亮
		onload = function(){
		    $('#menu_3').addClass("active");
		    
			var money = document.getElementById("money");
			money.onblur = function() {
				if (money.validity.patternMismatch) {
					money.setCustomValidity("请确保输入的金额为整数");
				} else if (money.validity.valueMissing) {
					money.setCustomValidity("请输入充值金额");
				} else if (money.value <= 0) {
					money.setCustomValidity("Are you kidding me?");
				} else {
					money.setCustomValidity("");
				}
			};
		};
	</script>
</html>
