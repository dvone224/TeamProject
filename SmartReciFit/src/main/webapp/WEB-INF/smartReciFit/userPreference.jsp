<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>

    <main>
      <div class="inner">
        <h2> 선호도 조사 </h2>
        
	<form method="post" action="CheckboxServlet">
        <h3>메인 재료</h3>
		<input type="checkbox" name="item" value="쌀">쌀
		<input type="checkbox" name="item" value="곡류">곡류
		<input type="checkbox" name="item" value="달걀">달걀
		<input type="checkbox" name="item" value="가공식품">가공식품
		<input type="checkbox" name="item" value="유제품">유제품
		<input type="checkbox" name="item" value="육류">육류
		
        <h3>고기</h3>
		<input type="checkbox" name="item" value="돼지고기">돼지고기
		<input type="checkbox" name="item" value="소고기">소고기
		<input type="checkbox" name="item" value="양고기">양고기
		<input type="checkbox" name="item" value="닭고기">닭고기
		<input type="checkbox" name="item" value="해산물">해산물
		
        <h3>채소</h3>
		<input type="checkbox" name="item" value="채소">채소
		<input type="checkbox" name="item" value="과일">과일
		<input type="checkbox" name="item" value="버섯">버섯
		
        <h3>국가</h3>
		<input type="checkbox" name="item" value="양식">양식
		<input type="checkbox" name="item" value="한식">한식
		<input type="checkbox" name="item" value="중식">중식
		<input type="checkbox" name="item" value="일식">일식
		<input type="checkbox" name="item" value="아시안식">아시안식
		
        <h3>조리방법</h3>
		<input type="checkbox" name="item" value="볶음">볶음
		<input type="checkbox" name="item" value="조림">조림
		<input type="checkbox" name="item" value="탕">탕
		<input type="checkbox" name="item" value="튀김">튀김
		<input type="checkbox" name="item" value="샐러드">샐러드
		<input type="checkbox" name="item" value="찜">찜
		<input type="checkbox" name="item" value="구이">구이
		
        <h3>시간</h3>
		<input type="checkbox" name="item" value="아침">아침
		<input type="checkbox" name="item" value="점심">점심
		<input type="checkbox" name="item" value="저녁">저녁
		<input type="checkbox" name="item" value="야식">야식
		<input type="checkbox" name="item" value="간식">간식
		<button name="btn-perforenceSubmit" id="btn-perforenceSubmit"></button>
	</form>
    </div>
    </main>
<%@ include file="../../part/footer.jsp"%>