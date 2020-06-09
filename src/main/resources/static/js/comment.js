

function comment() {
    //form태그 name인  commentForm 에 내용을 한번에 가져옴
    var Data = $('[name=commentForm]').serialize();
    commentInsert(Data);
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