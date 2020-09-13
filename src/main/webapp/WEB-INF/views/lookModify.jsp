<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>글 수정</title>
    <link rel="stylesheet" href="static/css/jeans_header_.css">
    <link rel="stylesheet" href="static/css/jeans_write_body.css">
    <link href="https://unpkg.com/filepond/dist/filepond.css" rel="stylesheet">
    <link href="https://unpkg.com/filepond-plugin-image-preview/dist/filepond-plugin-image-preview.css" rel="stylesheet">
</head>
<body>

<div class="webview">
    <!--/*여기는 맨 위에 있는 바 부분*/ -->
         <jsp:include page="header.jsp" flush="false"/>
    <!-- /*여기부터가 본문*/  -->
    <form name="writeForm">
        <div class="body_root"> <!--/* 전체 바탕 아무 것도 안함*/ -->
            <%--@elvariable id="boardDto" type="jeans"--%>


            <!--/* 위에 있는 바와 간격 벌리기 위한것*/ -->

            <div class="body_lookupload">
                <!--/* 본문 바탕 */ -->

                <div class="space"></div>
                <div class="space"/>
            </div>
            <!-- /* class 이 space 인건 layout 을 위해 넣은 빈공간임*/-->
            <div><span class="title">내 룩 수정</span></div>
            <div class="space"></div>
            <%--로그인한 아이디와 작성자 아이디 비교위해 작성자 아이디 넘겨줌 --%>
            <input type="hidden"  name="fk_userid_user_userid" value="${view.fk_userid_user_userid}"/>
            <%--게시글키 가져오기--%>
            <input type="hidden"  name="look_num" value="${view.look_num}"/>
            <div class="name">
                <span class="Jeans_bule">*글제목</span>ㅤ
                <input type="title" class="input_name" name="title" value="${view.title}"/>
            </div>

            <div class="space"></div>

            <div class="upload" id="js-uploadDiv">
            </div>

            <div class="space"></div>

            <div class="img_space">
                <input type="file" multiple  name="profile_pt" id="profile_pt"
                accept=".jpg, .jpeg, .png, .svg, .gif"/>
                <!-- <div class="look_img_container">
                    <div class="look_img_viewport">
                        <div class="look_flick_camera" id="View_area">
                        </div>

                        <button type="button" class="look_slide_button" id="look_slide_button_left" style="left: 0;">
                            <img src="static/images/look_slide_icon_left.png" class="look_slide_button_icon">
                        </button>
                        <button type="button" class="look_slide_button" id="look_slide_button_right" style="right: 0;">
                            <img src="static/images/look_slide_icon_right.png" class="look_slide_button_icon">
                        </button>
                    </div>

                </div> -->
            </div>

            <!--{/* 오른쪽 부분들 */} -->
            <div class="upload_right">

                <div><span class="Jeans_bule">*계절</span></div>

                <div class="look_season">
                    <input type="checkbox" value="봄" name="season"/>봄
                    <input type="checkbox" value="여름" name="season"/>여름
                    <input type="checkbox" value="가을" name="season"/>가을
                    <input type="checkbox" value="겨울" name="season"/>겨울
                </div>

                <div class="space"></div>

                <div class="public">
                    <span class="Jeans_bule">*공개여부</span>
                </div>

                <div>
                    <input type="radio" name="look_public" value="1">공개
                    <input type="radio" name="look_public" value="2">비공개

                </div>

                <div class="space"></div>

                <div class="tag">
                    <span class="Jeans_bule">*태그</span>
                </div>

<%--                <div>
                    <textarea name="tag" class="input_tag">${view.tag}</textarea>
                </div>--%>

                <div class="space"></div>
                <div class="space"></div>
                <div class="space"></div>
                <div class="space"></div>

                <div class="tag">
                    <span class="Jeans_bule"  >*메모</span>
                </div>

                <div class="memo">
                    <textarea name="memo" class="input_memo">${view.memo}</textarea>
                </div>
            </div>

            <div class="space"></div>
            <div>
                <div class="save">
                    <button type="button" class="save_button" onclick="modifiy()">수정</button>
                </div>
            </div>

        </div>
    </form>
</div>
<!-- FileFond CDN -->
<script src="https://unpkg.com/filepond-plugin-image-preview/dist/filepond-plugin-image-preview.js"></script>
<script src="https://unpkg.com/filepond/dist/filepond.js"></script>
<script src="https://unpkg.com/filepond-plugin-file-metadata/dist/filepond-plugin-file-metadata.js"></script>
<script src="https://unpkg.com/filepond-plugin-image-crop/dist/filepond-plugin-image-crop.js"></script>
<script src="https://unpkg.com/filepond-plugin-file-encode/dist/filepond-plugin-file-encode.js"></script>
<!-- FileFond CDN -->
<script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="/static/js/id_nickname_session.js"></script>
<script type="text/javascript" src="/static/js/lookModify/lookModify.js"></script>
<%--수정전 내가선택한 계절 이랑 공개여부 값보내기--%>
<script>seasonLook_publicResult('${view.season}',${view.look_privacy})</script>
<script>lookReady(${view.look_num})</script>

</body>
</html>


