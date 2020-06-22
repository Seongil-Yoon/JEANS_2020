function lookWrite() {
    var title=document.getElementsByName("title")[0].value
    var season=document.getElementsByName("season")[0].value
    var look_public=document.getElementsByName("look_public")[0].value
    var tag=document.getElementsByName("tag")[0].value
    var memo=document.getElementsByName("memo")[0].value
    season=season_check(season);
    look_public=look_public_check(look_public);

    var empty='';
    var list = ["제목","계절","공개여부","태그","메모"];
    var formList = [title,season,look_public,tag,memo];

    for (var i=0; i<formList.length; i++){
        if(formList[i]==false){ //자바 스크립트는 null 을 false 로인식
               empty+=list[i]+" ";
        }
    }


    swal('', empty+' 을 다시 입력하세요' ,'');
    // var data = $('[name=writeForm]').serialize();
}

function season_check(season) {

    for (var i=0; i<season.length; i++) {
        var result='';
        if (document.getElementsByName("season")[i].checked == true) {
           //check 박스 체크 헀는지 확인
            result+=document.getElementsByName("season")[i];
        }
    }
    return result;
}

function look_public_check(look_public) {

    for (var i=0; i<look_public.length; i++) {
        var result='';
        if (document.getElementsByName("look_public")[i].checked == true) {
            //radio 박스 체크 헀는지 확인
            result+=document.getElementsByName("look_public")[i];
        }
    }
    return result;
}