/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabeans.workwithderby;

import ejb.HttpSessionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
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
@WebServlet(name = "show_all_servlet_genre", urlPatterns = {"/show_all_servlet_genre"})
public class show_all_servlet_genre extends HttpServlet {
    
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
        
        String And = " AND ";
        
        String sql;
        sql = "SELECT * FROM GENRES";
        
        String name;
        String type;
        String year;   
        
        if (request.getParameter("name") == null) {
            name= "";
        }
        else name = request.getParameter("name");
        
        if (request.getParameter("type") == null) {
            type= "";
        }
        else type = request.getParameter("type");
        
        if (request.getParameter("year") == null){
            year= "";
        }
        else year = request.getParameter("year");
        
        if (!"".equals(name) || !"".equals(type) 
                || !"".equals(year) ){
            sql = "SELECT * FROM GENRES WHERE ";
            if (!"".equals(name)) {
                sql += "NAMEGENRE = '" + name + "'";
                if(!"".equals(type) || !"".equals(year)){
                    sql += And;
                }
            }
        
            if (!"".equals(type)) {
                sql += "TYPEGENRE = '" + type + "'";
                if(!"".equals(type)){
                    sql += And;
                }
            }
        
            if (!"".equals(year)) {
                sql += "NAMEBOOK = " + year;
            }
        }

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("Retrieving data from database...");
        System.out.println("\nGenres:");
        
        String[] nameArray = new String[1000];
        String[] typeArray = new String[1000];
        String[] yearArray = new String[1000];
        int countofArray = 0;
        
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String namegenre = resultSet.getString("NAMEGENRE");
            String typegenre = resultSet.getString("TYPEGENRE");
            String yeargenre = resultSet.getString("YEARGENRE");
            
            nameArray[countofArray] = namegenre;
            typeArray[countofArray] = typegenre;
            yearArray[countofArray] = yeargenre;
            
            countofArray++;
            
            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("namegenre: " + namegenre);
            System.out.println("typegenre: " + typegenre);
            System.out.println("yeargenre: " + yeargenre);
        }

        System.out.println("Closing connection and releasing resources...");
        resultSet.close();
        statement.close();
        connection.close();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Зміст бази </title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Зміст</h1>");
            out.println(HttpSessionManager.getActiveSessionsCount() + " user(s) using this site.");
            out.println("<table>");
            out.println("<tr><th>Назва жанру</th><th>Тип жанру</th><th>"
                    + "Рік створення</th></tr>");
            for (int i = 0; i < countofArray; i++) {
                out.println("<tr><td>"+ nameArray[i] + "</td><td>" 
                + typeArray[i] + "</td><td>" + yearArray[i] 
                + "</td></tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(show_all_servlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(show_all_servlet.class.getName()).log(Level.SEVERE, null, ex);
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
