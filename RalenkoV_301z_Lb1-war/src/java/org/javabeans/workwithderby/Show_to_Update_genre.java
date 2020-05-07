/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabeans.workwithderby;

import com.library.Genre;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
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
@WebServlet(name = "Show_to_Update_genre", urlPatterns = {"/Show_to_Update_genre"})
public class Show_to_Update_genre extends HttpServlet {
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
                sql += "YEARGENRE = " + year;
            }
        }

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("Retrieving data from database...");
        System.out.println("\nGenres:");
        
        int countofArray = 0;
        
        List<Genre> genres = new LinkedList<>();
        
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String namegenre = resultSet.getString("NAMEGENRE");
            String typegenre = resultSet.getString("TYPEGENRE");
            int yeargenre = resultSet.getInt("YEARGENRE");
            
            Genre genre = new Genre();
            genre.setId(id);
            genre.setNamegenre(namegenre);
            genre.setTypegenre(typegenre);
            genre.setYeargenre(yeargenre);
            
            genres.add(genre);
            
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
        request.setAttribute("genres", genres);
        request.getRequestDispatcher("/selecttoupdate_genre.jsp").forward(request,response);
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
            Logger.getLogger(Show_to_Update.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Show_to_Update.class.getName()).log(Level.SEVERE, null, ex);
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
