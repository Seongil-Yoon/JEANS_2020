<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="static/css/jeans_write_body.css">
    <link rel="stylesheet" href="static/css/slideShow.css" />
</head>
<body>

<div class="webview">
    <!--/*여기는 맨 위에 있는 바 부분*/ -->
        <jsp:include page="header.jsp" flush="false"/>
    <!-- /*여기부터가 본문*/  -->
    <form name="writeForm" encType="multipart/form-data">
        <div class="body_root"> <!--/* 전체 바탕 아무 것도 안함*/ -->

                <div class="header_space"></div>
                <!--/* 위에 있는 바와 간격 벌리기 위한것*/ -->

                <div class="body_lookupload">
                    <!--/* 본문 바탕 */ -->

                    <div class="space"></div>
                    <div class="space"/>
                </div>
                <!-- /* class 이 space 인건 layout 을 위해 넣은 빈공간임*/-->
                <div><span class="title">내 룩 등록</span></div>
                <div class="space"></div>


                <div class="name">

                    <span class="Jeans_bule">*글제목</span>ㅤ
                    <input type="title" class="input_name" name="title"/>&nbsp;

                </div>

                <div class="space"></div>

                <div class="upload" id="js-uploadDiv">
                    사진첨부ㅤ
                    <!-- <input type="file" multiple  name="profile_pt" id="profile_pt" accept=".jpg, .jpeg, .png, .svg, .gif" onchange="previewImage(this,'View_area')"> -->
                    <input type="file" multiple  name="profile_pt" id="profile_pt" accept=".jpg, .jpeg, .png, .svg, .gif"/>
                    <!-- JS에서 이벤트리스너 함수 추가 -->
                </div>

                <div class="space"></div>

                <div  class="img_space">
                    <div class="look_img_container">
                        <div class="look_img_viewport">
                            <div class="look_flick_camera"  id="View_area">  
                                <!-- preview == View_area -->
                                <!-- <div  class="flick_panel" style="left: 0px;">
                                    <img/>
                                </div> JS로 태그 생성-->
                            </div>
            
                            <button type="button" class="look_slide_button" id="look_slide_button_left" style="left: 0;">
                                <img src="static/images/look_slide_icon_left.png" class="look_slide_button_icon">
                            </button>
                            <button type="button" class="look_slide_button" id="look_slide_button_right" style="right: 0;">
                                <img src="static/images/look_slide_icon_right.png" class="look_slide_button_icon">
                            </button>
                        </div>
            
                    </div>
                </div>

                <!--{/* 오른쪽 부분들 */} -->
                <div class="upload_right">

                    <div><span class="Jeans_bule">*계절</span></div>

                    <div>
                        <input type="checkbox" value="봄" name="season"/>&nbsp;봄 &nbsp;
                        <input type="checkbox" value="여름" name="season"/>&nbsp;여름 &nbsp;
                        <input type="checkbox" value="가을" name="season"/>&nbsp;가을 &nbsp;
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

                    <div>

                        <textarea name="tag" class="input_tag"></textarea>

                    </div>

                    <div class="space"></div>
                    <div class="space"></div>
                    <div class="space"></div>
                    <div class="space"></div>

                    <div class="tag">
                        <span class="Jeans_bule">*메모</span>
                    </div>



                    <div class="memo">
                        <textarea name="memo" class="input_memo"></textarea>
                    </div>
                </div>

                <div class="space"></div>
                <div>
                    <div class="save">
                        <button type="button" class="save_button" onclick="lookWrite()">Save</button>
                    </div>
                </div>

        </div>
    </form>
</div>

<script src="/static/js/showiFileImage.js"></script>
<script src="/static/js/lookBoardWrite.js"></script>
<script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="/static/js/id_nickname_session.js"></script>

<%--서버세션이 종료되어 자바스크립트 session 종료--%>
<c:set var="userid" value="${sessionScope.userid}"/>
<c:if test="${userid == null}">
    <script>sessionRemove()</script>
</c:if>
<%--header 부분 초기화--%>
<script>headerReset()</script>
<!-- <script src="static/js/ex1.js"></script> -->
<!-- <script src="/static/js/look_info.js"></script> -->


</body>
</html>


