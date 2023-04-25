<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- My JavaScript -->
    <script src="../../../static/js/admin.js"></script>
    <!-- /My JavaScript -->
    <link rel="stylesheet" href="../../../static/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../../../static/css/admin/admin.css" type="text/css" media="all">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">

    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>


    <script>

        $(document).on('submit', '#addMovieForm', function (event) {
            var frm = $('#addMovieForm');
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

            data["duration"] = parseInt(data["duration"]);
            data["movieId"] = parseInt(data["movieId"]);

            console.log(JSON.stringify(data));

            $.ajax({
                contentType: "application/json;charset=UTF-8",
                type: frm.attr('method'),
                url: frm.attr('action') + data["movieId"],
                data: JSON.stringify(data),
                success: function () {
                    window.location.reload();
                }
            });
        });
    </script>

    <title>Manage Movies :: Admin</title>
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
                                    <a class="navbar-brand" href="#">Movies</a>
                                    <div class="collapse navbar-collapse menu" id="navbarSupportedContent">
                                        <ul class="navbar-nav ml-auto menu-nav">
                                            <li>
                                                <a href="#" class="nav-link menu-link" data-toggle="modal"
                                                   data-target="#modalCenter">ADD MOVIE</a>
                                                <div class="modal fade" id="modalCenter" tabindex="-1" role="dialog"
                                                     aria-labelledby="AddCinema" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="modalLongTitle">Add
                                                                    Movie</h5>
                                                                <button type="button" class="close" data-dismiss="modal"
                                                                        aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <form id="addMovieForm" method="post"
                                                                      action="${pageContext.request.contextPath}/api/addMovie"
                                                                      onsubmit="return false">
                                                                    <div class="row">
                                                                        <div class="col-sm-12">
                                                                            <div class="form-group">
                                                                                <input id="name"
                                                                                       class="form-control label"
                                                                                       placeholder="Name"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-6">
                                                                            <div class="form-group">
                                                                                <input id="duration" type="number"
                                                                                       class="form-control label"
                                                                                       placeholder="Duration"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-6">
                                                                            <div class="form-group">
                                                                                <input type="date" id="releaseDate"
                                                                                       class="form-control label"
                                                                                       placeholder="Release Date"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-12">
                                                                            <div class="form-group">
                                                                                <textarea id="description"
                                                                                          form="addMovieForm"
                                                                                          class="form-control label"
                                                                                          rows="3"
                                                                                          placeholder="Description"></textarea>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-12">
                                                                            <div class="form-group">
                                                                                <input id="director"
                                                                                       class="form-control label"
                                                                                       placeholder="Director"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-12">
                                                                            <div class="form-group">
                                                                                <input id="actors"
                                                                                       class="form-control label"
                                                                                       placeholder="Actors"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-6">
                                                                            <div class="form-group">
                                                                                <input id="smallImageURl"
                                                                                       class="form-control label"
                                                                                       placeholder="Small imageURL"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-6">
                                                                            <div class="form-group">
                                                                                <input id="largeImageURL"
                                                                                       class="form-control label"
                                                                                       placeholder="Large imageURL"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-12">
                                                                            <div class="form-group">
                                                                                <input id="language"
                                                                                       class="form-control label"
                                                                                       placeholder="Language"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-12">
                                                                            <div class="form-group">
                                                                                <input id="categories"
                                                                                       class="form-control label"
                                                                                       placeholder="Category"/>
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
                                        <th>MOVIEID</th>
                                        <th>NAMEOFMOVIES</th>
                                        <th>SMALLIMAGEURL</th>
                                        <th>LARGEIMAGEURL</th>
                                        <th>RELEASE DATE</th>
                                        <th>DURATION</th>
                                        <th>CATEGORY</th>
                                        <th>LANGUAGE</th>
                                        <th>DESCRIPTION</th>
                                        <th>DIRECTOR</th>
                                        <th>CAST</th>
                                        <th>STATUS</th>
                                        <th>UPDATE</th>
                                        <th>DELETE</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="mov" items="${movies}">
                                        <tr>
                                            <form id="updateForm" method="PUT" onsubmit="return false"
                                                  action="${pageContext.request.contextPath}/api/updateMovie/">
                                                <td>
                                                    <input class="form-control" name="movieId" type="text"
                                                           value="${mov.id}"/>
                                                </td>
                                                <td><input name="name" class="form-control" type="text"
                                                           value="${mov.name}"/></td>
                                                <td>
                                                    <input class="form-control" name="smallImageURl" type="text"
                                                           value="${mov.smallImageURl}"/>
                                                </td>
                                                <td>
                                                    <input class="form-control" name="largeImageURL" type="text"
                                                           value="${mov.largeImageURL}"/>
                                                </td>
                                                <td><input name="releaseDate" class="form-control" type="date"
                                                           value="${mov.releaseDate}"/>
                                                </td>
                                                <td><input name="duration" class="form-control" type="number"
                                                           value="${mov.duration}"/>
                                                </td>
                                                <td><input name="categories" class="form-control" type="text"
                                                           value="${mov.categories}"/>
                                                </td>
                                                <td><input name="language" class="form-control" type="text"
                                                           value="${mov.language}"/>
                                                </td>
                                                <td><input name="description" class="form-control" type="text"
                                                           value="${mov.description}"/>
                                                </td>
                                                <td><input name="director" class="form-control" type="text"
                                                           value="${mov.director}"/>
                                                </td>
                                                <td><input name="actors" class="form-control" type="text"
                                                           value="${mov.actors}"/></td>
                                                <c:choose>
                                                    <c:when test="${mov.isShowing == 1 }">
                                                        <td><input class="form-control" value="Availible"/></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td><input class="form-control" value="Unavailible"/></td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td>
                                                    <button type="submit"
                                                            class="btn btn-success">
                                                        UPDATE
                                                    </button>
                                                </td>
                                            </form>
                                            <td><a href="/api/deleteMovie/${mov.id}"
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
