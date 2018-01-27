<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Error Page</title>
    <script language="javascript">
        function showDetail()
        {
            var elm = document.getElementById('detail_system_error_msg');
            if(elm.style.display == '') {
                elm.style.display = 'none';
            }else {
                elm.style.display = '';
            }
        }
    </script>
</head>

<body style="font-family:微软雅黑;">

<div id="content" style="text-align:left;">

<table>
<tr>
	<td><img alt="system internal error" src="${pageContext.request.contextPath }/images/error01.jpg"/></td>

	
	
	<br>  
	<b>错误信息:</b>
 	
 	<div style="color:blue;padding:15px;">
 		<s:property value="exception.message"/>
 	</div>
    <button onclick="history.back();">返回</button>


    <p><a href="#" onclick="showDetail();">点击这里查看具体错误消息</a>,
	<br/>
	报告以下错误消息给系统管理员,可以更加快速的解决问题；
	<br/>联系电话：120
	</p>

	</td>
</tr>
</table>

	<div id="detail_system_error_msg" style="display:none;text-align:left;padding-bottom:100px;">  
		<pre><s:property value="exceptionStack"/></pre>  
	</div>
</div>
</body>
</html>