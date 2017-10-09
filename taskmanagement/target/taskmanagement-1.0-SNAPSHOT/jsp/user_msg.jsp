<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>任务管理系统</title>
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
				账单表
			 -->
			<div class="container-fluid">
				<c:if test="${empty user }">
					<h1 align="center">请先登录</h1>
				</c:if>
				<c:if test="${not empty user }">
				<table class="table table-hover" style="width: 70%; margin: 0 auto; text-align: center;">
					<caption style="text-align: right;">
						<a href="${pageContext.request.contextPath}/record?method=haveReadAll">
							<button>全部置为已读</button>
						</a>
					</caption>
					<thead>
						<tr>
							<th style="width: 10%; text-align: center;">序号</th>
							<th style="text-align: center;">内容</th>
							<th style="width: 10%; text-align: center;">状态</th>
							<th style="width: 20%; text-align: center;">时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageBean.list }" var="item" varStatus="i">
							<tr
								<c:if test="${i.count%2!=0 }">class="warning" </c:if>
								<c:if test="${i.count%2==0 }">class="success" </c:if>
							>
								<td>${i.count+pageBean.pageSize*(pageBean.currPage-1) }</td>
								<td>
									<c:if test="${item.state==3 }">恭喜，管理员通过了您的任务！</c:if>
									<c:if test="${item.state==4 }">噢噢，管理员拒绝通过您的任务，再加把劲吧！</c:if>
								</td>
								<td>
									<c:if test="${item.is_read!=1 }"><a href="${pageContext.request.contextPath}/record?method=haveRead&rid=${item.rid}"
																		style="color: green;" >未读</a></c:if>
									<c:if test="${item.is_read==1 }">已读</c:if>
								</td>
								<td>
									<jsp:useBean id="dateValue" class="java.util.Date"/> <!-- 通过jsp:userBean标签引入java.util.Date日期类 -->
									<jsp:setProperty name="dateValue" property="time" value="${item.update_at}"/> <!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->
									<fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm:ss"/> <!-- 转换格式 -->
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</c:if>
			</div>
			
			<!--
            	时间：2017-08-22
            	描述：页脚
            -->
			<div class="container-fluid">		
				<div style="text-align: center;margin-top: 35px;">
					<!--分页 -->
				<c:if test="${pageBean.totalPage>0 }">
					<ul class="pagination" style="text-align:center; margin-top:10px;">
						<!-- 上一页 -->
						<c:if test="${pageBean.currPage == 1 }">
							<li class="disabled"><a href="javascript:void(0)" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
						</c:if>
						<c:if test="${pageBean.currPage != 1 }">
							<li><a href="javascript:void(0)" onclick="showByPage('${pageBean.currPage-1}')" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
						</c:if>
						
						<!-- 当前页 -->
						<c:forEach begin="1" end="${pageBean.totalPage }" var="i">
							<c:if test="${i == pageBean.currPage }">
								<li class="active"><a href="javascript:void(0)">${i }</a></li>
							</c:if>
							<c:if test="${i != pageBean.currPage }">
								<li><a href="javascript:void(0)" onclick="showByPage('${i}')">${i }</a></li>
							</c:if>
						</c:forEach>
						
						<!-- 下一页 -->
						<c:if test="${pageBean.currPage == pageBean.totalPage }">
							<li class="disabled"><a href="javascript:void(0)" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
						</c:if>
						<c:if test="${pageBean.currPage != pageBean.totalPage }">
							<li><a href="javascript:void(0)" onclick="showByPage('${pageBean.currPage+1}')" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
						</c:if>
					</ul>
				</c:if>
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
				<div style="text-align: center; margin-bottom:20px;">
					Copyright &copy; 2017-? 任务管理系统 版权所有
				</div>
			</div>
			
		</div>
	</body>
	<script type="text/javascript">
		//将所选设为高亮
		onload = function(){
		    $('#menu_3').addClass("active");
		};


		function showByPage(currPage) {
			location.href="${pageContext.request.contextPath }/record?method=getMessages&currPage="+currPage;
		}

	</script>
</html>