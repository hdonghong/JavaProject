<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>校园卡查询系统—注册页面</title>
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
<body>
<body class="signin">
	<div class="container">
		<div class="row"> 
			<div class="col-md-2"></div>
			<div class="col-md-8" style="background:#fff;padding:40px 80px 5px;margin:80px 30px 5px;border:1px solid #ccc;">
				<font>用户注册</font>USER REGISTER
				<form class="form-horizontal" style="margin-top:5px;" action="${pageContext.request.contextPath }/user?method=regist" method="post">
					 <div class="form-group">
						<label for="username" class="col-sm-2 control-label">账号 *</label>
						<div class="col-sm-6">
						  <input type="text" class="form-control" id="username" placeholder="请输入账号" name="username" required pattern="^[a-zA-Z0-9]{6,12}$">
						</div>
					  </div>
					   <div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码 *</label>
						<div class="col-sm-6">
						  <input type="password" class="form-control" id="password" placeholder="请输入密码" name="password" required="required">
						</div>
					  </div>
					   <div class="form-group">
						<label for="confirmpwd" class="col-sm-2 control-label">确认密码 *</label>
						<div class="col-sm-6">
						  <input type="password" class="form-control" id="confirmpwd" placeholder="请输入确认密码" name="confirmpwd" required="required">
						</div>
					  </div>
					  <div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
						  <input type="email" class="form-control" id="inputEmail3" placeholder="Email" name="email">
						</div>
					  </div>
					 <div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">姓名 *</label>
						<div class="col-sm-6">
						  <input type="text" class="form-control" id="usercaption" placeholder="请输入姓名" name="name" required="required">
						</div>
					  </div>
					  <div class="form-group opt">  
					  <label for="inlineRadio1" class="col-sm-2 control-label">性别 *</label>  
					  <div class="col-sm-6">
						<label class="radio-inline">
					  <input type="radio" name="sex" id="inlineRadio1" value="男" checked="checked"> 男
					</label>
					<label class="radio-inline">
					  <input type="radio" name="sex" id="inlineRadio2" value="女"> 女
					</label>
					</div>
					  </div>		
					  <div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
						  <input type="date" class="form-control" name="birthday" >		      
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
						  <input type="submit"  width="100" value="注册" name="submit"
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
				<div style="margin-top: 10px; text-align: center;">© 2017-？校园卡管理系统 保留所有版权.</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	//账号验证提示
	$(function() {
		var username = document.getElementById("username");
		var flag = 0; //是否重用名的标识，1表示重用
		username.onblur = function() {	
			$.post("${pageContext.request.contextPath}/user?method=findByUsername", {"username":username.value}, function(data){
				flag = data;	
			}, "json");
		    if(username.value){
	            username.setCustomValidity("");//现将有输入时的提示设置为空
	        }else if(username.validity.valueMissing){
	            username.setCustomValidity("账号不能为空");  
	        };
			if (username.validity.patternMismatch) {
				username.setCustomValidity("账号只能是英文或数字，长度6到12位");
			} else if (flag == 1) {
				username.setCustomValidity("无法使用已存在的账号");
			};
		};
	});
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