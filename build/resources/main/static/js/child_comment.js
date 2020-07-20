let eventResult=true; //대댓글 이벤트 중복 실행 방지 위해

//답글누르면 경우 이벤트
$(document).on("click", ".re_comment", function (event) {
    if (sessionStorage.getItem("userid") == null) {
        //로그인 안할경우 로그인 해라
        swal('로그인 먼저하세요', '', 'error');
    }else if(eventResult == true) {
            eventResult=false; //이벤트 중복 실행방지
            //이벤트 부모태그 가져오기
            let look_commentTag = $(event.target).parents(".look_comment");

            let html = "";

            html += '<div class=\"child_look_comment\" >';
            html += '<input class="comment_id" value="' + sessionStorage.getItem("userid") + '" type="hidden"/>';
            html += '<div class="other_people_img">';
            html += '<img src=displayMthumbnail?id=' + sessionStorage.getItem("userid") + '>';
            html += '</div>';
            html += '<div class="child_other_people_name">' + sessionStorage.getItem("usernickname") + '</div>';
            html += '<div class="comment_textarea_space">';
            html += '<textarea style="background-color:#F6F6F6 " class="child_comment_textarea" placeholder="답글 내용을 입력 하세요"></textarea>';
            html += '</div>';
            html += '<div class="comment_date">';
            html += '<button class="child_comment_change_button" value="2" type="button" >취소</button>';
            html += '<button class="child_comment_change_button" value="1" type="button" >저장</button>';
            html += '</div>';
            html += '</div>';

            look_commentTag.after(html) //look_comment 아래에 추가
    }
});

$(document).on("click", ".child_comment_change_button", function (event) {
    //이벤트 부모태그 가져오기
    let child_look_commentTag = $(event.target).parents(".child_look_comment");

    if($(event.target).val() == 2){
         //취소 버튼 누를 경우 대댓글 작성 화면 지우기
         child_look_commentTag.remove();
    }else {
        alert("저장 버튼");
    }

    eventResult=true;//다시 대댓글 작성 할수 있게 바꿈

});