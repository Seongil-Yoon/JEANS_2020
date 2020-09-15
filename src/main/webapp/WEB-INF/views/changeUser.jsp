<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8" />
    <title>회원정보수정</title>
    <link rel="stylesheet" href="static/css/changeUser.css" />
    <link rel="stylesheet" href="static/css/slideShow.css" />
</head>
<body>
<!-- <div class="leftTop"></div>
<div class="leftBottom"></div>
<div class="rightTop"></div>
<div class="rightBottom"></div> -->

<div class="webview">
    <jsp:include page="header.jsp" flush="false"/>

    <!-- /*여기부터가 본문*/  -->

    <div class="body_root">
        <div class="changePW">
            <form class="changePW_1" method="post">
                <div class="changePW_1_1"> <!-- changePW의 손자 태그-->
                    <div class="changePW_1_1_1">비밀번호 변경</div>
                    <div class="changePW_1_1_2">변경할 비밀번호</div>
                    <div class="changePW_1_1_3">
                        <input type="password" class="main_pwInput" placeholder="P/W" name="password" ></input>
                    </div>
                    <div class="changePW_1_1_4" name="changePassword">비밀번호 확인</div>
                    <div class="changePW_1_1_5">
                        <input type="password" class="main_pwInput" placeholder="P/W" name="checkPassword"></input>
                    </div>
                    <div class="changePW_1_1_6">
                        <button class="register_save" type="submit" onclick="changePassword()" value="비밀번호 변경">비밀번호 변경</button>
                    </div>
                </div>
            </form>
        </div>


        <div class="changeEmail">
            <form class="changeEmail_1" action="/loginRequest" method="post">
                <div class="changeEmail_1_1"> <!-- changeEmail의 손자 태그-->
                    <div class="changeEmail_1_1_1">인증 이메일 변경</div>
                    <div class="changeEmail_1_1_2">변경할 인증 이메일</div>
                    <div class="changeEmail_1_1_3">
                        <input type="email" id="emailbox" name="email" placeholder="이메일을 입력하세요"/>
                    </div>
                    <div class="changeEmail_1_1_6">
                        <button class="register_save" type="button" onclick="y" value="인증메일 전송">인증메일 전송</button>
                    </div>
                    <div class="changeEmail_1_1_7">인증코드</div>
                    <div class="changeEmail_1_1_8">
                        <input type="password" class="main_pwInput" placeholder="입력하시오" name="auThenti"></input>
                    </div>
                    <div class="changeEmail_1_1_9">
                        <button class="register_save" type="submit" onclick="y" value="확인">확인</button>
                    </div>
                </div>
            </form>
        </div>


        <div class="changeOther">
            <form class="changeOther_1" action="/loginRequest" method="post">
                <div class="changeOther_1_1">
                    <div class="changeOther_1_1_1">닉네임</div>
                    <div class="changeOther_1_1_2">
                        <input type="text" name="nickname" class="register_nickInput" placeholder="수정할 닉네임">
                    </div>
                    <!-- -->
                    <div class="changeOther_1_1_3">프로필 열람설정</div>
                    <div class="changeOther_1_1_4">성별</div>
                    <div class="changeOther_1_1_5">
                        <input type="radio" name="privacy" value="bodyOpen"/>
                    </div>
                    <div class="changeOther_1_1_6">공개</div>
                    <div class="changeOther_1_1_7">
                        <input type="radio" name="privacy" value="bodyClose"/>
                    </div>
                    <div class="changeOther_1_1_8">비공개</div>
                    <!-- -->
                    <div class="changeOther_1_1_9">
                        <input type="radio" type="number" name="sex" value="1"/>
                    </div>
                    <div class="changeOther_1_1_10">남성</div>
                    <div class="changeOther_1_1_11">
                        <input type="radio" type="number" name="sex" value="0"/>
                    </div>
                    <div class="changeOther_1_1_12">여성</div>
                    <!-- -->
                    <div class="changeOther_1_1_13">키</div>
                    <div class="changeOther_1_1_14">몸무게</div>
                    <div class="changeOther_1_1_15">
                        <input type="number" class="register_heightInt" placeholder="168"/>
                    </div>
                    <div class="changeOther_1_1_16">cm</div>
                    <div class="changeOther_1_1_17">
                        <input type="number" class="register_weightInt" placeholder="168"/>
                    </div>
                    <div class="changeOther_1_1_18">Kg</div>
                    <!-- -->
                    <div class="changeOther_1_1_19">
                        <button class="register_save" type="submit" onclick="y" value="확인">확인</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="changePict">
            <form class="changePict_1" method="post">
                <div class="changePict_1_1"> <!-- changeEmail의 손자 태그-->
                    <div class="changePict_1_1_1">프로필 사진 수정</div>
                    <div class="changePict_1_1_2" id="View_area"></div>
                    <div class="changePict_1_1_3">
                        사진첨부ㅤ
                        <input type="file" name="profile_pt" id="profile_pt" onchange="previewImage(this,'View_area')">
                    </div>
                    <div class="changePict_1_1_4">
                        <button class="register_save" type="submit" onclick="changeprofileThumbnail()" value="확인">확인</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>

<script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="/static/js/backCashDelete.js"></script>
<script src="/static/js/id_nickname_session.js"></script>
<script src="/static/js/changeUser.js"></script>
<%--서버세션이 종료되어 자바스크립트 session 종료--%>
<c:set var="userid" value="${sessionScope.userid}"/>
<c:if test="${userid == null}">
    <script>sessionRemove()</script>
</c:if>
<%--header 부분 초기화--%>
<script>headerReset()</script>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="static/js/ex2.js"></script>

</body>
</html>
