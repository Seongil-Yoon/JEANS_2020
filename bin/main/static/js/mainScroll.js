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

                let html = "\n" +
                    "\n" +
                    "      <a class=\"look_view_a\"  href=\"look?look_num=" + result[i].look_num + "\">\n" +
                    "          <div class=\"main\">\n" +
                    "\n" +
                    "              <div claas=\"main_container\">\n" +
                    "\n" +
                    "                  <ul class=\"main_look_item\">\n" +
                    "                      <li id=1>\n" +
                    "                          <div class=\"is_body\" >\n" +
                    "                              <!-- 헤더-->\n" +
                    "                              <div class=\"my_img\">\n" +
                    "                                 <img src=\"displayMthumbnail?id=" + result[i].fk_userid_user_userid + "\">\n" +
                    "                              </div>\n" +
                    "                              <div class=\"name\">\n" +
                    "                                  <ul class=\"look_header_ul\">\n" +
                    "                                      <li class=\"look_header_li\" style=\"width: auto\">\n" +
                    "                                          <span class=\"user_name\" style=\"width: auto\">" + result[i].nickname + "</span>\n" +
                    "                                      </li>\n" +
                    "                                      <li class=\"look_header_li\" style=\"width: auto; \"></li>\n" +
                    "                                      <li class=\"look_header_li\"\n" +
                    "                                          style=\"width: fit-content; text-align: right; float: right; font-size: 15px; font-weight: bold\">\n" +
                    "                                          <span id=\"look_date\">" + result[i].look_date + "</span>\n" +
                    "                                      </li>\n" +
                    "                                  </ul>\n" +
                    "\n" +
                    "                              </div>\n" +
                    "                              <div class=\"title\" >" + result[i].title + "</div>\n" +
                    "\n" +
                    "                              <!-- 본문-->\n" +
                    "                              <div class=\"look_img\">\n" +
                    "                                  <div class=\"look_img_in\">\n" +
                    "                                      <img src=\"static/images/1.JPG\" alt=\"look_image\" class= \"look_img_file\"/>\n" +
                    "                                  </div>\n" +
                    "                              </div>\n" +
                    "\n" +
                    "                              <div class=\"look_textarea_space\">\n" +
                    "                                  <form class=\"textarea_form\" >\n" +
                    "                                      <textarea style=\"background-color: #F6F6F6\" class = \"look_textarea\" placeholder=\"" + result[i].tag + "\"></textarea>\n" +
                    "                                  </form>\n" +
                    "                              </div>\n" +
                    "\n" +
                    "                              <!-- 푸터-->\n" +
                    "                              <ul class=\"look_footer_ul\">\n" +
                    "                                  <li class=\"look_footer_li\">\n" +
                    "                                      <div class =\"like_img_box\">\n" +
                    "                                          <img src=\"static/images/heart.svg\" alt=\"heart_image\" class=\"like_img\" />\n" +
                    "                                      </div>\n" +
                    "                                  </li>\n" +
                    "                                  <li class=\"look_footer_li\">\n" +
                    "                                      <div class = \"like_number\">\n" +
                    "                                          <span>10.5K</span>\n" +
                    "                                             " +
                    "                                      </div>\n" +
                    "                                  </li>\n" +
                    "                                  <li class=\"look_footer_li\" style=\"width: 25px;\"></li>\n" +
                    "                                  <li class=\"look_footer_li\">\n" +
                    "                                      <div class= \"count_img_box\">\n" +
                    "                                          <img src=\"static/images/board_view_icon.svg\" alt=\"board_view_icon\" class=\"count_img\"/>\n" +
                    "                                      </div>\n" +
                    "                                  </li>\n" +
                    "                                  <li class=\"look_footer_li\">\n" +
                    "                                      <div class = \"count_number\">\n" +
                    "                                          <span>" + result[i].count + "</span>\n" +
                    "                                      </div>\n" +
                    "\n" +
                    "                              </ul>\n" +
                    "                              <div class=\"space_end\"></div>\n" +
                    "                          </div>\n" +
                    "                      </li>\n" +
                    "                  </ul>\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "\n" +
                    "              </div>\n" +
                    "\n" +
                    "          </div>\n" +
                    "      </a>\n" +
                    "      </div>"
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