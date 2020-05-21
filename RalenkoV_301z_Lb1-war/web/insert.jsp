<%-- 
    Document   : insert
    Created on : 21.05.2020, 10:12:21
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
        <title>Вставка нових даних</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    </head>
    <body>
        <h1>Введіть дані для додавання</h1>
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
            <input type="button" name="insert_btn" id="btn" value="Додати в таблицю"><br>
        </form>
        <script> 
                btn.onclick = function() {
                var s = document.getElementById("check").value;
                var sofau = document.getElementById("surname").value;
                var nameofau = document.getElementById("nameauthor").value;
                var namebook = document.getElementById("namebook").value;
                var city = document.getElementById("city").value;
                var select = document.getElementById("select_");
                var selectedvalue = select.value;
                if (s.trim() == '') {
                    alert('Ви нічого не ввели в поле "Рік видавництва книги", повторіть спробу');
                }
                else if(/[^[0-9]/.test(s)){
                    alert('Ви ввели не число в поле "Рік видавництва книги", повторіть спробу');
                }
                else if (sofau.trim() == '') {
                    alert('Ви нічого не ввели в поле "Прізвище автора", повторіть спробу');
                }
                else if (nameofau.trim() == '') {
                    alert('Ви нічого не ввели в поле "Імя автора", повторіть спробу');
                }
                else if (namebook.trim() == '') {
                    alert('Ви нічого не ввели в поле "Назва книги", повторіть спробу');
                }
                else if (city.trim() == '') {
                    alert('Ви нічого не ввели в поле "Місто видавництва книги", повторіть спробу');
                }
                else if (selectedvalue.trim() == '') {
                    alert('Ви не обрали жодного жанру, повторіть спробу');
                }
                else{
                    alert(selectedvalue);
                    location.href = "/RalenkoV_301z_Lb1-war/create_new_servlet?surname_of_author="
                    + sofau + "&name_of_author=" + nameofau + "&name_of_book=" + namebook+
                    "&year_of_book=" + s + "&city_of_print=" + city + "&id_genre=" + selectedvalue +
                    "&insert_btn=%D0%9F%D0%BE%D0%BA%D0%B0%D0%B7%D0%B0%D1%82%D0%B8";
                };
            };
        </script>
    </body>
</html>
