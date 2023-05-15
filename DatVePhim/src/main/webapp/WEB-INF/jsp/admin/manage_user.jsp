<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <!-- My JavaScript -->
        <script src="../../../static/js/admin.js"></script>
        <!-- /My JavaScript -->
        <link rel="stylesheet" href="../../../static/css/admin/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="../../../static/css/admin/admin.css" type="text/css" media="all">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">

        <script
                src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script
                src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
        <script
                src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function () {
            // Xử lý sự kiện khi người dùng thêm một user mới
            $("#addUserForm").submit(function (event) {
                event.preventDefault();
                var fullName = $("#fullName").val();
                var password = $("#password").val();
                var username = $("#username").val();
                var email = $("#email").val();
                var roles = $("#roles").val();
                var user = {
                    fullName: fullName,
                    password: password,
                    username: username,
                    email: email,
                    roles: roles
                };
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "${pageContext.request.contextPath}/api/addUser",
                    data: JSON.stringify(user),
                    dataType: "json",
                    success: function (data) {
                        alert("User added successfully!");
                        window.location.reload();
                    },
                    error: function (e) {
                        alert("Error while adding user.");
                        console.log("Error while adding user: " + e);
                    }
                });
            });

            // Xử lý sự kiện khi người dùng cập nhật thông tin của một user
            $("form[id^='updateForm']").submit(function (event) {
                event.preventDefault();
                var form = $(this);
                var userId = form.find("input[name='userId']").val();
                var fullName = form.find("input[name='fullName']").val();
                var password = form.find("input[name='password']").val();
                var username = form.find("input[name='username']").val();
                var email = form.find("input[name='email']").val();
                var roles = form.find("input[name='roles']").val();
                var user = {
                    id: userId,
                    fullName: fullName,
                    password: password,
                    username: username,
                    email: email,
                    roles: roles
                };
                $.ajax({
                    type: "PUT",
                    contentType: "application/json",
                    url: "${pageContext.request.contextPath}/api/updateUser/" + userId,
                    data: JSON.stringify(user),
                    dataType: "json",
                    success: function (data) {
                        alert("User updated successfully!");
                        window.location.reload();
                    },
                    error: function (e) {
                        alert("Error while updating user.");
                        console.log("Error while updating user: " + e);
                    }
                });
            });

            // Xử lý sự kiện khi người dùng xóa một user
            $(".btn-danger").click(function (event) {
                event.preventDefault();
                var href = $(this).attr("href");
                $.ajax({
                    type: "DELETE",
                    url: href,
                    success: function (data) {
                        alert("User deleted successfully!");
                        window.location.reload();
                    },
                    error: function (e) {
                        alert("Error while deleting user.");
                    }
                });
            })

            function updateUser() {
                var form = $(this);
                var url = form.attr("action");
                var data = form.serialize();
                $.ajax({
                    type: "PUT",
                    url: url,
                    data: data,
                    success: function () {
                        alert("User updated successfully!");
                    },
                    error: function (e) {
                        alert("Error while updating user.");
                        console.log("Error while updating user");
                    }
                });
            }

            function addUser() {
                var fullName = $("#fullName").val();
                var password = $("#password").val();
                var username = $("#username").val();
                var email = $("#email").val();
                var roles = $("#roles").val();

                $.ajax({
                    url: "${pageContext.request.contextPath}/api/addUser",
                    type: "POST",
                    data: {
                        fullName: fullName,
                        password: password,
                        username: username,
                        email: email,
                        roles: roles
                    },
                    success: function (data) {
                        alert("User added successfully!");
                        location.reload();
                    },
                    error: function (e) {
                        alert("Error while adding user.");
                        console.log("Error while adding user", e);
                    }
                });
            }

            $(document).ready(function () {
                $("#addUserForm").submit(addUser);
                $("#updateForm").submit(updateUser);
            })
        })

    </script>

        <script>

            $(document).on('submit', '#addUserForm', function (event) {
                var frm = $('#addUserForm');
                var Form = this;
                var data = {};

                $.each(this, function (i, v) {
                    var input = $(v);
                    data[input.attr("id")] = input.val();
                    delete data["undefined"];
                });

                data["duration"] = parseInt(data["duration"]);

                console.log(data);
                $.ajax({
                    contentType: "application/json;charset=UTF-8",
                    type: frm.attr('method'),
                    url: frm.attr('action'),
                    data: JSON.stringify(data),
                    success: function () {
                        window.location.reload();
                    }
                });

            });

            $(document).on('submit', '#updateForm', function (e) {
                var frm = $('#updateForm');
                e.preventDefault();
                var Form = this;
                var data = {};

                $.each(this, function (i, v) {
                    var input = $(v);
                    data[input.attr("name")] = input.val();
                    delete data["undefined"];
                });

                if (data["isShowing"] == "Availible") {
                    data["isShowing"] = 1;
                } else {
                    data["isShowing"] = 0;
                }


                data["duration"] = parseInt(data["duration"]);
                data["userId"] = parseInt(data["userId"]);

                console.log(JSON.stringify(data));

                $.ajax({
                    contentType: "application/json;charset=UTF-8",
                    type: frm.attr('method'),
                    url: frm.attr('action') + data["userId"],
                    data: JSON.stringify(data),
                    success: function () {
                        window.location.reload();
                    }
                });
            });

        </script>

        <title>Manage User :: Admin</title>
    </head>
    <body>

    <div class="admin-dashboard">
        <div class="container-fluid">
            <div class="admin-reg">
                <div class="row">
                    <%--                <div class="col-md-3">--%>
                    <%--                    <%@ include file ="admin-profile.jsp" %>--%>
                    <%--                </div>--%>
                    <div class="col-md-9">
                        <div class="col-md-9">
                            <div class="row">
                                <div class="col-md-12">
                                    <nav class="navbar navbar-light navbar-toggleable">
                                        <a class="navbar-brand" href="#">Users</a>
                                        <div class="collapse navbar-collapse menu" id="navbarSupportedContent">
                                            <ul class="navbar-nav ml-auto menu-nav">
                                                <li>
                                                    <a href="#" class="nav-link menu-link" data-toggle="modal"
                                                       data-target="#modalCenter">ADD USER</a>
                                                    <div class="modal fade" id="modalCenter" tabindex="-1" role="dialog"
                                                         aria-labelledby="AddCinema" aria-hidden="true">
                                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="modalLongTitle">Add
                                                                        User</h5>
                                                                    <button type="button" class="close" data-dismiss="modal"
                                                                            aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <form id="addUserForm" method="post"
                                                                          action="${pageContext.request.contextPath}/api/addMovie"
                                                                          onsubmit="return false">
                                                                        <div class="row">
                                                                            <div class="col-sm-12">
                                                                                <div class="form-group">
                                                                                    <input id="fullName"
                                                                                           class="form-control label"
                                                                                           placeholder="Full Name"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-sm-6">
                                                                                <div class="form-group">
                                                                                    <input id="password" type="password"
                                                                                           class="form-control label"
                                                                                           placeholder="Password"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-sm-6">
                                                                                <div class="form-group">
                                                                                    <input type="text" id="username"
                                                                                           class="form-control label"
                                                                                           placeholder="Username"/>
                                                                                </div>
                                                                            </div>

                                                                            <div class="col-sm-6">
                                                                                <div class="form-group">
                                                                                    <input type="email" id="email"
                                                                                           class="form-control label"
                                                                                           placeholder="Email"/>
                                                                                </div>
                                                                            </div>

                                                                            <div class="col-sm-6">
                                                                                <div class="form-group">
                                                                                    <label for="roles">Roles</label>
                                                                                    <select id="roles" class="form-control">
                                                                                        <option value="ADMIN">ADMIN</option>
                                                                                        <option value="USER">USER</option>
                                                                                    </select>
                                                                                </div>
                                                                            </div>


                                                                            <div class="col-sm-12">
                                                                                <input type="submit" value="ADD"
                                                                                       class="btn btn-primary form-control label"/>
                                                                            </div>
                                                                        </div>

                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <a href="/api/admin_home" class="nav-link menu-link">GO BACK</a>
                                                </li>
                                                <%@ include file="menu.jsp" %>
                                            </ul>
                                        </div>
                                    </nav>
                                </div>
                                <div class="col-md-12">
                                    <table class="table table-sm">
                                        <thead>
                                        <tr>
                                            <th>USER ID</th>
                                            <th>FULL NAME</th>
                                            <th>PASSWORD</th>
                                            <th>USERNAME</th>
                                            <th>EMAIL</th>
                                            <th>ROLES</th>
                                            <th>UPDATE</th>
                                            <th>DELETE</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="user" items="${users}">
                                            <tr>
                                                <form id="updateForm" method="PUT" onsubmit="return false" action="${pageContext.request.contextPath}/api/updateUser/">
                                                    <td>
                                                        <input class="form-control" name="userId" type="text" value="${user.id}"/>
                                                    </td>
                                                    <td>
                                                        <input name="fullName" class="form-control" type="text" value="${user.fullName}"/>
                                                    </td>
                                                    <td>
                                                        <input class="form-control" name="password" type="password" value="${user.password}"/>
                                                    </td>
                                                    <td>
                                                        <input class="form-control" name="username" type="text" value="${user.username}"/>
                                                    </td>
                                                    <td>
                                                        <input class="form-control" name="email" type="email" value="${user.email}"/>
                                                    </td>
                                                    <td>
                                                        <input name="roles" class="form-control" type="text" value="${user.roles}"/>
                                                    </td>
                                                    <td>
                                                        <button type="submit" class="btn btn-success"> UPDATE </button>
                                                    </td>
                                                </form>
                                                <td>
                                                    <a href="/api/deleteUser/${user.id}" class="btn btn-danger">DELETE</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    </body>
</html>
