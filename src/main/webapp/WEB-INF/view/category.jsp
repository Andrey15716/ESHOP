<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@include file="/resources/links.jsp" %>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2>${categoryName}</h2>
<%@include file="/resources/dropdownCategory.jsp" %>
<%@include file="/resources/navbar.jsp" %>
<div class="container-fluid mb-4">
    <c:forEach items="${category.getProductList()}" var="categoryItem">
        <div class="card w-25 m-1" type="categoryItem">
            <div>
                <p><a class="product" href="${contextPath}/product/${categoryItem.getId()}">${categoryItem.getName()}</a></p>
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
</div>
<div class="pages">
    <c:if test="${not empty numberOfPages}">
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:if test="${isFirst}">
                    <li class="page-item disabled">
                        <a class="page-link" tabindex="-1">Previous</a></li>
                </c:if>
                <c:if test="${not isFirst}">
                    <li class="page-item">
                        <a class="page-link" href="${contextPath}/category/${category.getId()}?pageNumber=${pageNumber-1}&pageSize=${pageSize}"
                           tabindex="-1">Previous</a></li>
                </c:if>
                <c:forEach begin="0" end="${numberOfPages-1}" var="pageNumber">
                <li class="page-item">
                    <a class="page-link"
                       href="${contextPath}/category/${category.getId()}?pageNumber=${pageNumber}&pageSize=${pageSize}">${pageNumber+1}</a></li>
                </c:forEach>
                    <li class="page-item active">
                    <a class="page-link" href="${contextPath}/category/${category.getId()}?pageNumber=${pageNumber}&pageSize=${pageSize}">${pageNumber+1}
                            <span class="sr-only">(current)</span></a></li>
                <li class="page-item">
                    <c:if test="${isLast}">
                <li class="page-item disabled"><a class="page-link">Next</a></li>
                </c:if>
                <c:if test="${not isLast}">
                    <li class="page-item">
                        <a class="page-link" href="${contextPath}/category/${category.getId()}?pageNumber=${pageNumber+1}&pageSize=${pageSize}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
</div>
<%@include file="/resources/footer.jsp" %>
</body>
</html>