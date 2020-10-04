


function lookDelete(lookNum,lookUserId,userid){

    var lookNum=lookNum;
    var lookUserId=lookUserId;
    //로그인 한 아이디 와 작성자 아이디 가 같아야 삭제 가능
    if(userid==lookUserId){
        deleteConfirm('', '게시글 을 삭제 할까요?',lookNum,lookUserId);
    } else {
        swal("","게시글 작성자 만 삭제 할수있습니다");
    }

}

 function deleteConfirm(msg, title,lookNum,lookUserId) {

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
                type:"DELETE", //데이터 전달방식
                success:function (result,textStatus,jqxHR) {
                    if(jqxHR.status==200){
                        swal('','게시글을 삭제하였습니다' ,'success');
                        setTimeout(function(){ location.href="/main";},2000);
                    }
                },
                error: function(error) {
                    //서버오류 500  찾는 자료없음 404  권한없음  401
                    if(error.status==404){
                        swal('찾는 자료가 없습니다','' ,'error');
                    }else if(error.status==401){
                        swal('삭제 권한이 없습니다','' ,'error');
                    }else if(error.status==500){
                        swal('서버 오류 관리자에게 문의 하세요','' ,'error');
                    }
                }
            })

        }else{
            swal('', '게시글 삭제를 취소하였습니다.');
        }

    });
}


