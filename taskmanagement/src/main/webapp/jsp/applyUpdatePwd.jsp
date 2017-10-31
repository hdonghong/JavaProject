<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>任务管理系统—申述页面</title>
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
			<div class="col-md-8" style="background:#fff;padding:40px 80px 0px;margin:60px 30px 0px;border:1px solid #ccc;">
				<font>申述修改密码</font>USER REGISTER
				<form class="form-horizontal" style="margin-top:5px;" action="${pageContext.request.contextPath }/user?method=applyUpdatePwd" method="post">
					 <div class="form-group">
						<label for="stuid" class="col-sm-2 control-label">账号</label>
						<div class="col-sm-6">
						  <input type="text" class="form-control" id="stuid" placeholder="请输入账号" name="stuid" required pattern="^[0-9]{12}$">
						</div>
					  </div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="inputEmail3" placeholder="请输入您绑定的Email" name="email">
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
						  <input type="submit"  width="100" value="确认提交申述" name="submit"
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
	//账号验证提示
	$(function() {
		var stuid = document.getElementById("stuid");
		var flag = 0; //是否重用名的标识，1表示重用
        stuid.onblur = function() {
		    /*
			$.post("${pageContext.request.contextPath}/user?method=findByUsername", {"stuid":stuid.value}, function(data){
				flag = data;	
			}, "json");
			*/
		    if(stuid.value){
                stuid.setCustomValidity("");//现将有输入时的提示设置为空
	        }else if(stuid.validity.valueMissing){
                stuid.setCustomValidity("账号不能为空");
	        };
			if (stuid.validity.patternMismatch) {
                stuid.setCustomValidity("账号须使用学号注册");
			} else if (flag == 1) {
                stuid.setCustomValidity("无法使用已存在的账号");
			};
		};
	});
</script>
</html>