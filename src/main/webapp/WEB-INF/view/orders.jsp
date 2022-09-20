<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order page</title>
    <%@include file="/resources/links.jsp" %>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2>Order</h2>
<%@include file="/resources/navbar.jsp" %>
<div class="m-4 flex-center">
    <div class="alert alert-info alert-dismissible d-flex align-items-center fade show w-33">
        <i class="bi-info-circle-fill"></i>
        <strong class="mx-2">Info!</strong> Ваш заказ был успешно оформлен!
    </div>
</div>
<%@include file="/resources/footer.jsp" %>
</body>
</html>