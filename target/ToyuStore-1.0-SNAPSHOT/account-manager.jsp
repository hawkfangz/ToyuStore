<%-- 
    Document   : account-manager
    Created on : Mar 14, 2023, 7:31:26 PM
    Author     : phanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
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
        <div class="container">
            <div class="table-responsive text-nowrap">
                <!--Table-->
                <table class="table table-striped">

                    <!--Table head-->
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Account</th>
                            <th>Phone</th>
                            <th>Order places</th>
                            <th>Email</th>
                            <th>Address</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <!--Table head-->

                    <!--Table body-->
                    <tbody>
                        <c:forEach items="${allAccounts}" var="account">
                            <tr>
                                <th scope="row">${account.customerId}</th>
                                <td>${account.account}</td>
                                <td>${account.phone}</td>
                                <td>${account.orders.size()}</td>   
                                <td>${account.email}</td>
                                <td>${account.address}</td>
                                <c:if test="${account.status == 1}">
                                    <td><a class="btn btn-danger" href="admin?action=toggle&id=${account.customerId}">Send to Shadow Realm</a></td>                                    
                                </c:if>
                                <c:if test="${account.status == 0}">
                                    <td><a class="btn btn-primary" href="admin?action=toggle&id=${account.customerId}">Out of Shadow Realm</a></td>                                    
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <!--Table body-->


                </table>
                <!--Table-->
            </div>
        </section>
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
