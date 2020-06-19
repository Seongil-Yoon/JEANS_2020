<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>register</title>
    <link rel="stylesheet" href="static/css/register.css"/>
    <link rel="stylesheet" href="static/css/jeans_header_.css"/>
</head>
<body>


<!--/*여기는 맨 위에 있는 바 부분*/ -->
<div class="webview">
    <div class="jeans_root">
        <div class="jeans_header">
            <div class="search_left"></div>
            <div class="search_logo">
                <img src="static/images/search.jpg" alt="search" height="30" width="30" />
            </div>
            <div class="search_input" style="margin-top: 0px" >
                <form>
                    <input type="text" class = "search_text"/>
                </form>
            </div>
            <div class="logo_left"></div>
            <div class="logo" >
                <a class="header_a" href="main"><img src="static/images/logo.png" alt="logo" height="30" width="71" /></a>
            </div>

            <a class="header_a" href="javascript:logo_right_click();">
                <div class="logo_right"><span class="look_write"></span></div>
            </a>

            <div class="my_info">
                <div class="my_picture"></div>
                <span class="user_nickname"></span>
            </div>

            <div class="logout_left"></div>


            <a class="header_a" href="javascript:logout_login_click();">
                <div class="logout_login"></div>
            </a>

            <div class="logout_right"></div>
        </div>
    </div>
    <!-- /*여기부터가 본문*/  -->


    <div>

        <%--@elvariable id="userDto" type="jeans"--%>
        <form:form modelAttribute="userDto" action="/joinRequest" method="post">
            <div class="main"></div>
            <div class="main_title">회원가입</div>


            <div class="register_id">아이디</div>
            <input type="text" name="userid" class="register_idInput" placeholder="아이디를 입력하세요"></input>
            <div class="register_idError"><form:errors path="userid" /></div>

            <!--<button type="button" class = "main_duplicate">중복확인</button>
            <div class="main_duplicateResult">사용가능</div>-->

            <div class="register_pw">비밀번호</div>
            <input type="password" name="password" class="register_pwInput" placeholder="비밀번호를 입력하세요">
            <div class="register_pwError"><form:errors path="password" /></div>


            <div class="register_nick">닉네임</div>
            <input type="text" name="nickname" class="register_nickInput" placeholder="닉네임을 입력하세요">
            <div class="register_nickError"><form:errors path="nickname"/></div>


            <%--    <div class="register_pictureTitle">프로필 사진</div>--%>
            <%--    <div class="register_pictureFile">--%>
            <%--        <input name="BOARD_FILE" id="BOARD_FILE" required="required" type="file"/>--%>
            <%--    </div>--%>

            <div class="register_bodyType">공개/비공개</div>
            <div class="register_lock">공개</div>
            <div class="register_lockButton">
                <input type="radio" value="bodyOpen"/>
            </div>
            <div class="register_unlock">비공개</div>
            <div class="register_unlockButton">
                <input type="radio"  value="bodyClose"/>
            </div>



            <div class="register_heightTitle">키</div>
            <input type="number" class="register_heightInt" placeholder="168"></input>
            <div class="register_heightCm">cm</div>

            <div class="register_weightTitle">몸무게</div>
            <input type="number" class="register_weightInt" placeholder="168"></input>
            <div class="register_weightKg">Kg</div>

            <div class="register_genderTitle">성별</div>
            <div class="register_genderMale">남</div>
            <div class="register_genderMaleRadio">
                <input type="radio" type="number" name="sex" value="1"/>
            </div>

            <div class="register_genderFemale">여</div>
            <div class="register_genderFemaleRadio">
                <input type="radio" type="number" name="sex" value="0"/>
            </div>

            <!--
            <div class="main_memoTitle">메모</div>
            <input type="text" class="main_memo"></input>
            -->

            <div class="register_emailTitle">이메일</div>
            <div class="register_email">
                <input type="email" placeholder="email" name="email" />
            </div>
            <div class="register_emailError"><form:errors path="email"/></div>


            <button class="register_save" type="submit" onclick="y" value="회원가입">Save</button>
        </form:form>

    </div>
</div>

<script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="/static/js/id_nickname_session.js"></script>
</body>
</html>