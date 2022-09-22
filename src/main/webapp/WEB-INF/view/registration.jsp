<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Registration</title>
    <%@include file="/resources/links.jsp" %>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@include file="/resources/registrationNavbar.jsp" %>
<div class="container">
    <div class="col-md-8 offset-md-4">
        <h2>Registration</h2>
        <form method="post" action="${contextPath}/registration" class="needs-validation" novalidate>
            <div class="form-group">
                <label for="name">Login:</label>
                <input type="text" class="form-control w-25" id="username" placeholder="Enter login" name="username"
                       required>
                <div class="invalid-feedback">Login should be entered!</div>
                <span class="error">${nameError}</span>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="text" class="form-control w-25" id="password" placeholder="Enter password" name="password"
                       required>
                <div class="invalid-feedback">Password should be entered!</div>
                <span class="error">${passwordError}</span>
            </div>
            <div class="form-group">
                <label for="confirmpassword">Confirm password:</label>
                <input type="text" class="form-control w-25" id="confirmpassword" placeholder="Confirm password"
                       name="confirmpassword"
                       required>
                <div class="invalid-feedback">You need to confirm password!</div>
                <span class="error">${passwordError}</span>
            </div>
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control w-25" id="name" placeholder="Enter name"
                       name="name"
                       required>
                <div class="invalid-feedback">Name should be entered!</div>
                <span class="error">${nameError}</span>
            </div>
            <div class="form-group">
                <label for="surname">Surname:</label>
                <input type="text" class="form-control w-25" id="surname" placeholder="Enter surname"
                       name="surname"
                       required>
                <div class="invalid-feedback">Surname should be entered!</div>
                <span class="error">${surnameError}</span>
            </div>
            <div class="form-group">
                <label for="dateBorn">Date born:</label>
                <input type="date" class="form-control w-25" id="dateBorn" placeholder="Enter your birth date"
                       name="dateBorn"
                       required>
                <div class="invalid-feedback">Birth date should be entered!</div>
            </div>
            <button type="submit" class="btn btn-primary" style="width: 110px; margin: 5px 0;">Registration</button>
        </form>
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