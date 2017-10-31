<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
					<caption style="text-align: center;">
						<form action="${pageContext.request.contextPath }/task?method=getTasks&currPage=1" method="post">
							任务类型：<input name="category" id="category" value="${category }" />&nbsp;&nbsp;
							任务内容：<input name="desc" id="desc" value="${desc }" />&nbsp;&nbsp;
							任务状态：<select name="state" id="state" >
										<option value="" selected>未选择</option>
										<option value="0">未领取</option>
										<option value="1">进行中</option>
										<option value="2">审核中</option>
										<option value="3">已完成</option>
										<option value="4">失败</option>
									</select>
							<input type="submit" value="查询" style="margin-left: 20px;"/>
						</form>
					</caption>
					<thead>
						<tr>
							<th style="width: 10%; text-align: center;">序号</th>
							<th style="width: 15%; text-align: center;">任务类型</th>
							<th style="width: 35%; text-align: center;">任务内容</th>
							<th style="width: 25%; text-align: center;">发布时间</th>
							<th style="width: 15%; text-align: center;">任务状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageBean.list }" var="item" varStatus="i">
							<tr
								<c:if test="${i.count%2!=0 }">class="warning" </c:if>
								<c:if test="${i.count%2==0 }">class="success" </c:if>
							>
								<td>${i.count+pageBean.pageSize*(pageBean.currPage-1) }</td>
								<td>${item.category }</td>
								<td>
									<a title="查看详情" style="cursor: pointer;color:black;" onclick="alert('${item.desc}');" >${fn:substring(item.desc, 0, 20)}...</a>
								</td>
								<td>
									<jsp:useBean id="dateValue" class="java.util.Date"/> <!-- 通过jsp:userBean标签引入java.util.Date日期类 -->
									<jsp:setProperty name="dateValue" property="time" value="${item.create_at}"/> <!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->
									<fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm:ss"/> <!-- 转换格式 -->
								</td>
								<td>
									<c:if test="${item.state == 0 }"><a title="点击领取任务" onclick="chageStateTo(1, '${user.uid}', '${item.tid}')" style="cursor: pointer" >未领取</a></c:if>
									<c:if test="${item.state == 1 }">
										<a title="点击提交任务" onclick="chageStateTo(2, '${user.uid}', '${item.tid}')" style="cursor: pointer;color: yellowgreen">进行中</a>
										<a title="点击放弃任务,将视为失败" onclick="chageStateTo(4, '${user.uid}', '${item.tid}')" style="cursor: pointer;color: brown">(放弃)</a>
									</c:if>
									<c:if test="${item.state == 2 }"><a title="等待管理员审核..." href="javascript:void(0)" style="cursor: pointer;color: deepskyblue;">审核中</a></c:if>
									<c:if test="${item.state == 3 }"><a title="您已完成任务" href="javascript:void(0)" style="cursor: pointer;color: springgreen">已完成</a></c:if>
									<c:if test="${item.state == 4 }"><a title="放弃的任务或被管理员否决的任务，点击重新领取任务" onclick="chageStateTo(1, '${user.uid}', '${item.tid}')" style="cursor: pointer;color: red">失败</a></c:if>
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
		    $('#menu_1').addClass("active");
		};


		function showByPage(currPage) {
			var category = document.getElementById("category");
			var desc = document.getElementById("desc");
			var state = document.getElementById("state");
			
			location.href="${pageContext.request.contextPath }/task?method=getTasks&currPage="+currPage+
					"&category="+encodeURI(encodeURI(category.value))+"&desc="+encodeURI(encodeURI(desc.value))+
					"&state="+state.value;
		}

        function chageStateTo(state, uid, tid){ //改变用户状态
			var arr = new Array(["", "领取", "提交", "", "放弃"]);
			arr[0] = "";
			arr[1] = "确认领取吗？\n提示：任务一旦被领取，必须被完成，否则视为失败的任务。";
			arr[2] = "确认提交吗？\n提示：任务提交后须等待管理员审核。"
			arr[3] = "";
			arr[4] = "确认放弃吗？\n提示：任务一旦被放弃，将视为失败的任务。";
			if (confirm([arr[state]])) {
            	location.href="${pageContext.request.contextPath}/record?method=update&state="+
					state+"&uid="+uid+"&tid="+tid;
			}
        }
	</script>
</html>