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
	 </style>
</head>
<body>
<body class="signin">
	<div class="container">
		<div> 
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<h1 style="text-align: center; margin-top: 30%;">
					注册成功，现在将转入登录页面
				</h1>
				<h1 style="text-align: center;">
					如果您的浏览器没有自动跳转，请点击<a href="${pageContext.request.contextPath }/">此链接</a>
				</h1>
			</div>
			<div class="col-md-2"></div>
		</div>
		<div>
			<div class="signup-footer col-sm-6 col-sm-offset-3 form-box">
				<div style="margin-top: 10px; text-align: center;">© 2017-？任务管理系统 保留所有版权.</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		setTimeout("location.href='${pageContext.request.contextPath}'", 5000);
	});
</script>
</html>