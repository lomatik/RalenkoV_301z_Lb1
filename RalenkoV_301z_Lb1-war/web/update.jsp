<%-- 
    Document   : update
    Created on : 21.05.2020, 20:12:35
    Author     : lomatik
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="com.library.Genre"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Оновлення</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Введіть критерії пошуку книг</h1>
        <h2>Для показу всіх книг залиште поля пустими</h2>
        <form>
            Прізвище автора <input type="text" name="surname_of_author" id = "surname"><br>
            Ім'я автора <input type="text" name="name_of_author" id = "nameauthor"><br>
            Назва книги<input type="text" name="name_of_book" id = "namebook"><br>
            Рік видавництва книги<input type="text" name="year_of_book" id="check"><br>
            Місто видавництва <input type="text" name="city_of_print" id = "city"><br>
            <select id = "select_" name="genreSelect" size="6">
            <%if (request.getAttribute("genres") != null) {
                for(Genre item: (List<Genre>) request.getAttribute("genres")) {
                    out.println("<option value=\""+item.getId()+"\">"+item.getNamegenre()+"</option>");
                }
            }%>
            </select><br>
            <input type="button" name="show_btn" value="Показати" id="btn"><br>
        </form>
        <script> 
                btn.onclick = function() {
                var s = document.getElementById("check").value;
                if(/[^[0-9]/.test(s)){
                    alert('Ви ввели не число в поле "рік видавництва книги", повторіть спробу');
                }
                else{
                    location.href = "/RalenkoV_301z_Lb1-war/Show_to_Update?surname_of_author="
                    + document.getElementById("surname").value+
                    "&name_of_author=" + document.getElementById("nameauthor").value+
                    "&name_of_book=" + document.getElementById("namebook").value+
                    "&year_of_book=" + document.getElementById("check").value+
                    "&city_of_print=" + document.getElementById("city").value+
                    "&id_genre=" + document.getElementById("select_").value +
                    "&show_btn=%D0%9F%D0%BE%D0%BA%D0%B0%D0%B7%D0%B0%D1%82%D0%B8";
                };
            };
        </script>
    </body>
</html>
