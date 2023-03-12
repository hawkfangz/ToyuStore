/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.ProductType;
import Manager.TypeManager;
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
@WebServlet(name = "types", urlPatterns = {"/types"})
public class TypesController extends HttpServlet {

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


        String destinate = "types.jsp";
        String title = "Types";
        int id;

        String action = request.getParameter("action");
        String sort = request.getParameter("sort");

        TypeManager typeManager = new TypeManager();
        if (action != null) {
            System.out.println(action);
        }
        if (sort == null && action == null) {
            List<ProductType> allTypes = typeManager.getAllType();
            request.setAttribute("typeList", allTypes);
        }
        if (action != null) {
            String strId = request.getParameter("id");
            if (null == strId) {
                strId = -1 + "";
            }
            id = Integer.parseInt(strId);

            if (action.equalsIgnoreCase("toggle")) {
                typeManager.toggleType(id);
            }
            if (action.equalsIgnoreCase("create")) {
                destinate = "type_form.jsp";
                request.setAttribute("action", "create");
            }
            if (action.equalsIgnoreCase("do-create")) {

                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String statusP = request.getParameter("status");
                int status = 0;
                if (statusP != null) {
                    status = 1;
                }
                typeManager.addType(name, description, status);
            }
            if (action.equalsIgnoreCase("edit")) {
                destinate = "type_form.jsp";
                ProductType type = typeManager.getType(id);
                title = "Edit "+type.getName();
                request.setAttribute("type", type);
                request.setAttribute("action", "edit");
            }
            if (action.equalsIgnoreCase("do-edit")) {

                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String statusP = request.getParameter("status");
                int status = 0;
                if (statusP != null) {
                    status = 1;
                }
                typeManager.updateType(id, name, description, status);
            }
        }

        request.setAttribute("title", title);
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
