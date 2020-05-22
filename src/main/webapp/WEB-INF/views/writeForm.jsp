<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>룩게시판 글쓰기</title>
</head>
<body>
<h1>룩게시판 글쓰기</h1>
<%--@elvariable id="boardDto" type="jeans"--%>
<form:form modelAttribute="boardDto" action="/boardWriteRequest" method="post">
    <input type="text" placeholder="글제목" name="title" path="title"><form:errors path="title" /><br/><br/>
    <input type="checkbox" name="season" value="봄">봄
    <input type="checkbox" name="season" value="여름">여름
    <input type="checkbox" name="season" value="가을">가을
    <input type="checkbox" name="season" value="겨울">겨울<form:errors path="season" /><br/><br/>
    <textarea placeholder="태그"name="tag"></textarea><form:errors path="tag" /><br/><br/>
    <input type="radio" name="look_public" value="1">공개
    <input type="radio" name="look_public" value="2">비공개<form:errors path="look_public" /><br/><br/>
    <textarea placeholder="메모"name="memo"></textarea><form:errors path="memo" /><br/><br/>
    <input type="submit"value="글쓰기">
</form:form>
</body>
</html>