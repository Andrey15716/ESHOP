<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DataPage</title>
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
<div>
    <h2>Category Data pages control</h2>
    <div>
        <label>Category Data</label>
        <div>
            <form method="POST" enctype="multipart/form-data" action="/category/upload">
                <p>Upload category:</p>
                <input type="file" name="file"><br/>
                <button type="submit" class="btn btn-primary" style="width: 100px; margin: 5px 0;">Upload</button>
            </form>
        </div>
        <div>
            <p>Download category</p>
            <a href="${contextPath}/category/download">
                <button class="btn btn-primary" style="width: 100px; margin: 5px 0;">Download</button>
            </a>
        </div>
    </div>

    <h2>Product Data pages control</h2>
    <div>
        <label>Product Data</label>
        <div>
            <form method="POST" enctype="multipart/form-data" action="/product/upload">
                <p>Upload product:</p>
                <input type="file" name="file"><br/>
                <button type="submit" class="btn btn-primary" style="width: 100px; margin: 5px 0;">Upload</button>
            </form>
        </div>
        <div>
            <p>Download product</p>
            <a href="${contextPath}/product/download">
                <button class="btn btn-primary" style="width: 100px; margin: 5px 0;">Download</button>
            </a>
        </div>
    </div>

    <h2>User Data pages control</h2>
    <div>
        <label>User Data</label>
        <div>
            <p>Download user</p>
            <a href="${contextPath}/user/download">
                <button class="btn btn-primary" style="width: 100px; margin: 5px 0;">Download</button>
            </a>
        </div>
    </div>

    <h2>Order Data pages control</h2>
    <div>
        <label>Order Data</label>
        <div>
            <form method="POST" enctype="multipart/form-data" action="/order/upload">
                <p>Upload order:</p>
                <input type="file" name="file"><br/>
                <button type="submit" class="btn btn-primary" style="width: 100px; margin: 5px 0;">Upload</button>
            </form>
        </div>
        <div>
            <p>Download order</p>
            <a href="${contextPath}/order/download">
                <button class="btn btn-primary" style="width: 100px; margin: 5px 0;">Download</button>
            </a>
        </div>
    </div>
</div>
</body>
</html>
