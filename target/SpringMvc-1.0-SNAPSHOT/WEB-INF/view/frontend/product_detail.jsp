<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 4/2/20
  Time: 8:33 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
    label[for='rating']{
        float: left;
    }
    custom-checked{
        color: orange;
    }
</style>
<section class="section-b-space" id="product_detail">
    <div class="collection-wrapper" id="detail">
        <div class="container">
            <div class="row">
                <div class="col-sm-3 collection-filter">
                    <div class="collection-filter-block">
                        <div class="collection-mobile-back">
                                <span class="filter-back">
                                    <i class="fa fa-angle-left" aria-hidden="true"></i>
                                    back
                                </span>
                        </div>
                        <div class="collection-collapse-block border-0 open">
                            <h3 class="collapse-block-title">brand</h3>
                            <div class="collection-collapse-block-content">
                                <div class="collection-brand-filter">
                                    <ul class="category-list">
                                        <c:if test="${not empty listBrand}">
                                            <c:forEach items="${listBrand}" var="b">
                                            <li><a href="#">${b.name}</a></li>
                                            </c:forEach>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9 col-sm-12 col-xs-12">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-xl-12">
                                <div class="filter-main-btn mb-2"><span class="filter-btn"><i class="fa fa-filter"
                                                                                              aria-hidden="true"></i> filter</span></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="product-slick">
                                    <c:if test="${not empty product.image_list}">
                                        <c:forEach var="i" items="${product.image_list}">
                                            <div><img src="<c:url value="/resources/images/pro3"/>/${i}" alt=""
                                                      class="img-fluid blur-up lazyload"></div>
                                        </c:forEach>
                                    </c:if>
                                </div>
                                <div class="row">
                                    <div class="col-12 p-0">
                                        <div class="slider-nav">
                                            <c:if test="${not empty product.image_list}">
                                                <c:forEach var="i" items="${product.image_list}">
                                                    <div><img src="<c:url value="/resources/images/pro3"/>/${i}" alt=""
                                                              class="img-fluid blur-up lazyload"></div>
                                                </c:forEach>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 rtl-text">
                                <div class="product-right">
                                    <img src="<c:url value="/resources/images/pro3"/>/${product.image}" id="image" hidden/>
                                    <h2 id="name">${product.name}</h2>
                                    <span id="product_id" content="${product.id}" hidden></span>
                                    <p id="code"></p>
                                    <input type="hidden" id="priceValue">
                                    <h4 id="discount"></h4>
                                    <h3 id="price"></h3>
                                    <c:forEach var="option" items="${product.optionDtos}" varStatus="count">
                                        <div class="product-description border-product">
                                            <h6 class="product-title size-text">select ${option.name}</h6>
                                        </div>
                                        <c:if test="${option.name=='color'}">
                                            <div class="color-selector select${count.index}">
                                                <ul>
                                                    <c:forEach items="${option.optionValueDtos}" varStatus="index" var="op">
                                                        <li content="${op.name}" id="${op.id}" class="${op.name}"><a href="#"></a></li>
                                                    </c:forEach>
                                                </ul>
                                                <c:if test="${count.index!=0}">
                                                    <span class="message${count.index}"></span>
                                                </c:if>
                                            </div>
                                        </c:if>
                                        <c:if test="${option.name!='color'}">
                                            <div class="size-box select${count.index} size-${count.index}">
                                                <ul>
                                                    <c:forEach items="${option.optionValueDtos}" var="op" varStatus="index">
                                                        <li content="${op.name}" id="${op.id}"><a href="#">${op.name}</a></li>
                                                    </c:forEach>
                                                </ul>
                                                <c:if test="${count.index!=0}">
                                                    <span class="message${count.index}"></span>
                                                </c:if>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${product.vision==1}">
                                        <div class="product-description border-product">
                                            <h6 class="product-title">quantity</h6>
                                            <div class="qty-box">
                                                <div class="input-group"><span class="input-group-prepend"><button type="button" class="btn quantity-left-minus" data-type="minus" data-field=""><i class="ti-angle-left"></i></button> </span>
                                                    <input type="text" name="quantity" class="form-control input-number" value="1"> <span class="input-group-prepend"><button type="button" class="btn quantity-right-plus" data-type="plus" data-field=""><i class="ti-angle-right"></i></button></span></div>
                                                <span class="text-danger" id="error"></span>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="product-buttons">
                                        <c:choose>
                                            <c:when test="${product.vision==1}">
                                                <a id="addCart" href="#" class="btn btn-solid">
                                                    add to cart
                                                </a>
                                                <span id="btnCart"></span>
                                            </c:when>
                                            <c:when test="${product.vision==2}">
                                                <p>Coming soon</p>
                                            </c:when>
                                            <c:when test="${product.vision==3}">
                                                <p>Stop selling</p>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <div class="border-product">
                                        <h6 class="product-title">product details</h6>
                                        <p>${product.description}</p>
                                    </div>
                                    <div class="border-product">
                                        <h6 class="product-title">share it</h6>
                                        <div class="product-icon">
                                            <ul class="product-social">
                                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                                <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                                                <li><a href="#"><i class="fa fa-rss"></i></a></li>
                                            </ul>
                                            <form class="d-inline-block">
                                                <button class="wishlist-btn"><i class="fa fa-heart"></i><span class="title-font">Add To WishList</span></button>
                                            </form>
                                        </div>
                                    </div>
                                    <div class="border-product">
                                        <h6 class="product-title">Time Reminder</h6>
                                        <div class="timer">
                                            <p id="demo"><span>25 <span class="padding-l">:</span> <span class="timer-cal">Days</span> </span><span>22 <span class="padding-l">:</span> <span class="timer-cal">Hrs</span> </span><span>13 <span class="padding-l">:</span> <span class="timer-cal">Min</span> </span><span>57 <span class="timer-cal">Sec</span></span>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <section class="tab-product m-0">
                        <div class="row">
                            <div class="col-sm-12 col-lg-12">
                                <ul class="nav nav-tabs nav-material" id="top-tab" role="tablist">
                                    <li class="nav-item"><a class="nav-link active" id="top-home-tab"
                                                            data-toggle="tab" href="#top-home" role="tab" aria-selected="true"><i
                                            class="icofont icofont-ui-home"></i>Description</a>
                                        <div class="material-border"></div>
                                    </li>
                                    <li class="nav-item"><a class="nav-link" id="profile-top-tab" data-toggle="tab"
                                                            href="#top-profile" role="tab" aria-selected="false"><i
                                            class="icofont icofont-man-in-glasses"></i>Details</a>
                                        <div class="material-border"></div>
                                    </li>
                                    <li class="nav-item"><a class="nav-link" id="review-top-tab" data-toggle="tab"
                                                            href="#top-review" role="tab" aria-selected="false"><i
                                            class="icofont icofont-contacts"></i>Write Review</a>
                                        <div class="material-border"></div>
                                    </li>
                                </ul>
                                <div class="tab-content nav-material" id="top-tabContent">
                                    <div class="tab-pane fade show active" id="top-home" role="tabpanel"
                                         aria-labelledby="top-home-tab">
                                        <p>${product.description}</p>
                                    </div>
                                    <div class="tab-pane fade" id="top-profile" role="tabpanel" aria-labelledby="profile-top-tab">
                                        <div class="col-md-12 review-box">

                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="top-review" role="tabpanel"
                                         aria-labelledby="review-top-tab">
                                        <form class="theme-form" id="form-review" method="post">
                                            <div class="form-row">
                                                <div class="col-md-12">
                                                    <div class="media">
                                                        <label>Rating</label>
                                                        <div class="media-body ml-3">
                                                            <div class="rating three-star">
                                                                <span>Bad</span>
                                                                <input type="radio" name="rating" value="1">
                                                                <input type="radio" name="rating" value="2">
                                                                <input type="radio" name="rating" value="3">
                                                                <input type="radio" name="rating" value="4">
                                                                <input type="radio" name="rating" value="5">
                                                                <span>Good</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <label for="review-name">Name</label>
                                                    <input type="text" name="name" class="form-control" id="review-name"
                                                           placeholder="Enter Your name">
                                                </div>
                                                <div class="col-md-6">
                                                    <label for="review-email">Email</label>
                                                    <input type="text" name="email" class="form-control" id="review-email"
                                                           placeholder="Email">
                                                </div>
                                                <div class="col-md-12">
                                                    <label for="review">Review Title</label>
                                                    <textarea class="form-control" name="content"
                                                              placeholder="Wrire Your Testimonial Here"
                                                              id="review" rows="6"></textarea>
                                                </div>
                                                <div class="col-md-12">
                                                    <button class="btn btn-solid" type="submit">Submit YOur
                                                        Review</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Section ends -->


