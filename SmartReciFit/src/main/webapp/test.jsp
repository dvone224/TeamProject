<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1> 회원가입 </h1>
	
<form action="${ctx}/userJoin.do" method="post">
	<table>
		<tr>
		<th>Id</th><td><input type="text" name="id" id="id" required><button class="btn-idCheck" id="btn-idCheck">ID중복검사</button></td> 
		</tr>
		<tr>
		<th>Password</th><td><input type="text" name="pw" id="pw" required> </td>
		</tr>
		<tr>
		<th>Name</th><td><input type="text" name="name" id="name" required> </td>
		</tr>
		<tr>
		<th>NickName</th><td><input type="text" name="nickName" id="nickName" required> </td>
		</tr>
	
			<tr>
			<td colspan="2">이메일 또는 전화번호 중 하나의 인증을 진행해주세요</td>
			</tr>
			<tr>
			<th>E-mail</th><td><input type="text" name="email" id="email"><button class="btn-phoneCheck" id="btn-phoneCheck">E-mail인증</button></td>
			</tr>
			<tr>
			<th>Phone</th><td><input type="text" name="phone" id="phone"><button class="btn-phoneCheck" id="btn-phoneCheck">번호인증</button></td>
			</tr>

		<tr>
		<th>프로필 사진</th><td><input type="file" name="uploadFile" accept="image/*"></td>
		</tr>
		<tr>
		<td colspan="2"><button class="btn-sudmit" id="btn-sudmit">가입하기</button></td>
		</tr>
	</table>
</form>	

</body>
</html>