<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Menu FOOD</title>
    <style>
        /* Thiết lập kiểu dáng cho các phần tử */
        .menu {
            display: flex;
            flex-wrap: wrap;
        }
        .food {
            width: 300px;
            margin: 10px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 0 5px #ddd;
        }
        .food img {
            width: 100%;
            height: 200px;
            object-fit: cover;
            margin-bottom: 10px;
        }
        .food h2 {
            font-size: 1.5rem;
            margin-bottom: 10px;
        }
        .food p {
            margin: 0;
        }
    </style>
</head>
<body>

<div class="full">
    <jsp:include page="menu.jsp"/>
    <div class="main">
        <div class="contact-content">
            <jsp:include page="header.jsp"/>
            <h1>Menu</h1>
            <div class="menu">
                <c:forEach items="${foods}" var="food">
                    <div class="food">
                        <img src="${food.urlImage}" alt="${food.name}">
                        <h2>${food.name}</h2>
                        <p>Price: ${food.price}</p>
                        <div>
                            <button class="add-to-cart" onclick="addToCart(${food.id})">+</button>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="footer">
            <jsp:include page="footer.jsp"/>
        </div>
    </div>
</body>
</html>