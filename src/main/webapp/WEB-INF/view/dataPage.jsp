<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DataPage</title>
    <%@include file="/resources/links.jsp" %>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@include file="/resources/navbar.jsp" %>
<div>
    <h2>Category Data pages control</h2>
    <div>
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
    <h2>Order Data pages control</h2>
    <div>
        <div>
            <p>Download all orders</p>
            <a href="${contextPath}/home/download">
                <button class="btn btn-primary" style="width: 100px; margin: 5px 0;">Download</button>
            </a>
        </div>
    </div>
</div>
<%@include file="/resources/footer.jsp" %>
</body>
</html>