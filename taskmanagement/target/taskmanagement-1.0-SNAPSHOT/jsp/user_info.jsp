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
	        	时间：2017-10-01 19:05:35
	        	描述：查看个人信息
	        -->

			<div class="container-fluid"  style="margin-bottom: 100px; padding-top: 50px;">
			<c:if test="${empty user }">
				<h1 align="center">请先登录</h1>
			</c:if>
			<c:if test="${not empty user }">
				<form action="${pageContext.request.contextPath }/user?method=update" method="post" >
					<input type="hidden" name="uid" value="${user.uid }" >
				<table class="table table-hover" style="width: 30%; margin: 0 auto; text-align: center;">			
					<tbody>
						<tr class="warning">
							<td style="font-weight: bold;">账号</td>
							<td>
								${user.stuid }
								<input type="hidden" name="stuid" value="${user.stuid }" />
							</td>
						</tr>
						<tr class="success">
							<td style="font-weight: bold;">密码</td>
							<td>
								<input type="password" style="text-align: center;width: 180px;" value="${user.password }" name="password" required="required">
							</td>
						</tr>
						<tr class="warning">
							<td style="font-weight: bold;">姓名</td>
							<td>
								<input style="text-align: center;width: 180px;" value="${user.name }" name="name" required="required">
							</td>
						</tr>
						<tr  class="success">
							<td style="font-weight: bold;">学院</td>
							<td>
								<input style="text-align: center;width: 180px;" value="${user.institute }" name="institute" required="required">
							</td>
						</tr>
						<tr class="warning">
							<td style="font-weight: bold;">专业</td>
							<td>
								<input style="text-align: center;width: 180px;" value="${user.major }" name="major" required="required">
							</td>
						</tr>
						<tr class="success">
							<td style="font-weight: bold;">年级</td>
							<td>
								<label class="radio-inline">
									<input type="radio" name="grade" id="inlineRadio1" value="1" checked="checked"> 大一
								</label>
								<label class="radio-inline">
									<input type="radio" name="grade" id="inlineRadio2" value="2"> 大二
								</label>
								<label class="radio-inline">
									<input type="radio" name="grade" id="inlineRadio3" value="3"> 大三
								</label>
								<label class="radio-inline">
									<input type="radio" name="grade" id="inlineRadio4" value="4"> 大四
								</label>
							</td>
						</tr>
						<tr  class="warning">
							<td style="font-weight: bold;">Email</td>
							<td>
								<input type="email" style="text-align: center;width: 180px;" value="${user.email }" name="email" required="required">
							</td>
						</tr>
						<tr class="success">
							<td style="font-weight: bold;">主攻方向</td>
							<td>
								<input style="text-align: center;width: 180px;" value="${user.category }" name="category" required="required">
							</td>
						</tr>

						<tr>
							<td>
								<input type="submit" value="保存"  class="btn btn-primary" />
							</td>
							<td>
								<input type="reset" value="取消"  class="btn btn-primary" />
							</td>
						</tr>
					</tbody>
				</table>
				</form>
			</c:if>
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
					Copyright &copy; 2017-? 任务管理系统 版权所有
				</div>
			</div>
			
		</div>
	</body>
	<script type="text/javascript">
		//将所选设为高亮
		onload = function(){
		    $('#menu_2').addClass("active");

		    var arr = document.getElementsByName("grade");
		    for (var i=0; i < arr.length; i++) {
		    	if (arr[i].value == ${user.grade}) {
		    		arr[i].checked = true;
		    		break;
		    	}
		    }
		};
	</script>
</html>
