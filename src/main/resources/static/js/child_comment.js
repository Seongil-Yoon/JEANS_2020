
let eventResult=true; //대댓글 이벤트 중복 실행 방지 위해

//답글 누르면 이벤트
$(document).on("click", ".re_comment", function (event) {


    if (sessionStorage.getItem("userid") == null) {
        //로그인 안할경우 로그인 해라
        swal('로그인 먼저하세요', '', 'error');
    }else if(eventResult == true) {
            eventResult=false; //이벤트 중복 실행방지
            //이벤트 부모 태그 가져 오기
            let look_commentTag = $(event.target).parents(".look_comment");
            //댓글 아이디값 가져오기  find는 후손에 값 가져옴
            let comment_id=look_commentTag.find(".comment_id").val();

            let html = "";

            html += '<div class=\"child_look_comment\" >';
            html += '<input class="child_comment_id" value="' + sessionStorage.getItem("userid") + '" type="hidden"/>';
            html += '<input class="child_comment_nickname" value="' + sessionStorage.getItem("usernickname") + '" type="hidden"/>';
            html += '<input class="fk_comment_id" value="' + comment_id + '" type="hidden"/>';
            html += '<div class="other_people_img">';
            html += '<img src=displayMthumbnail/' + sessionStorage.getItem("userid") + '>';
            html += '</div>';
            html += '<div class="child_other_people_name">' + sessionStorage.getItem("usernickname") + '</div>';
            html += '<div class="comment_textarea_space">';
            html += '<textarea  class="child_comment_content" placeholder="답글 내용을 입력 하세요"></textarea>';
            html += '</div>';
            html += '<div class="comment_date">';
            html += '<button class="child_comment_change_button" value="2" type="button" >취소</button>';
            html += '<button class="child_comment_change_button" value="1" type="button" >저장</button>';
            html += '</div>';
            html += '</div>';

            look_commentTag.after(html) //look_comment 아래에 추가
    }
});

//취소나 저장 버튼 누를 경우 이벤트
$(document).on("click", ".child_comment_change_button", function (event) {
    //이벤트 부모태그 가져오기
    let child_look_commentTag = $(event.target).parents(".child_look_comment");
    //대댓글 작성자 아이디
    let child_comment_id=child_look_commentTag.find(".child_comment_id").val();
    //대댓글 작성자 닉네임
    let child_comment_nickname=child_look_commentTag.find(".child_comment_nickname").val();
    //대댓글 속할 댓글 기본키 가져 오기
    let fk_comment_id=child_look_commentTag.find(".fk_comment_id").val();
    //대댓글 내용 가져오기
    let child_comment_content=child_look_commentTag.find(".child_comment_content").val();

    let data = {
        child_comment_id: child_comment_id,
        child_comment_nickname: child_comment_nickname,
        fk_comment_id: fk_comment_id,
        child_comment_content: child_comment_content,
    };
    //데이터 json 문자열 형태로 변환
    let childCommentDto = JSON.stringify(data);
    //저장 버튼 눌렀 는데 대댓글 이 null 값일 경우 에러 발생
    if(child_comment_content==false&&$(event.target).val()==1){
        swal('답글 내용을 입력 하세요', '', 'error');
         return ;
    }else if($(event.target).val()==1) {
        //저장 버튼 누를경우 ajax 로 대댓글 저장
        alert("저장 버튼");
        $.ajax({
            url: "/look_ChildComment",
            type: "POST", //데이터 전달방식
            data: childCommentDto, //전송객체
            dataType: "json",//데이터 받을타입
            contentType: "application/json", //json 형태로 댓글보내기
            success: function (result, textStatus, jqxHR) {
                //result 리턴값 textStatus
                if (jqxHR.status == 201) {
                    let html = "";
                    alert("대댓글 등록 성공!!");

                    // $("form[name=commentForm]").after(html); //form태그 name이commentForm 인거 바로밑에 추가하기
                }
            },
            error: function (error) {
                //서버오류 500  권한없음  401
                if (error.status == 401) {
                    swal('접근 권한이 없습니다', '', 'error');
                } else if (error.status == 500) {
                    swal('서버 오류 관리자에게 문의 하세요', '', 'error');
                }
            }
        })
    }
    // 저장 이나 취소 버튼을 눌렀 으니 대댓글 입력 화면을 지움
    child_look_commentTag.remove();
    //다시 대댓글 화면 나올수 있게 바꿈
    eventResult=true;

});