<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="mailSend" method="post">
<table>
	<tr>
		<td>받는 사람 메일</td>
		<td><input name="receiver"></td>
	</tr>
	<tr>
		<td>제목</td>
		<td><input name="subject"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea name="content"></textarea></td>
	</tr>
	<tr>
		<td></td>
		<td><button>메일 보내기</button></td>
	</tr>
</table>
</form>
</body>
</html>