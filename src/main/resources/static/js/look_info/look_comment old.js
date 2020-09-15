// 처음 게시글 들어 갈때 최신 댓글 10개 받아 오기 위해 0을보냄
// 그 이후부턴 마지막 값에 부모키 보냄
let comment_id=0;
let scrollTime = true;
let fk_look_num_Look_look_num;
let userId ;
let userNickname;

function commentReady(look_num,userid,usernickname) {

    //게시글 기본키 가져 오기
    fk_look_num_Look_look_num=look_num;
    //세션 아이디 값 가져 오기
    userId=userid;
    //세션 닉네임 값 가져 오기
    userNickname=usernickname;

    //처음 화면 들어 갔을 떄 댓글 데이터 10개 가져 오기
    commentGet();

    $(window).scroll(function () {   //스크롤 감지 이벤트
        let scroll = $(document).scrollTop(); //현재 스크롤 값
        let documentHeight = $(document).height();//문서 전체높이
        let windowHeight = window.innerHeight; //윈도우 높이
        //윈도우 높이에 스크롤값을 계속더해서 문서 전체 길이에서 50 px 앞에 스크롤이 왔을때 데이터 불러옴
        if ((windowHeight + scroll) >= documentHeight - 50) {
            if (scrollTime == true) {
                //다음 댓글 10개 가져옴
                commentGet();
            }
        }
    })
}

//댓글 html 태그 생성 하여 화면에 보여줌
function commentHTML(result,html) {

    html += '<div class=\"other_people_img\">';
    html += '<img src=displayMthumbnail/' + result.comment_sender_id + '>';
    html += '</div>';
    html += '<div class=\"other_people_name\">' + result.comment_sender_name + '</div>';
    html += '<div class="right_etc">';
    html += '<input class=\"comment_id\" value="' + result.comment_id + '" type="hidden" />';
    html += '<input class=\"comment_sender_id\" value="' + result.comment_sender_id + '" type="hidden"/>';
    html += '<img src="static/images/pen.png" alt="modify_img" height="25" width="25" class="right_pen"/>';
    html += '<img src="static/images/delete.png" alt="delete_img" height="25" width="25" class="right_delete"/>';
    html += '<img src="static/images/alarm.png" alt="alarm_img" height="25" width="25" class="alarm"/>';
    html += '</div>';
    html += '<div class=\"comment_textarea_space\">';
    html += '<textarea disabled class=\"view_comment_textarea\" placeholder=\"' + result.comment_content + '\"></textarea>';
    html += '</div>';
    html += '<div class="re_comment"> 답글 </div>';
    html += '<div class=\"comment_date\">' + result.date + '</div>';

    return html;
}

//댓글 입력 함수
function commentWrite() {
    //name 은중복을 허용해서 사용할때 0을 꼭붙여야함
    var comment_sender_id = document.getElementsByName("comment_sender_id")[0].value
    var comment_sender_name = document.getElementsByName("comment_sender_name")[0].value
    var fk_look_num_Look_look_num = document.getElementsByName("fk_look_num_Look_look_num")[0].value
    var comment_content = document.getElementsByName("comment_content")[0].value

    let data = {
        comment_sender_id: comment_sender_id,
        comment_sender_name: comment_sender_name,
        fk_look_num_Look_look_num: fk_look_num_Look_look_num,
        comment_content: comment_content,
        parents : 0, //대댓글 아니므로 값을 0줌
    };

    //데이터 json 문자열 형태로 변환
    var commentDto = JSON.stringify(data);

    if (comment_content) { //자바스크립트는 null값을 false 로 인식함
        //form태그 name인 commentForm 에 내용을 한번에 가져옴 serialize 는 form태그 내용 한번에 가져옴
        commentConfirm('', '댓글을 등록할까요?', commentDto);
    } else {
        //false null 값이니 경고문 나오기
        swal("댓글내용 입력안됨!!", "", "error")
    }

}

