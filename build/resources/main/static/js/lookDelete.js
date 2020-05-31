
function lookDelete(lookNum,lookUserId){

    var lookNum=lookNum;
    var lookUserId=lookUserId;


    confirm('', '게시글을 삭제할까요?',lookNum,lookUserId);

}

 function confirm(msg, title,lookNum,lookUserId) {
    swal({
        title : title,
        text : msg,
        type : "warning",
        showCancelButton : true,
        confirmButtonClass : "btn-danger",
        confirmButtonText : "예",
        cancelButtonText : "아니오",
        closeOnConfirm : false,
        closeOnCancel : true
    }, function(isConfirm) {
        if (isConfirm) {
            swal('', '게시글을 삭제하였습니다.', "success");
            location.href="/delete?lookNum="+lookNum+"&lookUserId="+lookUserId;
        }else{
            swal('', '취소하였습니다.', "success");
            location.href="main";
        }

    });
}


