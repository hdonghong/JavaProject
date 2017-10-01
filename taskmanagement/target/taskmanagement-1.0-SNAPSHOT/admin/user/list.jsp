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
			function showByPage(currPage) { //提交查询条件和当前页码
				var begin = document.getElementById("begin");
				var end = document.getElementById("end");
				var username = document.getElementById("username");
				
				location.href="${pageContext.request.contextPath }/adminUser?method=findUsers&currPage="+currPage+
						"&begin="+begin.value+"&end="+end.value+"&username="+username.value+"&state=${state}";
			}
		    function chageStateTo(state, uid, adesc){ //改变用户状态
		    	location.href="${pageContext.request.contextPath}/adminApply?method=add&state="+state+"&uid="+uid+"&adesc="+adesc;
		    }
		</script>
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table style="width: 100%;text-align: center;">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>用户列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01">
							查询日期 起：<input type="date" id="begin" name="begin" value="${begin }" >
							至：<input type="date" id="end" name="end" value="${end }" >
							用户名：<input name="username" id="username" value="${username }" />
							<input type="button" onclick="showByPage(1)" value="查询" /></td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
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
								<c:forEach items="${pageBean.list }" var="item" varStatus="i" >
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										${i.count+(pageBean.currPage-1)*pageBean.pageSize }
									</td>
									<td style="CURSOR: hand; HEIGHT: 25px" align="center" >
										${item.username }
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										${item.password }
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										${item.name }
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										${item.email }
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										${item.birthday }
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										${item.sex }
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										${item.balance }元
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<fmt:formatDate var="time" value="${item.time }" pattern="yyyy-MM-dd HH:mm:ss"/>${time }
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<c:if test="${item.state==0 }"> <a title="锁定" onclick="chageStateTo(1, '${item.uid }', '管理员锁定该用户')" >正常状态</a> </c:if>
										<c:if test="${item.state==1 }"> <a title="解锁" onclick="chageStateTo(0, '${item.uid }', '管理员解锁该用户')" style="color: red" onclick="test()" >锁定状态</a> </c:if>
										<c:if test="${item.state==2 }"> <a title="解锁" onclick="chageStateTo(0, '${item.uid }', '管理员解锁该用户')" style="color: green" onclick="chageState(1, '${item.uid }')" >申请解锁</a> </c:if>
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<a href="${ pageContext.request.contextPath }/adminUser?method=findByUid&uid=${item.uid}">
											<img src="${pageContext.request.contextPath}/images/i_edit.gif" title="编辑" alt="编辑" style="CURSOR: hand">
										</a>
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<a href="${ pageContext.request.contextPath }/adminUser?method=delete&uid=${item.uid}" >
											<img src="${pageContext.request.contextPath}/images/i_del.gif" title="注销" alt="注销" style="CURSOR: hand">
										</a>
									</td>
								</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
					<tr align="center">
						<td colspan="12">
							<c:if test="${pageBean.totalPage > 0 }">
								 第${pageBean.currPage }/${pageBean.totalPage }页<br/>
								<c:if test="${pageBean.currPage == 1 }">
									<a href="javascript:void(0)" >首页</a>
									<a href="javascript:void(0)" >上一页</a>
								</c:if>
								<c:if test="${pageBean.currPage != 1 }">
									<a href="javascript:void(0)" onclick="showByPage(1)" >首页</a>
									<a href="javascript:void(0)" onclick="showByPage('${pageBean.currPage-1 }')" >上一页</a>
								</c:if>
								<c:if test="${pageBean.currPage == pageBean.totalPage }">
									<a href="javascript:void(0)" >下一页</a>
									<a href="javascript:void(0)" >尾页</a>
								</c:if>
								<c:if test="${pageBean.currPage != pageBean.totalPage }">
									<a href="javascript:void(0)" onclick="showByPage('${pageBean.currPage+1 }')" >下一页</a>
									<a href="javascript:void(0)" onclick="showByPage('${pageBean.totalPage }')" >尾页</a>
								</c:if>
							</c:if>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