//댓글 등록후 댓글 바로 위에 등록 함수
function commentConfirm(msg, title, commentDto) {
    swal({
        title: title,
        text: msg,
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
                url: "/look_comment",
                type: "POST", //데이터 전달방식
                data: commentDto, //전송객체
                dataType: "json",//데이터 받을타입 데이터받는이유 댓글작성하고 추가된 글까지 받기위해서
                contentType: "application/json", //json 형태로 댓글보내기
                success: function (result, textStatus, jqxHR) {
                    //result 리턴값 textStatus
                    if (jqxHR.status == 201) {
                        swal('', '댓글을 등록하였습니다.', "success");
                        let html="";
                        html += '<div class=\"look_comment_wrap\">';
                        html += '<div class=\"look_comment\">';
                        html = commentHTML(result,html);
                        html += '</div>';
                        html += '</div>';
                         $("form[name=commentForm]").after(html);
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
        } else {
            swal("", "댓글 작성을 취소하였습니다");
        }

    });
}

//댓글 10개씩 가져 오는 함수
function commentGet() {
    //스크롤 이벤트 중복 실행 방지
    scrollTime = false
    let html="";
    $.ajax({
        url: "/look_comment_list/"+fk_look_num_Look_look_num+"/"+comment_id,
        type: "get",
        dataType: "json", //json 형태로 받기
        success: function (result) {
            //최신글 순으로  댓글 10개 받아 와서 받아온 만큼 댓글 화면에 보여줌
            for(var i=0; i<result.length; i++){
                let data = result[i];

                    html += '<div class=\"look_comment_wrap\">';
                    html += '<div class=\"look_comment\">';
                    html = commentHTML(data,html);
                    html += '</div>';
                    html += '</div>'; //look_comment_wrap 닫기
                    $(".body_root").append(html); //body 마지막에 추가
                    html="";
            }
            //마지막 댓글 기본키 를 변수값 에 넣어서 다음 데이터 10개를 받아올 수 있게 준비함
            comment_id=result[result.length-1].comment_id;

            setTimeout(function () {
                scrollTime = true;
            }, 200);//스크롤 이벤트 0.2초뒤 실행 중복 방지 위해

        },
        error: function (error) {
            if (error.status == 404) {
                swal('찾는 자료가 없습니다', '', 'error');
            }
        }
    })
}

//댓글 삭제 이벤트
$(document).on("click", ".right_delete", function (event) {
    // 가져온 이벤트 객체에 부모태그 .right_etc 에 자식객체 input에 value 값 comment_id 가져오기
    let comment_id = $(event.target).parents(".right_etc").children('.comment_id').val();

    if (userId == false) {
        //로그인 안할 경우 로그인 해라
        swal('로그인 먼저하세요', '', 'error');
    } else {
        swal({
            title: "댓글을 삭제할까요?",
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
                    url: "/look_comment/" + comment_id,
                    type: "DELETE", //데이터 전달방식
                    success: function () {
                        //삭제 이미지 부모 객체 look_comment 화면 에서 지우기
                        $(event.target).parents(".look_comment").remove();
                        swal('', '댓글을 삭제하였습니다.', "success");
                    },
                    error: function (error) {
                        //서버오류 500  권한없음 401  찾는내용없음 400
                        if (error.status == 401) {
                            swal('삭제 권한이 없습니다', '', 'error');
                        } else if (error.status == 500) {
                            swal('서버 오류 관리자에게 문의 하세요', '', 'error');
                        } else if (error.status == 404) {
                            swal('삭제할 댓글이 없습니다', '', 'error');
                        }
                    }
                })
            } else {
                swal("", "댓글 삭제를 취소하였습니다");
            }

        });
    }
});

//댓글 수정 이벤트
$(document).on("click", ".right_pen", function (event) {

    //댓글 기본키값 가져오기
    let comment_id = $(event.target).parents(".right_etc").children('.comment_id').val();
    //댓글 아이디값 가져오기
    let comment_sender_id = $(event.target).parents(".right_etc").children('.comment_sender_id').val();
    //닉네임 가져오기
    let nickName = $(event.target).parents(".look_comment").children('.other_people_name').text();
    //이벤트 부모태그 가져오기
    let look_commentTag = $(event.target).parents(".look_comment");


    if (userId != comment_sender_id) {
        //작성자 아이디 와 로그인 아디가 같아야 수정 가능
        swal("접근 권한이 없습니다", "", "error");
        return
    }

    //look_comment 밑에 태그 들 다지우기 수정화면으로 바꾸기 위해
    $(event.target).parents(".look_comment").children().remove();
    //댓글 수정할수있게 화면변경 코드
    let html = "";
    html += '<input class="comment_id" value="' + comment_id + '" type="hidden"/>';
    html += '<div class="other_people_img">';
    html += '<img src=displayMthumbnail/' + comment_sender_id + '>';
    html += '</div>';
    html += '<div class="other_people_name">' + nickName + '</div>';
    html += '<div class="comment_textarea_space">';
    html += '<textarea class="comment_textarea" placeholder="수정할 내용을 입력하세요" name="comment_content"></textarea>';
    html += '</div>';
    html += '<div class="comment_date">';
    html += '<button class="comment_change_button" value="2" type="button" >취소</button>';
    html += '<button class="comment_change_button" value="1" type="button" >저장</button>';
    html += '</div>';

    look_commentTag.append(html); //look_comment 아래에 추가
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



