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
            transition: all 0.3s ease;
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

                <div class="invoice">
                    <h1>Giỏ hàng</h1>
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
                        <button class="book-now" type="submit"><i class="book1"></i>Thanh toán</button>
                    </form>
                </div>


                <script>

                    $(document).on('submit', '#payment', function (event) {
                        let isLoggedIn = ${sessionScope.loggedInUser != null}; // Kiểm tra session loggedInUser

                        if (isLoggedIn) {
                            const frm = $('#payment');
                            const ticketData = {
                                movieName: '${movie}',
                                startDate: '${startdate}',
                                startTime: '${starttime}',
                                branchName: '${branch}',
                                room: '${room}',
                                seating: '${seating}',
                                total: ${price * count},
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

                    <%--document.getElementById('paymentButton').addEventListener('click', function() {--%>
                    <%--    // Kiểm tra trạng thái đăng nhập--%>
                    <%--    var isLoggedIn = ${sessionScope.loggedInUser != null}; // Kiểm tra session loggedInUser--%>

                    <%--    if (isLoggedIn) {--%>
                    <%--        // Lấy dữ liệu vé từ các biến và tạo một object chứa thông tin vé--%>
                    <%--        var ticketData = {--%>
                    <%--            movieName: '${movie}',--%>
                    <%--            startDate: '${startdate}',--%>
                    <%--            startTime: '${starttime}',--%>
                    <%--            branchName: '${branch}',--%>
                    <%--            room: '${room}',--%>
                    <%--            seating: '${seating}',--%>
                    <%--            total: ${price * count},--%>
                    <%--            username: '${sessionScope.loggedInUser.username}'--%>
                    <%--        };--%>

                    <%--        // Gửi dữ liệu vé qua Ajax để lưu vào cơ sở dữ liệu--%>
                    <%--        var xhr = new XMLHttpRequest();--%>
                    <%--        xhr.open('POST', '/saveTicket', true);--%>
                    <%--        xhr.setRequestHeader('Content-Type', 'application/json');--%>
                    <%--        xhr.onreadystatechange = function() {--%>
                    <%--            if (xhr.readyState === 4 && xhr.status === 200) {--%>
                    <%--                // Xử lý kết quả sau khi lưu thành công--%>
                    <%--                alert('Thanh toán thành công!');--%>
                    <%--                // Chuyển hướng hoặc thực hiện các thao tác khác sau khi thanh toán--%>
                    <%--            } else if (xhr.readyState === 4 && xhr.status !== 200) {--%>
                    <%--                // Xử lý kết quả sau khi lưu thất bại--%>
                    <%--                alert('Thanh toán thất bại. Vui lòng thử lại sau!');--%>
                    <%--                // Xử lý lỗi hoặc hiển thị thông báo lỗi--%>
                    <%--            }--%>
                    <%--        };--%>
                    <%--        xhr.send(JSON.stringify(ticketData));--%>
                    <%--    } else {--%>
                    <%--        // Người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập--%>
                    <%--        window.location.href = 'login';--%>
                    <%--    }--%>
                    <%--});--%>
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
