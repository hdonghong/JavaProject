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
			/* 分页展示 */
            function showByPage(currPage) {
                var stuid = document.getElementById("stuid");
                var category = document.getElementById("category");
                var desc = document.getElementById("desc");
                var state = document.getElementById("state");

                location.href="${pageContext.request.contextPath }/adminRecord?method=getRecords&currPage="+currPage+
                    "&category="+category.value+"&desc="+desc.value+"&stuid="+stuid.value+"&state="+state.value;
            }
			/* 改变用户状态 */
            function chageStateTo(state, rid){
                location.href="${pageContext.request.contextPath}/adminRecord?method=update&state="+state+"&rid="+rid;
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
							<strong>任务记录</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01">
							学号：<input name="stuid" id="stuid" value="${stuid }" style="margin-right: 20px;" />
							任务类型：<input id="category" name="category" value="${category }" style="margin-right: 20px;" />
							任务内容：<input  id="desc" name="desc" value="${desc }" style="margin-right: 20px;" />
							<input type="hidden" id="state" value="${state}" />
							<input type="button" onclick="showByPage(1)" value="查询" />
							<label style="float: right;margin-right: 10px;margin-top: 5px;">当前记录数量：${pageBean.totalCount}</label>
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
									<th align="center" width="10%">
										账号
									</th>
									<th align="center" width="10%">
										任务类型
									</th>
									<th align="center" >
										任务内容
									</th>
									<th align="center" width="10%">
										任务状态
									</th>
									<th align="center" width="15%">
										执行时间
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
										${item.stuid }
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										${item.category }
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										${item.desc }
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<c:if test="${item.state==1 }"><a href="javascript:void(0)" style="color: yellowgreen;" title="任务进行中" >进行中</a></c:if>
										<c:if test="${item.state==2 }">
											<a style="color: deepskyblue;" title="点击通过任务" onclick="chageStateTo(3,'${item.rid}')">审核中</a>
											<a style="color: brown;" title="点击拒绝任务通过" onclick="chageStateTo(4,'${item.rid}')">(拒绝)</a>
										</c:if>
										<c:if test="${item.state==3 }"><a href="javascript:void(0)" style="color: springgreen;" title="任务已完成"  >已完成</a></c:if>
										<c:if test="${item.state==4 }"><a href="javascript:void(0)" style="color: red;" title="放弃的任务或管理员不通过">失败</a></c:if>
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<jsp:useBean id="dateValue" class="java.util.Date"/> <!-- 通过jsp:userBean标签引入java.util.Date日期类 -->
										<jsp:setProperty name="dateValue" property="time" value="${item.update_at}"/> <!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->
										<fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm:ss"/> <!-- 转换格式 -->
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<a href="javascript:void(0)">
											<img src="${pageContext.request.contextPath}/images/i_edit.gif" title="须拥有dba权限才能修改任务记录" alt="编辑" style="CURSOR: hand">
										</a>
									</td>
									<td style="CURSOR: hand; HEIGHT: 30px" align="center" >
										<a  href="javascript:void(0)" >
											<img src="${pageContext.request.contextPath}/images/i_del.gif" title="须拥有dba权限才能删除任务记录" alt="删除" style="CURSOR: hand">
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

