<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Schedule</title>
    <link href="../../../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <!-- Custom Theme files -->
    <link href="../../../static/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- Custom Theme files -->
    <script src="../../../static/js/jquery.min.js"></script>
    function hideURLbar() {
    window.scrollTo(0, 1);
    } </script>

    <script type="text/javascript">
        $(window).load(function () {
            $("#flexiselDemo1").flexisel({
                visibleItems: 6,
                animationSpeed: 1000,
                autoPlay: true,
                autoPlaySpeed: 3000,
                pauseOnHover: false,
                enableResponsiveBreakpoints: true,
                responsiveBreakpoints: {
                    portrait: {
                        changePoint: 480,
                        visibleItems: 2
                    },
                    landscape: {
                        changePoint: 640,
                        visibleItems: 3
                    },
                    tablet: {
                        changePoint: 768,
                        visibleItems: 4
                    }
                }
            });
        });
    </script>

    <script type="text/javascript">
        $(window).load(function () {

            $("#flexiselDemo2").flexisel({
                visibleItems: 4,
                animationSpeed: 1000,
                autoPlay: true,
                autoPlaySpeed: 3000,
                pauseOnHover: false,
                enableResponsiveBreakpoints: true,
                responsiveBreakpoints: {
                    portrait: {
                        changePoint: 480,
                        visibleItems: 2
                    },
                    landscape: {
                        changePoint: 640,
                        visibleItems: 3
                    },
                    tablet: {
                        changePoint: 768,
                        visibleItems: 3
                    }
                }
            });
        });
    </script>
<style>.full {
    background-color: #f5f5f5;
    height: 100%;
}


thead {
    background-color: #f2f2f2;
}
table {
    border-collapse: collapse;
    width: 100%;
    font-family: Arial, sans-serif;
}

th, td {
    text-align: left;
    border: 1px solid #ddd;
    padding: 8px;
    color: #333;
    font-size: 16px;
}

th {
    background-color: #8ac1f6;
    color: #fff;
    font-weight: bold;
}



tr:nth-child(even) {
    background-color: #f2f2f2;
}

tr:hover {
    background-color: #ddd;
}

.main-contact {
    margin-top: 20px;
}

h1 {
    font-size: 36px;
    margin-bottom: 20px;
}

</style>
</head>
<body>

<div class="full">
    <div class="main">
        <jsp:include page="menu.jsp"/>
        <div class="contact-content">
            <jsp:include page="header.jsp"/>
            <div class="main-contact">

                <h1>Schedule</h1>
                <table>
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Movie</th>
                        <th>Theater</th>
                        <th>Option</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="schedule" items="${schedules}">
                        <tr>
                            <td>${schedule.startDate}</td>
                            <td>${schedule.startTime}</td>
                            <td>${schedule.movie.name}</td>
                            <td>${schedule.branch.name}</td>
                            <td > <a class="book" href="ticket"><i class="book1"></i>BOOK</a></td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
        <div class="more-reviews">
            <ul id="flexiselDemo2">
                <c:forEach var="movie" begin="0" end="5" items="${movies}">
                    <li onclick="location.href='movie-detail/${movie.id}'"><img src="${movie.largeImageURL}" alt=""/>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <%@ include file="footer.jsp" %>
    </div>


</div>
    <div class="clearfix"></div>
</div>

</body>

</html>
