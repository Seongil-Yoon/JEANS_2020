function userLogin() {

    let userid=document.getElementsByName("userid")[0].value;
    let password=document.getElementsByName("password")[0].value;

        if(userid!=false&&password!=false){ //자바스크립트는 null값을 false 로 인식
        // var Data = $('[name=commentForm]').serialize();
        // commentConfirm('', '댓글을 등록할까요?',Data);
            var Data = $('[name=loginForm]').serialize();
            $.ajax({
                url:"/session", //저장되는 위치가 session 이라 url을 rest형식에 맟춰 /session으로함
                type:"post", //데이터 전달방식
                data : Data, //전송객체
                dataType: "json",//데이터 받을타입 닉네임 아이디 받아옴
                    success:function (result) {
                    if(result.usernickname!=null) {
                            sessionStorage.setItem("userid", result.userid); //자바스크립트에 쓸수있게 아이디 저장
                            sessionStorage.setItem("usernickname", result.usernickname); // 닉네임 저장
                            swal("로그인 성공", "", "success");
                            //로그인 성공후 메인 화면 이동
                            setTimeout(function(){  location.href="/main"},1500);
                        }else {
                            swal("","아이디 비밀번호를 다시 입력하세요", "error");
                        }
                    },
                error: function() {

                }
            })
        }else {
            swal("입력안된 사항이 있습니다!!", "", "error");
        }

}