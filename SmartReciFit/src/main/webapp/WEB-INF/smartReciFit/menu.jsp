<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="admin-menu">
    <h3>회원 관리</h3>
    <ul>
        <li><a href="${ctx}/userList.do">전체 회원</a></li>
        <li><a href="${ctx}/adminUser.do">관리/운영</a></li>
        <li><a href="${ctx}/inquiry.do">문의 내역</a></li>
    </ul>
    <h3>콘텐츠 관리</h3>
    <ul>
        <li><a href="${ctx}/mainPage.do">레시피</a></li>
        <li><a href="${ctx}/playList.do">랭킹</a></li>
        <li><a href="${ctx}/trendInfo.do">후기</a></li>
        <li><a href="${ctx}/keyword.do">EVENT</a></li>
    </ul>
    <h3>인사이트 분석</h3>
    <ul>
        <li><a href="${ctx}/dailyStatus.do">일간 현황</a></li>
        <li><a href="${ctx}/visitAnalysis.do">방문 분석</a></li>
        <li><a href="${ctx}/userAnalysis.do">사용자 분석</a></li>
        <li><a href="${ctx}/viewRanking.do">조회수 순위</a></li>
    </ul>
</div>