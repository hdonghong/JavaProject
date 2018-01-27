var errDivIsCenter =true;
var ox = 0;
var oy = 0;
var relErrDivLeft = -1;
var relErrDivTop = -1;
var errFrameWidth = 300;
var errFrameHeight = 150;

function _removeErr()
{
	if(ErrDIV.style.display == "none")
	{
		var clientRight = document.body.scrollLeft + document.body.clientWidth;
		var clientBottom = document.body.scrollTop + document.body.clientHeight;

		var divLeft = 0;
		var divTop = 0;
		var divRight = 0;
		var divBottom = 0;

		if(errDivIsCenter)
		{
			var frameWidth = document.body.clientWidth;
			var frameHeight = document.body.clientHeight;
			var frameLeft = document.body.clientLeft;
			var frameTop = document.body.clientTop;
			//alert(frameWidth + ":" + frameHeight + ":" + frameLeft + ":" + frameTop);
			divLeft = (frameWidth - errFrameWidth)/2.2 + document.body.scrollLeft;
			divTop = (frameHeight - errFrameHeight)/3 + document.body.scrollTop;
		}
		else
		{
			divLeft = relErrDivLeft + document.body.scrollLeft;
			divTop = relErrDivTop + document.body.scrollTop;
		}

		var divRight = divLeft + errFrameWidth;
		var divBottom = divTop + errFrameHeight;

		if(divLeft < document.body.scrollLeft)
		{
			divLeft = document.body.scrollLeft;
		}
		
		if(divTop < document.body.scrollTop)
		{
			divTop = document.body.scrollTop;
		}
		
		if(divRight > clientRight)
		{
			divLeft = divLeft - ( divRight - clientRight);
		}
		
		if(divBottom > clientBottom)
		{
			divTop = divTop - (divBottom - clientBottom);
		}

		ErrDIV.style.pixelLeft = divLeft;
		ErrDIV.style.pixelTop = divTop;
	}
}

function showError(ErrMessage){
	var errFr = ErrFrame;
	errFr.ErrDiv.innerHTML = ErrMessage;

	_removeErr();
	ErrDIV.style.display = "";
}

//add by tony 是错误提示框公用,也可以作为信息提示框
function showMsg(ErrMessage)
{
	var errFr = ErrFrame;
	errFr.ErrDiv.innerHTML = ErrMessage;

	_removeErr();
	ErrDIV.style.display = "";
}

//add by tony 正在提示框
function showBuilding( )
{
	var errFr = ErrFrame;
	errFr.ErrDiv.innerHTML = "正在建设中...";

	_removeErr();
	ErrDIV.style.display = "";
}

function showClose(){
	ErrDIV.style.display = "none";
}

function start(x, y)
{
	ox = x;
	oy = y;
	document.body.setCapture(false);
	ErrDIV.style.filter = "alpha(opacity=80)";
	return true;
}

function move(x, y)
{
	var offsetX = x - ox;
	var offsetY = y - oy;
	ox = x;
	oy = y;

	var clientRight = document.body.scrollLeft + document.body.clientWidth;
	var clientBottom = document.body.scrollTop + document.body.clientHeight;
	
	var divLeft = ErrDIV.style.pixelLeft + offsetX;
	var divTop = ErrDIV.style.pixelTop + offsetY;
	var divRight = divLeft + errFrameWidth;
	var divBottom = divTop + errFrameHeight;
	
	if(divLeft < document.body.scrollLeft)
	{
		divLeft = document.body.scrollLeft;
	}
	
	if(divTop < document.body.scrollTop)
	{
		divTop = document.body.scrollTop;
	}
	
	if(divRight > clientRight)
	{
		divLeft = divLeft - ( divRight - clientRight);
	}
	
	if(divBottom > clientBottom)
	{
		divTop = divTop - (divBottom - clientBottom);
	}
	
	ErrDIV.style.pixelLeft = divLeft;
	ErrDIV.style.pixelTop = divTop;
	return true;
}

function end()
{
	errDivIsCenter = false;
	relErrDivLeft = ErrDIV.style.pixelLeft - document.body.scrollLeft;
	relErrDivTop = ErrDIV.style.pixelTop - document.body.scrollTop;
	document.body.releaseCapture();
	ErrDIV.style.filter = "alpha(opacity=99)";
	return true;
}