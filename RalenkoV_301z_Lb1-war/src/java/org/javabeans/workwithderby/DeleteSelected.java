/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabeans.workwithderby;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lomatik
 */
@WebServlet(name = "DeleteSelected", urlPatterns = {"/DeleteSelected"})
public class DeleteSelected extends HttpServlet {

static final String JDBC_DRIVER = "java.sql.Driver";
    static final String DATABASE_URL = "jdbc:derby://localhost:1527/item_library";
    
    static final String USER = "APP";
    static final String PASSWORD = "123";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        System.out.println("Registering JDBC driver...");
        
        Class.forName("java.sql.Driver");

        System.out.println("Creating database connection...");
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement...");
        Statement statement = connection.createStatement();
        
        String idchecked = request.getParameter("idchecked");
        
        String sql;
        sql = "DELETE FROM BOOKS WHERE ID = " + idchecked;
        
        statement.executeUpdate(sql);
        System.out.println("Successfully deleted");
        
        System.out.println("Closing connection and releasing resources...");
        statement.close();
        connection.close();
        
        request.getRequestDispatcher("/deletedsuccess.jsp").forward(request,response);
        
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
    try {
        processRequest(request, response);
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(DeleteSelected.class.getName()).log(Level.SEVERE, null, ex);
    }
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
    try {
        processRequest(request, response);
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(DeleteSelected.class.getName()).log(Level.SEVERE, null, ex);
    }
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
