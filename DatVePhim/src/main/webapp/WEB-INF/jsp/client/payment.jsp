<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            transition: all 0.3s ease;}
    </style>
</head>
<body>

<div class="full">
    <div class="main">
        <jsp:include page="menu.jsp"/>
        <div class="contact-content">
            <jsp:include page="header.jsp"/>
            <div class="main-contact">

                <div class="invoice">
                    <h1>Trang thanh toán</h1>
                    <table class="table">
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
                            <th>Giá vé</th>
                            <td>${price}</td>
                        </tr>
                        <tr>
                            <th>Tổng cộng</th>
                            <td>${price*count}</td>
                        </tr>
                    </table>
                </div>

                <h2>Thông tin thanh toán</h2>
                <form id="paymentForm" action="/payment/process" method="post">
                    <!-- Các trường nhập thông tin thanh toán -->
                    <!-- Ví dụ: -->
                    <label for="cardNumber">Số thẻ:</label>
                    <input type="text" id="cardNumber" name="cardNumber" required>

                    <label for="expirationDate">Ngày hết hạn:</label>
                    <input type="text" id="expirationDate" name="expirationDate" required>

                    <label for="cvv">CVV:</label>
                    <input type="text" id="cvv" name="cvv" required>
                    <button class="book-now" type="submit"><i class="book1"></i>Thanh Toán</button>
                </form>

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
                <script>
                    $(document).ready(function() {
                        $('#paymentForm').submit(function(event) {
                            event.preventDefault(); // Ngăn chặn submit form mặc định

                            // Thực hiện các xử lý trước khi thanh toán (nếu cần)

                            // Gửi form
                            this.submit();
                        });
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
