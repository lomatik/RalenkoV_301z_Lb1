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
@WebServlet(name = "Show_genres_to_insert", urlPatterns = {"/Show_genres_to_insert"})
public class Show_genres_to_insert extends HttpServlet {

    static final String JDBC_DRIVER = "java.sql.Driver";
    static final String DATABASE_URL = "jdbc:derby://localhost:1527/item_library";
    
    static final String USER = "APP";
    static final String PASSWORD = "123";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        
        Class.forName("java.sql.Driver");

        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD); 
        Statement statement = connection.createStatement();
        String sql;
        sql = "SELECT * FROM GENRES";
        ResultSet resultSet = statement.executeQuery(sql);
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
            
            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("namegenre: " + namegenre);
            System.out.println("typegenre: " + typegenre);
            System.out.println("yeargenre: " + yeargenre);
        }
        resultSet.close();
        statement.close();
        connection.close();
        request.setAttribute("genres", genres);
        request.getRequestDispatcher("/insert.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Show_to_Update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Show_to_Update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}