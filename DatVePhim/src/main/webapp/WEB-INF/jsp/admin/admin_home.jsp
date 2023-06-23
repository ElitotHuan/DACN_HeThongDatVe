<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 20/04/2023
  Time: 11:36 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../../../static/css/admin/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../../../static/css/admin/admin.css" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">


    <!-- My JavaScript -->
    <script src="<%=request.getContextPath()%>/static/js/admin.js"></script>
    <!-- /My JavaScript -->


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/static/js/bootstrap.min.js"></script>

    <title>Dashboard :: Admin </title>
</head>
<body onload="startTime()">

<div class="admin-dashboard">
    <div class="container-fluid">
        <div class="admin-rg">
            <div class="row">
<%--                <div class="col-md-4">--%>
<%--                    &lt;%&ndash;          <sec:authentication var="user" property="principal" />&ndash;%&gt;--%>
<%--                    &lt;%&ndara sh;          <%@ include file ="admin-profile.jsp" %>&ndash;%&gt;--%>
<%--                </div>--%>
                <div class="col-md-8 admin-box">
                    <c:if test="${sessionScope != null}">
                        <span>Hello, <strong>${username}</strong>!</span>
                        <a href="/admin/logout">Đăng xuất</a>
                    </c:if>
                    <c:if test="${sessionScope == null}">
                        <h2>Bạn không có quyền</h2>
                    </c:if>
                    <h2>${successMsg}</h2>
                    <div class="row">
                        <div class="col-md-6">
                            <a href="/api/manage_user">
                                <div class="option-box">
                                    <div class="option-box-img">
                                        <img src="<%=request.getContextPath()%>/static/images/admin/manage-user.png"
                                             width="100%">
                                    </div>
                                    <h5>MANAGE USERS</h5>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-6">
                            <a href="/api/manage_movie">
                                <div class="option-box">
                                    <div class="option-box-img">
                                        <img src="<%=request.getContextPath()%>/static/images/admin/manage-movies.png"
                                             width="100%">
                                    </div>
                                    <h5>MANAGE MOVIES</h5>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <a href="/api/manage_food">
                                <div class="option-box">
                                    <div class="option-box-img">
                                        <img src="<%=request.getContextPath()%>/static/images/admin/manage-combos.png"
                                             width="100%">
                                    </div>
                                    <h5>MANAGE COMBOS</h5>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-6">
                            <a href="/api/manage_schedule">
                                <div class="option-box">
                                    <div class="option-box-img">
                                        <img src="<%=request.getContextPath()%>/static/images/admin/manage-schedule.png"
                                             width="100%">
                                    </div>
                                    <h5>MANAGE SCHEDULES</h5>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-6">
                            <a href="/api/manage_room">
                                <div class="option-box">
                                    <div class="option-box-img">
                                        <img src="<%=request.getContextPath()%>/static/images/admin/manage-cinemas.png"
                                             width="100%">
                                    </div>
                                    <h5>MANAGE ROOM</h5>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-6">
                            <a href="/api/manage_branch">
                                <div class="option-box">
                                    <div class="option-box-img">
                                        <img src="<%=request.getContextPath()%>/static/images/admin/manage-branch.png"
                                             width="100%">
                                    </div>
                                    <h5>MANAGE BRANCH</h5>
                                </div>
                            </a>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <a href="/api/manage_ticket">
                                <div class="option-box">
                                    <div class="option-box-img">
                                        <img src="<%=request.getContextPath()%>/static/images/admin/manage-ticket.png"
                                             width="100%">
                                    </div>
                                    <h5>MANAGE TICKET</h5>
                                </div>
                            </a>
                        </div>

                        <div class="col-md-6">
                            <a href="/api/manage_satistics">
                                <div class="option-box">
                                    <div class="option-box-img">
                                        <img src="<%=request.getContextPath()%>/static/images/admin/statistics.png"
                                             width="100%">
                                    </div>
                                    <h5>STATISTICS</h5>
                                </div>
                            </a>
                        </div>

                        <div class="col-md-6">
                            <a href="/api/manage_employee">
                                <div class="option-box">
                                    <div class="option-box-img">
                                        <img src="<%=request.getContextPath()%>/static\images\admin\manage_employee.png"
                                             width="100%">

                                    </div>
                                    <h5>MANAGE EMPLOYEE</h5>
                                </div>
                            </a>
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
