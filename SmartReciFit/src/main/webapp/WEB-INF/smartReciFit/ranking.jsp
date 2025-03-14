<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>

<style>
  .ranking-entries {
    display: flex;
    justify-content: space-around;
  }
  .ranking-entry {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 200px;
    height: 250px;
    border: 1px solid #ccc;
    margin: 10px;
    text-decoration: none; /* 링크의 기본 밑줄 제거 */
    color: inherit; /* 링크의 기본 색상 제거 */
  }
  .ranking-entry img {
    width: 150px;
    height: 150px;
    object-fit: cover;
  }
  .ranking-entry .rank {
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 10px;
  }
  .ranking-entry .recipe-title {
    font-size: 18px;
    text-align: center;
  }
</style>

<div class="main-content">
  <!-- API 조회수 랭킹 -->
  <div class="ranking-section">
    <div class="title">이번 달의 API 조회수 랭킹</div>
    <div class="ranking-entries">
      <a href="#" class="ranking-entry"> <!-- href 부분은 do?num=어쩌구 저쩌구로 갈것 일단 임시로 -->
        <img src="placeholder.jpg" alt="Recipe Image"> <!-- 임시 사진 -->
        <div class="rank">1</div>
        <div class="recipe-title">레시피 제목</div>
      </a>
      <a href="#" class="ranking-entry">
        <img src="placeholder.jpg" alt="Recipe Image">
        <div class="rank">2</div>
        <div class="recipe-title">레시피 제목</div>
      </a>
      <a href="#" class="ranking-entry">
        <img src="placeholder.jpg" alt="Recipe Image">
        <div class="rank">3</div>
        <div class="recipe-title">레시피 제목</div>
      </a>
    </div>
  </div>

  <!-- 유저 랭킹 -->
  <div class="ranking-section">
    <div class="title">이번 달의 유저 랭킹</div>
    <div class="ranking-entries">
      <a href="#" class="ranking-entry">
        <img src="placeholder.jpg" alt="Recipe Image">
        <div class="rank">1</div>
        <div class="recipe-title">레시피 제목</div>
      </a>
      <a href="#" class="ranking-entry">
        <img src="placeholder.jpg" alt="Recipe Image">
        <div class="rank">2</div>
        <div class="recipe-title">레시피 제목</div>
      </a>
      <a href="#" class="ranking-entry">
        <img src="placeholder.jpg" alt="Recipe Image">
        <div class="rank">3</div>
        <div class="recipe-title">레시피 제목</div>
      </a>
    </div>
  </div>
</div>

<%@ include file="../../part/footer.jsp"%>

