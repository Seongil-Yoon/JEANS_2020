<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
00
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="static/css/jeans_header_.css">
    <link rel="stylesheet" href="static/css/jeans_info_body.css">
  </head>
  <body>
    <!--/*여기는 맨 위에 있는 바 부분*/ -->

            <div class="jeans_root">
                <div class="jeans_header">
                    <div class="search_left"></div>
                    <div class="search_logo">
                        <img src="static/images/search.jpg" alt="search" height="30" width="30" />
                    </div>
                    <div class="search_input" >
                        <form>
                            <input type="text"
                                 class = "search_text"
                            />
                        </form>
                    </div>
                    <div class="logo_left"></div>
                    <div class="logo" >
                        <img src="static/images/logo.png" alt="logo" height="30" width="71" />

                    </div>
                    <div class="logo_right"></div>

                    <div class="my_info">
                        <div>
                            <img src="static/images/mypicture.png" alt="pitcture" height="35" width="40" />
                        </div>

                        <span class="user_id"><c:out value="${sessionScope.usernickname}"></c:out></span>
                    </div>

                    <div class="logout_left"></div>


                    <div class="logout"> logout</div>
                    <div class="logout_right"></div>
                </div>
            </div>
  <!-- /*여기부터가 본문*/  -->

<div class="body_root">

    <div class = "header_space"></div>

        <div class="body_look">

          <div class="my_img">
           <img src="static/images/mypicture.png" alt="search" height="50" width="60" />
          </div>

          <div class="name">찬영</div>
          <div class="title">제목</div>

          <div class="look_img">
            <div class="look_img_in">
              <img src="static/images/1.jpg" alt="look_image" class= "look_img_file"/>
            </div>
          </div>

          <div class="look_textarea_space">
            <form class="textarea_form">
              <textarea class = "look_textarea"></textarea>
            </form>
          </div>

          <div class ="like">
            <img src="static/images/heart.png" alt="heart_image" class="heart_img" />


          </div>

          <div class = "like_number">
            10.5K
          </div>

          <div class ="look_tag"> #박보검 #김유정 #태그</div>

        </div>

        <div class="look_comment">
          <div class="other_people_img">
           <img src="static/images/mypicture.png" alt="other_people_imgage" height="50" width="60" />
          </div>

          <div class="other_people_name"> 규진</div>

          <div class="comment_textarea_space">
              <form>
                  <textarea class="comment_textarea">

                  </textarea>
              </form>
          </div>

          <div class="comment_date">
              1초전
          </div>
        </div>
      </div>


  </body>
</html>
