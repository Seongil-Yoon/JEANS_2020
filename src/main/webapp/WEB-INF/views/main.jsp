<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2020-05-15
  Time: 오후 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>룩게시판</title>
</head>
<body>

<div>
    <ul>
        <li>
            <a  href="loginUser">로그인</a>
        </li>
        <li>
            <a  href="joinUser">회원가입</a>
        </li>
        <li>
            <a  href="writeForm">게시판 작성</a>
        </li>
    </ul>
</div>

<table width="700" cellpadding="0" border="1">
    <tr>
        <td>룩넘버</td>
        <td>아이디</td>
        <td>작성시간</td>
        <td>제목</td>
        <td>조회수</td>
        <td>상세보기</td>
        <td>삭제</td>
    </tr>
    <c:forEach items="${list}" var="dto">
        <tr>
            <td>${dto.look_num}</td>
            <td>${dto.userid}</td>
            <td>${dto.look_date}</td>
            <td>${dto.title}</td>
            <td>${dto.count}</td>
            <td><a href="view?look_num=${dto.look_num}">상세보기</a></td>
            <td><a href="delete?look_num=${dto.look_num}">삭제</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>