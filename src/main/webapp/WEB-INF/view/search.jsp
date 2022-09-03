<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Page</title>
    <%@include file="/resources/links.jsp" %>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2>Search product</h2>
<%@include file="/resources/navbar.jsp" %>
<div class="container-fluid">
    <form method="post" action="${contextPath}/search">
        <input type="search" placeholder="Поиск товаров"
               name="searchKey" value="${searchParams.getSearchKey()}">
        <div class="row">
            <div class="col-sm-4">
                <select name="categoryName">
                    <c:if test="${empty searchParams.getCategoryName()}">
                        <option selected value="">Не выбрано</option>
                    </c:if>
                    <c:if test="${not empty searchParams.getCategoryName()}">
                        <option selected
                                value="${searchParams.getCategoryName()}">${searchParams.getCategoryName()}</option>
                    </c:if>
                    <option value="Mobiles">Mobiles</option>
                    <option value="Laptops">Laptops</option>
                    <option value="GPS">GPS</option>
                    <option value="Fridges">Fridges</option>
                    <option value="Cars">Cars</option>
                    <option value="Cameras">Cameras</option>
                    <option value="Washing machines">Washing machines</option>
                    <option value="Appliances">Appliances</option>
                    <option value="TV">TV</option>
                    <option value="">Не выбрано</option>
                </select>

                <br>
                <label for="minPrice"></label><input id="minPrice" type="number" min="0"
                                                     placeholder="цена от" name="minPrice"
                                                     value="${searchParams.getMinPrice()}">
                <label for="maxPrice"></label><input id="maxPrice" type="number" min="0"
                                                     placeholder="цена до" name="maxPrice"
                                                     value="${searchParams.getMaxPrice()}">

                <a href="${contextPath}/search?pageNumber=${pageNumber+1}&pageSize=${pageSize}
                &searchKey=${searchParams.getSearchKey()}&categoryName=${searchParams.getCategoryName()}
                &minPrice=${searchParams.getMinPrice()}&maxPrice=${searchParams.getMaxPrice()}">

                    <button id="searchBtn" type="submit">Применить</button>
                </a>
            </div>
        </div>
    </form>
</div>
<div class="col-sm-8">
    <c:if test="${not empty search_result}">
    <h3><p class="text-center">Найденные товары</p></h3>
    <c:forEach items="${search_result}" var="product">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-1" style="background-color:white;">
                    <a href="${contextPath}/product/${product.getId()}">
                        <img class="card-img" style="width:50px;height:120px"
                             src="${contextPath}/images/${product.getImageName()}" alt="Product images"></a>
                </div>
                <div class="col" style="background-color:white;">
                    <p>Модель:</p> <a>${product.getName()}</a>
                    <p>Цена:</p> <a>${product.getPrice()} руб</a>
                </div>
            </div>
        </div>
        <br>
    </c:forEach>
</div>
</c:if>
<c:if test="${empty search_result}">
    <h3><p class="text-center">Поиск товаров в нашем магазине ESHOP</p></h3>
</c:if>
<%@include file="/resources/footer.jsp" %>
</body>
</html>