<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="static/css/jeans_header_.css">
    <link rel="stylesheet" href="static/css/jeans_info_body.css">
    <script type="text/javascript" src="/static/js/lookDelete.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
    <script type="text/javascript" src="/static/js/comment.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
    <script
            src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
            crossorigin="anonymous"></script>

</head>
<body>

<!--/*여기는 맨 위에 있는 바 부분*/ -->
<div class="jeans_root">
    <div class="jeans_header">
        <div class="search_left"></div>
        <div class="search_logo">
            <img src="static/images/search.jpg" alt="search" height="30" width="30"/>
        </div>
        <div class="search_input" style="margin-top: 20px">
            <form>
                <input type="text" class="search_text"/>
            </form>
        </div>
        <div class="logo_left"></div>
        <div class="logo">
            <a class="header_a" href="main"><img src="static/images/logo.png" alt="logo" height="30"
                                                 width="71"/></a>
        </div>

        <c:set var="userid" value="${sessionScope.userid}"/>
        <c:if test="${userid != null}">
            <a class="header_a" href="look_write">
                <div class="logo_right"><span class="look_write">Look Write</span></div>
            </a>
        </c:if>

        <div class="my_info">
            <div>
                <c:set var="userid" value="${sessionScope.userid}"/>
                <c:if test="${userid != null}">
                    <img src="static/images/mypicture.png" alt="pitcture" height="35" width="40"/>
                </c:if>
            </div>
            <span class="user_id"><c:out value="${sessionScope.usernickname}"></c:out></span>
        </div>

        <div class="logout_left"></div>

        <c:set var="userid" value="${sessionScope.userid}"/>
        <c:choose>
            <c:when test="${userid != null}">
                <a class="header_a" href="logout">
                    <div class="logout">logout</div>
                </a>
            </c:when>

            <c:otherwise>
                <a class="header_a" href="loginUser">
                    <div class="login">login</div>
                </a>
            </c:otherwise>
        </c:choose>
        <div class="logout_right"></div>
    </div>
</div>
<!-- /*여기부터가 본문*/  -->

<div class="body_root">

    <div class="header_space"></div>

    <div class="body_look">

        <div class="my_img">
            <img src="static/images/mypicture.png" alt="search" height="50" width="60"/>
        </div>

        <div class="name">${view.nickname}</div>

        <div class="write_date">${view.look_date}</div>

        <div class="title">${view.title}</div>

        <c:set var="viewId" value="${view.fk_userid_user_userid}"/>

        <div class="delete">
            <a href="#"><img src="static/images/delete.png" alt="search"
                             height="25" width="25" onclick="lookDelete(${view.look_num},'${viewId}')"/></a>
        </div>


        <!-- 슬라이드 쇼-->

        <div class="look_img_container">
            <div class="look_img_viewport">
                <div class="look_flick_camera">

                    <div class="flick_panel s1" style="left: 0px;">
                        <img src="static/images/100.jpg" alt="look_image" class="look_img_file"/>
                    </div>
                    <div class="flick_panel s2" style="left: 305px;">
                        <img src="static/images/101.jpg" alt="look_image" class="look_img_file"/>
                    </div>
                    <div class="flick_panel s3" style="left: 610px;">
                        <img src="static/images/102.jpg" alt="look_image" class="look_img_file"/>
                    </div>
                    <div class="flick_panel s4" style="left: 915px;">
                        <img src="static/images/201.jpg" alt="look_image" class="look_img_file"/>
                    </div>
                    <div class="flick_panel s5" style="left: 1220px;">
                        <img src="static/images/202.jpg" alt="look_image" class="look_img_file"/>
                    </div>
                    <div class="flick_panel s6" style="left: 1525px;">
                        <img src="static/images/203.jpg" alt="look_image" class="look_img_file"/>
                    </div>
                    <div class="flick_panel s7" style="left: 1830px;">
                        <img src="static/images/4.jpg" alt="look_image" class="look_img_file"/>
                    </div>
                </div>

                <button type="button" class="look_slide_button" id="look_slide_button_left" style="left: 0;">
                    <img src="static/images/look_slide_icon_left.png" class="look_slide_button_icon">
                </button>
                <button type="button" class="look_slide_button" id="look_slide_button_right" style="right: 0;">
                    <img src="static/images/look_slide_icon_right.png" class="look_slide_button_icon">
                </button>
            </div>

        </div>

        <div class="look_textarea_space">
            <form class="textarea_form">
                <textarea style="background-color:#F6F6F6 " disabled class="look_textarea"
                          placeholder="${view.memo}"></textarea>
            </form>
        </div>


        <div class="like">
            <img src="static/images/heart.png" alt="heart_image" class="heart_img"/>
            <div class="like_number">
                10.5K
            </div>

        </div>

        <div class="views">
            <img src="static/images/board_view_icon.svg" alt="search" height="25" width="25" class="view_icon"/>
            ${view.count}
        </div>

        <div class="look_tag">
            ${view.tag}
        </div>

    </div>

    <form name="commentForm">
        <c:if test="${sessionScope.userid != null}"> <%--로그인 해야 작성가능--%>
            <div class="look_comment">
                <div class="other_people_img">
                    <img src="static/images/mypicture.png" alt="other_people_imgage" height="50" width="60"/>
                </div>

                <div class="other_people_name">${sessionScope.usernickname}</div>

                <div class="comment_textarea_space">
                    <input type="hidden" name="comment_sender_id" value="${sessionScope.userid}"/> <%--작성자 아이디--%>
                    <input type="hidden" name="comment_sender_name"
                           value="${sessionScope.usernickname}"/> <%--작성자 닉네임--%>
                    <input type="hidden" name="fk_look_num_Look_look_num"
                           value="${view.look_num}"/> <%--게시글 기본키인 게시글숫자--%>
                    <textarea style="background-color:#F6F6F6 "
                              class="comment_textarea" placeholder="댓글 내용을 입력하세요" name="comment_content"></textarea>
                </div>
                <div class="comment_date">
                    <button type="button" onclick="comment()">댓글등록</button>
                </div>
            </div>
        </c:if>
    </form>

    <%--처음에 최신 댓글내용 두개출력--%>
    <c:forEach items="${comment}" var="dto" begin="0" end="1" step="1">
    <div class="look_comment">
        <div class="other_people_img">
            <img src="static/images/mypicture.png" alt="other_people_imgage" height="50" width="60"/>
        </div>

        <div class="other_people_name">${dto.comment_sender_name}</div>

        <div class="comment_textarea_space">
            <form>
                             <textarea style="background-color:#F6F6F6 "
                                       disabled class="comment_textarea" placeholder="${dto.comment_content}"></textarea>
                <%--댓글나오게 수정해야됨--%>
            </form>
        </div>

        <div class="comment_date">
                ${dto.date}
        </div>
    </div>
    </c:forEach>




</div>

<script src="/static/js/look_info.js"></script>

</body>
</html>
