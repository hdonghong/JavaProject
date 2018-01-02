<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>您访问的页面不存在</title>
<style type="text/css">
		<!--body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,textarea,p,blockquote,th,td{margin:0;padding:0}table{border-collapse:collapse;border-spacing:0}fieldset,img{border:0}-->
			
			body{
				
			  display:block;
			}
			
			div{
			display:block;
			}
			
			.main{
				
				box-sizing:border-box;
				
				margin:90px 0;		
			}
			.tu{
				width:100%;
				text-align:center;
				border:0px;
				margin-top:30px;
				
			}
			

			}
			
			dl{
			display: block;
	
			}
			
			
			dl{
			text-align:center;
			
			}
			
			.ti{
				margin-top:10px;
			font-family:"微软雅黑";
			font-size:35px;
			color:black;
			}
			
			p{
			color:#9c6d6d;
			word-spacing: 15px;
			font-family:"微软雅黑";
			font-size:25px;
		
			}
			
			
			.return{
			margin:10px 45%;
			width:130px;
			height:50px;
			background-color:#0033FF;
			border-radius:15px;
			font-family:"微软雅黑";
			font-size:25px;
			line-height:50px;
			
			}
			
			a{
			color:#B0B0B0;
			text-decoration:none;
			}
		
		
		</style>
</head>
<body>
	<div class="main">
		<div class="tu"><img src="${pageContext.request.contextPath }/images/404.gif" alt="找不到网页"></div>
		<dl>
			<dd class="ti">咦~网页不见了~</dd>
			<dd>请核对您输入的网页地址是否正确哦~</dd>
			<dd class="return"><a href="${pageContext.request.contextPath }/">返回首页</a></dd>
		</dl>
	</div>
</body>
</html>