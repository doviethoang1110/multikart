<%@ page import="java.util.List" %>
<%@ page import="com.hoang.global.GlobalModel" %>
<%@ page import="com.hoang.dto.client.CategoryDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 3/29/20
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="multikart">
    <meta name="keywords" content="multikart">
    <meta name="author" content="multikart">
    <link rel="icon" href="<c:url value="/resources/images/favicon/1.png"/> " type="image/x-icon">
    <link rel="shortcut icon" href="<c:url value="/resources/images/favicon/1.png"/> " type="image/x-icon"/>
    <meta id="csrf" name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta id="csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
    <title>
        <tiles:getAsString name="title"/>
        <c:if test="${not empty product}">
            ${product.name}
        </c:if>
        <c:if test="${not empty blog}">
            ${blog.title}
        </c:if>
    </title>
    <tiles:importAttribute name="scripts"></tiles:importAttribute>
    <c:forEach var="script" items="${scripts}">
        <script src="<c:url value="${script}" />"></script>
    </c:forEach>
    <c:url var="url" value="/resources/images/pro3/"/>
    <!--Google font-->
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet">
<%--    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>--%>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js" type="text/javascript"></script>
    <!-- Icons -->
    <tiles:importAttribute name="links"></tiles:importAttribute>
    <c:forEach items="${links}" var="link">
        <link rel="stylesheet" href="<c:url value="${link}" />" type="text/css" />
    </c:forEach>
    <style>
        ul.color-select li.active {
            border: 0px solid black;
            position: relative; }
        ul.color-select li:after {
            content: "";
            background-image: url("data:image/svg+xml;charset=utf8,<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 8 8'><path fill='%23000' d='M6.564.75l-3.59 3.612-1.538-1.55L0 4.26 2.974 7.25 8 2.193z'/></svg>");
            top: 10px;
            right: 5px;
            height: 15px;
            width: 15px;
            background-size: 70%;
            background-repeat: no-repeat;
            position: absolute; }
        .tooltiptext {
            /*visibility: hidden;*/
            width: 120px;
            background-color: black;
            color: #fff;
            text-align: center;
            border-radius: 6px;
            padding: 5px 0;
            position: absolute;
            z-index: 1;
        }
        .tooltiptext1 {
            visibility: hidden;
            width: 200px;
            background-color: black;
            color: #fff;
            text-align: center;
            border-radius: 6px;
            padding: 5px 0;
            position: absolute;
            z-index: 1;
        }
        label.error{
            color: red!important;
        }
        .materialChange:hover .tooltiptext {
            visibility: visible;
        }
        .sizeChange:hover .tooltiptext {
            visibility: visible;
        }
        #addCart:hover .tooltiptext1 {
            visibility: visible;
        }
        .custom-active{
            background-color:cyan;
        }
    </style>
