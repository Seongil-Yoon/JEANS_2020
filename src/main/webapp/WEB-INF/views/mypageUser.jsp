<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>프로필</title>
    <link rel="stylesheet" href="/static/css/mypageUser_top.css">
    <link rel="stylesheet" href="/static/css/slideShow.css" />
    <jsp:include page="libsStyles.jsp" flush="false"/>
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
                    <img id="js-my_img" src="/static/images/mypicture.png" />
                </div>

                <div class = "modify_top">
                    <span id="js-modify_top"></span>
                    <%--로그인 한경우--%>
                    <c:choose>
                        <c:when test="${sessionScope.userid != null}">
                            <button class="messageBtn" id="js-messagePen" >
                                <img src="/static/images/pen.png" alt="pen" height="30" width="30"/>
                            </button>
                            <a href="/changeUser" id="js-changeUser">
                                <img src="/static/images/gear.png" alt="gear" height="30" width="30" />
                            </a>
                            <a href="/deleteUser" id="js-deleteUser">
                                <img src="/static/images/delete.png" alt="gear" height="30" width="30" />
                            </a>
                        </c:when>
                    </c:choose>
                </div>

                <div class="modify_right"></div>

                <div class="modify_bottom"></div>

                <div class = "my_information">
                    <span id="js-myInfo" class="user_nickname"></span>
                </div>
                <div class = "my_followers">
                    <div class="div_space"></div>
                    <div class ="div_inner">
                        팔로워<br>
                        <span id="js-follower">10k</span>
                    </div>
                    <div class="div_space"></div>
                </div>
                <div class = "my_following">
                    <div class="div_space"></div>
                    <div class ="div_inner">
                        팔로잉<br>
                        <span id="js-following">100</span>
                    </div>
                    <div class="div_space"></div>
                </div>
                <div class = "my_like">
                    <div class="div_space"></div>
                    <div class ="div_inner_right">
                        좋아요<br>
                        0
                    </div>
                    <div class="div_space"></div>
                </div>

                <div class ="my_memo" id="js-message">
                    <span class="my_message"></span>
                </div>

                <div class = "space_middle"></div>

                <div class = "my_items">
                    <div class="div_space"></div>
                    <div class ="div_inner">
                        룩(작성 글)<br>
                        <span id="js-board">2</span>
                    </div>
                    <div class="div_space"></div>
                </div>
                <div class = "my_closet">
                    <div class="div_space"></div>
                    <div class ="div_inner">
                        옷<br>
                        3
                    </div>
                    <div class="div_space"></div>
                </div>
                <div class = "my_look">
                    <div class="div_space"></div>
                    <div class ="div_inner_right">
                        무드<br>
                        밀리터리
                    </div>
                    <div class="div_space"></div>
                </div>
            </div>
        </div>
        </div>
    </div>

    <jsp:include page="libsScript.jsp" flush="false"/>
    <script type="text/javascript" src ="/static/js/mypageUser/mypageUser.js"></script>
    <script src="/static/js/backCashDelete.js"></script>
    <script>myUsernickname('${nick}')</script>
</body>
</html>
