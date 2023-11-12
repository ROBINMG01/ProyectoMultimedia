package co.edu.uptc.model;

import java.util.ArrayList;

public class Admin {
    private ArrayList<Movie> listMovies;
    private ArrayList<Movie> listSeries;

    public Admin() {
    }

    public ArrayList<Movie> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<Movie> listMovies) {
        this.listMovies = listMovies;
    }

    public ArrayList<Movie> getListSeries() {
        return listSeries;
    }

    public void setListSeries(ArrayList<Movie> listSeries) {
        this.listSeries = listSeries;
    }

    @Override
    public String toString() {
        return "Admin [listMovies=" + listMovies + ", listSeries=" + listSeries + "]";
    }
}