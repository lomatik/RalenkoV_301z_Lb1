<%-- 
    Document   : selecttoupdate
    Created on : 06.05.2020, 13:53:21
    Author     : lomatik
--%>
<%@page import="java.util.List"%>
<%@page import="com.library.Book"%>
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
            <jsp:useBean id="Books" class="org.javabeans.workwithderby.Show_to_Update">
            </jsp:useBean>
            <h1>Зміст</h1>
            <h2>Введіть дані які ви хочете оновити,оберіть дані для оновлення, а потім натисніть кнопку "Оновити"</h2>
            <form>
            Прізвище автора <input type="text" name="surname_of_author" id = "surname"><br>
            Ім'я автора <input type="text" name="name_of_author" id = "nameauthor"><br>
            Назва книги<input type="text" name="name_of_book" id = "namebook"><br>
            Рік видавництва книги<input type="text" name="year_of_book" id="check"><br>
            Місто видавництва <input type="text" name="city_of_print" id = "city"><br>
            </form>
            <table>
            <tr><th>Вибір</th><th>Ім'я автора</th><th>Прізвище автора</th><th>Назва книги</th><th>Рік видавництва</th><th>Місто видавництва</th></tr>
            <%if (request.getAttribute("Books") != null) {
                   for(Book item: (List<Book>) request.getAttribute("Books")) {
                       out.println("<tr><td><input type=\"radio\" name=\"item\" "
                + " id = \""+ item.getId()+"\" /></td><td>"+ item.getName_of_author() + "</td><td>" 
                + item.getSurname_of_author() + "</td><td>" + item.getName_of_book()
                + "</td><td>" + item.getYear_of_book() + "</td><td>"
                + item.getCity_of_print() + "</td></tr>");
                   }
            }%>           
            </table>
            <input id="btn" type="button" name="snd_update_btn" value="Оновити обране">
            <script>
                btn.onclick = function() {
                var с = document.getElementById("check").value;
                var s = document.getElementsByName("item");
                var idchecked;
                if(/[^[0-9]/.test(с)){
                    alert('Ви ввели не число в поле "Рік видавництва книги", повторіть спробу');
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
                        location.href = "/RalenkoV_301z_Lb1-war/UpdateSelected?idchecked=" + idchecked + 
                        "&surname_of_author=" + document.getElementById("surname").value+
                        "&name_of_author=" + document.getElementById("nameauthor").value+
                        "&name_of_book=" + document.getElementById("namebook").value+
                        "&year_of_book=" + document.getElementById("check").value+
                        "&city_of_print=" + document.getElementById("city").value;
                    }
                }
            };
            </script>
        </body>
    </html>