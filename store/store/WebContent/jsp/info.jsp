<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>WEB01</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
	</head>

	<body>
		<div class="container-fluid">

		<!--
			菜单栏 导航条
			动态包含 
		-->
		<jsp:include page="/jsp/head.jsp"></jsp:include>

			<div class="container-fluid">
				<div class="main_con">
					<h2>公司简介</h2>
					<hr/>
					<div>
						<p>
							<font color="red">“中关村黑马程序员训练营”</font>是由传智播客联合中关村软件园、CSDN，并委托传智播客进行教学实施的软件开发高端培训机构，致力于服务各大软件企业，解决当前软件开发技术飞速发展，而企业招不到优秀人才的困扰。 目前，“中关村黑马程序员训练营”已成长为行业“学员质量好、课程内容深、企业满意”的移动开发高端训练基地，并被评为中关村软件园重点扶持人才企业。
						</p>

						<p>
							黑马程序员的学员多为大学毕业后，有理想、有梦想，想从事IT行业，而没有环境和机遇改变自己命运的年轻人。黑马程序员的学员筛选制度，远比现在90%以上的企业招聘流程更为严格。任何一名学员想成功入学“黑马程序员”，必须经历长达2个月的面试流程，这些流程中不仅包括严格的技术测试、自学能力测试，还包括性格测试、压力测试、品德测试等等测试。毫不夸张地说，黑马程序员训练营所有学员都是精挑细选出来的。百里挑一的残酷筛选制度确保学员质量，并降低企业的用人风险。
						</p>

						<p>
							中关村黑马程序员训练营不仅着重培养学员的基础理论知识，更注重培养项目实施管理能力，并密切关注技术革新，不断引入先进的技术，研发更新技术课程，确保学员进入企业后不仅能独立从事开发工作，更能给企业带来新的技术体系和理念。
						</p>

						<p>
							一直以来，黑马程序员以技术视角关注IT产业发展，以深度分享推进产业技术成长，致力于弘扬技术创新，倡导分享、 开放和协作，努力打造高质量的IT人才服务平台。
						</p>
					</div>
				</div>
			</div>

		</div>
		<div class="container-fluid">
			<div style="margin-top:50px;">
				<img src="${pageContext.request.contextPath}/img/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
			</div>
	
			<div style="text-align: center;margin-top: 5px;">
				<ul class="list-inline">
					<li><a href="info.html">关于我们</a></li>
					<li><a>联系我们</a></li>
					<li><a>招贤纳士</a></li>
					<li><a>法律声明</a></li>
					<li><a>友情链接</a></li>
					<li><a>支付方式</a></li>
					<li><a>配送方式</a></li>
					<li><a>服务声明</a></li>
					<li><a>广告声明</a></li>
				</ul>
			</div>
			<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
				Copyright &copy; 2017-? web商城 版权所有
			</div>
		</div>
	</body>

</html>