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
    <title>로그인</title>
</head>
<body>
<div>
    <h1>Login 화면</h1>
    <form action="/loginRequest" method="post">
        <input type="text" placeholder="아이디" name="userid"/><br/><br/>
        <input type="password" placeholder="비밀번호" name="password"/><br/><br/>
        <input type="submit" value="로그인"><br/><br/>
    </form>
</div>
</body>
</html>