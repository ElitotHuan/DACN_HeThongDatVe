<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<div class="top-header span_top">
    <div class="logo">
        <a href="/Home"><img src="../../../static/images/logo.png" alt=""/></a>
    </div>
    <div class="search v-search">
        <form id="searchFrom" method="get" action="${pageContext.request.contextPath}/searchMovie?name="
              onsubmit="return false">
            <input id="search-input" type="text" value="Search.." onfocus="this.value = '';"
                   onblur="if (this.value == '') {this.value = 'Search..';}"/>
            <input type="submit" value=""/>
        </form>
    </div>
    <div class="login-signup">
        <c:if test="${sessionScope.loggedInUser != null}">
            <span>Hello, <strong>${sessionScope.loggedInUser.username}</strong>!</span>
            <a href="/logout">Đăng xuất</a>
        </c:if>
        <c:if test="${sessionScope.loggedInUser == null}">
            <a id="login" href="login">Log In</a>
            <a id="signup" href="register">Sign Up</a>
        </c:if>
    </div>
    <div class="clearfix"></div>
</div>


</html>