<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="static/css/jeans_header_.css">
    <link rel="stylesheet" href="static/css/jeans_write_body.css">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="/static/js/ex1.js"></script>
</head>
<body>

<!--/*여기는 맨 위에 있는 바 부분*/ -->

<!--/*여기는 맨 위에 있는 바 부분*/ -->
<div class="jeans_root">
    <div class="jeans_header">
        <div class="search_left"></div>
        <div class="search_logo">
            <img src="static/images/search.jpg" alt="search" height="30" width="30" />
        </div>
        <div class="search_input" style="margin-top: 20px">
            <form>
                <input type="text" class = "search_text"/>
            </form>
        </div>
        <div class="logo_left"></div>
        <div class="logo" >
            <a class="header_a" href="main"><img src="static/images/logo.png" alt="logo" height="30" width="71" /></a>
        </div>

        <c:set var="userid" value="${sessionScope.userid}"/>
        <c:if test="${userid != null}">
            <a class="header_a" href="look_write"><div class="logo_right"> <span class="look_write">Look Write</span></div></a>
        </c:if>

        <div class="my_info">
            <div>
                <c:set var="userid" value="${sessionScope.userid}"/>
                <c:if test="${userid != null}">
                    <img src="static/images/mypicture.png" alt="pitcture" height="35" width="40" />
                </c:if>
            </div>
            <span class="user_id"><c:out value="${sessionScope.usernickname}"></c:out></span>
        </div>

        <div class="logout_left"></div>

        <c:set var="userid" value="${sessionScope.userid}"/>
        <c:choose>
            <c:when test="${userid != null}">
                <a class="header_a" href="logout"><div class="logout">logout</div></a>
            </c:when>

            <c:otherwise>
                <a class="header_a" href="loginUser"><div class="login">login</div></a>
            </c:otherwise>
        </c:choose>

        <div class="logout_right"></div>
    </div>
</div>
<!-- /*여기부터가 본문*/  -->

<!--/*여기부터가 본문*/ -->
    <div class="body_root"> <!--/* 전체 바탕 아무 것도 안함*/ -->
        <%--@elvariable id="boardDto" type="jeans"--%>
        <form:form id="form1" runat="server" modelAttribute="boardDto" action="/boardWriteRequest" method="post">

            <c:set var="error" value="${sessionScope.error}"/>
            <c:if test="${error != null}">
                <%--에러메시지 호출 --%>
                <script>
                    swal ( "입력안됨!!" ,  "입력안된 사항이 있습니다 다시입력해주세요" ,  "error" )
                </script>
                <%--에러메시지 세션 삭제--%>
                <c:remove var="error" scope="session"/>
            </c:if>

            <div class = "header_space"></div>  <!--/* 위에 있는 바와 간격 벌리기 위한것*/ -->

            <div class="body_lookupload">
                <!--/* 본문 바탕 */ -->

                <div class="space"> </div><div class="space"/></div>
            <!-- /* class 이 space 인건 layout 을 위해 넣은 빈공간임*/-->
            <div> <span class ="title">내 룩 등록</span></div>
            <div class="space"> </div>


                <div class="name">

                <span class = "Jeans_bule">*글제목</span>ㅤ
                    <input type="title" class = "input_name" name = "title" />&nbsp;

            </div>

            <div class="space"> </div>

            <div class="upload">
                사진첨부ㅤ
                <input type="file" name="profile_pt" id="profile_pt" onchange="previewImage(this,'View_area')">
            </div>

            <div class="space"> </div>

            <div id='View_area'  class="img_space"></div>

            <!--{/* 오른쪽 부분들 */} -->
            <div class = "upload_right">

                <div><span class = "Jeans_bule">*계절</span></div>

                <div>
                        <input type="checkbox" value ="봄" name="season"/>&nbsp;봄 &nbsp;
                        <input type="checkbox" value ="여름" name="season"/>&nbsp;여름 &nbsp;
                        <input type="checkbox" value ="가을" name="season"/>&nbsp;가을 &nbsp;
                        <input type="checkbox" value ="겨울" name="season"/>겨울

                </div>
                <div class="space"> </div>


                <div class="public">
                    <span class = "Jeans_bule">*공개여부</span>
                </div>

                <div>
                        <input type="radio" name="look_public" value="1">공개
                        <input type="radio" name="look_public" value="2">비공개

                </div>

                <div class="space"> </div>

                <div class="tag">
                    <span class = "Jeans_bule">*태그</span>
                </div>

                <div>

                        <textarea name= "tag"  class = "input_tag"></textarea>

                </div>

                <div class="space"> </div>
                <div class="space"> </div>
                <div class="space"> </div>
                <div class="space"> </div>

                <div class="tag">
                    <span class = "Jeans_bule">*메모</span>
                </div>


                <div class="memo">
                        <textarea   name= "memo"  class = "input_memo" /></textarea>
                </div>
                
            </div>

            <div class="space"> </div>
            <div>
                <div class="save">
                        <button type="submit" class = "save_button" >Save</button>
                </div>
            </div>

        </form:form>

        </div>
        </div>

<script src="ex1.js">

</script>
    </body>
</html>


