<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>任务管理系统—注册页面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/login.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style2.css" type="text/css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
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
		 
		font {
			color: #3164af;
			font-size: 20px;
			font-weight: normal;
			padding: 0 10px;
		}
		label {
			color: #000;
		}
	 </style>
</head>
<body class="signin">
	<div class="container">
		<div class="row"> 
			<div class="col-md-2"></div>
			<div class="col-md-8" style="background:#fff;padding:40px 80px 0px;margin:60px 30px 0px;border:1px solid #ccc;">
				<font>设置新密码</font>USER REGISTER
				<form class="form-horizontal" style="margin-top:5px;" action="${pageContext.request.contextPath }/user?method=updatePwd" method="post">
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="hidden" value="${uid}" name="uid"/>
						  <input type="password" class="form-control" id="password" placeholder="请输入密码" name="password" required="required">
						</div>
					  </div>
					   <div class="form-group">
						<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-6">
						  <input type="password" class="form-control" id="confirmpwd" placeholder="请输入确认密码" name="confirmpwd" required="required">
						</div>
					  </div>
					  <!--
					  <div class="form-group">
						<label for="date" class="col-sm-2 control-label">验证码 *</label>
						<div class="col-sm-3">
						  <input type="text" class="form-control" name="code" > 
						</div>
						<div class="col-sm-2">
							<img src="${pageContext.request.contextPath }/img/captcha.jhtml"/>
						</div>
						
					  </div>  -->
					 
					  <div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
						  <input type="submit"  width="100" value="确认修改" name="submit"
							style="background: url('${pageContext.request.contextPath }/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
							height:35px;width:100px;color:white;">
						</div>
					  </div>
				</form>
			</div>
			<div class="col-md-2"></div>
		</div>
		<div class="row">
			<div class="signup-footer col-sm-6 col-sm-offset-3 form-box">
				<div style="margin-top: 0px; text-align: center;font-size: 13px;">© 2017-？任务管理系统 保留所有版权.</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	//密码验证提示
	var password = document.getElementById('password');
	var confirmpwd = document.getElementById('confirmpwd');
	confirmpwd.onblur = function() {
		if (confirmpwd.value!=password.value) {	
			confirmpwd.setCustomValidity("前后输入的密码应保持一致");
		} else {
			confirmpwd.setCustomValidity("");
		}
		
	};
</script>
</html>