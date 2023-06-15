<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <title>Movie Ticket and Service Website | Ticket</title>

    <!-- for-mobile-apps -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords"
          content="Movie Ticket Booking Widget Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
    <!-- //for-mobile-apps -->
    <link href='//fonts.googleapis.com/css?family=Kotta+One' rel='stylesheet' type='text/css'/>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
          rel='stylesheet' type='text/css'/>

    <link href="../../../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <!-- Custom Theme files -->
    <link href="../../../static/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="../../../static/css/ticket.css" rel="stylesheet" type="text/css" media="all"/>

    <script src="../../../static/js/jquery.min.js"></script>
    <script src="../../../static/js/jquery.seat-charts.js"></script>

</head>
<body>
<!-- header-section-starts -->
<div class="full">
    <jsp:include page="menu.jsp"/>
    <div class="main" style=" background: url(../../../static/images/ticket_banner.png) no-repeat center;">
        <div class="error-content">
            <jsp:include page="header.jsp"/>
            <div class="main_ticket">
                <h2>Thông tin vé</h2>
                <div>
                    <p>Bạn đã đặt vé thành công.</p>
                    <div class="ticket-details">
                        <ul class="ticket-left">
                            <li>Tên khách hàng:</li>
                            <li>Tên Phim:</li>
                            <li>Ngày chiếu:</li>
                            <li>Thời gian chiếu:</li>
                            <li>Rạp:</li>
                            <li>Phòng:</li>
                            <li>Tổng tiền:</li>
                            <li>Vị trí ghế:</li>
                        </ul>
                        <ul class="ticket-right">
                            <li>${ticketInfo.username}</li>
                            <li>${ticketInfo.movieName}</li>
                            <li>${ticketInfo.startDate}</li>
                            <li>${ticketInfo.startTime}</li>
                            <li>${ticketInfo.branchName}</li>
                            <li>${ticketInfo.room}</li>
                            <li>${ticketInfo.total}</li>
                            <li>${ticketInfo.seating}</li>
                        </ul>
                        <div class="clear"></div>
                    </div>
                </div>

            </div>
            <jsp:include page="footer.jsp"/>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<script src="../static/js/jquery.nicescroll.js"
        th:src="@{js/jquery.nicescroll.js}"></script>
<script src="../static/js/scripts.js"
        th:src="@{js/scripts.js}"></script>

</body>
</html>