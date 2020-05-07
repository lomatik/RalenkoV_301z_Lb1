<%-- 
    Document   : selecttoupdate_genre
    Created on : 06.05.2020, 17:38:27
    Author     : lomatik
--%>

<%@page import="java.util.List"%>
<%@page import="com.library.Genre"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Вибір даних для оновлення</title>
        </head>
        <body>
            <jsp:useBean id="genres" class="org.javabeans.workwithderby.Show_to_delete_genre">
            </jsp:useBean>
            <h1>Зміст</h1>
            <h2>Введіть дані які ви хочете оновити,оберіть дані для оновлення, а потім натисніть кнопку "Оновити"</h2>
            <form>
            Назва жанру <input type="text" name="namegenre" id = "namegenre"><br>
            Тип жанру <input type="text" name="typegenre" id = "typegenre"><br>
            Рік створення<input type="text" name="yeargenre" id = "yeargenre"><br>
            </form>
            <table>
            <tr><th>Вибір</th><th>Назва жанру</th><th>Тип жанру</th><th>Рік створення</th></tr>
            <%if (request.getAttribute("genres") != null) {
                   for(Genre item: (List<Genre>) request.getAttribute("genres")) {
                       out.println("<tr><td><input type=\"radio\" name=\"item\" "
                + " id = \""+ item.getId()+"\" /></td><td>"+ item.getNamegenre() + "</td><td>" 
                + item.getTypegenre() + "</td><td>" + item.getYeargenre() + "</td></tr>");
                   }
            }%>          
            </table>
            <input id="btn" type="button" name="snd_update_btn" value="Оновити обране">
            <script>
                btn.onclick = function() {
                var с = document.getElementById("yeargenre").value;
                var s = document.getElementsByName("item");
                var idchecked;
                if(/[^[0-9]/.test(с)){
                    alert('Ви ввели не число в поле "Рік створення", повторіть спробу');
                }
                else {
                    for (var i = 0; i < s.length; i++) {
                        if (s[i].checked) {
                            idchecked = s[i].id;
                        }
                    }
                    if (idchecked.trim() === '') {
                        alert("Ви нічого не обрали для оновлення, повторіть спробу");
                    }
                    else{
                        location.href = "/RalenkoV_301z_Lb1-war/UpdateSelected_genre?idchecked=" + idchecked + 
                        "&name=" + document.getElementById("namegenre").value+
                        "&type=" + document.getElementById("typegenre").value+
                        "&year=" + document.getElementById("yeargenre").value;
                    }
                }
            };
            </script>
        </body>
    </html>