window.addEventListener('scroll', () => {
  const scrollTop = document.documentElement.scrollTop;
  const scrollHeight = document.documentElement.scrollHeight;
  const clientHeight = document.documentElement.clientHeight;

  if (scrollHeight - scrollTop <= clientHeight + 200) { // 버퍼값 설정
    fetch(`${ctx}/recipeInfiniteScroll.do`, loadMoreData);
  }
});

function loadMoreData() {
	
}