<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>header</title>
    <link rel="stylesheet" href="static/css/jeans_header_.css"/>
    <link rel="stylesheet" href="static/css/slideShow.css" />
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
                <form class="search_input_form">
                    <input type="text" class="search_text"/>
                </form>
            </div>
            <div class="logo_left"></div>
            <div class="logo">
                <a class="header_a" href="main"><img src="static/images/logo.PNG" alt="logo" height="30"
                                                     width="71"/></a>
            </div>

            <c:choose>
                <%--로그인 한경우--%>
                <c:when test="${sessionScope.userid != null}">
                    <a class="header_a" href="look_write">
                        <div class="logo_right"><span class="look_write">look_write</span></div>
                    </a>

                    <a class="header_a">
                        <div class="my_info">
                            <div class="my_picture"><img src="/displaySthumbnail"/></div>
                            <span class="user_nickname">${sessionScope.usernickname}</span>
                        </div>
                    </a>

                    <div class="logout_left"></div>

                    <a class="header_a" href="javascript:logout();">
                        <div class="logout_login">logout</div>
                    </a>

                    <div class="logout_right"></div>
                </c:when>

                <c:otherwise>
                    <a class="header_a" href="joinUser">
                        <div class="logo_right"><span class="look_write">회원가입</span></div>
                    </a>

                    <a class="header_a">
                        <div class="my_info">
                            <div class="my_picture"></div>
                            <span class="user_nickname"></span>
                        </div>
                    </a>

                    <div class="logout_left"></div>

                    <a class="header_a" href="loginUser">
                        <div class="logout_login">login</div>
                    </a>

                    <div class="logout_right"></div>
                </c:otherwise>

            </c:choose>

        </div>
    </div>  
    <div class = "header_space"></div>


<script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="/static/js/backCashDelete.js"></script>
<script src="/static/js/logout.js"></script>


</body>

</html>