function checkValidate(CheckElement){
	if(!CheckElement.dataType){
		return;
	}
	var errMess = "";
	sIfNull			=	CheckElement.dataType.substring(0,2);
	sOther			=	CheckElement.dataType.substring(2,CheckElement.dataType.length);
	var CheckdataType;
	if(sIfNull=="非空"){
		CheckdataType		=	sOther;
	}
	else{
		CheckdataType		=	CheckElement.dataType;
	}
	if(CheckElement.value!=""){
		if(CheckdataType	==	""){
			return;
		}
		switch(CheckdataType){
			//可选属性：maxLength,minLength
			case 'FCK框':	errMess = checkFckEditString(CheckElement);break;
			case '字符串':	errMess = checkString(CheckElement);break;
			case '邮政编码':	errMess = checkPost(CheckElement);break;
			//可选属性：maxValue,minValue
			case '数字':		errMess = checkNumber(CheckElement);break;
			//可选属性：maxValue,minValue
			case '百分比':	errMess = checkPercent(CheckElement);break;
			//可选属性：maxValue,minValue
			case '整数':		errMess = checkInt(CheckElement);break;
			//可选属性：maxValue,minValue
			case '正整数':	errMess = checkPlusInt(CheckElement);break;

			//样式：2006-12-12
			case '日期':		errMess = checkDate(CheckElement);break;
			//样式：2006-12
			case '年月日期':	errMess = checkYearMonth(CheckElement);break;
			//样式：2006-12-12 12:00:00
			case '时刻':		errMess = checkDateTime(CheckElement);break;
			//样式：2006-12-12 12:00
			case '日期时分':	errMess = checkDateMinute(CheckElement);break;
			//样式：12:00
			case '时分':		errMess = checkMinute(CheckElement);break;
			//样式：12:00:00
			case '日期时间':	errMess = checkTime(CheckElement);break;

			//样式：192.168.0.1
			case 'IP地址':	errMess = checkIP(CheckElement);break;
			case 'Email':	errMess = checkEmail(CheckElement);break;
			case '电话':		errMess = checkPhone(CheckElement);break;
			//样式：13000000000
			case '手机':		errMess = checkMobilephone(CheckElement);break;
			//可选属性：maxValue,minValue
			case '货币':		errMess = checkCurrency(CheckElement);break;

			case '下拉列表':	errMess = checkSelect(CheckElement);break;
			case '下拉框':	errMess = checkSelect(CheckElement);break;
			case '单选按钮':	errMess = checkRadio(CheckElement);break;
			case '单选框':	errMess = checkRadio(CheckElement);break;
			//可选属性：maxSelect,minSelect
			case '复选框':	errMess = checkCheckbox(CheckElement);break;

			//可选属性：fileType(如："xml,doc,htm"),maxSize(如："3")单位为M
			case '中文':		errMess = checkChinese(CheckElement);break;
			case '非中文':	errMess = checkNoChinese(CheckElement);break;
			
			case '文件选择':	errMess = checkFileSelect(CheckElement);break;
			case '非中文文件名':errMess = checkCnFileName(CheckElement);break;
			default:alert('暂时不能识别:'+CheckElement.dataType+'的数据类型');
		}
	}
	var eleErrMsg = CheckElement.nextSibling;
	if(eleErrMsg && eleErrMsg.name == "ErrMsg" ){
		if(errMess != "" ){
			errMess = "<div style='position:absolute;padding-top:5px;padding-left:5px;background:#fff;z-index:999'><img src='../images/check_error.gif' /> " + errMess + "</div>";			
		}
		eleErrMsg.innerHTML =  errMess;
	}else{
		var eleErrMsg = document.createElement("span");
		eleErrMsg.name = "ErrMsg";
		if(errMess != "" ){
			errMess = "<div style='position:absolute;padding-top:5px;padding-left:5px;background:#fff;z-index:999'><img src='../images/check_error.gif' /> " + errMess + "</div>";			
		}
		eleErrMsg.innerHTML = errMess;
		CheckElement.insertAdjacentElement('afterEnd', eleErrMsg);
	}
	
	return true;			
}
function getLength(str){
  return str.replace(/[^\x00-\xff]/g,"**").length;
}

