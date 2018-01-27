<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="id" value="${id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   浏览出口报运
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">报运号：</td>
	            <td class="tableContent">${customerContract}</td>
	            <td class="columnTitle">制单日期：</td>
	            <td class="tableContent">${inputDate}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">信用证号</td>
	            <td class="tableContent">${lcno}</td>
	            <td class="columnTitle">收货人及地址：</td>
	            <td class="tableContent">${consignee}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">装运港：</td>
	            <td class="tableContent">${shipmentPort}</td>
	            <td class="columnTitle">目的港：</td>
	            <td class="tableContent">${destinationPort}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">运输方式：</td>
	            <td class="tableContent">${transportMode}</td>
	            <td class="columnTitle">价格条件：</td>
	            <td class="tableContent">${priceCondition}</td>
	        </tr>
	        <tr>
	            <td class="columnTitle">唛头：</td>
	            <td class="tableContent"><pre>${marks}</pre></td>
	            <td class="columnTitle">备注：</td>
	            <td class="tableContent"><pre>${remark}</pre></td>
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>

