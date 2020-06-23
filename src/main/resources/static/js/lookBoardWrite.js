function lookWrite() {

    if(sessionStorage.getItem("userid")==null){
        swal('','로그인을 먼저하세요' ,'');
        return
    }
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
        $.ajax({
            url:"/looks",
            type:"POST", //데이터 전달방식
            data : data, //전송객체
            success:function (result,textStatus,jqxHR) {
                if(jqxHR.status==201){
                    swal('','게시글 등록을 하였습니다' ,'success');
                    //등록 성공하면 내가등록한 게시글화면으로 이동
                    setTimeout(function(){  location.href="/look?look_num="+result.look_num;},2000);
                }
            },
            error: function(error) {
                //서버오류 500  찾는 자료없음 404  권한없음  401
                if(error.status==404){
                    swal('찾는 자료가 없습니다','' ,'error');
                }else if(error.status==401){
                    swal('접근 권한이 없습니다','' ,'error');
                }else if(error.status==401){
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