let isEnd=false; //더이상 가져올값이 없으면 중지하기위한 변수
let num=0; //댓글 세개씩 나오기 위한변수
let scrollTime = true;

function commentReady(lookNum){
    var lookNum=lookNum;
        commentList(lookNum); //스크롤 전 처음에 3개 댓글 출력
        $(window).scroll(function(){   //스크롤 감지 이벤트
            let scroll = $(document).scrollTop(); //현재 스크롤 값
            let documentHeight = $(document).height();//문서 전체높이
            let windowHeight= window.innerHeight; //윈도우 높이
            //윈도우 높이에 스크롤값을 계속더해서 문서 전체 길이에서 50 px 앞에 스크롤이 왔을때 데이터 불러옴
            if((windowHeight + scroll) >= documentHeight-50){
                if(scrollTime==true){
                    commentList(lookNum);
                }
            }
        })

}

function comment() {
    //name 은중복을 허용해서 사용할때 0을 꼭붙여야함
    var comment_content=document.getElementsByName("comment_content")[0].value
    if(comment_content){ //자바스크립트는 null값을 false 로 인식함
        //form태그 name인 commentForm 에 내용을 한번에 가져옴 serialize 는 form태그 내용 한번에 가져옴
        var Data = $('[name=commentForm]').serialize();
        commentConfirm('', '댓글을 등록할까요?',Data);
    }else {
        //false null 값이니 경고문 나오기
        swal("댓글내용 입력안됨!!", "", "error")
    }

}


function commentConfirm(msg, title,Data) {
    swal({
        title : title,
        text : msg,
        type : "warning",
        showCancelButton : true,
        confirmButtonClass : "btn-danger",
        confirmButtonText : "예",
        cancelButtonText : "아니오",
        closeOnConfirm : false,
        closeOnCancel : false
    }, function(isConfirm) {
        if (isConfirm) {
            $.ajax({
                url:"/look_comment",
                type:"POST", //데이터 전달방식
                data : Data, //전송객체
                dataType: "json",//데이터 받을타입 데이터받는이유 댓글작성하고 추가된 글까지 받기위해서
                success:function (result,textStatus,jqxHR) {
                   //result 리턴값 textStatus
                    if(jqxHR.status==201){
                        swal('', '댓글을 등록하였습니다.', "success");
                        let a="";

                        a+='<div class=\"look_comment\">';
                        a+='<div class=\"other_people_img\">';
                        a+='<img src=\"static/images/mypicture.png\" alt=\"other_people_imgage\" height=\"50\" width=\"60\"/>';
                        a+='</div>';
                        a+='<div class=\"other_people_name\">'+result.comment_sender_name+'</div>';
                        a+='<div class="right_etc">';
                        a+='<input class=\"comment_id\" value="'+result.comment_id+'" type="hidden" />';
                        a+='<img src="static/images/pen.png" alt="modify_img" height="25" width="25" class="right_pen"/>';
                        a+='<img src="static/images/delete.png" alt="delete_img" height="25" width="25" class="right_delete"/>';
                        a+='<img src="static/images/alarm.png" alt="alarm_img" height="25" width="25" class="alarm"/>';
                        a+='</div>';
                        a+='<div class=\"comment_textarea_space\">';
                        a+='<textarea style=\"background-color:#F6F6F6 \"disabled class=\"comment_textarea\" placeholder=\" '+result.comment_content+' \"></textarea>';
                        a+='</div>';
                        a+='<div class="re_comment"> 답글 </div>';
                        a+='<div class=\"comment_date\">'+result.date+'</div>';
                        a+='</div>';

                        $("form[name=commentForm]").after(a); //form태그 name이commentForm 인거 바로밑에 추가하기
                    }
                },
                error: function(error) {
                    //서버오류 500  권한없음  401
                    if(error.status==401){
                        swal('접근 권한이 없습니다','' ,'error');
                    }else if(error.status==500){
                        swal('서버 오류 관리자에게 문의 하세요','' ,'error');
                    }
                }
            })
        }else{
            swal("","댓글 작성을 취소하였습니다");
        }

    });
}

