package co.edu.uptc.controllerFx;

import java.util.ArrayList;

public class Prueba1 {
    private static Prueba1 instance;
    private String nameMovie;
    private int year;
    private ArrayList<String> listAuthors;
    private ArrayList<String> listActors;

    public Prueba1() {
        // constructor privado
    }

    public static Prueba1 getInstance() {
        if (instance == null) {
            instance = new Prueba1();
        }
        return instance;
    }

    public static void setInstance(Prueba1 instance) {
        Prueba1.instance = instance;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<String> getListAuthors() {
        return listAuthors;
    }

    public void setListAuthors(ArrayList<String> listAuthors) {
        this.listAuthors = listAuthors;
    }

    public ArrayList<String> getListActors() {
        return listActors;
    }

    public void setListActors(ArrayList<String> listActors) {
        this.listActors = listActors;
    }
}