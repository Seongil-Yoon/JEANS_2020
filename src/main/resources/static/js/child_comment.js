
$(document).on("click", ".re_comment", function (event) {
    if (sessionStorage.getItem("userid") == null) {
        //로그인 안할경우 로그인 해라
        swal('로그인 먼저하세요', '', 'error');

    }else {

        //이벤트 부모태그 가져오기
        let look_commentTag = $(event.target).parents(".look_comment");

        $(event.target).remove();

        let html = "";

        html += '<div class=\"child_look_comment\" >';
        html += '<input class="comment_id" value="' + sessionStorage.getItem("userid") + '" type="hidden"/>';
        html += '<div class="other_people_img">';
        html += '<img src=displayMthumbnail?id='+sessionStorage.getItem("userid")+'>';
        html += '</div>';
        html += '<div class="other_people_name">' + sessionStorage.getItem("usernickname") + '</div>';
        html += '<div class="comment_textarea_space">';
        html += '<textarea style="background-color:#F6F6F6 " class="child_comment_textarea" placeholder="답글 내용을 입력 하세요"></textarea>';
        html += '</div>';
        html += '<div class="comment_date">';
        html += '<button class="comment_change_button" value="2" type="button" >취소</button>';
        html += '<button class="comment_change_button" value="1" type="button" >저장</button>';
        html += '</div>';
        html += '</div>';

        look_commentTag.after(html) //look_comment 아래에 추가

    }

});