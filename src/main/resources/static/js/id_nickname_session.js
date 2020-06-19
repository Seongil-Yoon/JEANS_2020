
let nickname=sessionStorage.getItem("usernickname");

//로그인 했으면 작성권한페이지 가게 나오기
if(sessionStorage.getItem("userid")!=null) {
    $(".look_write").html('look_write');
    $(".logout_login").html('logout');
    $(".my_picture").html(' <img src="static/images/mypicture.png" alt="pitcture" height="35" width="40"/>');
    $(".user_nickname").html(nickname);
}else {
    $(".look_write").html('회원가입');
    $(".logout_login").html('login');
}
function logo_right_click() {
    if(sessionStorage.getItem("userid")!=null) {
        location.href = "/look_write";
    }else{
        location.href="/joinUser";
    }
}

function logout_login_click() {
    if(sessionStorage.getItem("userid")!=null) {
        $.ajax({
            url:"/session", //저장되는 위치가 session 이라 url을 rest형식에 맟춰 /session으로함
            type:"DELETE", //데이터 전달방식 로으아웃이니 세션을 지워서 delete 사용
            success:function () {
                //logout 하여 자바스크립트 session 지우기
                sessionStorage.removeItem("userid");
                sessionStorage.removeItem("usernickname");
                swal('', '로그아웃 하였습니다.', "");
                    setTimeout(function(){  location.href="/main"},1500);
            },
            error: function() {
                swal("","로그아웃 실패", "error");
            }
        })
    }else{
        //session 값없으면 로그인 페이지가기
        location.href="/loginUser";
    }
}

