<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2020-05-15
  Time: 오후 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글상세보기</title>
    <script>

    </script>
</head>
<body>
    <h1>게시판 상세보기 페이지</h1>
    <table width="700" cellpadding="0" border="1">
            <tr>
                <td>아이디</td>
                <td>제목</td>
                <td>메모</td>
                <td>조회수</td>
                <td>삭제</td>
            </tr>
            <tr>
                <td>${view.nickname}</td>
                <td>${view.title}</td>
                <td>${view.memo}</td>
                <td>${view.count}</td>
                <td><a href="delete?look_num=${view.look_num}">삭제</a></td>
            </tr>
    </table>
</body>
</html>
