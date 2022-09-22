<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <%@include file="/resources/links.jsp" %>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2>Profile</h2>
<%@include file="/resources/navbar.jsp" %>
<%@include file="/resources/dropdownProfile.jsp" %>
<div class="container-fluid">
    <div class=" row">
        <p align="left" style="font-size: 22px">Наши товары</p>
        <div class="card w-25 m-1">
            <div class="card-body">
                <ul class="list-group-list-group-flush">
                    <li class="list-group-item"><b>Имя:</b> <a>${loggedInUser.getName()}</a></li>
                    <li class="list-group-item"><b>Фамилия:</b> <a>${loggedInUser.getSurname()}</a></li>
                    <li class="list-group-item"><b>День рождения:</b> <a>${loggedInUser.getDateBorn()}</a></li>
                    <li class="list-group-item"><b>ID пользователя:</b> <a>${loggedInUser.getId()}</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="container-orders">
    <div class="container-orders-title">
        <p>История заказов</p>
    </div>
    <c:if test="${not empty userOrders}">
        <c:forEach items="${userOrders}" var="userOrder">
            <div class="order">
                <div class="order-info">
                    <p>Дата заказа:<b> ${userOrder.getDate()}</b></p>
                </div>
                <div class="row order-list">
                    <c:forEach items="${userOrder.getProductList()}" var="products">
                        <div class="card-body order-product">
                            <img class="card-img" style="width:30px;height:30px"
                                 src="${contextPath}/images/${products.getImageName()}" alt="Product image"/>
                            <ul class="list-group order-product-info">
                                <li class="list-group-item"><b>Name:</b> <a>${products.getName()}</a></li>
                                <li class="list-group-item"><b>Description:</b> <a>${products.getDescription()}</a></li>
                                <li class="list-group-item"><b>Price:</b> <a>${products.getPrice()}</a></li>
<%--                                <li class="list-group-item"><b>Quantity:</b> <a>${cart.getQuantity()}</a></li>--%>
                            </ul>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
        <div class="pages">
            <c:if test="${not empty numberOfPages}">
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <c:if test="${isFirst}">
                            <li class="page-item disabled">
                                <a class="page-link" tabindex="-1">Previous</a>
                            </li>
                        </c:if>
                        <c:if test="${not isFirst}">
                            <li class="page-item">
                                <a class="page-link"
                                   href="${contextPath}/login/profile?pageNumber=${pageNumber-1}&pageSize=${pageSize}"
                                   tabindex="-1">Previous</a>
                            </li>
                        </c:if>
                        <c:forEach begin="0" end="${numberOfPages-1}" var="pageNumber">
                            <li class="page-item"><a class="page-link active"
                                                     href="${contextPath}/login/profile?pageNumber=${pageNumber}&pageSize=${pageSize}">${pageNumber+1}</a>
                            </li>
                            <span class="sr-only">(current)</span>
                            </span>
                        </c:forEach>
                        <li class="page-item">
                            <c:if test="${isLast}">
                        <li class="page-item disabled">
                            <a class="page-link">Next</a>
                        </li>
                        </c:if>
                        <c:if test="${not isLast}">
                            <li class="page-item">
                                <a class="page-link"
                                   href="${contextPath}/login/profile?pageNumber=${pageNumber+1}&pageSize=${pageSize}">
                                    Next</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </c:if>
        </div>
    </c:if>
</div>
<%@include file="/resources/footer.jsp" %>
</body>
</html>