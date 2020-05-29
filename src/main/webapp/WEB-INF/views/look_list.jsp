<!-- 정적인 메인페이지 -->

<!--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
-->
<html>
  <head>
    <meta charset="UTF-8"/>
    <title>look_list</title>
    <link rel="stylesheet" href="static/css/look_list.css"/>
    <link rel="stylesheet" href="static/css/look_list_Look.css"/>
    <link rel="stylesheet" href="static/css/jeans_header_.css"/>
      <script>
          //백오브 캐시 있으면 다시 새로고침
          window.onpageshow = function(event) {
              if ( event.persisted || (window.performance && window.performance.navigation.type == 2)) {
                  window.location.reload(); //새로고침 다시 불러오기
              }
          };
      </script>
  </head>
  <body>
    <div class="webview">
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

              <c:set var="userid" value="${sessionScope.userid}"/>
              <c:if test="${userid != null}">
                  <a href="look_write">
                    <div class="logo_right"> 
                      <span class="look_write">Look Write</span>
                    </div>
                  </a>
              </c:if>

              <div class="my_info">
                  <div>
                      <c:set var="userid" value="${sessionScope.userid}"/>
                      <c:if test="${userid != null}">
                          <img src="static/images/mypicture.png" alt="pitcture" height="35" width="40" />
                      </c:if>
                  </div>
                  <span class="user_id"><c:out value="${sessionScope.usernickname}"></c:out></span>
              </div>

              <div class="logout_left"></div>

              <c:set var="userid" value="${sessionScope.userid}"/>
              <c:choose>
                  <c:when test="${userid != null}">
                      <a href="logout"><div class="logout">logout</div></a>
                  </c:when>

                  <c:otherwise>
                      <a href="loginUser"><div class="login">login</div></a>
                  </c:otherwise>
              </c:choose>

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
                      <!-- 헤더-->
                      <div class="my_img">
                          <img src="static/images/mypicture.png" alt="search" height="50" width="60" />    
                        </div>
                      <div class="name">
                        <ul class="look_header_ul">
                          <li class="look_header_li">
                              <span class="user_name">찬영</span>
                          </li>
                          <li class="look_header_li" style="width: auto; "></li>
                          <li class="look_header_li" style="width: fit-content; text-align: right; float: right; font-size: 10px;">
                               <span id="look_date">2020년 5월 29일 22시 56분 22초</span>
                          </li>
                        </ul>

                      </div>
                      <div class="title" >제목</div>
              
                      <!-- 본문-->
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
                        
                        <!-- 푸터-->
                        <ul class="look_footer_ul">
                          <li class="look_footer_li">
                            <div class ="like_img_box">
                              <img src="static/images/heart.svg" alt="heart_image" class="like_img" />
                            </div>
                          </li>
                          <li class="look_footer_li">
                            <div class = "like_number">
                              <span>10.5K</span>
                            </div>
                          </li>
                          <li class="look_footer_li" style="width: 25px;"></li>
                          <li class="look_footer_li">
                            <div class= "count_img_box">
                              <img src="static/images/board_view_icon.svg" alt="board_view_icon" class="count_img"/>
                            </div>
                          </li>
                          <li class="look_footer_li">
                            <div class = "count_number">
                              <span>1024</span>
                            </div>
                          </li>
                          <li class="look_footer_li" style="width: 140px;"></li>
                          <li class="look_footer_li" style="width: 240px;">
                            <div class ="look_tag"> #박보검 #김유정 #태그</div>
                          </li>
                        </ul>          
                      
                       <div class="space_end"></div>
                    </div>
                  </li>
                  <li id=2>
                    <div class="is_body" >
                      <!-- 헤더-->
                      <div class="my_img">
                          <img src="static/images/mypicture.png" alt="search" height="50" width="60" />    
                        </div>
                      <div class="name">
                        <ul class="look_header_ul">
                          <li class="look_header_li">
                              <span class="user_name">찬영</span>
                          </li>
                          <li class="look_header_li" style="width: auto; "></li>
                          <li class="look_header_li" style="width: fit-content; text-align: right; float: right; font-size: 10px;">
                               <span>2020년 5월 29일 22시 56분 22초</span>
                          </li>
                        </ul>

                      </div>
                      <div class="title" >제목</div>
              
                      <!-- 본문-->
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
                        
                        <!-- 푸터-->
                        <ul class="look_footer_ul">
                          <li class="look_footer_li">
                            <div class ="like_img_box">
                              <img src="static/images/heart.svg" alt="heart_image" class="like_img" />
                            </div>
                          </li>
                          <li class="look_footer_li">
                            <div class = "like_number">
                              <span>10.5K</span>
                            </div>
                          </li>
                          <li class="look_footer_li" style="width: 25px;"></li>
                          <li class="look_footer_li">
                            <div class= "count_img_box">
                              <img src="static/images/board_view_icon.svg" alt="board_view_icon" class="count_img"/>
                            </div>
                          </li>
                          <li class="look_footer_li">
                            <div class = "count_number">
                              <span>1024</span>
                            </div>
                          </li>
                          <li class="look_footer_li" style="width: 140px;"></li>
                          <li class="look_footer_li" style="width: 240px;">
                            <div class ="look_tag"> #박보검 #김유정 #태그</div>
                          </li>
                        </ul>          
                      
                       <div class="space_end"></div>
                    </div>
                  </li>
                  <li id=3>
                    <div class="is_body" >
                      <!-- 헤더-->
                      <div class="my_img">
                          <img src="static/images/mypicture.png" alt="search" height="50" width="60" />    
                        </div>
                      <div class="name">
                        <ul class="look_header_ul">
                          <li class="look_header_li">
                              <span class="user_name">찬영</span>
                          </li>
                          <li class="look_header_li" style="width: auto; "></li>
                          <li class="look_header_li" style="width: fit-content; text-align: right; float: right; font-size: 10px;">
                               <span>2020년 5월 29일 22시 56분 22초</span>
                          </li>
                        </ul>

                      </div>
                      <div class="title" >제목</div>
              
                      <!-- 본문-->
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
                        
                        <!-- 푸터-->
                        <ul class="look_footer_ul">
                          <li class="look_footer_li">
                            <div class ="like_img_box">
                              <img src="static/images/heart.svg" alt="heart_image" class="like_img" />
                            </div>
                          </li>
                          <li class="look_footer_li">
                            <div class = "like_number">
                              <span>10.5K</span>
                            </div>
                          </li>
                          <li class="look_footer_li" style="width: 25px;"></li>
                          <li class="look_footer_li">
                            <div class= "count_img_box">
                              <img src="static/images/board_view_icon.svg" alt="board_view_icon" class="count_img"/>
                            </div>
                          </li>
                          <li class="look_footer_li">
                            <div class = "count_number">
                              <span>1024</span>
                            </div>
                          </li>
                          <li class="look_footer_li" style="width: 140px;"></li>
                          <li class="look_footer_li" style="width: 240px;">
                            <div class ="look_tag"> #박보검 #김유정 #태그</div>
                          </li>
                        </ul>          
                      
                       <div class="space_end"></div>
                    </div>
                  </li>
                  <li id=4>
                    <div class="is_body" >
                      <!-- 헤더-->
                      <div class="my_img">
                          <img src="static/images/mypicture.png" alt="search" height="50" width="60" />    
                        </div>
                      <div class="name">
                        <ul class="look_header_ul">
                          <li class="look_header_li">
                              <span class="user_name">찬영</span>
                          </li>
                          <li class="look_header_li" style="width: auto; "></li>
                          <li class="look_header_li" style="width: fit-content; text-align: right; float: right; font-size: 10px;">
                               <span >2020년 5월 29일 22시 56분 22초</span>
                          </li>
                        </ul>

                      </div>
                      <div class="title" >제목</div>
              
                      <!-- 본문-->
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
                        
                        <!-- 푸터-->
                        <ul class="look_footer_ul">
                          <li class="look_footer_li">
                            <div class ="like_img_box">
                              <img src="static/images/heart.svg" alt="heart_image" class="like_img" />
                            </div>
                          </li>
                          <li class="look_footer_li">
                            <div class = "like_number">
                              <span>10.5K</span>
                            </div>
                          </li>
                          <li class="look_footer_li" style="width: 25px;"></li>
                          <li class="look_footer_li">
                            <div class= "count_img_box">
                              <img src="static/images/board_view_icon.svg" alt="board_view_icon" class="count_img"/>
                            </div>
                          </li>
                          <li class="look_footer_li">
                            <div class = "count_number">
                              <span>1024</span>
                            </div>
                          </li>
                          <li class="look_footer_li" style="width: 140px;"></li>
                          <li class="look_footer_li" style="width: 240px;">
                            <div class ="look_tag"> #박보검 #김유정 #태그</div>
                          </li>
                        </ul>          
                      
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