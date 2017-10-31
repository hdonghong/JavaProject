<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    	<meta name="renderer" content="webkit">
		<title>任务管理系统—登录页面</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/font-awesome.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/login.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/form-elements.css">
	    <script src="${pageContext.request.contextPath }/js/jquery-1.11.0.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
	    <style type="text/css">
	        .warn {
	            font-size: 12px;
	            color: #DD4444;
	            display: inline-block;
	            padding: 10px 10px 0;
	        }
			a:hover{
				color: salmon;
				font-weight: 800;
				text-decoration: none;
			}
	    </style>
		<script type="text/javascript">
            function selectAll(obj) {
                document.getElementById("saveStuid").checked = obj.checked;
            }
		</script>
	</head>
	<body class="signin">
		<div class="signinpanel" style="position: relative;">
		    <div class="container">
		        <div class="row">
		            <div class="col-sm-12 text">
		                <h1 style="text-align: center;font-weight: 800">任务查询系统</h1>
		            </div>
		        </div>
		
		        <div class="row">
		            <div class="col-sm-6 col-sm-offset-3 form-box" style="display: none;">
		            </div>
		            <div class="col-sm-6 col-sm-offset-3 form-box">
		                <ul>
		                    <li class="con active">
		                        <div class="form-top">
		                            <div class="form-top-left">
		                                <h3>账户登录：</h3>
		                            </div>
		                        </div>
		                        <div class="form-bottom">
		                            <form id="signupForm" action="${pageContext.request.contextPath }/user?method=login" method="post">
		                            	<div style="color: #DD4444;text-align: center;font-size: 20px;">${msg }</div>
		                            	<div class="form-group">
		                                    <label class="sr-only" for="form-stuid">请输入账号</label>
		                                    <input type="text" name="stuid" required="required" aria-required="true" value="${cookie.saveStuid.value}" placeholder="请输入账号" class="form-username form-control" id="form-stuid">
		                                </div>
		                                <div class="form-group">
		                                    <label class="sr-only" for="form-password">请输入密码</label>
		                                    <input type="password" name="password" required="required" aria-required="true" value="${cookie.savePwd.value}" placeholder="请输入密码" class="form-password form-control" id="form-password">
		                                </div>
										<div style="color: black;" align="center">
											<input type="checkbox" style="position: relative;top: 3px;" name="save" id="saveStuid" <c:if test="${not empty cookie.saveStuid}">checked</c:if> /> <label for="saveStuid">记住账号</label>
											<input type="checkbox" style="position: relative;top: 3px;margin-left: 25%;" name="save" id="savePwd" <c:if test="${not empty cookie.savePwd}">checked</c:if> onclick="selectAll(this)"/> <label for="savePwd">记住密码</label>
										</div>
		                                <input type="submit" class="btn btn-success btn-block" value="登录">
										<div  style="float: left;color: #DD4444;">推荐您使用谷歌浏览器访问网站</div>
										<div style="float: right;">
											<a href="${pageContext.request.contextPath }/jsp/applyUpdatePwd.jsp">忘记密码？</a>
											<a href="${pageContext.request.contextPath }/user?method=registUI">注册新账号</a>
										</div>
		                            </form>
		                        </div>
		                    </li>
		                </ul>
		            </div>
		            <div class="col-sm-4">
		            </div>
		
		        </div>
		        <div class="row">
		            <div class="signup-footer col-sm-6 col-sm-offset-3 form-box">
		                <div style="margin-top: 20px; text-align: center;">© 2017-？任务管理系统 保留所有版权.</div>
		            </div>
		        </div>
		    </div>
		</div>
	</body>
</html>