<!-- related products -->
<section class="section-b-space pt-0 ratio_asos">
    <div class="container">
        <div class="row">
            <div class="col-12 product-related">
                <h2>related products</h2>
            </div>
        </div>
        <div class="row search-product">
            <c:forEach items="${listPro}" var="pro">
                <div class="col-xl-2 col-md-4 col-sm-6">
                    <div class="product-box">
                        <div class="img-wrapper">
                            <div class="front">
                                <a href="${pageContext.request.contextPath}/${pro.categorySlug}/${pro.slug}"><img src="<c:url value="/resources/images/pro3/${pro.image}"/> "
                                                 class="img-fluid blur-up lazyload bg-img" alt=""></a>
                            </div>
                            <div class="cart-info cart-wrap">
                                <a href="javascript:void(0)"
                                                                                  title="Add to Wishlist"><i class="ti-heart" aria-hidden="true"></i></a> <a href="#"
                                                                                                                                                             data-toggle="modal" data-target="#quick-view" title="Quick View"><i
                                    class="ti-search" aria-hidden="true"></i></a> <a href="compare.html"
                                                                                     title="Compare"><i class="ti-reload" aria-hidden="true"></i></a></div>
                        </div>
                        <div class="product-detail">
                            <div class="rating"><i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
                                    class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i></div>
                            <a href="product-page(no-sidebar).html">
                                <h6>${pro.name}</h6>
                            </a>
                            <h4><fmt:formatNumber value="${pro.priceFrom}"></fmt:formatNumber> - <fmt:formatNumber value="${pro.priceTo}"></fmt:formatNumber> VND</h4>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
