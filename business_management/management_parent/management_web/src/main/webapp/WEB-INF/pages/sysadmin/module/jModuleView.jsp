<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">

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
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
   浏览模块
  </div> 
  </div>
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">模块名：</td>
	            <td class="tableContent">${name}</td>
	            <td class="columnTitle">层数：</td>
	            <td class="tableContent">${layerNum}</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">权限标识：</td>
	            <td class="tableContent">${cpermission}</td>
	            <td class="columnTitle">链接：</td>
	            <td class="tableContent">${curl}</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">类型：</td>
	            <td class="tableContentAuto">
	            	<c:if test="${ctype==1}">主菜单</c:if>
	            	<c:if test="${ctype==2}">左侧菜单</c:if>
	            	<c:if test="${ctype==3}">按钮</c:if>
	            	<c:if test="${ctype==4}">链接</c:if>
	            	<c:if test="${ctype==5}">状态</c:if>
	            </td>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContentAuto">
	            	<c:if test="${state==1}">启用</c:if>
	            	<c:if test="${state==0}">停用</c:if>
	            </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">从属：</td>
	            <td class="tableContent">${belong}</td>
	            <td class="columnTitle">复用标识：</td>
	            <td class="tableContent">${cwhich}</td>
	        </tr>			
	        <tr>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent"><pre>${remark}</pre></td>
	            <td class="columnTitle">排序号：</td>
	            <td class="tableContent">${orderNo}</td>
	        </tr>			
		</table>
	</div>
 
 
</form>
</body>
</html>

