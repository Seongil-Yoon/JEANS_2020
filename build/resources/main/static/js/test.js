
const num=0;

$(document).ready(function(){
    $(window).scroll(function(){   //스크롤 감지 이벤트

        let scrollTop = window.scrollY; //현재 스크롤 값
        let documentHeight = document.body.offsetHeight;//문서 전체높이
        let windowHeight= window.innerHeight; //윈도우 높이

        if((windowHeight + scrollTop) >= documentHeight-30){
            test();
        }
    })

});



function test() {
    $.ajax({
        url: "/test2", //요청url
        type: "post",  //post 방식받기
        dataType: "json", //json 으로 받기
        success: function(result) {
            // alert("success");
            // $.each(result.boardList, function(index, vo){
            //     alert(vo.look_num);
            // })

            for(var i=num; i<num+7; i++){
                // alert(result.boardList[i].look_num);
                $('<h1>추가 <h1>').appendTo('body');
            }
        },
        error: function(errorThrown) {
            alert("fail");
        }
    });
}