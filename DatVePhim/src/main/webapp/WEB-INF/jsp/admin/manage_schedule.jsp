<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- My JavaScript -->
    <script src="../../../static/js/admin.js"></script>
    <!-- /My JavaScript -->
    <link rel="stylesheet" href="../../../static/css/admin/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../../../static/css/admin/admin.css" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">

    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <script type="text/javascript">

        $(document).on('submit', '#addScheduleForm', function (event) {
            var frm = $('#addScheduleForm');
            event.preventDefault();
            var Form = this;
            var data = {};

            var select2 = document.getElementById("roomName");
            var roomName = $("option:selected", select2).text();

            $.each(this, function (i, v) {
                var input = $(v);
                data[input.attr("id")] = input.val();
                delete data["undefined"];
            });

            data["price"] = parseFloat(data["price"]);
            data["movieId"] = parseInt(data["movieId"]);
            data["branchId"] = parseInt(data["branchId"]);
            data["roomName"] = roomName;

            console.log(JSON.stringify(data));
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


        $(document).on('submit', '#updateForm', function (event) {
            var frm = $('#updateForm');
            event.preventDefault();
            var Form = this;
            var data = {};

            var select2 = document.getElementById("rName");
            var roomName = $("option:selected", select2).text();

            $.each(this, function (i, v) {
                var input = $(v);
                data[input.attr("name")] = input.val();
                delete data["undefined"];
            });

            data["price"] = parseFloat(data["price"]);
            data["movieId"] = parseInt(data["movieId"]);
            data["branchId"] = parseInt(data["branchId"]);
            data["roomName"] = roomName;

            console.log(JSON.stringify(data));
            $.ajax({
                contentType: "application/json;charset=UTF-8",
                type: frm.attr('method'),
                url: frm.attr('action') + data["scheduleId"],
                data: JSON.stringify(data),
                success: function () {
                    window.location.reload();
                }
            });

        });

        function catch_value_types() {
            const selectedValue = document.getElementById("branchId").value;
            const select2 = document.getElementById("roomName");

            Array.from(select2.options).forEach((node) => node.style.display = node.value === selectedValue ? "block" : "none");
        }

        function change_value_by_select() {
            const branchValue = document.getElementById("bId").value;
            const room = document.getElementById("rName");
            Array.from(room.options).forEach((node) => node.style.display = node.value === branchValue ? "block" : "none");
        }
    </script>
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
                                    <a class="navbar-brand" href="#">Schedules</a>
                                    <div class="collapse navbar-collapse menu" id="navbarSupportedContent">
                                        <ul class="navbar-nav ml-auto menu-nav">
                                            <li>
                                                <a href="#" class="nav-link menu-link" data-toggle="modal"
                                                   data-target="#modalCenter">ADD SCHEDULE</a>
                                                <div class="modal fade" id="modalCenter" tabindex="-1" role="dialog"
                                                     aria-labelledby="AddCinema" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="modalLongTitle">Add
                                                                    Schedules</h5>
                                                                <button type="button" class="close" data-dismiss="modal"
                                                                        aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <form id="addScheduleForm" method="post"
                                                                      action="${pageContext.request.contextPath}/api/addSchedule"
                                                                      onsubmit="return false">
                                                                    <div class="row">
                                                                        <div class="col-sm-12">
                                                                            <div class="form-group">
                                                                                <input type="number" id="price"
                                                                                       class="form-control label"
                                                                                       placeholder="Price"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-6">
                                                                            <div class="form-group">
                                                                                <input type="date" id="startDate"
                                                                                       class="form-control label"
                                                                                       placeholder="Start date"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-6">
                                                                            <div class="form-group">
                                                                                <input type="time" id="startTime"
                                                                                       class="form-control label"
                                                                                       placeholder="Start time"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-12">
                                                                            <div class="form-group">
                                                                                <select class="form-control"
                                                                                        id="movieId">
                                                                                    <c:forEach var="mov"
                                                                                               items="${movies}">
                                                                                        <option value="${mov.id}">${mov.name}</option>
                                                                                    </c:forEach>
                                                                                </select>
                                                                            </div>
                                                                        </div>

                                                                        <div class="col-sm-6">
                                                                            <div class="form-group">
                                                                                <select class="form-control"
                                                                                        onchange="catch_value_types()"
                                                                                        id="branchId">
                                                                                    <option>Please Select</option>
                                                                                    <c:forEach var="branch"
                                                                                               items="${branches}">
                                                                                        <option value="${branch.id}">${branch.name}</option>
                                                                                    </c:forEach>
                                                                                </select>
                                                                            </div>
                                                                        </div>

                                                                        <div class="col-sm-6">
                                                                            <div class="form-group">
                                                                                <select class="form-control"
                                                                                        id="roomName">
                                                                                    <option>Please Select</option>
                                                                                    <c:forEach var="room"
                                                                                               items="${rooms}">
                                                                                        <option value="${room.branch.id}">${room.name}</option>
                                                                                    </c:forEach>
                                                                                </select>
                                                                            </div>
                                                                        </div>

                                                                        <div class="col-sm-12">
                                                                            <button type="submit"
                                                                                    class="btn btn-primary form-control label">
                                                                                ADD
                                                                            </button>
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
                                        <th>ID</th>
                                        <th>PRICE</th>
                                        <th>START_DATE</th>
                                        <th>START_TIME</th>
                                        <th>MOVIE_NAME</th>
                                        <th>BRANCH_NAME</th>
                                        <th>ROOM_NAME</th>
                                        <th>UPDATE</th>
                                        <th>DELETE</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="schedule" items="${schedules}">
                                        <tr>
                                            <form id="updateForm" method="put"
                                                  action="${pageContext.request.contextPath}/api/updateSchedule/"
                                                  onsubmit="return false">
                                                <td><input class="form-control" name="scheduleId"
                                                           value="${schedule.id}"/>
                                                </td>
                                                <td>
                                                    <input class="form-control" name="price" value="${schedule.price}"/>
                                                </td>
                                                <td>
                                                    <input class="form-control" name="startDate"
                                                           value="${schedule.startDate}"/>
                                                </td>
                                                <td><input class="form-control" name="startTime"
                                                           value="${schedule.startTime}"/></td>
                                                <td>
                                                    <select class="form-control" name="movieId">
                                                        <c:forEach var="mov" items="${movies}">
                                                            <c:choose>
                                                                <c:when test="${schedule.movie.id eq mov.id}">
                                                                    <option selected
                                                                            value="${mov.id}">${mov.name}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${mov.id}">${mov.name}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td>
                                                    <select class="form-control" onchange="change_value_by_select()"
                                                            name="branchId" id="bId">

                                                        <c:forEach var="branch"
                                                                   items="${branches}">
                                                            <c:choose>
                                                                <c:when test="${schedule.branch.id eq branch.id}">
                                                                    <option selected
                                                                            value="${branch.id}">${branch.name}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${branch.id}">${branch.name}</option>
                                                                </c:otherwise>
                                                            </c:choose>

                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td>
                                                    <select class="form-control" name="roomName" id="rName">
                                                        <c:forEach var="room"
                                                                   items="${rooms}">
                                                            <c:choose>
                                                                <c:when test="${room.id eq schedule.room.id}">
                                                                    <option selected
                                                                            value="${room.branch.id}">${room.name}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${room.branch.id}">${room.name}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </td>

                                                <td><input type="submit" class="btn btn-success" value="UPDATE"></td>
                                            </form>
                                            <td><a href="/api/deleteSchedule/${schedule.id}"
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
