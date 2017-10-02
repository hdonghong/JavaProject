<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

		<!--
        	时间：2017-09-30 21:06:27
        	描述：Logo图
        -->
		<div class="container-fluid">
			<div class="col-sm-10">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img src="${pageContext.request.contextPath }/img/logo.png" />
			</div>
			<div class="col-sm-2" style="padding-top:20px;" align="center">
				<ol class="list-inline">
				<c:if test="${empty user }">
					<li><a href="${pageContext.request.contextPath }/user?method=loginUI">登录</a></li>
					<li><a href="${pageContext.request.contextPath }/user?method=registUI">注册</a></li>
				</c:if>
				<c:if test="${not empty user }">
					<li>${user.name }，你好</li>
					<li><a href="${pageContext.request.contextPath }/user?method=logout">退出</a></li>
				</c:if>
				</ol>
			</div>
	<!--[if IE]>		
	<![endif]-->	
		</div>
		
		<!--
        	时间：2017-09-30 21:08:27
        	描述：导航条
        -->
		<div class="container-fluid">
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>					
					</div>
		
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav" id="menuId">
							<li id="menu_1"><a href="${pageContext.request.contextPath }/task?method=getTasks&currPage=1&category=&desc=&state=">任务列表</a></li>
							<li id="menu_2"><a href="${pageContext.request.contextPath }/jsp/user_info.jsp">个人信息</a></li>
							<%--
        					<li class="dropdown" id="menu_2">
					          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">个人信息<span class="caret"></span></a>
					          <ul class="dropdown-menu">
					            <li><a href="${pageContext.request.contextPath}/jsp/user_info.jsp">我的信息</a></li>
					            <li role="separator" class="divider"></li>
					            <li><a href="${pageContext.request.contextPath}/jsp/">我的任务</a></li>
					          </ul>
					        </li>
					        --%>

						</ul>
						<p class="navbar-text navbar-right" style="color: white;">
							<script type="text/JavaScript">
								tmpDate = new Date();
								date = tmpDate.getDate();
								month= tmpDate.getMonth() + 1 ;
								year= tmpDate.getYear();
								
								document.write(year+1900);
								document.write(" 年 ");
								document.write(month);
								document.write(" 月 ");
								document.write(date);
								document.write(" 日  ");
								
								myArray=new Array(6);
								myArray[0]="星期日"
								myArray[1]="星期一"
								myArray[2]="星期二"
								myArray[3]="星期三"
								myArray[4]="星期四"
								myArray[5]="星期五"
								myArray[6]="星期六"
								weekday=tmpDate.getDay();
								if (weekday==0 | weekday==6){
									document.write(myArray[weekday])
								} else{
									document.write(myArray[weekday])
								};

							</script>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</div>
