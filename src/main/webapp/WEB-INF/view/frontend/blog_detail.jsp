<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 4/2/20
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<section class="blog-detail-page section-b-space ratio2_3">
    <div class="container">
        <c:if test="${not empty blog}">
            <div class="row">
                <div class="col-sm-12 blog-detail"><img width="100%" src="${pageContext.request.contextPath}/resources/images/blog/${blog.image}" class="img-fluid blur-up lazyloaded" alt="">
                    <h3>${blog.title}</h3>
                    <ul class="post-social">
                        <li><fmt:formatDate value="${blog.createdAt}"></fmt:formatDate></li>
                        <li>Posted By : ${blog.createdBy}</li>
                        <li><i class="fa fa-comments"></i> 10 Comment</li>
                    </ul>
                    ${blog.content}
                </div>
            </div>
        </c:if>
    </div>
</section>