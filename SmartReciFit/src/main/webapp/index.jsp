
<!DOCTYPE html>
<%@ include file="./part/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script>

	let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	location.href=contextPath+"/recipes.do";
</script>

	<h1> home </h1>

<%@ include file="./part/footer.jsp" %>