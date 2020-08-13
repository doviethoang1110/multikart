<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 4/2/20
  Time: 8:55 PM
  To change this template use File | Settings | File Templates.
--%>
<section class="dashboard-section section-b-space">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="dashboard-sidebar">
                    <div class="profile-top">
                        <div class="profile-image">
                            <img src="<c:url value="/resources/images/logos/17.png"/>" alt="" class="img-fluid">
                        </div>
                        <div class="profile-detail">
                            <h5>Tài khoản của bạn</h5>
                        </div>
                    </div>
                    <div class="faq-tab">
                        <ul class="nav nav-tabs" id="top-tab" role="tablist">
                            <li class="nav-item"><a data-toggle="tab" class="nav-link active" href="#profile">profile</a>
                            </li>
                            <li class="nav-item"><a data-toggle="tab" class="nav-link" href="#orders">orders</a>
                            </li>
                            <li class="nav-item"><a data-toggle="tab" class="nav-link" href="#wishlists">wishlists</a>
                            </li>
                            <li class="nav-item"><a data-toggle="tab" class="nav-link" href="#settings">settings</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-lg-9">
                <div class="faq-content tab-content" id="top-tabContent">
                    <div class="tab-pane fade" id="orders">
                        <div class="row">
                            <div class="col-12">
                                <div class="card dashboard-table mt-0">
                                    <div class="card-body" id="content_order">
                                        <table class="table table-responsive-sm mb-0">
                                            <thead>
                                            <tr>
                                                <th scope="col">order id</th>
                                                <th scope="col">product details</th>
                                                <th scope="col">Price</th>
                                                <th scope="col">Quantity</th>
                                                <th scope="col">Shipping method</th>
                                                <th scope="col">Order method</th>
                                                <th scope="col">total</th>
                                            </tr>
                                            </thead>
                                            <tbody id="tbody">

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane active" id="profile">
                        <div class="row">
                            <div class="col-12">
                                <div class="card mt-0">
                                    <div class="card-body">
                                        <div class="dashboard-box">
                                            <div class="dashboard-title">
                                                <h4>Thông tin cá nhân</h4>
                                                <span data-toggle="modal" data-target="#edit-profile">edit</span>
                                            </div>
                                            <div class="dashboard-detail">
                                                <security:authorize access="isAuthenticated()">
                                                    <ul>
                                                        <li>
                                                            <div class="details">
                                                                <div class="left">
                                                                    <h6>Tên</h6>
                                                                </div>
                                                                <div class="right">
                                                                    <h6><security:authentication property="principal.name"></security:authentication></h6>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li>
                                                            <div class="details">
                                                                <div class="left">
                                                                    <h6>Địa chỉ mail</h6>
                                                                </div>
                                                                <div class="right">
                                                                    <h6><security:authentication property="principal.email"></security:authentication></h6>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li>
                                                            <div class="details">
                                                                <div class="left">
                                                                    <h6>Địa chỉ</h6>
                                                                </div>
                                                                <div class="right">
                                                                    <h6><security:authentication property="principal.address"></security:authentication></h6>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li>
                                                            <div class="details">
                                                                <div class="left">
                                                                    <h6>Số điện thoại</h6>
                                                                </div>
                                                                <div class="right">
                                                                    <h6><security:authentication property="principal.phone"></security:authentication></h6>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </security:authorize>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="wishlists">
                        <div class="row">
                            <div class="col-12">
                                <table class="table cart-table table-responsive-xs">
                                    <thead>
                                    <tr class="table-head">
                                        <th scope="col">image</th>
                                        <th scope="col">product name</th>
                                        <th scope="col">category</th>
                                        <th scope="col">price</th>
                                        <th scope="col">action</th>
                                    </tr>
                                    </thead>
                                    <tbody id="wishlist">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    $(document).ready(function () {
       $.ajax({
           url:ctx+"/customers/"+customer_id+"/orders",
           method:"GET",
           success:function (response) {
               if(response.length===0){
                   $("#content_order").html(`<h2>Bạn chưa có đơn hàng nào</h2>`);
               }else{
                   response.forEach(r=>{
                        $("#tbody").append(`
                            <tr>
                                <th scope="row">#\${r.orderClientProjection.id}\</th>
                                <td id="order\${r.orderClientProjection.id}\"></td>
                                <td id="price\${r.orderClientProjection.id}\"></td>
                                <td id="quantity\${r.orderClientProjection.id}\"></td>
                                <td>\${r.orderClientProjection.shippingStatus}\</td>
                                <td>\${r.orderClientProjection.orderStatus}\</td>
                                <td>`+new Intl.NumberFormat('en-US',{ style: 'currency', currency: r.orderClientProjection.currency }).format(parseFloat(r.orderClientProjection.total))+`</td>
                            </tr>
                        `);
                        r.list.forEach(e=>{
                            $("#order"+r.orderClientProjection.id).append(`
                                <span>\${e.name}\</span><br>
                            `);
                        });
                       r.list.forEach(e=>{
                           $("#price"+r.orderClientProjection.id).append(`
                                <span>`+new Intl.NumberFormat('en-US',{ style: 'currency', currency: r.orderClientProjection.currency }).format(parseFloat(e.price))+`</span><br>
                            `);
                       });
                       r.list.forEach(e=>{
                           $("#quantity"+r.orderClientProjection.id).append(`
                                <span>\${e.quantity}\</span><br>
                            `);
                       });
                   });
               }
           },
           error:function (error) {
                console.log(error)
           }
       });
       getWishlist();
    });
    window.onload = function () {
        $(document).on('click','.removeWishList',function (event) {
            event.preventDefault();
            if(confirm("Bạn có chắc không ?")){
                $.ajax({
                    url:ctx+"/wishlists/"+$(this).attr("id"),
                    method:"DELETE",
                    success:function (response) {
                        sweetAlert(response.status,response.data);
                        getWishlist();
                    },
                    error:function (error) {
                        sweetAlert(error.responseJSON.status,error.responseJSON.data);
                    }
                })
            }else{
               return false;
            }
        });
    }
    function getWishlist() {
        $.ajax({
            url:ctx+"/customers/"+customer_id+"/wishlists",
            method:"GET",
            success:function (response) {
                if(response.length===0){
                    $("#wishlist").html("<h3>Chưa có sản phẩm nào</h3>");
                }else{
                    $("#wishlist").html("");
                    response.forEach(r=>{
                        $("#wishlist").append(`
                    <tr>
                        <td>
                            <a href="#"><img width="50px" src="`+ctx+'/resources/images/pro3/'+r.image+`" alt=""></a>
                        </td>
                        <td><a href="#">\${r.name}\</a></td>
                        <td><p>\${r.categoryName}\</p></td>
                        <td><h2>
                            `+new Intl.NumberFormat('en-US',{ style: 'currency', currency: symbol.code }).format(Math.round(parseFloat(symbol.rate !== 1 ? r.priceFrom/symbol.rate/1000 : r.priceFrom/symbol.rate)))+`-
                            `+new Intl.NumberFormat('en-US',{ style: 'currency', currency: symbol.code }).format(Math.round(parseFloat(symbol.rate !== 1 ? r.priceTo/symbol.rate/1000 : r.priceTo/symbol.rate)))+`
                        </h2></td>
                        <td><a href="#" id="\${r.id}\" class="removeWishList icon mr-3"><i class="ti-close"></i> </a></td>
                    </tr>
                  `);
                    })
                }
            },
            error:function (error) {
                console.log(error);
            }
        });
    }
</script>