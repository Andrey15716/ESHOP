<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Categories</title>
    <%@include file="/resources/links.jsp" %>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2>Popular categories</h2>
<%@include file="/resources/navbar.jsp" %>
<div class="container-fluid">
    <c:if test="${not empty categories}">
        <div class="row">
            <c:forEach items="${categories}" var="category">
                <div class="card w-25 m-1" type="category">
                    <div class="card-body">
                        <a href="${contextPath}/category/${category.getId()}">${category.getName()}</a>
                        <img class="card-img" style="width:150px;height:120px"
                             src="/images/${category.getImageName()}" alt="Card image">
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>
<%@include file="/resources/footer.jsp" %>
</body>
</html>