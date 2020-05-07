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
public class Genre {
    int id;
    String namegenre;
    String typegenre;
    int yeargenre;

    public Genre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamegenre() {
        return namegenre;
    }

    public void setNamegenre(String namegenre) {
        this.namegenre = namegenre;
    }

    public String getTypegenre() {
        return typegenre;
    }

    public void setTypegenre(String typegenre) {
        this.typegenre = typegenre;
    }

    public int getYeargenre() {
        return yeargenre;
    }

    public void setYeargenre(int yeargenre) {
        this.yeargenre = yeargenre;
    }

    @Override
    public String toString() {
        return "Genre{" + "id=" + id + ", namegenre=" + namegenre + ", typegenre=" + typegenre + ", yeargenre=" + yeargenre + '}';
    }
}
