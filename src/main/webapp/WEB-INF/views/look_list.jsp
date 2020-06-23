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

<div class="webview">
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
    <div class="header_space" style="width: 60px"></div>
</div>

<script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="/static/js/backCashDelete.js"></script>
<script src="/static/js/mainScroll.js" charset="utf-8"></script>
<script src="/static/js/id_nickname_session.js"></script>
<%--서버세션이 종료되어 자바스크립트 session 종료--%>
<c:set var="userid" value="${sessionScope.userid}"/>
<c:if test="${userid == null}">
    <script>sessionRemove()</script>
</c:if>
<%--header 부분 초기화--%>
<script>headerReset()</script>

</body>

</html>