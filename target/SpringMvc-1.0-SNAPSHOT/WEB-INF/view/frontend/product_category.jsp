<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 8/11/20
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<section class="section-b-space ratio_asos">
    <div class="collection-wrapper">
        <div class="container">
            <div class="row">
                <div class="collection-content col">
                    <div class="page-main-content">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="collection-product-wrapper">
                                    <div class="product-wrapper-grid">
                                        <div class="row margin-res">
                                            <c:if test="${not empty listPro}">
                                                <c:forEach items="${listPro}" var="p">
                                                    <div class="col-xl-3 col-6 col-grid-box">
                                                        <div class="product-box">
                                                            <div class="img-wrapper">
                                                                <div class="front">
                                                                    <a href="${pageContext.request.contextPath}/${slug}/${p.slug}" class="bg-size blur-up lazyloaded" style="background-image: url(&quot;../assets/images/pro3/35.jpg&quot;); background-size: cover; background-position: center center; display: block;"><img src="${pageContext.request.contextPath}/resources/images/pro3/${p.image}" class="img-fluid blur-up lazyload bg-img" alt="" style="display: none;"></a>
                                                                </div>
                                                                <div class="cart-info cart-wrap">
                                                                    <a href="javascript:void(0)" title="Add to Wishlist"><i class="ti-heart" aria-hidden="true"></i></a><a href="compare.html" title="Compare"><i class="ti-reload" aria-hidden="true"></i></a>
                                                                </div>
                                                            </div>
                                                            <div class="product-detail">
                                                                <div>
                                                                    <div class="rating"><i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i></div>
                                                                    <a href="${pageContext.request.contextPath}/${slug}/${p.slug}">
                                                                        <h6>${p.name}</h6>
                                                                    </a>
                                                                    <h4><fmt:formatNumber value="${p.priceFrom}"></fmt:formatNumber> - <fmt:formatNumber value="${p.priceTo}"></fmt:formatNumber> VND</h4>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>