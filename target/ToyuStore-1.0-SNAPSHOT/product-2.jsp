<%-- 
    Document   : product-2
    Created on : Apr 4, 2023, 12:05:01 AM
    Author     : phanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="header.jsp" /> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                                        <a class="nav-link" href="Product">Products</a>
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
        <div class="product-container">
            <div class="row">
                <div class="col-md-2">
                    <div class="card">
                        <div class="card-header">
                            <i class="fa fa-chevron-down mr-2"></i>Product Types
                        </div>
                        <ul class="list-group list-group-flush">
                            <c:forEach items="${typeList}" var="type">
                                <li class="list-group-item"><a href="Search?type=type&&filter=${type.id}">${type.name}(${type.quantity})</a>  </li>
                            </c:forEach>
                        </ul>
                    </div>
                    
                </div>
                <div class="col-md-10">
                    <div class="card">
                        <div class="card-header">Products</div>
                        <div class="card-body product_section">
                            <div class="row">
                                <c:forEach items="${productList}" var="o" begin="${start}" end="${end}">
                                    <div class="col-sm-6 col-lg-3">
                                        <div class="box">
                                            <div class="img-box">
                                                <img src="product/${o.id}.jpg" alt="">
                                                <a href="cart?action=add&id=${o.id}" class="add_cart_btn">
                                                    <span>
                                                        Add To Cart
                                                    </span>
                                                </a>
                                            </div>
                                            <div class="detail-box">
                                                <a href="product?id=${o.id}"><h5>${o.name}</h5></a>
                                                <div class="product_info">
                                                    <h5>
                                                        <span>$</span> ${o.price}
                                                    </h5>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
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
                                        Minics
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
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <script>
            $(document).ready(function () {
                // Hide the list by default
                $("#product-types-list").hide();

                // Toggle the list when the header is clicked
                $("#product-types-header").click(function () {
                    $("#product-types-list").slideToggle();
                });
            });
        </script>

        <!-- bootstrap js -->
        <script src="js/bootstrap.js"></script>
        <!-- custom js -->
        <script src="js/custom.js"></script>

    </body>
</html>