</head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
    let ctx = "${pageContext.request.contextPath}";
    let listCart = JSON.parse(localStorage.getItem("listCart"))===null ? [] : JSON.parse(localStorage.getItem("listCart"));
    let currencies = [];
    var symbol;
    function calTotal(){
        let total = 0;
        listCart.forEach(cart=>{
            total += parseFloat(cart.price*cart.quantity);
        });
        return total;
    }
    function getCurrencies() {
        $.ajax({
            async:false,
            url:ctx+"/currency",
            method:"GET",
            dataType:"json",
            success:function (response) {
                localStorage.setItem("currencies",JSON.stringify(response));
                currencies = JSON.parse(localStorage.getItem("currencies"));
                // symbol = currencies.length>0 ? currencies[0] :JSON.parse(localStorage.getItem("currency"));
            },
            error:function (error) {
                console.log(error);
            }
        });
    }
    function getCartNumber() {
        let number = 0;
        listCart.forEach(c=>{
            number += parseInt(c.quantity);
        })
        $("#cartsize").text(number);
    }
    function sweetAlert(icon,title){
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            onOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        });
        Toast.fire({
            icon: icon,
            title: title
        });
        return Toast;
    }
    function removeCart(id){
        if(confirm("Bạn chắc không ?")){
            let cart = listCart.find(c => c.id === id);
            sweetAlert("success","Xóa khỏi giỏ hàng thành công");
            listCart.splice(listCart.indexOf(cart),1);
            localStorage.setItem("listCart", JSON.stringify(listCart));
            $("li[content='"+id+"']").remove();
            $("tbody[content='"+id+"']").remove();
            getCartNumber();
            if(listCart.length===0){
                $("ul.shopping-cart").html("<h4>Chưa có sản phẩm nào</h4>");
                $("#cartView").html("<h2>Chưa có sản phẩm nào</h2>");
            }else{
                $("#total").text(new Intl.NumberFormat('en-US', {style: 'currency', currency: symbol.code}).format(parseFloat(symbol.rate !== 1 ? calTotal()/symbol.rate/1000 : calTotal()/symbol.rate)));
                $("div.total h5 span").text(new Intl.NumberFormat('en-US', {style: 'currency', currency: symbol.code}).format(parseFloat(symbol.rate !== 1 ?calTotal()/symbol.rate/1000 : calTotal()/symbol.rate)));
            }
        }else{
            return false;
        }
    }
    function addTopCart() {
        $("ul.shopping-cart").html("");
        listCart.forEach(c=>{
            $("ul.shopping-cart").append(`
                <li content="\${c.id}\">
                    <div class="media">
                        <a href="#"><img class="mr-3" src="\${c.image}\" alt="Generic placeholder image"></a>
                            <div class="media-body">
                                    <a href="#"><h4>\${c.name}\</h4></a>
                                    <h4><span>\${c.quantity}\ x `+new Intl.NumberFormat('en-US',{ style: 'currency', currency: symbol.code }).format(parseFloat(symbol.rate !== 1 ? c.price/symbol.rate/1000 : c.price/symbol.rate))+`</span></h4>
                            </div>
                    </div>
                    <div class="close-circle">
                        <a href="#" content="\${c.id}\"><i class="fa fa-times" aria-hidden="true"></i></a>
                    </div>
                </li>
            `);
        });
        $("ul.shopping-cart").append(`
                <li>
                    <div class="total">
                        <h5>subtotal : <span>`+new Intl.NumberFormat('en-US',{ style: 'currency', currency: symbol.code }).format(parseFloat(symbol.rate !== 1 ? calTotal()/symbol.rate/1000 : calTotal()/symbol.rate))+`</span></h5>
                    </div>
                </li>
                <li>
                    <div class="buttons">
                        <a href="`+ctx+`/gio-hang" class="view-cart">view cart</a>
                        <a href="`+ctx+`/thanh-toan" class="checkout">checkout</a>
                    </div>
                </li>
            `);
    }
    $(document).ready(function () {
        getCartNumber();
        getCurrencies();
        symbol = JSON.parse(localStorage.getItem("currency")) === null ? currencies[0] : JSON.parse(localStorage.getItem("currency"));
        console.log(symbol)
        if(listCart.length===0||listCart===null){
            $("ul.shopping-cart").html(`
                <li><h4>Chưa có sản phẩm nào</h4></li>
            `);
        }else{
            addTopCart();
        }
        $("ul.list-inline").html("");
        currencies.forEach(r=>{
            $("ul.list-inline").append(`
                <li class="\${symbol.id === r.id ? 'custom-active' : ''}\"><a href="#" id="\${r.code}\">\${r.name}\</a></li>
            `);
        });
        $(document).on('click',"ul.list-inline li a",function (event) {
            event.preventDefault();
            symbol = currencies.find(c=>c.code === $(this).attr("id"));
            localStorage.removeItem("currency");
            localStorage.setItem("currency",JSON.stringify(symbol));
            setTimeout(()=>{
                window.location.reload();
            },0.00001);
        })
        $(document).on('click',"div.close-circle a",function (event) {
            event.preventDefault();
            removeCart($(this).attr("content"));
        });
    });

</script>

