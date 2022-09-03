<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <%@include file="/resources/links.jsp" %>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2>${product.getName()}</h2>
<%@include file="/resources/navbar.jsp" %>
<div class="container-fluid" type="product">
    <div class="row">
        <div class="block1">
            <p>Наименование</p>
            <a>${product.getName()}</a>
            <p>Описание</p>
            <a>${product.getDescription()}</a>
            <p>Цена</p>
            <a>${product.getPrice()}</a>
        </div>
        <div class="card w-25 m-1" type="product">
            <div class="card-body">
                <img class="card-img" style="width:150px;height:120px"
                     src="${contextPath}/images/${product.getImageName()}" alt="Product images">
                <a href="${contextPath}/cart/add?product_id=${product.getId()}&name=${product.getName()}"
                   class="btn btn-primary stretched-link">Добавить в корзину</a>
                <i class="fa-solid fa-cart-arrow-down fa-2x " style="color: black"></i>
            </div>
        </div>
    </div>
</div>
<%@include file="/resources/footer.jsp" %>
</body>
</html>