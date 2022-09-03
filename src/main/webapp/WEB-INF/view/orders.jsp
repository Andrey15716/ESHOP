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
<div>
    <b> Ваш заказ был успешно оформлен! </b>
</div>
<%@include file="/resources/footer.jsp" %>
</body>
</html>