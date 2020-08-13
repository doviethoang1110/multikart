<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 4/2/20
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>
<section class="section-b-space blog-page ratio2_3">
    <div class="container">
        <div class="row">
            <!--Blog sidebar start-->
            <div class="col-xl-3 col-lg-4 col-md-5">
                <div class="blog-sidebar">
                    <div class="theme-card">
                        <h4>Recent Blog</h4>
                        <ul class="recent-blog">
                            <c:if test="${not empty listBlog}">
                                <c:forEach var="b" items="${listBlog}">
                                    <li>
                                        <div class="media"><img class="img-fluid blur-up lazyloaded" src="${pageContext.request.contextPath}/resources/images/blog/${b.image}" alt="Generic placeholder image">
                                            <div class="media-body align-self-center">
                                                <h6><fmt:formatDate value="${b.createdAt}"></fmt:formatDate></h6>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
            <!--Blog List start-->
            <div class="col-xl-9 col-lg-8 col-md-7 order-sec" id="blogs">

            </div>
            <div class="loader" hidden></div>
            <!--Blog List start-->
        </div>
    </div>
</section>
<style>
    .loader {
        border: 16px solid #f3f3f3; /* Light grey */
        border-top: 16px solid #3498db; /* Blue */
        border-radius: 50%;
        width: 50px;
        height: 50px;
        animation: spin 2s linear infinite;
        margin: 0 auto;
        margin-top: 20px;
    }

    @keyframes spin {
        0% { transform: rotate(0deg); }
        100% { transform: rotate(360deg); }
    }
</style>
<script>
    let currentPage = 0;
    let delay = false;
    let totalPage;
    $(document).ready(function () {
        getBlogs(currentPage);
        $(window).scroll(function() {
            if($(window).scrollTop() > $(".blog-page").height()) {
                if(currentPage>=totalPage-1){
                    return false;
                }else{
                    if(delay === true){
                        return false;
                    }
                    delay = true;
                    currentPage++;
                    $(".loader").prop("hidden",false);
                    setTimeout(()=>{
                        getBlogs(currentPage).always(function () {
                            $(".loader").prop("hidden",true);
                            delay = false;
                        });
                    },1000);
                    return false;
                }
            }
        });

    });
    function getBlogs(page) {
        return $.ajax({
            async:false,
            url:ctx+"/blogs?page="+page,
            method:"GET",
            success:function (response) {
                currentPage = response.currentPage;
                totalPage = response.totalPages;
                response.listBlog.forEach(blog=>{
                    console.log(blog.slug)
                    $("#blogs").append(`
                        <div class="row blog-media">
                            <div class="col-xl-6">
                                <div class="blog-left">
                                    <a href="\${ctx+'/bai-viet/'+blog.slug}\" class="bg-size blur-up lazyloaded" style="background-image: url(&quot;\${ctx}\/resources/images/blog/\${blog.image}\&quot;); background-size: cover; background-position: center center; display: block;"></a>
                                </div>
                            </div>
                            <div class="col-xl-6">
                                <div class="blog-right">
                                    <div>
                                        <h6>`+new Intl.DateTimeFormat('en', { year: 'numeric',month: 'short',day: '2-digit' }).format(blog.createdAt)+`</h6>
                                        <a href="\${ctx+'/bai-viet/'+blog.slug}\">
                                            <h4>\${blog.title}\</h4>
                                        </a>
                                        <ul class="post-social">
                                            <li>Posted By : \${blog.createdBy}\</li>
                                            <li><i class="fa fa-comments"></i> 10 Comment</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    `);
                })
            },
            error:function (error) {
                console.log(error);
            }
        });
    }
</script>