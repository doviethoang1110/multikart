<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 4/2/20
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<section class="section-b-space ratio_asos">
    <div class="collection-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-sm-3 collection-filter">
                    <!-- side-bar colleps block stat -->
                    <div class="collection-filter-block">
                        <!-- brand filter start -->
                        <div class="collection-mobile-back"><span class="filter-back"><i class="fa fa-angle-left"
                                                                                         aria-hidden="true"></i> back</span></div>
                        <div class="collection-collapse-block open">
                            <h3 class="collapse-block-title">brand</h3>
                            <div class="collection-collapse-block-content">
                                <div class="collection-brand-filter">
                                    <c:forEach items="${sessionScope.listBrand}" var="b">
                                        <div class="custom-control custom-checkbox collection-filter-checkbox brand-div">
                                            <input type="checkbox" name="brand" class="custom-control-input" value="${b.slug}" id="${b.name}">
                                            <label id="${b.slug}" class="custom-control-label brand" for="${b.name}">${b.name}</label>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <!-- price filter start here -->
                        <div class="collection-collapse-block border-0 open">
                            <h3 class="collapse-block-title">price</h3>
                            <div class="collection-collapse-block-content" style="">
                                <div class="collection-brand-filter" id="price-filter">

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="collection-sidebar-banner">
                        <a href="#"><img src="../assets/images/side-banner.png" class="img-fluid blur-up lazyload"
                                         alt=""></a>
                    </div>
                    <!-- side-bar banner end here -->
                </div>
                <div class="collection-content col">
                    <div class="page-main-content">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="collection-product-wrapper">
                                    <div class="product-top-filter">
                                        <div class="row">
                                            <div class="col-xl-12">
                                                <div class="filter-main-btn"><span
                                                        class="filter-btn btn btn-theme"><i class="fa fa-filter"
                                                                                            aria-hidden="true"></i> Filter</span></div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-12">
                                                <div class="product-filter-content">
                                                    <div class="search-count">
                                                        <h5>Showing Products 1-24 of 10 Result</h5>
                                                    </div>
                                                    <div class="collection-view">
                                                        <ul>
                                                            <li><i class="fa fa-th grid-layout-view"></i></li>
                                                            <li><i class="fa fa-list-ul list-layout-view"></i></li>
                                                        </ul>
                                                    </div>
                                                    <div class="collection-grid-view">
                                                        <ul>
                                                            <li><img src="<c:url value="/resources/images/icon"/>/2.png" alt=""
                                                                     class="product-2-layout-view"></li>
                                                            <li><img src="<c:url value="/resources/images/icon"/>/3.png" alt=""
                                                                     class="product-3-layout-view"></li>
                                                            <li><img src="<c:url value="/resources/images/icon"/>/4.png" alt=""
                                                                     class="product-4-layout-view"></li>
                                                            <li><img src="<c:url value="/resources/images/icon"/>/6.png" alt=""
                                                                     class="product-6-layout-view"></li>
                                                        </ul>
                                                    </div>
                                                    <div class="product-page-per-view">
                                                        <select>
                                                            <option value="High to low">24 Products Par Page
                                                            </option>
                                                            <option value="Low to High">50 Products Par Page
                                                            </option>
                                                            <option value="Low to High">100 Products Par Page
                                                            </option>
                                                        </select>
                                                    </div>
                                                    <div class="product-page-filter">
                                                        <select id="order">
                                                            <option value="" selected>Sorting items</option>
                                                            <option value="priority-asc">Mới nhất</option>
                                                            <option value="price-asc">Giá tăng dần</option>
                                                            <option value="price-desc">Giá giảm dần</option>
                                                            <option value="name-asc">Tên A-Z</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="listPro">
                                        <div class="product-wrapper-grid">
                                            <div class="row" id="products">

                                            </div>
                                        </div>
                                        <div class="product-pagination">
                                            <div class="theme-paggination-block">
                                                <div class="row">
                                                    <div class="col-xl-6 col-md-6 col-sm-12">
                                                        <nav aria-label="Page navigation">
                                                            <ul class="pagination">

                                                            </ul>
                                                        </nav>
                                                    </div>
                                                    <div class="col-xl-6 col-md-6 col-sm-12">
                                                        <div class="product-search-count-bottom">
                                                            <h5></h5>
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
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    const prices = [
        {
            id:"price1",
            from:50000,
            to:300000
        },
        {
            id:"price2",
            from:300000,
            to:500000
        },
        {
            id:"price3",
            from:500000,
            to:1000000
        },
        {
            id:"price4",
            from:1000000,
            to:1500000
        },
        {
            id:"price5",
            from:1500000,
            to:2000000
        }
    ];
    $(document).ready(function () {
        $("#price-filter").html("");
        prices.forEach(p=>{
            $("#price-filter").append(`
                <div class="custom-control custom-checkbox collection-filter-checkbox">
                    <input type="checkbox" class="custom-control-input" name="price" id="\${p.id}\">
                    <label id="\${p.from+'-'+p.to}\" class="custom-control-label price-select" for="\${p.id}\">
                        `+new Intl.NumberFormat('en-US',{ style: 'currency', currency: symbol.code }).format(Math.round(parseFloat(symbol.rate !== 1 ? p.from/symbol.rate/1000 : p.from/symbol.rate)))+` ---
                        `+new Intl.NumberFormat('en-US',{ style: 'currency', currency: symbol.code }).format(Math.round(parseFloat(symbol.rate !== 1 ? p.to/symbol.rate/1000 : p.to/symbol.rate)))+`
                    </label>
                </div>
            `);
        });
        $(document).on('click','.brand',function () {
            let url = new URL(window.location.href);
            let param = url.searchParams;
            param.get("brand")===$(this).attr("id") ? param.delete("brand") : param.set("brand",$(this).attr("id"));
            url.search = param.toString();
            window.history.pushState("", "", url.toString());
            param.get("brand")===$(this).attr("id") ?
                $("input[name='brand']").prop("checked",false) :
                $("input#"+$(this).attr("id")).prop("checked",true);
            ajax();
        });
        $(document).on('click','.price-select',function () {
            let url = new URL(window.location.href);
            let param = url.searchParams;
            param.get("price")===$(this).attr("id") ? param.delete("price") : param.set("price",$(this).attr("id"));
            url.search = param.toString();
            window.history.pushState("", "", url.toString());
            param.get("price")===$(this).attr("id") ?
                $("input[name='price']").prop("checked",false) :
                $("input#"+$(this).attr("id")).prop("checked",true);
            ajax();
        });
        $("#order").change(function () {
            if($(this).val()!==0){
                let url = new URL(window.location.href);
                let param = url.searchParams;
                param.set("sort",$(this).val());
                url.search = param.toString();
                window.history.pushState("","",url.toString());
                ajax();
            }
        });
        ajax();
        let url = new URL(window.location.href);
        let param = url.searchParams;
        let array = [
            <c:forEach items="${sessionScope.listBrand}" var="brand">
            "${brand.slug}",
            </c:forEach>
        ];
        array.forEach(a=>{
            if(a===param.get("brand")){
                $("input[value='"+a+"']").prop("checked",true);
            }
        });
        prices.forEach(p=>{
            if(param.get("price")===p.from+"-"+p.to){
                $("input#"+p.id).prop("checked",true);
            }
        });
        $(document).on('click','.addWishList',function (event) {
            event.preventDefault();
            if(customer_id === "undefined"){
                alert("Bạn chưa đăng nhập");
                return false;
            }else{
                $.ajax({
                    headers:{
                        'Accept':'application/json',
                        'Content-Type': 'application/json'
                    },
                    url:ctx+"/wishlists",
                    method:"POST",
                    data:JSON.stringify({customer_id:customer_id,product_id:$(this).attr("content")}),
                    success:function (response) {
                        sweetAlert(response.status,response.data);
                    },
                    error:function (error) {
                        sweetAlert(error.responseJSON.status,error.responseJSON.data);
                    }
                })
            }
        })
    });
    function ajax() {
        let url = new URL(window.location.href);
        let param = url.searchParams;
        param.get("page") === null ? param.set("page",0) : param.set("page",param.get("page"));
        url.search = param.toString();
        window.history.pushState("","",url.toString());
        url = window.location.href.includes("?") ?
            ctx+"/search"+window.location.href.substring(window.location.href.indexOf("?"),window.location.href.length) :
            ctx+"/search";
        $.ajax({
            url:url,
            method:"GET",
            success:function (response) {
                if(response.listPro.length===0){
                    $("#listPro").html('<h2 class="text-center text-info">Không có sản phẩm nào</h2>');
                }else{
                    $("#listPro").html(`
                        <div class="product-wrapper-grid">
                                            <div class="row" id="products">

                                            </div>
                                        </div>
                        <div class="product-pagination">
                                            <div class="theme-paggination-block">
                                                <div class="row">
                                                    <div class="col-xl-6 col-md-6 col-sm-12">
                                                        <nav aria-label="Page navigation">
                                                            <ul class="pagination">

                                                            </ul>
                                                        </nav>
                                                    </div>
                                                    <div class="col-xl-6 col-md-6 col-sm-12">
                                                        <div class="product-search-count-bottom">
                                                            <h5></h5>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                    `);
                    $("#products").html("");
                    response.listPro.forEach(pro=>{
                        let name = "";
                        let value = "";
                        switch (parseInt(pro.priority)) {
                            case 1:
                                name = "new";
                                value = "grey";
                                break;
                            case 2:
                                name = "hot";
                                value = "orange";
                                break;
                            case 3:
                                name = "sales";
                                value = "red";
                                break;
                        }
                        $("#products").append(`
                            <div class="col-xl-3 col-md-6 col-grid-box">
                                <div class="product-box">
                                    <div class="img-wrapper">
                                        <div class="lable-block"><span style="font-size: 13px;
    padding: 15px 10px;background-color: `+value+`;border-radius: 100%;
  text-align: center;
  font-size: 14px;
  font-weight: 700;
  position: absolute;
  padding: 12px 6px;
  text-transform: uppercase;
  color: #ffffff;
  top: 7px;
  left: 7px;
  z-index: 1;">`+name+`</span> <span class="lable4">\${pro.discount > 0 ? 'on sale' : ''}\</span></div>
                                        <div class="front">
                                            <a href="`+ctx+`/\${pro.categorySlug}/\${pro.slug}\" class="bg-size blur-up lazyloaded" style="background-image: url(&quot;\${ctx+'/resources/images/pro3/'}\${pro.image}\&quot;); background-size: cover; background-position: center center; display: block;">
                                                <img src="\${ctx+'/resources/images/pro3/'}\${pro.image}\" class="img-fluid blur-up lazyload bg-img" alt="" style="display: none;">
                                            </a>
                                        </div>
                                        <div class="cart-info cart-wrap">
                                            <a href="javascript:void(0)" content="\${pro.id}\" class="addWishList" title="Add to Wishlist">
                                                <i class="ti-heart" aria-hidden="true"></i>
                                            </a>
                                            <a href="compare.html" title="Compare">
                                                <i class="ti-reload" aria-hidden="true"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="product-detail">
                                        <div>
                                             <div class="rating">
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                             </div>
                                            <a href="`+ctx+`/\${pro.categorySlug}/\${pro.slug}\">
                                                <h6>\${pro.name}\</h6>
                                            </a>
                                            <p></p>
                                            <h4>
                                                `+new Intl.NumberFormat('en-US',{ style: 'currency', currency: symbol.code }).format(Math.round(parseFloat(symbol.rate !== 1 ? pro.from/symbol.rate/1000 : pro.from/symbol.rate)))+`-
                                                `+new Intl.NumberFormat('en-US',{ style: 'currency', currency: symbol.code }).format(Math.round(parseFloat(symbol.rate !== 1 ? pro.to/symbol.rate/1000 : pro.to/symbol.rate)))+`
                                            </h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        `);
                    });
                    $("ul.pagination").html("");
                    $("ul.pagination").append(`
                        <li class="page-item" \${response.currentPage<1?'hidden':''}\>
                            <a id="previous" class="page-link" href="`+window.location.href.replace('?page='+parseInt(response.currentPage),'?page='+parseInt(response.currentPage-1))+`" aria-label="Previous">
                                <span aria-hidden="true"><i class="fa fa-chevron-left" aria-hidden="true"></i></span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                    `);
                    response.listPages.forEach(page=>{
                        $("ul.pagination").append(`
                           <li class="page-item \${page===response.currentPage?'active':''}\">
                                <a class="page-link" href="`+window.location.href.replace('?page='+parseInt(response.currentPage),'?page='+parseInt(page))+`">\${page+1}\</a>
                           </li>
                        `);
                    });
                    $("ul.pagination").append(`
                        <li class="page-item" \${response.currentPage===response.totalPage-1?'hidden':''}\>
                            <a id="next" class="page-link" href="`+window.location.href.replace('?page='+parseInt(response.currentPage),'?page='+parseInt(response.currentPage+1))+`" aria-label="Next">
                                <span aria-hidden="true"><i class="fa fa-chevron-right" aria-hidden="true"></i></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    `);
                    $("div.product-search-count-bottom h5").text(`Showing \${response.totalItems}\ Result`);
                }
            },
            error:function (error) {
                console.log(error);
            }
        });
    }
</script>