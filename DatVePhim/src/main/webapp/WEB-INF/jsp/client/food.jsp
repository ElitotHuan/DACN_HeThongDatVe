<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Menu FOOD</title>
    <link href="../../../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <!-- Custom Theme files -->
    <link href="../../../static/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- Custom Theme files -->
    <script src="../../../static/js/jquery.min.js"></script>

    <script>
        function increaseCart() {
            var cartBadge = document.getElementById("cart-badge");
            var currentCount = parseInt(cartBadge.innerText);

            cartBadge.innerText = currentCount + 1;
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
                <h1>Menu</h1>
                <div class="menu1">
                    <c:forEach items="${foods}" var="food">
                        <div class="food">
                            <img src="${food.urlImage}" alt="${food.name}">
                            <h2>${food.name}</h2>
                            <p>Price: ${food.price}</p>
                            <div>
                                <button class="plus-button" onclick="increaseCart()">+</button>

                            </div>
                        </div>
                    </c:forEach>
                </div>
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
