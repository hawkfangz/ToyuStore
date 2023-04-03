/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Cart;
import Entity.CartItem;
import Entity.Order;
import Entity.Product;
import Entity.User;
import Manager.OrderManager;
import Manager.ProductManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author phanh
 */
@WebServlet(name = "cart", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        Cart userCart;

        String title;
        String action;
        int productId;

        String destinate = "cart.jsp";

        Product product;
        OrderManager orderManager;
        ProductManager productManager;
        User user = null;
        Order order;

        action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        userCart = (Cart) session.getAttribute("cart");
        user = (User) session.getAttribute("user");
        System.out.println(action);
        
        if (userCart == null) {
            session.setAttribute("cart", new Cart());
        }

        if (user != null && userCart != null) {
            System.out.println(action);
            productManager = new ProductManager();
            if (action.equals("add")) {
                productId = Integer.parseInt(request.getParameter("id"));
                product = productManager.getProduct(productId);
                CartItem item = new CartItem();
                item.setProduct(product);
                userCart.addItem(item);
                session.setAttribute("cart", userCart);
            }
            if (action.equals("minus")) {
                productId = Integer.parseInt(request.getParameter("id"));
                product = productManager.getProduct(productId);
                CartItem item = new CartItem();
                item.setProduct(product);
                int quantity  = userCart.minusItem(item);
                System.out.println(quantity);
                if (quantity ==0) {
                    userCart.removeItem(item.getProductId());
                }
                session.setAttribute("cart", userCart);
            }
            if (action.equalsIgnoreCase("remove")) {
                productId = Integer.parseInt(request.getParameter("id"));
                userCart.removeItem(productId);
                session.setAttribute("cart", userCart);
            }
            if (action.equalsIgnoreCase("place")) {
                orderManager = new OrderManager();
                order = orderManager.CreateOrder(user, userCart);
                orderManager.addItemsToOrder(order, userCart);
                destinate = "order";
                userCart = new Cart();
                session.setAttribute("cart", userCart);
            }

        } else {
            destinate = "login";
        }

        response.sendRedirect(destinate);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
