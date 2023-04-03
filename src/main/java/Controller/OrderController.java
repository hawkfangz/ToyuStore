/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Admin;
import Entity.CartItem;
import Entity.Order;
import Entity.User;
import Manager.OrderManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "order", urlPatterns = {"/order"})
public class OrderController extends HttpServlet {

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
        User user = (User) session.getAttribute("user");
        Admin admin = (Admin) session.getAttribute("admin");
        String action = request.getParameter("action");
//        String orderId = request.getParameter("id");
        String destinate = "orders.jsp";
        String title = "Orders";
        OrderManager orderManager = new OrderManager();

        if (user != null) {
            if (action != null) {
                if (action.equals("get")) {
                    int orderId = Integer.parseInt(request.getParameter("orderId"));
                    Order order = orderManager.getOrder(orderId);
                    request.setAttribute("order", order);
                    request.setAttribute("title", "Order detail");
                    destinate = "order_detail.jsp";
                }
            } else {
                List<Order> userOrders = orderManager.getUserOrders(user);
                request.setAttribute("orders", userOrders);
                request.setAttribute("title", user.getName() + " orders");
            }
        }
        if (admin != null && action != null) {
            if (action.equals("all")) {
                List<Order> userOrders = orderManager.getAllUserOrders();
                request.setAttribute("ordersList", userOrders);
                request.setAttribute("title", "All Orders");
                destinate = "admin-order.jsp";
            }
            if (action.equals("get")) {
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                Order order = orderManager.getOrder(orderId);
                request.setAttribute("order", order);
                request.setAttribute("title", "Order detail");
                destinate = "order_detail.jsp";
            }
            if (action.equals("set")) {
                int orderId = Integer.parseInt(request.getParameter("id"));
                int status = Integer.parseInt(request.getParameter("status"));
                System.out.println(orderId+"==="+status);
                orderManager.setStatus(orderId, status);
                destinate ="order?action=all";
                response.sendRedirect(destinate);
                return;
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destinate);
        requestDispatcher.forward(request, response);
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
