<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <meta charset="UTF-8" />
    <title>글 목록</title>

    <!-- <link rel="stylesheet" href="static/css/look_list.css"/> -->
    <link rel="stylesheet" href="static/css/look_list_Look.css" />
    <link rel="stylesheet" href="static/css/slideShow.css" />
  </head>
  <body>
    <div class="webview">
      <!--/*여기는 맨 위에 있는 바 부분*/ -->
      <jsp:include page="header.jsp" flush="false" />
      <!-- /*여기부터가 본문*/  -->
      <div class="body_root"></div>
    </div>

    <script src="/static/js/look_list/mainScroll.js" charset="utf-8"></script>
    <script
      src="https://code.jquery.com/jquery-3.5.1.min.js"
      integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
      crossorigin="anonymous"
    ></script>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"
    />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
    <script src="/static/js/backCashDelete.js"></script>
    <script>
      <%--파이썬에 데이터 받아오기위해 아이디 넘겨주기--%>
      setUserId('${sessionScope.userid}');
    </script>
  </body>
</html>
