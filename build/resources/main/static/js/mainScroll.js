
let isEnd = false;

$(function(){
    $(window).scroll(function(){   //스크롤 감지 이벤트
        let $window = $(this); //이벤트의 객체를 반환
        let scrollTop = $window.scrollTop(); //현재 스크롤 값
        let windowHeight = $window.height();//윈도우 높이
        let documentHeight = $(document).height();//전체 문서길이

        console.log("documentHeight:" + documentHeight + " | scrollTop:" + scrollTop + " | windowHeight: " + windowHeight );

        // scrollbar가 바닥 전 30px까지 도달 하면 리스트를 가져온다.
        if( scrollTop + windowHeight + 30 > documentHeight ){

        }
    })

})

// let fetchList = function(){
//     if(isEnd == true){
//         return;
//     }
//
//     // 방명록 리스트를 가져올 때 시작 번호
//     // renderList 함수에서 html 코드를 보면 <li> 태그에 data-no 속성이 있는 것을 알 수 있다.
//     // ajax에서는 data- 속성의 값을 가져오기 위해 data() 함수를 제공.
//     let startNo = $("#list-guestbook li").last().data("no") || 0;
//     $.ajax({
//         url:"/guestbook/api/guestbook/list?no=" + startNo ,
//         type: "GET",
//         dataType: "json",
//         success: function(result){
//             // 컨트롤러에서 가져온 방명록 리스트는 result.data에 담겨오도록 했다.
//             // 남은 데이터가 5개 이하일 경우 무한 스크롤 종료
//             let length = result.data.length;
//             if( length < 5 ){
//                 isEnd = true;
//             }
//             $.each(result.data, function(index, vo){
//                 renderList(false, vo);
//             })
//         }
//     });
// }
//
// let renderList = function(mode, vo){
//     // 리스트 html을 정의
//     let html = "<li data-no='"+ vo.no +"'>" +
//         "<strong>"+ vo.name +"</strong>" +
//         "<p>"+ vo.content.replace(/\n/gi, "<br>") +"</p>" +
//         "<strong></strong>" +
//         "<a href='#' data-no='"+ vo.no +"'>삭제</a>" +
//         "</li>"
//
//     if( mode ){
//         $("#list-guestbook").prepend(html);
//     }
//     else{
//         $("#list-guestbook").append(html);
//     }
// }