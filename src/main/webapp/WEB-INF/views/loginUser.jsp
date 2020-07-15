<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>login</title>
    <link rel="stylesheet" href="static/css/login.css"/>
    <link rel="stylesheet" href="static/css/slideShow.css" />
</head>
<body>

<!--/*여기는 맨 위에 있는 바 부분*/ -->
<div class="webview">
    <jsp:include page="header.jsp" flush="false"/>
    <!-- End of Header-->

    <form name="loginForm">
        <div class="container">
            <div class="wrap"></div>
            <div class="main"></div>
            <div class="main_title">Jeans에 로그인!</div>
            <div class="main_idInputbox">
                <input type="text" class="main_idInput" placeholder="ID" name="userid"></input>
            </div>
            <div class="main_pwInputbox">
                <input type="password" class="main_pwInput" placeholder="P/W" name="password"></input>
            </div>
            <div class="main_loginBtnbox">
                <button class="main_loginBtn" type="button" onClick="userLogin()">LOGIN</button>
            </div>
            <div class="main_joinBtnbox">
                <button class="main_joinBtn" type="button" onclick="location.href='joinUser'" formaction="joinUser">JOIN</button>
            </div>
            <div class="main_bar">
                <hr></hr>
            </div>
            <div class="main_noIdea">
                <a href="https://www.naver.com">계정을 잊어버리셨나요?</a>
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
<script src="/static/js/id_nickname_session.js"></script>
<script src="/static/js/login.js"></script>
<%--서버세션이 종료되어 자바스크립트 session 종료--%>
<c:set var="userid" value="${sessionScope.userid}"/>
<c:if test="${userid == null}">
    <script>sessionRemove()</script>
</c:if>
<%--header 부분 초기화--%>
<script>headerReset()</script>
</body>
</html>