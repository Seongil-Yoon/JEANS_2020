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
    <title>회원가입</title>
</head>
<body>
<div>
    <h1>회원가입 화면</h1>
    <form action="/joinRequest" method="post">
        <input type="text" placeholder="아이디" name="user_id"/><br/><br/>
        <input type="password" placeholder="비밀번호" name="user_pw"/><br/><br/>
        <input type="text" placeholder="닉네임" name="user_nickname"/><br/><br/>
        <input type="radio" name="sex" value="남자">남자
        <input type="radio" name="sex" value="여자">여자<br/><br/>
        <input type="text" placeholder="height" name="user_height"/><br/><br/>
        <input type="text" placeholder="weight" name="user_weight"/><br/><br/>
        <input type="text" placeholder="picture" name="user_picture"/><br/><br/>
        <input type="email" placeholder="email" name="user_email"/><br/><br/>
        <input type="submit" value="로그인"><br/><br/>
    </form>
</div>
</body>
</html>