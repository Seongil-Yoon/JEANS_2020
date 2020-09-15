<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
prefix="form" uri="http://www.springframework.org/tags/form" %> <%@ page
contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>인트로</title>
    <link rel="stylesheet" href="static/css/fullpage.min.css" />
    <link rel="stylesheet" href="static/css/intro.css" />
  </head>
  <body>
    <div class="header">
      <a href="main"><span>main</span></a>
    </div>
    <div id="full-page">
      <div class="section s0">
        <h1>Back-end skills</h1>
        <div>
          <img src="static/images/jsp.png" class="html" />
          <img src="static/images/springboot.png" class="springboot" />
          <img src="static/images/mybatis.png" class="mybatis" />
          <img src="static/images/mysql.png" class="mysql" />
          <img src="static/images/gradle.png" class="gradle" />
        </div>
        <div style="padding: 25px;"></div>
        <h1>Developer</h1>
        <h1>오경택 , 김규진</h1>
      </div>

      <div class="section s1">
        <h1>Web Front-end skills</h1>
        <div>
          <img src="static/images/html.png" class="html" />
          <img src="static/images/jquary.png" class="jquary" />
          <img src="static/images/ajax.png" class="ajax" />
        </div>
        <div style="padding: 25px;"></div>
        <h1>Developer</h1>
        <h1>윤성일 , 김희진</h1>
      </div>

      <div class="section s2">
        <h1>App skills</h1>
        <div>
          <img src="static/images/Android.png" class="Android" />
          <spnn style="padding: 15px;"></spnn>
          <img src="static/images/ko1.jpg" class="Kotlin" />
        </div>
        <div style="padding: 25px;"></div>
        <h1>Developer</h1>
        <h1>박찬영 , 최찬호</h1>
      </div>


    <script src="/static/js/intro/fullpage.min.js"></script>
    <script src="/static/js/intro/intro.js"></script>
    <script
      src="https://code.jquery.com/jquery-3.5.1.min.js"
      integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
      crossorigin="anonymous"
    ></script>
    <script>
      introStart();
    </script>
  </body>
</html>
