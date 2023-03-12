/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Cart;
import Entity.User;
import Manager.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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
@WebServlet(name = "user", urlPatterns = {"/user"})
public class UserController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String action = request.getParameter("action");
        String destinate = "login.jsp";
        User user = (User) session.getAttribute("user");
        UserManager userManager = new UserManager();
        if (action != null && user == null) {
            if (action.equals("login") && user == null) {
                user = userManager.login(account, password);
                if (user != null) {
                    session.setAttribute("user", user);
                    Cart userCart = userCart = new Cart();
                    session.setAttribute("cart", userCart);
                    destinate = "user.jsp";
                } else {
                    request.setAttribute("message", "account or password wrong, please try again!");
                    destinate = "login.jsp";
                }
            }
            if (action.equals("signup")) {
                String name = request.getParameter("name");
                String address = request.getParameter("address");
                String email = request.getParameter("email");
                String dob = request.getParameter("dob");
                String phone = request.getParameter("phone");
                String gender = request.getParameter("gender");
                LocalDate dateOfBirth = LocalDate.parse(dob);
                if (userManager.signUp(account, password, name, phone, dateOfBirth, address, email, gender)) {
                    destinate = "login";
                    request.setAttribute("message", "Sign up Success, please login!");
                }
            }

        }
        if (user != null) {
            destinate = "user.jsp";
            if (action != null) {
                if (action.equals("logout")) {
                    session.invalidate();
                    destinate = "login.jsp";
                }
                if (action.equals("do-edit")) {
                    String name = request.getParameter("name");
                    String address = request.getParameter("address");
                    String email = request.getParameter("email");

                    String dob = request.getParameter("dob");
                    LocalDate dateOfBirth = LocalDate.parse(dob);
                    String phone = request.getParameter("phone");
                    String gender = request.getParameter("gender");
                    user = userManager.update(user.getCustomerId(), name, phone, dateOfBirth, address, email, gender);
                    session.setAttribute("user", user);
                    destinate = "user.jsp";
                }
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
