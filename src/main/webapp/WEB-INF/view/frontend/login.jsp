<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 4/2/20
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<section class="login-page section-b-space">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <h3>Login</h3>
                <div id="check_login"></div>
                <div class="theme-card">
                    <form class="theme-form" method="post" action="${pageContext.request.contextPath}/login">
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="text" class="form-control" id="email" placeholder="Email" name="email">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" placeholder="Enter your password" name="password">
                        </div>
                        <button  type="submit" class="btn btn-solid">Login</button>
                        <c:if test="${not empty error}">
                            <span class="alert alert-danger">${error}</span>
                        </c:if>
                    </form>
                </div>
            </div>
            <div class="col-lg-6 right-login">
                <h3>New Customer</h3>
                <div class="theme-card authentication-right">
                    <h6 class="title-font">Create A Account</h6>
                    <p>Sign up for a free account at our store. Registration is quick and easy. It allows you to be
                        able to order from our shop. To start shopping click register.</p><a href="#" class="btn btn-solid">Create an Account</a>
                </div>
            </div>
        </div>
    </div>
</section>