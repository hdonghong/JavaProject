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
			function showByPage(currPage) {
				var category = document.getElementById("category");
				var desc = document.getElementById("desc");
				
				location.href="${pageContext.request.contextPath }/adminTask?method=getTasks&currPage="+currPage+
						"&category="+category.value+"&desc="+desc.value;
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
							<strong>账单列表</strong>
						</td>
					</tr>
					<tr>
						<td class="ta_01">
							任务类型：<input type="text" id="category" name="category" value="${category }" style="margin-right: 20px;" >
							任务内容：<input type="text" id="desc" name="desc" value="${desc }" style="margin-right: 20px;" >
							<input type="button" onclick="showByPage(1)" value="查询" />
							<a href="${ pageContext.request.contextPath }/adminTask?method=addUI" >
								<input type="button" value="发布任务" style="float: right;"/>
							</a>
							<label style="float: right;margin-right: 10px;margin-top: 5px;">当前任务数量：${pageBean.totalCount}</label>
						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
									<th align="center" width="5%">
										序号
									</th>
									<th align="center" width="15%">
										任务类型
									</th>
									<th align="center">
										任务内容
									</th>
									<th align="center" width="15%">
										发布时间
									</th>
									<th align="center" width="15%">
										修改时间
									</th>
									<th align="center" width="6%" colspan="2">
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
										${item.category }
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										${item.desc }
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<jsp:useBean id="dateValue_1" class="java.util.Date"/> <!-- 通过jsp:userBean标签引入java.util.Date日期类 -->
										<jsp:setProperty name="dateValue_1" property="time" value="${item.create_at}"/> <!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->
										<fmt:formatDate value="${dateValue_1}" pattern="yyyy-MM-dd HH:mm:ss"/> <!-- 转换格式 -->
									</td>
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<c:if test="${not empty item.update_at}">
											<jsp:useBean id="dateValue_2" class="java.util.Date"/> <!-- 通过jsp:userBean标签引入java.util.Date日期类 -->
											<jsp:setProperty name="dateValue_2" property="time" value="${item.update_at}"/> <!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->
											<fmt:formatDate value="${dateValue_2}" pattern="yyyy-MM-dd HH:mm:ss"/> <!-- 转换格式 -->
										</c:if>
									</td>
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<a href="${ pageContext.request.contextPath }/adminTask?method=getByTid&tid=${item.tid}">
											<img src="${pageContext.request.contextPath}/images/i_edit.gif" title="编辑任务" alt="编辑" style="CURSOR: hand">
										</a>
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<a href="${ pageContext.request.contextPath }/adminTask?method=delete&tid=${item.tid}" >
											<img src="${pageContext.request.contextPath}/images/i_del.gif" title="删除任务" alt="删除" style="CURSOR: hand">
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

