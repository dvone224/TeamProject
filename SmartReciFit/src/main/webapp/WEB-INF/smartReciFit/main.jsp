
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>

    <main>
      <div class="inner">
        <h2> 여기는 메인페이지</h2>
        	<form action="${ctx}/userJoin.do" method="post">
	<button name=test id=test>test</button>
	</form>
      </div>
    </main>
<%@ include file="../../part/footer.jsp"%>