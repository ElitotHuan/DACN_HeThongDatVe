<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html> <head> <link href="../../../static/css/bootstrap.css" rel='stylesheet' type='text/css'/> <!-- Custom Theme files --> <link href="../../../static/css/style.css" rel="stylesheet" type="text/css" media="all"/> <!-- Custom Theme files --> <script src="../../../static/js/jquery.min.js"></script> <!-- Custom Theme files --> <meta name="viewport" content="width=device-width, initial-scale=1"/> <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> <meta name="keywords" content="Cinema Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <title>Movie Ticket and Service Website | Forgot Password</title>
</head>
    <body>
        <div class="full"> <jsp:include page="menu.jsp"/>
            <div class="main"> <div class="contact-content">
                <jsp:include page="header.jsp"/>
                <div class="main-contact">
                    <div class="contact-form">
                        <form action="/forgot-password" method="post" class="form-signin" role="form">
                            <div class="form-group">
                                <input type="email" class="form-control" name="email" placeholder="Email address" required autofocus/>
                            </div>
                            <!-- Thêm thông báo lỗi ở đây -->
                            <div class="form-group"> <p style="color: red">${errorMsg}</p> </div>
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Reset Password</button>
                        </form>
                    </div>
                </div>
            </div>
                <div class="footer"> <jsp:include page="footer.jsp"/> </div>
</div>
    <div class="clearfix"></div>
</div> </body> </html>