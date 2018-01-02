<%@page import="team.webstore.utils.CookieUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>商品页面</title>
		<!-- 引入自定义css文件 style.css -->
		<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" /> --%>
		<link rel="shortcut icon"  type="images/x-icon"  href="${pageContext.request.contextPath}/images/icon.ico">
		<link rel="Bookmark"  type="images/x-icon" href="${pageContext.request.contextPath}/images/icon.ico">

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
				width: 100%;
			}
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
			
			
/*边框hover动画*/
.warpper li span{
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
.warpper:hover .border_top, .warpper:hover .border_bottom{
  width: 100%;
}
.warpper:hover .border_left, .warpper:hover .border_right{
  height: 100%;
}

 #scroll {position:fixed; top:300px; right:50px;}
.scrollItem {font-size:11px;width:20px; height:70px;border:#e1e1e1 1px solid; cursor: pointer; text-align: center; padding-top: 10px;margin-bottom:2px;background:white;padding:0px;}

		</style>
		
		<script type="text/javascript">
			// 清空访问纪录
			function clearHistories() {
				$.get("${pageContext.request.contextPath}/product_clearHistories");
				$("#historyId ul").html("<li style='font-size: 30px;font-weight:800;color: #424242;cursor:default;float: left;'>暂无浏览记录</li>");
			}
			// 分页查询提交表单
			function to_page(page){
				if(page){
					$("#currPage").val(page);
				}
				document.productForm.submit();
				
			}
		</script>
	</head>

	<body style="background-color:#f6f6f6;">
	
		<!--
			菜单栏 导航条
			动态包含 
		-->
		<jsp:include page="/jsp/head.jsp"></jsp:include>


		<div class="row" style="width:80%;margin:0 auto;">
			<div class="col-md-12" >
				<ol class="breadcrumb" style="background-color: #E0E0E0;">
					<li><a href="${pageContext.request.contextPath }/">首页</a></li>
					<li>
						<a href="javascript:void(0)">
							<c:if test="${empty model.pdesc }">${page.beanList[0].category.cname}</c:if>
							<c:if test="${not empty model.pdesc }">查询"<font color="#ff6b05">${model.pdesc }</font>"的结果</c:if>
						</a>
					</li>
				</ol>
			</div>
			<c:forEach items="${page.beanList }" var="p">
				<div class="col-lg-2 col-md-4 col-sm-6" style="text-align:center;padding:5px;">
						<div class="warpper" style="background:white;padding:1px">
						
							<span class="border_top"></span>
		                    <span class="border_left"></span>
		                    <span class="border_right"></span>
		                    <span class="border_bottom"></span>
		                    
							<a href="${pageContext.request.contextPath}/product_getById?pid=${p.pid}">
								<img src="${pageContext.request.contextPath}/${p.pimage }" width="170" height="170" style="display: inline-block;margin-top:3px">
							</a>
							<p><a href="${pageContext.request.contextPath}/product_getById?pid=${p.pid}" style='color:green'>${fn:substring(p.pname,0,10) }...</a></p>
							<p><font color="#FF0000">商城价：&yen;${p.shop_price }</font></p>
						</div>
				</div>
			</c:forEach>
		</div>

		<!--分页 -->
		<form id="productForm" name="productForm" action="${pageContext.request.contextPath }/product_findByPage" method="post">
		<div>
			<input type="hidden" name="currPage" id="currPage" />
			<input type="hidden" name="category.cid" value="${model.category.cid }" />
			<input type="hidden" name="pdesc" value="${model.pdesc }" />
		</div>
		<div style="width:420px;margin:0 auto;margin-top:50px;text-align:center;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
			<c:if test="${not empty page}">
				<!-- 上一页,先判断当前页是否首页 -->
				<c:if test="${page.currPage==1 }">
					<li class="disabled"><a href="javascript:void(0)" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>			
				</c:if>
				<c:if test="${page.currPage!=1 }">
					<li>
						<a href="javascript:to_page('${page.currPage-1}')" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span></a>
					</li>			
				</c:if>
				
				<!-- 展示所有页码 -->
				<!-- 前五后四 -->
				<c:forEach begin="${page.currPage>5? page.currPage-5:1 }" end="${page.currPage+4>page.totalPage? page.totalPage: page.currPage+4}" var="i" step="1">
					<!-- 判断是否当前页 -->
					<c:if test="${page.currPage==i }">
						<li class="active"><a href="javascript:void(0)">${i }</a></li>
					</c:if>
					<c:if test="${page.currPage!=i }">
						<li>
							<a href="javascript:to_page('${i}')">${i }</a>
						</li>
					</c:if>
				</c:forEach>
				
				<!-- 下一页,先判断当前页是否尾页 -->
				<c:if test="${page.currPage==page.totalPage }">
					<li class="disabled"><a href="javascript:void(0)" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
				</c:if>
				<c:if test="${page.currPage!=page.totalPage }">
					<li>
						<a href="javascript:to_page('${page.currPage+1}')" aria-label="Next">
						<span aria-hidden="true">&raquo;</span></a></li>
				</c:if>
			</c:if>
			</ul>
		</div>
		</form>
		<!-- 分页结束=======================        -->

		<!--
       		商品浏览记录:
        -->
		<div style="width:80%;margin:0 auto; padding: 0 9px;border: 1px solid #ddd;border-top: 2px solid #999;height: 220px;">

			<label style="width: 50%;float: left;font-size: 18px;font-weight: 600;color: #757575;">浏览记录</label>
			<div style="width: 50%;float: right;text-align: right;"><a onclick="clearHistories()" style="cursor: pointer;" >清空</a></div>
		
			<div style="clear: both;"></div>

			<div style="overflow-x: auto;">
			<div id="historyId" style="width:1210px;">
				<ul style="list-style: none;">
				<%
					Cookie c = CookieUtils.getCookieByName("histories", request.getCookies());
					if (c == null) {
				%>
						<li style="font-size: 30px;font-weight:800;color: #424242;cursor:default;float: left;">暂无浏览记录</li>
				<%
					} else {
						String[] id_arr = c.getValue().split("-");
						for (String id : id_arr) {
				%>
							<li style="width: 150px;height: 216;float: left;margin: 0 8px 0 0;padding: 0 18px 15px;text-align: center;">
								<img src="${pageContext.request.contextPath}/<%=id %>" width="130px" height="130px" />
							</li>
				<%
						}
					}
				%>
				</ul>
			</div>
			</div>
		</div>
		
		<!-- 
			页脚
			时间：2017-12-12 08:28:10
		 -->
		<div>
			<%@ include file="foot.jsp" %>
		</div>

	</body>

</html>