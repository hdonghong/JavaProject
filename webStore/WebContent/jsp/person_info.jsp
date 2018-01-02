<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="${pageContext.request.contextPath }/css/bootstrap.min2.css" rel="stylesheet"/>
		<link rel="shortcut icon"  type="images/x-icon"  href="${pageContext.request.contextPath}/images/icon.ico">
		<link rel="Bookmark"  type="images/x-icon" href="${pageContext.request.contextPath}/images/icon.ico">
		<title>个人中心</title>
		
		<style type="text/css">
			form div>input{
				padding: 4px 6px;
			}
		</style>
		
		<script type="text/javascript">
			// 表单提交前检查表单
			function saveOrNot() {
				var name = $("#name").val().trim();
				var email = $("#email").val().trim();
				if (name == "" || email == "") {
					$("#btnSaveId").css("cursor","not-allowed");
					$("#btnSaveId").attr("title","请填写完整信息");
				} else {
					$("#btnSaveId").attr("title","");
					$("#btnSaveId").css("cursor","pointer");
				}
			}
			// 表单提交时检查表单
			function checkForm() {
				var name = $("#name").val().trim();
				var email = $("#email").val().trim();
				if (name == "" || email == "") {
					return false;
				}
			}
		</script>
	</head>

	<body style="background-color:#f6f6f6;">
		<!--
			菜单栏 导航条
			动态包含 
		-->
		<jsp:include page="/jsp/head.jsp"></jsp:include>
		
		<!-- <div class="container-fluid"> -->
		<div class="container" style="margin-top: 20px;">
			<div class="span9" style="margin:0 auto;float:none;">
				<div class="module">
					<div class="module-head">
						<h3 style="font-weight:800;">个人资料</h3>
					</div>
					<div class="module-body">
						<div class="alert alert-success" id="module_body_head"  style="padding: 8px 35px 8px 14px;margin-bottom: 0px;">
							<button type="button" class="close" data-dismiss="alert"
							 onclick="document.getElementById('module_body_head').style.display='none';">×</button>
							点击 "保存" 可以修改您的资料，点击 "取消" 重置您未保存的数据
						</div>
						<br />

						<form class="form-horizontal row-fluid" action="${pageContext.request.contextPath }/user_update.action" method="post" 
							onsubmit="return checkForm();" id="infoFormId">
						<!-- 主键 -->
							<input type="hidden" name="uid" value="${user.uid }" />
						<!-- 用户名输入框 -->
							<div class="control-group">
								<label class="control-label" for="username">用户名</label>
								<div class="controls">
									<input type="text" id="username" name="username" placeholder="请输入用户名" value="${user.username }" style="cursor:not-allowed;"
									class="span8" maxlength="20"  title="用户名一经注册，不可修改！" disabled>
									<!--<span class="help-inline"></span>-->
								</div>
							</div>
						<!-- 密码输入框 -->
							<div class="control-group">
								<label class="control-label" for="password">密码</label>
								<div class="controls">
									<input type="password" id="password" name="password" placeholder="********" class="span8" maxlength="20">
									<!-- <span class="help-inline" id="passwordMsg"></span> -->
								</div>
							</div>

						<!-- 姓名输入框 -->
							<div class="control-group">
								<label class="control-label" for="name">姓名</label>
								<div class="controls">
									<input type="text" id="name" name="name" placeholder="请输入您的真实姓名" value="${user.name }" class="span8" maxlength="20" required>
								</div>
							</div>
							
						<!-- email输入框 -->
							<div class="control-group">
								<label class="control-label" for="email">Email</label>
								<div class="controls">
									<input type="email" id="email" name="email" placeholder="请输入Email" value="${user.email }" class="span8" maxlength="30" required>
									<!-- <span class="help-inline" id="emailMsg"></span> -->
								</div>
							</div>

						<!--
							<div class="control-group">
								<label class="control-label" for="basicinput">Appended Input</label>
								<div class="controls">
									<div class="input-append">
										<input type="text" placeholder="5.000" class="span8"><span class="add-on">$</span>
									</div>
								</div>
							</div>
						-->

							<div class="control-group">
								<div class="controls">
									<button type="submit" class="btn" id="btnSaveId" onmouseover="saveOrNot()">保存</button>
									<button type="reset" class="btn" style="position: relative;left: 20px;" >取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>

			</div><!--/.content-->
		</div><!--/.span9-->

		<!-- 
			页脚
			时间：2017-12-12 08:28:10
		 -->
		<div>
			<%@ include file="foot.jsp" %>
		</div>

	</body>

</html>