<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 12/04/2023
  Time: 9:43 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href="../../../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
        <!-- Custom Theme files -->
        <link href="../../../static/css/style.css" rel="stylesheet" type="text/css" media="all"/>
        <!-- Custom Theme files -->
        <script src="../../../static/js/jquery.min.js"></script>
        <!-- Custom Theme files -->
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta name="keywords" content="Cinema Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>

        <title>Movie Ticket and Service Website | Login</title>
    </head>
    <div class="full">
        <jsp:include page="menu.jsp"/>
        <div class="main">
            <div class="contact-content">
                <jsp:include page="header.jsp"/>
                <div class="main-contact">
                    <div class="contact-form">
                        <form action="/change-password" method="post" class="form-signin" role="form" onsubmit="return validateForm()">

                            <div class="form-group">
                                <input type="password" class="form-control" name="currentPassword" placeholder="Current Password" required
                                       autofocus/>
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" name="newPassword" placeholder="New Password"
                                       required/>
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" name="confirmPassword" placeholder="Confirm New Password"
                                       required/>
                                <p id="password-error" style="color: red"></p>
                            </div>

                            <button class="btn btn-lg btn-primary btn-block" type="submit">Change Password</button>
                        </form>

                    </div>
                </div>
            </div>
            <div class="footer">
                <jsp:include page="footer.jsp"/>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>

    <script>
        function validateForm() {
            const newPassword = document.getElementsByName('newPassword')[0].value;
            const confirmPassword = document.getElementsByName('confirmPassword')[0].value;

            if (newPassword !== confirmPassword) {
                document.getElementById('password-error').innerHTML = 'Mật khẩu xác nhận không khớp với mật khẩu mới';
                return false;
            }

            return true;
        }
    </script>

</html>
