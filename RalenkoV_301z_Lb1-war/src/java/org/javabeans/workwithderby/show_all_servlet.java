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
import javax.servlet.http.HttpSession;

/**
 *
 * @author lomatik
 */
@WebServlet(name = "show_all_servlet", urlPatterns = {"/show_all_servlet"})
public class show_all_servlet extends HttpServlet {
    
    static final String JDBC_DRIVER = "java.sql.Driver";
    static final String DATABASE_URL = "jdbc:derby://localhost:1527/item_library";
    
    static final String USER = "APP";
    static final String PASSWORD = "123";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession();
        session.setAttribute("url", "google.com");
        session.removeAttribute("url");
        
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
        
        String[] nameauthorArray = new String[1000];
        String[] surnameauthorArray = new String[1000];
        String[] namebookArray = new String[1000];
        int[] yearbookArray = new int[1000];
        String[] cityofprintArray = new String[1000];
        int countofArray = 0;
        
        while (resultSet.next()) {
            int id = resultSet.getRow();
            String name = resultSet.getString("NAMEAUTHOR");
            String surnameauthor = resultSet.getString("SURNAMEAUTHOR");
            String namebook = resultSet.getString("NAMEBOOK");
            int yearbook = resultSet.getInt("YEARBOOK");
            String cityofprint = resultSet.getString("CITYOFPRINT");
            
            nameauthorArray[countofArray] = name;
            surnameauthorArray[countofArray] = surnameauthor;
            namebookArray[countofArray] = namebook;
            yearbookArray[countofArray] = yearbook;
            cityofprintArray[countofArray] = cityofprint;
            
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
            out.println("<tr><th>Ім'я автора</th><th>Прізвище автора</th><th>"
                    + "Назва книги</th><th>Рік видавництва</th><th>Місто видавництва</th></tr>");
            for (int i = 0; i < countofArray; i++) {
                out.println("<tr><td>"+ nameauthorArray[i] + "</td><td>" 
                + surnameauthorArray[i] + "</td><td>" + namebookArray[i] 
                + "</td><td>" + yearbookArray[i] + "</td><td>"
                + cityofprintArray[i] + "</td></tr>");
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
