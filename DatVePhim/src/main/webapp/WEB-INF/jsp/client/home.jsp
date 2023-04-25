<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <link href="../../../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <!-- Custom Theme files -->
    <link href="../../../static/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- Custom Theme files -->
    <script src="../../../static/js/jquery.min.js"></script>
    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Cinema Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

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
    <script type="text/javascript" src="../../../static/js/jquery.flexisel.js"></script>

    <!--webfont-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
          rel='stylesheet' type='text/css'/>

    <title>Movie Ticket and Service Website | Home</title>

</head>
<body>
<!-- header-section-starts -->
<div class="full">
    <jsp:include page="menu.jsp"/>
    <div class="main">
        <div class="header">
            <jsp:include page="header.jsp"/>
            <div class="header-info">
                <h1>BIG HERO 6</h1>
                <p class="age"><a href="#">All Age</a> Don Hall, Chris Williams</p>
                <p class="review">Rating &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: &nbsp;&nbsp; 8,5/10</p>
                <p class="review reviewgo">Genre &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp; Animation, Action,
                    Comedy</p>
                <p class="review">Release &nbsp;&nbsp;&nbsp;&nbsp;: &nbsp;&nbsp; 7 November 2014</p>
                <p class="special">The special bond that develops between plus-sized inflatable robot Baymax, and
                    prodigy Hiro Hamada, who team up with a group of friends to form a band of high-tech heroes.</p>
                <a class="video" href="#"><i class="video1"></i>WATCH TRAILER</a>
                <a class="book" href="ticket"><i class="book1"></i>BOOK NOW</a>
            </div>
        </div>
        <div class="more-reviews">
            <ul id="flexiselDemo2">
                <li onclick="location.href='movie-detail'"><img src="../../../static/images/m1.jpg" alt=""/></li>
                <li><img src="../../../static/images/m2.jpg" alt=""/></li>
                <li><img src="../../../static/images/m3.jpg" alt=""/></li>
                <li><img src="../../../static/images/m4.jpg" alt=""/></li>
            </ul>

        </div>
        <jsp:include page="footer.jsp"/>

    </div>
</div>
<div class="clearfix"></div>
</body>
</html>
