
<!DOCTYPE html>
<%@ include file="./part/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
	let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	location.href=contextPath+"/main.do";	
</script>

	<h1> home 메인 </h1>
	<form action="${ctx}/userJoin.do" method="post">
	<button name=test id=test>test</button>
	</form>
<%@ include file="./part/footer.jsp" %>