<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>LoginErrorPage</title>
    <%@include file="/resources/links.jsp" %>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,900" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/error_style.css">
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div id="notfound">
    <div class="notfound">
        <div class="notfound-404">
            <h1>Oops!</h1>
        </div>
        <h2>${error}</h2>
        <p>User was not found</p>
        <a href="${contextPath}/login">
            <button type="button" class="btn btn-danger">Try again...</button>
        </a>
        <a href="${contextPath}/registration">
            <button type="button" class="btn btn-danger">Or Register new User</button>
        </a>
    </div>
</div>
</body>
</html>