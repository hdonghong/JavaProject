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
<table width="100%">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
	
		d = new dTree('d');
		d.add('01',-1,'系统菜单树');
		d.add('0102','01','分类管理','','','mainFrame');
		d.add('010201','0102','展示分类','${pageContext.request.contextPath}/adminCategory_findAll.action','','mainFrame');
		d.add('010202','0102','添加分类','${pageContext.request.contextPath}/admin/category/add.jsp','','mainFrame');
		d.add('0103','01','商品管理');
		d.add('010301','0103','展示商品','${pageContext.request.contextPath}/adminProduct_findAll.action','','mainFrame');
		d.add('010302','0103','添加商品','${pageContext.request.contextPath}/admin/product/add.jsp','','mainFrame');
		d.add('0104','01','订单管理');
		d.add('010401','0104','展示订单','${pageContext.request.contextPath}/adminOrder_findAll.action','','mainFrame');
		d.add('0105','01','用户管理');
		d.add('010501','0105','展示用户','${pageContext.request.contextPath}/adminUser_findAll.action','','mainFrame');
		d.add('0106','01','销售汇总');
		d.add('010601','0106','分类汇总','${pageContext.request.contextPath}/adminCategory_getCollection.action','','mainFrame');
		d.add('010602','0106','商品汇总','${pageContext.request.contextPath}/adminProduct_getCollection.action','','mainFrame');
		document.write(d);
		
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
