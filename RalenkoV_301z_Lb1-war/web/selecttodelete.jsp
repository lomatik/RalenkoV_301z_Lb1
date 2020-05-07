<%-- 
    Document   : newjsf
    Created on : 03.05.2020, 21:32:33
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
            <title>Вибір даних для видалення</title>
        </head>
        <body>
            <jsp:useBean id="Books" class="org.javabeans.workwithderby.Show_to_delete">
            </jsp:useBean>
            <h1>Зміст</h1>
            <h2>Оберіть дані для видалення, а потім натисніть кнопку "Видалити"</h2>
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
                    location.href = "/RalenkoV_301z_Lb1-war/DeleteSelected?idchecked=" + idchecked;
                };
            };
            </script>
        </body>
    </html>