<body>
<header>
    <div class="mobile-fix-option"></div>
    <div class="top-header">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="header-contact">
                        <ul>
                            <li>Welcome to Our store Multikart</li>
                            <li><i class="fa fa-phone" aria-hidden="true"></i>Call Us: 123 - 456 - 7890</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6 text-right">
                    <ul class="header-dropdown">
                        <li class="onhover-dropdown mobile-account"> <i class="fa fa-user" aria-hidden="true"></i>
                            <security:authorize access="isAuthenticated()">
                                Xin chào <security:authentication property="principal.name"></security:authentication>
                            </security:authorize>
                            <security:authorize access="!isAuthenticated()">
                                My account
                            </security:authorize>
                            <ul class="onhover-show-div">
                                <security:authorize access="!isAuthenticated()">
                                    <li><a href="${pageContext.request.contextPath}/login" data-lng="en">Đăng nhập</a></li>
                                    <input type="hidden" value="undefined" name="customer_id"/>
                                    <li><a href="${pageContext.request.contextPath}/dang-ky" data-lng="en">Đăng ký</a></li>
                                </security:authorize>
                                <security:authorize access="isAuthenticated()">
                                    <input type="hidden" value='<security:authentication property="principal.id"></security:authentication>' name="customer_id"/>
                                    <li><a href="${pageContext.request.contextPath}/trang-ca-nhan" data-lng="es">Trang cá nhân</a></li>
                                    <li><a href="#" data-lng="es" id="logout">Đăng xuất</a></li>
                                </security:authorize>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="main-menu">
                    <div class="menu-left">
                        <div class="navbar">
                            <a href="javascript:void(0)" onclick="openNav()">
                                <div class="bar-style"><i class="fa fa-bars sidebar-bar" aria-hidden="true"></i>
                                </div>
                            </a>
                            <div id="mySidenav" class="sidenav">
                                <a href="javascript:void(0)" class="sidebar-overlay" onclick="closeNav()"></a>
                                <nav>
                                    <div onclick="closeNav()">
                                        <div class="sidebar-back text-left"><i class="fa fa-angle-left pr-2"
                                                                               aria-hidden="true"></i> Back</div>
                                    </div>
                                    <ul id="sub-menu" class="sm pixelstrap sm-vertical">
                                        <%
                                            GlobalModel globalModel = new GlobalModel(request);
                                            globalModel.createMenu((List<CategoryDto>) session.getAttribute("listCat"),0,out,"","");
                                        %>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                        <div class="brand-logo">
                            <a href="${pageContext.request.contextPath}/trang-chu"><img src="<c:url value="/resources/images/icon/logo.png"/>"
                                                      class="img-fluid blur-up lazyload" alt=""></a>
                        </div>
                    </div>
                    <div class="menu-right pull-right">
                        <div>
                            <nav id="main-nav">
                                <div class="toggle-nav"><i class="fa fa-bars sidebar-bar"></i></div>
                                <ul id="main-menu" class="sm pixelstrap sm-horizontal">
                                    <li>
                                        <div class="mobile-back text-right">Back<i class="fa fa-angle-right pl-2"
                                                                                   aria-hidden="true"></i></div>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/trang-chu">Home</a></li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/san-pham">product</a>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/bai-viet">Blog</a>
                                    </li>
                                    <li style="width: 600px">
                                        <div style="padding-top: 42px" class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroupPrepend">
                                                    <img src="<c:url value="/resources/images/icon/search.png"/>"
                                                         class="img-fluid blur-up lazyload" alt="">
                                                </span>
                                            </div>
                                            <input style="display: block" width="500px" type="text" class="form-control" id="search" placeholder="Nhập từ khóa tìm kiếm">
                                            <div id="result" style="display: block;width:600px;position:absolute;margin-top:50px;background-color:white;z-index: 9999;height: 350px;overflow-y: auto"></div>
                                        </div>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                        <div>
                            <div class="icon-nav">
                                <ul>
                                    <li class="onhover-div mobile-search">
                                        <div id="search-overlay" class="search-overlay">
                                            <div> <span class="closebtn" onclick="closeSearch()"
                                                        title="Close Overlay">×</span>
                                                <div class="overlay-content">
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-xl-12">
                                                                <form>
                                                                    <div class="form-group">
                                                                        <input type="text" class="form-control"
                                                                               id="exampleInputPassword1"
                                                                               placeholder="Search a Product">
                                                                    </div>
                                                                    <button type="submit" class="btn btn-primary"><i
                                                                            class="fa fa-search"></i></button>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="onhover-div mobile-setting">
                                        <div><img src="<c:url value="/resources/images/icon/setting.png"/>"
                                                  class="img-fluid blur-up lazyload" alt=""> <i
                                                class="ti-settings"></i></div>
                                        <div class="show-div setting">
                                            <h6>language</h6>
                                            <ul>
                                                <li><a href="#">english</a></li>
                                                <li><a href="#">french</a></li>
                                            </ul>
                                            <h6>currency</h6>
                                            <ul class="list-inline">

                                            </ul>
                                        </div>
                                    </li>
                                    <li class="onhover-div mobile-cart">
                                        <div><img src="<c:url value="/resources/images/icon/cart.png"/>"
                                                  class="img-fluid blur-up lazyload" alt=""> <i
                                                class="ti-shopping-cart"></i></div>
                                        <ul class="show-div shopping-cart">

                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="breadcrumb-section">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <div class="page-title">
                    <h2><tiles:insertAttribute name="title" ignore="true"/></h2>
                </div>
            </div>
            <div class="col-sm-6">
                <nav aria-label="breadcrumb" class="theme-breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/trang-chu">home</a></li>
                        <c:if test="${not empty product}">
                            <li class="breadcrumb-item" aria-current="page">${product.name}</li>
                        </c:if>
                        <c:if test="${not empty blog}">
                            <li class="breadcrumb-item" aria-current="page">${blog.title}</li>
                        </c:if>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
