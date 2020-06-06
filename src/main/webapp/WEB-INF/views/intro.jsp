<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>fullPage.js</title>
    <link rel="stylesheet" href="static/css/fullpage.min.css"/>
    <link rel="stylesheet" href="static/css/intro.css"/>
    <script src="/static/js/fullpage.min.js"></script>
</head>
    <body>

    <div id="full-page">
        <div class="section s0" >
            <h2>Back end</h2>
            <h3>skills</h3>
        </div>

        <div class="section s1">
            <h2>Web Front end</h2>
            <h3>skills</h3>
        </div>

        <div class="section s2">
            <h2>App</h2>
            <h3>skills</h3>
        </div>

        <div class="section s3" style="background-size: cover; background-image: url('static/images/JeansModel.jpg')" >
            <h2>Jeans</h2>
        </div>
    </div>

    <script>
        new fullpage("#full-page",{
            sectionsColor: ['#66CC66','#33CCFF','#FF9966','#FFFF66'],
            navigation:true, //옆에 화면 움직으는  . . . . 모양 생성
            navigationTooltips: ['Back end','Web Front end','App','Jeans'],
            scrollingSpeed: 1000, // 스크롤 속도1초
            onLeave:function (origin,declaration,direction) {
                
            }
        });
    </script>

    </body>
</html>