//是否中文 add by tony
function checkNoChinese(s){
	if(isChinese(s)){
		return '不能输入中文,只能输入数字或英文!';
	}
}
function checkChinese(s){
	if(isChinese(s)){
		return '只能输入输入中文!';
	}
}
function checkCnFileName(s){
	alert(s);
	if(isChinese(s)){
		return '文件名不能为中文,只能由数字或英文组成!';
	}
}
function isChinese(s){
	var errorChar;
	var badChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789><,[]{}?/+=|\\'\":;~!#$%()`";
	errorChar = isCharsInBag( s, badChar)
	if (errorChar != "" ){
		//report=report+"请重新输入中文\n";
		return false;
	}
	return true;
}
function isCharsInBag(s, bag){ 
	var i,c;
	for (i = 0; i < s.length; i++){
		c = s.charAt(i);		//字符串s中的字符
		if (bag.indexOf(c) > -1) 
		return c;
	}
	return "";
}

function checkFileSelect(ele){
	var matchFile=false;
	matchFile = new RegExp("^.+\.(?=EXT)(EXT)$".replace(/EXT/g, ele.fileType.split(/\s*,\s*/).join("|")), "gi").test(ele.value);
	if(matchFile==false){
		return "文件类型应为:"+ele.fileType;
	}
	var fso = new ActiveXObject("Scripting.FileSystemObject");
	fileName = fso.FileExists(ele.value);
	if (!fileName){
		return "文件'"+ele.value+"'不存在";
	}
	else if(ele.maxSize){
		var f=fso.GetFile(ele.value);  
		var fileSize = new Number(f.size);  
		var maxSize = new Number(ele.maxSize);
		if(fileSize>maxSize*1024*1024){
			return "文件大小不能够超过"+ele.maxSize+"M";
		}
	}
	return "";
}
function checkSelect(ele){
	if(ele.value&&ele.value!=""){
		return "";
	}
	return "请您选择"+ele.dispName;
}
function checkRadio(ele){
	for(var i=0;i<document.getElementsByName(ele.name).length;i++){
		if(document.getElementsByName(ele.name)[i].checked){
			return "";
		}
	}
	for(var i=1;i<document.getElementsByName(ele.name).length;i++){
		if(document.getElementsByName(ele.name)[i].dataType){
			document.getElementsByName(ele.name)[i].dataType="";
		}
	}	
	return "请您选择;";
}
function checkCheckbox(ele){
	var selectedNumber=0;
	var minSelect =1;
	var maxSelect =document.getElementsByName(ele.name).length;
	for(var i=0;i<document.getElementsByName(ele.name).length;i++){
		if(document.getElementsByName(ele.name)[i].checked){
			selectedNumber++;
		}
	}
	for(var i=1;i<document.getElementsByName(ele.name).length;i++){
		if(document.getElementsByName(ele.name)[i].dataType){
			document.getElementsByName(ele.name)[i].dataType="";
		}
	}	
	if(ele.maxSelect){
		maxSelect = new Number(ele.maxSelect);
	}
	if(ele.minSelect){
		minSelect = new Number(ele.minSelect);
	}
	if(selectedNumber>=minSelect&&selectedNumber<=maxSelect){
		return "";
	}
	else if(ele.minSelect&&ele.maxSelect){
		return "请您选择"+minSelect+"~"+maxSelect+"项";
	}
	else if(ele.minSelect){
		return "请您至少选择"+minSelect+"条记录";
	}
	else if(ele.maxSelect){
		return "请最多选择"+maxSelect+"项";
	}
	else{
		return "请您选择"+minSelect+"~"+maxSelect+"项";
	}
}
function alertRightDate(){
	bCheckLock	=	false;
	return("格式:2000-12-21");
}
function checkDate(ele){
	var ev		=	ele.value;
	var evyear;
	var evmonth;
	var evdate;
	var iindex;
	if(ev.length<8){
		return alertRightDate();
		//ele.value="";
	}
	iindex		=	ev.indexOf("-")
	if(iindex==-1){
		return alertRightDate();
		//ele.value="";
	}
	evyear		=	ev.substring(0,iindex);
	if (evyear.length != 4){
		return alertRightDate();
	}
	ev			=	ev.substring(iindex+1,ev.length);
	iindex		=	ev.indexOf("-")
	if(iindex==-1){
		return alertRightDate();
		//ele.value="";
	}
	evmonth		=	ev.substring(0,iindex);
	if (evmonth.length >2){
		return alertRightDate();
	}
	evdate		=	ev.substring(iindex+1,ev.length);
	if (evdate.length >2){
		return alertRightDate();
	}
	var ndate	=	evmonth+"/"+evdate+"/"+evyear;
	var tdate	=	Date.parse(ndate);
	var ddate	=	new Date(tdate);
	if(isNaN(tdate)){
		return alertRightDate();
	}
	if(	(evyear!=ddate.getFullYear()	)||(evmonth!=(ddate.getMonth()+1)	)||(evdate!=ddate.getDate())	){
		return alertRightDate();
	}
	return "";
}
function checkYearMonth(ele){
	var ev		=	ele.value;
	var evyear;
	var evmonth;
	var iindex;
	if(ev.length<6){
		return alertRightYearMonthDate();
		//ele.value="";
	}
	iindex		=	ev.indexOf("-")
	if(iindex==-1){
		return alertRightYearMonthDate();
		//ele.value="";
	}
	evyear		=	ev.substring(0,iindex);
	if (evyear.length != 4){
		return alertRightYearMonthDate();
	}
	ev			=	ev.substring(iindex+1,ev.length);
	evmonth		=	ev;
	if (evmonth.length >2){
		return alertRightYearMonthDate();
	}

	var ndate	=	evmonth+"/"+"01"+"/"+evyear;
	var tdate	=	Date.parse(ndate);
	var ddate	=	new Date(tdate);
	if(isNaN(tdate)){
		return alertRightYearMonthDate();
		//ele.value="";
	}
	if(	(evyear!=ddate.getFullYear()	)||(evmonth!=(ddate.getMonth()+1))){
		return alertRightYearMonthDate();
		//ele.value="";
	}
	return "";
}
function alertRightYearMonthDate(){
	bCheckLock = false;
	return "格式:2014-12";
}
function alertDateTime(){
	bCheckLock = false;
	return "格式:2003-6-1 12:00:00";
}
function alertDateMinute(){
	bCheckLock = false;
	return "格式:2003-6-1 12:00";
}
function alertTime(){
	bCheckLock = false;
	return "格式:12:00:00";
}
/* 检查DateTime类型是否满足"yyyy-mm-dd MM-mm-ss"形式. */
function checkDateTime(ele){
	var str = ele.value;
	if (str.length < 14) {
		return alertDateTime();
	}
	var iSpc = str.indexOf(" ");
	if (iSpc == -1) {
		return alertDateTime();
	}
	
	var ev = str.substring(0, iSpc);
	var et = str.substring(iSpc + 1, str.length);
	var idx;
	var evyear, evmonth, evdate;
	var hour, minute, second;
	
	idx = ev.indexOf("-");
	if (idx == -1) {
		return alertDateTime();
	}
	
	evyear = ev.substring(0, idx);
	if (evyear.length != 4)	{
		return alertDateTime();
	}
	ev = ev.substring(idx + 1, ev.length);
	idx = ev.indexOf("-");
	if (idx == -1) {
		return alertDateTime();
	}
	
	evmonth		=	ev.substring(0,idx);
	if (evmonth.length >2){
		return alertDateTime();
	}
	evdate		=	ev.substring(idx+1,ev.length);
	if (evdate.length >2){
		return alertDateTime();
	}
	var ndate	=	evmonth + "/" + evdate + "/" + evyear;
	var tdate	=	Date.parse(ndate);
	var ddate	=	new Date(tdate);
	if(isNaN(tdate)) {
		return alertDateTime();
	}
	
	if ((evyear != ddate.getFullYear()) || (evmonth != (ddate.getMonth() + 1)) || (evdate != ddate.getDate())) {
		return alertDateTime();
	}
	
	idx = et.indexOf(":");
	if (idx == -1) {
		return alertDateTime();
	}
	
	hour = et.substring(0, idx);
	et = et.substring(idx + 1, et.length);
	var hr = new Number(hour);
	if (isNaN(hr)) {
		return alertDateTime();
	} else {
		if (hr < 0 || hr > 24) {
			return alertDateTime();
		}
	}
	
	idx = et.indexOf(":");
	if (idx == -1) {
		return alertDateTime();
	}

	minute = et.substring(0, idx);
	second = et.substring(idx + 1, et.length);

	mint = new Number(minute);
	sec = new Number(second);
	if (isNaN(mint) || isNaN(sec)) {
		return alertDateTime();
	} else {
		if (mint < 0 || mint > 59 || sec < 0 || sec > 59) {
			return alertDateTime();
		}
	}
	
	return "";
}
function checkTime(ele){
	var str = ele.value;
	if (str.length < 5) {
		return alertTime();
	}
	if (str.length > 8) {
		return alertTime();
	}
	var idx = str.indexOf(":");
	if (idx == -1) {
		return alertTime();
	}
	var hour = str.substring(0, idx);
	if(hour=="")	{
		return alertTime();
	}	
	var et = str.substring(idx + 1, str.length);
	var hr = new Number(hour);
	if (isNaN(hr)) 	{
		return alertTime();
	} 	else 	{
		if (hr < 0 || hr > 23) 		{
			
			return alertTime();
		}
	}
	idx = et.indexOf(":");
	if (idx == -1) {
		return alertTime();
	}

	var minute = et.substring(0, idx);
	if(minute=="")	{
			return alertTime();
	}	
	var second = et.substring(idx + 1, et.length);
	if(second=="")	{
			return alertTime();
	}	
	var mint = new Number(minute);
	var sec = new Number(second);
	if (isNaN(mint) || isNaN(sec)) {
		return alertTime();
	} else {
		if (mint < 0 || mint > 59 || sec < 0 || sec > 59) {
			return alertTime();
		}
	}
	
	return "";
}

