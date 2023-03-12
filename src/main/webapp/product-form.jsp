<%-- 
    Document   : product-edit
    Created on : Feb 2, 2023, 5:36:35 PM
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
        <!-- Product create section -->
        <%
            String action = request.getParameter("action");
            if (action.equals("create")) {
        %>
        <section class="product_section layout_padding">
            <div class="container">
                <div class="heading_container heading_center">
                    <h2>
                        <c:out value='${requestScope["do-what"]}' /><br/>
                        Fill the form
                    </h2>
                </div>
                <div class="img-container">
                    <img id="previewImage" class="align-self-start mr-3" src="product/undefined.png" onerror="this.src='product/undefined.png';">
                </div>
                <form action="AdminProduct" method="POST" enctype="multipart/form-data">
                    <input id="imageInput" type="file" name="image">
                    <br>
                    <input type="hidden" name="action" value="do-create">
                    <input type="text" name="name" class="form-control" placeholder="Name" ">
                    <br>
                    <input type="number" name="price" class="form-control" placeholder="Price. . ." >
                    <br>
                    <textarea class="form-control" name="description" rows="3" placeholder="Description..."></textarea>
                    <br>
                    <label>Product types</label>
                    <c:forEach items="${allProductType}" var="o">
                        <div class="form-check s-types">
                            <input class="form-check-input" type="checkbox" name="types-selected" value="${o.id}" id="product-type">
                            <label class="form-check-label">
                                ${o.name}
                            </label>
                        </div>
                    </c:forEach>
                    <br>
                    <label>Manufacturers</label>
                    <c:forEach items="${allManufacturers}" var="o">
                        <div class="form-check s-manufacturers">
                            <input class="form-check-input" type="checkbox" name="manufacturers-selected" value="${o.id}" id="product-manufacturers">
                            <label class="form-check-label">
                                ${o.name}
                            </label>
                        </div>
                    </c:forEach>
                    <br>
                    <input type="submit" value="Submit" name="Submit" />
                </form>
            </div>
        </section>
        <%}%>
        <%
            if (action.equals("edit")) {
        %>
        <section class="product_section layout_padding">
            <div class="container">
                <div class="heading_container heading_center">
                    <h2>
                        <c:out value='${requestScope["do-what"]}' /><br/>
                        Fill the form
                    </h2>
                </div>
                <c:set var="object" value="${product}" />
                <div class="img-container">
                    <img id="previewImage" class="align-self-start mr-3" src="product/${product.id}.jpg" onerror="this.src='product/undefined.png';">
                </div>

                <form action="AdminProduct" method="POST" enctype="multipart/form-data">
                    <input id="imageInput" type="file" name="image">
                    <br>
                    <input type="hidden" name="action" value="do-edit">
                    <input type="hidden" name="id" value="${product.id}">
                    <input type="text" name="name" class="form-control" placeholder="Name" value="${product.name}">
                    <br>
                    <input type="number" name="price" class="form-control" placeholder="Price. . ." value="${product.price}">
                    <br>
                    <textarea class="form-control" name="description" rows="3" placeholder="Description...">${product.des}</textarea>
                    <br>
                    <label>Product types</label>
                    <c:forEach items="${allProductType}" var="o">
                        <div class="form-check s-types">
                            <input class="form-check-input" type="checkbox" name="types-selected" value="${o.id}" id="product-type">
                            <label class="form-check-label">
                                ${o.name}
                            </label>
                        </div>
                    </c:forEach>
                    <br>
                    <label>Manufacturers</label>
                    <c:forEach items="${allManufacturers}" var="o">
                        <div class="form-check s-manufacturers">
                            <input class="form-check-input" type="checkbox" name="manufacturers-selected" value="${o.id}" id="product-manufacturers">
                            <label class="form-check-label">
                                ${o.name}
                            </label>
                        </div>
                    </c:forEach>
                    <br>
                    <input type="submit" value="Submit" name="Submit" />
                </form>
            </div>
        </section>

        <%}%>
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
        <%
            if (action.equals("edit")) {
        %>
        <script>
            $(document).ready(function () {
            <c:forEach items="${productManufacturers}" var="o">
                $('input#product-manufacturers[value="${o.id}"]').prop('checked', true);
            </c:forEach>
            });
        </script>
        <script>
            $(document).ready(function () {
            <c:forEach items="${productTypes}" var="o">
                $('input#product-type[value="${o.id}"]').prop('checked', true);
            </c:forEach>
            });
        </script>
        <%
            }
        %>
        <script>
            $(document).ready(function () {
                $('#imageInput').change(function () {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $('#previewImage').attr('src', e.target.result);
                    };
                    reader.readAsDataURL(this.files[0]);
                });
            });
        </script>



    </body>

</html>