<script>
    $(document).ready(function () {
        let discount = ${product.discount};
        let options = [];
        <c:forEach items="${product.optionDtos}" var="pro">
        var obj = {name:'${pro.name}',value:null,valueName:""};
        options.push(obj);
        </c:forEach>
        for (let i = 0; i < options.length; i++) {
            if(i+1<options.length){
                if(!$('.select'+i+' ul li').hasClass("active")){
                    $('.select'+(i+1)+' ul li').addClass("disable");
                    $(".message"+(i+1)).html("<span class='tooltiptext'>Vui lòng chọn "+options[i].name+" trước</span>");
                    $("#addCart").addClass("disable");
                    $("div.product-buttons").on('mouseover',function (event) {
                        $("#btnCart").html("<span class='tooltiptext'>Vui lòng chọn đủ option</span>")
                    });
                    $("div.product-buttons").on('mouseout',function (event) {
                        $("#btnCart").html("");
                    })
                }
            }
            $('.size-'+i+' ul li').on('click', function(e) {
                e.preventDefault();
                $('.size-'+i+' ul li').removeClass("active");
                $(this).addClass("active");
            });
            $('.select'+i+' ul li').on('click', function(e) {
                e.preventDefault();
                $('.select'+i+' ul li').removeClass("active");
                $(this).addClass("active");
            });
            $('.select'+i+' ul li').on('click',function (event) {
                event.preventDefault();
                options[i].value = parseInt($(this).attr("id"));
                options[i].valueName = $(this).attr("content");
                $('.select'+(i+1)+' ul li').removeClass("disable");
                $(".message"+(i+1)).html("");
                if($('.select'+(options.length-1)+' ul li').hasClass("active")){
                    $("#addCart").removeClass("disable");
                    $("div.product-buttons").on('mouseover',function (event) {
                        $("#btnCart").html("");
                    });
                    $.ajax({
                        url:"${pageContext.request.contextPath}/products/filter",
                        method:"POST",
                        data:JSON.stringify(options.map(e=>e.value)),
                        dataType: 'json',
                        contentType:'application/json',
                        success:(function (response) {
                            $("#code").html("Code: <h3 style='color: orange'>"+response.data.code+"</h3>");
                            $("#addCart").removeClass("disable");
                            if(parseFloat(discount)>0){
                                $("#discount").html("<del>"+new Intl.NumberFormat('en-US',{ style: 'currency', currency: symbol.code }).format(parseFloat(symbol.rate !==1 ? (response.data.price/symbol.rate/1000) : (response.data.price/symbol.rate)))+"</del><span>"+discount+"% off</span>");
                                $("#price").html(new Intl.NumberFormat('en-US',{ style: 'currency', currency: symbol.code }).format(parseFloat(symbol.rate !==1 ? ((response.data.price-(response.data.price*discount)/100)/symbol.rate/1000) : ((response.data.price-(response.data.price*discount)/100)/symbol.rate))));
                                $("#priceValue").val(response.data.price-(response.data.price*discount)/100);
                            }else{
                                $("#discount").html("");
                                $("#price").html(new Intl.NumberFormat('en-US',{ style: 'currency', currency: symbol.code }).format(parseFloat(symbol.rate !== 1 ? response.data.price/symbol.rate/1000 : response.data.price/symbol.rate).toFixed(2)));
                                $("#priceValue").val(response.data.price);
                            }
                        }),
                        error:(function (error) {
                            $("#code").html("Option này tạm hết hàng");
                            $("#discount").html("");
                            $("#addCart").addClass("disable");
                            $("#price").html("");
                        })
                    })
                }else{
                    $("div.product-buttons").on('mouseover',function (event) {
                        $("#btnCart").html("<span class='tooltiptext'>Vui lòng chọn đủ option</span>");
                    })
                }
            })
        }
        $("#addCart").on('click',function (event) {
            event.preventDefault();
            if($("input[name='quantity']").val()<1){
                $("#error").text("Tối thiểu 1 sản phẩm");
                return false;
            }else{
                $("#error").text("");
                let name = $("#name").text()+" / ";
                options.map(e=>e.valueName).forEach(e=>{
                    name += e+" ";
                });
                let item = {
                    id:$("#code").text().slice(6),
                    productId:parseInt($("#product_id").attr("content")),
                    image:$("#image").attr("src"),
                    name:name,
                    price:$("#priceValue").val(),
                    quantity:parseInt($("input[name='quantity']").val())
                }
                addCart(item);
            }
        });
        function addCart(item) {
            if(listCart===null||listCart.length===0){
                listCart.push(item);
            }else{
                let cart = listCart.find(e=>e.id===item.id);
                if(cart!==undefined){
                    cart.quantity += parseInt(item.quantity);
                }else{
                    listCart.push(item);
                }
            }
            localStorage.setItem("listCart",JSON.stringify(listCart));
            sweetAlert("success","Thêm mới thành công");
            getCartNumber();
            addTopCart();
        }
        getReviews();
    });
    function getReviews(){
        $.ajax({
           url:ctx+"/reviews/"+$("#product_id").attr("content"),
           method:"GET",
           success:function (response) {
                $("div.review-box").html("");
                response.forEach(r=>{
                   $("div.review-box").append(`
                        <div class="col-md-12">
                            <div class="post-author">
                                <p><span>\${r.name}\ -</span> `+new Intl.DateTimeFormat(['ban', 'id']).format(r.created)+`</p>
                            </div>
                                <div id="review\${r.id}\" class="ratings" style="padding-left: 20px">
                                </div>
                   `);
                   $("div#review"+r.id).html("");
                    for (let i = 1; i <= 5; i++) {
                        $("div#review"+r.id).append(`
                            <span class="fa fa-star" style="color: \${i<=r.rating ? 'orange' : ''}\"></span>
                        `);
                    }
                   $("div.review-box").append(`
                            <p>Nội dung : \${r.content}\</p>
                        </div>
                   `);
                });
           },
           error:function (error) {
                console.log(error);
           }
        });
    }
    $("#form-review").on('submit',function (event) {
        event.preventDefault();
        $("#form-review").validate({
            rules: {
                "name": {
                    required: true,
                    maxlength: 15
                },
                "email": {
                    required: true,
                    maxlength: 40,
                    email:true
                },
                "content": {
                    required: true,
                },
                "rating":{required:true}
            },
            messages:{
                "name":{
                    required:"Tên không được rỗng",
                    maxlength:"Tối đa 15 ký tự"
                },
                "email":{
                    required:"Email không được rỗng",
                    maxlength:"Tối đa 40 ký tự",
                    email:"Email không đúng định dạng"
                },
                "content":{
                    required:"Nội dung không được rỗng"
                },
                "rating":{
                    required:"Rating không được rỗng"
                }
            },
            submitHandler: function () {
                let data = {
                    id:null,
                    slug:"${product.slug}",
                    rating:$("input[name='rating']:checked").val(),
                    name:$("input[name='name']").val(),
                    email:$("input[name='email']").val(),
                    content:$("textarea[name='content']").val(),
                };
                $.ajax({
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    url:ctx+"/reviews",
                    method:"POST",
                    data:JSON.stringify(data),
                    dataType: "json",
                    success:function (response) {
                        alert(response.data);
                        document.getElementById("form-review").reset();
                    },
                    error:function (error) {
                        console.log(error)
                    }
                })
            }
        })
    });
</script>