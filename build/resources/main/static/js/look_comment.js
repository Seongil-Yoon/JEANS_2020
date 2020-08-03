// 처음 게시글 들어 갈때 초기 최신 댓글 10개 받아 오기 위해 20억 기본값 으로 보냄
let comment_id= 2000000000;
let scrollTime = true;
let fk_look_num_Look_look_num;
let userId="";

function commentReady(look_num,userid) {
//게시글 기본키 가져오기
fk_look_num_Look_look_num=look_num;
//수정 삭제 할때 권한 문제 떄문에 세션 아이디 값 가져 오기
userId=userid;

    $(window).scroll(function () {   //스크롤 감지 이벤트
        let scroll = $(document).scrollTop(); //현재 스크롤 값
        let documentHeight = $(document).height();//문서 전체높이
        let windowHeight = window.innerHeight; //윈도우 높이
        //윈도우 높이에 스크롤값을 계속더해서 문서 전체 길이에서 50 px 앞에 스크롤이 왔을때 데이터 불러옴
        if ((windowHeight + scroll) >= documentHeight - 50) {
            if (scrollTime == true) {
                //스크롤 이벤트 중복 실행 방지
                scrollTime = false
                //다음 댓글 10개 가져옴
                commentGet();
            }
        }
    })
}

//댓글 html 태그 생성 하여 화면에 보여줌
function commentHTML(result) {

    let html="";

    html += '<div class=\"look_comment\">';
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
    html += '</div>';

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

                         $("form[name=commentForm]").after(commentHTML(result));
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

    $.ajax({
        url: "/look_comment_list/"+fk_look_num_Look_look_num+"/"+comment_id,
        type: "get",
        dataType: "json", //json 형태로 받기
        success: function (result) {
            //최신글 순으로  댓글 10개 받아 와서 받아온 만큼 댓글 화면에 보여줌
            for(var i=0; i<result.length; i++){
                let data = result[i];
                $(".body_root").append( commentHTML(data)); //body 마지막에 추가
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





