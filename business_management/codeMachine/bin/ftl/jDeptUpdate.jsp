<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="id" value="${'$'}{id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('${className?uncap_first}Action_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
   修改部门
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
<#list properties as pro>  
	        <tr>
	            <td class="columnTitle"><#if pro.proComment ??>${pro.proComment}</#if>：</td>
	            <td class="tableContent"><input type="text" name="${pro.proName}" value="${'$'}{${pro.proName}}"/></td>
	        </tr>	
</#list>	
		</table>
	</div>
 
 
</form>
</body>
</html>

