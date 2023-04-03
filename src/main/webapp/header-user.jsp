<%-- 
    Document   : header-user
    Created on : Feb 2, 2023, 2:55:35 PM
    Author     : phanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="header_top">
    <div class="container-fluid">
        <div class="top_nav_container">
            <div class="contact_nav">
                <a href="">
                    <i class="fa fa-phone" aria-hidden="true"></i>
                    <span>
                        Call : +01 123455678990
                    </span>
                </a>
                <a href="">
                    <i class="fa fa-envelope" aria-hidden="true"></i>
                    <span>
                        Email : demo@gmail.com
                    </span>
                </a>
            </div>
            <form action= "Search" method="GET" class="search_form">
                <input type="text" name="filter" class="form-control" placeholder="Search here...">
                <button class="" type="submit">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </button>
            </form>
            <div class="user_option_box">
                <c:if test="${sessionScope.user == null}">
                    <a href="login" class="account-link">
                        <i class="fa fa-user" aria-hidden="true"></i>
                        <span>
                            Log in
                        </span>
                    </a>
                    <a href="sign-up" class="account-link">
                        <i class="fa fa-signing" aria-hidden="true"></i>
                        <span>
                            Sign Up
                        </span>
                    </a>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <a href="user" class="account-link">
                        <i class="fa fa-user" aria-hidden="true"></i>
                        <span>
                            My Account
                        </span>
                    </a>
                    <a href="cart" class="cart-link">
                        <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                        <span>
                            Cart
                        </span>
                    </a>
                    <a href="order" class="cart-link">
                        <i class="fa fa-history" aria-hidden="true"></i>
                        <span>
                            Order
                        </span>
                    </a>
                    <a href="user?action=logout" class="account-link">
                        <i class="fa fa-user" aria-hidden="true"></i>
                        <span>
                            Log out
                        </span>
                    </a>
                </c:if>
                <c:if test="${sessionScope.admin != null}">
                    <a href="admin-menu" class="account-link">
                        <i class="fa fa-dashboard" aria-hidden="true"></i>
                        <span>
                            Menu
                        </span>
                    </a>
                    <a href="admin?action=logout" class="account-link">
                        <i class="fa fa-user" aria-hidden="true"></i>
                        <span>
                            admin log out
                        </span>
                    </a>
                </c:if>

            </div>
        </div>
    </div>
</div>
