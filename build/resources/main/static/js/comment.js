

function comment() {
    //name 은중복을 허용해서 사용할때 0을 꼭붙여야함
    var comment_content=document.getElementsByName("comment_content")[0].value
    if(comment_content){ //자바스크립트는 null값을 false 로 인식함
        //form태그 name인  commentForm 에 내용을 한번에 가져옴
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
        type:"get",
        data : Data, //전송객체
        success:function (Data) {
            alert("성공");
        },
        error: function(errorThrown) {
            alert("fail");
        }
    })
}