//수정전 season 이랑 look_public 값 내가 원래 선택한 값 보여주기
function seasonLook_publicResult(season,look_public) {
    // 계절 문자열,기준 으로 자르기
    let seasonString=season.split(',');
    for(let i=0; i<seasonString.length; i++){
        //자른 계절 문자열 차례대로 반복해서 같은 값이있으면 check로 바꾸기
        for(let y=0; y<4; y++) {
            if (seasonString[i] == document.getElementsByName("season")[y].value) {
                document.getElementsByName("season")[y].checked = true;
            }
        }
    }
    if(look_public==1){  //1공개 2비공개
        document.getElementsByName("look_public")[0].checked=true;
    }else {
        document.getElementsByName("look_public")[1].checked=true;
    }
}

//게시글에서 수정 pen 버튼 누를경우 이벤트
function lookModify(lookNum,lookUserId){

    var lookNum=lookNum;
    var lookUserId=lookUserId;

    if(sessionStorage.getItem("userid")==lookUserId){
        modifyConfirm('', '게시글을 수정할까요?',lookNum,lookUserId);
    }else if(sessionStorage.getItem("userid")==null){
        swal("","로그인을 먼저해주세요");
    }else {
        swal("","게시글 작성자만 수정 할수있습니다");
    }

}

function modifyConfirm(msg,title,lookNum) {
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
            location.href="/lookModify?look_num="+lookNum;
        }else{
            swal('', '게시글 수정을 취소 하였습니다.');
        }

    });
}


