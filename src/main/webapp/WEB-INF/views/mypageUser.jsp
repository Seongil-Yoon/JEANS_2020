<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>프로필</title>
    <link rel="stylesheet" href="static/css/mypageUser_top.css">
    <link rel="stylesheet" href="static/css/mypageUser_bottom.css">
    <link rel="stylesheet" href="static/css/slideShow.css" />
</head>
<body>
<!--/*여기는 맨 위에 있는 바 부분*/ -->
<div class="webview">
    <jsp:include page="header.jsp" flush="false"/>

<!-- /*여기부터가 본문*/  -->

    <div class="body_root">
        <div class = "my_page_body">
            <div class ="my_page_body_top">

                <div class = "my_img">
                    <img src="static/images/mypicture.png" alt="big_my_picture" height="110" width="140" />
                </div>

                <div class = "modify_top">
                    <img src="static/images/pen.png" alt="pen" height="30" width="30" />
                    <a href="changeUser">
                        <img src="static/images/gear.png" alt="gear" height="30" width="30" />
                    </a>
                </div>

                <div class="modify_right"></div>

                <div class="modify_bottom"></div>

                <div class = "my_information">
                    <span class="user_nickname"></span><span>   </span>
                    <span>177cm 55kg</span><span>   </span>
                    <span>(남)</span>
                </div>
                <div class = "my_followers">
                    <div class="div_space"></div>
                    <div class ="div_inner">
                        팔로워<br>
                        10K
                    </div>
                    <div class="div_space"></div>
                </div>
                <div class = "my_following">
                    <div class="div_space"></div>
                    <div class ="div_inner">
                        팔로잉<br>
                        100
                    </div>
                    <div class="div_space"></div>
                </div>
                <div class = "my_like">
                    <div class="div_space"></div>
                    <div class ="div_inner_right">
                        좋아요<br>
                        20K
                    </div>
                    <div class="div_space"></div>
                </div>

                <div class ="my_memo">
                    팔로우 좀
                </div>

                <div class = "space_middle"></div>

                <div class = "my_items">
                    <div class="div_space"></div>
                    <div class ="div_inner">
                        옷<br>
                        10
                    </div>
                    <div class="div_space"></div>
                </div>
                <div class = "my_closet">
                    <div class="div_space"></div>
                    <div class ="div_inner">
                        옷장<br>
                        3
                    </div>
                    <div class="div_space"></div>
                </div>
                <div class = "my_look">
                    <div class="div_space"></div>
                    <div class ="div_inner_right">
                        룩<br>
                        10
                    </div>
                    <div class="div_space"></div>
                </div>
            </div>


            <div class ="my_page_body_bottom">

                <input type="radio" id="tab-1" name="show" onclick="page_select1();" checked/>
                <input type="radio" id="tab-2" name="show" onclick="page_select2();"/>
                <div class="tab">
                    <label for="tab-1">내 옷</label>
                    <label for="tab-2">내 옷장</label>
                    <label for="space"></label>              <label for="space"></label>
                    <label for="space"></label>              <label for="space"></label>
                    <label for="space"></label>              <label for="space"></label>
                    <label for="space"></label>
                    <label class="add">
                        <button class="add_img" onclick="page_move();"></button>
                    </label>
                </div>
                <div class="content">
                    <div class="content-1">
                        <div>
                            <img src="static/images/1.jpg" alt="" height="350" width="300"><br>
                            후드티
                        </div>
                        <div>
                            <img src="static/images/1.jpg" alt="" height="350" width="300"><br>
                            후드티
                        </div>
                        <div>
                            <img src="static/images/1.jpg" alt="" height="350" width="300"><br>
                            후드티
                        </div>
                        <div>
                            <img src="static/images/1.jpg" alt="" height="350" width="300"><br>
                            후드티
                        </div>
                    </div>
                    <div class="content-2">

                        <div class="closet1_body">
                            <input type="button" class="closet1_button" value="기본 옷장" onclick="div_show1();" />
                        </div>

                        <div class="closet1" id="closet1">
                            <div>
                                <img src="static/images/1.jpg" alt="" height="350" width="300"><br>
                                후드티
                            </div>
                            <div>
                                <img src="static/images/1.jpg" alt="" height="350" width="300"><br>
                                후드티
                            </div>
                            <div>
                                <img src="static/images/1.jpg" alt="" height="350" width="300"><br>
                                후드티
                            </div>
                            <div>
                                <img src="static/images/1.jpg" alt="" height="350" width="300"><br>
                                후드티
                            </div>
                        </div>


                        <div class="closet2_body">
                            <input type="button" class="closet2_button" value="여름 옷장" onclick="div_show2();"/>
                        </div>

                        <div class="closet2" id="closet2">
                            <div>
                                <img src="static/images/1.jpg" alt="" height="350" width="300"><br>
                                후드티
                            </div>
                            <div>
                                <img src="static/images/1.jpg" alt="" height="350" width="300"><br>
                                후드티
                            </div>
                            <div>
                                <img src="static/images/1.jpg" alt="" height="350" width="300"><br>
                                후드티
                            </div>
                            <div>
                                <img src="static/images/1.jpg" alt="" height="350" width="300"><br>
                                후드티
                            </div>
                        </div>

                        <div class="closet3_body">
                            <input type="button" class="closet3_button" value="가을 옷장" onclick="div_show3();"/>
                        </div>

                        <div class="closet3" id="closet3">
                            <div>
                                <img src="static/images/1.jpg" alt="" height="350" width="300"><br>
                                후드티
                            </div>
                            <div>
                                <img src="static/images/1.jpg" alt="" height="350" width="300"><br>
                                후드티
                            </div>
                            <div>
                                <img src="static/images/1.jpg" alt="" height="350" width="300"><br>
                                후드티
                            </div>
                            <div>
                                <img src="static/images/1.jpg" alt="" height="350" width="300"><br>
                                후드티
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        </div>
    </div>
    

    <script src ="static/js/mypageUser.js"></script>
    <script
            src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
    <script src="/static/js/backCashDelete.js"></script>
    <script src="/static/js/id_nickname_session.js"></script>

</body>
</html>
