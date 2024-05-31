/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Product;
import Manager.ManufacturerManager;
import Manager.ProductManager;
import Manager.CategoryManager;
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
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class SearchController extends HttpServlet {

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
        String type;
        String keyword;
        String path = "product-result.jsp";
        int page;
        ProductManager productManager;
        CategoryManager typeManager;
        ManufacturerManager manufacturerManager;

        List<Product> result;

        String pageNumber = request.getParameter("page");
        if (pageNumber != null) {
            page = Integer.parseInt(pageNumber);
            if (page < 0) {
                page = 1;
            }
//            if (page > getListResult(keyword).size()/9) {
//                page = getPageLimit();
//            }
        } else {
            page = 1;
        }

        int start = (page - 1) * 9;
        int end = start + 9 - 1;

        type = request.getParameter("type");
        keyword = request.getParameter("filter");

        if (type == null) {
            type = "product";
        }
        if (keyword == null) {
            keyword = "";
        }
        if (type.equalsIgnoreCase("product")) {
            productManager = new ProductManager();
            result = productManager.search(keyword);
            System.out.println("Result size:" + result.size());
            request.setAttribute("result", result);
        }
        if (type.equals("type")) {
            typeManager = new CategoryManager();
            int id = Integer.parseInt(keyword);
            result = typeManager.getProductByType(id);
            request.setAttribute("result", result);
        }

        request.setAttribute("start", start);
        request.setAttribute("end", end);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
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

    public List<Product> getListResult(String keyword) {
        ProductManager productManager = new ProductManager();
        List<Product> result = productManager.search(keyword);
        return result;
    }
}
