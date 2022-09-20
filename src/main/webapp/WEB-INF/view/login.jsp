<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login form</title>
    <%@include file="/resources/links.jsp" %>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="container">
    <div class="col-md-8 offset-md-4">
        <h2>Login</h2>
        <p>Please, enter your credentials</p>
        <form method="POST" action="${pageContext.request.contextPath}/login" class="needs-validation" novalidate>
            <div class="form-group">
                <label for="name">Username:</label>
                <input type="text" class="form-control w-25" id="name" placeholder="Enter username" name="name"
                       required>
                <div class="invalid-feedback">Username should be entered!</div>
                <span class="error">${nameError}</span>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control w-25" id="password" placeholder="Enter password"
                       name="password"
                       required>
                <div class="invalid-feedback">Password should be entered!</div>
                <span class="error">${passwordError}</span>
            </div>
            <button type="submit" class="btn btn-primary" style="width: 90px; margin: 5px 0;">Login</button>
        </form>
        <a href="${contextPath}/registration">
            <button class="btn btn-primary" style="width: 110px; margin: 5px 0;" id="registerRedirect" type="submit">
                Registration
            </button>
        </a>
    </div>
</div>
<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Get the forms we want to add validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
</body>
</html>