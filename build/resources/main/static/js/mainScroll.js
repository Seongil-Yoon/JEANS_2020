
let isEnd = false; //더이상 가져올값이 없으면 중지하기 위한변수
let num=0; //4개씩 나오기 위한변수


$(document).ready(function(){
    $(window).scroll(function(){   //스크롤 감지 이벤트

        let scroll = $(document).scrollTop(); //현재 스크롤 값
        let documentHeight = $(document).height();//문서 전체높이
        let windowHeight= window.innerHeight; //윈도우 높이
        //윈도우 높이에 스크롤값을 계속더해서 문서 전체 길이에서 30 px 앞에 스크롤이 왔을때 데이터 불러옴
        if((windowHeight + scroll) >= documentHeight-50){
            start();
        }
    })

});

function start() {
    $.ajax({
        url: "/lookScroll", //요청url
        dataType: "json", //json 으로 받기
        success: function(result) { //성공 하면 데이터를 result로 받아옴
            if(isEnd == true){
                return;
            }
            // $.each(result.boardList, function(index, vo){
            //     alert(vo.look_num);
            // })

            num+=4;//처음화면에 에 0~3 까지 출력하기 때문에 4부터 출력함
            
            //4개씩 출력  4~7 8~11 12~15
            for(var i=num; i<num+4; i++){

                if( result.boardList[i].look_num  ==null ){
                    isEnd = true; //더이상 가져올값이 없으면 true로 바꿔 더이상 값을 못가져오게함
                }

                let html="\n" +
                    "<div class=\"header_space\" style=\"width: 60px\"></div>\n" +
                    "\n" +
                    "      <a class=\"look_view_a\"  href=\"view?look_num=" + result.boardList[i].look_num + "\">\n" +
                    "          <div class=\"main\">\n" +
                    "\n" +
                    "              <div claas=\"main_container\">\n" +
                    "\n" +
                    "                  <ul class=\"main_look_item\">\n" +
                    "                      <li id=1>\n" +
                    "                          <div class=\"is_body\" >\n" +
                    "                              <!-- 헤더-->\n" +
                    "                              <div class=\"my_img\">\n" +
                    "                                  <img src=\"static/images/mypicture.png\" alt=\"search\" height=\"50\" width=\"60\" />\n" +
                    "                              </div>\n" +
                    "                              <div class=\"name\">\n" +
                    "                                  <ul class=\"look_header_ul\">\n" +
                    "                                      <li class=\"look_header_li\" style=\"width: auto\">\n" +
                    "                                          <span class=\"user_name\" style=\"width: auto\">" + result.boardList[i].nickname + "</span>\n" +
                    "                                      </li>\n" +
                    "                                      <li class=\"look_header_li\" style=\"width: auto; \"></li>\n" +
                    "                                      <li class=\"look_header_li\"\n" +
                    "                                          style=\"width: fit-content; text-align: right; float: right; font-size: 15px; font-weight: bold\">\n" +
                    "                                          <span id=\"look_date\">" + result.boardList[i].look_date + "</span>\n" +
                    "                                      </li>\n" +
                    "                                  </ul>\n" +
                    "\n" +
                    "                              </div>\n" +
                    "                              <div class=\"title\" >" + result.boardList[i].title + "</div>\n" +
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
                    "                                      <textarea style=\"background-color: #F6F6F6\" class = \"look_textarea\" placeholder=\"" + result.boardList[i].tag + "\"></textarea>\n" +
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
                    "                                          <span>" + result.boardList[i].count + "</span>\n" +
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
                $("body").append(html);
            }

        },
        error: function(errorThrown) {
            alert("fail");
        }
    });
}