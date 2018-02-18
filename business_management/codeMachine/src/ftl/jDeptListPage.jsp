<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<li id="view"><a href="#" onclick="formSubmit('${className?uncap_first}Action_toview','_self');this.blur();">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('${className?uncap_first}Action_tocreate','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="formSubmit('${className?uncap_first}Action_toupdate','_self');this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="formSubmit('${className?uncap_first}Action_delete','_self');this.blur();">删除</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    部门列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
<#list properties as pro>  
		<td class="tableHeader"><#if pro.proComment ??>${pro.proComment}</#if></td>
</#list>
	</tr>
	</thead>
	<tbody class="tableBody" >
${'$'}{page.links}
	
	<c:forEach items="${'$'}{page.results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${'$'}{o.id}"/></td>
		<td>${'$'}{status.index+1}</td>
		<td>${'$'}{o.id}</td>
		<td><a href="${className?uncap_first}Action_toview?id=${'$'}{o.id}">${'$'}{o.deptName}</a></td>
<#list properties as pro>  
		<td>${'$'}{o.${pro.proName}}</td>
</#list>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

