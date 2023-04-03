<%-- 
    Document   : cart
    Created on : Feb 26, 2023, 5:14:54 PM
    Author     : phanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <c:set var="title" value="Cart" scope="request"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="header.jsp" /> 

        <title>Detail</title>
    </head>
    <body>
        <div class="hero_area">
            <!-- header section strats -->
            <header class="header_section">
                <jsp:include page="header-user.jsp" />
                <div class="header_bottom">
                    <div class="container-fluid">
                        <nav class="navbar navbar-expand-lg custom_nav-container ">
                            <a class="navbar-brand" href="index.jsp">
                                <span>
                                    Mistify
                                </span>
                            </a>

                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                <span class=""> </span>
                            </button>

                            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                <ul class="navbar-nav ">
                                    <li class="nav-item ">
                                        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="about.jsp"> About</a>
                                    </li>
                                    <li class="nav-item active">
                                        <a class="nav-link" href="product">Products</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="why.jsp">Why Us</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="testimonial.jsp">Testimonial</a>
                                    </li>
                                </ul>
                            </div>
                        </nav>
                    </div>
                </div>
            </header>
        </div>
        <!-- end header section code start here-->
        <c:if test="${empty sessionScope.cart}">
            <c:redirect url="login" />
        </c:if>    
        <!-- new template-->
        <div class="container px-3 my-5 clearfix">
            <!-- Shopping cart table -->
            <div class="card">
                <div class="card-header">
                    <h2>Shopping Cart</h2>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered m-0">
                            <thead>
                                <tr>
                                    <!-- Set columns width -->
                                    <th class="text-center py-3 px-4" style="min-width: 400px;">Product Name &amp; Details</th>
                                    <th class="text-right py-3 px-4" style="width: 100px;">Price</th>
                                    <th class="text-center py-3 px-4" style="width: 120px;">Quantity</th>
                                    <th class="text-right py-3 px-4" style="width: 100px;">Total</th>
                                    <th class="text-center align-middle py-3 px-0" style="width: 40px;"><a href="#" class="shop-tooltip float-none text-light" title="" data-original-title="Clear cart"><i class="ino ion-md-trash"></i></a></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.cart.cart}" var="item">
                                    <tr>
                                        <td class="p-4">
                                            <div class="media align-items-center">
                                                <img src="product/${item.getProductId()}.jpg" class="d-block ui-w-40 ui-bordered mr-4" alt="">
                                                <div class="media-body">
                                                    <a href="#" class="d-block text-dark">${item.product.name}</a>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="text-right font-weight-semibold align-middle p-4">$${item.product.price}</td>
                                        <!--<td class="align-middle p-4"><input type="text" class="form-control text-center" value="${item.quantity}" disabled><i class="fa fa-plus"></i></td>-->
                                        <td class="align-middle">
                                            <div class="d-flex flex-row">
                                                <div class="btn btn-link px-2">
                                                    <a class="fa fa-minus" href="cart?action=minus&id=${item.product.id}" ></a>
                                                </div>

                                                <input id="form1" min="0" name="quantity" value="${item.quantity}" type="text" disabled
                                                       class="form-control form-control-sm" style="width: 50px;" />

                                                <div class="btn btn-link px-2">
                                                    <a class="fa fa-plus" href="cart?action=add&id=${item.product.id}" ></a>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="text-right font-weight-semibold align-middle p-4">$${item.getPriceString()}</td>
                                        <td>
                                            <form class="form-button" action="cart" method="post">
                                                <input type="hidden" name="id" value="${item.getProductId()}">
                                                <input type="hidden" name="action" value="remove">
                                                <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <!-- / Shopping cart table -->

                    <div class="d-flex flex-wrap justify-content-between align-items-center pb-4">
                        <div class="mt-4">
                            <!--                            <label class="text-muted font-weight-normal">Promocode</label>
                                                        <input type="text" placeholder="ABC" class="form-control">-->
                        </div>
                        <div class="d-flex">
                            <div class="text-right mt-4 mr-5">
                                <!--                                <label class="text-muted font-weight-normal m-0">Discount</label>
                                                                <div class="text-large"><strong>$20</strong></div>-->
                            </div>
                            <div class="text-right mt-4">
                                <!--                                <label class="text-muted font-weight-normal m-0">Total price</label>
                                                                <div class="text-large"><strong>$${sessionScope.cart.getPriceString()}</strong></div>-->
                            </div>
                        </div>
                    </div>

                    <div class="float-right">
                        <a class="btn btn-lg btn-default md-btn-flat mt-2 mr-3">Total: ${sessionScope.cart.getPriceString()}$</a>
                        <a href="product" class="btn btn-lg btn-default md-btn-flat mt-2 mr-3">Back to shopping</a>
                        <a href="cart?action=place" class="btn btn-lg btn-primary mt-2">Place Order</a>
                    </div>

                </div>
            </div>
        </div>


        <!-- new one end here-->

        <div class="container w-75 cart-box">
            <c:if test="${sessionScope.cart.cart.size() == 0}">
                <p>Yo your cart is empty!!</p>
            </c:if>
        </div>
        <!-- info section -->
        <section class="info_section ">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <div class="info_contact">
                            <h5>
                                <a href="" class="navbar-brand">
                                    <span>
                                        Mistify
                                    </span>
                                </a>
                            </h5>
                            <p>
                                <i class="fa fa-map-marker" aria-hidden="true"></i>
                                Address
                            </p>
                            <p>
                                <i class="fa fa-phone" aria-hidden="true"></i>
                                +01 1234567890
                            </p>
                            <p>
                                <i class="fa fa-envelope" aria-hidden="true"></i>
                                demo@gmail.com
                            </p>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="info_info">
                            <h5>
                                Information
                            </h5>
                            <p>
                                Eligendi sunt, provident, debitis nemo, facilis cupiditate velit libero dolorum aperiam enim nulla iste maxime corrupti ad illo libero minus.
                            </p>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="info_links">
                            <h5>
                                Useful Link
                            </h5>
                            <ul>
                                <li>
                                    <a href="index.jsp">
                                        Home
                                    </a>
                                </li>
                                <li>
                                    <a href="about.jsp">
                                        About
                                    </a>
                                </li>
                                <li>
                                    <a href="product.jsp">
                                        Products
                                    </a>
                                </li>
                                <li>
                                    <a href="why.jsp">
                                        Why Us
                                    </a>
                                </li>
                                <li>
                                    <a href="testimonial.jsp">
                                        Testimonial
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="info_form ">
                            <h5>
                                Newsletter
                            </h5>
                            <form action="">
                                <input type="email" placeholder="Enter your email">
                                <button>
                                    Subscribe
                                </button>
                            </form>
                            <div class="social_box">
                                <a href="">
                                    <i class="fa fa-facebook" aria-hidden="true"></i>
                                </a>
                                <a href="">
                                    <i class="fa fa-twitter" aria-hidden="true"></i>
                                </a>
                                <a href="">
                                    <i class="fa fa-instagram" aria-hidden="true"></i>
                                </a>
                                <a href="">
                                    <i class="fa fa-youtube" aria-hidden="true"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- end info_section -->


        <!-- footer section -->
        <footer class="footer_section">
            <div class="container">
                <p>
                    &copy; <span id="displayYear"></span> All Rights Reserved By
                    <a href="https://html.design/">Free Html Templates</a>
                </p>
            </div>
        </footer>
        <!-- footer section -->

        <!-- jQery -->
        <script src="js/jquery-3.4.1.min.js"></script>
        <!-- bootstrap js -->
        <script src="js/bootstrap.js"></script>
        <!-- custom js -->
        <script src="js/custom.js"></script>

    </body>
</html>
