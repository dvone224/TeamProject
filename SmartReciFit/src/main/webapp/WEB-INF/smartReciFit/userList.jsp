<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>전체 회원 목록</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/adminStyle.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
    <jsp:include page="menu.jsp" />
    <h2>전체 회원 목록</h2>
    <table>
        <thead>
            <tr>
                <th>회원번호</th>
                <th>아이디</th>
                <th>닉네임</th>
                <th>이메일</th>
                <th>전화번호</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.user_num}</td>
                    <td>${user.user_id}</td>
                    <td>${user.user_nickname}</td>
                    <td>${user.user_email}</td>
                    <td>${user.user_phone}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>