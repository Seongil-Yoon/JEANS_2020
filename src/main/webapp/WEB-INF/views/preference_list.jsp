<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
        contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
    <title>추천 글 목록</title>
    <link rel="stylesheet" href="static/css/look_list_Look.css" />
    <link rel="stylesheet" href="static/css/slideShow.css" />
    <jsp:include page="libsStyles.jsp" flush="false" />
</head>

<body>
    <div class="webview">
        <!--/*여기는 맨 위에 있는 바 부분*/ -->
        <jsp:include page="header.jsp" flush="false" />
        <!-- /*여기부터가 본문*/  -->
        <div class="body_root"></div>
    </div>

    <jsp:include page="libsScript.jsp" flush="false" />
    <script src="/static/js/look_list/preferenceScroll.js" charset="utf-8"></script>
    <script src="/static/js/backCashDelete.js"></script>
    <script>setUserId('${sessionScope.userid}');</script>
</body>

</html>