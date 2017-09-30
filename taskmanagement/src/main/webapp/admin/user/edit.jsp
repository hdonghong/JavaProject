<%@page import="team.xg2.domain.User"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
		<script type="text/javascript">
		    function chageStateTo(state){ //改变用户状态
		    	var td = document.getElementById("tdId");
				td.innerHTML = "";
				if (state == 1) { //改为锁定状态
					td.innerHTML = "<a title='解锁' onclick='chageStateTo(0)' style='color: red;' >锁定状态</a>";				
				} else if (state == 0) {
					td.innerHTML = "<a title='锁定' onclick='chageStateTo(1)' >正常状态</a>";
				}
				td.innerHTML += "<input type='hidden' name='state' value="+state+" />";
		    }
		</script>
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/adminUser?method=edit" method="post">
			<table style="width: 100%;text-align: center;">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>编辑用户</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table border="1" id="DataGrid1" style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
									<th align="center" width="5%">
										序号
									</th>
									<th align="center" width="10%">
										账号
									</th>
									<th align="center" width="10%">
										密码
									</th>
									<th align="center" width="10%">
										姓名
									</th>
									<th align="center" width="10%">
										Email
									</th>
									<th align="center" width="10%">
										出生日期
									</th>
									<th align="center" width="5%">
										性别
									</th>
									<th align="center" width="10%">
										余额
									</th>
									<th align="center" >
										创建时间
									</th>
									<th align="center" width="5%">
										状态
									</th>
									<th align="center" width="10%" colspan="2">
										操作
									</th>
									
								</tr>
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<input type="hidden" name="uid" value="${user.uid }" size="5" >
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<input name="username" type="text" value="${user.username }" size="15" required="required" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<input name="password" type="text" value="${user.password }" size="15" required="required" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<input name="name" type="text" value="${user.name }" size="15" required="required" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<input name="email" type="email" value="${user.email }" size="15" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<input name="birthday" type="date" value="${user.birthday }" style="width:130" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<input name="sex" type="text" value="${user.sex }" size="5" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<input name="balance" type="text" value="${user.balance }" size="5" />元
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<fmt:formatDate var="time" value="${user.time }" pattern="yyyy-MM-dd HH:mm:ss"/>${time }
										<input type="hidden" name="time" value="${user.time }" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" id="tdId" >
										<%
											int state = ((User)request.getAttribute("user")).getState();
											if (state == 0) {
										%>
												<a title="锁定" onclick="chageStateTo(1)" >正常状态</a>
										<%
											} else if (state == 1) {
										%>
												<a title="解锁" onclick="chageStateTo(0)" style="color: red" >锁定状态</a>
										<%
											} else if (state == 2) {
										%>
												<a title="解锁" onclick="chageStateTo(0)" style="color: green" >申请解锁</a>
										<%
											}
										%>
										<input type="hidden" name="state" value="${user.state }" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<a href="javascript:void(0)" >
											<img src="${pageContext.request.contextPath}/images/i_edit.gif" title="编辑" alt="编辑" style="CURSOR: hand">
										</a>
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<a href="javascript:void(0)" >
											<img src="${pageContext.request.contextPath}/images/i_del.gif" title="注销" alt="注销" style="CURSOR: hand">
										</a>
									</td>
								</tr>
								<tr>
									<td class="ta_01" style="WIDTH: 100%" align="center"
										bgColor="#f5fafe" colSpan="12">
										<button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
											&#30830;&#23450;
										</button>
				
										<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
										<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>
				
										<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
										<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
										<span id="Label1"></span>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

