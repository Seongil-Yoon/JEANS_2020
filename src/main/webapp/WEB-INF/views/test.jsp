<!-- 정적인 메인페이지 -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8"/>
    <title>look_list</title>
    <link rel="stylesheet" href="static/css/look_list.css"/>
    <link rel="stylesheet" href="static/css/look_list_Look.css"/>
    <link rel="stylesheet" href="static/css/jeans_header_.css"/>
  </head>
  <body>
  <div class="jeans_root">
      <div class="jeans_header">
          <div class="search_left"></div>
          <div class="search_logo">
              <img src="static/images/search.jpg" alt="search" height="30" width="30" />
          </div>
          <div class="search_input" >
              <form>
                  <input type="text" class = "search_text"/>
              </form>
          </div>
          <div class="logo_left"></div>
          <div class="logo" >
              <a href="main"><img src="static/images/logo.png" alt="logo" height="30" width="71" /></a>
          </div>
          <div class="logo_right"></div>

          <div class="my_info">
              <div>
                  <img src="static/images/mypicture.png" alt="pitcture" height="35" width="40" />
              </div>

              <span class="user_id"><c:out value="${sessionScope.usernickname}"></c:out></span>
          </div>

          <div class="logout_left"></div>


          <a href="logout"><div class="logout">logout</div></a>
          <div class="logout_right"></div>
      </div>
  </div>
    <!--End of Head-->  
      <div class="main">

          <div claas="main_container">
         <!--<ul id="js-look_item" class="main_look_item">
          </ul> -->

            <ul class="main_look_item">
              <li id=1>
                <div class="is_body" >
                  <div class="my_img">
                      <img src="static/images/mypicture.png" alt="search" height="50" width="60" />    
                    </div>
                  <div class="name">찬영</div>
                  <div class="title" >제목</div>
          
                  <div class="look_img">
                      <div class="look_img_in">
                        <img src="static/images/1.JPG" alt="look_image" class= "look_img_file"/>
                      </div>
                    </div>
          
                    <div class="look_textarea_space">
                      <form class="textarea_form">
                        <textarea class = "look_textarea"></textarea>
                      </form>
                    </div>
          
                    <div class ="like">
                      <img src="static/images/heart.PNG" alt="heart_image" class="heart_img" />
                    </div>
          
                    <div class = "like_number">
                      10.5K
                    </div>
          
                    <div class ="look_tag"> #박보검 #김유정 #태그</div>
          
                    <div class="space_end"></div>
                </div>
              </li>
              <li id=2>
                <div class="is_body" >
                  <div class="my_img">
                      <img src="static/images/mypicture.png" alt="search" height="50" width="60" />    
                    </div>
                  <div class="name">찬영</div>
                  <div class="title" >제목</div>
          
                  <div class="look_img">
                      <div class="look_img_in">
                        <img src="static/images/2.JPG" alt="look_image" class= "look_img_file"/>
                      </div>
                    </div>
          
                    <div class="look_textarea_space">
                      <form class="textarea_form">
                        <textarea class = "look_textarea"></textarea>
                      </form>
                    </div>
          
                    <div class ="like">
                      <img src="static/images/heart.PNG" alt="heart_image" class="heart_img" />
                    </div>
          
                    <div class = "like_number">
                      10.5K
                    </div>
          
                    <div class ="look_tag"> #박보검 #김유정 #태그</div>
          
                    <div class="space_end"></div>
                </div>
              </li>
              <li id=3>
                <div class="is_body" >
                  <div class="my_img">
                      <img src="static/images/mypicture.png" alt="search" height="50" width="60" />    
                    </div>
                  <div class="name">찬영</div>
                  <div class="title" >제목</div>
          
                  <div class="look_img">
                      <div class="look_img_in">
                        <img src="static/images/1.JPG" alt="look_image" class= "look_img_file"/>
                      </div>
                    </div>
          
                    <div class="look_textarea_space">
                      <form class="textarea_form">
                        <textarea class = "look_textarea"></textarea>
                      </form>
                    </div>
          
                    <div class ="like">
                      <img src="static/images/heart.PNG" alt="heart_image" class="heart_img" />
                    </div>
          
                    <div class = "like_number">
                      10.5K
                    </div>
          
                    <div class ="look_tag"> #박보검 #김유정 #태그</div>
          
                    <div class="space_end"></div>
                </div>
              </li>
              <li id=4>
                <div class="is_body" >
                  <div class="my_img">
                      <img src="static/images/mypicture.png" alt="search" height="50" width="60" />    
                    </div>
                  <div class="name">찬영</div>
                  <div class="title" >제목</div>
          
                  <div class="look_img">
                      <div class="look_img_in">
                        <img src="static/images/2.JPG" alt="look_image" class= "look_img_file"/>
                      </div>
                    </div>
          
                    <div class="look_textarea_space">
                      <form class="textarea_form">
                        <textarea class = "look_textarea"></textarea>
                      </form>
                    </div>
          
                    <div class ="like">
                      <img src="static/images/heart.PNG" alt="heart_image" class="heart_img" />
                    </div>
          
                    <div class = "like_number">
                      10.5K
                    </div>
          
                    <div class ="look_tag"> #박보검 #김유정 #태그</div>
          
                    <div class="space_end"></div>
                </div>
              </li>
            </ul>

            <!-- 1페이지에 8개의 룩 정적인 목록으로 띄워줌-->


        </div>
        
      </div> 
    </div>
    <script src="look_list.js"></script>
  </body>
</html>