<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>회원가입</title>
    <link rel="stylesheet" href="/static/css/joinUser.css"/>
    <link rel="stylesheet" href="/static/css/jeans_header_.css"/>
</head>
<body>


<!--/*여기는 맨 위에 있는 바 부분*/ -->
<div class="webview">
      <jsp:include page="header.jsp" flush="false"/>
    <!-- /*여기부터가 본문*/  -->

    <div class="container">
        <div class="container_inner">
            <form:form modelAttribute="userDto" class="container_inner_inner">
                <div class="join_title">
                    <span class="join_title blue">JEANS </span>
                    <span class="join_title black">회원가입</span>
                    <div class="join_title sub"> 필수정보를 입력하세요.</div>
                </div>
                <div class="container_inner_FormWrap">
                    <div class="container_inner_Form join_id">
                        <h4 class="join_title">아이디</h4>
                        <input type="text" id="id" name="userid" class="join_input" placeholder="아이디를 입력하세요"></input>
                        <div class="register_idError" id="id_check"></div>
                    </div>
                    <div class="container_inner_Form join_pw">
                        <h4 class="join_title">비밀번호</h4>
                        <input type="password" id="pw" name="password" class="join_input" placeholder="비밀번호를 입력하세요">
                        <div class="register_pwError" id="pass_check"></div>
                    </div>
                    <div class="container_inner_Form join_nick">
                        <h4 class="join_title">닉네임</h4>
                        <input type="text" id="nick" name="nickname" class="join_input" placeholder="닉네임을 입력하세요">
                        <div class="register_nickError" id="nick_check"></div>
                    </div>
                    <div class="container_inner_Form join_email">
                        <h4 class="join_title">이메일</h4>
                        <input type="email" id="emailbox" name="email"  class="join_input" placeholder="이메일을 입력하세요"/>
                        <div class="register_emailError" id="email_check"></div>
                    </div>
                    <div class="container_inner_Form join_pic">
                        <h4 class="join_title">프로필사진 등록</h4>
                        <div class="select_profile">
                            <input type="file" id="input_profile" name="picture" accept=".jpg,.jpeg,.png,.bmp" onchange="previewImage(this,'View_area')"/>
                        </div>
                        <div class="upload_image" id="View_area">
                            <!-- JS로 img태그 생성 -->
                        </div>
                    </div>
                    <div class="container_inner_Form join_privacy">
                            <h4 class="join_title" style="margin-top: 0;">프로필 공개여부</h4>
                            <label class="selector" style="float: left;">
                                <input type="radio" name="privacy"  value="bodyOpen"/>
                                <span class="selector_span">공개</span>
                            </label>
                            <label class="selector" style="float: right;">
                                <input type="radio" name="privacy" value="bodyClose"/>
                                <span class="selector_span">비공개</span>
                            </label>
                    </div>
                    <div class="container_inner_Form join_bodyinfo">
                        <div class="bodyinfo heightDiv">
                            <h4 class="join_title" style="margin-top: 0;">키</h4>
                            <input type="number" id="height" name="height" class="bodyinfo_input" ></input>
                            <span class="register_heightCm">cm</span>
                            <div class="register_heightError" id="height_check"></div>
                        </div>
                        <div class="bodyinfo weightDiv">
                             <h4 class="join_title" style="margin-top: 0;">몸무게</h4>
                             <input type="number" id="weight" name="weight" class="bodyinfo_input" ></input>
                             <span class="register_weightKg">Kg</span>
                             <div class="register_weightError" id="weight_check"></div>
                        </div>
                    </div>
                    <div class="container_inner_Form join_gender">
                        <h4 class="join_title" style="margin-top: 0;">성별</h4>
                        <label class="selector" style="float: left;">
                            <input type="radio" type="number" name="sex" value="1"/>
                            <span class="selector_span">남자</span>
                        </label>
                        <label class="selector" style="float: right;">
                            <input type="radio" type="number" name="sex" value="0"/>
                            <span class="selector_span">여자</span>
                        </label>
                        <div class="register_genderError" id="gender_check"></div>
                    </div>
                    <button class="register_save" type="button" onClick="joinUser()">가입 완료하기</button>
                </div>
            </form:form>
        </div>
    </div>
</div>

<script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="/static/js/joinUser/joinUser.js"></script>
<script src="/static/js/joinUser/ex1.js"></script>
</body>
</html>