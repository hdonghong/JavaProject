<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				账单表
			 -->
			<div class="container-fluid"> <!--<div class="table-responsive">-->
				<table class="table table-hover" style="width: 60%; margin: 0 auto; text-align: center;">
					<caption style="text-align: center;">
						<form action="${pageContext.request.contextPath }/bill?method=findBills&currPage=1" method="post">
							查询日期 起：<input type="date" id="begin" name="begin" value="${begin }" >&nbsp;&nbsp;
							至：<input type="date" id="end" name="end" value="${end }" >&nbsp;&nbsp;
							关键词：<input name="condition" id="condition" value="${condition }" />&nbsp;&nbsp;
							<input type="submit" style="margin-right: 50px;"/>
							<label>余额：${user.balance }元</label>
						</form>
					</caption>
					<thead>
						<tr>
							<th style="width: 10%; text-align: center;">序号</th>
							<th style="width: 40%; text-align: center;">描述</th>
							<th style="width: 25%; text-align: center;">金额</th>
							<th style="width: 25%; text-align: center;">时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageBean.list }" var="item" varStatus="i">
							<c:if test="${i.count%2!=0 }">
								<tr class="warning">
									<td>${i.count+pageBean.pageSize*(pageBean.currPage-1) }</td>
									<td>${item.bdesc }</td>
									<td>${item.money }元</td>
									<td><fmt:formatDate var="time" value="${item.time }" pattern="yyyy-MM-dd HH:mm:ss"/>${time }</td>
								</tr>
							</c:if>
							<c:if test="${i.count%2==0 }">
								<tr class="success">
									<td>${i.count+pageBean.pageSize*(pageBean.currPage-1) }</td>
									<td>${item.bdesc }</td>
									<td>${item.money }元</td>
									<td><fmt:formatDate var="time" value="${item.time }" pattern="yyyy-MM-dd HH:mm:ss"/>${time }</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
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
					Copyright &copy; 2017-? 校园卡查询系统 版权所有
				</div>
			</div>
			
		</div>
	</body>
	<script type="text/javascript">
		//将所选设为高亮
		onload = function(){
		    $('#menu_2').addClass("active");
		};
		
		function showByPage(currPage) {
			var begin = document.getElementById("begin");
			var end = document.getElementById("end");
			var condition = document.getElementById("condition");
			
			location.href="${pageContext.request.contextPath }/bill?method=findBills&currPage="+currPage+
					"&begin="+begin.value+"&end="+end.value+"&condition="+condition.value;
		}
		
	</script>
</html>