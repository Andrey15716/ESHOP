<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css">
    <script>
        function orderCompletedMsg() {
            window.confirm("Ваш заказ был оформлен!");
        }

        function productDeletedMsg() {
            window.confirm("Товар был удален из корзины")
        }

        function productIncreaseMsg() {
            window.confirm("Товар был добавлен!")
        }

        function productDecreaseMsg() {
            window.confirm("Количество товара стало на 1 единицу меньше!")
        }
    </script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2>Корзина</h2>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="${contextPath}/home">Online Shop</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="${contextPath}/login/profile">Profile</a></li>
                <li class="nav-item"><a class="nav-link" href="${contextPath}/search">Search</a></li>
                <li class="nav-item"><a class="nav-link" href="${contextPath}/home">Home</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid mb-4">
    <c:forEach items="${sessionScope.cart.getProducts()}" var="product">
        <div class="card w-25 m-1" type="product">
            <div class="card-body">
                <img class="card-img" style="width:45%;height:45%"
                     src="${contextPath}/images/${product.getImageName()}" alt="Product image">
                <div class="list-group list-group-flush">
                    <li class="card-title"><b>Name:</b> <a>${product.getName()}</a></li>
                    <li class="card-title"><b>Description:</b> <a>${product.getDescription()}</a></li>
                    <li class="card-title"><b>Price:</b> <a>${product.getPrice()}</a></li>


                        <%--                    <a href="${contextPath}/cart/delete?productId=${product.getId()}">--%>
                        <%--                        <button class="buttonDelete" onclick="productDeletedMsg()" type="submit"><i--%>
                        <%--                                class="fa fa-minus-square fa-lg" style="padding-right: 5px"></i>Удалить--%>
                        <%--                        </button>--%>
                        <%--                    </a>--%>
                        <%--                    <a href="${contextPath}/cart/increase?productId=${product.getId()}">--%>
                        <%--                        <button class="buttonIncrease" onclick="productIncreaseMsg()" type="submit"><i--%>
                        <%--                                class="fa fa-minus-square fa-lg" style="padding-right: 5px"></i>Добавить--%>
                        <%--                        </button>--%>
                        <%--                    </a>--%>
                        <%--                    <a href="${contextPath}/cart/decrease?productId=${product.getId()}">--%>
                        <%--                        <button class="buttonDecrease" onclick="productDecreaseMsg()" type="submit"><i--%>
                        <%--                                class="fa fa-minus-square fa-lg" style="padding-right: 5px"></i>Уменьшить--%>
                        <%--                        </button>--%>
                        <%--                    </a>--%>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<c:if test="${empty cart.getProducts()}">
    <h3><p class="text-center">В корзине нет товаров</p></h3>
</c:if>
<c:if test="${not empty cart.getProducts()}">
    <div class="total-sum center">
        <b>Итого : ${cart.getTotalPrice()}</b>
        <a href="${contextPath}/cart/buy">
            <button type="submit" class="btn btn-primary btn-sm" onclick="orderCompletedMsg()">Купить
            </button>
        </a>
    </div>
</c:if>
</body>
</html>