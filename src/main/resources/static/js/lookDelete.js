
function lookDelete(lookNum,lookUserId){

    var lookNum=lookNum;
    var lookUserId=lookUserId;

    if(sessionStorage.getItem("userid")==lookUserId){
        lookConfirm('', '게시글을 삭제할까요?',lookNum,lookUserId);
    }else if(sessionStorage.getItem("userid")==null){
        swal("","로그인을 먼저해주세요");
    }else {
        swal("","게시글 작성자만 삭제할수있습니다");
    }

}

 function lookConfirm(msg, title,lookNum,lookUserId) {

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
                url:"/looks/"+lookNum,
                method:"DELETE", //데이터 전달방식
                success:function () {
                    swal('', '게시글을 삭제하였습니다.', "success");
                    setTimeout(function(){ location.href="/main";},2000);
                },
                error: function() {
                    swal('', '게시글 삭제를 실패하였습니다.', "error");
                }
            })

        }else{
            swal('', '게시글 삭제를 취소하였습니다.');
        }

    });
}


