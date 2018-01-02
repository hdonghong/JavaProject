<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head></head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>重置密码</title>
		<link rel="shortcut icon"  type="images/x-icon"  href="${pageContext.request.contextPath}/images/icon.ico">
		<link rel="Bookmark"  type="images/x-icon" href="${pageContext.request.contextPath}/images/icon.ico">
	<style>
		  body{
		   margin-top:20px;
		   margin:0 auto;
		 }
		 .carousel-inner .item img{
			 width:100%;
			 height:300px;
		 }
		 .container .row div{ 
			 /* position:relative;
			 float:left; */
		 }
		 .error {
		 	color:red;
		 }
		 .ok {
		 	color:#64DD17;
		 }
		#moduleId font {
		    color: #3164af;
		    font-size: 18px;
		    font-weight: normal;
		    padding: 0 10px;
		}
	</style>
	
	<script type="text/javascript">

		// 检查是否已经填入密码
		function checkPassword() {
			var password = $("#password").val();
			if ( password == "" ) {
				$("#passwordMsg").removeClass("ok");
				$("#passwordMsg").addClass("error");
				$("#passwordMsg").html("密码不能为空");
			} else {
				$("#passwordMsg").removeClass("error");
				$("#passwordMsg").addClass("ok");
				$("#passwordMsg").html("密码可以使用");
			}
		}
		
		// 检查二次密码是否匹配
		function checkSecondPassword() {
			var secondPassword = $("#secondPassword").val();
			if (secondPassword == "") {
				$("#secondPasswordMsg").removeClass("ok");
				$("#secondPasswordMsg").addClass("error");
				$("#secondPasswordMsg").html("二次密码不能为空");
			} else if ( secondPassword != $("#password").val()) {
				$("#secondPasswordMsg").removeClass("ok");
				$("#secondPasswordMsg").addClass("error");
				$("#secondPasswordMsg").html("前后输入的密码不一致");
			} else {
				$("#secondPasswordMsg").removeClass("error");
				$("#secondPasswordMsg").addClass("ok");
				$("#secondPasswordMsg").html("密码匹配正确");
			}
		}
		
		
		// 重置提示
		function resetMsg(id) {
			$(id).html("");
		}
		
		// 提交表单前检查是否符合要求
		function checkForm() {
			checkPassword();
			checkSecondPassword();
			
			if ($(".error").size() > 0) {
				return false;
			}
			return true;
		}
		
	</script>
 
</head>
<body style="background-color:#f6f6f6;">

		<!--
			菜单栏 导航条
			动态包含 
		-->
		<jsp:include page="/jsp/head.jsp"></jsp:include>

<div class="container" style="width:100%;background:url('${pageContext.request.contextPath}/image/regist_bg.jpg');">
<div class="row">

	<div class="col-md-2"></div>
	<div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;" id="moduleId">
		<font>重置密码</font>RESET PASSWORD
		<form class="form-horizontal" style="margin-top:5px;" action="${pageContext.request.contextPath }/user_resetPassword.action" method="post" onsubmit="return checkForm()">
			<div>
				<input type="hidden" name="uid" value="${requestScope.updatedUser.uid }"  />
				<input type="hidden" name="name" value="${requestScope.updatedUser.name }"  />
				<input type="hidden" name="email" value="${requestScope.updatedUser.email }"  />
			</div>
			 <div class="form-group">
			    <label for="username" class="col-sm-2 control-label">用户名</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" maxlength="20" id="username" placeholder="请输入用户名" name="username" value="${requestScope.updatedUser.username }" disabled="disabled" required />
			    </div>
			    <label id="usernameMsg" class="control-label"></label>
			 </div>
			  
			   <div class="form-group">
			    <label for="password" class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-6">
			      <input type="password" class="form-control" maxlength="20" id="password" placeholder="请输入新密码" name="password" onblur="checkPassword()" onfocus="resetMsg('#passwordMsg')" required >
			    </div>
			    <label id="passwordMsg" class="control-label"></label>
			  </div>
			  
			   <div class="form-group">
			    <label for="secondPassword" class="col-sm-2 control-label">确认密码</label>
			    <div class="col-sm-6">
			      <input type="password" class="form-control" maxlength="20" id="secondPassword" placeholder="请输入二次密码" onblur="checkSecondPassword()" onfocus="resetMsg('#secondPasswordMsg')" required>
			    </div>
			    <label id="secondPasswordMsg" class="control-label"></label>
			  </div>
			  		  
 			   <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <input type="submit"  width="100" value="确定" name="submit"
				    style="background: url('${pageContext.request.contextPath}/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;">
			    </div>
			  </div>
			</form>
	</div>
	
	<div class="col-md-2"></div>
  
</div>
</div>

	  
	
		<!-- 
			页脚
			时间：2017-12-12 08:28:10
		 -->
		<div>
			<%@ include file="foot.jsp" %>
		</div>

</body></html>




