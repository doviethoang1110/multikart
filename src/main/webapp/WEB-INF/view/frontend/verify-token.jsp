<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 8/7/20
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<section class="pwd-page section-b-space">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3">
                <c:if test="${not empty map}">
                    <c:if test="${not empty map.error}">
                        <h2>${map.error}</h2>
                    </c:if>
                    <c:if test="${not empty map.success}">
                        <h2>${map.success}</h2>
                        <a href="${pageContext.request.contextPath}/login" class="btn btn-solid">Submit</a>
                    </c:if>
                </c:if>
            </div>
        </div>
    </div>
</section>