<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
    <script type="text/javascript" src="${ctx}/components/jquery-ui/jquery-1.2.6.js"></script>
    <script type="text/javascript" src="${ctx}/js/tabledo.js"></script>	
	<script type="text/javascript" src="${ctx}/js/datepicker/WdatePicker.js"></script>

<script language="JavaScript">
    $().ready(function(){
		${mRecordData}
		//发送ajax请求-------------返回json------------后面就去组织数据（调用函数）
		//当进入更新页面时-----------直接获取服务器返回的串
		
		
    });
    

	/* 实现表格序号列自动调整 created by wyj 20081219 */
	function sortnoTR(){
		sortno('mRecordTable', 2, 1);
	}
		
	function addTRRecord(objId, id, productNo, cnumber, grossWeight, netWeight, sizeLength, sizeWidth, sizeHeight, exPrice, tax) {
		
		var _tmpSelect = "";
		var tableObj = document.getElementById(objId);
		var rowLength = tableObj.rows.length;
		
		oTR = tableObj.insertRow();
		
		oTD = oTR.insertCell(0);
		oTD.style.whiteSpace="nowrap";
		oTD.ondragover = function(){this.className="drag_over" };	//动态加事件, 改变样式类
		oTD.ondragleave = function(){this.className="drag_leave" };
		oTD.onmousedown = function(){ clearTRstyle("result"); this.parentNode.style.background = '#0099cc';};	
		//this.style.background="#0099cc url(../images/arroww.gif) 4px 9px no-repeat";
		oTD.innerHTML = "&nbsp;&nbsp;";	
		oTD = oTR.insertCell(1);
		oTD.innerHTML = "<input class=\"input\" type=\"checkbox\" name=\"del\" value=\""+id+"\"><input type=\"hidden\" name=\"mr_id\" value=\""+id+"\"><input class=\"input\" type=\"hidden\" id=\"mr_changed\" name=\"mr_changed\">";
		oTD = oTR.insertCell(2);
		oTD.innerHTML = "<input class=\"input\" type=\"text\" name=\"mr_orderNo\" readonly size=\"3\" value=\"\">";
		oTD = oTR.insertCell(3);
		oTD.innerHTML = "<b><font face='微软雅黑'><font color='blue'>"+productNo;+"</font></font></b> "
		oTD = oTR.insertCell(4);
		oTD.innerHTML = "<input type=\"text\" name=\"mr_cnumber\" maxLength=\"10\" value=\""+cnumber+"\" onBlur=\"setTRUpdateFlag(this);\" size=\"15\">";
		oTD = oTR.insertCell(5);
		oTD.innerHTML = "<input type=\"text\" name=\"mr_grossWeight\" maxLength=\"10\" value=\""+grossWeight+"\" onBlur=\"setTRUpdateFlag(this);\" size=\"15\">";
		oTD = oTR.insertCell(6);
		oTD.innerHTML = "<input type=\"text\" name=\"mr_netWeight\" maxLength=\"10\" value=\""+netWeight+"\" onBlur=\"setTRUpdateFlag(this);\" size=\"15\">";
		oTD = oTR.insertCell(7);
		oTD.innerHTML = "<input type=\"text\" name=\"mr_sizeLength\" maxLength=\"10\" value=\""+sizeLength+"\" onBlur=\"setTRUpdateFlag(this);\" size=\"15\">";
		oTD = oTR.insertCell(8);
		oTD.innerHTML = "<input type=\"text\" name=\"mr_sizeWidth\" maxLength=\"10\" value=\""+sizeWidth+"\" onBlur=\"setTRUpdateFlag(this);\" size=\"15\">";
		oTD = oTR.insertCell(9);
		oTD.innerHTML = "<input type=\"text\" name=\"mr_sizeHeight\" maxLength=\"10\" value=\""+sizeHeight+"\" onBlur=\"setTRUpdateFlag(this);\" size=\"15\">";
		oTD = oTR.insertCell(10);
		oTD.innerHTML = "<input type=\"text\" name=\"mr_exPrice\" maxLength=\"10\" value=\""+exPrice+"\" onBlur=\"setTRUpdateFlag(this);\" size=\"15\">";
		oTD = oTR.insertCell(11);
		oTD.innerHTML = "<input type=\"text\" name=\"mr_tax\" maxLength=\"10\" value=\""+tax+"\" onBlur=\"setTRUpdateFlag(this);\" size=\"15\">";

		dragtableinit();	//拖动表格行
		sortnoTR();			//排序号
		
	}    
    
</script> 

</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="id" value="${id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('exportAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   修改出口报运
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">报运号：</td>
	            <td class="tableContent">${customerContract}</td>
	            <td class="columnTitle">制单日期：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="inputDate"
	            	 value="<fmt:formatDate value="${inputDate}" pattern="yyyy-MM-dd"/>"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">信用证号</td>
	            <td class="tableContent"><input type="text" name="lcno" value="${lcno}"/></td>
	            <td class="columnTitle">收货人及地址：</td>
	            <td class="tableContent"><input type="text" name="consignee" value="${consignee}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">装运港：</td>
	            <td class="tableContent"><input type="text" name="shipmentPort" value="${shipmentPort}"/></td>
	            <td class="columnTitle">目的港：</td>
	            <td class="tableContent"><input type="text" name="destinationPort" value="${destinationPort}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">运输方式：</td>
	            <td class="tableContent"><input type="text" name="transportMode" value="${transportMode}"/></td>
	            <td class="columnTitle">价格条件：</td>
	            <td class="tableContent"><input type="text" name="priceCondition" value="${priceCondition}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle">唛头：</td>
	            <td class="tableContent"><textarea name="marks" style="height:120px;">${marks}</textarea></td>
	            <td class="columnTitle">备注：</td>
	            <td class="tableContent"><textarea name="remark" style="height:120px;">${remark}</textarea></td>
	        </tr>
		</table>
	</div>

	<div class="listTablew">
		<table class="commonTable_main" cellSpacing="1" id="mRecordTable">
			<tr class="rowTitle" align="middle">
				<td width="25" title="可以拖动下面行首,实现记录的位置移动.">
					<img src="${ctx }/images/drag.gif">
				</td>
				<td width="20">
					<input class="input" type="checkbox" name="ck_del" onclick="checkGroupBox(this);" />
				</td>
				<td width="33">序号</td>
				<td>货号</td>
				<td>数量</td>
				<td>毛重</td>
				<td>净重</td>
				<td>长</td>
				<td>宽</td>
				<td>高</td>
				<td>出口单价</td>
				<td>含税</td>
			</tr>
		</table>
	</div>


						</div>
						<div class="textbox-bottom">
							<div class="textbox-inner-bottom">
								<div class="textbox-go-top">
								</div>
							</div>
						</div>
					</div>
 
</form>
</body>
</html>