function checkNumber(ele){
	var elevalue	=	new Number(ele.value);
	if(isNaN(elevalue))	{
		bCheckLock	=	false;
		return("只能输入数字"); 
	}
	ele.value=elevalue;
	if(ele.maxValue)	{
		return(checkmaxValue(ele));
	}
	if(ele.minValue)	{
		return(checkminValue(ele));
	}
	return "";
}
function checkCurrency(ele){
	var elevalue	=	new Number(ele.value);
	if(isNaN(elevalue))	{
		bCheckLock	=	false;
		return("只能输入数字"); 
	}
	if(elevalue<0)	{
		bCheckLock	=	false;
		return("不能输入负数"); 
	}		
	var tempnum=elevalue;
	if(ele.value.indexOf(".")>=0)	{
		var su=ele.value.indexOf(".");
		var s=ele.value.substring(su+1);
		if(s.length>2)	{
			var res="只能输入两位小数";
			return(res); 
		}
		else if (s.length == 1)	{
			ele.value=ele.value+"0";
		}
		else if(s.length==0){
			ele.value=ele.value+"00";
		}
	}	
	else{
		ele.value=tempnum+".00";
	}
	if(ele.maxValue){
		var res = checkmaxValue(ele);
		if(res != ""){
			return res;
		}
	}
	if(ele.minValue){
		var res = checkminValue(ele);
		if(res != ""){
			return res;
		}
	}
	return "";
}	
function checkmaxValue(sElement){
	var sMaxNumber		=	new Number(sElement.maxValue);
	var sCurrentNumber	=	new Number(sElement.value);
	if(sCurrentNumber>sMaxNumber){
		bCheckLock	=	false;
		return "最大值:" + sElement.maxValue;
	}
	
	if(sElement.minValue){
		var sMinNumber	=	new Number(sElement.minValue);
		if(sCurrentNumber < sMinNumber)	{
			bCheckLock	=	false;
			return "最小值:" +  sElement.minValue;
		}	
	}	
	return "";
}
function checkminValue(sElement){
	var sCurrentNumber	=	new Number(sElement.value);
	var sMinNumber	=	new Number(sElement.minValue);
	if(sCurrentNumber < sMinNumber){
			bCheckLock	=	false;
			return "最小值:" +  sElement.minValue;
	}	
	return "";
}
function checkInt(ele){
	var elevalue	=	new Number(ele.value);
	if(isNaN(elevalue))	{
		return "只能输入整数";
	}
	if(ele.value.indexOf(".")!=-1)	{
		return "只能输入整数";
	}
	ele.value=elevalue;
	if(ele.maxValue)	{
		return(checkmaxValue(ele));
	}
	if(ele.minValue)	{
		return(checkminValue(ele));
	}
	return "";
}
function checkPlusInt(ele){
	var elevalue	=	new Number(ele.value);
	if(isNaN(elevalue))	{
		return "只能输入正整数";
	}
	if(ele.value.indexOf(".")!=-1)	{
		return "只能输入正整数";
	}
	if(elevalue <=  0)	{
		return "只能输入正整数";
	}
	
	ele.value=elevalue;
	if(ele.maxValue)	{
		return(checkmaxValue(ele));
	}
	if(ele.minValue)	{
		return(checkminValue(ele));
	}
	return "";
}
/*
  检查DateTime类型是否满足"hh:mi"形式.
*/
function checkMinute(checkEle){
	var str = checkEle.value;
	if((str.length!=5)&&(str.length!=4))	{		
		return alertMinute();
	}
	var idx = str.indexOf(":");
	if (idx != 2){
		if(idx !=1)
			return alertMinute();
	}
	var h = str.substring(0,idx);
	var m = str.substring(idx+1,str.length);
	
	h = new Number(h);
	m = new Number(m);
	if(isNaN(h)){
		return alertMinute();
	}else{
		if(h < 0 || h > 23)
			return alertMinute();
	}
	
	if(isNaN(m)){
		return alertMinute();
	}else{
		if(m <0 || m >59)
			return alertMinute();
	}
	return "";
}
function alertMinute(){
	bCheckLock = false;
	return "格式:12:00";
}
/*
  时间格式这能写道分钟
  检查DateTime类型是否满足"yyyy-mm-dd hh-mm"形式.
*/
function checkDateMinute(ele){
	var str = ele.value;
	if (str.length < 12) {
		return alertDateMinute();
	}
	var iSpc = str.indexOf(" ");
	if (iSpc == -1) {
		return alertDateMinute();
	}
	
	var ev = str.substring(0, iSpc);
	var et = str.substring(iSpc + 1, str.length);
	var idx;
	var evyear, evmonth, evdate;
	var hour, minute, second;
	
	idx = ev.indexOf("-");
	if (idx == -1) {
		return alertDateMinute();
	}
	
	evyear = ev.substring(0, idx);
	if (evyear.length != 4){
		return alertDateMinute();
	}
	ev = ev.substring(idx + 1, ev.length);
	idx = ev.indexOf("-");
	if (idx == -1) {
		return alertDateMinute();
	}
	
	evmonth		=	ev.substring(0,idx);
	if (evmonth.length >2){
		return alertDateMinute();
	}
	evdate		=	ev.substring(idx+1,ev.length);
	if (evdate.length >2){
		return alertDateMinute();
	}
	var ndate	=	evmonth + "/" + evdate + "/" + evyear;
	var tdate	=	Date.parse(ndate);
	var ddate	=	new Date(tdate);
	if(isNaN(tdate)) {
		return alertDateMinute();
	}
	
	if ((evyear != ddate.getFullYear()) || (evmonth != (ddate.getMonth() + 1)) || (evdate != ddate.getDate())) {
		return alertDateMinute();
	}
	
	idx = et.indexOf(":");
	if (idx == -1) {
		return alertDateMinute();
	}
	
	hour = et.substring(0, idx);
	et = et.substring(idx + 1, et.length);
	var hr = new Number(hour);
	if (isNaN(hr)) {
		return alertDateMinute();
	} else 
		{
		if (hr < 0 || hr >= 24) {
			return alertDateMinute();
		}
	}
	
	minute = et;

	mint = new Number(minute);
	if (isNaN(mint)) {
		return alertDateMinute();
	} else {
		if (mint < 0 || mint > 59) {
			return alertDateMinute();
		}
	}
	
	return "";
}
function checkPercent(ele){
	var elevalue	=	new Number(ele.value);
	if(isNaN(elevalue)||(elevalue<0)||(elevalue>100)){
		bCheckLock	=	false;
		return "必须是0到100之间数字";
	}
	ele.value=elevalue;
	if(ele.maxValue){
		return(checkmaxValue(ele));
	}
	if(ele.minValue){
		return(checkminValue(ele));
	}
	return true;
}

