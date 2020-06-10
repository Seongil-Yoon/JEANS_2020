let isEnd=false; //더이상 가져올값이 없으면 중지하기위한 변수
let num=2; //댓글 두개씩 나오기 위한변수

// $(document).ready(function(){
//     $(window).scroll(function(){   //스크롤 감지 이벤트
//         let scroll = $(document).scrollTop(); //현재 스크롤 값
//         let documentHeight = $(document).height();//문서 전체높이
//         let windowHeight= window.innerHeight; //윈도우 높이
//         //윈도우 높이에 스크롤값을 계속더해서 문서 전체 길이에서 30 px 앞에 스크롤이 왔을때 데이터 불러옴
//         if((windowHeight + scroll) >= documentHeight-50){
//             commentList();
//         }
//     })
//
// });


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
        success:function (Data) {
            alert(Data);
        },
        error: function(errorThrown) {
            alert(errorThrown);
        }
    })
}

function commentList() {
    let look_num =document.getElementsByName("fk_look_num_Look_look_num")[0].value
    $.ajax({
        url: "/commentList", //요청url
        type: "get",//데이터 전달방식
        dataType: "json", //json 으로 받기
        data: {
            'look_num':look_num,
            'num':num
        }, //2개씩받기 위해 전달
        success:function (result) {
            $.each(result, function(key, value){
                alert('key:' + key + ' / ' + 'value:' + value.comment_content);
            });
        },
        error: function(errorThrown) {
            alert("fail");
        }
    })
}