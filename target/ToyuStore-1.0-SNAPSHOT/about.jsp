<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <c:set var="title" value="About" scope="request"/>
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
                                    <li class="nav-item active">
                                        <a class="nav-link" href="about.jsp"> About</a>
                                    </li>
                                    <li class="nav-item">
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

        <!-- about section -->

        <section class="about_section">
            <div class="container-fluid  ">
                <div class="row">
                    <div class="col-md-5 ml-auto">
                        <div class="detail-box pr-md-3">
                            <div class="heading_container">
                                <h2>
                                    We Provide Best For You
                                </h2>
                            </div>
                            <p>
                                Welcome to our online store, where we offer a wide range of anime character figures, collectibles, and games for fans of all ages.

                                Our store is dedicated to providing high-quality and authentic anime merchandise that is sure to impress even the most discerning fans. We have a large selection of figures from popular anime shows, including Naruto, Dragon Ball, Sailor Moon, One Piece, and many more. Whether you're looking for a rare collector's item or just want to add to your collection, we have something for everyone.

                                In addition to our vast collection of anime figures, we also offer a variety of collectibles, such as keychains, plushies, and cosplay accessories. We understand that fans want to immerse themselves in their favorite anime worlds, and our merchandise allows them to do just that.

                                Our online store also features a wide range of anime-related games, including trading card games, board games, and video games. We strive to offer the latest and most popular anime games, so fans can stay up to date with their favorite shows.

                                We're proud to say that our merchandise appeals to fans of all ages, whether you're a young child just getting into anime or an adult collector. We believe that everyone should be able to enjoy anime merchandise, and our store reflects that philosophy.

                                We also understand that Western audiences have their own unique tastes and interests when it comes to anime, and we offer a selection of figures and merchandise that reflect those interests. Whether you're a fan of Western adaptations of anime, such as Avatar: The Last Airbender, or are interested in Western-inspired anime, such as RWBY, we have something for you.

                                Thank you for visiting our online store, and we hope you find the perfect anime merchandise to add to your collection</p>
                            <a href="">
                                Read More
                            </a>
                        </div>
                    </div>
                    <div class="col-md-6 px-0">
                        <div class="img-box">
                            <img src="images/about-img.jpg" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- end about section -->

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