<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mymd.css" type="text/css" />
		<link rel="shortcut icon"  type="images/x-icon"  href="${pageContext.request.contextPath}/images/icon.ico">
		<link rel="Bookmark"  type="images/x-icon" href="${pageContext.request.contextPath}/images/icon.ico">
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		

	<script type="text/javascript">// <![CDATA[
    $( function () {
        var speed = 1000;//自定义滚动速度
        //回到顶部
        $( "#toTop").click( function () {
            $( "html,body").animate({ "scrollTop" : 0 }, speed);
            });
        //回到底部
        var windowHeight = parseInt($("body").css("height" ));//整个页面的高度
        $( "#toBottom").click(function () {
            $( "html,body").animate({ "scrollTop" : windowHeight }, speed);
        });
    });

//]]></script>

		
<style>
/*边框hover动画*/
.col-md-2 li span{
  display: block;
}
.border_top, .border_left, .border_right, .border_bottom{
  position: absolute;
  background-color: #e01222;
  -webkit-transition: width 0.5s, height 0.5s;
  -o-transition: width 0.5s, height 0.5s;
  transition: width 0.5s, height 0.5s;
  z-index:1000;
}
.border_top{
  width: 0;
  height: 1px;
  left: 0;
  top: 0;
}
.border_left{
  width: 1px;
  height: 0;
  left: 0;
  top: 0;
}
.border_right{
  width: 1px;
  height: 0;
  right: 0;
  bottom: 0;
}
.border_bottom{
  width: 0;
  height: 1px;
  right: 0;
  bottom: 0;
}
.col-md-2:hover .border_top, .col-md-2:hover .border_bottom{
  width: 100%;
}
.col-md-2:hover .border_left, .col-md-2:hover .border_right{
  height: 100%;
}

 #scroll {position:fixed; top:300px; right:50px;}
.scrollItem {font-size:11px;width:20px; height:70px;border:#e1e1e1 1px solid; cursor: pointer; text-align: center; padding-top: 10px;margin-bottom:2px;background:white;padding:0px;}

</style>
		<title>22311WEB商城</title>
	</head>

	<body style="background-color:#f6f6f6;">
		<!--
			菜单栏 导航条
			静态包含 
		-->
		<%@include file="/jsp/head.jsp" %>
		

			<!--
            	作者：22311
            	时间：2017年11月8日23:38:04
            	描述：轮播条
            -->
 			<div class="container-fluid" style="padding:0px;">
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
							<img style="height:583px;width:100%" src="${pageContext.request.contextPath}/images/1.jpg">
							<div class="carousel-caption">

							</div>
						</div>
						<div class="item">
							<img  style="height:583px;width:100%" src="${pageContext.request.contextPath}/images/2.jpg">
							<div class="carousel-caption">

							</div>
						</div>
						<div class="item">
							<img  style="height:583px;width:100%" src="${pageContext.request.contextPath}/images/3.jpg">
							<div class="carousel-caption">

							</div>
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
            	作者：22311
            	时间：2017年11月9日06:52:33
            	描述：商品显示
            -->
			<div class="container" id="popularShop">
				<div class="col-md-12"  style="background-color:#3aaba9;color:white;margin-bottom:2px">
					<h2 style="font-size:30px;margin-top:20px;margin-bottom:10px;color:white;">热门商品&nbsp;&nbsp;</h2>
				</div>
				<div class="col-md-2" style="border-right:0;padding:0;">
					<img src="${pageContext.request.contextPath}/products/hao/8.jpeg" width="194px" height="400px" style="display: inline-block;"/>
				</div>
				<div class="col-md-10" style="background-color:#FFF;padding:0">
					<div class="col-md-6" style="text-align:center;height:200px;padding:0px;">
						<a href="javasript:void(0)">
							<img src="${pageContext.request.contextPath}/products/hao/middle01.jpg" width="99%" height="200px" style="display: inline-block;">
						</a>
					</div>
				
				
					<span class="spilitRow"></span>
					<span class="spilitCol" style="left:66.66%"></span> <!-- 640px -->
					<span class="spilitCol" style="left:83.33%"></span> <!-- 796.95px -->
				<c:forEach items="${hotProducts }" var="p">
					<div class="col-md-2" style="text-align:center;height:198px;padding:10px 0px;margin:1px 0;">
						<a href="${pageContext.request.contextPath }/product_getById?pid=${p.pid}">
							<img  class="moveableImg" src="${pageContext.request.contextPath}/${p.pimage}" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="${pageContext.request.contextPath }/product_getById?pid=${p.pid}" style='color:#666'> ${fn:substring(p.pname, 0, 10)}...</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;${p.shop_price }</font></p>
					<span class="border_top"></span>
                    <span class="border_left"></span>
                    <span class="border_right"></span>
                    <span class="border_bottom"></span>
					</div>
				</c:forEach>

				</div>
			</div>
			<!--
            	作者：22311
            	时间：2017年11月9日06:52:47
            	描述：广告部分
            -->
            <div>
				<img src="${pageContext.request.contextPath}/products/hao/2.jpg" width="100%"/>
			</div>
			<!--
            	作者：22311
            	时间：2017年11月9日07:04:42
            	描述：商品显示
            -->
			<div class="container" id="newestShop">
				<div class="col-md-12"  style="background-color:#e77005;color:white;margin-bottom:2px">
					<h2 style="font-size:30px;margin-top:20px;margin-bottom:10px;color:white;">最新商品&nbsp;&nbsp;</h2>
				</div>
				<div class="col-md-2" style="border-right:0;padding:0;">
					<img src="${pageContext.request.contextPath}/products/hao/5.jpeg" width="194px" height="400px" style="display: inline-block;"/>
				</div>
				<div class="col-md-10" style="background-color:#FFF;padding:0">
					<div class="col-md-6" style="text-align:center;height:200px;padding:0px;">
						<a href="javasript:void(0)">
							<img src="${pageContext.request.contextPath}/products/hao/10.jpeg" width="99%" height="200px" style="display: inline-block;">
						</a>
					</div>
				
					<span class="spilitRow"></span>
					<span class="spilitCol" style="left:66.66%"></span> <!-- 640px -->
					<span class="spilitCol" style="left:83.33%"></span> <!-- 796.95px -->
				<c:forEach items="${newProducts }" var="p">
					<div class="col-md-2" style="text-align:center;height:198px;padding:10px 0px;margin:1px 0"">
						<a href="${pageContext.request.contextPath }/product_getById?pid=${p.pid}">
							<img class="moveableImg" src="${pageContext.request.contextPath}/${p.pimage}" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="${pageContext.request.contextPath }/product_getById?pid=${p.pid}" style='color:#666'>${fn:substring(p.pname,0,10) }...</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;${p.shop_price }</font></p>
					<span class="border_top"></span>
                    <span class="border_left"></span>
                    <span class="border_right"></span>
                    <span class="border_bottom"></span>
					</div>
				</c:forEach>

				</div>
			</div>			
			<!--
            	作者：22311
            	时间：2017年11月9日07:05:08
            	描述：页脚部分
            -->
			<div>
				<%@ include file="foot.jsp" %>
			</div>
			
						<div id="scroll">
      
    			<div id="toTop" class="scrollItem">
       			 回到顶部
    			</div>
  
   				 <div id="toBottom" class="scrollItem">
        		回到底部
			    </div>
			  

			</div>   
	</body>

</html>