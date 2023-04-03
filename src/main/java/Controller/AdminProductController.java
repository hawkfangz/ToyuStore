/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Manufacturer;
import Entity.Product;
import Entity.ProductType;
import Manager.ManufacturerManager;
import Manager.ProductManager;
import Manager.TypeManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author phanh
 */
@WebServlet(name = "Product-Admin", urlPatterns = {"/AdminProduct"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class AdminProductController extends HttpServlet {

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

        String path = "product";

        String action = request.getParameter("action");

        HttpSession session = request.getSession();

        if (action != null) {
            System.out.println("Action is :" + action + "\n\n");
        }

        ProductManager productManager = new ProductManager();

        TypeManager typeManager = new TypeManager();

        ManufacturerManager manufacturerManager = new ManufacturerManager();
        boolean isAdmin = false;
        if (session.getAttribute("admin") != null) {
            isAdmin = true;
        }
        if (action != null && isAdmin) {

            if (action.equals("toggle")) {
                int productId = Integer.parseInt(request.getParameter("id"));
                productManager.toggleProduct(productId);
                response.sendRedirect("product");
                return;
            }

            if (action.equals("create")) {

                List<ProductType> allTypes = typeManager.getAllType();
                List<Manufacturer> allManufacturers = manufacturerManager.getManufacturersList();
                String action2 = "Create";
                request.setAttribute("do-what", action2);

                request.setAttribute("allProductType", allTypes);

                request.setAttribute("allManufacturers", allManufacturers);
                path = "ProductForm";
            }

            if (action.equals("do-create")) {
                //Input session
                String name = request.getParameter("name");
                float Price = Float.parseFloat(request.getParameter("price"));
                String description = request.getParameter("description");

                String[] typesSelected = request.getParameterValues("types-selected");
                String[] manufacturersSelected = request.getParameterValues("manufacturers-selected");

                int[] types = new int[typesSelected.length];

                for (int i = 0; i < typesSelected.length; i++) {
                    types[i] = Integer.parseInt(typesSelected[i]);

                }

                int[] manufacturers = new int[manufacturersSelected.length];

                for (int i = 0; i < manufacturersSelected.length; i++) {
                    manufacturers[i] = Integer.parseInt(manufacturersSelected[i]);
                }

                int id = productManager.addProduct(name, description, Price, types, manufacturers);
                System.out.println("id is: " + id);

                //Image session
                Part filePart = request.getPart("image");
                String fileName = id + ".jpg";
                System.out.println("id is: " + fileName);

                InputStream fileContent = filePart.getInputStream();
                byte[] buffer = new byte[fileContent.available()];
                fileContent.read(buffer);

                String savePath = request.getServletContext().getRealPath("/product/");
                File targetFile = new File(savePath + fileName);
                OutputStream outStream = new FileOutputStream(targetFile);
                outStream.write(buffer);

                outStream.close();
                fileContent.close();

                path = "product";
            }
//            http://localhost:8080/ToyuStore/AdminProduct?action=edit&id=6
//           Set type cua product sang List 
            if (action.equals("edit")) {
                request.setAttribute("do-what", "Edit");
                path = "ProductForm";
                String productId = request.getParameter("id");
                Product requestProduct = productManager.getProduct(Integer.parseInt(productId));

                List<ProductType> allTypes = typeManager.getAllType();

                List<Manufacturer> allManufacturers = manufacturerManager.getManufacturersList();

                Set<ProductType> productTypes = requestProduct.getTypes();

                Set<Manufacturer> productManufacturers = requestProduct.getManufacturers();

                request.setAttribute("allProductType", allTypes);

                request.setAttribute("allManufacturers", allManufacturers);

                request.setAttribute("productTypes", productTypes);

                request.setAttribute("productManufacturers", productManufacturers);

                request.setAttribute("product", requestProduct);
            }

            if (action.equals("do-edit")) {
                //Image session
                Part filePart = request.getPart("image");
                String fileName = request.getParameter("id") + ".jpg";
                if (filePart != null && filePart.getSize() > 0) {
                    InputStream fileContent = filePart.getInputStream();
                    byte[] buffer = new byte[fileContent.available()];
                    fileContent.read(buffer);

                    String savePath = request.getServletContext().getRealPath("/product/");
                    File targetFile = new File(savePath + fileName);
                    OutputStream outStream = new FileOutputStream(targetFile);
                    outStream.write(buffer);
                    outStream.close();
                    fileContent.close();
                }
                //text session here
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                float Price = Float.parseFloat(request.getParameter("price"));
                String description = request.getParameter("description");

                String[] typesSelected = request.getParameterValues("types-selected");
                String[] manufacturersSelected = request.getParameterValues("manufacturers-selected");

                int[] types = new int[typesSelected.length];

                for (int i = 0; i < typesSelected.length; i++) {
                    types[i] = Integer.parseInt(typesSelected[i]);
                }

                int[] manufacturers = new int[manufacturersSelected.length];

                for (int i = 0; i < manufacturersSelected.length; i++) {
                    manufacturers[i] = Integer.parseInt(manufacturersSelected[i]);
                }

                productManager.editProduct(id, name, description, Price, types, manufacturers);

                path = "product";
            }
        } else if (!isAdmin) {
            path = "admin";
        }

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

}
