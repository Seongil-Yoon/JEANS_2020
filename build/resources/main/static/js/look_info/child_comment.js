let childEvent=true; //대댓글 이벤트 중복 실행 방지 위해
let parents_comment_id //대댓글 작성할 부모댓글 기본키

//대댓글 출력 html 태그
function childHTML(result) {

    let html="";
    html += '<div class="child_comment_wrap">';
    html += '<input class="child_comment_sender_id" value="' + result.comment_sender_id + '" type="hidden"/>';
    html += '<input class="child_comment_sender_name" value="' + result.comment_sender_name + '" type="hidden"/>';
    html += '<input class="child_comment_id" value="' + result.comment_id + '" type="hidden"/>';
    html += '<div class="other_people_img">';
    html += '<img src=displayMthumbnail/' + result.comment_sender_id + '>';
    html += '</div>';
    html += '<div class="child_other_people_name">' + result.comment_sender_name + '</div>';
    html += '<div class="comment_textarea_space">';
    html += '<textarea disabled class="child_comment_content" placeholder=\"' + result.comment_content + '\"></textarea>';
    html += '<button class="child_sujung_button" value="2" type="button" >수정</button>';
    html += '<button class="child_delete_button" value="1" type="button" >삭제</button>';
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
    let look_comment_wrap = $(event.target).parents(".look_comment_wrap");
    //대댓글 작성하고 대댓글 보기하면 겹처서 한번 지워줌 그리고 대댓글 숨기기할때도 사용
    look_comment_wrap.find(".child_comment_wrap").remove();

    //eventDecision 이 1이면 대댓글 보여주면서 답글 숨기기로 글자변경
    if(look_comment_wrap.find(".eventDecision").val()==1){
        $(event.target).text("답글 숨기기");
        //중복 방지 위해 eventDecision 2로 값바꿈
        look_comment_wrap.find(".eventDecision").val('2');

        $.ajax({
            //대댓글 부모기본키 넘겨서 받아오기
            url: "/child_comment/"+look_comment_wrap.find(".comment_id").val(),
            type: "get",
            dataType: "json", //json 형태로 받기
            success: function (result) {

                for(var i=0; i<result.length; i++){
                    let data = result[i];
                    //댓글태그 안에 look_comment_wrap 안에 마지막에 추가
                    look_comment_wrap.append(childHTML(data));
                }

            },
            error: function (error) {
                if (error.status == 404) {
                    swal('대댓글이 없습니다', '', 'error');
                }
            }
        })
    }else {
        //반대경우는 대댓글 감추고 답글 더보기로 글자 바꿈
        $(event.target).text("답글 더보기");
        look_comment_wrap.find(".eventDecision").val('1');
    }

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
            //look_comment 밖에 바로 아래에 추가 look_comment_wrap 안에 포함되어있음
            look_commentTag.after(childWriteHtml(userId,userNickname,parents_comment_id))
    }

});

