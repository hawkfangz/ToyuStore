<%-- 
    Document   : product.jsp
    Created on : Feb 1, 2023, 6:40:53 AM
    Author     : phanh
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="title" scope="session" value="Product"/>
    <jsp:include page="header.jsp" /> 

    <body class="sub_page">

        <div class="hero_area">
            <!-- header section strats -->
            <header class="header_section">
                <jsp:include page="header-user.jsp" />
                <div class="header_bottom">
                    <div class="container-fluid">
                        <nav class="navbar navbar-expand-lg custom_nav-container ">
                            <a class="navbar-brand" href="index.jsp">
                                <span>
                                    Minics
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
            <!-- end header section -->
        </div>


        <!-- product section -->

        <section class="product_section layout_padding">
            <div class="container">
                <div class="heading_container heading_center">
                    <h2>
                        Our Products
                    </h2>
                </div>
                <c:if test="${sessionScope.admin != null}">
                    <a class="btn btn-primary" href="AdminProduct?action=create">Create</a>
                    <c:forEach items="${allProductList}" var="o" begin="${start}" end="${end}">
                        <div class="row">
                            <div class="product-detail media">
                                <div class="img-container">
                                    <img class="align-self-start mr-3" src="product/${o.id}.jpg" onerror="this.src='product/undefined.png';">
                                </div>
                                <div class="media-body">
                                    <h5 class="mt-0">${o.name}</h5>
                                    <p>Price: ${o.price}</p>
                                    <p>Desciption: ${o.des}</p>
                                    <p>
                                        <a class="btn btn-primary" href="AdminProduct?action=edit&id=${o.id}">Edit</a>
                                        <c:if test="${o.status == 1}">
                                            <a class="btn btn-danger" href="AdminProduct?action=toggle&id=${o.id}">Disable</a>
                                        </c:if>
                                        <c:if test="${o.status == 0}">
                                            <a class="btn btn-success " href="AdminProduct?action=toggle&id=${o.id}">Enable</a>
                                        </c:if>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${sessionScope.admin == null}">
                    <div class="row">
                        <c:forEach items="${productList}" var="o" begin="${start}" end="${end}">
                            <div class="col-sm-6 col-lg-4">
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
                    <c:set var="productList" value="${requestScope.productList}" />
                </c:if>

                <div class="btn_box">
                    <c:forEach var="i" begin="1" end="${pages}">
                        <a href="product?page=${i}" class="view_more-link">
                            ${i}
                        </a>
                    </c:forEach>
                </div>
            </div>
        </section>

        <!-- end product section -->


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
                                The site launched around March 2023 since the author can't find good official source to buy collectibles so he himself try to become one.
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