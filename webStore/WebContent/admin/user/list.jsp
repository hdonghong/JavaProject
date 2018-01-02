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
		<script type="text/javascript">
			// 提交表单
			function to_page(page){
				if(page){
					document.getElementById("currPage").value = page;
				}
				document.productForm.submit();
			}
			// 封停用户前二次确认
			function deleteUser(uid){
				if (confirm("你确定要封停该用户账号吗？")) {
					location.href="${pageContext.request.contextPath}/adminUser_delete?uid="+uid;
				}
			}
		</script>
		<style type="text/css">
			.change:hover{
				font-size: 13px;
				color: #ee7a2f;
			}
		</style>
	</HEAD>
	<body>
		<br>
		<form id="productForm" name="productForm" action="${pageContext.request.contextPath}/adminUser_findAll.action" method="post">
			<table style="width: 100%;text-align: center;">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>用户列表</strong>
						</td>
					</tr>
					<tr>
						<td class="ta_01" >
							<input type="hidden" name="currPage" id="currPage" />
							<label for="state">用户类型: </label>
							<select name="state" id="state">
								<option value="">-请选择-</option>
								<option value="1" <c:if test="${model.state==1 }">selected</c:if> >正常状态</option>
								<option value="0" <c:if test="${model.state==0 }">selected</c:if> >封停状态</option>
							</select>&nbsp;&nbsp;
							<label for="username">用户名: </label> <input name="username" id="username" value="${model.username }"></input>&nbsp;&nbsp;
							<label for="begin">注册时间起: </label><input type="date"  id="begin" name="begin" value="<fmt:formatDate value='${begin }' pattern='yyyy-MM-dd' />"/>&nbsp;&nbsp;
							<label for="end">至: </label><input type="date" name="end" id="end" value="<fmt:formatDate value='${end }' pattern='yyyy-MM-dd' />"/>&nbsp;&nbsp;
							<input type="submit" value="查询" ></input>
							<label style="float: right;">用户数量：${page.totalCount }&nbsp;&nbsp;</label>
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
								<c:forEach items="${page.beanList }" var="p" varStatus="vs">
									<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<td style="CURSOR: hand; HEIGHT: 27px" align="center">${vs.count+(page.currPage-1)*page.pageSize }</td>
										<td style="CURSOR: hand; HEIGHT: 27px" align="center">${p.username }</td>
										<td style="CURSOR: hand; HEIGHT: 27px" align="center">${p.name }</td>
										<td style="CURSOR: hand; HEIGHT: 27px" align="center">${p.email }</td>
										<td style="CURSOR: hand; HEIGHT: 27px" align="center">
											<c:if test="${p.state==1 }">
												<a class="change" href="${ pageContext.request.contextPath }/adminUser_update.action?uid=${p.uid}&state=0" title="封停此账号">正常状态</a>
											</c:if>
											<c:if test="${p.state==0 }">
												<a class="change" href="${ pageContext.request.contextPath }/adminUser_update.action?uid=${p.uid}&state=1" title="恢复此账号" style="color:red;">封停状态</a>
											</c:if>
										</td>
										<td style="CURSOR: hand; HEIGHT: 27px" align="center">
											<!-- 通过jsp:userBean标签引入java.util.Date日期类 -->
											<jsp:useBean id="dateValue" class="java.util.Date"/> 
											<!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->
											<jsp:setProperty name="dateValue" property="time" value="${p.create_at }"/> 
											<!-- 转换格式 -->
											<fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm:ss"/> 
										</td>
										<td align="center" style="HEIGHT: 27px">
											<a href="${ pageContext.request.contextPath }/adminUser_getById.action?uid=${p.uid}" title="修改用户信息">
												<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
											</a>
										</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
					<tr align="center">
						<td colspan="10">
							<c:if test="${page.totalPage > 0 }">
								 第${page.currPage }/${page.totalPage }页<br/>
								<c:if test="${page.currPage == 1 }">
									<a href="javascript:void(0)" >首页</a>
									<a href="javascript:void(0)" >上一页</a>
								</c:if>
								<c:if test="${page.currPage != 1 }">
									<a href="javascript:void(0)" onclick="to_page(1)" >首页</a>
									<a href="javascript:void(0)" onclick="to_page('${page.currPage-1 }')" >上一页</a>
								</c:if>
								<c:if test="${page.currPage == page.totalPage }">
									<a href="javascript:void(0)" >下一页</a>
									<a href="javascript:void(0)" >尾页</a>
								</c:if>
								<c:if test="${page.currPage != page.totalPage }">
									<a href="javascript:to_page('${page.currPage+1}')" >下一页</a>
									<a href="javascript:void(0)" onclick="to_page('${page.totalPage }')" >尾页</a>
								</c:if>
							</c:if>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

