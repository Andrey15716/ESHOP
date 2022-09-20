<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart Page</title>
    <%@include file="/resources/links.jsp" %>
    <script>
        function orderCompletedMsg() {
            window.confirm("Ваш заказ был оформлен!");
        }

        function clearCartCompletedMsg() {
            window.confirm("Корзина очищена!");
        }

        function decreaseCompletedMsg() {
            window.confirm("Уменьшено");
        }

        function addedCompletedMsg() {
            window.confirm("Продукт добавлен!");
        }

        function deletedCompletedMsg() {
            window.confirm("Продукт удален!");
        }
    </script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2>Корзина</h2>
<%@include file="/resources/navbar.jsp" %>
<div class="container-fluid mb-4">
    <c:if test="${cart.getTotalPrice() ==0}">
        <div class="m-4 flex-center">
            <div class="alert alert-info alert-dismissible d-flex align-items-center fade show w-33">
                <i class="bi-info-circle-fill"></i>
                <strong class="mx-2">Info!</strong> Корзина пустая! Давайте добавим что нибудь :)
            </div>
        </div>
    </c:if>
    <c:if test="${cart.getTotalPrice() >0}">
        <c:forEach items="${cart.getProducts()}" var="product">
            <div class="card w-25 m-1" type="product">
                <div class="card-body">
                    <img class="card-img" style="width:45%;height:45%"
                         src="${contextPath}/images/${product.getImageName()}" alt="Product image">
                    <div class="list-group list-group-flush">
                        <li class="card-title"><b>Name:</b> <a>${product.getName()}</a></li>
                        <li class="card-title"><b>Description:</b> <a>${product.getDescription()}</a></li>
                        <li class="card-title"><b>Price:</b> <a>${product.getPrice()}</a></li>
                    <div class="cart-amount">
                            <a href="${contextPath}/cart/increase?productId=${product.getId()}">
                                <button type="submit" class="btn btn-primary btn-sm" onclick="addedCompletedMsg()">Добавить товар</button>
                                <i class="fa-solid fa-circle-plus fa-2x" style="color: #555555"></i>
                            </a>
                            <a href="${contextPath}/cart/decrease?productId=${product.getId()}">
                                <button type="submit" class="btn btn-primary btn-sm" onclick="decreaseCompletedMsg()">Уменьшить</button>
                                <i class="fa-solid fa-circle-minus fa-2x" style="color: #555555"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>
<c:if test="${not empty cart.getProducts() && cart.getTotalPrice() !=0}">
    <div class="total-sum center">
        <b>Итого : ${cart.getTotalPrice()}</b>
        <a href="${contextPath}/cart/buy">
            <button type="submit" class="btn btn-primary btn-sm" onclick="orderCompletedMsg()">Купить</button>
        </a><br><br>
        <a href="${contextPath}/cart/clear">
            <button type="submit" class="btn btn-primary btn-sm" onclick="clearCartCompletedMsg()">Очистить корзину
            </button>
        </a>
    </div>
</c:if>
</body>
</html>