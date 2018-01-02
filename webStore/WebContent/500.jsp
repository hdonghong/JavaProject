<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务器异常</title>
<style>
	*{
		margin:0px;
		padding: 0px;
	}

	.main{

	margin-top: 100px;
	position: relative;
	min-width:1500px;
	
	}
	.main .left{
		position: absolute;
		left: 10%;
	}
	.main .left .dabai{
	
		margin-left: 25%;
		
	}
	.main .left .dabai img{
		
	
			margin: 0 auto;
		width:450px;
		height:450px;
		display: inline-block;
		
	}
	.main .right{
	float: left;
	margin-top: 15px;
	position: absolute;
		left: 50%;
		
		
	}
	.main .right  .warn{
	  
			margin: 0 auto;
		font-size:140px;
		color:#329446;
		font-family:微软雅黑；
	
	}
	.main .right  .sup1{
		
			margin: 0 auto;
			font-size: 50px;
		color:#18B8CF;
			
		
	}
		.main .right .sup2{
		
				margin: 0 auto;
			font-size: 30px;
		color:#18B8CF;
			line-height: 37px;
		
		
	}
	.main .right .bottom {
			position: relative;
	}
	.main .right .bottom .return{
		text-align: center;
		background-color: skyblue;
		width: 200px;
		height: 60px;
		display: inline-block;
		font-size: 30px;
		line-height: 60px;
		margin-top: 30px;
		border-radius:15px;
		opacity:0.7;
		
	
	}
	.main .right .bottom .return a{
		text-decoration: none;
		color: blue;
	}
	.main .right .bottom  .left{
			position: absolute;
			left: 30px;
			
		
	}
	.main .right .bottom .right{
			position: absolute;
			left: 310px;
			
		
	}
	.main .right .bottom .return:hover{
	opacity:1;
	transform:scale(1.1,1.1);
	}
	
</style>
</head>
<body>
<div class="main">
	 <div class="left">
			<div class="dabai"><img src="${pageContext.request.contextPath }/images/500.gif"></img></div>
	</div>
		<div class="right">
			<span class="warn">500</span></p>
			<span class="sup1">抱歉，服务器内部故障了。</span></p>
			<span class="sup2">您可以掉头返回上一页或回到网站首页。</span>
		
			<div class="bottom">
				<span class="return left"><a href="javascript :;" onClick="javascript :history.back(-1);">返回上一页</a></span>
				<span class="return right"><a href="#">返回网站首页</a></span>
			</div>
		</div>
</div>
</body>
</html>