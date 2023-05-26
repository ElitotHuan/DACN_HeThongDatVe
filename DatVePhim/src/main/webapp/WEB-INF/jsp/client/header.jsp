<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script type="text/javascript">
        $(document).on('submit', '#searchFrom', function (event) {
            var frm = $('#searchFrom');
            var searchInput = document.getElementById("search-input").value;
            $.ajax({
                type: frm.attr('method'),
                url: frm.attr('action'),
                data: {
                    name: searchInput
                },
                success: function () {
                    window.location.replace(frm.attr('action') + searchInput);
                }
            });
        });

        $(function () {
            $("#search-input").autocomplete({
                source: function (request, response) {
                    $.ajax({
                        url: "http://localhost:8080/search",
                        dataType: "json",
                        data: {
                            q: request.term
                        },
                        success: function (data) {
                            //alert(data);
                            console.log(data);
                            response(data);
                        }
                    });
                },
            });
        });
    </script>
    <script type="text/javascript" src="../../../static/js/jquery.flexisel.js"></script>
</head>
<div class="top-header span_top">
    <div class="logo">
        <a href="/"><img src="../../../static/images/logo.png" alt=""/></a>
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
        <p style="color:green">${successMsgChangePassword}</p>
        <c:if test="${sessionScope.loggedInUser != null}">
            <span>Hello, <strong>${sessionScope.loggedInUser.username}</strong>!</span>
            <a href="change-password">Đổi mật khẩu</a>
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