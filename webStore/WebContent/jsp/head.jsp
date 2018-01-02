<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/style2.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/theme.css" rel="stylesheet" type="text/css">
		<link rel="shortcut icon"  type="images/x-icon"  href="${pageContext.request.contextPath}/images/icon.ico">
		<link rel="Bookmark"  type="images/x-icon" href="${pageContext.request.contextPath}/images/icon.ico">
		
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		
		
		<style type="text/css">
			li:hover .dropdown-menu {
				display: block;
}
		</style>
		
		<script type="text/javascript">
		$(function(){
		var nav=$(".header-bottom"); //得到导航对象
		var win=$(window); //得到窗口对象
		var sc=$(document);//得到document文档对象。
		win.scroll(function(){
		  if(sc.scrollTop()>=100){
			nav.addClass("fixednav"); 
		   $(".navTmp").fadeIn(); 
		  }else{
		   nav.removeClass("fixednav");
		   $(".navTmp").fadeOut();
		  }
		})  
		})
		</script>

<style>
.fixednav {
    position: fixed;
    top: 0px;
    left: 0px;
    width: 100%;
    z-index: 999;
}
</style>
	</head>
	<body>
			<!-- 
 				顶拦
 				2017年11月9日07:07:29
 			 -->
			<div class="header-top">
				<div class="container">
					<div class="row">
                      <div class="col-sm-3">
                      	<ul class="list-inline">
                        	<li><a><c:if test="${not empty user }">${user.username }，</c:if>欢迎来到WEB商城</a></li>
                        </ul>
                       </div>
					  <div class="col-sm-4">
						  <ul class="list-inline">
                          		
								<li><a href="javascript:void(0)"><i class="fa fa-mobile"></i> +86 11111111</a></li>
								<li><a href="javascript:void(0)"><i class="fa fa-envelope-o"></i> example@gmail.com</a></li>
							</ul>	
						</div>
						<div class="col-sm-5">
							<ul class="list-inline pull-right">
								<c:if test="${empty user }">
									<li><a href="${pageContext.request.contextPath }/jsp/register.jsp"><i class="fa fa-user"></i>注册账户</a></li>
									<li><a class="register" href="${pageContext.request.contextPath }/jsp/login.jsp">登录</a></li>
								</c:if>
								<c:if test="${not empty user }">
									<li class="dropdown" id="menu_4">
							          <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
							          	<i class="fa fa-user"></i><font>我的账户</font></a>
							          <ul class="dropdown-menu"  style="min-width: 0px;padding:0px;">
							            <li><a href="${pageContext.request.contextPath }/jsp/person_info.jsp" style="padding: 2px 12px;font-size:12px;">个人中心</a></li>
							            <li role="separator" class="divider" style="margin:0px;"></li>
							            <li><a href="${pageContext.request.contextPath }/order_findByPage.action" style="padding: 2px 12px;font-size:12px;">我的订单</a></li>
							            <li role="separator" class="divider" style="margin:0px;"></li>
							            <li><a href="${pageContext.request.contextPath }/user_logout" style="padding: 2px 12px;font-size:12px;">退出</a></li>
							          </ul>
							        </li>
									<li><a href="${pageContext.request.contextPath }/jsp/cart.jsp"><i class="fa fa-heart-o"></i> 
										购物车 (<c:if test="${empty cart }">0</c:if>${sessionScope.cart.cartItems.size() })
									</a></li>
	                                
									<%-- <li><a href="${pageContext.request.contextPath }/user_logout"><i class="fa fa-comment-o"></i>消息通知</a></li> --%>
									
								</c:if>
							</ul>	
						</div>	
					</div>	
				</div>
			</div>
			
			<!-- logo图
				2017年11月9日07:07:42
			-->
			<div class="logo-add">
				<div class="container">
					<div class="row">
						<div class="col-sm-4">
							<div class="logo"><img src="${pageContext.request.contextPath }/images/logo.png" height="60px"/></div>
						</div>
<%-- 						<div class="col-sm-5">
                            <img src="${pageContext.request.contextPath }/images/header.jpg"/>
                        </div> --%>
					</div>
				</div>
			</div>
			
			<!-- 
				导航条
				2017-11-09 07:08:00
			-->
			<div class="header-bottom">
				<div class="row">
					<div class="col-sm-12">
						<nav class="navbar navbar-default" style="margin-bottom:0px;background:#0277BD">
							<div class="container">
								<!-- Brand and toggle get grouped for better mobile display -->
								<div class="navbar-header">
									<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#header-bottom" aria-expanded="false">
										<span class="sr-only">Toggle navigation</span>
										<span class="icon-bar"></span>
										<span class="icon-bar"></span>
										<span class="icon-bar"></span>
									</button>
								</div>

								<!-- Collect the nav links, forms, and other content for toggling -->
								<div class="collapse navbar-collapse" id="header-bottom">
									<ul class="nav navbar-nav" id="menuId">
										<li><a href="${pageContext.request.contextPath}/">首页</a></li>
										<!-- <li><a href="">电子办公</a></li> -->
									</ul>
									<form class="navbar-form navbar-right" role="search" id="searchFromId"  style="width:30%;"
									      action="${pageContext.request.contextPath}/product_findByPage.action" method="post">
										<div class="form-group" style="width:100%;">
											<input type="text" name="pdesc" class="form-control" placeholder="搜索" style="width:100%;background:#f6f6f6;" />
											<span class="nav-search" >
												<a onclick="document.getElementById('searchFromId').submit()" style="cursor: pointer;"><i class="fa fa-search"></i></a>
											</span>
										</div>	
									</form>
								</div>
							</div>
						</nav>
					</div>	
				</div>	
			</div>	
</body>
<script type="text/javascript">
	$(function(){
		
		//发送ajax请求
		$.get("${pageContext.request.contextPath}/category_findAll", function(data){
			//获取menu的ul标签
			var $ul = $("#menuId");
			
			//遍历数组
			$(data).each(function(){
				$ul.append($("<li><a href='${pageContext.request.contextPath}/product_findByPage?category.cid="+
						this.cid+"'>"+ this.cname +"</a></li>"));
			});
			
		}, "json");
		
	});
</script>
</html>
