<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/left.css" media="all"/>

	<script language="javascript" src="${ctx}js/common.js"></script>
	<script language="javascript" src="${ctx}/js/ajax/setFastMenu.js"></script>
	<script language="javascript" src="${ctx}/js/pngfix_map.js"></script>
	<script type="text/javascript" src="${ctx}/components/jquery-ui/jquery-1.2.6.js"></script>
	<script type="text/javascript" src="${ctx}/skin/default/js/toggle.js"></script>
 
 
    
</head>
 
<body id="left_frame">
 
 
<!-- begin1  -->
<div id="sidebar" class="sidebar">
	<div class="sidebar_t">
		<div class="sidebar_t_l"></div>
		<div class="sidebar_t_c"></div>
		<div class="sidebar_t_r"></div>
	</div>
    <div class="panel">
    	<div class="panel_icon"><img src="${ctx}/skin/default/images/icon/document_chart.png" /></div>
        <div class="panel-header">
        <div class="panel-title">
		统计查询
        </div>
        
        <div class="panel-content">
			<%-- <ul>
				<li><a href="${ctx}/stat/statChartAction_factorySale" onclick="linkHighlighted(this)" target="main" id="aa_1">生产厂家销售情况</a></li>
				<li><a href="${ctx}/stat/statChartAction_productSale" onclick="linkHighlighted(this)" target="main" id="aa_1">产品销售排行</a></li>
				<li><a href="${ctx}/stat/statChartAction_onlineInfo" onclick="linkHighlighted(this)" target="main" id="aa_1">系统访问压力图</a></li>					
			</ul> --%>
			 <%@include file="../leftmenu.jsp" %> 
        </div>
    </div>
    </div>
    <div class="sidebar_t">
		<div class="sidebar_b_l"></div>
		<div class="sidebar_t_c"></div>
		<div class="sidebar_b_r"></div>
	</div>
</div>
<!-- end1 -->
 
</body>
</html>