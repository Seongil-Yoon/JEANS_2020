
function lookDelete(lookNum,lookUserId){

    var lookNum=lookNum;
    var lookUserId=lookUserId;


    lookConfirm('', '게시글을 삭제할까요?',lookNum,lookUserId);
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
            swal('', '게시글을 삭제하였습니다.', "success");
            //이렇게 안하면 선택창 안나오고 바로넘어감
            setTimeout(function(){  location.href="/delete?lookNum="+lookNum+"&lookUserId="+lookUserId;},2000);
        }else{
            swal('', '게시글 삭제를 취소하였습니다.');
        }

    });
}


