<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2>${categoryName}</h2>

<%--<div class="auth-info">--%>
<%--    <div class="auth-status">--%>
<%--        <sec:authorize access="isAuthenticated()">--%>
<%--            <i class="fa-solid fa-user-check"></i>--%>
<%--            <a>${pageContext.request.userPrincipal.name}</a>--%>
<%--        </sec:authorize>--%>
<%--        <sec:authorize access="!isAuthenticated()">--%>
<%--            <i class="fa-solid fa-user-xmark"></i>--%>
<%--            <a>unathorised</a>--%>
<%--        </sec:authorize>--%>
<%--    </div>--%>
<%--    <div class="auth-btn">--%>
<%--        <sec:authorize access="!isAuthenticated()">--%>
<%--            <a href="${contextPath}/login">--%>
<%--                <i class="fa-solid fa-right-to-bracket fa-2x"></i>--%>
<%--            </a>--%>
<%--        </sec:authorize>--%>
<%--        <sec:authorize access="isAuthenticated()">--%>
<%--            <a href="${contextPath}/logout">--%>
<%--                <i class="fa-solid fa-arrow-right-from-bracket fa-2x"></i>--%>
<%--            </a>--%>
<%--        </sec:authorize>--%>
<%--    </div>--%>
<%--</div>--%>

<div class="dropdown">
    <select onchange="location=value" id="pageSize" name="pageSize">
        <option>Выбрать количество продуктов на странице</option>
        <option value="${contextPath}/category/${category.getId()}?pageNumber=0&pageSize=1">1</option>
        <option value="${contextPath}/category/${category.getId()}?pageNumber=0&pageSize=2">2</option>
        <option value="${contextPath}/category/${category.getId()}?pageNumber=0&pageSize=3">3</option>
    </select>
    ${pageSize}
</div>

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
<%--                <li class="nav-item"><a class="nav-link" href="${contextPath}/cart">Cart</a></li>--%>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid mb-4">
    <c:forEach items="${category.getProductList()}" var="categoryItem">
    <div class="card w-25 m-1" type="categoryItem">
        <div>
            <p><a class="product" href="${contextPath}/product/${categoryItem.getId()}">${categoryItem.getName()}</a>
            </p>
            <div class="card-body">
                <img class="card-img" style="width:150px;height:120px"
                     src="${contextPath}/images/${categoryItem.getImageName()}" alt="Product image">
                <ul class="list-group">
                    <li class="list-group-item"><b>Описание:</b> <a>${categoryItem.getDescription()}</a></li>
                    <li class="list-group-item"><b>Цена:</b> <a>${categoryItem.getPrice()}</a></li>
                </ul>
            </div>
        </div>
    </div>
    </c:forEach>

    <div class="pages">
        <c:if test="${not empty numberOfPages}">
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <c:if test="${isFirst}">
                        <li class="page-item disabled"><a class="page-link" tabindex="-1">Предыдущая</a></li>
                    </c:if>
                    <c:if test="${not isFirst}">
                        <li class="page-item">
                            <a class="page-link" href="${contextPath}/category/${category.getId()}?pageNumber=${pageNumber-1}&pageSize=${pageSize}" tabindex="-1">Предыдущая</a></li>
                    </c:if>
                    <c:forEach begin="0" end="${numberOfPages-1}" var="pageNumber">
                        <li class="page-item"><a class="page-link active"
                                                 href="${contextPath}/category/${category.getId()}?pageNumber=${pageNumber}&pageSize=${pageSize}">${pageNumber+1}</a>
                        </li><span class="sr-only">(current)</span></span>
                    </c:forEach>
                    <li class="page-item"><c:if test="${isLast}">
                    <li class="page-item disabled"><a class="page-link">Следующая</a></li>
                        </c:if>
                        <c:if test="${not isLast}">
                    <li class="page-item"><a class="page-link" href="${contextPath}/category/${category.getId()}?pageNumber=${pageNumber+1}&pageSize=${pageSize}">Следующая</a>
                    </li>
                    </c:if>
                </ul>
            </nav>
        </c:if>
    </div>
</body>
</html>