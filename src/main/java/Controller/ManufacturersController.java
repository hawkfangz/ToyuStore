/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Manufacturer;
import Manager.ManufacturerManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author phanh
 */
@WebServlet(name = "manufacturers", urlPatterns = {"/manufacturers"})
public class ManufacturersController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String destinate = "manufacturers.jsp";
            String title = "Manufacturers";

            int id;
            String name;
            String country;

            String action = request.getParameter("action");
            String sort = request.getParameter("sort");

            ManufacturerManager manuManager = new ManufacturerManager();
            List<Manufacturer> manuList;

            if (action == null && sort == null) {
                manuList = manuManager.getManufacturersList();
                request.setAttribute("manufacturerList", manuList);
            }
            if (action != null) {
                if (action.equals("create") || action.equals("edit")) {
                    destinate = "manufacturer-form.jsp";
                }
                if (action.equals("do-create")) {
                    name = request.getParameter("name");
                    country = request.getParameter("country");
                    String statusP = request.getParameter("status");
                    int status = 0;
                    if (statusP != null) {
                        status = 1;
                    }
                    manuManager.add(name, country, status);
                }
                if (action.equals("toggle")) {
                    id = Integer.parseInt(request.getParameter("id"));
                    manuManager.toggle(id);
                }
                if (action.equals("edit")) {
                    id = Integer.parseInt(request.getParameter("id"));
                    Manufacturer manufacturer = manuManager.getManufacturer(id);
                    System.out.println(manufacturer.getCountry());
                    request.setAttribute("manufacturer", manufacturer);
                }
                if (action.equals("do-edit")) {
                    id = Integer.parseInt(request.getParameter("id"));
                    name = request.getParameter("name");
                    country = request.getParameter("country");
                    String statusP = request.getParameter("status");
                    int status = 0;
                    if (statusP != null) {
                        status = 1;
                    }
                    manuManager.editManufacturer(id, name, country, status);
                    destinate = "manufacturers.jsp";
                }
            }

            request.setAttribute("title", title);
            System.out.println(destinate);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(destinate);
            requestDispatcher.forward(request, response);
        }
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
