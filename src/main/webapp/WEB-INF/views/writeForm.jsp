<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2020-05-15
  Time: 오후 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>룩게시판 글쓰기</title>
</head>
<body>
<h1>룩게시판 글쓰기</h1>
<form action="/boardWriteRequest"method="post">
    <input type="text" placeholder="글제목" name="title"><br/><br/>
    <input type="checkbox" name="season" value="봄">봄
    <input type="checkbox" name="season" value="여름">여름
    <input type="checkbox" name="season" value="가을">가을
    <input type="checkbox" name="season" value="겨울">겨울<br/><br/>
    <textarea placeholder="태그"name="tag"></textarea><br/><br/>
    <input type="radio" name="look_public" value="1">공개
    <input type="radio" name="look_public" value="2">비공개<br/><br/>
    <textarea placeholder="메모"name="memo"></textarea><br/><br/>
    <input type="submit"value="글쓰기">
</form>
</body>
</html>