<%-- 
    Document   : signup
    Created on : Mar 1, 2023, 9:24:06 AM
    Author     : hawk
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:set var="title" value="Sign Up" scope="request"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="header.jsp" /> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
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
        </div>
        <!-- end header section -->
        <div class="container mt-lg-5 login-form">
            <div class="row">
                <div class="col-md-6 mx-auto">
                    <div class="card">
                        <div class="card-header">
                            Sign Up
                        </div>
                        <div class="card-body">
                            <form method="POST" action="user">
                                <input type="hidden" id="action"name="action" value="signup">

                                <div class="form-group">
                                    <label for="account">Account</label>
                                    <input name ="account" type="text" class="form-control" id="account" placeholder="Enter Account">
                                </div>
                                <div class="form-group">
                                    <label for="password">Password:</label>
                                    <input class="form-control" type="password" name="password" id="password" required>

                                    <label for="confirmPassword">Confirm Password:</label>
                                    <input class="form-control" type="password" name="confirmPassword" id="confirmPassword" required onkeyup='checkPasswordMatch();'>

                                    <div id="passwordError" style="color:red;"></div>
                                </div>
                                <div class="form-group">
                                    <label for="account">Customer Name:</label>
                                    <input name ="name" type="text" class="form-control" id="account" placeholder="Enter Account">
                                </div>


                                <div class="form-group">
                                    <label for="email">Email Address</label>
                                    <input name ="email" type="email" class="form-control" id="email" placeholder="haupro@example.com">
                                </div>
                                <div class="form-group">
                                    <select name="gender">
                                        <option value="other">Select Gender</option>
                                        <option value="male">Male</option>
                                        <option value="female">Female</option>
                                        <option value="other">Other</option>
                                    </select>

                                </div>

                                <div class="form-group">
                                    <label for="address">Address</label>
                                    <input name ="address" type="text" class="form-control" id="address" placeholder="Enter Address for delivery">
                                </div>
                                <label for="datepicker">Date of birth: </label>
                                <input type="text" class="datepicker" data-date-format="yyyy-mm-dd">
                                <div class="form-group">
                                    <label for="phone-input">Phone Number</label>
                                    <input type="text" class="form-control" id="phone-input" name="phone" pattern="(03|05|07|08|09)+([0-9]{8})\b" required>
                                    <div class="invalid-feedback">Please enter a valid Vietnamese phone number.</div>
                                </div>

                                <div class="form-group">
                                    <label><c:out value="${message}"></c:out></label>
                                </div>
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </form>
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
        <!-- bootstrap js -->
        <script src="js/bootstrap.js"></script>
        <!-- custom js -->
        <script src="js/custom.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
        <script>
                                        $(document).ready(function () {
                                            $('.datepicker').datepicker();
                                        });
        </script>
        <script>

            function checkPasswordMatch() {
                var password = $("#password").val();
                var confirmPassword = $("#confirmPassword").val();
                if (password != confirmPassword) {
                    $("#passwordError").html("Passwords do not match.");
                } else {
                    $("#passwordError").html("");
                }
            }

        </script>

    </body>

</html>
