<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="dropdown">
    <select onchange="location=value" id="pageSize" name="pageSize">
        <option>Выбрать количество продуктов на странице</option>
        <option value="${contextPath}/category/${category.getId()}?pageNumber=0&pageSize=1">1</option>
        <option value="${contextPath}/category/${category.getId()}?pageNumber=0&pageSize=2">2</option>
        <option value="${contextPath}/category/${category.getId()}?pageNumber=0&pageSize=3">3</option>
    </select>
    ${pageSize}
</div>
