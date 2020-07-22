let isEnd = false; //더이상 가져올값이 없으면 중지하기 위한변수
let num = 0; //4개씩 나오기 위한변수
let mainScrollTime = true;

$(document).ready(function () {
    start(); //처음 4개 출력
    $(window).scroll(function () {   //스크롤 감지 이벤트

        let scroll = $(document).scrollTop(); //현재 스크롤 값
        let documentHeight = $(document).height();//문서 전체높이
        let windowHeight = window.innerHeight; //윈도우 높이
        //윈도우 높이에 스크롤값을 계속더해서 문서 전체 길이에서 100 px 앞에 스크롤이 왔을때 데이터 불러옴
        if ((windowHeight + scroll) >= documentHeight - 100) {
            if (mainScrollTime == true) {
                console.log("현재 스크롤 값", scroll);
                console.log("전체높이", documentHeight);
                console.log("윈도우 높이", windowHeight);
                console.log("윈도우높이 + 스크롤 >= 문서높이 -100px");
                start();
            }
        }
    })

});

function start() {
    mainScrollTime = false;

    $.ajax({
        url: "/looks", //요청url
        type: "GET",
        dataType: "json", //json 으로 받기
        success: function (result) { //성공 하면 데이터를 result로 받아옴

            if (isEnd == true) {
                return;
            }

            //4개씩 출력 0~3  4~7 8~11 12~15
            for (var i = num; i < num + 4; i++) {

                if (result[i].look_num == null) {
                    isEnd = true; //더이상 가져올값이 없으면 true로 바꿔 더이상 값을 못가져오게함
                }

                let html = "";
                html += '<a class=\"look_view_a\"  href=\"look?look_num=' + result[i].look_num + '\">';
                html += '<div class=\"main\">';
                html += '<div claas=\"main_container\">';
                html += '<ul class=\"main_look_item\">';
                html += '<li id=1>';
                html += '<div class=\"is_body\" >';
                html += '<!-- 헤더-->';
                html += '<div class=\"my_img\">';
                html += '<img src=displayMthumbnail/' + result[i].fk_userid_user_userid + '>';
                html += ' </div>';
                html += '<div class=\"name\">';
                html += ' <ul class=\"look_header_ul\">';
                html += ' <li class=\"look_header_li\" style=\"width: auto\">';
                html += '<span class=\"user_name\" style=\"width: auto\">' + result[i].nickname + '</span>';
                html += ' </li>';
                html += ' <li class=\"look_header_li\" style=\"width: auto; \"></li>';
                html += '<li class=\"look_header_li\"\n" style=\"width: fit-content; text-align: right; float: right; font-size: 15px; font-weight: bold\">';
                html += '<span id=\"look_date\">' + result[i].look_date + '</span>';
                html += ' </li>';
                html += ' </ul>';
                html += ' </div>';
                html += '<div class=\"title\" >' + result[i].title + '</div>';
                html += '<!-- 본문-->';
                html += ' <div class=\"look_img\">';
                html += '<div class=\"look_img_in\">';
                // html += '<img src=\"static/images/1.JPG\" alt=\"look_image\" class= \"look_img_file\"/>';
                html += '<img class= \"look_img_file\" src=\"displayLthumbnail/' + result[i].look_num + '\">';
                html += ' </div>';
                html += ' </div>';
                html += '<div class=\"look_textarea_space\">';
                html += '<form class=\"textarea_form\" >';
                html += '<textarea  class = \"view_look_textarea\" placeholder=\"' + result[i].tag + '\"></textarea>';
                html += '</form>';
                html += ' </div>';
                html += '<!-- 푸터-->';
                html += ' <ul class=\"look_footer_ul\">';
                html += '<li class=\"look_footer_li\">';
                html += '<div class =\"like_img_box\">';
                html += '<img src=\"static/images/heart.svg\" alt=\"heart_image\" class=\"like_img\" />';
                html += ' </div>';
                html += ' </li>';
                html += '<li class=\"look_footer_li\">';
                html += '<div class = \"like_number\">';
                html += '<span>10.5K</span>';
                html += ' </div>';
                html += ' </li>';
                html += '<li class=\"look_footer_li\" style=\"width: 25px;\"></li>';
                html += ' <li class=\"look_footer_li\">';
                html += '<div class= \"count_img_box\">';
                html += '<img src=\"static/images/board_view_icon.svg\" alt=\"board_view_icon\" class=\"count_img\"/>';
                html += ' </div>';
                html += ' </li>';
                html += '<li class=\"look_footer_li\">';
                html += ' <div class = \"count_number\">';
                html += '<span>' + result[i].count + '</span>';
                html += ' </div>';
                html += '</ul>';
                html += ' <div class=\"space_end\"></div>';
                html += ' </div>';
                html += ' </li>';
                html += '</ul>';
                html += ' </div>';
                html += ' </div>';
                html += ' </a>';


                // let toBodyroot = $(".body_root").append(html);
                // let toWebview = $(".webview").append(toBodyroot);
                // $('body').append(toWebview);
                $(".body_root").append(html);
            }

            setTimeout(function () {
                mainScrollTime = true;
            }, 400);//스크롤이벤트 0.2초뒤실행 중복방지위해
            num += 4; //4개씩 차례대로 출력하게 4더함
        },
        error: function (errorThrown) {
            alert("fail");
        }
    });
}