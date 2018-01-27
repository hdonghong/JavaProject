var PageLoading = false;
//正常的提交
function _Submit(target, formName, btnName) {
	if (PageLoading) {
		alert("页面正在加载中，请等待...");
		return ;
	} else {
		try {
			if (target && target=="exportAction.do") {
				//下载页面，下载的同时可以进行其它的提交操作
				PageLoading = false;
			} else {
				PageLoading = true;
			}
		} catch (e) {
			PageLoading = true;
		}
	}
	//alert("target="+target);
	try {
		if (!target || target == "") {
			alert("没有定义要执行的内容");
			PageLoading = false;
			return ;
		}
		var aForm;
		if (formName && formName != "") {
			aForm = eval(formName);
		} else {
			aForm = document.forms[0];
		}
		if (!btnName) {
			btnName = "";
		}	
		if (aForm) {
			aForm.action = target;
			aForm.method = "post";
			try {
				if (preSubmit(btnName)==false) {
					PageLoading = false;
					return ;
				}
				//exportedReset();
			} catch (e) {
				alert(e);
			}
			if(beforeSubmit){
				beforeSubmit(target, formName, btnName);	
			}
			aForm.submit();					//提交
			if(afterSubmit){
				afterSubmit(target, formName, btnName);	
			}			
		} else {
			if (formName && formName != "") {
				alert("页面中缺少Form：" + formName);
			} else {
				alert("页面中缺少Form：");
			}
			PageLoading = false;
		}
	} catch (e) {
		PageLoading = false;
//		alert(e);
	}
}

//检验数据类型是否正确
function _CheckValidate(CheckElement) {
	try {
		if (parent.Func) {
			parent.Func.checkValidate(CheckElement);
		} else if (parent.checkValidate) {
			parent.checkValidate(CheckElement);
		} else if (checkValidate) {
			checkValidate(CheckElement);
		}
	} catch (e){}
}
//检验整个表单数据类型是否正确（目前限制document只能够有一个表单）
function _CheckAll(ifCheckNull,serviceName, formId) {

	try {
		var ErrMessage = "";		
		var op_form	= document.forms[0].elements;
		if(formId){
			op_form = document.getElementById(formId);
		}
		ErrMessage = CheckAll(ifCheckNull, op_form.elements);

		try {			
			var userErr = user_check(ifCheckNull);			
			if (userErr) {
				if (ErrMessage) {
					ErrMessage += "</br>";
				}
				ErrMessage += userErr;
			}			
		} catch (e){}
		try {		
			var tempErr = preCheck(serviceName);
			if(tempErr){
				
				ErrMessage += "</br>" + tempErr;			
			}	
		} catch (e) {}	
		if (ErrMessage != "") {
			showError(ErrMessage);
			return false;
		} else {
			closeError();
			return true;
		}
	} catch (e){}
}

//检验整个表单数据类型是否正确（目前限制document只能够有一个表单）
function _CheckAllType(typeName,ifCheckNull) {
	try {
		var ErrMessage = "";
		
		ErrMessage = CheckAllType(typeName,ifCheckNull);		
		try {			
			var userErr = user_check(ifCheckNull);			
			if (userErr) {
				if (ErrMessage) {
					ErrMessage += "</br>";
				}
				ErrMessage += userErr;
			}			
		} catch (e){}
		try {		
			var tempErr = preCheck(serviceName);
			if(tempErr){
				
				ErrMessage += "</br>" + tempErr;			
			}	
		} catch (e) {}	
		if (ErrMessage != "") {
			showError(ErrMessage);
			return false;
		} else {
			closeError();
			return true;
		}
	} catch (e){}
}


/**
 * 用来判断何种状态下，可以进行操作！
 * serviceName:操作名；
 * selectObjId:复选框id；
 * canNotStatus：不可操作的状态；
 * 注：使用此方法必须在页面selectObjId复选框增加自定义属性：currentStatus。存放当前状态。
 * 	
 */
function isCanOperate(serviceName, selectObjId, canNotStatus){
	alertString = checkIsExist(selectObjId);    		
//	alert(alertString);
	if (alertString) {
		return alertString;
	} else {
		alertString = "";
		objectArray = document.getElementsByName(selectObjId);
//		alert(objectArray.length);
		var selectObj;
		for(i=0; i<objectArray.length; i++){
			if(objectArray[i].checked){
//				alert(objectArray[i].currentStatus);
				statusHidden = objectArray[i].currentStatus;						
				if(statusHidden == canNotStatus) {
//					alert(statusHidden);
					alertString += "第" + (i+1) + "条记录，归档状态不符合进行【" + serviceName + "】操作的条件，请核查！<br>";
				}
			}
		}
	}
	return alertString;
}

//只能选择一条记录，适用于【查看】、【修改】按钮
function onlySelect(serviceName, selectObjName, quantity){
	alertString = checkIsExist(selectObjName);    		
	if (alertString) {
		return alertString;		
	} else {
		var checkBoxArray = document.getElementsByName(selectObjName);
		for(var index=0, count=0; index<checkBoxArray.length; index++) {
			if (checkBoxArray[index].checked) {
				count++;
			}
			if (count > quantity) {
				return "最多只可选择" + quantity + "条记录进行【" + serviceName + "】操作。<br/>";
			}
		}
	}
}

//至少选择一条记录
function mustSelect(serviceName, selectObjName){
	var checkBoxArray = document.getElementsByName(selectObjName);
	var count = 0;
	for(var index=0; index<checkBoxArray.length; index++) {
		if (checkBoxArray[index].checked) {
			count++;
		}
	}
	if (count==0) {
		return "请选择要"+serviceName+"的记录！<br/>";
	}
}

function beforeSubmit(actionUrl, formName, btnName) {
	if (btnName == "打印") {
		if (typeof(window.formTarget) == 'undefined') {
			window.formTarget = document.forms[0].target;
		}
		document.forms[0].target='_blank';						//打印是弹出新窗口
		PageLoading = false;
	} else { 
		if (typeof(window.formTarget) != 'undefined') {
			document.forms[0].target = window.formTarget;
		}
	}
}

