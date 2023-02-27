/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Cart;
import Entity.CartItem;
import Entity.Product;
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

        String id;
        String action;
        int productId;

        String destinate = "cart.jsp";

        Product product;

        action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        userCart = (Cart) session.getAttribute("cart");

        synchronized (session) {
            if (session.getAttribute("user") != null && userCart != null) {

                ProductManager productManager = new ProductManager();
                if (action.equals("add")) {

                    productId = Integer.parseInt(request.getParameter("id"));
                    product = productManager.getProduct(productId);
                    CartItem item = new CartItem();
                    item.setProduct(product);
                    userCart.addItem(item);
                    session.setAttribute("cart", userCart);
//                  session.setAttribute("userItemList", userCart.getCart());
//                  System.out.println(userCart.getCart().size());
                    System.out.println("=====");
                    for (CartItem item2 : userCart.getCart()) {
                        System.out.println(item2);
                    }

//                    List<CartItem> itemOrder = (List<CartItem>) session.getAttribute("userItemList");
//                    System.out.println("SIZE IS"+itemOrder.size());
                }
            } else {
                destinate = "login";
            }
        }
        response.sendRedirect(destinate);

//        RequestDispatcher rd = request.getRequestDispatcher(destinate);
//        
//        rd.forward(request, response);
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
