<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head></head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员注册</title>
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
		
		// 检查防止重用名
		function checkRepeatedUser() {
			var username = $("#username").val().trim();
			if (username == "") {
				$("#usernameMsg").removeClass("ok"); // 去除设置字体为绿色
				$("#usernameMsg").addClass("error"); // 设置字体为红色
				$("#usernameMsg").html("用户名不能为空");
			} else {
				var url = "${pageContext.request.contextPath}/user_checkRepeatedUser";
				var params = {"username": username };
				$.post(url, params, function(data){
					if (data == "no") { // 不可用
						$("#usernameMsg").removeClass("ok");
						$("#usernameMsg").addClass("error");
						$("#usernameMsg").html("用户名已存在");
					} else {
						$("#usernameMsg").removeClass("error");
						$("#usernameMsg").addClass("ok");
						$("#usernameMsg").html("用户名可以使用");
					}
				})
			}
		};
		
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
		
		// 检查邮箱
		function checkEmail() {
			var email = $("#email").val().trim();
			var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/; // 邮箱正则式
			if (email == "") {
				$("#emailMsg").removeClass("ok");
				$("#emailMsg").addClass("error");
				$("#emailMsg").html("email不能为空");
			} else if (!myreg.test(email)) {
				$("#emailMsg").removeClass("ok");
				$("#emailMsg").addClass("error");
				$("#emailMsg").html("email格式错误");
			} else {
				$("#emailMsg").removeClass("error");
				$("#emailMsg").addClass("ok");
				$("#emailMsg").html("email可以使用");
			}
		}
		
		// 检查姓名
		function checkName() {
			var mail = $("#name").val().trim();
			if (mail == "") {
				$("#nameMsg").removeClass("ok");
				$("#nameMsg").addClass("error");
				$("#nameMsg").html("姓名不能为空");
			} else {
				$("#nameMsg").removeClass("error");
				$("#nameMsg").addClass("ok");
				$("#nameMsg").html("姓名可以使用");
			}
		}
		
		// 检查验证码
		/*
		function checkCode() {
			var mail = $("#code").val().trim();
			if (mail == "") {
				$("#codeMsg").removeClass("ok");
				$("#codeMsg").addClass("error");
				$("#codeMsg").html("验证码不能为空");
			} else {
				$("#codeMsg").removeClass("error");
				$("#codeMsg").addClass("ok");
				$("#codeMsg").html("验证码正确");
			}
		}
		*/
		
		// 重置提示
		function resetMsg(id) {
			$(id).html("");
		}
		
		// 提交表单前检查是否符合要求
		function checkForm() {
			checkRepeatedUser();
			checkPassword();
			checkSecondPassword();
			checkEmail();
			checkName();
			
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

<div class="container" style="width:100%;background:url('${pageContext.request.contextPath}/images/regist_bg.jpg');">
<div class="row">

	<div class="col-md-2"></div>
	<div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;" id="moduleId">
		<font>会员注册</font>USER REGISTER
		<form class="form-horizontal" style="margin-top:5px;" action="${pageContext.request.contextPath }/user_regist" method="post" onsubmit="return checkForm()">
			 <div class="form-group">
			    <label for="username" class="col-sm-2 control-label">用户名</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" maxlength="20" id="username" placeholder="请输入用户名" name="username" onblur="checkRepeatedUser()" onfocus="resetMsg('#usernameMsg')" required />
			    </div>
			    <label id="usernameMsg" class="control-label"></label>
			  </div>
			  
			   <div class="form-group">
			    <label for="password" class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-6">
			      <input type="password" class="form-control" maxlength="20" id="password" placeholder="请输入密码" name="password" onblur="checkPassword()" onfocus="resetMsg('#passwordMsg')" required >
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
			    <label for="name" class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" maxlength="20" id="name" placeholder="请输入您的真实姓名" onblur="checkName()" onfocus="resetMsg('#nameMsg')" name="name" required>
			    </div>
			    <label id="nameMsg" class="control-label"></label>
			  </div>
			  
			  <div class="form-group">
			    <label for="email" class="col-sm-2 control-label">Email</label>
			    <div class="col-sm-6">
			      <input type="email" class="form-control" maxlength="30" id="email" placeholder="请输入Email" name="email" onblur="checkEmail()" onfocus="resetMsg('#emailMsg')" required title="忘记密码时将通过您绑定的邮箱找回密码">
			    </div>
			    <label id="emailMsg" class="control-label"></label>
			  </div>
			  
 			   <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <input type="submit"  width="100" value="注册" name="submit"
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




