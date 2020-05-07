<%-- 
    Document   : successfulyinserted
    Created on : 06.05.2020, 12:34:06
    Author     : lomatik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Успішно Додано</title>
        </head>
        <body>
            <h1>Успішно додано до бази даних</h1>
            <form action="index.html">
                <input type="submit" name="index_btn" value="На головну">
            </form>
            <form action="show_all_servlet">
                <input type="submit" name="show_btn" value="Показати весь зміст Книг">
            </form>
            <form action="show_all_servlet_genre">
                <input type="submit" name="show_genre_btn" value="Показати весь зміст Жанрів">
            </form>
        </body>
    </html>