//fckedit框的非空和长度校验 by aaa 2011-04-08
function checkFckEditString(ele){
	if(ele.minLength){
		if(getLength(ele.value)<ele.minLength){
			return "最小长度:" + ele.minLength+"字符";
		}
	}
	if(ele.maxLength){
		if(getLength(ele.value)>ele.maxLength){
			var msg = "当前长度:" + getLength(ele.value) +"字符,最大长度:" + ele.maxLength+"字符,或"+((ele.maxLength%2==0)?(ele.maxLength/2):((ele.maxLength-1)/2))+"个汉字";
			msg = msg + "<br />输入的内容中含有隐藏字符!<br />与您输入的内容长度相加后超过了限制长度!<br / >请点击[源代码]按钮查看隐藏字符."
			return msg;
		}
	}
	var regobj	=	/\s+$/g;
	var s_end	=	(ele.value).replace(regobj,'');
	var regobjb	=	/^\s+/g;
	ele.value	=	(s_end).replace(regobjb,'');
	return "";
}

function checkString(ele){
	//暂时规定长字符串内不能包含单引号以及双引号,如果找到了，去处这些单引号和双引号
	//if(ele.value.indexOf("'") != -1){
	//	return "不能包含单引号";
	//}
	//if(ele.value.indexOf("\"") != -1){
	//	return "不能包含双引号";
//	}

	if(ele.minLength){
		if(getLength(ele.value)<ele.minLength){
			return "最小长度:" + ele.minLength+"字符";
		}
	}
	if(ele.maxLength){
		if(getLength(ele.value)>ele.maxLength){
			return "最大长度:" + ele.maxLength+"字符,或"+((ele.maxLength%2==0)?(ele.maxLength/2):((ele.maxLength-1)/2))+"个汉字";
		}
	}
	var regobj	=	/\s+$/g;
	var s_end	=	(ele.value).replace(regobj,'');
	var regobjb	=	/^\s+/g;
	ele.value	=	(s_end).replace(regobjb,'');
	return "";
}
function alertRightPost(){
	bCheckLock	=	false;
	return "格式:710043";
}
function checkPost(ele){
	if(ele.value.length != 6){
		return alertRightPost();
	}
	
	var num	=	new Number(ele.value);
	
	if(isNaN(num)){
		return alertRightPost();
	}
	
	if(num < 0){
		return alertRightPost();
	}
	
	if(ele.value.indexOf(".")!=-1){
		return alertRightPost();
	}
	return "";
}
function alertRightPhone(){
	bCheckLock	=	false;
	return "格式:029-12345678";
}
function checkPhone(ele)
{
	var ev	=	ele.value;
	//检测长度是否足够
	if(ev.length<7){
		return alertRightPhone();
	}
	//检测长度是否超过限制
	if(ev.length>20){
		return alertRightPhone();
	}
	//检测是否含有非法字符
	if(	(ev.indexOf("'")!=-1)||(ev.indexOf("\"")!=-1)){
		return alertRightPhone();
	}
	//允许出现两次"("号,两次")"号,两次"-"号,一次","号,一次";"号
	//其余的必须为数字
	var re = "(";
	var sTemp = ev.replace(re, "" );
	re =")";
	sTemp = sTemp.replace(re, "" );
	re = "-";
	
	sTemp = sTemp.replace(re, "");
	
	re = "(";
	var sTemp = sTemp.replace(re, "" );
	re =")";
	sTemp = sTemp.replace(re, "" );
	re = "-";
	
	sTemp = sTemp.replace(re, "");
	
	re = ",";
	sTemp = sTemp.replace( re, "" );
	re = ";";
	sTemp = sTemp.replace( re, "" );
	
	var elevalue	=	new Number(sTemp);
	if( isNaN(elevalue) ){
		return alertRightPhone();
	}
	return "";
}
function checkMobilephone(ele){
	//if (ele.value.length !== 11) {
	//	return "手机号码应为11位！";
	//}
	var elevalue	=	new Number(ele.value);
	if( isNaN(elevalue) ){
		return "手机号码必须是数字！";
	}
	//if (ele.value.substring(0,2) != '13' && ele.value.substring(0,2) !='15') {
	//	return "输入的不是手机号码";
	//}
	return "";
}
function alertRightIP()
{
	bCheckLock	=	false;
	return "格式:192.168.0.1";
}
function checkIP(ele){
	try{
		var sReturn = "";
		var evalue = ele.value;
		var arrEvalue = evalue.split(".");
		if(arrEvalue.length != 4){
			sReturn = alertRightIP();
		}else{
			for(var i = 0;i < arrEvalue.length;i++){
				var ipNum = new Number(arrEvalue[i]);
				if(isNaN(ipNum) || ipNum - 0 < 0 || ipNum - 255 > 0){
					sReturn = alertRightIP();
					break;
				}
			}
		}
		return sReturn;
	}
	catch(e){
		return alertRightIP();
	}
}
function checkEmail(ele){
	var index = ele.value.indexOf("@");
	if(index < 0){
		return "错误的Email格式";
	}
	
	if(ele.value.indexOf("@",index+1) != -1){
		return "错误的Email格式";
	}

	if(ele.value.length < 6 ) {
		return "错误的Email格式!";
	}
	var iDot;
	var sTail;
	iDot = ele.value.lastIndexOf(".");
	sTail = ele.value.substring(iDot, ele.value.length);
	sTail = sTail.toLowerCase();
	if (!(sTail == ".cn" || sTail == ".com" || sTail == ".net" || sTail == ".org" || sTail == ".edu")) {
		return "错误的Email格式!";
	}
	return "";
}
function CheckAll(ifCheckNull, eles){

	var errMsg = "";
	for(i=0;i<eles.length;i++){	
		if(eles[i].dataType){
			
			var errMess = "";
			var CheckElement = eles[i];
			sIfNull			=	CheckElement.dataType.substring(0,2);
			sOther			=	CheckElement.dataType.substring(2,CheckElement.dataType.length);
			var CheckdataType;
			if(sIfNull=="非空"){
				CheckdataType		=	sOther;
			}
			else{
				CheckdataType		=	CheckElement.dataType;
			}
			if(CheckElement.value){
				if(CheckdataType !=	""){
					switch(CheckdataType){
						case 'FCK框':	errMess = checkFckEditString(CheckElement);break;
						case '字符串':	errMess = checkString(CheckElement);break;
						case '邮政编码':	errMess = checkPost(CheckElement);break;
						case '长字符串':	errMess = checkLongString(CheckElement);break;
						case '数字':		errMess = checkNumber(CheckElement);break;
						case '百分比':	errMess = checkPercent(CheckElement);break;
						case '整数':		errMess = checkInt(CheckElement);break;
						case '正整数':	errMess = checkPlusInt(CheckElement);break;

						case '日期':		errMess = checkDate(CheckElement);break;
						case '日期时分':	errMess = checkDateMinute(CheckElement);break;
						case '年月日期':	errMess = checkYearMonth(CheckElement);break;
						case '时分':		errMess = checkMinute(CheckElement);break;
						case '时刻':		errMess = checkDateTime(CheckElement);break;
						case '日期时间':	errMess = checkTime(CheckElement);break;

						case 'IP地址':	errMess = checkIP(CheckElement);break;
						case 'Email':	errMess = checkEmail(CheckElement);break;
						case '电话':		errMess = checkPhone(CheckElement);break;
						case '手机':		errMess = checkMobilephone(CheckElement);break;
						case '货币':		errMess = checkCurrency(CheckElement);break;

						case '下拉框':	errMess = checkSelect(CheckElement);break;
						case '下拉列表':	errMess = checkSelect(CheckElement);break;
						case '单选按钮':	errMess = checkRadio(CheckElement);break;
						case '单选框':	errMess = checkRadio(CheckElement);break;
						case '复选框':	errMess = checkCheckbox(CheckElement);break;
						case '文件选择':	errMess = checkFileSelect(CheckElement);break;
						default:alert('暂时不能识别:'+CheckElement.dataType+'的数据类型');
					}
				}
			}
			if(ifCheckNull){
				if(CheckElement.value == "" && CheckElement.dataType.substring(0,2)=="非空"){
					if(CheckdataType=="下拉列表" || CheckdataType=="下拉框"){
						errMess = "必须选择;";
					}
					else if(CheckdataType=="单选按钮" || CheckdataType=="单选框"){
						errMess = "必须选择;";
					}
					else if(CheckdataType=="复选框"){
						errMess = "必须选择;";
					}
					else{
						errMess = "必须填写;";
					}
				}			
			}

			if(errMess != ""){
				var disName = CheckElement.dispName;
				errMsg = errMsg + disName + ":" + errMess + "<br/>" ;
			}
		}
	}
	return errMsg;
}