<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Log in Success</title>
    <%@include file="/resources/links.jsp" %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css">
</head>
<body>
<%@include file="/resources/registrationNavbar.jsp" %>
<div class="m-4 flex-center">
    <div class="alert alert-info alert-dismissible d-flex align-items-center fade show w-33">
        <i class="bi-info-circle-fill"></i>
        <strong class="mx-2">Info!</strong>Добро пожаловать в интернет магазин!
        Чтобы начать покупки войдите в свой аккаунт
        <a href="${contextPath}/login">
            <button class="btn" style="width: 60px; color:aquamarine;" id="registerRedirect" type="submit">
                Login
            </button>
        </a>
    </div>
</div>
</body>
</html>