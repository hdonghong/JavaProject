<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<link rel="shortcut icon"  type="images/x-icon"  href="${pageContext.request.contextPath}/images/icon.ico">
	<link rel="Bookmark"  type="images/x-icon" href="${pageContext.request.contextPath}/images/icon.ico">
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>身份验证</title>
		
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
		
		// 检查用户名
		function checkRepeatedUser() {
			var username = $("#username").val().trim();
			if (username == "") {
				$("#usernameMsg").removeClass("ok"); // 去除设置字体为绿色
				$("#usernameMsg").addClass("error"); // 设置字体为红色
				$("#usernameMsg").html("用户名不能为空");
			} else {
				$("#usernameMsg").removeClass("error");
				$("#usernameMsg").addClass("ok");
				$("#usernameMsg").html("用户名可以使用");
			}
		};
		
		
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
		
		
		
		// 重置提示
		function resetMsg(id) {
			$(id).html("");
		}
		
		// 提交表单前检查是否符合要求
		function checkForm() {
			checkRepeatedUser();
			checkEmail();
			
			if ($(".error").size() > 0) {
				return false;
			}
			return true;
		}
		
	</script>
 
</head>
<body>

		<!--
			菜单栏 导航条
			动态包含 
		-->
		<jsp:include page="/jsp/head.jsp"></jsp:include>

<div class="container" style="width:100%;background:url('${pageContext.request.contextPath}/images/regist_bg.jpg');">
<div class="row">

	<div class="col-md-2"></div>
	<div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;" id="moduleId" >
		<font>身份验证</font>AUTHENTICATION
		<form class="form-horizontal" style="margin-top:5px;" action="${pageContext.request.contextPath }/user_authenticate.action" method="post" onsubmit="return checkForm()">
			 <div class="form-group">
			    <label for="username" class="col-sm-2 control-label">用户名</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" maxlength="20" id="username" placeholder="请输入您要找回密码的账号用户名" name="username" onblur="checkRepeatedUser()" onfocus="resetMsg('#usernameMsg')" required />
			    </div>
			    <label id="usernameMsg" class="control-label"></label>
			  </div>	  
			  
			  <div class="form-group">
			    <label for="email" class="col-sm-2 control-label">Email</label>
			    <div class="col-sm-6">
			      <input type="email" class="form-control" maxlength="30" id="email" placeholder="请输入该账号绑定的Email" name="email" onblur="checkEmail()" onfocus="resetMsg('#emailMsg')" required title="忘记密码时将通过您绑定的邮箱找回密码">
			    </div>
			    <label id="emailMsg" class="control-label"></label>
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




