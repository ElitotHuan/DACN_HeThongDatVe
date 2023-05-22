<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>

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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <link href="../static/css/ticket.css"
          th:href="@{css/ticket.css}" rel="stylesheet" type="text/css" media="all"/>
    <link href="../static/css/bootstrap.css"
          th:href="@{css/bootstrap.css}" rel='stylesheet' type='text/css'/>
    <link href="../static/css/style.css"
          th:href="@{css/style.css}" rel="stylesheet" type="text/css" media="all"/>
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap&subset=vietnamese" rel="stylesheet">
    <script src="../static/js/jquery.min.js"
            th:src="@{js/jquery.min.js}"></script>
    <script src="../static/js/jquery.seat-charts.js"
            th:src="@{js/jquery.seat-charts.js}"></script>
    <script>
        // Lưu giá trị cart-badge vào sessionStorage
        sessionStorage.setItem('cartBadgeValue', '0');
    </script>
</head>
<body>
<!-- header-section-starts -->
<div class="full">
    <jsp:include page="menu.jsp"/>

    <div class="main">
        <div class="error-content">
            <div class="top-header span_top">
            </div>
            <div class="main-ticket">
                <div class="demo">
                    <div id="seat-map">
                        <div class="front">SCREEN</div>
                    </div>
                    <div class="booking-details">
                        <ul class="book-left">
                            <li>Movie:</li>
                            <li>StartDate:</li>
                            <li>StartTime:</li>
                            <li>Tickets:</li>
                            <li>Total:</li>
                            <li>Seats:</li>
                        </ul>
                        <ul class="book-right">
                            <li th:text="${movie}">${movie}</li>
                            <li th:text="${startDate}">${startdate}</li>
                            <li th:text="${startTime}">${starttime}</li>
                            <li><span id="counter">0</span></li>
                            <li><b><i>$</i><span id="total">0</span></b></li>
                        </ul>

                        <div class="clear"></div>
                        <ul id="selected-seats" class="scrollbar scrollbar1"></ul>
                        <form id="order-form" action="/payment" method="POST">
                            <fieldset>
                                <div class="form-group input-group">
                                    <input name="movie" type="text" value="${movie}" style="display: none">
                                    <input id="count" name="count" type="text" value="*{count}" style="display: none"/>
                                    <input name="price" type="text" value="${price}" style="display: none">
                                    <input id="seating" name="seating" type="text" value="${seating}"
                                           style="display: none">

                                    <input name="startdate" type="text" value="${startdate}" style="display: none">
                                    <input name="starttime" type="text" value="${starttime}" style="display: none">
                                    <input name="username" type="text" value="${sessionScope.loggedInUser.username}"
                                           style="display: none">
                                    <span class="input-group-btn">
                    <button class="btn btn-warning" id="book-now-btn" type="submit">Book Now!</button>
                </span>
                                </div>
                            </fieldset>
                        </form>
                        <script>
                            $(document).ready(function() {
                                $('#book-now-btn').click(function() {
                                    // Lấy giá trị hiện tại của cart-badge từ sessionStorage
                                    var currentCount = parseInt(sessionStorage.getItem('cartBadgeValue'));

                                    // Cộng thêm 1 vào giá trị hiện tại
                                    var updatedCount = currentCount + 1;

                                    // Cập nhật giá trị mới vào sessionStorage
                                    sessionStorage.setItem('cartBadgeValue', updatedCount.toString());

                                });
                            });
                        </script>


                        <div id="legend"></div>
                    </div>
                    <div style="clear:both"></div>
                </div>

                <script type="text/javascript">
                    var seating = [];
                    var price = parseFloat('${price}');
                    $(document).ready(function () {
                        var $cart = $('#selected-seats'), //Sitting Area
                            $counter = $('#counter'), //Votes
                            $total = $('#total'), //Total money
                            $count = $('#count'),
                            $seating = $('#seating');

                        var sc = $('#seat-map').seatCharts({
                            map: [  //Seating chart
                                'aaaaaaaaaaaaa',
                                'aaaaaaaaaaaaa',
                                'aaaaaaaaaaaaa',
                                'aaaaaaaaaaaaa',
                                'aaaaaaaaaaaaa',
                                'aaaaaaaaaaaaa',
                                'aaaaaaaaaaaaa',
                                'aaaaaaaaaaaaa',
                                'aaaaaaaaaaaaa',
                                'aaaaaaaaaaaaa'
                            ],
                            naming: {
                                top: false,
                                getLabel: function (character, row, column) {
                                    return column;
                                }
                            },
                            legend: { //Definition legend
                                node: $('#legend'),
                                items: [
                                    ['a', 'available', 'Available'],
                                    ['a', 'unavailable', 'Sold'],
                                    ['a', 'selected', 'Selected']
                                ]
                            },
                            click: function () { //Click event
                                if (this.status() == 'available') { //optional seat
                                    var seatInfo = (this.settings.row + 1) + '_' + this.settings.label; // Tạo chuỗi dạng "row_seat"
                                    $('<li>[Row' + (this.settings.row + 1) + ' Seat' + this.settings.label + '] ' + '</li>')
                                        .attr('id', 'cart-item-' + this.settings.id)
                                        .data('seatId', this.settings.id)
                                        .appendTo($cart);

                                    $counter.text(sc.find('selected').length + 1);
                                    $total.text(recalculateTotal(sc, price));
                                    $count.val($counter.text());
                                    seating.push(seatInfo); // Thêm thông tin ghế đã chọn vào mảng seating
                                    $seating.val(seating.join(',')); // Cập nhật giá trị của input seating


                                    return 'selected';
                                } else if (this.status() == 'selected') { //Checked
                                    //Update Number
                                    $counter.text(sc.find('selected').length - 1);
                                    //update totalnum
                                    $total.text($counter.text() * price);
                                    $count.val($counter.text())

                                    var seatId = this.settings.id;
                                    var seatInfo = (this.settings.row + 1) + '_' + this.settings.label; // Tạo chuỗi dạng "row_seat"
                                    seating = seating.filter(function (seat) {
                                        return seat !== seatInfo; // Xóa ghế đã bỏ chọn khỏi mảng seating
                                    });


                                    //Delete reservation
                                    $('#cart-item-' + this.settings.id).remove();
                                    $seating.val(seating.join(','));
                                    //optional
                                    return 'available';
                                } else if (this.status() == 'unavailable') { //sold
                                    return 'unavailable';
                                } else {
                                    return this.style();
                                }
                            }
                        });
                        //sold seat
                        //sc.get(['1_2', '4_4', '4_5', '6_6', '6_7', '8_5', '8_6', '8_7', '8_8', '10_1', '10_2']).status('unavailable');
                        var seatingList = [
                            <c:forEach var="seating" items="${seatingList}">
                            '${seating}',
                            </c:forEach>
                        ];

                        // Đặt trạng thái 'unavailable' cho các seating trong danh sách
                        sc.get(seatingList).status('unavailable');

                    });

                    //sum total money
                    function recalculateTotal(sc, price) {
                        var total = price || 0;
                        sc.find('selected').each(function () {
                            total += price || 0;
                        });

                        return total;
                    }
                </script>
            </div>
            <div class="footer">
                <!--/*/ <th:block th:include="fragments/utils :: footer"></th:block> /*/-->
            </div>
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