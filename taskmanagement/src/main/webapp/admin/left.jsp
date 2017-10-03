<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
</head>
<body>
<table style="width: 100%;" >
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table style="width: 100%;">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
	
		d = new dTree('d');
		d.add('01',-1,'系统菜单树');
		
		d.add('0102','01','用户管理','','','mainFrame');
		d.add('010201','0102','所有用户','${pageContext.request.contextPath}/adminUser?method=getUsers&currPage=1&stuid=&name=&category=','','mainFrame');
		
		d.add('0103','01','任务管理');
		d.add('010301','0103','所有任务','${pageContext.request.contextPath}/adminTask?method=getTasks&currPage=1&category=&desc=','','mainFrame');
		
		d.add('0105','01','任务记录管理');
		d.add('010501','0105','所有记录','${pageContext.request.contextPath}/adminApply?method=findApplys&currPage=1&begin=&end=&username=','','mainFrame');
		d.add('010502','0105','任务进行中','${pageContext.request.contextPath}/adminApply?method=findApplys&currPage=1&begin=&end=&username=','','mainFrame');
		d.add('010503','0105','任务审核中','${pageContext.request.contextPath}/adminApply?method=findApplys&currPage=1&begin=&end=&username=','','mainFrame');
		d.add('010504','0105','任务已完成','${pageContext.request.contextPath}/adminApply?method=findApplys&currPage=1&begin=&end=&username=','','mainFrame');
		d.add('010505','0105','任务失败','${pageContext.request.contextPath}/adminApply?method=findApplys&currPage=1&begin=&end=&username=','','mainFrame');
		document.write(d);
	</script>
</div>	
	</td>
  </tr>
</table>
</body>
</html>