//취소나 저장 버튼 누를 경우 이벤트
$(document).on("click", ".child_comment_change_button", function (event) {
    //이벤트 부모태그 가져오기
    let child_look_commentTag = $(event.target).parents(".child_look_comment");
    //대댓글 내용 가져오기
    let child_comment_content=child_look_commentTag.find(".child_comment_content").val();
    let look_comment_wrap = child_look_commentTag.parents(".look_comment_wrap");

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
                    look_comment_wrap.append(childHTML(result));
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

//삭제 이벤트
$(document).on("click", ".child_delete_button", function (event) {
    //이벤트 부모태그 가져오기
    let child_comment_wrap = $(event.target).parents(".child_comment_wrap");

    let child_comment_id=child_comment_wrap.find(".child_comment_id").val();
    //로그인한 아이디와 작성자 아이디를 비교
    if(userId==child_comment_wrap.find(".child_comment_sender_id").val()){
        swal({
            title: "답글을 삭제할까요?",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-danger",
            confirmButtonText: "예",
            cancelButtonText: "아니오",
            closeOnConfirm: false,
            closeOnCancel: false
        }, function (isConfirm) {
            if (isConfirm) {
                $.ajax({
                    //댓글과 대댓글 둘다 중복을 줄이기 위해 같은 삭제 api 사용
                    url: "/look_comment/" + child_comment_id,
                    type: "DELETE",
                    success: function () {
                        //삭제하여서 화면에서 답글 html 태그 지우기
                        child_comment_wrap.remove();
                        swal('', '답글을 삭제하였습니다.', "success");
                    },
                    error: function (error) {
                        //서버오류 500  권한없음 401  찾는내용없음 400
                        if (error.status == 401) {
                            swal('삭제 권한이 없습니다', '', 'error');
                        } else if (error.status == 500) {
                            swal('서버 오류 관리자에게 문의 하세요', '', 'error');
                        } else if (error.status == 404) {
                            swal('삭제할 답글이 없습니다', '', 'error');
                        }
                    }
                })

            } else {
                swal("", "답글 삭제를 취소 하였습니다");
            }

        });
    }else{
        swal("", "작성자만 답글을 삭제 할수 있습니다","error");
    }

});

//답글 수정 이벤트
$(document).on("click", ".child_sujung_button", function (event) {
    //이벤트 부모태그 가져오기
    let child_comment_wrap = $(event.target).parents(".child_comment_wrap");
    //기본키값 가져오기
    let child_comment_id=child_comment_wrap.find(".child_comment_id").val();
    //작성자 아이디값 가져오기
    let child_comment_sender_id=child_comment_wrap.find(".child_comment_sender_id").val();
    //작성자 닉네임값 가져오기
    let child_comment_sender_name=child_comment_wrap.find(".child_comment_sender_name").val();

    alert(child_comment_id+" 기본키값 가져오기");
    alert(child_comment_sender_id+"아이디값 가져오기");
    alert(child_comment_sender_name+"닉네임값 가져오기");

    // if(userId==child_comment_wrap.find(".child_comment_sender_id").val()){
    //     swal({
    //         title: "답글을 수정할까요?",
    //         text: "",
    //         type: "warning",
    //         showCancelButton: true,
    //         confirmButtonClass: "btn-danger",
    //         confirmButtonText: "예",
    //         cancelButtonText: "아니오",
    //         closeOnConfirm: false,
    //         closeOnCancel: false
    //     }, function (isConfirm) {
    //         if (isConfirm) {
    //
    //             child_comment_wrap.children().remove();
    //
    //             let html = "";
    //             html += '<input class="child_comment_id" value="' + child_comment_id + '" type="hidden"/>';
    //             html += '<div class="other_people_img">';
    //             html += '<img src=displayMthumbnail/' + result.comment_sender_id + '>';
    //             html += '</div>';
    //             html += '<div class="child_other_people_name">' + result.comment_sender_name + '</div>';
    //             html += '<div class="comment_textarea_space">';
    //             html += '<textarea disabled class="child_comment_content" placeholder=\"' + result.comment_content + '\"></textarea>';
    //             html += '<button class="child_sujung_button" value="2" type="button" >수정</button>';
    //             html += '<button class="child_delete_button" value="1" type="button" >삭제</button>';
    //             html += '</div>';
    //
    //             child_comment_wrap.append(html); //child_comment_wrap 아래에 추가
    //
    //             $.ajax({
    //                 //댓글과 대댓글 둘다 중복을 줄이기 위해 같은 삭제 api 사용
    //                 url: "/look_comment/" + child_comment_id,
    //                 type: "DELETE",
    //                 success: function () {
    //                     swal('', '답글을 수정하였습니다.', "success");
    //                 },
    //                 error: function (error) {
    //                     //서버오류 500  권한없음 401  찾는내용없음 400
    //                     if (error.status == 401) {
    //                         swal('수정 권한이 없습니다', '', 'error');
    //                     } else if (error.status == 500) {
    //                         swal('서버 오류 관리자에게 문의 하세요', '', 'error');
    //                     } else if (error.status == 404) {
    //                         swal('수정할 댓글이 없습니다', '', 'error');
    //                     }
    //                 }
    //             })
    //         } else {
    //             swal("", "답글 수정을 취소 하였습니다");
    //         }
    //
    //     });
    // }else{
    //     swal("", "작성자만 답글을 삭제 할수 있습니다","error");
    // }

});

//수정 다하고 저장 버튼 이나 취소 버튼 누를 경우 이벤트
$(document).on("click", ".comment_change_button", function (event) {
    //이벤트 부모태그인 look_comment 값 가져오기
    let look_commentTag = $(event.target).parents(".look_comment");
    //댓글 기본키값 가져오기
    let comment_id = look_commentTag.children('.comment_id').val();
    //수정내용 가져오기
    let content = look_commentTag.find(".comment_textarea").val();
    // 값을 key value 형태로 변환
    let contentData = {
        comment_content: content,
    };

    //저장 버튼누르면 1  취소는 2
    if ($(event.target).val() == 1) {
        $.ajax({
            url: "/look_comment/" + comment_id,
            type: "PATCH", //데이터 전달방식
            data: JSON.stringify(contentData), //json 문자열로 반환 해서보냄
            contentType: "application/json", //json 형태로 보내기
            success: function (result, textStatus, jqxHR) {
                look_commentTag.children().remove();
                let html="";
                //수정 하여 수정된 html 화면으로 돌려주기
                look_commentTag.append(commentHTML(result,html));
            },
            error: function (error) {
                //서버오류 500  권한없음 401  찾는내용없음 400
                if (error.status == 404) {
                    swal('수정할 댓글이 없습니다', '', 'error');
                } else if (error.status == 500) {
                    swal('서버 오류 관리자에게 문의 하세요', '', 'error');
                } else if (error.status == 401) {
                    swal('수정할 권한이 없습니다', '', 'error');
                }
            }
        })
    } else {
        $.ajax({
            url: "/look_comment/" + comment_id,
            type: "GET", //데이터 전달방식
            success: function (result) {
                look_commentTag.children().remove();
                let html="";
                //수정취소 를 하여 원래 html 화면 으로 돌려 주기
                look_commentTag.append(commentHTML(result,html));
            },
            error: function (error) {
                //서버오류 500  권한없음 401  찾는내용없음 400
                if (error.status == 404) {
                    swal('찾는 댓글이 없습니다', '', 'error');
                } else if (error.status == 500) {
                    swal('서버 오류 관리자에게 문의 하세요', '', 'error');
                }
            }
        })
    }
});