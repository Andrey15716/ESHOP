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
    </script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2>Корзина</h2>
<%@include file="/resources/navbar.jsp" %>

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