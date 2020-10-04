<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <title>회원정보수정</title>
    <link rel="stylesheet" href="static/css/changeUser.css" />
    <jsp:include page="libsStyles.jsp" flush="false" />
</head>

<body>
    <div class="webview">
        <jsp:include page="header.jsp" flush="false" />

        <!-- /*여기부터가 본문*/  -->

        <div class="body_root">
            <div class="changePW">
                <form class="changePW_1" method="post">
                    <div class="changePW_1_1">
                        <!-- changePW의 손자 태그-->
                        <div class="changePW_1_1_1">비밀번호 변경</div>
                        <div class="changePW_1_1_2">변경할 비밀번호</div>
                        <div class="changePW_1_1_3">
                            <input type="password" class="main_pwInput" placeholder="P/W" name="password"></input>
                        </div>
                        <div class="changePW_1_1_4" name="changePassword">비밀번호 확인</div>
                        <div class="changePW_1_1_5">
                            <input type="password" class="main_pwInput" placeholder="P/W" name="checkPassword"></input>
                        </div>
                        <div class="changePW_1_1_6">
                            <button class="register_save" type="submit" onclick="changePassword()" value="비밀번호 변경">비밀번호
                                변경</button>
                        </div>
                    </div>
                </form>
            </div>


            <div class="changeEmail">
                <form class="changeEmail_1">
                    <div class="changeEmail_1_1">
                        <!-- changeEmail의 손자 태그-->
                        <div class="changeEmail_1_1_1">인증 이메일 변경</div>
                        <div class="changeEmail_1_1_2">변경할 인증 이메일</div>
                        <div class="changeEmail_1_1_3">
                            <input type="email" id="emailbox" name="email" placeholder="이메일을 입력하세요" />
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
                <form class="changeOther_1">
                    <div class="changeOther_1_1">
                        <div class="changeOther_1_1_1">닉네임</div>
                        <div class="changeOther_1_1_2">
                            <input type="text" id="nick" name="nickname" class="register_nickInput">
                        </div>
                        <div class="changeOther_1_1_3">프로필 열람설정</div>
                        <div class="changeOther_1_1_4">성별</div>

                        <div class="changeOther_1_1_5">
                            <input type="radio" id="open" name="privacy" value="1" />
                        </div>
                        <div class="changeOther_1_1_6">공개</div>
                        <div class="changeOther_1_1_7">
                            <input type="radio" id="close" name="privacy" value="0" />
                        </div>
                        <div class="changeOther_1_1_8">비공개</div>
                        <!-- -->
                        <div class="changeOther_1_1_9">
                            <input type="radio" id="male" type="number" name="sex" value="1" />
                        </div>
                        <div class="changeOther_1_1_10">남성</div>
                        <div class="changeOther_1_1_11">
                            <input type="radio" id="female" type="number" name="sex" value="0" />
                        </div>
                        <div class="changeOther_1_1_12">여성</div>
                        <!-- -->
                        <div class="changeOther_1_1_13">키</div>
                        <div class="changeOther_1_1_14">몸무게</div>
                        <div class="changeOther_1_1_15">
                            <input type="number" id="height" name="height" class="register_heightInt" />
                        </div>
                        <div class="changeOther_1_1_16">cm</div>
                        <div class="changeOther_1_1_17">
                            <input type="number" id="weight" name="weight" class="register_weightInt" />
                        </div>
                        <div class="changeOther_1_1_18">Kg</div>
                        <!-- -->
                        <div class="changeOther_1_1_19">
                            <button class="register_save" type="submit" onclick="changeUserinformation()"
                                value="확인">확인</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="changePict">
                <form class="changePict_1">
                    <div class="changePict_1_1">
                        <!-- changeEmail의 손자 태그-->
                        <div class="changePict_1_1_1">프로필 사진 수정</div>
                        <div class="changePict_1_1_2" id="View_area"></div>
                        <div class="changePict_1_1_3">
                            사진첨부ㅤ
                            <input type="file" name="profile_pt" id="profile_pt" accept=".jpg,.jpeg,.png,.bmp"
                                onchange="previewImage(this,'View_area')">
                        </div>
                        <div class="changePict_1_1_4">
                            <button class="register_save" type="submit" onclick="changeprofileThumbnail()"
                                value="확인">확인</button>
                        </div>

                    </div>
                </form>
            </div>

        </div>
    </div>

    <jsp:include page="libsScript.jsp" flush="false" />
    <script src="/static/js/backCashDelete.js"></script>
    <script src="/static/js/changeUser/changeThumnail.js"></script>
    <script src="/static/js/changeUser/changeUser.js"></script>


</body>

</html>