<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon"  type="images/x-icon"  href="${pageContext.request.contextPath}/images/icon.ico">
		<link rel="Bookmark"  type="images/x-icon" href="${pageContext.request.contextPath}/images/icon.ico">
		<title>会员登录</title>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
#moduleId font {
    color: #666;
    font-size: 22px;
    font-weight: normal;
    padding-right:17px;
}
 </style>
 
 <script type="text/javascript">
 	
 	// 自动登录
	function autoLoginSelectionCheck(){
		document.getElementById('rememberUserName').checked = document.getElementById('autoLogin').checked;
 	}
 	
 	// 记住用户名
 	function rememberUserNameSelectionCheck(){
 		if(document.getElementById('rememberUserName').checked == false)
			document.getElementById('autoLogin').checked =false;
 	}
 	
 	// 提交时检查表单
 	function checkForm() {
 		
 		if ($("#username").val().trim() == "" || 
 			$("#code").val().trim() == "") {
 			return false;
 		} else {
 			return true;
 		}
 	}
 	
 	// 更换验证码
    function changeCode() {
        //操作src属性
        $("#codeImg").attr("src", "${pageContext.request.contextPath}/user_createCode?i=" + Math.random());
        //传一个变化的参数使区别每次提交的请求发生变化！！！
    }
 	
 </script>
 
</head>
<body style="background-color:#f6f6f6;">
	<!--
		菜单栏 导航条
		动态包含 
	-->
	<jsp:include page="/jsp/head.jsp"></jsp:include>
	
<div class="container"  style="width:100%;height:460px;background:#FF2C4C url('${pageContext.request.contextPath}/images/loginbg.jpg') no-repeat;">
	<div class="row"> 
		<div class="col-md-7">
			<!--<img src="${pageContext.request.contextPath}/images/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
		</div>
		
		<div class="col-md-5">
			<div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;" id="moduleId">
				<font>会员登录</font>USER LOGIN <br />
				<font style="font-size: 13px;font-weight: 600;color: red;position: relative;left: 20%">${message }</font>
				<!-- <div>&nbsp;</div> -->
				<form class="form-horizontal" method="post" action="${pageContext.request.contextPath }/user_login" onsubmit="return checkForm()">
				 	<div class="form-group">
					    <label for="username" class="col-sm-2 control-label" >用户名</label>
					    <div class="col-sm-6">
					    <%
					    	String save_username = "";
					    	Cookie c = team.webstore.utils.CookieUtils.getCookieByName("save_username", request.getCookies());
					    	save_username = 
					    			c!=null ? java.net.URLDecoder.decode(c.getValue(), "utf-8") : "";
					    %>
					      <input type="text" class="form-control" maxlength="20" id="username" placeholder="请输入用户名" title="请输入用户名" name="username" value="<%=save_username %>" required><!-- ${cookie.save_username.value} -->
					    </div>
				  	</div>
			   		<div class="form-group">
			    		<label for="password" class="col-sm-2 control-label">密码</label>
			    		<div class="col-sm-6">
			      			<input type="password" class="form-control" maxlength="20" id="password" placeholder="请输入密码" title="请输入密码" name="password" value="${cookie.save_password.value}" required>
			    		</div>
			  		</div>
			   		<div class="form-group">
			       		<label for="code" class="col-sm-2 control-label">验证码</label>
					    <div class="col-sm-3">
					      <input type="text" class="form-control" maxlength="7" id="code" name="code" placeholder="请输入..." title="请输入验证码" required>
					    </div>
					    <div class="col-sm-3">
					      <img src="${pageContext.request.contextPath}/user_createCode" onclick="changeCode();" id="codeImg" title="眼花了？点击换一张吧~" alt="我是验证码" style="cursor: pointer;" />
					    </div>
			  		</div>
				   <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <div class="checkbox">
				        <label>
				          <input type="checkbox" id="autoLogin" name="autoLogin" onChange="autoLoginSelectionCheck()" <c:if test="${not empty cookie.save_password.value }">checked="checked"</c:if>> 记住密码
				        </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        <label>
				          <input type="checkbox" id="rememberUserName" name="rememberUserName" onChange="rememberUserNameSelectionCheck()" <c:if test="${not empty cookie.save_username.value }">checked="checked"</c:if>> 记住用户名
				        </label>
				      </div>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				    <input type="submit"  width="100" value="登录" name="submit"
				    style="background: url('${pageContext.request.contextPath}/images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;">
				    <a href="${pageContext.request.contextPath }/jsp/authentication.jsp" style="position:relative;left:5px;">找回密码</a>
				    </div>
				  </div>
				</form>
			</div>			
		</div>
	</div>
</div>	

<!-- 页脚部分 -->
			<div>
				<%@ include file="foot.jsp" %>
			</div>
</body>
</html>