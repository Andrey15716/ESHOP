<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="dropdown">
    <select onchange="location=value" id="pageSize" name="pageSize">
        <option>Выбрать количество продуктов на странице</option>
        <option value="${contextPath}/search/result?pageNumber=0&pageSize=10&searchKey=${searchParams.getSearchKey()}&categoryName=${searchParams.getCategoryName()}&minPrice=${searchParams.getMinPrice()}&maxPrice=${searchParams.getMaxPrice()}">10</option>
        <option value="${contextPath}/search/result?pageNumber=0&pageSize=20&searchKey=${searchParams.getSearchKey()}&categoryName=${searchParams.getCategoryName()}&minPrice=${searchParams.getMinPrice()}&maxPrice=${searchParams.getMaxPrice()}">20</option>
        <option value="${contextPath}/search/result?pageNumber=0&pageSize=50&searchKey=${searchParams.getSearchKey()}&categoryName=${searchParams.getCategoryName()}&minPrice=${searchParams.getMinPrice()}&maxPrice=${searchParams.getMaxPrice()}">50</option>
        <option value="${contextPath}/search/result?pageNumber=0&pageSize=${pageSize}&searchKey=${searchParams.getSearchKey()}&categoryName=${searchParams.getCategoryName()}&minPrice=${searchParams.getMinPrice()}&maxPrice=${searchParams.getMaxPrice()}">Все</option>
    </select>
    ${pageSize}
</div>