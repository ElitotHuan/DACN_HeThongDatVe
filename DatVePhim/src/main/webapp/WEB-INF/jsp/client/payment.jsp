<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap&subset=vietnamese" rel="stylesheet">
    <link href="../../../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <!-- Custom Theme files -->
    <link href="../../../static/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- Custom Theme files -->
    <script src="../../../static/js/jquery.min.js"></script>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
        }

        .main-contact {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 5px;
        }

        h1, h2 {
            color: #333;
        }

        label {
            font-weight: bold;
        }

        input[type="text"] {
            width: 100%;
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        .book-now {
            background-position: center;
            background-size: cover;
            color: white;
            background-color: #bb7b29;
            font-family: Arial, sans-serif;
            font-size: 16px;
            text-transform: uppercase;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: all 0.3s ease;
        }
    </style>

    <script>
        function addToCart(foodId, username) {

            // Gửi request AJAX đến endpoint để thêm food vào giỏ hàng với thông tin username
            var cartBadge = document.getElementById("cart-badge");
            var currentCount = parseInt(cartBadge.innerText);

            cartBadge.innerText = currentCount + 1;
            $.ajax({
                type: 'POST',
                url: '/addToCart',
                data: {
                    foodId: foodId,
                    username: username
                },
                success: function (response) {
                    // Xử lý phản hồi từ server (nếu cần)
                    window.location.reload();
                },
                error: function (xhr, status, error) {
                    // Xử lý lỗi (nếu cần)
                }
            });
        }

        function removeFromCart(foodId, username) {
            // Gửi request AJAX đến endpoint để thêm food vào giỏ hàng với thông tin username
            var cartBadge = document.getElementById("cart-badge");
            var currentCount = parseInt(cartBadge.innerText);

            cartBadge.innerText = currentCount - 1;
            $.ajax({
                type: 'POST',
                url: '/removeFromCart',
                data: {
                    foodId: foodId,
                    username: username
                },
                success: function (response) {
                    // Xử lý phản hồi từ server (nếu cần)
                    window.location.reload();
                },
                error: function (xhr, status, error) {
                    // Xử lý lỗi (nếu cần)
                }
            });
        }
    </script>

</head>


<body>

<div class="full">
    <div class="main">
        <jsp:include page="menu.jsp"/>
        <div class="contact-content">
            <jsp:include page="header.jsp"/>
            <div class="main-contact">

                <div class="invoice">
                    <h1>THÔNG TIN VÉ</h1>
                    <form action="http://localhost:8080/pay" method="post" id="payment" onsubmit="return false">
                        <table class="table">
                            <tr>
                                <th>ID USER</th>
                                <td>${sessionScope.loggedInUser.username}</td>
                            </tr>
                            <tr>
                                <th>Tên phim</th>
                                <td>${movie}</td>
                            </tr>
                            <tr>
                                <th>Ngày chiếu</th>
                                <td>${startdate}</td>
                            </tr>
                            <tr>
                                <th>Thời gian chiếu</th>
                                <td>${starttime}</td>
                            </tr>
                            <tr>
                                <th>Chỗ ngồi</th>
                                <td>${seating}</td>
                            </tr>
                            <tr>
                                <th>Số vé</th>
                                <td>${count}</td>
                            </tr>
                            <tr>
                                <th>Chi nhánh</th>
                                <td>${branch}</td>
                            </tr>
                            <tr>
                                <th>Phòng</th>
                                <td>${room}</td>
                            </tr>
                            <tr>
                                <th>Giá vé</th>
                                <td>${price}</td>
                            </tr>
                            <tr>
                                <th>Tổng cộng</th>
                                <td>${price*count}</td>
                            </tr>
                        </table>
                        <h2>THÔNG TIN ĐỒ ĂN:</h2>
                        <table class="table">
                            <tr>
                                <th>Tên sản phẩm</th>
                                <th>Số lượng</th>
                                <th>Giá</th>
                            </tr>
                            <c:forEach var="item" items="${cartItems}">
                                <tr>
                                     <td>${item.food.name}</td>
                                    <td>
                                        <button class="plus-button"
                                                onclick="removeFromCart(${item.food.id}, '${sessionScope.loggedInUser.username}')">
                                            -
                                        </button>
                                            ${item.count}
                                        <button class="plus-button" type="button"
                                                onclick="addToCart(${item.food.id}, '${sessionScope.loggedInUser.username}')">
                                            +
                                        </button>
                                    </td>
                                    <td>${item.food.price}</td>
                                </tr>
                                <c:set var="totalFoodPrice" value="0"/>
                                <c:forEach var="item" items="${cartItems}">
                                    <c:set var="totalFoodPrice"
                                           value="${totalFoodPrice + (item.food.price * item.count)}"/>
                                </c:forEach>
                            </c:forEach>
                        </table>

                        <h2>TỔNG TIỀN PHẢI THANH TOÁN: ${price * count + totalFoodPrice} VND</h2>

                        <button class="book-now" type="submit"><i class="book1"></i>Thanh toán</button>
                    </form>
                </div>


                <script>
                    $(document).on('submit', '#payment', function (event) {
                        let isLoggedIn = ${sessionScope.loggedInUser != null}; // Kiểm tra session loggedInUser

                        if (isLoggedIn) {
                            const frm = $('#payment');
                            const ticketData = {
                                scheduleId:'${id}',
                                seating: '${seating}',
                                total: ${price * count + totalFoodPrice},
                                username: '${sessionScope.loggedInUser.username}'
                            };

                            console.log(JSON.stringify(ticketData));


                            $.ajax({
                                contentType: "application/json;charset=UTF-8",
                                type: frm.attr('method'),
                                url: frm.attr('action'),
                                data: JSON.stringify(ticketData),
                                success: function (response, data) {
                                    if (data == 'success') {
                                        window.location.href = response
                                    }
                                }
                            });

                        } else {
                            window.location.href = 'login';
                        }

                    });
                </script>
            </div>
        </div>
        <div class="footer">
            <jsp:include page="footer.jsp"/>
        </div>
    </div>
    <div class="clearfix"></div>
</div>
</body>
</html>