</div>

<tiles:insertAttribute name="content"/>

<footer class="footer-light">
    <div class="light-layout">
        <div class="container">
            <section class="small-section border-section border-top-0">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="subscribe">
                            <div>
                                <h4>KNOW IT ALL FIRST!</h4>
                                <p>Never Miss Anything From Multikart By Signing Up To Our Newsletter.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <form
                                action="https://pixelstrap.us19.list-manage.com/subscribe/post?u=5a128856334b598b395f1fc9b&amp;id=082f74cbda"
                                class="form-inline subscribe-form auth-form needs-validation" method="post"
                                id="mc-embedded-subscribe-form" name="mc-embedded-subscribe-form" target="_blank">
                            <div class="form-group mx-sm-3">
                                <input type="text" class="form-control" name="EMAIL" id="mce-EMAIL"
                                       placeholder="Enter your email" required="required">
                            </div>
                            <button type="submit" class="btn btn-solid" id="mc-submit">subscribe</button>
                        </form>
                    </div>
                </div>
            </section>
        </div>
    </div>
    <section class="section-b-space light-layout">
        <div class="container">
            <div class="row footer-theme partition-f">
                <div class="col-lg-4 col-md-6">
                    <div class="footer-title footer-mobile-title">
                        <h4>about</h4>
                    </div>
                    <div class="footer-contant">
                        <div class="footer-logo"><img src="../assets/images/icon/logo.png" alt=""></div>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                            ut labore et dolore magna aliqua. Ut enim ad minim veniam,</p>
                        <div class="footer-social">
                            <ul>
                                <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-google-plus" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-rss" aria-hidden="true"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col offset-xl-1">
                    <div class="sub-title">
                        <div class="footer-title">
                            <h4>my account</h4>
                        </div>
                        <div class="footer-contant">
                            <ul>
                                <li><a href="#">mens</a></li>
                                <li><a href="#">womens</a></li>
                                <li><a href="#">clothing</a></li>
                                <li><a href="#">accessories</a></li>
                                <li><a href="#">featured</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="sub-title">
                        <div class="footer-title">
                            <h4>why we choose</h4>
                        </div>
                        <div class="footer-contant">
                            <ul>
                                <li><a href="#">shipping & return</a></li>
                                <li><a href="#">secure shopping</a></li>
                                <li><a href="#">gallary</a></li>
                                <li><a href="#">affiliates</a></li>
                                <li><a href="#">contacts</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="sub-title">
                        <div class="footer-title">
                            <h4>store information</h4>
                        </div>
                        <div class="footer-contant">
                            <ul class="contact-list">
                                <li><i class="fa fa-map-marker"></i>Multikart Demo Store, Demo store India 345-659
                                </li>
                                <li><i class="fa fa-phone"></i>Call Us: 123-456-7898</li>
                                <li><i class="fa fa-envelope-o"></i>Email Us: <a href="#">Support@Fiot.com</a></li>
                                <li><i class="fa fa-fax"></i>Fax: 123456</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <div class="sub-footer">
        <div class="container">
            <div class="row">
                <div class="col-xl-6 col-md-6 col-sm-12">
                    <div class="footer-end">
                        <p><i class="fa fa-copyright" aria-hidden="true"></i> 2017-18 themeforest powered by
                            pixelstrap</p>
                    </div>
                </div>
                <div class="col-xl-6 col-md-6 col-sm-12">
                    <div class="payment-card-bottom">
                        <ul>
                            <li>
                                <a href="#"><img src="../assets/images/icon/visa.png" alt=""></a>
                            </li>
                            <li>
                                <a href="#"><img src="../assets/images/icon/mastercard.png" alt=""></a>
                            </li>
                            <li>
                                <a href="#"><img src="../assets/images/icon/paypal.png" alt=""></a>
                            </li>
                            <li>
                                <a href="#"><img src="../assets/images/icon/american-express.png" alt=""></a>
                            </li>
                            <li>
                                <a href="#"><img src="../assets/images/icon/discover.png" alt=""></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- footer end -->

