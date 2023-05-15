<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <!-- for-mobile-apps -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Movie Ticket Booking Widget Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
    <!-- //for-mobile-apps -->
    <link href='//fonts.googleapis.com/css?family=Kotta+One' rel='stylesheet' type='text/css'/>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'/>

    <link href="../static/css/ticket.css"
          th:href="@{css/ticket.css}" rel="stylesheet" type="text/css" media="all" />
    <link href="../static/css/bootstrap.css"
          th:href="@{css/bootstrap.css}" rel='stylesheet' type='text/css' />
    <link href="../static/css/style.css"
          th:href="@{css/style.css}" rel="stylesheet" type="text/css" media="all" />

    <script src="../static/js/jquery.min.js"
            th:src="@{js/jquery.min.js}"></script>
    <script src="../static/js/jquery.seat-charts.js"
            th:src="@{js/jquery.seat-charts.js}"></script>

</head>
<body>
<!-- header-section-starts -->
<div class="full">
    <jsp:include page="menu.jsp"/>
    <div class="menu">

    </div>
    <div class="main" style=" background: url(../images/ticket_banner.jpg) no-repeat center;">
        <div class="error-content">
            <div class="top-header span_top">
                <!--/*/ <th:block th:include="fragments/utils :: header"></th:block> /*/-->
            </div>
            <div class="main_ticket">
                <h2>Multiplex Theatre Showing Screen 1</h2>
                <div class="demo">
                    <div id="seat-map">
                        <div class="front">SCREEN</div>
                    </div>
                    <div class="booking-details">
                        <ul class="book-left">
                            <li>Movie: </li>
                            <li>Time: </li>
                            <li>Tickets: </li>
                            <li>Total: </li>
                            <li>Seats: </li>
                        </ul>
                        <ul class="book-right">
                            <li th:text="${mname}">Gingerclown</li>
                            <li th:text="${time}"></li>
                            <li><span id="counter">0</span></li>
                            <li><b><i>$</i><span id="total">0</span></b></li>
                            <li id="price" th:text="${price}" style="display: none"></li>
                        </ul>
                        <div class="clear"></div>
                        <ul id="selected-seats" class="scrollbar scrollbar1"></ul>
                        <form id="order-form" th:action="@{/ticket_success}" method="POST" >
                            <fieldset>
                                <div class="form-group input-group" th:object="${order}">
                                    <input name="mname" type="text" th:value="${mname}" style="display: none">
                                    <input id="count" name="count" type="text" th:value="*{count}" style="display: none"/>
                                    <input name="price" type="text" th:value="${price}" style="display: none">
                                    <input id="seating" name="seating" type="text" th:value="*{seating}" style="display: none">
                                    <input name="time" type="text" th:value="${time}" style="display: none">
                                    <span class="input-group-btn">
                                        <input class="btn btn-warning" type="submit" value="Book Now!" />
                                    </span>
                                </div>
                            </fieldset>
                        </form>
                        <div id="legend"></div>
                    </div>
                    <div style="clear:both"></div>
                </div>

                <script type="text/javascript">
                    var price = parseFloat($('#price').text());
                    $(document).ready(function() {
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
                            naming : {
                                top : false,
                                getLabel : function (character, row, column) {
                                    return column;
                                }
                            },
                            legend : { //Definition legend
                                node : $('#legend'),
                                items : [
                                    [ 'a', 'available',   'Available' ],
                                    [ 'a', 'unavailable', 'Sold'],
                                    [ 'a', 'selected', 'Selected']
                                ]
                            },
                            click: function () { //Click event
                                if (this.status() == 'available') { //optional seat
                                    $('<li>Row'+(this.settings.row+1)+' Seat'+this.settings.label+'</li>')
                                        .attr('id', 'cart-item-'+this.settings.id)
                                        .data('seatId', this.settings.id)
                                        .appendTo($cart);

                                    $counter.text(sc.find('selected').length+1);
                                    $total.text(recalculateTotal(sc)+price);
                                    $count.val($counter.text());
                                    $cart.each(function(){
                                        var s = $(this).children().text();
                                        var ss = $seating.text();
                                        $seating.val(ss + s);
                                    });

                                    return 'selected';
                                } else if (this.status() == 'selected') { //Checked
                                    //Update Number
                                    $counter.text(sc.find('selected').length-1);
                                    //update totalnum
                                    $total.text(recalculateTotal(sc)-price);
                                    $count.val($counter.text())

                                    //Delete reservation
                                    $('#cart-item-'+this.settings.id).remove();
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
                        sc.get(['1_2', '4_4','4_5','6_6','6_7','8_5','8_6','8_7','8_8', '10_1', '10_2']).status('unavailable');

                    });
                    //sum total money
                    function recalculateTotal(sc) {
                        var total = 0;
                        sc.find('selected').each(function () {
                            total += price;
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