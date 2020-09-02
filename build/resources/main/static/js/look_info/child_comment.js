let childEvent=true; //대댓글 이벤트 중복 실행 방지 위해
let parents_comment_id //대댓글 작성할 부모댓글 기본키

//대댓글 출력 html 태그
function childHTML(result,html) {

    html += '<div class="child_comment_wrap">';
    html += '<input class="child_comment_id" value="' + result.comment_sender_id + '" type="hidden"/>';
    html += '<input class="child_comment_nickname" value="' + result.comment_sender_name + '" type="hidden"/>';
    html += '<input class="parents_comment_id" value="' + result.comment_id + '" type="hidden"/>';
    html += '<div class="other_people_img">';
    html += '<img src=displayMthumbnail/' + result.comment_sender_id + '>';
    html += '</div>';
    html += '<div class="child_other_people_name">' + result.comment_sender_name + '</div>';
    html += '<div class="comment_textarea_space">';
    html += '<textarea disabled class="child_comment_content" placeholder=\"' + result.comment_content + '\"></textarea>';
    html += '</div>';
    html += '</div>';

    return html;
}

//대댓글 입력 html 태그
function childWriteHtml (userid,userNickname,parents_comment_id){

    let html="";

    html += '<div class=\"child_look_comment\" >';
    html += '<input class="child_comment_id" value="' + userid + '" type="hidden"/>';
    html += '<input class="child_comment_nickname" value="' + userNickname + '" type="hidden"/>';
    html += '<input class="parents_comment_id" value="' + parents_comment_id + '" type="hidden"/>';
    html += '<div class="other_people_img">';
    html += '<img src=displayMthumbnail/' + userid + '>';
    html += '</div>';
    html += '<div class="child_other_people_name">' + userNickname + '</div>';
    html += '<div class="comment_textarea_space">';
    html += '<textarea  class="child_comment_content" placeholder="내용을 입력 하세요"></textarea>';
    html += '</div>';
    html += '<div class="comment_date">';
    html += '<button class="child_comment_change_button" value="2" type="button" >취소</button>';
    html += '<button class="child_comment_change_button" value="1" type="button" >저장</button>';
    html += '</div>';
    html += '</div>';

    return html;

}

//답글 (숫자)개 보기 할경우 이벤트
$(document).on("click", ".re_comment_more", function (event) {

    //이벤트 부모 태그 가져 오기
    let look_commentTag = $(event.target).parents(".look_comment");
    //부모 댓글 기본키 값 가져 오기  find 는 후손에 값 가져옴
    parents_comment_id=look_commentTag.find(".comment_id").val();

    alert("답글 더보기 이벤트");

});

//답글 누르면 이벤트
$(document).on("click", ".re_comment", function (event) {

    //이벤트 부모 태그 가져 오기
    let look_commentTag = $(event.target).parents(".look_comment");
    //부모 댓글 기본키 값 가져 오기  find 는 후손에 값 가져옴
    parents_comment_id=look_commentTag.find(".comment_id").val();

    if (userId == false) {
        //로그인 안할경우 로그인 해라
        swal('로그인 먼저하세요', '', 'error');
    }else if(childEvent == true) {
            childEvent=false; //이벤트 중복 실행방지
            //look_comment 아래에 추가
            look_commentTag.after(childWriteHtml(userId,userNickname,parents_comment_id))
    }

});

//취소나 저장 버튼 누를 경우 이벤트
$(document).on("click", ".child_comment_change_button", function (event) {
    //이벤트 부모태그 가져오기
    let child_look_commentTag = $(event.target).parents(".child_look_comment");
    //대댓글 내용 가져오기
    let child_comment_content=child_look_commentTag.find(".child_comment_content").val();

    let data = {
        comment_sender_id: userId,
        comment_sender_name: userNickname,
        fk_look_num_Look_look_num: fk_look_num_Look_look_num,
        comment_content: child_comment_content,
        parents : parents_comment_id, //부모댓글 기본키 값 줌
        ref_count: 0
    };

    //데이터 json 문자열 형태로 변환
    let commentDto = JSON.stringify(data);

    //저장 event 값 1   취소 event 값 2
    //저장 버튼 눌러서 이벤트 값이 1인데  대댓글 내용이 null 값일 경우 에러 발생
    if(child_comment_content==false&&$(event.target).val()==1){
        swal('답글 내용을 입력 하세요', '', 'error');
         return ;
    }else if($(event.target).val()==1) {
        //대댓글 내용이 null 값이 아니고 저장 버튼 눌렀을 경우 ajax 로 저장
        $.ajax({
            url: "/look_comment",
            type: "POST", //데이터 전달방식
            data: commentDto, //전송객체
            dataType: "json",//데이터 받을타입
            contentType: "application/json", //json 형태로 댓글보내기
            success: function (result, textStatus, jqxHR) {
                //result 리턴값 textStatus
                if (jqxHR.status == 201) {
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
    childEvent=true;

});