<!--modal popup start-->
<div class="modal fade bd-example-modal-lg theme-modal" id="exampleModal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body modal1">
                <div class="container-fluid p-0">
                    <div class="row">
                        <div class="col-12">
                            <div class="modal-bg">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <div class="offer-content"> <img src="../assets/images/Offer-banner.png"
                                                                 class="img-fluid blur-up lazyload" alt="">
                                    <h2>newsletter</h2>
                                    <form
                                            action="https://pixelstrap.us19.list-manage.com/subscribe/post?u=5a128856334b598b395f1fc9b&amp;id=082f74cbda"
                                            class="auth-form needs-validation" method="post"
                                            id="mc-embedded-subscribe-form" name="mc-embedded-subscribe-form"
                                            target="_blank">
                                        <div class="form-group mx-sm-3">
                                            <input type="email" class="form-control" name="EMAIL" id="mce-EMAIL"
                                                   placeholder="Enter your email" required="required">
                                            <button type="submit" class="btn btn-solid"
                                                    id="mc-submit">subscribe</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade bd-example-modal-lg theme-modal exit-modal" id="exit_popup" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body modal1">
                <div class="container-fluid p-0">
                    <div class="row">
                        <div class="col-12">
                            <div class="modal-bg">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <div class="media">
                                    <img src="../assets/images/stop.png"
                                         class="stop img-fluid blur-up lazyload mr-3" alt="">
                                    <div class="media-body text-left align-self-center">
                                        <div>
                                            <h2>wait!</h2>
                                            <h4>We want to give you
                                                <b>10% discount</b>
                                                <span>for your first order</span>
                                            </h4>
                                            <h5>Use discount code at checkout</h5>
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
<!-- Add to cart modal popup end-->


<!-- facebook chat section start -->
<div id="fb-root"></div>
<!-- Your customer chat code -->
<div class="fb-customerchat" attribution=setup_tool page_id="2123438804574660" theme_color="#0084ff"
     logged_in_greeting="Hi! Welcome to PixelStrap Themes  How can we help you?"
     logged_out_greeting="Hi! Welcome to PixelStrap Themes  How can we help you?">
</div>
<!-- facebook chat section end -->

<script>(function (d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = 'https://connect.facebook.net/en_US/sdk/xfbml.customerchat.js#xfbml=1&version=v2.12&autoLogAppEvents=1';
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<!-- cart start -->
<div class="addcart_btm_popup" id="fixed_cart_icon">
    <a href="${pageContext.request.contextPath}/gio-hang" class="fixed_cart">
        <i class="ti-shopping-cart"></i>
        <span id="cartsize"></span>
    </a>
</div>
<!-- cart end -->

<!-- tap to top -->
<div class="tap-top top-cls">
    <div>
        <i class="fa fa-angle-double-up"></i>
    </div>
</div>
<!-- latest jquery-->
<style>
    .disable {
        pointer-events:none;
        opacity:0.6;
    }
</style>
<script src="<c:url value="/resources/js/script.js" />"></script>
<script>
    $("#logout").on('click',function (event) {
        event.preventDefault();
        $.ajax({
            url:ctx+"/dang-xuat",
            method:"GET",
            success:function () {
                setTimeout(function () {
                    location.reload();
                },0.0001);
            }
        });
    });
    let customer_id = $("input[name='customer_id']").val();
    function limit(c){
        return this.filter((x,i)=>{
            if(i<=(c-1)){return true}
        })
    }
    $("#result").fadeOut();
    Array.prototype.limit=limit;
    $("#search").on('keyup',function (event) {
        event.preventDefault();
        if(event.target.value!==""){
            $.ajax({
                url:ctx+"/tim-kiem?key="+event.target.value,
                method:"GET",
                success:function (response) {
                    $("#result").html("");
                    if(response.length>0){
                        response.forEach(pro=>{
                            $("#result").append(`
                            <a href="\${ctx+'/'+pro.categorySlug+'/'+pro.slug}\" style="padding: 20px">
                                <div class="row">
                                    <img class="col-md-4" src="\${ctx+'/resources/images/pro3/'+pro.image}\"/>
                                    <span class="col-md-8">
                                        <span style="display:block">\${pro.name}\</span>
                                        <span style="display:block">
                                            `+new Intl.NumberFormat('en-US',{ style: 'currency', currency: symbol.code }).format(Math.round(parseFloat(symbol.rate !== 1 ? pro.priceFrom/symbol.rate/1000 : pro.priceFrom/symbol.rate)))+`-
                                            `+new Intl.NumberFormat('en-US',{ style: 'currency', currency: symbol.code }).format(Math.round(parseFloat(symbol.rate !== 1 ? pro.priceTo/symbol.rate/1000 : pro.priceTo/symbol.rate)))+`
                                        </span>
                                    </span>
                                </div>
                            </a>
                        `);
                        });
                    }else{
                        $("#result").html("<h3>Không có sản phẩm phù hợp</h3>");
                    }
                },
                error:function (error) {
                    console.log(error);
                }
            })
            $("#result").fadeIn();
        }else{
            $("#result").html("");
            $("#result").fadeOut();
            return false;
        }
    });
</script>
</body>

</html>