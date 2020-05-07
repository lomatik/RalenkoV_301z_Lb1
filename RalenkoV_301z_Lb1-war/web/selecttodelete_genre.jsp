<%-- 
    Document   : selecttodelete_genre
    Created on : 06.05.2020, 17:34:22
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
            <title>Вибір даних для видалення</title>
        </head>
        <body>
            <jsp:useBean id="genres" class="org.javabeans.workwithderby.Show_to_delete_genre">
            </jsp:useBean>
            <h1>Зміст</h1>
            <h2>Оберіть дані для видалення, а потім натисніть кнопку "Видалити"</h2>
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
            <input id="btn" type="button" name="snd_delete_btn" value="Видалити обране">
            <script>
                btn.onclick = function() {
                var s = document.getElementsByName("item");
                var idchecked;
                for (var i = 0; i < s.length; i++) {
                    if (s[i].checked) {
                        idchecked = s[i].id;
                    }
                }
                if (idchecked.trim() === '') {
                    alert("Ви нічого не обрали для видалення, повторіть спробу");
                }
                else{
                    location.href = "/RalenkoV_301z_Lb1-war/DeleteSelected_genre?idchecked=" + idchecked;
                };
            };
            </script>
        </body>
    </html>