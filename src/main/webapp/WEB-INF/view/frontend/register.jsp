<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 4/2/20
  Time: 8:40 PM
  To change this template use File | Settings | File Templates.
--%>
<section class="register-page section-b-space">
    <div class="container">
        <div class="row">
            <div class="col-lg-6" style="margin: 0 auto">
                <div id="result"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6" style="margin:0 auto">
                <h3>create account</h3>
                <div class="theme-card">
                    <form id="register" class="theme-form" method="post" action="#">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="name">Tên</label>
                                <input name="name" type="text" class="form-control" id="name" placeholder="Tên"/>
                            </div>
                            <div class="col-md-6">
                                <label for="email">Email</label>
                                <input name="email" type="email" class="form-control" id="email" placeholder="Email"/>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="address">Địa chỉ</label>
                                <input name="address" type="text" class="form-control" id="address" placeholder="Địa chỉ"/>
                            </div>
                            <div class="col-md-6">
                                <label for="address">Số điện thoại</label>
                                <input name="phone" type="text" class="form-control" id="phone" placeholder="Số điện thoại"/>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="password">Password</label>
                                <input type="password" name="password" class="form-control" id="password" placeholder="Enter your password"/>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-6">
                                <button id="btn-submit" type="submit" class="btn btn-solid"><i id="loading" class="fa fa-spinner fa-spin" hidden></i> create Account</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    $('#register').on('submit',function (event) {
        event.preventDefault();
        $("#register").validate({
            rules: {
                "name": {
                    required: true,
                    minlength: 10,
                    maxlength: 20
                },
                "email": {
                    required: true,
                    maxlength: 40,
                    email:true
                },
                "address": {
                    minlength:10,
                    required: true,
                },
                "phone":{
                    required:true,
                    minlength:10,
                    maxlength:11,
                },
                "password":{
                    required:true,
                    minlength:6,
                    maxlength:20
                }
            },
            messages:{
                "name":{
                    required:"Tên không được rỗng",
                    maxlength:"Tối đa 20 ký tự",
                    minlength:"Ít nhất 10 ký tự"
                },
                "email":{
                    required:"Email không được rỗng",
                    maxlength:"Tối đa 40 ký tự",
                    email:"Email không đúng định dạng"
                },
                "address":{
                    required:"Địa chỉ không được rỗng",
                    minlength:"Ít nhất 10 ký tự"
                },
                "phone":{
                    required:"Điện thoại không được rỗng",
                    maxlength:"Tối đa 11 ký tự",
                    minlength:"Tối đa 10 ký tự",
                },
                "password":{
                    required:"Mật khẩu không được rỗng",
                    maxlength:"Tối đa 20 ký tự",
                    minlength:"Ít nhất 6 ký tự"
                }
            },
            submitHandler: function () {
                let customer = {
                    id:null,
                    name:$("#name").val(),
                    email:$("#email").val(),
                    password:$("#password").val(),
                    address:$("#address").val(),
                    phone:$("#phone").val()
                };
                $.ajax({
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    url:ctx+"/dang-ky",
                    method: "POST",
                    data:JSON.stringify(customer),
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
                        $("#result").html("");
                        $("#register")[0].reset();
                        Swal.fire({
                            text:response.data+" ,vui lòng xác thực email",
                            icon:response.status
                        }).then(()=>{window.location.reload()})
                    },
                    error:function (error) {
                        let html = "";
                        html += "<div class='alert alert-danger'>";
                        for (let i = 0; i < error.responseJSON.data.length; i++) {
                            html += "<p>"+error.responseJSON.data[i]+"</p>";
                        }
                        html += "</div>";
                        $("#result").html(html);
                    }
                });
            }
        });
    });
</script>