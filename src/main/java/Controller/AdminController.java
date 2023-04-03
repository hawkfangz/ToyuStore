/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Admin;
import Entity.User;
import Manager.AdminManager;
import Manager.UserManager;
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
@WebServlet(name = "admin", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

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

        String destinate = "login_admin.jsp";
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String action = request.getParameter("action");
        UserManager usersManager = null;
        //hau admin
        List<User> users;
        if (session.getAttribute("admin") == null && account == null) {
            destinate = "login_admin.jsp";
        }
        if (action != null) {
            if (action.equals("login")) {
                AdminManager adminManager = new AdminManager();

                Admin admin = adminManager.login(account, password);

                if (admin != null) {
                    session.setAttribute("admin", admin);
                    destinate = "admin-menu";
                }
            }
            if (action.equals("logout")) {
                session.invalidate();
            }
            if (action.equals("accounts")) {
                usersManager = new UserManager();
                users = usersManager.getAllUsers();
                request.setAttribute("allAccounts", users);
                destinate = "account-manager.jsp";
            }
            if (action.equals("toggle")) {
                usersManager = new UserManager();
                String userId = request.getParameter("id");
                usersManager.toggle(Integer.parseInt(userId));
            }
        }
        request.setAttribute("title", "Admin");
//        response.sendRedirect(destinate);
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
