package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class UserController {
    private ArrayList<Movie> listMovies;
    private ArrayList<Serie> listSeries;
    private boolean exit;
    private ArrayList<Movie> favoriteMovies;
    private ArrayList<Serie> favoriteSeries;


    public UserController(AdminController ad) {
        listMovies = new ArrayList<>();
        listSeries = new ArrayList<>();
        exit = true;
        favoriteMovies = new ArrayList<>();
        favoriteSeries = new ArrayList<>();
    }

    public boolean addMovie(String name, String description, int duration,
            ArrayList<String> authors, ArrayList<String> actors, String gender) {
        Movie movie = new Movie(name, description, duration, authors, actors, gender);
        listMovies.add(movie);

        return true;
    }

    public boolean addSerie(String name, String description, int duration, ArrayList<String> authors,
            ArrayList<String> chapters, ArrayList<String> actors, String gender) {
        Serie serie = new Serie(name, description, duration, authors, chapters, gender, actors);
        listSeries.add(serie);
        return true;
    }

    public ArrayList<Movie> showListMovies() {
        return listMovies;
    }

    public ArrayList<Serie> showListSeries() {
        return listSeries;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public ArrayList<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public ArrayList<Serie> getFavoriteSeries() {
        return favoriteSeries;
    }

}

