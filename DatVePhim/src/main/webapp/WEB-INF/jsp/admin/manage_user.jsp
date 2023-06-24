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

        $(document).on('submit', '#addUserForm', function (event) {
            var frm = $('#addUserForm');
            var Form = this;
            var data = {};

            $.each(this, function (i, v) {
                var input = $(v);
                if (input.attr("id") !== "role") {
                    data[input.attr("id")] = input.val();}
                delete data["undefined"];
            });
            console.log({data});
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
                if (input.attr("name") !== "role") {
                    data[input.attr("name")] = input.val();}
                delete data["undefined"];
            });


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

    <title>Manage Users :: Admin</title>
</head>
<body>

<div class="admin-dashboard">
    <div class="container-fluid">
        <div class="admin-reg">
            <div class="row">
                <%--                <div class="col-md-3">--%>
                <%--                    <%@ include file ="admin-profile.jsp" %>--%>
                <%--                </div>--%>
                <div class="col-md-12">
                    <div class="col-md-12">
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
                                                                      action="${pageContext.request.contextPath}/api/addUser"
                                                                      onsubmit="return false">
                                                                    <div class="row">
                                                                        <div class="col-sm-12">
                                                                            <div class="form-group">
                                                                                <input id="fullName"
                                                                                       class="form-control label"
                                                                                       placeholder="FullName"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-6">
                                                                            <div class="form-group">
                                                                                <input id="username" type="text"
                                                                                       class="form-control label"
                                                                                       placeholder="Username" required/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-6">
                                                                            <div class="form-group">
                                                                                <input type="email" id="email"
                                                                                       class="form-control label"
                                                                                       placeholder="Email" required/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-6">
                                                                            <div class="form-group">
                                                                                <input type="text" id="address"
                                                                                       class="form-control label"
                                                                                       placeholder="Address"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-6">
                                                                            <div class="form-group">
                                                                                <input type="date" id="birthday"
                                                                                       class="form-control label"
                                                                                       placeholder="Birthday"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-6">
                                                                            <div class="form-group">
                                                                                <input type="password" id="password"
                                                                                       class="form-control label"
                                                                                       placeholder="Password" required/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-6">
                                                                            <div class="form-group">
                                                                                <select id="role"  class="form-control label">
                                                                                    <option value="ADMIN">ADMIN</option>
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
                                        <th>FULLNAME</th>
                                        <th>USERNAME</th>
                                        <th>EMAIL</th>
                                        <th>PASSWORD</th>
                                        <th>BIRTHDAY</th>
                                        <th>ADDRESS</th>
                                        <th>ROLES</th>
                                        <th>UPDATE</th>
                                        <th>DELETE</th>

                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="user" items="${users}">
                                        <tr>
                                            <form id="updateForm" method="PUT" onsubmit="return false"
                                                  action="${pageContext.request.contextPath}/api/updateUser/">
                                                <td>
                                                    <input class="form-control" name="userId" type="text"
                                                           value="${user.id}" disabled/>
                                                </td>
                                                <td><input name="fullName" class="form-control" type="text"
                                                           value="${user.fullName}"/></td>
                                                <td>
                                                    <input class="form-control" name="username" type="text"
                                                           value="${user.username}"/>
                                                </td>
                                                <td>
                                                    <input class="form-control" name="email" type="text"
                                                           value="${user.email}"/>
                                                </td>
                                                <td>
                                                    <input class="form-control" name="password" type="password"
                                                           value="${user.password}" disabled/>
                                                </td>
                                                <td>
                                                    <input class="form-control" name="birthday" type="date"
                                                           value="${user.birthday}" />
                                                </td>
                                                <td>
                                                    <input class="form-control" name="address" type="text"
                                                           value="${user.address}" />
                                                </td>

                                                <td>
                                                    <c:forEach var="role" items="${user.roles}">
                                                        <input class="form-control" name="role" type="text"
                                                               value="${role.name}" disabled/>
                                                    </c:forEach>
                                                </td>

                                                <td>
                                                    <button type="submit"
                                                            class="btn btn-success">
                                                        UPDATE
                                                    </button>
                                                </td>
                                            </form>
                                            <td><a href="/api/deleteUser/${user.id}"
                                                   class="btn btn-danger">DELETE</a></td>
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