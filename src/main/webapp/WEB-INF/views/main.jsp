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

        <%if(session.getAttribute("userid")==null)
                {
             %>
            <a  href="loginUser">로그인</a>
            <%}else{
               String nick=(String)session.getAttribute("usernickname");
                System.out.println("nick = " + session.getAttribute("usernickname"));
                System.out.println("nick = " + session.getAttribute("userid"));
            %>
            <h1></h1>
            <a href="logout">로그아웃</a>
            <%}%>
        </li>
        <li>
            <a  href="joinUser">회원가입</a>
        </li>
        <li>
            <a  href="writeForm">글쓰기</a>
        </li>
    </ul>
</div>
</body>
</html>