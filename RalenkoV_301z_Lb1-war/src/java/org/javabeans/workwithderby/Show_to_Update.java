/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabeans.workwithderby;

import com.library.Book;
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
@WebServlet(name = "Show_to_Update", urlPatterns = {"/Show_to_Update"})
public class Show_to_Update extends HttpServlet {
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
        sql = "SELECT * FROM BOOKS";
        
        String surname_of_author;
        String name_of_author;
        String name_of_book;   
        String year_of_book;
        String city_of_print;
        
        if (request.getParameter("surname_of_author") == null) {
            surname_of_author= "";
        }
        else surname_of_author = request.getParameter("surname_of_author");
        
        if (request.getParameter("name_of_author") == null) {
            name_of_author= "";
        }
        else name_of_author = request.getParameter("name_of_author");
        
        if (request.getParameter("name_of_book") == null){
            name_of_book= "";
        }
        else name_of_book = request.getParameter("name_of_book");
        
        if (request.getParameter("year_of_book") == null){
            year_of_book= "";
        }
        else year_of_book = request.getParameter("year_of_book");
        
        if (request.getParameter("city_of_print") == null){
            city_of_print = "";
        }
        else city_of_print = request.getParameter("city_of_print");
        
        if (!"".equals(surname_of_author) || !"".equals(name_of_author) 
                || !"".equals(name_of_book) || !"".equals(year_of_book) 
                || !"".equals(city_of_print)){
            sql = "SELECT * FROM BOOKS WHERE ";
            if (!"".equals(surname_of_author)) {
                sql += "SURNAMEAUTHOR = '" + surname_of_author + "'";
                if(!"".equals(name_of_author) || !"".equals(name_of_book) 
                    || !"".equals(year_of_book) || !"".equals(city_of_print)){
                    sql += And;
                }
            }
        
            if (!"".equals(name_of_author)) {
                sql += "NAMEAUTHOR = '" + name_of_author + "'";
                if(!"".equals(name_of_book) || !"".equals(year_of_book) 
                        || !"".equals(city_of_print)){
                    sql += And;
                }
            }
        
            if (!"".equals(name_of_book)) {
                sql += "NAMEBOOK = '" + name_of_book + "'";
                if(!"".equals(year_of_book) || !"".equals(city_of_print)){
                    sql += And;
                }
            }
        
            if (!"".equals(year_of_book)) {
                sql += "YEARBOOK = " + year_of_book;
                if(!"".equals(city_of_print)){
                    sql += And;
                }
            }
        
            if (!"".equals(city_of_print)) {
                sql += "CITYOFPRINT = '" + city_of_print + "'";
            }
        }

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("Retrieving data from database...");
        System.out.println("\nBooks:");
        
        int countofArray = 0;
        
        List<Book> books = new LinkedList<>();
        
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("NAMEAUTHOR");
            String surnameauthor = resultSet.getString("SURNAMEAUTHOR");
            String namebook = resultSet.getString("NAMEBOOK");
            int yearbook = resultSet.getInt("YEARBOOK");
            String cityofprint = resultSet.getString("CITYOFPRINT");
            
            Book book = new Book();
            book.setId(id);
            book.setName_of_author(name);
            book.setSurname_of_author(surnameauthor);
            book.setName_of_book(namebook);
            book.setYear_of_book(yearbook);
            book.setCity_of_print(cityofprint);
            
            books.add(book);
            
            countofArray++;
            
            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("name: " + name);
            System.out.println("surnameauthor: " + surnameauthor);
            System.out.println("namebook: " + namebook);
            System.out.println("yearbook: " + yearbook);
            System.out.println("cityofprint: " + cityofprint);
        }

        System.out.println("Closing connection and releasing resources...");
        resultSet.close();
        statement.close();
        connection.close();
        request.setAttribute("Books", books);
        request.getRequestDispatcher("/selecttoupdate.jsp").forward(request,response);
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
