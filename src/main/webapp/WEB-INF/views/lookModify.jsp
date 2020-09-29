<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>글 수정</title>
    <link rel="stylesheet" href="static/css/jeans_header_.css">
    <link rel="stylesheet" href="static/css/jeans_write_body.css">
    <jsp:include page="libsStyles.jsp" flush="false" />
</head>

<body>

    <div class="webview">
        <!--/*여기는 맨 위에 있는 바 부분*/ -->
        <jsp:include page="header.jsp" flush="false" />
        <!-- /*여기부터가 본문*/  -->
        <form name="writeForm">
            <div class="body_root">
                <!--/* 전체 바탕 아무 것도 안함*/ -->
                <%--@elvariable id="boardDto" type="jeans"--%>


                <!--/* 위에 있는 바와 간격 벌리기 위한것*/ -->

                <div class="body_lookupload">
                    <!--/* 본문 바탕 */ -->

                    <div class="space"></div>
                    <div class="space" />
                </div>
                <!-- /* class 이 space 인건 layout 을 위해 넣은 빈공간임*/-->
                <span class="boardTitle">내 룩 수정</span>
                <div class="space"></div>
                <%--로그인한 아이디와 작성자 아이디 비교위해 작성자 아이디 넘겨줌 --%>
                <input type="hidden" name="fk_userid_user_userid" value="${view.fk_userid_user_userid}" />
                <%--게시글키 가져오기--%>
                <input type="hidden" name="look_num" value="${view.look_num}" />
                <div class="name">
                    <span class="Jeans_bule">*글제목</span>ㅤ
                    <input type="title" class="input_name" name="title" value="${view.title}" />
                </div>

                <div class="space"></div>

                <div class="upload" id="js-uploadDiv">
                </div>

                <div class="space"></div>

                <div class="img_space">
                    <input type="file" multiple name="profile_pt" id="profile_pt"
                        accept=".jpg, .jpeg, .png, .svg, .gif" />

                </div>

                <!--{/* 오른쪽 부분들 */} -->
                <div class="upload_right">

                    <div><span class="Jeans_bule">*계절</span></div>

                    <div class="look_season">
                        <label>
                            <input type="checkbox" value="봄" name="season" />
                            <span>봄</span>
                        </label>
                        <label>
                            <input type="checkbox" value="여름" name="season" />
                            <span>여름</span>
                        </label>
                        <label>
                            <input type="checkbox" value="가을" name="season" />
                            <span>가을</span>
                        </label>
                        <label>
                            <input type="checkbox" value="겨울" name="season" />
                            <span>겨울</span>
                        </label>
                    </div>

                    <div class="space"></div>

                    <div class="public">
                        <span class="Jeans_bule">*공개여부</span>
                    </div>

                    <div>
                        <label>
                            <input type="radio" name="look_public" value="1">
                            <span>공개</span>
                        </label>
                        <label>
                            <input type="radio" name="look_public" value="2">
                            <span>비공개</span>
                        </label>
                    </div>

                    <div class="space"></div>

                    <div class="tag">
                        <span class="Jeans_bule">*태그</span>
                    </div>

                    <div class="mood_wrap" id="js-mood_wrap">
                        <label class="mood_label">
                            <input type="checkbox" value="스트리트" name="mood" />
                            <span>스트리트</span>
                        </label>
                        <label class="mood_label">
                            <input type="checkbox" value="캐쥬얼" name="mood" />
                            <span>캐쥬얼</span>
                        </label>
                        <label class="mood_label">
                            <input type="checkbox" value="미니멀" name="mood" />
                            <span>미니멀</span>
                        </label>
                        <label class="mood_label">
                            <input type="checkbox" value="그런지" name="mood" />
                            <span>그런지</span>
                        </label>
                        <label class="mood_label">
                            <input type="checkbox" value="시티보이" name="mood" />
                            <span>시티보이</span>
                        </label>
                        <label class="mood_label">
                            <input type="checkbox" value="테크웨어" name="mood" />
                            <span>테크웨어</span>
                        </label>
                        <label class="mood_label">
                            <input type="checkbox" value="워크웨어" name="mood" />
                            <span>워크웨어</span>
                        </label>
                        <label class="mood_label">
                            <input type="checkbox" value="아메카지" name="mood" />
                            <span>아메카지</span>
                        </label>
                        <label class="mood_label">
                            <input type="checkbox" value="밀리터리" name="mood" />
                            <span>밀리터리</span>
                        </label>
                        <!-- <textarea name="tag" class="input_tag"></textarea> -->
                    </div>

                    <div class="space"></div>
                    <div class="space"></div>
                    <div class="space"></div>
                    <div class="space"></div>

                    <div class="tag">
                        <span class="Jeans_bule">*메모</span>
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

    <jsp:include page="libsScript.jsp" flush="false" />
    <script type="text/javascript" src="/static/js/lookModify/lookModify.js"></script>
    <%--수정전 내가선택한 계절 이랑 공개여부 값보내기--%>
    <script>seasonLook_publicResult('${view.season}', ${ view.look_privacy })</script>

    <c:forEach items="${mood}" var="mood">
        <script>moodLook_Result('${mood.mood}')</script>
    </c:forEach>

    <script>lookReady(${ view.look_num })</script>
    <script>
        getLooknum(${ view.look_num })
    </script>
</body>

</html>