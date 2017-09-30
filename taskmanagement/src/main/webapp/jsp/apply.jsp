<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>任务管理系统</title>
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
				<form action="${pageContext.request.contextPath }/apply?method=apply" method="post" name="myform" onSubmit="return submitForm('${user.state}')" >
					<table style="margin: 0 auto; text-align: center;">
					<c:if test="${user.state==0 }">
						<tr style="margin: 200px;">
							<td colspan="2" style="font-size: small;">
								<p>温馨提示：挂失校园卡后,您的校园卡将处于锁定状态,须经过管理员</p>
								<p>批准后才能解除锁定,处于锁定状态的校园卡账户将失去部分权限.</p>
							</td>
						</tr>
						<tr>
							<td><label>挂失理由：</label></td>
							<td>
						        <textarea cols="40" rows="5" name="reason" id="reason"></textarea>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input type="hidden" value="1" name="state" />
								<input type="hidden" value="挂失校园卡" name="adesc" id="adesc" />
								<input type="submit" value="锁定账户" style="margin: 20px 10px;" class="btn btn-primary" />
							</td>
						</tr>
					</c:if>
					<c:if test="${user.state==1 }">
						<tr>
							<td>
								<h3>您的账户处于“锁定状态”，其间失去部分权限(查看消费记录，余额充值，</h3>
								<h3>消费，查看个人信息等)，您可申请解锁由管理员批准后恢复使用</h3>
							</td>
						</tr>
						<tr>
							<td>
								<input type="hidden" value="2" name="state" />
								<input type="hidden" value="申请解锁校园卡" name="adesc" />
								<input type="submit" value="申请解锁" style="margin: 20px 10px;" class="btn btn-primary" />
							</td>
						</tr>
					</c:if>
					<c:if test="${user.state==2 }">
						<tr>
							<td>
								<h3>您正在申请解锁账户，其间失去部分权限(查看消费记录，余额充值，</h3>
								<h3>消费，查看个人信息等)，待管理员批准后恢复使用</h3>
							</td>
						</tr>
						<tr>
							<td>
								<input type="hidden" value="1" name="state" />
								<input type="hidden" value="取消申请" name="adesc" />
								<input type="submit" value="取消申请" style="margin: 20px 10px;" class="btn btn-primary" />
							</td>
						</tr>
					</c:if>
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
					Copyright &copy; 2017-? 任务管理系统 版权所有
				</div>
			</div>
			
		</div>
	</body>
		<script type="text/javascript">
		//将所选设为高亮
		onload = function(){
		    $('#menu_4').addClass("active");
		};
	    
	    function submitForm(state){
	    	if (state == 0) {
	    		myform.adesc.value = "挂失校园卡";
	    		if (myform.reason.value != null && myform.reason.value.length > 0) {
		    		myform.adesc.value += (": " + myform.reason.value);
	    		}
	    		return confirm("您确定要挂失校园卡吗？");
	    	} else if (state == 1) {
	    		return confirm("您确定要申请解锁校园卡吗？");
	    	} else if (state == 2) {
	    		return confirm("您确定要取消申请？");
	    	}
	    }
	</script>
</html>
