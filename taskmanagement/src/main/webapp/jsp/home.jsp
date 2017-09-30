<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>校园卡查询系统</title>
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
	        	描述：轮播图
	        -->
			<div class="container-fluid">
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
					</ol>
	
					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<img src="${pageContext.request.contextPath }/img/1.jpg" style="width:100%;height:700px">
							<div class="carousel-caption">...</div>
						</div>
						<div class="item">
							<img src="${pageContext.request.contextPath }/img/2.jpg" style="width:100%;height:700px">
							<div class="carousel-caption">...</div>
						</div>
						<div class="item">
							<img src="${pageContext.request.contextPath }/img/3.jpg" style="width:100%;height:700px">
							<div class="carousel-caption">...</div>
						</div>
					</div>
	
					<!-- Controls -->
					<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
						<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a>
					<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
						<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
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
					Copyright &copy; 2017-? 校园卡查询系统 版权所有
				</div>
			</div>
			
		</div>
	</body>
	<script type="text/javascript">
		//将所选设为高亮
		onload = function(){
		    $('#menu_1').addClass("active");
		};
	</script>
</html>
