
function logout() {
        $.ajax({
            url:"/session", //저장되는 위치가 session 이라 url을 rest형식에 맟춰 /session으로함
            type:"DELETE", //데이터 전달방식 로으아웃이니 세션을 지워서 delete 사용
            success:function () {
                //로그아웃후  메인 화면 이동
                location.href = "/main"
            },
            error: function() {
                swal("","로그아웃 실패", "error");
            }
        })
}

