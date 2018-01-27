<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
</head>

<script type="text/javascript">
	//必须从此转向，否则路径错误会导致读取配置xml和数据xml文件错误。
	var _date = new Date();
	window.location.href = "${pageContext.request.contextPath}/stat/chart/<%=request.getParameter("forward")%>/index.html?d="+_date;
</script>

<body>


</body>
</html>
