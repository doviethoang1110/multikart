<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 4/2/20
  Time: 8:42 PM
  To change this template use File | Settings | File Templates.
--%>
<section class="cart-section section-b-space">
    <div class="container" id="cartView">

    </div>
</section>
<script>
    $(document).ready(function () {
        if(listCart===null||listCart.length===0){
            $("#cartView").html("<h2>Chưa có sản phẩm nào</h2>");
        }else{
            $("#cartView").html("");
            $("#cartView").append(`
                <div class="row">
                    <div class="col-sm-12" style="height: 400px;overflow-y: auto">
                        <table class="table cart-table table-responsive-xs">
                            <thead>
                                <tr class="table-head">
                                    <th scope="col">image</th>
                                    <th scope="col">product name</th>
                                    <th scope="col">price</th>
                                    <th scope="col">quantity</th>
                                    <th scope="col">total</th>
                                    <th scope="col">action</th>
                                </tr>
                            </thead>
            `);
            listCart.forEach(cart=>{
                $("table.table-responsive-xs").append(`
                <tbody content="\${cart.id}\">
                            <tr>
                                <td>
                                    <a href="#"><img src="\${cart.image}\" alt=""></a>
                                </td>
                                <td><a href="#">\${cart.name}\</a></td>
                                <td>
                                    <h2 id="\${cart.id}\"></h2>
                                </td>
                                <td>
                                    <div class="qty-box">
                                        <div class="input-group">
                                            <input type="number" name="quantity" class="form-control input-number" content="\${cart.id}\" value="\${cart.quantity}\">
                                        </div>
                                        <span class="text-danger" content="\${cart.id}\"></span>
                                    </div>
                                </td>
                                <td>
                                    <h2 class="td-color \${cart.id}\"></h2>
                                </td>
                                <td><a href="#" content="\${cart.id}\" class="icon"><i class="ti-close"></i></a></td>
                            </tr>
                        </tbody>
                `);
                $('#'+cart.id).text(new Intl.NumberFormat('en-US',{ style: 'currency', currency: symbol.code }).format(parseFloat(symbol.rate !==1 ? cart.price/symbol.rate/1000 : cart.price/symbol.rate)));
                $("."+cart.id).text(new Intl.NumberFormat('en-US',{ style: 'currency', currency: symbol.code }).format(parseFloat(symbol.rate !== 1 ? cart.price*cart.quantity/symbol.rate/1000 : cart.price*cart.quantity/symbol.rate)));
            });
            $("#cartView").append(`
                    <table class="table cart-table table-responsive-md">
                            <tfoot>
                                <tr>
                                    <td>total price :</td>
                                    <td>
                                        <h2 id="total"></h2>
                                    </td>
                                </tr>
                            </tfoot>
                    </table>
                </div>
            </div>
            `);
            $("#total").text(new Intl.NumberFormat('en-US',{ style: 'currency', currency: symbol.code }).format(parseFloat(symbol.rate !== 1 ? calTotal()/symbol.rate/1000 : calTotal()/symbol.rate)));
            $("#cartView").append(`
                <div class="row cart-buttons">
                    <div class="col-6"><a href="#" class="btn btn-solid">continue shopping</a></div>
                    <div class="col-6"><a href="${pageContext.request.contextPath}/thanh-toan" class="btn btn-solid">check out</a></div>
                </div>
            `);
        }
    });
    $(document).on("change","input[name='quantity']",function (event) {
        event.preventDefault();
        let cart = listCart.find(c => c.id === $(this).attr("content"));
        if(parseInt($(this).val())<1){
            $("span[content='"+cart.id+"'").text("Tối thiểu 1");
            return false;
        }else {
            $("span[content='"+cart.id+"'").text("");
            cart.quantity = parseInt($(this).val());
            $("." + cart.id).text(new Intl.NumberFormat('en-US', {
                style: 'currency',
                currency: symbol.code
            }).format(parseFloat(symbol.rate !== 1 ? cart.price * cart.quantity/symbol.rate/1000 : cart.price * cart.quantity/symbol.rate)));
            getCartNumber();
            addTopCart();
            $("#total").text(new Intl.NumberFormat('en-US', {style: 'currency', currency: symbol.code}).format(parseFloat(symbol.rate !==1 ? calTotal()/symbol.rate/1000 : calTotal()/symbol.rate)));
            localStorage.setItem("listCart", JSON.stringify(listCart));
        }
    });
    $(document).on('click','a.icon',function (event) {
        event.preventDefault();
        removeCart($(this).attr("content"));
    });
</script>