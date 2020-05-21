/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library;

/**
 *
 * @author lomatik
 */
public class Book {
    int id;
    String surname_of_author;
    String name_of_author;
    String name_of_book;
    int year_of_book;
    String city_of_print;
    Genre genre;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname_of_author() {
        return surname_of_author;
    }

    public void setSurname_of_author(String surname_of_author) {
        this.surname_of_author = surname_of_author;
    }

    public String getName_of_author() {
        return name_of_author;
    }

    public void setName_of_author(String name_of_author) {
        this.name_of_author = name_of_author;
    }

    public String getName_of_book() {
        return name_of_book;
    }

    public void setName_of_book(String name_of_book) {
        this.name_of_book = name_of_book;
    }

    public int getYear_of_book() {
        return year_of_book;
    }

    public void setYear_of_book(int year_of_book) {
        this.year_of_book = year_of_book;
    }

    public String getCity_of_print() {
        return city_of_print;
    }

    public void setCity_of_print(String city_of_print) {
        this.city_of_print = city_of_print;
    }

    public String getGenre() {
        return genre.getNamegenre();
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", surname_of_author=" + surname_of_author + ", name_of_author=" + name_of_author + ", name_of_book=" + name_of_book + ", year_of_book=" + year_of_book + ", city_of_print=" + city_of_print + '}';
    }
    
}
