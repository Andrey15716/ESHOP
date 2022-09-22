<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <li class="nav-item"><a class="nav-link" href="${contextPath}/login/profile" style="color:#1bbde6">Profile</a></li>
                <li class="nav-item"><a class="nav-link" href="${contextPath}/search" style="color:#1bbde6">Search</a></li>
                <li class="nav-item"><a class="nav-link" href="${contextPath}/logout" style="color:#1bbde6">Logout</a></li>
                <li class="nav-item"><a class="nav-link" href="${contextPath}/cart" style="color:#1bbde6">Cart</a></li>
                <sec:authorize access="hasRole('ADMIN')">
                    <li class="nav-item"><a class="nav-link" href="${contextPath}/home/admin" style="color:#1bbde6">Admin</a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
