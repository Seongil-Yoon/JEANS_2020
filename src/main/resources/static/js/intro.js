
function introStart() {


    new fullpage("#full-page",{
        sectionsColor: ['#FFCC66','#FF9966','#33CCFF','#FFFF66'],
        navigation:true, //옆에 화면 움직으는  . . . . 모양 생성
        navigationTooltips: ['Back end','Web Front end','App','Jeans'],
        scrollingSpeed: 1000, // 스크롤 속도1초


        //스크롤이 시작할때 실행
        // origin 원래 있었던 섹션정보 destination 이동한곳 섹션정보
        // direction 은 스크롤 되는 방향 아래이동하면 down 위로가면 up뜸
        onLeave:function (origin,destination,direction) {
            if(origin.index==0){
                $(".s0 h1").animate({
                    width: "hide",
                    height: "hide",  //1000은 1초 swing 처음은 느린데 점점빨리
                }, 1000,"swing");
            }
            if(origin.index==1){
                $(".s1 h1").animate({
                    width: "hide",
                    height: "hide",  //1000은 1초 swing 처음은 느린데 점점빨리
                }, 1000,"swing");
            }
            if(origin.index==2){
                $(".s2 h1").animate({
                    width: "hide",
                    height: "hide",  //1000은 1초 swing 처음은 느린데 점점빨리
                }, 1000,"swing");
            }
            if(origin.index==3){
                $('.s3 h1').hide();
                $('.s3 image').hide();
            }
        },
        //스크롤이 끝날때 실행
        afterLoad:function (origin,destination,direction) {
            if(destination.index==0){
                $(".s0 h1").animate({
                    width: "show",
                    height: "show",
                }, 1000,"swing"); //1000은 1초 swing 처음은 느린데 점점빨리
            }
            if(destination.index==1){
                $(".s1 h1").animate({
                    width: "show",
                    height: "show",
                }, 1000,"swing"); //1000은 1초 swing 처음은 느린데 점점빨리
            }
            if(destination.index==2){
                $(".s2 h1").animate({
                    width: "show",
                    height: "show",
                }, 1000,"swing"); //1000은 1초 swing 처음은 느린데 점점빨리
            }
            if(destination.index==3){
                $('.s3 h1').show();
                $('.s3 image').show();
            }
        },
    });

}
