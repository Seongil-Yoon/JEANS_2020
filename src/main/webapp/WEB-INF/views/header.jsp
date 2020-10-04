<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- 헤더 css,js 불러오기는 libs.jsp파일 -->
<!--/*여기는 맨 위에 있는 바 부분*/ -->
<div class="jeans_root">
  <nav class="jeans_header">
    <ul class="gnb" style="margin: 0; padding: 0">
      <li class="gnb_list search">
        <form class="search_input_form a" action="searchlist?" method="GET">
          <select class="searchOption a" id="js-searchOption" name="searchOption">
            <option value="title">제목</option>
            <option value="nickname">글쓴이</option>
            <option value="season">계절</option>
            <option value="memo">메모내용</option>
          </select>
          <div class="search_input a">
            <input type="text" class="keyword" name="keyword" value="" />
          </div>
          <button class="search_logo">
            <img src="/static/images/search.jpg" alt="search" />
          </button>
        </form>
        <button class="recomendBtn" id="js-recomendBtn">
          <a href="/preferencelist">
            <img src="/static/images/recomend_button.png" alt="logo" />
          </a>
        </button>
      </li>
      <li class="gnb_list logo">
        <a class="header_a" href="/main">
          <img src="/static/images/logo.PNG" alt="logo" />
        </a>
      </li>
      <li class="gnb_list menu">
        <c:choose>
          <%--로그인 한경우--%>
          <c:when test="${sessionScope.userid != null}">
            <a class="header_a" href="/look_write">
              <div class="logo_right">
                <span class="look_write">look_write</span>
              </div>
            </a>
            <a class="header_a" href="mypageUser/${sessionScope.usernickname}">
              <div class="my_info">
                <div class="my_picture">
                  <img src="/displaySthumbnail" />
                </div>
                <span class="user_nickname">${sessionScope.usernickname}</span>
              </div>
            </a>
            <a class="header_a" href="javascript:logout();">
              <div class="logout_login">logout</div>
            </a>
          </c:when>
          <c:otherwise>
            <a class="header_a" href="joinUser">
              <div class="logo_right">
                <span class="look_write">회원가입</span>
              </div>
            </a>
            <a class="header_a" href="loginUser">
              <div class="logout_login">login</div>
            </a>
          </c:otherwise>
        </c:choose>
      </li>
    </ul>
  </nav>
</div>
<div class="header_space"></div>
