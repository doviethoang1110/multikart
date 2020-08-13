<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 4/2/20
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<section class="section-b-space">
    <div class="container">
        <div class="checkout-page">
            <div class="checkout-form">
                <form method="post" id="checkout">
                    <div class="row">
                        <div class="col-lg-6 col-sm-12 col-xs-12">
                            <div class="checkout-title">
                                <h3>Billing Details</h3>
                            </div>
                            <div class="row check-out">
                                <div class="form-group col-md-6 col-sm-6 col-xs-12">
                                    <div class="field-label">Tên người mua</div>
                                    <input type="hidden" name="f_id" value="<security:authentication property="principal.id" />" readonly disabled>
                                    <input type="text" name="f_name" value="<security:authentication property="principal.name" />" readonly disabled>
                                </div>
                                <div class="form-group col-md-6 col-sm-6 col-xs-12">
                                    <div class="field-label">Số điện thoại</div>
                                    <input type="text" name="f_phone" value="<security:authentication property="principal.phone" />" readonly disabled>
                                </div>
                                <div class="form-group col-md-6 col-sm-6 col-xs-12">
                                    <div class="field-label">Địa chỉ mail</div>
                                    <input type="text" name="f_email" value="<security:authentication property="principal.email" />" readonly disabled>
                                </div>
                                <div class="form-group col-md-6 col-sm-6 col-xs-12">
                                    <div class="field-label">Địa chỉ</div>
                                    <input type="text" name="f_address" value="<security:authentication property="principal.address" />" readonly disabled>
                                </div>
                                <div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <input type="checkbox" name="shipping-option" id="account-option"> &ensp;
                                    <label for="account-option">Địa chỉ người nhận</label>
                                </div>
                            </div>
                            <div class="row check-out" style="margin-top: 60px">
                                <div class="form-group col-md-6 col-sm-6 col-xs-12">
                                    <div class="field-label">Tên người nhận</div>
                                    <input type="text" name="name" id="name" value="" placeholder="Tên người mua">
                                </div>
                                <div class="form-group col-md-6 col-sm-6 col-xs-12">
                                    <div class="field-label">Số điện thoại người nhận</div>
                                    <input type="text" name="phone" id="phone" value="" placeholder="Số điện thoại">
                                </div>
                                <div class="form-group col-md-6 col-sm-6 col-xs-12">
                                    <div class="field-label">Địa chỉ mail người nhận</div>
                                    <input type="email" name="email" id="email" value="" placeholder="Địa chỉ mail">
                                </div>
                                <div class="form-group col-md-6 col-sm-6 col-xs-12">
                                    <div class="field-label">Địa chỉ người nhận</div>
                                    <input type="text" name="address" id="address" value="" placeholder="Địa chỉ">
                                </div>
                                <div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="field-label">Order Note</div>
                                    <textarea id="note" style="height: 170px" placeholder="Notes about your order, e.g. special notes for delivery."></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-sm-12 col-xs-12">
                            <div class="checkout-details">
                                <div class="order-box">
                                    <div class="title-box">
                                        <div>Product <span>Total</span></div>
                                    </div>
                                    <ul class="qty"  style="height: 100px;overflow-y: auto">

                                    </ul>
                                    <div class="form-group">
                                        <label for="coupon" class="sr-only">Mã giảm giá</label>
                                        <input type="text" class="form-control col-lg-8" id="coupon" placeholder="Mã giảm giá">
                                    </div>
                                    <a id="useCoupon" class="btn btn-primary col-lg-4">Sử dụng</a>
                                    <a class="btn btn-warning col-lg-4" id="listCoupon">Coupon của bạn</a>
                                    <span id="showCoupon" class="text-success"></span>
                                    <hr>
                                    <ul class="sub-total">
                                        <input type="hidden" value="" id="subtotal"/>
                                        <li>Subtotal <span class="count"></span></li>
                                        <li id="shipping">Shipping
                                            <div class="shipping">

                                            </div>
                                        </li>
                                        <div id="append"></div>
                                    </ul>
                                    <ul class="total">
                                        <input type="hidden" value="" id="totalValue"/>
                                        <li>Total <span class="count" id="count"></span></li>
                                    </ul>
                                </div>
                                <div class="payment-box">
                                    <div class="upper-box">
                                        <div class="payment-options">
                                            <ul>

                                            </ul>
                                        </div>
                                    </div>
                                    <div class="text-right">
                                        <button type="submit" id="btn-submit" class="btn-solid btn">
                                            <i id="loading" class="fa fa-spinner fa-spin" hidden></i> Place Order
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<div class="modal fade bd-example-modal-lg" id="openCoupons" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Code</th>
                    <th scope="col">Description</th>
                </tr>
                </thead>
                <tbody id="modal-coupon">

                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    let subTotal = calTotal() + parseInt(0);
    const shippings = [
        {
            id:1,
            name:"Basic shipping",
            fee:0
        },
        {
            id:2,
            name:"Fast shipping",
            fee:20000
        },
        {
            id:3,
            name:"Air shippinng",
            fee:50000
        }
    ];
    const payments = [
        {
            id:1,
            name:"Cash on deliver"
        },
        {
            id:2,
            name:"Paypal"
        }
    ];
    $(document).ready(function () {
        if(listCart===null||listCart.length===0){
            $("div.checkout-page").html(`
                <div class="jumbotron">
                        <h2>Bạn chưa mua sản phẩm nào</h2>
                </div>
            `);
        }else{
            $("ul.qty").html("");
            listCart.forEach(cart=>{
                $("ul.qty").append(`
                    <li>\${cart.name}\ × \${cart.quantity}\ <span>`+new Intl.NumberFormat('en-US', {style: 'currency', currency: symbol.code}).format(parseFloat(symbol.rate !== 1 ? cart.quantity*cart.price/symbol.rate/1000 : cart.quantity*cart.price/symbol.rate))+`</span></li>
                `);
            });
            $("ul.sub-total li .count").text(new Intl.NumberFormat('en-US', {style: 'currency', currency: symbol.code}).format(parseFloat(symbol.rate !== 1 ? calTotal()/symbol.rate/1000 : calTotal()/symbol.rate)));
            $("#subtotal").val(parseFloat(symbol.rate !== 1 ? calTotal()/symbol.rate/1000 : calTotal()/symbol.rate).toFixed(2));
            $("#account-option").click(function (event) {
                var check = document.getElementById("account-option").checked;
                if(check){
                    $('input[name="name"]').val($('input[name="f_name"]').val());
                    $('input[name="email"]').val($('input[name="f_email"]').val());
                    $('input[name="address"]').val($('input[name="f_address"]').val());
                    $('input[name="phone"]').val($('input[name="f_phone"]').val());
                }
                else {
                    $('input[name="name"]').val('');
                    $('input[name="email"]').val('');
                    $('input[name="address"]').val('');
                    $('input[name="phone"]').val('');
                }
            });
            shippings.forEach(ship=>{
                $("div.shipping").append(`
                    <div class="shopping-option">
                        <input content="\${ship.name}\" type="radio" name="delivery" value="`+(symbol.rate !== 1 ? ship.fee/symbol.rate/1000 : ship.fee/symbol.rate).toFixed(2)+`" id="ship\${ship.id}\" class="delivery">
                        <label for="ship\${ship.id}\">\${ship.name}\</label><br>
                `);
                if(parseInt(ship.fee)>0){
                    $("div.shipping").append(`
                        <span>Mô tả : `+new Intl.NumberFormat('en-US', {style: 'currency', currency: symbol.code}).format(parseFloat(symbol.rate !== 1 ? ship.fee/symbol.rate/1000 : ship.fee/symbol.rate))+`</span>
                    `);
                }else{
                    $("div.shipping").append(`
                        <span>Mô tả : Free</span>
                    `);
                }
                $("div.shipping").append(`
                    </div>
                `);
            });
            payments.forEach(pay=>{
                $("div.payment-options ul").append(`
                    <li>
                        <div class="radio-option">
                            <input content="\${pay.name}\" type="radio" value="\${pay.id}\" name="payment-group" id="payment-\${pay.id}\">
                            <label for="payment-\${pay.id}\">\${pay.name}\</label>
                        </div>
                    </li>
                `);
            })
            $("#ship1").prop("checked",true);
            $("#payment-1").prop("checked",true);
            $("#count").text(new Intl.NumberFormat('en-US', {style: 'currency', currency: symbol.code}).format(parseFloat(symbol.rate !== 1 ? subTotal/symbol.rate/1000 : subTotal/symbol.rate)));
            $("#totalValue").val(parseFloat(symbol.rate !== 1 ? subTotal/symbol.rate/1000 : subTotal/symbol.rate).toFixed(2));
            $(document).on('change','input[name="delivery"]',function (event) {
                subTotal = 0;
                subTotal = parseFloat(symbol.rate !== 1 ? calTotal()/symbol.rate/1000 : calTotal()/symbol.rate) + parseFloat($(this).val());
                $("#count").text(new Intl.NumberFormat('en-US', {style: 'currency', currency: symbol.code}).format(subTotal));
                $("#totalValue").val(subTotal.toFixed(2));
            })
        }
    });
    $("#useCoupon").click(function (event) {
        event.preventDefault();
       $.ajax({
          url:"${pageContext.request.contextPath}/getCoupon?coupon="+$("#coupon").val()+"&userId="+<security:authentication property="principal.id"/>,
          method:"GET",
          success:function (response) {
            if(response===""){
                alert("Mã giảm giá không tồn tài hoặc đã hết hạn");
            }else{
                $("#shipping").hide();
                switch (response.type) {
                    case 1:
                        $("#showCoupon").text("Free shipping");
                        $("#count").text(new Intl.NumberFormat('en-US', {style: 'currency', currency: symbol.code}).format(parseFloat(calTotal())));
                        $("#totalValue").val(parseFloat(calTotal()).toFixed(2));
                        $("#append").html("");
                        break;
                    case 2:
                        $("#showCoupon").text("Flash sale "+new Intl.NumberFormat('en-US', {style: 'currency', currency: symbol.code}).format(parseFloat(symbol.rate !== 1 ? response.detail/symbol.rate/1000 : response.detail/symbol.rate)));
                        $("#append").html(`
                           <li>
                                Flash sale
                                <span class="count"> - `+new Intl.NumberFormat('en-US', {style: 'currency', currency: symbol.code}).format(parseFloat(symbol.rate !== 1 ? response.detail/symbol.rate/1000 : response.detail/symbol.rate))+`</span>
                           </li>
                        `);
                        $("#count").text(new Intl.NumberFormat('en-US', {style: 'currency', currency: symbol.code}).format(parseFloat(symbol.rate !== 1 ? (calTotal()-response.detail)/symbol.rate/1000 : (calTotal()-response.detail)/symbol.rate)));
                        $("#totalValue").val(parseFloat(symbol.rate !== 1 ? (calTotal()-response.detail)/symbol.rate/1000 : (calTotal()-response.detail)/symbol.rate).toFixed(2))
                        break;
                    case 3:
                        $("#showCoupon").text("Discount "+response.detail+"% in total");
                        $("#append").html(`
                           <li>
                                Discount
                                <span class="count"> - `+response.detail+`%</span>
                           </li>
                        `);
                        $("#count").text(new Intl.NumberFormat('en-US', {style: 'currency', currency: symbol.code}).format(parseFloat(symbol.rate !== 1 ? (calTotal()-(calTotal()*response.detail)/100)/symbol.rate/1000 : ((calTotal()-(calTotal()*response.detail)/100))/symbol.rate)));
                        $("#totalValue").val(parseFloat(symbol.rate !== 1 ? (calTotal()-(calTotal()*response.detail)/100)/symbol.rate/1000 : ((calTotal()-(calTotal()*response.detail)/100))/symbol.rate).toFixed(2));
                        break;
                }
            }
          },
          error:function (error) {
            console.log(error);
          }
       });
    });
    $("#listCoupon").click(function (event) {
        event.preventDefault();
        $.ajax({
            url:"${pageContext.request.contextPath}/listCoupon?userId="+<security:authentication property="principal.id"/>,
            method:"GET",
            success:function (response) {
                $("#modal-coupon").html("");
                response.forEach(r=>{
                    let a = "";
                    let className = "";
                    switch (r.type) {
                        case 1:
                            a = "Free shipping";
                            className = "text-primary";
                            break;
                        case 2:
                            a = "Flash sale "+new Intl.NumberFormat('en-US', {style: 'currency', currency: symbol.code}).format(parseFloat(symbol.rate !== 1 ? r.detail/symbol.rate/1000 : r.detail));
                            className = "text-success";
                            break;
                        case 3:
                            a = "Discount "+r.detail+"%";
                            className = "text-warning";
                            break;
                    }
                    $("#modal-coupon").append(`
                        <tr>
                            <th>\${r.code}\</th>
                            <td class="\${className}\">\${a}\</td>
                        </tr>
                    `);
                });
                $("#openCoupons").modal("show");
            },
            error:function (error) {
                console.log(error);
            }
        });
    });
    // let loading = false;
    $("#checkout").submit(function (event) {
        // loading = true;
        // $("#btn-submit").prop("disabled",loading === true ? true : false);
        // if(loading === true){
        //     $("#btn-submit").append(`
        //         <i class="fa fa-spinner fa-spin"></i>
        //     `);
        // }
        event.preventDefault();
        if(parseInt(symbol.id)!==2&&parseInt($("input[name='payment-group']:checked").val())===2){
            alert("Mệnh giá VND không phù hợp với phương thức thanh toán này");
        }else{
            let order = {
                customerId:parseInt($("input[name='f_id']").val()),
                name:$("#name").val(),
                email:$("#email").val(),
                phone:$("#phone").val(),
                address:$("#address").val(),
                currency:symbol.code,
                rate:symbol.rate,
                note:$("#note").val(),
                subtotal:$("#subtotal").val(),
                shipping:$("#showCoupon").text() !== "" ? 0 : parseInt($("input[name='delivery']:checked").val()),
                coupon:$("#showCoupon").text() !== "" ? $("#coupon").val() : "",
                total:$("#totalValue").val(),
                shippingMethod:$("input[name='delivery']:checked").attr("content"),
                paymentMethod:$("input[name='payment-group']:checked").attr("content"),
                orderDetailRequests:listCart.map(c=>
                    {
                        return {
                            productId:c.productId,
                            sku:c.id,
                            quantity:c.quantity,
                            price:parseFloat(symbol.rate !== 1 ? c.price/symbol.rate/1000 : c.price).toFixed(2),
                            productName:c.name
                        }
                    }
                )
            }
            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                url:ctx+"/orders",
                method:"POST",
                data:JSON.stringify(order),
                dataType:"json",
                beforeSend: function(){
                    $("#loading").removeAttr("hidden");
                    $("#btn-submit").prop("disabled",true);
                },
                complete: function(){
                    $("#loading").hide();
                    $("#btn-submit").prop("disabled",false);
                },
                success:function (response) {
                    localStorage.removeItem("listCart");
                    Swal.fire({
                        text:response.data,
                        icon:response.status
                    }).then(()=>{location.reload()})
                },
                error:function (error) {
                    console.log(error);
                }
            })
        }
    });
</script>