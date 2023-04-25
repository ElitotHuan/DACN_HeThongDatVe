<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 20/04/2023
  Time: 12:08 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/admin/style.css">
    <link href='https://fonts.googleapis.com/css?family=Baloo' rel='stylesheet'>

    <title>Login :: Bookmymovie.com</title>
</head>
<body>
<br><br><br>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-sm-4">
            <div class="login-panel">
                <h1>Admin</h1>
                <br>
                <form action="performLogin" method="post">
                    <div class="form-group">
                        <label>Username</label>
                        <input name="Username" id="email" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="Password" id="password" class="form-control">
                    </div>
                    <input type="submit" value="Login" class="btn cust-btn form-control">
                </form>
            </div>
        </div>
    </div>
</div>
<br><br><br>
</body>
</html>
