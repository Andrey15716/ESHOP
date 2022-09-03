<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="dropdown">
    <select onchange="location=value" id="pageSize" name="pageSize">
        <option>Выбрать количество продуктов на странице</option>
        <option value="${contextPath}/login/profile?pageNumber=0&pageSize=5">5</option>
        <option value="${contextPath}/login/profile?pageNumber=0&pageSize=10">10</option>
        <option value="${contextPath}/login/profile?pageNumber=0&pageSize=15">15</option>
    </select>
    ${pageSize}
</div>
