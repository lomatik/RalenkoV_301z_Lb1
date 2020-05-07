<%-- 
    Document   : updatesuccess
    Created on : 06.05.2020, 14:15:05
    Author     : lomatik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Оновлення успішне</title>
        </head>
        <body>
            <h1>Оновлення прошло успішно!</h1>
            <form action="index.html">
                <input type="submit" name="index_btn" value="На головну">
            </form>
            <form>
                <input id="show_btn" type="button" name="show_btn" value="Показати весь зміст книг">
            </form>
            <form>
                <input id="show_genre_btn" type="button" name="show_btn" value="Показати весь зміст жанрів">
            </form>
            <script>
                 show_btn.onclick = function() {
                     location.href = "/RalenkoV_301z_Lb1-war/show_all_servlet";
                 }
                 show_genre_btn.onclick = function() {
                     location.href = "/RalenkoV_301z_Lb1-war/show_all_servlet_genre";
                 }
            </script>
        </body>
</html>