function commentList(lookNum) {
    if(isEnd == true) { //가져올값으 없으므로 리턴
        return;
    }
    let look_num =lookNum;
    scrollTime=false//스크롤이벤트 중복실행방지
    $.ajax({
        url: "/look_comment_all/"+look_num, //요청url
        type: "get",//데이터 전달방식
        dataType: "json", //json 으로 받기
        success:function (result) {
             //0 1 2   3 4 5   6 7 8
            for(var i=num; i<num+3; i++) {
                let html="";
                if( result.length==i){
                   //더이상 가져올 값이 없으므로 true 로 바꿔줌
                    isEnd=true;
                }
                html+='<div class=\"look_comment\" >';
                html+='<div class=\"other_people_img\">';
                html+='<img src=\"static/images/mypicture.png\" alt=\"other_people_imgage\" height=\"50\" width=\"60\"/>';
                html+='</div>';
                html+='<div class=\"other_people_name\">'+result[i].comment_sender_name+'</div>';
                html+='<div class="right_etc">';
                html+='<input class=\"comment_id\" value="'+result[i].comment_id+'" type="hidden"/>';
                html+='<img src="static/images/pen.png" alt="modify_img" height="25" width="25" class="right_pen"/>';
                html+='<img src="static/images/delete.png" alt="delete_img" height="25" width="25" class="right_delete" />';
                html+='<img src="static/images/alarm.png" alt="alarm_img" height="25" width="25" class="alarm"/>';
                html+='</div>';
                html+='<div class=\"comment_textarea_space\">';
                html+='<textarea style=\"background-color:#F6F6F6 \"disabled class=\"comment_textarea\" placeholder=\" '+result[i].comment_content+' \"></textarea>';
                html+='</div>';
                html+='<div class="re_comment"> 답글 </div>';
                html+='<div class=\"comment_date\">'+result[i].date+'</div>';
                html+='</div>';


                $(".body_root").append(html); //body 마지막에 추가
            }
            num+=3; //다음3개 가져오기 위해 3더함
            setTimeout(function(){scrollTime = true;},200);//스크롤이벤트 0.2초뒤실행 중복방지위해
        },
        error: function(error) {
            if(error.status==400) {
                swal('찾는 자료가 없습니다', '', 'error');
            }
        }
    })
}

$(document).on("click",".right_delete",function(event) {
    // 가져온 이벤트 객체에 부모태그 .right_etc 에 자식객체 input에 value 값 comment_id 가져오기
    let comment_id =$(event.target).parents(".right_etc").children('.comment_id').val();

    if(sessionStorage.getItem("userid")==null){
        //로그인 안할경우 로그인 해라
        swal('로그인 먼저하세요','','error');
    }else{
        swal({
            title : "댓글을 삭제할까요?",
            text : "",
            type : "warning",
            showCancelButton : true,
            confirmButtonClass : "btn-danger",
            confirmButtonText : "예",
            cancelButtonText : "아니오",
            closeOnConfirm : false,
            closeOnCancel : false
        }, function(isConfirm) {
            if (isConfirm) {
                $.ajax({
                    url:"/look_comment/"+comment_id,
                    type:"DELETE", //데이터 전달방식
                    success:function () {
                        //삭제 이미지 부모객체 look_comment 화면에서 지우기
                        $(event.target).parents(".look_comment").remove();
                        swal('', '댓글을 삭제하였습니다.', "success");
                    },
                    error: function(error) {
                        //서버오류 500  권한없음 401  찾는내용없음 400
                        if(error.status==401){
                            swal('접근 권한이 없습니다','' ,'error');
                        }else if(error.status==500){
                            swal('서버 오류 관리자에게 문의 하세요','' ,'error');
                        }else if(error.status==400){
                            swal('삭제할 댓글이 없습니다','' ,'error');
                        }
                    }
                })
            }else{
                swal("","댓글 작성을 취소하였습니다");
            }

        });
    }

});




