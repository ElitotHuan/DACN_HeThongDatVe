<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link href="../../../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <link href="../../../static/css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
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

    <!--webfont-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
          rel='stylesheet' type='text/css'/>

    <script>
        $(document).ready(function () {
            $('.popup-with-zoom-anim').magnificPopup({
                type: 'inline',
                fixedContentPos: false,
                fixedBgPos: true,
                overflowY: 'auto',
                closeBtnInside: true,
                preloader: false,
                midClick: true,
                removalDelay: 300,
                mainClass: 'my-mfp-zoom-in'
            });
        });
    </script>

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

    <script src="../../../static/js/jquery.magnific-popup.js" type="text/javascript"></script>
    <script type="text/javascript" src="../../../static/js/jquery.flexisel.js"></script>


    <title>Movie Ticket and Service Website | Movie-Info</title>
</head>
<body>
<!-- header-section-starts -->
<div class="full">
    <jsp:include page="menu.jsp"/>
    <div class="main">
        <div class="top-header span_top">
            <jsp:include page="header.jsp"/>
        </div>
        <div class="reviews-section">
            <!--//pop-up-box -->
            <h3 class="head">Movie on the Theater</h3>
            <div class="col-md-9 reviews-grids">
                <div class="review">
                    <div class="movie-pic">
                        <img src="${movie.smallImageURl}" alt=""/>
                    </div>
                    <div class="review-info">
                        <a class="span">${movie.name}</a>
                        <p class="dirctr"><a href="">Reagan Gavin Rasquinha, </a>TNN,
                            Mar 12, 2015, 12.47PM IST</p>
                        <div class="clearfix"></div>
                        
                        <p class="ratingview c-rating">Avg Readers' Rating:</p>
                        <p class="ratingview c-rating">&nbsp; 3.3/5</p>
                        <div class="clearfix"></div>
                        <div class="yrw">
                            <div class="rtm text-center">
                                <form id="ticket-form" action="#" method="GET">
                                    <fieldset>
                                        <div class="form-group input-group">
                                            <input name="mname" style="display:none;"/>
                                            <span class="input-group-btn">
                                                              <c:choose>
                                                                  <c:when test="${movie.isShowing eq 1}">
                                                                          <input class="btn btn-warning" type="submit"
                                                                                 value="BOOK NOW"/>
                                                                  </c:when>
                                                                  <c:otherwise>
                                                                           <input class="btn btn-warning" type="submit" disabled
                                                                                  value="NOT SCREENING"/>
                                                                  </c:otherwise>
                                                              </c:choose>

                                                        </span>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="wt text-center">
                                <a class="play-icon popup-with-zoom-anim btn btn-warning" href="#small-dialog">WATCH
                                    THIS TRAILER</a>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <p class="info"><span>CAST: </span>${movie.actors}</p>
                        <p class="info"><span>DIRECTORS: </span> ${movie.director}</p>
                        <p class="info"><span>GENRE: </span> ${movie.categories}</p>
                        <p class="info"><span>DESCRIPTION: </span> ${movie.description}</p>
                        <p class="info"><span>DURATION: </span> ${movie.duration} minutes</p>
                        <c:choose>
                            <c:when test="${movie.isShowing eq 1}">
                                <p class="info"><span>SHOWING: </span>Availible</p>
                            </c:when>
                            <c:otherwise>
                                <p class="info"><span>SHOWING: </span>Unavailable</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="clearfix"></div>
                </div>

            </div>
            <div class="clearfix"></div>
        </div>
        <div class="review-slider">
            <ul id="flexiselDemo1">
                <li onclick="location.href='movie-detail'"><img src="../../../static/images/r1.jpg" alt=""/></li>
                <li><img src="../../../static/images/r2.jpg" alt=""/></li>
                <li><img src="../../../static/images/r3.jpg" alt=""/></li>
                <li><img src="../../../static/images/r4.jpg" alt=""/></li>
                <li><img src="../../../static/images/r5.jpg" alt=""/></li>
                <li><img src="../../../static/images/r6.jpg" alt=""/></li>
            </ul>
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
</div>
</body>
</html>
