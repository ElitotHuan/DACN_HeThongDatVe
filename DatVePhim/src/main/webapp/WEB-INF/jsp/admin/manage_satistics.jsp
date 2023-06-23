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

        $(document).on('submit', '#addRoomForm', function (event) {
            var frm = $('#addRoomForm');
            var Form = this;
            var data = {};

            $.each(this, function (i, v) {
                var input = $(v);
                data[input.attr("id")] = input.val();
                delete data["undefined"];
            });

            data['totalArea'] = parseFloat(data['totalArea']);
            data['capacity'] = parseInt(data['capacity']);

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

            data["branchId"] = parseInt(data["branchId"]);
            data["roomId"] = parseInt(data["roomId"]);

            console.log(JSON.stringify(data));

            $.ajax({
                contentType: "application/json;charset=UTF-8",
                type: frm.attr('method'),
                url: frm.attr('action') + data["roomId"],
                data: JSON.stringify(data),
                success: function () {
                    window.location.reload();
                }
            });
        });

    </script>

    <title>Manage Satistics :: Admin</title>
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
                                    <a class="navbar-brand" href="#">Satistics</a>
                                    <div class="collapse navbar-collapse menu" id="navbarSupportedContent">
                                        <ul class="navbar-nav ml-auto menu-nav">
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
                                        <th>BRANCH NAME</th>
                                        <th>EMPLOYEE TOTAL</th>
                                        <th>TOTAL</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="in" items="${info}">
                                        <tr>
                                            <td>
                                                <input class="form-control" name="branchName" type="text"
                                                       value="${in.name}"/>
                                            </td>
                                            <td>
                                                <input class="form-control" name="branchName" type="text"
                                                       value="${in.employee}"/>
                                            </td>
                                            <td><input name="total" class="form-control" type="number"
                                                       value="${in.total}"/></td>
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