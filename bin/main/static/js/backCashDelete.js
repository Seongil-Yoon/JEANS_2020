//조회수 초기화 위해 뒤로갔을때 캐시 지우기
if (window.persisted || (window.performance && window.performance.navigation.type == 2)) {
    window.location.reload(); //새로고침 다시 불러오기
}