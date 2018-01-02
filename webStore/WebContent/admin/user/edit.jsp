<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="layer/layer.js" ></script>
	</HEAD>
	<body>
		<br>
		<form id="productForm" name="productForm" action="${pageContext.request.contextPath}/adminUser_update.action" method="post">
			<table style="width: 100%;text-align: center;">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>修改用户信息</strong>
						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
									<td align="center" width="5%">序号</td>
									<td align="center" width="20%">用户名</td>
									<td align="center" width="17%">姓名</td>
									<td align="center" width="25%">email</td>
									<td align="center" width="10%">状态</td>
									<td align="center" width="17%">注册时间</td>
									<td align="center" width="3%">编辑</td>
								</tr>
								<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 27px" align="center">
										<input type="hidden" name="uid" value="${user.uid }" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 27px" align="center">
										${user.username }
									</td>
									<td style="CURSOR: hand; HEIGHT: 27px" align="center">
										<input type="text" name="name" value="${user.name }" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 27px" align="center">
										<input type="text" name="email" value="${user.email }" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 27px" align="center">
										<c:if test="${user.state==1 }">正常状态</c:if>
										<c:if test="${user.state==0 }">封停状态</c:if>
									</td>
									<td style="CURSOR: hand; HEIGHT: 27px" align="center">
										<!-- 通过jsp:userBean标签引入java.util.Date日期类 -->
										<jsp:useBean id="dateValue" class="java.util.Date"/> 
										<!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->
										<jsp:setProperty name="dateValue" property="time" value="${user.create_at }"/> 
										<!-- 转换格式 -->
										<fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm:ss"/> 
									</td>
									<td align="center" style="HEIGHT: 27px"></td>
								</tr>
								<tr>
									<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="7">
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

