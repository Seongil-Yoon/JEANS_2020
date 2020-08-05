
//게시글에서 수정 pen 버튼 누를경우 이벤트
function lookModify(lookNum,lookUserId,userid){

    var lookNum=lookNum;
    var lookUserId=lookUserId;

    if(userid==lookUserId){
        //로그인 한 아이디 와 작성자 아이디 가 같으면 글수정
        modifyConfirm('', '게시글을 수정할까요?',lookNum);
    } else {
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

function modifiy() {

    var title=document.getElementsByName("title")[0].value
    var season=document.getElementsByName("season");
    var look_public=document.getElementsByName("look_public");
    var tag=document.getElementsByName("tag")[0].value
    var memo=document.getElementsByName("memo")[0].value
    let seasonCheck=season_check(season);
    let look_publicCheck=look_public_check(look_public);
    var empty='';
    var list = ["제목","계절","공개여부","태그","메모"];
    var formList = [title,seasonCheck,look_publicCheck,tag,memo];

    for (var i=0; i<formList.length; i++){
        if(formList[i]==false){ //자바 스크립트는 null 을 false 로인식
            empty+=list[i]+" ";
        }
    }
    if(empty==''){
        var data = $('[name=writeForm]').serialize();
            //글수정하기
            $.ajax({
                url:"/looks",
                type:"PUT", //데이터 전달방식
                data : data, //전송객체
                success:function (result,textStatus,jqxHR) {
                    if(jqxHR.status==201){
                        swal('','게시글 수정을 하였습니다' ,'success');
                        //수정 성공하면 내가수정한 게시글화면으로 이동
                        setTimeout(function(){  location.href="/look?look_num="+result.look_num;},2000);
                    }
                },
                error: function(error) {
                    //서버오류 500  찾는 자료없음 404  권한없음  401
                    if(error.status==404){
                        swal('수정 자료가 없습니다','' ,'error');
                    }else if(error.status==401){
                        swal('접근 권한이 없습니다','' ,'error');
                    }else if(error.status==500){
                        swal('서버 오류 관리자에게 문의 하세요','' ,'error');
                    }
                }
            })
    }else{
        //입력안한 부분있으면 다시작성하게함
        swal('', empty+' 을 다시 입력하세요' ,'');
    }

}

function season_check(season) {
    var result='';
    for (var i=0; i<season.length; i++) {

        if (document.getElementsByName("season")[i].checked == true) {
            //check 박스 체크 헀는지 확인
            result+=document.getElementsByName("season")[i].value+" ";
        }
    }
    return result;
}

function look_public_check(look_public) {
    var result='';
    for (var i=0; i<look_public.length; i++) {

        if (document.getElementsByName("look_public")[i].checked == true) {
            //radio 박스 체크 헀는지 확인
            result+=document.getElementsByName("look_public")[i].value;
        }
    }
    return result;
}


//수정전 season 이랑 look_public 값 내가 원래 선택한 값 보여주기
function seasonLook_publicResult(season,look_public) {
    // 계절 하나씩 자르기
    let seasonString=season.split(' ');

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




