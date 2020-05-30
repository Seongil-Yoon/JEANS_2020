<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>회원가입</title>
</head>
<body>
<div>
    <h1>회원가입 화면</h1>
    <%--@elvariable id="userDto" type="jeans"--%>
    <form:form modelAttribute="userDto" action="/joinRequest" method="post">

        <input type="text" placeholder="아이디" name="userid" ><form:errors path="userid" /><br/><br/>
        <input type="password" placeholder="비밀번호" name="password"><form:errors path="password" /><br/><br/>
        <input type="text" placeholder="닉네임" name="nickname"><form:errors path="nickname"/><br/><br/>
        
        <input type="radio" name="sex" value=1 checked>남자
        <input type="radio" name="sex" value=0>여자<br/><br/>

        <input type="number"  placeholder="height" name="height"><br/><br/>
        <input type="number" placeholder="weight" name="weight"><br/><br/>
        <input type="text" placeholder="picture" name="picture"><br/><br/>
        <input type="email" placeholder="email" name="email"><form:errors path="email"/><br/><br/>

        <input type="submit" value="로그인"><br/><br/>
    </form:form>
</div>
</body>
</html>