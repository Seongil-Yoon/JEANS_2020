let isEnd=false; //더이상 가져올값이 없으면 중지하기위한 변수
let num=2; //댓글 세개씩 나오기 위한변수 2로 시작이유는 초반에 2개가 기본으로 나와서


function commentReady(lookNum){
    var lookNum=lookNum;

        $(window).scroll(function(){   //스크롤 감지 이벤트
            let scroll = $(document).scrollTop(); //현재 스크롤 값
            let documentHeight = $(document).height();//문서 전체높이
            let windowHeight= window.innerHeight; //윈도우 높이
            //윈도우 높이에 스크롤값을 계속더해서 문서 전체 길이에서 30 px 앞에 스크롤이 왔을때 데이터 불러옴
            if((windowHeight + scroll) >= documentHeight-20){
                commentList(lookNum);
            }
        })

}

function comment() {
    //name 은중복을 허용해서 사용할때 0을 꼭붙여야함
    var comment_content=document.getElementsByName("comment_content")[0].value
    if(comment_content){ //자바스크립트는 null값을 false 로 인식함
        //form태그 name인  commentForm 에 내용을 한번에 가져옴 serialize 는 form태그 내용 한번에 가져옴
        var Data = $('[name=commentForm]').serialize();
        commentInsert(Data);
    }else {
        //false null 값이니 경고문 나오기
        swal("댓글내용 입력안됨!!", "댓글내용을 입력하세요", "error")
    }

}

function commentInsert(Data) {
    $.ajax({
        url:"/commentWrite",
        type:"get", //데이터 전달방식
        data : Data, //전송객체
        dataType: "text",
        success:function (result) {

        },
        error: function() {

        }
    })
}

function commentList(lookNum) {
    let look_num =lookNum;

    $.ajax({
        url: "/commentList", //요청url
        type: "get",//데이터 전달방식
        dataType: "json", //json 으로 받기
        data: {
            'look_num':look_num,
        },
        success:function (result) {
            if(isEnd == true) { //가져올값으 없으므로 리턴
                return;
            }

             //2   2 3 4   5    5   5 6 7    8
            for(var i=num; i<num+3; i++) {
                let html="";
                if( result.commentList.length==i){
                   isEnd=true;
                }
                html+='<div class=\"look_comment\">';
                html+='<div class=\"other_people_img\">';
                html+='<img src=\"static/images/mypicture.png\" alt=\"other_people_imgage\" height=\"50\" width=\"60\"/>';
                html+='</div>';
                html+='<div class=\"other_people_name\">'+result.commentList[i].comment_sender_name+'</div>';
                html+='<div class=\"comment_textarea_space\">';
                html+='<textarea style=\"background-color:#F6F6F6 \"disabled class=\"comment_textarea\" placeholder=\" '+result.commentList[i].comment_content+' \"></textarea>';
                html+='</div>';
                html+='<div class=\"comment_date\">'+result.commentList[i].date+'</div>';
                html+='</div>';

                $(".body_root").append(html); //body 마지막에 추가
            }
            num+=3; //다음3개 가져오기 위해 3더함
        },
        error: function(errorThrown) {
            alert("fail");
        }
    })
}



