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

            data["total"] = parseInt(data["total"]);

            console.log(JSON.stringify(data));

            $.ajax({
                contentType: "application/json;charset=UTF-8",
                type: frm.attr('method'),
                url: frm.attr('action') + data["ticketId"],
                data: JSON.stringify(data),
                success: function () {
                    window.location.reload();
                }
            });
        });

    </script>

    <title>Manage Tickets :: Admin</title>
</head>
<body>

<div class="admin-dashboard">
    <div class="container-fluid">
        <div class="admin-reg">
            <div class="row">

                <div class="col-md-9">
                    <div class="col-md-9">
                        <div class="row">
                            <div class="col-md-12">
                                <nav class="navbar navbar-light navbar-toggleable">
                                    <a class="navbar-brand" href="#">Tickets</a>
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
                                        <th>TICKET ID</th>
                                        <th>USERNAME</th>
                                        <th>SEATS</th>
                                        <th>TOTAL</th>
                                        <th>MOVIE NAME</th>
                                        <th>START DATE</th>
                                        <th>START TIME</th>
                                        <th>BRANCH</th>
                                        <th>ROOM</th>
                                        <th>UPDATE</th>
                                        <th>DELETE</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="ticket" items="${tickets}">
                                        <tr>
                                            <form id="updateForm" method="PUT" onsubmit="return false"
                                                  action="${pageContext.request.contextPath}/api/updateTicket/">
                                                <td>
                                                    <input class="form-control" name="ticketId" type="text"
                                                           value="${ticket.id}"/>
                                                </td>
                                                <td>
                                                    <input name="username" class="form-control" type="text"
                                                           value="${ticket.username}"/>
                                                </td>
                                                <td>
                                                    <input name="seating" class="form-control" type="text"
                                                           value="${ticket.seating}"/>
                                                </td>
                                                <td>
                                                    <input name="total" class="form-control" type="number"
                                                           value="${ticket.total}"/>
                                                </td>

                                                <input name="scheduleId" class="form-control" type="text"
                                                       style="display: none"
                                                       value="${ticket.schedule.id}"/>

                                                <td>
                                                    <input class="form-control" type="text"
                                                           value="${ticket.schedule.movie.name}"/>
                                                </td>
                                                <td>
                                                    <input class="form-control" type="date"
                                                           value="${ticket.schedule.startDate}"/>
                                                </td>
                                                <td>
                                                    <input class="form-control" type="time"
                                                           value="${ticket.schedule.startTime}"/>
                                                </td>

                                                <td>
                                                    <input class="form-control" type="text"
                                                           value="${ticket.schedule.branch.name}"/>
                                                </td>
                                                <td>
                                                    <input class="form-control" type="text"
                                                           value="${ticket.schedule.room.name}"/>
                                                </td>

                                                <td>
                                                    <button type="submit" class="btn btn-success"> UPDATE</button>
                                                </td>
                                            </form>
                                            <td>
                                                <a href="/api/deleteTicket/${ticket.id}"
                                                   class="btn btn-danger">DELETE</a>
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
