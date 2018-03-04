<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
</head>
<body>
<table width="100"  cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%"=>
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
	
		d = new dTree('d');
		d.add('01',-1,'系统菜单树');
		d.add('0102','01','分类管理','','','mainFrame');
		d.add('010201','0102','展示所有','${pageContext.request.contextPath}/adminCategory?method=findAll','','mainFrame');
		d.add('010202','0102','添加分类','${pageContext.request.contextPath}/adminCategory?method=addUI','','mainFrame');
		d.add('0104','01','商品管理');
		d.add('010401','0104','商品管理','${pageContext.request.contextPath}/adminProduct?method=findAll','','mainFrame');
		d.add('0105','01','订单管理');
		d.add('010501','0105','所有订单','${pageContext.request.contextPath}/adminOrder?method=findAllByState','','mainFrame');
		d.add('010502','0105','待付款订单','${pageContext.request.contextPath}/adminOrder?method=findAllByState&state=0','','mainFrame');
		d.add('010503','0105','已支付订单','${pageContext.request.contextPath}/adminOrder?method=findAllByState&state=1','','mainFrame');
		d.add('010504','0105','已发货订单','${pageContext.request.contextPath}/adminOrder?method=findAllByState&state=2','','mainFrame');
		d.add('010505','0105','已完成订单','${pageContext.request.contextPath}/adminOrder?method=findAllByState&state=3','','mainFrame');
		document.write(d);
		
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
