

package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class UserController {
    private ArrayList<Movie> listMovies;
    private ArrayList<Serie> listSeries;
    private boolean exit;
    private ArrayList<String> favoriteMovies;
    private ArrayList<String> favoriteSeries;

    public UserController(AdminController ad) {
        listMovies = new ArrayList<>();
        listSeries = new ArrayList<>();
        exit = true;
        favoriteMovies = new ArrayList<>();
        favoriteSeries = new ArrayList<>();
    }

    public boolean addMovie(String name, String description, int duration,
            ArrayList<String> actors) {
        Movie movie = new Movie(name, description, duration, actors, description);
        listMovies.add(movie);
        return true;
    }

    public boolean addSerie(String name, String description, int duration, ArrayList<String> actors,
            ArrayList<String> chapters) {
        Serie serie = new Serie(name, description, duration, actors, chapters, description);
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

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    public ArrayList<String> getFavoriteSeries() {
        return favoriteSeries;
    }
}
